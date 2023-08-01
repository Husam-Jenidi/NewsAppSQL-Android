<?php
require "con.php";

$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];

$mysql_qry = "SELECT * FROM users WHERE name LIKE '$user_name' AND password LIKE '$user_pass'";
//, (CASE WHEN admin = 1 THEN 1 ELSE 0 END) AS admin
$result = mysqli_query($conn, $mysql_qry);

if (mysqli_num_rows($result) > 0) {
    $row = mysqli_fetch_assoc($result);

    $response = array();
    $response["login_status"] = "success";
    $response["id"]=$row["id"];
    $response["user_name"] = $row["name"];
    $response["email"] = $row["email"];
    $response["img_url"] =$row["img_url"];
    $response["admin"] = $row["admin"];

    echo json_encode($response);
} else {
    echo "Login not success";
}
?>
