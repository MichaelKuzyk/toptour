<?




  
 

$db = mysql_connect('localhost','root','') or die (mysql_error ());
 mysql_select_db('toptour' ,$db) or die (mysql_error ());
$query=mysql_query('SET NAMES cp1251');

$sql = mysql_query("SELECT  `order`.`kod_order` AS '����� ����������', `order`.`passport_nomer` AS '�������', `klient`.`pib_kl` AS 'ϲ�', `putivka`.`city` AS '̳���', `order`.`zirk_hotel` AS '������� ������',`order`.`kilkist_osib` AS 'ʳ������ ���', `order`.`date_putivka` AS '���� ������', `order`.`kilkist_osib` * (`putivka`.`price` + `hotel`.`price`) AS '������� ������'
FROM `order` 
INNER JOIN `hotel` ON `order`.`zirk_hotel` = `hotel`.`zirka` 
INNER JOIN `putivka` ON `order`.`kod_putivky` = `putivka`.`kod_putiv` 
INNER JOIN `klient` ON `order`.`passport_nomer` = `klient`.`passport_nom`
where `order`.`passport_nomer`='��344594'
ORDER BY `order`.`kod_order`
 " ,$db);
$query=mysql_query('SET NAMES cp1251');


// ������� ��������� ������������ 
  
if ($sql){
print "
<link rel='stylesheet' href='css/orderbox.css' type='text/css' >
 <div class='order_box' >
<CENTER><h2>���� ��������� ������ </h2></CENTER>
<div class='orderbox_content'>
";




 //����� ��������� ������ ����� ������������ HTML-������� 
  echo ("<table border ='1' align='center' >");
  //������� ������ ���������� 
  echo ("<tr><td align='center'>ID ����������</td><td align='center'>�������</td><td align='center'>ϲ�</td><td align='center'>̳���</td><td align='center'>������</td><td align='center'>ʳ������ ���</td><td align='center'>���� ������</td><td align='center'><b>�������</b></td></tr>");
  //������� mysql_fetch_row() ��������� ���� ������ �� ���������� 
  //� ��������� � � ������� $tablerows
  while ($tablerows = mysql_fetch_row($sql))
  {
  //������ � ����� ��� ������ ���������� ������ ������� ����� 
 
  echo("<tr><td align='center'>$tablerows[0]</td><td align='center'>$tablerows[1]</td><td align='center'>$tablerows[2]</td><td align='center'>$tablerows[3]</td><td align='center'>$tablerows[4]*</td><td align='center'>$tablerows[5]</td><td align='center'>$tablerows[6]</td><td align='center'><b>$tablerows[7]</b> ���.</td></tr> ");
  }
  echo ("</table></div>
</div>
");

  //�������� ���������� (�������������)
  mysql_close($db);


}
else{
echo "<center><h2>��²�Ͳ ��Ͳ!!!<br><h2><u>�������� ���������� ����� :</u></h2><br> </center>";
} 
exit; 
 

 
 
?>