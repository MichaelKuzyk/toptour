<?



// ----------------------------конфигурация-------------------------- // 
 
$adminemail="kuzyk.mladshiy@yandex.ua";  // e-mail админа 
 
 
//$date=date("d/m/y"); // число.месяц.год 
 
$time=date("Hгод. iхв. sс."); // часы:минуты:секунды 
 
$backurl="http://localhost/toptour.ho.ua/";  // На какую страничку переходит после отправки письма 
$backurl_false="javascript:history.back()";
//---------------------------------------------------------------------- // 
 
  
 
// Принимаем данные с формы 


$passport=$_POST['passport']; 
 
$city=$_POST['city']; 
 
$termin=$_POST['termin']; 

$hotel=$_POST['hotel']; 
  
$date=$_POST['date']; 

$kilkist=$_POST['kilkist']; 

 
 


// Сохраняем в базу данных 
 

 $db = mysql_connect("localhost","root","") or die (mysql_error ());
  mysql_select_db("toptour" ,$db) or die (mysql_error ());
$query=mysql_query("SET NAMES cp1251");
  $sql = mysql_query("SELECT * FROM putivka" ,$db);
$query=mysql_query("SET NAMES cp1251");
  

  // Формируем текст письма админу  
 $client_get = mysql_query("SELECT pib_kl FROM klient where passport_nom='$passport' " ,$db);
$client = mysql_fetch_row($client_get);

 $city_get = mysql_query("SELECT city FROM putivka where kod_putiv='$city' " ,$db);
$city_name = mysql_fetch_row($city_get);
$msg=" 
Клієнт: $client[0],
 Місто: $city_name[0],
 Готель:$hotel *,
 Кількість осіб:$kilkist,
 Дата поїздки:$date,
 Термін:$termin (днів)"; 
 


/*

//после получения данных начнём формирование HTML-таблицы 
  echo ("<table border ='1' align='center'>");
  //выводим строку заголовков 
  echo ("<tr><td align='center'>Идентификатор</td><td align='center'>Город</td><td align='center'>Стоимость на 1 человека</td></tr>");
  //функция mysql_fetch_row() извлекает одну строку из результата 
  //и сохраняет её в массиве $tablerows
  while ($tablerows = mysql_fetch_row($sql))
  {
  //теперь в цикле для каждой полученной строки сделаем вывод 
 
  echo("<tr><td align='center'>$tablerows[0]</td><td align='center'>$tablerows[1]</td><td align='center'>$tablerows[2]</td></tr> ");
  }
  echo "</table>";
*/
 $strINSERT="INSERT INTO `order`(`passport_nomer`, `kod_putivky`, `zirk_hotel`, `kilkist_osib`, `date_putivka`, `termin`) VALUES ('" . $_POST["passport"] . "','" . $_POST["city"] . "','" . $_POST["hotel"] . "','" . $_POST["kilkist"] . "','" . $_POST["date"] . "','" . $_POST["termin"] . "')";
 $result=mysql_query($strINSERT);
  //закрытие соединение (рекомендуется)
  mysql_close($db);

// Выводим сообщение пользователю 
  
if ($result){
  // Отправляем письмо админу  
mail("$adminemail", " НОВЕ ЗАМОВЛЕННЯ", "$msg"); 
/*
print "<center><script language='Javascript'><!-- 
function reload() {location = \"$backurl\"}; setTimeout('reload()', 6000); 
//--></script><center> </center>
 <center><p> Your message being delivering. Please Wait...</p></center><center>";
    echo "<h2>Информация занесена в базу данных </h2>";
*/
?>
<script type="text/javascript">   
		alert("Ваше замовлення прийнято! Найближчим часом з Вами зв'яжеться наш менеджер! Гарного дня!");
		location.replace('index.php');
     </script>
    <?php
}
else{
/*print "<center><script language='Javascript'><!-- 
function reload() {location = \"$backurl_false\"}; setTimeout('reload()', 112000); 
//--></script><center> 
 </center><center>";
    echo "<h2>НЕВІРНІ ДАНІ!!!<br><u>Перевірте коректність вводу :</u><br> $msg</h2></center>";
*/
?> 
     <script type="text/javascript">   alert("Даний паспорт не закріплений за жодним клієнтом \n Пропонуємо Вам пройти реєстрацію.");  
//window.history.back();
location.replace('index.php');

fadeIn();
</script>
    <?php
    	
} 
exit; 
 

 
 
?>