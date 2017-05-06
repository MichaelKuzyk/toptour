//проверка поля ПІБ отправки заказа
function myName() {
 var x = document.getElementById("namepib").value;
var y =/[-\.,<>{}@#+_%?!$^&()`№~*;:'/0-9/a-zA-Z]/;
y1 = x.search(y);
if(y1<0)  {document.getElementById("namepib").value=document.getElementById("namepib").value}
else {var j = document.getElementById("namepib").value;var arr= j.split('');var i= arr.length;
document.getElementById("namepib").value=document.getElementById("namepib").value.substring(0,document.getElementById("namepib").value.length-1);
alert('Ви ввели НЕДОПУСТИМИЙ СМВОЛ: '+arr[length+i-1]);
}}


//Капсим паспорт для унификации ввода
function myPassport(){  {document.getElementById("passport").value=document.getElementById("passport").value.toUpperCase()}}

//Капсим паспорт для унификации ввода
function myPassport_reg(){
  {document.getElementById("passport_reg").value=document.getElementById("passport_reg").value.toUpperCase()}}

//проверка поля ПІБ
function reg_name() {
var x = document.getElementById("namepib_reg").value;
var y =/[\.,<>{}@#+_%?!$^&()`№~*;:'/0-9/a-zA-Z]/;
y1 = x.search(y);
if(y1<0)  {document.getElementById("namepib_reg").value=document.getElementById("namepib_reg").value}
else {var j = document.getElementById("namepib_reg").value;var arr= j.split('');var i= arr.length;
document.getElementById("namepib_reg").value=document.getElementById("namepib_reg").value.substring(0,document.getElementById("namepib_reg").value.length-1);
alert('Ви ввели НЕДОПУСТИМИЙ СМВОЛ: '+arr[length+i-1]);
}}

//Установка даты в формах регистрации и заказа 
function myFunction() { 
      
temp_date = new Date(); 
day = temp_date.getDate(); 

  
month = temp_date.getMonth()+1; 
year = temp_date.getFullYear(); 
year_birth=temp_date.getFullYear()-17; 
//alert(day +"___"+year); 
dayt=day+1; 
if (day < 10) { 
day = "0" + day;
 
dayt="0" + dayt;} 
  
  
if (month <10) { 
month = "0" + month;} 
birthday=year_birth + "-" + month + "-" + day; 
ride=year + "-" + month + "-" + dayt; 
  
//document.getElementById('city').value=4; 
document.getElementsByClassName('date')[0].value=ride; 
document.getElementsByClassName('date')[1].value=birthday; 
document.getElementsByClassName('date')[0].setAttribute("min",ride); 
} 

//Проверка совпадения паролей
function reg_submit_check(){
var pass1=document.getElementById('regpassw1');
var pass2=document.getElementById('regpassw2');
var button_reg=document.getElementById('reg_submit_btn');
var true_pass=document.getElementById('true');
var false_pass=document.getElementById('false');
var pass2div=document.getElementById('pass2');

pass2div.style.left="0px";
if( pass1.value==pass2.value)
   { 
//document.getElementById('registration').setAttribute("onsubmit", "return false");
button_reg.type = 'submit';
false_pass.style.display = "none";
true_pass.style.display = "block";

}
else{
true_pass.style.display = "none";
false_pass.style.display = "block";
button_reg.type = 'button';

}
}



//Очистка вторго поля пароля и обнуление error-статуса поля
function clear_pass2(){
var button_reg=document.getElementById('reg_submit_btn');
var pass2=document.getElementById('regpassw2');
var pass2div=document.getElementById('pass2');
var true_pass=document.getElementById('true');
var false_pass=document.getElementById('false');
button_reg.type = 'submit';
pass2.value="";
true_pass.style.display = "none";
false_pass.style.display = "none";
pass2div.style.left="0px";
}


