<?php
	class REST {
		public $_allow = array();
		public $_content_type = "application/json";
		public $_request = array();

		private $_method = "";
		private $_code = 200;

		public function __construct(){
			$this->inputs();
		}

		public function get_referer(){
			return $_SERVER['HTTP_REFERER'];
		}

		private function inputs(){
			switch($this->get_request_method()){
				case "POST":
					$this->_request = $this->cleanInputs($_POST);
					break;
				case "GET":
				case "DELETE":
					$this->_request = $this->cleanInputs($_GET);
					break;
				case "PUT":
					parse_str(file_get_contents("php://input"), $this->_request);
					$this->_request = $this->cleanInputs($this->_request);
					break;
				case "OPTIONS":
					// respond to preflights
					// return only the headers and not the content
					header('Access-Control-Allow-Origin: *');
    				header('Access-Control-Allow-Headers: X-Requested-With, Content-Type, Accept, Authorization');
    				exit;
				default:
					$this->response('method not allowed', 406);
					break;
			} // end switch
		} // end function :: inputs

		public function get_request_method(){
			return $_SERVER['REQUEST_METHOD'];
		}

		private function cleanInputs($data){
			$clean_input = array();
			if(is_array($data)){
				foreach($data as $k => $v){
					$clean_input[$k] = $this->cleanInputs($v);
				}
			}else{
				if(get_magic_quotes_gpc()){
					$data = trim(stripslashes($data));
				}
				$data = strip_tags($data);
				$clean_input = trim($data);
			}
			return $clean_input;
		} // end function :: cleanInputs

		public function response($data, $status){
			$this->_code = ($status)?$status:200;
			$this->set_headers();
			echo $data;
			exit;
		} // end function :: response

		private function set_headers(){
			header("HTTP/1.1 ".$this->_code." ".$this->get_status_message());
			header("Content-Type:".$this->_content_type);
			header("Access-Control-Allow-Origin: *");
			header("Access-Control-Allow-Methods: GET, POST, DELETE, PUT");
			header('Access-Control-Allow-Headers: X-Requested-With, Content-Type, Accept, Authorization');
		} // end function :: set_headers

		private function get_status_message(){
			$status = array(
				200 => 'OK',
				201 => 'Created',
				204 => 'No Content',
				401 => 'Unauthorized',
				404 => 'Not Found',
				406 => 'Not Acceptable');
			return ($status[$this->_code])?$status[$this->_code]:$status[500];
		} // end function :: get_status_message
	}
?>