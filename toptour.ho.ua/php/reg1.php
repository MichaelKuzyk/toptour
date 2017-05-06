<?php


    if (isset($_POST['passport'])) { $passport = $_POST['passport']; if ($passport == '') { unset($passport);} }
	if (isset($_POST['password'])) { $password=$_POST['password']; if ($password =='') { unset($password);} }
    if (isset($_POST['password2'])) { $password2=$_POST['password2']; if ($password2 =='') { unset($password2);} }
    if (isset($_POST['login'])) { $login = $_POST['login']; if ($login == '') { unset($login);} } //заносим введенный пользователем логин в переменную $login, если он пустой, то уничтожаем переменную
    if (isset($_POST['date'][2])) { $date=$_POST['date'][2]; if ($date =='') { unset($date);} }
    if (isset($_POST['phone'])) { $phone=$_POST['phone']; if ($phone =='') { unset($phone);} } 
	if (isset($_POST['email'])) { $email=$_POST['email']; if ($email =='') { unset($email);} }
    
/*
    //заносим введенный пользователем пароль в переменную $password, если он пустой, то уничтожаем переменную
 if (empty($passport) or empty($login) or empty($password) or empty($password2) or empty($email) or empty($phone) or empty($date)) //если пользователь не ввел логин или пароль, то выдаем ошибку и останавливаем скрипт
    {  
    ?> 
     <script type="text/javascript">   alert("Ви ввели не всю інформацію, заповніть всі поля!"); location.replace('index.php');</script>
    <?php
	exit();
    }

 if ($password != $password2) //если пользователь не ввел логин или пароль, то выдаем ошибку и останавливаем скрипт
    {  
    ?> 
     <script type="text/javascript">   alert("Не вірно введений пароль!"); location.replace('index.php');</script>
    <?php
	exit();
    }
*/

 // подключаемся к базе
   // include ("bd.php");
$con = mysql_connect("localhost","root","");
 $db = mysql_select_db("toptour",$con);
$query=mysql_query("SET NAMES cp1251");

 // проверка на существование пользователя с таким же логином
/*
    $result = mysql_query("SELECT id FROM klient WHERE passport_nom='$passport'",$db);
    $myrow = mysql_fetch_array($result);
    if (!empty($myrow['id'])) {

 ?> 
     <script type="text/javascript">   alert("Введений Вами паспорт уже зареєстрований."); location.replace('index.php'); </script>
    <?php
	exit();
    }
    */
 // если такого нет, то сохраняем данные
    $result2 = mysql_query ("INSERT INTO klient (passport_nom,password,pib_kl,birthday,phone,email) VALUES('$passport','$password','$login','$date','$phone','$email')");
    // Проверяем, есть ли ошибки
    if ($result2=='TRUE')
    {
 ?> 
     <script type="text/javascript">   
		alert("Ви успішно зареєстровані в системі TopTours!");
		location.replace('index.php');
     </script>
    <?php

	
    }
 else {
    ?> 
     <script type="text/javascript">   alert("Помилка! Ви не зареєстровані"); location.replace('index.php');</script>
    <?php
    }
    ?>	