<?php
require "con.php";
$user_id =$_POST["user_id"];
$article_id =$_POST["article_id"];
$is_liked =$_POST["is_liked"];




if ($is_liked!=null && $user_id != null && $article_id != null){
$mysql_qry="insert into likes (user_id,article_id,is_liked) values ('$user_id','$article_id','$is_liked')";
}
if($conn -> query($mysql_qry )===TRUE){
    echo "Insert Successfully";
}
else {
    echo "ERROR:".$mysql_qry."<br>".$conn -> error;
}
$conn->close();
?>