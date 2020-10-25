FROM php:7.4-fpm
LABEL maintainer="Alex Kachynskyi"

RUN apt-get update && apt-get install -y \
        curl \
        wget \
        git \
        libfreetype6-dev \
        libjpeg62-turbo-dev \
	libpng-dev \
	libonig-dev \
	libzip-dev \
	libmcrypt-dev \
        && pecl install mcrypt-1.0.3 \
	&& docker-php-ext-enable mcrypt \
        && docker-php-ext-install -j$(nproc) iconv mbstring mysqli pdo_mysql zip \
	&& docker-php-ext-configure gd --with-freetype --with-jpeg \
        && docker-php-ext-install -j$(nproc) gd 

# install dd-agent
RUN DD_AGENT_MAJOR_VERSION=7 DD_API_KEY=f2eaf9b212fb3106f8761f7e8529aefc DD_SITE="datadoghq.eu" bash -c "$(curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script.sh)"

# copy dd-agent configs
COPY ./conf/dd-agent/conf.d/php-fpm.d/conf.yaml /etc/datadog-agent/conf.d/php_fpm.d/conf.yaml
# enable /status
COPY ./conf/php-fpm/www.conf /usr/local/etc/php-fpm.d/www.conf
COPY ./scripts /scripts
RUN chmod -R 700 /scripts

EXPOSE 9000
#CMD ["php-fpm"]
CMD [ "/scripts/init.sh" ]