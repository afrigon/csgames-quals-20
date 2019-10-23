<?php
    session_start();

    if (isset($_GET["logout"]) && $_GET["logout"] == "1") {
        $_SESSION["auth"] = 'false';
        header('Location: ourgoal.php');
    }

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $servername = 'db';
        $username  = 'vapor';
        $password = 'vapor';
        $database = 'vapor';
        
        // Create connection
        $conn = new mysqli($servername, $username, $password, $database);

        // Check connection
        if ($conn->connect_error) {
            die("Unable to connect to MYSQL server");
        }

        $query = "SELECT * FROM users WHERE username = '" . $_POST['username'] . "' and password = '" . $_POST['password'] . "';";
        $result = $conn->query($query);
        $auth = $result->num_rows > 0;


        if ($auth) {
            $_SESSION["auth"] = 'true';
        }
    }
?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8"/>
    <title>O U R  G O A L</title>
    <link rel="stylesheet" href="main.css">
    <link rel="shortcut icon" type="image/gif" sizes="120x120" href="http://image.noelshack.com/fichiers/2016/19/1463341901-vapordoudou.gif" />
      
    <style type="text/css">
      { margin: 0; padding: 0; }
        
      html { 
        background: url('http://image.noelshack.com/fichiers/2016/21/1464099817-1463971296783.gif') no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
      }
    </style>
  </head>
<body>
    <iframe width="0" height="0" src="//www.youtube.com/embed/cU8HrO7XuiE?autoplay=1&loop=1" frameborder="0" allowfullscreen></iframe>
    <center><font size="6"><font color="4afafc"> O U R&nbsp; &nbsp;G O A L </font><center>

    <?php 
    if(empty($_SESSION["auth"]) || $_SESSION["auth"] != 'true') {
    ?>
    <form method="POST">
        <input type="text" name="username" placeholder="username"></input>
        <input type="password" name="password" placeholder="password"></input>
        <input type="submit" style="display:none;"></input>
    </form>
    <?php 
    } else {
    ?>
    <a href="ourgoal.php?logout=1"><button>logout</button></a>
    <p>ourgoal - flag{h0W_ab0uT_th3m_v4p0r_a3stet1cs}</p>
    <a href="/">MAIN MENU</a>
    <?php 
    }
    ?>
</body>
</html>


