<?

 

 $db = mysql_connect("localhost","root","") or die (mysql_error ());
  mysql_select_db("toptour" ,$db) or die (mysql_error ());
$query=mysql_query("SET NAMES cp1251");
  $sql = mysql_query("SELECT * FROM putivka" ,$db);
$query=mysql_query("SET NAMES cp1251");
  



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

 $strINSERT="INSERT INTO `order`(`passport_nomer`, `kod_putivky`, `zirk_hotel`, `kilkist_osib`, `date_putivka`, `termin`) VALUES ('" . $_POST["passport"] . "','" . $_POST["city"] . "','" . $_POST["hotel"] . "','" . $_POST["kilkist"] . "','" . $_POST["date"] . "','" . $_POST["termin"] . "')";
 $result=mysql_query($strINSERT);
  //�������� ���������� (�������������)
  mysql_close($db);

// ������� ��������� ������������ 
  

exit; 
 

 
 
?>