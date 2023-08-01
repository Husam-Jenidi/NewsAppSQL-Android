<?php
require "con.php";

$response = array();

if (isset($_GET['id'])) {
    $id = $_GET['id'];

    $deleteCommentsQry = "DELETE FROM comments WHERE article_id='$id'";
    if(mysqli_query($conn, $deleteCommentsQry)){
    $deleteLikesQry = "DELETE FROM likes WHERE article_id='$id'";
    if(mysqli_query($conn, $deleteLikesQry)){
    $qry = "DELETE FROM posts WHERE id='$id'";
    if (mysqli_query($conn, $qry)) {
      
        $response["success"] = true;
        $response["message"] = "Post deleted successfully.";
    } else {
       
        $response["success"] = false;
        $response["message"] = "Failed to delete post.";
    }
}else {
    $response["success"] = false;
    $response["message"] = "Something went wrong, please try again later.";
}

}else {
    $response["success"] = false;
    $response["message"] = "Something went wrong, please try again later.";
}
}
 else {
    $response["success"] = false;
    $response["message"] = "Missing 'id' parameter.";
}

echo json_encode($response);
?>
