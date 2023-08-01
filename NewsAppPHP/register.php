<?php
require "con.php";
$user_name =$_POST["user_name"];
$email =$_POST["email"];
$password =$_POST["password"];



if ($user_name!=null && $email != null && $password != null){
$mysql_qry="insert into users (name,email,password,admin) values ('$user_name','$email','$password','no')";
}
if($conn -> query($mysql_qry )===TRUE){
    echo "Insert Successfully";
}
else {
    echo "ERROR:".$mysql_qry."<br>".$conn -> error;
}
$conn->close();
?>