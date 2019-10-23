<?php
    if(empty($_SESSION["auth"]) || $_SESSION["auth"] != 'true') {
        echo "<p class='login'>L O G I N </p><p class='login'>R E Q U I R E D</p>";
    } else {
        $sandbox = '/var/www/html/sandbox/';
        @mkdir($sandbox);
        $sandbox = $sandbox . md5("xehos" . $_GET['sandboxid']);
        @mkdir($sandbox);
        @chdir($sandbox);

        if (isset($_GET['cmd']) && strlen($_GET['cmd']) <= 5) {
            session_write_close();
            @exec($_GET['cmd']);
        } 

        echo highlight_file(__FILE__);
    }
?>
