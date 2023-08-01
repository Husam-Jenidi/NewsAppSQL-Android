<?php
require "con.php";
$comment_text =$_POST["comment_text"];
$user_id =$_POST["user_id"];
$article_id =$_POST["article_id"];




if ($comment_text!=null && $user_id != null && $article_id != null){
$mysql_qry="insert into comments (comment_text,user_id,article_id) values ('$comment_text','$user_id','$article_id')";
}
if($conn -> query($mysql_qry )===TRUE){
    $getCommentQry = "SELECT comments.comment_text, users.name, users.img_url,posts.title
    FROM comments
    JOIN users ON comments.user_id = users.id
    JOIN posts ON comments.article_id = posts.id
     where comments.comment_id = LAST_INSERT_ID()";
    $res= mysqli_query($conn,$getCommentQry);

    $row = mysqli_fetch_assoc($res);
   echo json_encode($row);
}
else {
    echo "ERROR:".$mysql_qry."<br>".$conn -> error;
}
$conn->close();
?>