<?php

require "con.php";
$stmt= $conn ->prepare("SELECT id,title,category,date,content,src,image FROM posts");

$stmt -> execute();

$stmt -> bind_result($id,$title,$category,$date,$content,$src,$image);

$posts = array();
while ($stmt -> fetch()){
    $temp=array();
    $temp['id']=$id;
    $temp['title']=$title;
    $temp['category']=$category ;
    $temp['date']=$date;
    $temp['content']=$content;
    $temp['src']=$src;
    $temp['image']=$image;

    array_push($posts, $temp); 
}
echo json_encode($posts);
?>