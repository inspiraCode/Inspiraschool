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
		const SECURED = true;

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
				$query="select "
				." metter_course.id_metter_course, cat_group.grade, cat_group.day_trip, cat_metter.metter_name, cat_teacher.name, cat_group.period, cat_group.year_of_course"
				." from metter_course "
				." inner join cat_group "
				." on metter_course.id_group = cat_group.id_group"
				." inner join cat_metter"
				." on metter_course.id_metter = cat_metter.id_metter"
				." inner join cat_teacher"
				." on metter_course.id_teacher = cat_teacher.id_teacher"
				." where cat_teacher.user_name = '".$token->data->userName."' and cat_group.period = '".$this->calcularPeriodo()."'";
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
			// de ENERO A ABRIL
			// de MAYO A AGOSTO
			// de SEPTIEMBRE A DICIEMBRE
			return "SEPTIEMBRE-DICIEMBRE";
		}

		/*
		 * Lista de alumnos para capturar calificaciones
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
				$query=" select "
				." student.id_student , metter_course.id_group , metter_course.id_metter_course , student.student_name, student.enroll_number, student.status, "
				." note.p1, note.p2 "
				." from student "
				." inner join metter_course "
				." on student.id_group = metter_course.id_group "
				." left join note "
 				." on note.id_metter_course = metter_course.id_metter_course "
				." where metter_course.id_metter_course =  ".$_REQUEST['course_id']
				." order by student.student_name ";
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
				$column_names = array('id_student', 'id_group_assignment', 'id_score_type', 'score', 'comments');
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
				//error_log(print_r($insertSentence, TRUE));
				if(!empty($form)){
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
					$this->response($this->json($success),200);
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