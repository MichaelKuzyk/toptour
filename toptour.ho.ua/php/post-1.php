<?



// ----------------------------������������-------------------------- // 
 
$adminemail="kuzyk.mladshiy@yandex.ua";  // e-mail ������ 
 
 
//$date=date("d/m/y"); // �����.�����.��� 
 
$time=date("H���. i��. s�."); // ����:������:������� 
 
$backurl="http://localhost/toptour.ho.ua/";  // �� ����� ��������� ��������� ����� �������� ������ 
$backurl_false="javascript:history.back()";
//---------------------------------------------------------------------- // 
 
  
 
// ��������� ������ � ����� 


$passport=$_POST['passport']; 
 
$city=$_POST['city']; 
 
$termin=$_POST['termin']; 

$hotel=$_POST['hotel']; 
  
$date=$_POST['date']; 

$kilkist=$_POST['kilkist']; 

 
 


// ��������� � ���� ������ 
 

 $db = mysql_connect("localhost","root","") or die (mysql_error ());
  mysql_select_db("toptour" ,$db) or die (mysql_error ());
$query=mysql_query("SET NAMES cp1251");
  $sql = mysql_query("SELECT * FROM putivka" ,$db);
$query=mysql_query("SET NAMES cp1251");
  

  // ��������� ����� ������ ������  
 $client_get = mysql_query("SELECT pib_kl FROM klient where passport_nom='$passport' " ,$db);
$client = mysql_fetch_row($client_get);

 $city_get = mysql_query("SELECT city FROM putivka where kod_putiv='$city' " ,$db);
$city_name = mysql_fetch_row($city_get);
$msg=" 
�볺��: $client[0],
 ̳���: $city_name[0],
 ������:$hotel *,
 ʳ������ ���:$kilkist,
 ���� ������:$date,
 �����:$termin (���)"; 
 


/*

//����� ��������� ������ ����� ������������ HTML-������� 
  echo ("<table border ='1' align='center'>");
  //������� ������ ���������� 
  echo ("<tr><td align='center'>�������������</td><td align='center'>�����</td><td align='center'>��������� �� 1 ��������</td></tr>");
  //������� mysql_fetch_row() ��������� ���� ������ �� ���������� 
  //� ��������� � � ������� $tablerows
  while ($tablerows = mysql_fetch_row($sql))
  {
  //������ � ����� ��� ������ ���������� ������ ������� ����� 
 
  echo("<tr><td align='center'>$tablerows[0]</td><td align='center'>$tablerows[1]</td><td align='center'>$tablerows[2]</td></tr> ");
  }
  echo "</table>";
*/
 $strINSERT="INSERT INTO `order`(`passport_nomer`, `kod_putivky`, `zirk_hotel`, `kilkist_osib`, `date_putivka`, `termin`) VALUES ('" . $_POST["passport"] . "','" . $_POST["city"] . "','" . $_POST["hotel"] . "','" . $_POST["kilkist"] . "','" . $_POST["date"] . "','" . $_POST["termin"] . "')";
 $result=mysql_query($strINSERT);
  //�������� ���������� (�������������)
  mysql_close($db);

// ������� ��������� ������������ 
  
if ($result){
  // ���������� ������ ������  
mail("$adminemail", " ���� ����������", "$msg"); 
/*
print "<center><script language='Javascript'><!-- 
function reload() {location = \"$backurl\"}; setTimeout('reload()', 6000); 
//--></script><center> </center>
 <center><p> Your message being delivering. Please Wait...</p></center><center>";
    echo "<h2>���������� �������� � ���� ������ </h2>";
*/
?>
<script type="text/javascript">   
		alert("���� ���������� ��������! ���������� ����� � ���� ��'������� ��� ��������! ������� ���!");
		location.replace('index.php');
     </script>
    <?php
}
else{
/*print "<center><script language='Javascript'><!-- 
function reload() {location = \"$backurl_false\"}; setTimeout('reload()', 112000); 
//--></script><center> 
 </center><center>";
    echo "<h2>��²�Ͳ ��Ͳ!!!<br><u>�������� ���������� ����� :</u><br> $msg</h2></center>";
*/
?> 
     <script type="text/javascript">   alert("����� ������� �� ���������� �� ������ �볺���� \n ��������� ��� ������ ���������.");  
//window.history.back();
location.replace('index.php');

fadeIn();
</script>
    <?php
    	
} 
exit; 
 

 
 
?>