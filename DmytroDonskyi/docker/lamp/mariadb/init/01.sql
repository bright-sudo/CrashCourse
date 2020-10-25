CREATE DATABASE IF NOT EXISTS `demodb`;
CREATE USER 'demouser'@'%' IDENTIFIED BY 'demouserpasswd';
GRANT ALL ON `demodb`.* TO 'demouser'@'%';
