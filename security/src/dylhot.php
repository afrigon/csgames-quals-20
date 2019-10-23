<?php session_start() ?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Do You Like Hurting Other People?</title>
    <style type="text/css">
      { margin: 0; padding: 0; }
        
      html { 
        background: url('http://image.noelshack.com/fichiers/2016/22/1464648838-giphy.gif') center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        overflow: hidden;
      }
      
      .content {
        transform: skew(30deg) scale(2, 3) translate(400px, 100px);
      }

      .login {
        color: #01cdfe;
        font-size: 32px;
        font-weight: bold;
      }
    </style>
  </head>
  <body>
    <iframe width="0" height="0" src="https://www.youtube.com/embed/CcMz3aAZDv4?autoplay=1&loop=1" frameborder="0" allowfullscreen></iframe>
    <div class="content">

    <?php
        // flag{5_ch4rs_a1nt_th4t_MuCh_hUh}
        include("./backdoor.php");
    ?>
    </div>
  </body>
</html>
