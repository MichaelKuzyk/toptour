<?

 

 $db = mysql_connect("localhost","root","") or die (mysql_error ());
  mysql_select_db("toptour" ,$db) or die (mysql_error ());
$query=mysql_query("SET NAMES cp1251");
  $sql = mysql_query("SELECT * FROM putivka" ,$db);
$query=mysql_query("SET NAMES cp1251");
  



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

 $strINSERT="INSERT INTO `order`(`passport_nomer`, `kod_putivky`, `zirk_hotel`, `kilkist_osib`, `date_putivka`, `termin`) VALUES ('" . $_POST["passport"] . "','" . $_POST["city"] . "','" . $_POST["hotel"] . "','" . $_POST["kilkist"] . "','" . $_POST["date"] . "','" . $_POST["termin"] . "')";
 $result=mysql_query($strINSERT);
  //закрытие соединение (рекомендуется)
  mysql_close($db);

// Выводим сообщение пользователю 
  

exit; 
 

 
 
?>