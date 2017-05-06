<?




  
 

$db = mysql_connect('localhost','root','') or die (mysql_error ());
 mysql_select_db('toptour' ,$db) or die (mysql_error ());
$query=mysql_query('SET NAMES cp1251');

$sql = mysql_query("SELECT  `order`.`kod_order` AS 'Номер Замовлення', `order`.`passport_nomer` AS 'Паспорт', `klient`.`pib_kl` AS 'ПІБ', `putivka`.`city` AS 'Місто', `order`.`zirk_hotel` AS 'Рейтинг Готелю',`order`.`kilkist_osib` AS 'Кількість осіб', `order`.`date_putivka` AS 'Дата поїздки', `order`.`kilkist_osib` * (`putivka`.`price` + `hotel`.`price`) AS 'Вартість поїздки'
FROM `order` 
INNER JOIN `hotel` ON `order`.`zirk_hotel` = `hotel`.`zirka` 
INNER JOIN `putivka` ON `order`.`kod_putivky` = `putivka`.`kod_putiv` 
INNER JOIN `klient` ON `order`.`passport_nomer` = `klient`.`passport_nom`
where `order`.`passport_nomer`='НМ344594'
ORDER BY `order`.`kod_order`
 " ,$db);
$query=mysql_query('SET NAMES cp1251');


// Выводим сообщение пользователю 
  
if ($sql){
print "
<link rel='stylesheet' href='css/orderbox.css' type='text/css' >
 <div class='order_box' >
<CENTER><h2>Ваши последние заказы </h2></CENTER>
<div class='orderbox_content'>
";




 //после получения данных начнём формирование HTML-таблицы 
  echo ("<table border ='1' align='center' >");
  //выводим строку заголовков 
  echo ("<tr><td align='center'>ID замовлення</td><td align='center'>Паспорт</td><td align='center'>ПІБ</td><td align='center'>Місто</td><td align='center'>Готель</td><td align='center'>Кількість осіб</td><td align='center'>Дата поїздки</td><td align='center'><b>Вартість</b></td></tr>");
  //функция mysql_fetch_row() извлекает одну строку из результата 
  //и сохраняет её в массиве $tablerows
  while ($tablerows = mysql_fetch_row($sql))
  {
  //теперь в цикле для каждой полученной строки сделаем вывод 
 
  echo("<tr><td align='center'>$tablerows[0]</td><td align='center'>$tablerows[1]</td><td align='center'>$tablerows[2]</td><td align='center'>$tablerows[3]</td><td align='center'>$tablerows[4]*</td><td align='center'>$tablerows[5]</td><td align='center'>$tablerows[6]</td><td align='center'><b>$tablerows[7]</b> грн.</td></tr> ");
  }
  echo ("</table></div>
</div>
");

  //закрытие соединения (рекомендуется)
  mysql_close($db);


}
else{
echo "<center><h2>НЕВІРНІ ДАНІ!!!<br><h2><u>Перевірте коректність вводу :</u></h2><br> </center>";
} 
exit; 
 

 
 
?>