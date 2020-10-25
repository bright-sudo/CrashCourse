#!/bin/bash
set -e

mysql --protocol=socket -uroot -p$MYSQL_ROOT_PASSWORD <<EOSQL
CREATE USER 'datadog'@'localhost' IDENTIFIED WITH mysql_native_password by '$MYSQL_ROOT_PASSWORD';
CREATE USER 'datadog'@'127.0.0.1' IDENTIFIED WITH mysql_native_password by '$MYSQL_ROOT_PASSWORD';
GRANT PROCESS ON *.* TO 'datadog'@'localhost';
GRANT PROCESS ON *.* TO 'datadog'@'127.0.0.1';
GRANT REPLICATION CLIENT ON *.* TO 'datadog'@'localhost';
GRANT REPLICATION CLIENT ON *.* TO 'datadog'@'127.0.0.1';
ALTER USER 'datadog'@'localhost' WITH MAX_USER_CONNECTIONS 5;
ALTER USER 'datadog'@'127.0.0.1' WITH MAX_USER_CONNECTIONS 5;
GRANT SELECT ON performance_schema.* TO 'datadog'@'localhost';
GRANT SELECT ON performance_schema.* TO 'datadog'@'127.0.0.1';
EOSQL
