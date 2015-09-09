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
		const DB = "enrollment";

		private $db = NULL;
		private $conn = NULL;
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
			$this->response('Student API is alive: v1.0!', 200);
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
				$query="SELECT idsys_user FROM sys_user WHERE user_password = password('".$password."') AND user_name = '".$user_name."' LIMIT 1";
				$r = $this->conn->query($query) or die($this->conn->error.__LINE__);

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
		 * Lista de calificaciones para armar boleta
		 *
		 */
		private function boleta() {
			// Validate token
			$jwt = $this->getJWT();
			if($jwt!=""){
				try{
					$token = JWT::decode($jwt, $this->jwt_key, array('HS512'));
					error_log(print_r('decoded token', TRUE));
				}catch(Exception $e){
					// The token could not be decoded.
					// this is likely because the signature was not able to be verified.
					error_log(print_r('Token signature not valid', TRUE));
					$this->response('UNAUTHORIZED', 401);
				}
				// If succeed, query the database for available enrollments
				// TODO: Obtener calificaciones de los usuarios
				$query="SELECT ". 
						"note.id_student as id, note.id_cat_group, note.id_metter, note.p1, note.p2, ".
						"note.average, note.special1, note.special2, student.enrollment_id, student.name, ".
						"metter_course.id_metter, cat_group.grade, cat_group.period, student.Id, cat_group.I_cod_curso, metter_course.id_metter, metter_course.id_teacher ".
						"FROM cat_group ".
							"INNER JOIN (metter_course ".
							"INNER JOIN (student ". 
							"INNER JOIN note ".
								"ON student.Id = note.id_student) ". 
								"ON metter_course.id_metter = note.id_metter) ".
								"ON cat_group.Id_cod_curso = note.id_cat_group ".
						"WHERE (((note.id_metter) Not In (210,201,195)) AND ((student.enrollment_id)=22015)) ".
						"ORDER BY student.name";
				$r = $this->conn->query($query) or die($this->conn->error.__LINE__);
				$rows = array();
				// Obtener los datos de mysql y llenarlos en objeto de php
				while($row = $r->fetch_assoc()){
					$rows[]=$row;
				}

				$jwt = $this->tokenize($token->data->userId, $token->data->userName);
				$response_array = ['ErrorThrown'=>false, 'ResponseDescription'=>'Success','Result'=>$rows, 'Token'=>$jwt];
				// Token renewal
				$response = $this->json($response_array);
				$this->response($response, 200);
			}else{
				// No token found in the header.
				error_log(print_r('Token not found in request', TRUE));
				$this->response('Bad Request', 400);	
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