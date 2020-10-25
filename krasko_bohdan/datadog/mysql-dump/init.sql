CREATE USER 'datadog'@'172.29.0.1' IDENTIFIED WITH mysql_native_password by 'password';
GRANT REPLICATION CLIENT ON *.* TO 'datadog'@'172.29.0.1';
GRANT PROCESS ON *.* TO 'datadog'@'172.29.0.1';
ALTER USER 'datadog'@'172.29.0.1' WITH MAX_USER_CONNECTIONS 5;
show databases like 'performance_schema';
GRANT SELECT ON performance_schema.* TO 'datadog'@'172.29.0.1';
