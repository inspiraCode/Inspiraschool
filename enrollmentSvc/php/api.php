<?php
	require_once("secret.php"); // Includes the secret keys for jwt ($jwt_key)
	require_once("Rest.inc.php");
	require_once("jwt_helper.php");

	class API extends REST{
		public $data = "";

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
			$func = strtolower(trim(str_replace("/", "", $_REQUEST['x'])));
			if((int)method_exists($this, $func)>0)
				$this->$func();
			else
				$this->response('', 404); // If the method is not found "PAGE NOT FOUND".
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
			$this->response('RESTful API is alive: v0.4!', 200);
		}

		private function login(){
			if($this->get_request_method() != "POST"){
				$this->response($this->get_request_method().' NOT ALLOWED',406);
			}

			$loginForm = json_decode(file_get_contents('php://input'), TRUE);
			$user_name = $loginForm['username'];
			$password = $loginForm['password'];

			// TODO: Validate username and password in mysql database
			// use "SELECT password($password), password FROM sys_users WHERE user_name = ?"

			// TODO: If succeed, tokenize as in http://www.sitepoint.com/php-authorization-jwt-json-web-tokens/
			// and respond in json format

		}

		private function enrollments(){
			if($this->get_request_method() != "POST"){
				$this->response($this->get_request_method().' NOT ALLOWED',406);
			}

			// TODO: Validate token
			// TODO: If succeed, query the database for available enrollments

			// TODO: Token renewal
		}

		private function subscribe(){
			if($this->get_request_method() != "POST"){
				$this->response($this->get_request_method().' NOT ALLOWED',406);
			}

			$enrollmentForm = json_decode(file_get_contents('php://input'), TRUE);
			$column_names = array('shift', 'course', 'course_plan', 'first_name', 'last_name',  'gender',  
				'birth_place',  'birth_date',  'address_1',  'address_2',  'city',  'state',  'zip',  'phone_home',  
				'phone_mobile',  'person_unique_id',  'from_school',  'email');
			$keys = array_keys($enrollmentForm);
			$columns = '';
			$values = '';

			$enrollmentForm['birth_date'] = preg_replace('#(\d{2})/(\d{2})/(\d{4})#', '$3-$1-$2', $enrollmentForm['birth_date']);
			
			foreach($column_names as $desired_key){
				if(!in_array($desired_key, $keys)){
					$$desired_key = '';
				}else{
					$$desired_key = $enrollmentForm[$desired_key];
				}
				$columns = $columns.$desired_key.',';
				$values = $values."'".$$desired_key."',";
			}
			$insertSentence = "INSERT INTO enrollment(".trim($columns,',').") VALUES(".trim($values,',').")";
			$insertSentence = utf8_decode($insertSentence);
			//error_log(print_r($insertSentence, TRUE));
			if(!empty($enrollmentForm)){
				$result = $this->conn->query($insertSentence) or die($this->conn->error.__LINE__);
				$enrollmentForm['folio'] = $this->conn->insert_id;
				//error_log(print_r($enrollmentForm, TRUE));
				$success = array('status' => "Success", "msg" => "Enrollment form created.", "data" => $enrollmentForm);
				$this->response($this->json($success),200);
			}else{
				// EMPTY REQUEST, EMPTY RESPONSE
				$this->response('',204);	
			}
		}

	} // end API class

	// Library initialization
	$api = new API;
	$api->processApi();
?>