<?php
require "con.php";

$query= "SELECT comments.comment_text, users.name, users.img_url,posts.title
FROM comments
JOIN users ON comments.user_id = users.id
JOIN posts ON comments.article_id = posts.id
ORDER BY comments.comment_id desc";
$res= mysqli_query($conn,$query);

$json_data = array();
while($row = mysqli_fetch_assoc($res)){
    $json_data[]= $row;

}
echo json_encode($json_data);

?>