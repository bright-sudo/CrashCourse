<?

echo 'Hello World!'.PHP_EOL;
 
$servername = "mariadb";
$username = getenv('MYSQL_USER');
$password = getenv('MYSQL_PASSWORD');
 
$conn = mysqli_connect($servername, $username, $password);
if (!$conn) {
   exit('Connection failed: '.mysqli_connect_error().PHP_EOL);
}
 
echo 'Successful database connection!'.PHP_EOL;

?>
