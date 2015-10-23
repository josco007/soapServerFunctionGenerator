<?php 
 $urlServerLocation = "http://localhost/sampleWebServicePhp/sampleWS.php ";
?>
<!doctype html>
  <html>
    <head>
       <meta charset="UTF-8"/>
       <title> TESTEO DE SERVICIOS WEB</title>
    </head>
	
	Informacion: <br>
	*************************************************************************************************<br> 
	* uri : urn://soluniware.com                                                                     <br> 
	* server location : <?php echo $urlServerLocation; ?>                                            <br> 
	*************************************************************************************************<br>
<form method="post" action="consumeWS.php">
  <input type= "text" name="funcion" value = readonly>
  <input type="text" name="{		conn=mysqli_connect"localhost"" placeholder="{		conn=mysqli_connect"localhost"">
  <input type="text" name=""root"" placeholder=""root"">
  <input type="text" name=""12345"" placeholder=""12345"">
  <input type="text" name=""SampleDBWS"" placeholder=""SampleDBWS"">
  <input type="hidden" name="serverLocation" value=<?php echo "$urlServerLocation"; ?>>
  <input type="submit" name="enviar" value="enviar">
</form>


<form method="post" action="consumeWS.php">
  <input type= "text" name="funcion" value =conMysql readonly>
  <input type="text" name="{			conn=conMysql" placeholder="{			conn=conMysql">
  <input type="hidden" name="serverLocation" value=<?php echo "$urlServerLocation"; ?>>
  <input type="submit" name="enviar" value="enviar">
</form>


<form method="post" action="consumeWS.php">
  <input type= "text" name="funcion" value =toSql readonly>
  <input type="text" name="pString" placeholder="pString">
  <input type="hidden" name="serverLocation" value=<?php echo "$urlServerLocation"; ?>>
  <input type="submit" name="enviar" value="enviar">
</form>


<form method="post" action="consumeWS.php">
  <input type= "text" name="funcion" value =__construct readonly>
  <input type="hidden" name="serverLocation" value=<?php echo "$urlServerLocation"; ?>>
  <input type="submit" name="enviar" value="enviar">
</form>


<form method="post" action="consumeWS.php">
  <input type= "text" name="funcion" value =getString readonly>
  <input type="hidden" name="serverLocation" value=<?php echo "$urlServerLocation"; ?>>
  <input type="submit" name="enviar" value="enviar">
</form>


<form method="post" action="consumeWS.php">
  <input type= "text" name="funcion" value =getEmployeeInfo readonly>
  <input type="text" name="pName" placeholder="pName">
  <input type="text" name="pLastName" placeholder="pLastName">
  <input type="hidden" name="serverLocation" value=<?php echo "$urlServerLocation"; ?>>
  <input type="submit" name="enviar" value="enviar">
</form>


</html>
