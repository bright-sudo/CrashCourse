#!/bin/bash
sh -c "php-fpm -D -c  /usr/local/etc/php-fpm.conf"
sh -c "datadog-agent start"
#supervisord -n -c /etc/supervisor/supervisord.conf