<!doctype html>
  <html>
    <head>
       <meta charset="UTF-8"/>
    </head>

<?php
	//init own client soap
	/*locacion: url of the service
	* uri: space of names of service SOAP
	* login: user mysql
	* password: password mysql
	*/
	$client = new SoapClient(null, array(
		  'location' => $_POST["serverLocation"],
		  'uri'      => "urn://soluniware.com",      
		  'login' => 'root',
		  'password' => ''));
	 

	try{

	  
	  $limit = count($_POST);
	  $cont = 0;
	  $parametros = array();
	  foreach($_POST as $key=>$value){
	       
		   // si el contador es diferente de cero o es diferente del los dos ultimos
		   //ya que el primero es el nombre de la funcion y los dos ultimos es el submit y el la ruta del servicio
		    if ($cont != 0 && $cont != ($limit -1)&& $cont != ($limit -2)) {
				//echo $_POST[$key] . "<br>";
				if (strlen($_POST[$key]) == 0){
					$addToArray = "";
				}
				else {
					$addToArray = $_POST[$key];
				}
				array_push($parametros,$addToArray);
			}
			$cont ++;
	  }
	  
	  foreach($_POST as $k => $v) {
			if(strpos($k, 'item_number') === 0) {
				echo "$k = $v";
			}
		}
	  
	  //$return = $client->__soapCall($_POST["funcion"],array("","","",""));
	    $return = $client->__soapCall($_POST["funcion"],$parametros);	  
	  
	  //Print de function called with the parameters values
	  echo "******** FUNCION *******************<br>";
	  $limit = count($_POST);
	  $cont = 0;
	  echo $_POST["funcion"]."(<br>";
	  foreach($_POST as $key => $value) {
			if ($cont != 0 && $cont != ($limit -1) && $cont != ($limit -2)) {
				echo "$key => $value <br>";
			}
			$cont++;
	  }
	  echo ")<br>";
	  echo "******************************<br><br>";
	  
	 
	  
	  //count the size of the array returned for the web service
	  $limite= count($return);

	 
	 echo "RESULTS<br>";
	 
	  //Print the results
	  for ($i=0;$i<$limite; $i++){		
		echo "*****************<br>";
		foreach($return[$i] as $key => $value) {
			echo "$key = $value <br>";
		}
		}
	  
	  
//------------------------------------	 	  
	  
	  
	}catch (SoapFault $e){
	  //print errors
	  echo $e->getMessage();
	}
	?>
	
	
</html>