<?php
	require_once("Rest.inc.php");

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

		private function ping(){
			$this->response('RESTful API is alive!', 200);
		}

		private function subscribe(){
			$this->response('NOT IMPLEMENTED YET', 204);	
		}

	} // end API class

	// Library initialization
	$api = new API;
	$api->processApi();
?>