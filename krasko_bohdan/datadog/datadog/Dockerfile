FROM datadog/agent:7

RUN apt-get update && apt-get -y install vim

ADD mysql.d/conf.yaml /etc/datadog-agent/conf.d/mysql.d/conf.yaml
ADD nginx.d/conf.yaml /etc/datadog-agent/conf.d/nginx.d/conf.yaml
ADD php_fpm.d/conf.yaml /etc/datadog-agent/conf.d/php_fpm.d/conf.yaml
