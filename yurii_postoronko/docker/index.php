<?php


$link = mysqli_connect('mysql', 'root', 'secret', 'db_test');
if (!$link) {
    die('Ошибка соединения: ' . mysqli_error($link));
}
echo 'Успешно соединились';
mysqli_close($link);


phpinfo();