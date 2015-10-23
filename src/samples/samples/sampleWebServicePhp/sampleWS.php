<?php

	//function for connect with mysql
	function conMysql(){
		$conn= mysqli_connect("localhost","root","","SampleDBWS");    

		if (!$conn){  
			echo 'no connection';
			throw new SOAPFault("Error", "No connection ".mysqli_connect_error());
		}else{
			echo 'connected';
			$conn->set_charset("utf8");
			return $conn;
		}
	}
 
	
	function toSql($pString){

		if($pString == null or $pString === null){
			return 'null';
		}
		else{
			if (is_numeric($pString)){
				return $pString;
			}

			return "'".addslashes($pString)."'";
		}
	}
	 
 
    //disable wsdl cache  
	ini_set("soap.wsdl_cache_enabled", "0");  
 
	//class with functions availables in the web service
	class soap_clase{
 
		public function __construct(){
			
		}  
		
	
	
	  	function getString(){  
	
			$sql= " HELLO WORD";
				

		    $arreglo[] = array('STRING'=>$sql);
			
			return $arreglo;
	  	}


		function getEmployeeInfo($pName,$pLastName) {  
		

			$arreglo = array(); //arrat to save data
			$conn    = conMysql(); //open mysql connection
	 
		    //convert parameters to sql format
	    	$sqlName       = toSql($pName); 
			//$lastName  = $this->toSql($pLastName); //parameters for sql like not must be converted

	        //write the sql statment 
			$sql = "SELECT employee_id , name , lastname
					FROM employees
					WHERE name = $sqlName 
					AND   lastname like '%$pLastName%'";

			$result	=  mysqli_query($conn, $sql); 
	 
			//recolect data
			while ($row= mysqli_fetch_array($result)){
		  		
		  		$employee_id    = 	$row['employee_id'];
		  		$name    	    = 	$row['name'];
		  		$lastname    	= 	$row['lastname'];
	 
		  		//save data in an array
		  		$arreglo[] = array('EMPLOYEE_ID'=>$employee_id,'NAME'=>$name, 'LASTNAME'=>$lastname);
			}

			return $arreglo;
	  	}
	
	
	}
 
	//instance new soap server
	$server = new SoapServer(null, array('uri' => "urn://soluniware.com"));
	//set the class with the functions
	$server->setClass('soap_clase');
	 
	//process soap petitions
	$server->handle();
	


?>