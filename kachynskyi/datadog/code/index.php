<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
    body {
        width: 35em;
        margin: 0 auto;
        font-family: Tahoma, Verdana, Arial, sans-serif;
    }
</style>
</head>
<body>
<?php
$date = new DateTime();
echo $date->format('Y-m-d H:i:sP') . "\n";

phpinfo();
phpinfo(INFO_MODULES);

?>
</body>
</html>