CREATE DATABASE IF NOT EXISTS demodb;
CREATE USER 'demouser'@'%' IDENTIFIED BY 'demouserpasswd';
GRANT ALL ON demodb.* TO 'demouser'@'%';
CREATE USER 'datadog'@'%' IDENTIFIED BY 'datadogpasswd';
GRANT REPLICATION CLIENT ON *.* TO 'datadog'@'%' WITH MAX_USER_CONNECTIONS 5;
GRANT PROCESS ON *.* TO 'datadog'@'%';
FLUSH PRIVILEGES;
