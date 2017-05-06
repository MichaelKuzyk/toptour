<!DOCTYPE HTML>
<html>
<title>Order form</title>
<head>
<link rel="stylesheet" href="css/style_index.css" type="text/css" media="screen"/>
<script src="scripts/jquery.min.js"></script>
<script src="scripts/fade.js"></script>
<script src="scripts/forms.js"></script>
<meta name="viewport" content="width=device-width">

</head>

<body onload="myFunction()" >

 <a  id="go" class="show-btn" 
 
 onclick = 
 "
 fadeIn()" > 
 <input type="button" class="reg_btn" value="" > 
 </a>

<div class="content_reg">
<div class="datauser">


<?php
//------------------Заполнение списка городов для заказа------------//
$con = mysql_connect("localhost","root","");
 $db = mysql_select_db("toptour",$con);
$query=mysql_query("SET NAMES cp1251");
 $get=mysql_query("SELECT kod_putiv,city FROM putivka ORDER BY kod_putiv ASC");

$option = '';
 while($row = mysql_fetch_assoc($get))
{
  $option .= '<option value = "'.$row['kod_putiv'].'">'.$row['city'].'</option>';
}
//-----------------------------------------------------------------//
?>



<form action= "post-1.php" method= "POST"  autocomplete="off">
<input type="text" id="passport" name="passport"  placeholder="серія та № паспорта (АА123456)" size="15" required maxlength="8" pattern="[А-Я,І,Ї,Є]{2}[0-9]{6}" oninput="myPassport()" >

<select id="city" name="city" required="" >

<option value="" selected  disabled>Оберіть місто</option>
<?php echo $option; ?>
  
   </select> 
<input type="number" id="termin" name="termin"  min="1" max="10" placeholder="тривалість поїздки" required>
<select id="hotel "name="hotel" required>

	<option value="" selected  disabled>Вкажіть рейтинг готелю</option>
	<option value="0">Без готелю</option>
	<option value="1"> *</option>
	<option value="2">**</option>
	<option value="3">***</option>
	<option value="4">****</option>
	<option value="5">*****</option>
   </select>
<input type="date" id="date" name="date" class="date" placeholder="дата поїздки" size="20" required   min="1960-01-01">


<input type="number" id="kilkist" name="kilkist"  min="1" max="10" placeholder="кількість осіб"required> 

<div class="layer1">
<input type="submit"   Value=" "  id="zzz"> 



</div>
</form>

</div>
<footer>

 <a href=http://localhost/toptour.ho.ua>
<input type="button" class="button" value="" width="50%">
</a>
  
 <a href=http://localhost/toptour.ho.ua/php/order_box.php target=blank2>
<input type="button" class="button1" value="" >
</a>



</fotter>
</div> 
 	
 <div id="fade"  class="black-overlay" id="reg_client"  onload="myFunction()">
 
<div id="regform" class="regform" >

	<div id="envelope" class="envelope">
		<a class="close-btn" onclick="fade()" > 
		
		</a>
		
		<div class="title" >Реєстрація</div>
		
		
		<form id="registration" action="reg1.php" method="post" >
<!--ПАСПОРТ Пароль Пароль ПІБ ДР  ТФ МИЛО-->
		<input type="text" id="passport_reg" name="passport" class="reg_passport" placeholder="серія та № паспорта (АА123456)"  title="НМ223311 " size="15" maxlength="8" pattern="[А-Я,І,Ї,Є]{2}[0-9]{6}" oninput="myPassport_reg()" autocomplete="off" required >
		<input type="password" id="regpassw1" name="password" class="Rpassword" placeholder="* Пароль" minlength="4" title="Пароль" autocomplete="off" required autocomplete="off"  oninput="clear_pass2()">
<div id="pass2" style="
position:relative; left:0px;
display:flex;
flex-direction:row; justify-content: flex-start;  align-items: stretch;
" >		
<input type="password" id="regpassw2" name="password2" class="Rpassword2"  placeholder="* Повторіть пароль" title="Підтвердження пароля" autocomplete="off" required autocomplete="off" oninput="reg_submit_check()" >
<img src="images/false.png" id="false" class="true_false" style="display:none;">
<img src="images/true.png" id="true" class="true_false"  style="display:none;">
</div>
		<input type="text" name="login" id="namepib_reg" class="Ryour-name" placeholder="* ПІБ" autocomplete="off" oninput="reg_name()"  autocomplete="off" required>
		<input type="date" id="date" class="date" name="date[2]" placeholder="Дата народження" size="20" required   min="1950-01-01" title="Дата народження">
		<input type="tel" name="phone" placeholder="Номер телефону" size="12"   maxlength="12" minlength="12" pattern="380[0-9]{9}" title="380987654321" value="380" autocomplete="off" required >
		<input type="email" name="email"  class="Remail" placeholder="Е-mail" autocomplete="off"  >
		<input type="submit" id="reg_submit_btn" name="submit" value="Зареєструватися" class="send-message">
		</form>
	</div>
	
	</div>
	<div id="fade_back" onclick="fade()">
</div>
</div>





</body>
</html>