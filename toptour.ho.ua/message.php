<? 
// ----------------------------������������-------------------------- // 
 
$adminemail="kuzyk.mladshiy@yandex.ua";  // e-mail ������ 
 
 
$date=date("d.m.y"); // �����.�����.��� 
 
$time=date("H:i"); // ����:������:������� 
 
$backurl="http://localhost/toptour.ho.ua/contacts.html";  // �� ����� ��������� ��������� ����� �������� ������ 
 
//---------------------------------------------------------------------- // 
 
  
 
// ��������� ������ � ����� 
 
$name=$_POST['name']; 
 
$email=$_POST['email']; 
 
$msg=$_POST['message']; 
 
  
 
// ��������� ���������� e-mail 
 
if (!preg_match("|^([a-z0-9_\.\-]{1,20})@([a-z0-9\.\-]{1,20})\.([a-z]{2,4})|is", 
strtolower($email))) 
 
 { 
 
  echo 
"<center>��������� <a 
href='javascript:history.back(1)'><B>�����</B></a>. �� 
������� �������� ������!"; 
 
  } 
 
 else 
 
 { 
 
 
$msg="  
���: $name 
E-mail: $email
���������:
 $msg
 
 
"; 
 
  
 
 // ���������� ������ ������  
 
mail("$adminemail", "$date $time ��������� 
�� $name", "$msg"); 
 
  
 
// ��������� � ���� ������ 
 
$f = fopen("message.txt", "a+"); 
 
fwrite($f," \n $date $time ��������� �� $name"); 
 
fwrite($f,"\n $msg "); 
 
fwrite($f,"\n ---------------"); 
 
fclose($f); 
 
  
 
// ������� ��������� ������������ 
 
print "<script language='Javascript'><!-- 
function reload() {location = \"$backurl\"}; setTimeout('reload()', 6000); 
//--></script> 
 
$msg 
 
<p>��������� ����������! ���������, ������ �� ������ �������������� �� ������� ��������...</p>";  
exit; 
 
 } 
 
?>