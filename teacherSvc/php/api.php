<?php
	require_once("secret.php"); // Includes the secret keys for jwt ($jwt_key)
	require_once("Rest.inc.php");
	require_once("jwt_helper.php");

	class API extends REST{
		public $data = "";
		public $jwt_key = "";

		const DB_SERVER = "127.0.0.1";
		const DB_USER = "root";
		const DB_PASSWORD = "";
		const DB = "school_control";
		const DB_AUTH = "inspiraschool_realm";
		const SECURED = false;

		private $db = NULL;
		private $conn = NULL;
		private $conn_auth = NULL;
		public function __construct(){
			parent::__construct();
			$this->dbConnect();
		}

		/**
		 * Connects to the MySql database
		 */
		private function dbConnect(){
			$this->conn = new mysqli(self::DB_SERVER, self::DB_USER, self::DB_PASSWORD, self::DB);
		}

		public function processApi(){
			try{
				$func = strtolower(trim(str_replace("/", "", $_REQUEST['x'])));
				if((int)method_exists($this, $func)>0)
					$this->$func();
				else
					$this->response('', 404); // If the method is not found "PAGE NOT FOUND".
			}catch(Exception $e){
				// The token could not be decoded.
				// this is likely because the signature was not able to be verified.
				$response_array = ['ErrorThrown'=>true, 'ResponseDescription'=>$e->getMessage(),'Result'=>'', 'Token'=>''];
				// Token renewal
				$response = $this->json($response_array);
				$this->response($response, 200);
			}
		}

		/**
		 * Encode array into JSON
		 */
		private function json($data){
			if(is_array($data)){
				return json_encode($data);
			}
		}

		private function ping(){
			$sSecured = 'Secured';
			if(!self::SECURED){
				$sSecured = 'Not Secured';
			}
			$this->response('Teachers API is alive: v1.0 '.$sSecured, 200);
		}

		private function login(){
			if($this->get_request_method() != "POST"){
				$this->response($this->get_request_method().' NOT ALLOWED',406);
			}

			$loginForm = json_decode(file_get_contents('php://input'), TRUE);
			$user_name = $loginForm['username'];
			$password = $loginForm['password'];

			if(!empty($user_name) and !empty($password)){
				// Validate username and password in mysql database
				$this->conn_auth = new mysqli(self::DB_SERVER, self::DB_USER, self::DB_PASSWORD, self::DB_AUTH);

				$query="SELECT idsys_user FROM sys_user WHERE user_password = sha1('".$password."') AND user_name = '".$user_name."' LIMIT 1";
				$r = $this->conn_auth->query($query) or die($this->conn_auth->error.__LINE__);

				if($r->num_rows > 0) {
					$row = $r->fetch_assoc();
					$user_id = $row["idsys_user"];

					// If user validation succeed, tokenize as in http://www.sitepoint.com/php-authorization-jwt-json-web-tokens/
					$jwt = $this->tokenize($user_id, $user_name);
					//error_log(print_r($jwt, TRUE));
					$response_array = ['jwt'=>$jwt];
					$this->response($this->json($response_array), 200);
				}
				
				$error = array('status'=>"Failed", "msg"=>"Invalid user name or Password");
				$this->response($this->json($error), 401);
			}

			$error = array('status'=>"Failed", "msg"=>"Invalid user name or Password");
			$this->response($this->json($error), 401);
			
		}

		/*
		 * Lista de materias por maestro.
		 *
		 */
		private function materias() {
			// Validate token
			$jwt = $this->getJWT();
			if($jwt!="" || !self::SECURED){
				date_default_timezone_set('America/Chihuahua');
				if(self::SECURED){
					try{
						$token = JWT::decode($jwt, $this->jwt_key, array('HS512'));
						error_log(print_r('decoded token', TRUE));
					}catch(Exception $e){
						// The token could not be decoded.
						// this is likely because the signature was not able to be verified.
						error_log(print_r('Token signature not valid', TRUE));
						$this->response('UNAUTHORIZED', 401);
					}
					$user_name = $token->data->userName;
				} else {
					$user_name = 'meliton.gonzalez';
				}
				
				// If succeed, query the database for available enrollments
				// Obtener calificaciones de los usuarios
				$query="select "
				." id, grade, day_trip, assignment_name, name, period, year_of_course"
				." FROM cross_group_assignment xga "
				." INNER JOIN cat_group ON xga.id_group = cat_group.id_group"
				." INNER JOIN cat_assignment ON xga.id_assignment = cat_assignment.id_assignment"
				." INNER JOIN cat_teacher ON xga.id_teacher = cat_teacher.id_teacher"
				." WHERE cat_teacher.user_name = '".$user_name."'"
				." and cat_group.period = '".$this->calcularPeriodo()."'"
				." and cat_group.year_of_course = '".date('Y')."'";
				$r = $this->conn->query($query) or die($this->conn->error.__LINE__);
				$rows = array();
				// Obtener los datos de mysql y llenarlos en objeto de php
				while($row = $r->fetch_assoc()){
					$rows[]=array_map('utf8_encode', $row);
				}

				if(self::SECURED){
					$jwt = $this->tokenize($token->data->userId, $token->data->userName);
					$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$rows, 'Token'=>$jwt];
				} else {
					$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$rows, 'Token'=>'NOT SECURED INTERFACE'];
				}
				
				// Token renewal
				$response = $this->json($response_array);
				$this->response($response, 200);
			}else{
				// No token found in the header.
				error_log(print_r('Token not found in request', TRUE));
				$this->response('UNAUTHORIZED', 401);	
			}
		}

		private function calcularPeriodo() {
			// Conocer fecha actual
			$cur_month = date('n');
			$result = "";
			switch ($cur_month) {
				// de ENERO A ABRIL
				case 1:
				case 2:
				case 3:
				case 4:
					$result = "ENERO-ABRIL";
					break;
				// de MAYO A AGOSTO
				case 5:
				case 6:
				case 7:
				case 8:
					$result = "MAYO-AGOSTO";
					break;
				// de SEPTIEMBRE A DICIEMBRE
				default:
					$result = "SEPTIEMBRE-DICIEMBRE";	
			}
			return $result;
		}

		/*
		 * Lista de alumnos para capturar calificaciones
		 * //server/tearcherService/alumnos?course_id=357
		 *
		 */
		private function alumnos() {
			// Validate token
			$jwt = $this->getJWT();
			if($jwt!="" || !self::SECURED){
				if(self::SECURED){
					try{
						$token = JWT::decode($jwt, $this->jwt_key, array('HS512'));
						error_log(print_r('decoded token', TRUE));
					}catch(Exception $e){
						// The token could not be decoded.
						// this is likely because the signature was not able to be verified.
						error_log(print_r('Token signature not valid', TRUE));
						$this->response('UNAUTHORIZED', 401);
					}
				}
				// If succeed, query the database for available enrollments
				// Obtener calificaciones de los usuarios
				$query=" SELECT xga.id, cat_student.id_student, cat_student.student_name, cat_student.lastname, cat_student.enroll_number,"
				." (SELECT partial_one FROM ctrl_score WHERE ctrl_score.id_student = cat_student.id_student AND ctrl_score.id_group_assignment = xga.id) AS partial_one,"
    			." (SELECT partial_two FROM ctrl_score WHERE ctrl_score.id_student = cat_student.id_student AND ctrl_score.id_group_assignment = xga.id) AS partial_two,"
    			." (SELECT final FROM ctrl_score WHERE ctrl_score.id_student = cat_student.id_student AND ctrl_score.id_group_assignment = xga.id) AS final,"
			    ." (SELECT comments FROM ctrl_score WHERE ctrl_score.id_student = cat_student.id_student AND ctrl_score.id_group_assignment = xga.id) AS comments"
				." FROM cross_group_assignment xga"
					." INNER JOIN cross_student_group_assignment xsga ON xga.id = xsga.id_group_assignment"
					." INNER JOIN cat_student ON xsga.id_student = cat_student.id_student"
				." WHERE id = ".$_REQUEST['course_id']
				." ORDER BY lastname, student_name";
				$r = $this->conn->query($query) or die($this->conn->error.__LINE__);
				$rows = array();
				// Obtener los datos de mysql y llenarlos en objeto de php
				while($row = $r->fetch_assoc()){
					$rows[]=array_map('utf8_encode', $row);
				}

				if(self::SECURED){
					$jwt = $this->tokenize($token->data->userId, $token->data->userName);
					$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$rows, 'Token'=>$jwt];
				} else {
					$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$rows, 'Token'=>'NOT SECURED INTERFACE'];
				}
				// Token renewal
				$response = $this->json($response_array);
				$this->response($response, 200);
			}else{
				// No token found in the header.
				error_log(print_r('Token not found in request', TRUE));
				$this->response('UNAUTHORIZED', 401);	
			}
		}

		private function calificar(){
			if($this->get_request_method() != "POST"){
				$this->response($this->get_request_method().' NOT ALLOWED',406);
			}

			// Validate token
			$jwt = $this->getJWT();
			if($jwt!="" || !self::SECURED){
				if(self::SECURED){
					try{
						$token = JWT::decode($jwt, $this->jwt_key, array('HS512'));
						error_log(print_r('decoded token', TRUE));
					}catch(Exception $e){
						// The token could not be decoded.
						// this is likely because the signature was not able to be verified.
						error_log(print_r('Token signature not valid', TRUE));
						$this->response('UNAUTHORIZED', 401);
					}
				}
				$form = json_decode(file_get_contents('php://input'), TRUE);
				$column_names = array('id_student', 'id_group_assignment', 'partial_one', 'partial_two', 'final', 'comments');
				$keys = array_keys($form);
				$columns = '';
				$values = '';
				
				foreach($column_names as $desired_key){
					if(!in_array($desired_key, $keys)){
						$$desired_key = '';
					}else{
						$$desired_key = $form[$desired_key];
					}
					$columns = $columns.$desired_key.',';
					$values = $values."'".$$desired_key."',";
				}
				$insertSentence = "INSERT INTO ctrl_score(".trim($columns,',').") VALUES(".trim($values,',').")";
				$insertSentence = utf8_decode($insertSentence);

				$deleteSentence = "DELETE FROM ctrl_score WHERE id_student=".$form['id_student']." AND id_group_assignment=".$form['id_group_assignment'];

				//error_log(print_r($insertSentence, TRUE));
				if(!empty($form)){
					$result = $this->conn->query($deleteSentence);
					$result = $this->conn->query($insertSentence) or die($this->conn->error.__LINE__);
					//$form['folio'] = $this->conn->insert_id;
					//error_log(print_r($form, TRUE));
					//$success = array('status' => "Success", "msg" => "Enrollment form created.", "data" => $form);
					if(self::SECURED){
						// Token renewal
						$jwt = $this->tokenize($token->data->userId, $token->data->userName);
						$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$form, 'Token'=>$jwt];
					} else {
						$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$form, 'Token'=>'NOT SECURED INTERFACE'];
					}
					$response = $this->json($response_array);
					$this->response($response,200);
				}else{
					// EMPTY REQUEST, EMPTY RESPONSE
					$this->response('',204);	
				}
			}else{
				// No token found in the header.
				error_log(print_r('Token not found in request', TRUE));
				$this->response('UNAUTHORIZED', 401);
			}
		}

		private function tokenize($user_id, $user_name){
			error_log(print_r('Tokenize for '.$user_id.': '.$user_name, TRUE));
			// If user validation succeed, tokenize as in http://www.sitepoint.com/php-authorization-jwt-json-web-tokens/
			$tokenId = base64_encode(mcrypt_create_iv(32));
			$issuedAt = time();
			$notBefore = $issuedAt + 10; // Adding 10 seconds
			$expire = $notBefore + 300; // Adding 5 minutes (300 seconds)
			$serverName = gethostname();

			/*
			 * Create the token as an array
			 */
			$_jwt = [
				'iat' => $issuedAt,
				'jti' => $tokenId,
				'iss' => $serverName,
				'nbf' => $notBefore,
				'exp' => $expire,
				'data' => [
					'userId' => $user_id,
					'userName' => $user_name
				]
			];

			error_log(print_r('Tokenization Success', TRUE));
			// and respond in json format
			return JWT::encode($_jwt, $this->jwt_key, 'HS512');
		}

		

		private function getJWT() {
			foreach(getallheaders() as $name => $value) {
				if($name == 'Authorization') {
					list($jwt) = sscanf($value, 'Bearer %s');
					return $jwt;
				}
			}

			return "";
		}

	} // end API class

	// Library initialization
	$api = new API;
	// $jwt_key stored in include file "secret.php"
	/*
	 * include file example:
	 * <?php
	 * $jwt_key = "replace with secret phrase";
	 * ?>
	 */
	$api->jwt_key = $jwt_key;
	$api->processApi();
?>