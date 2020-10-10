apt-get update
apt-get upgrade

apt-get install -y git

apt-get install -y apache2
a2enmod rewrite

apt-add-repository ppa:ondrej/php
apt-get update

apt-get install -y php7.2
apt-get install -y libapache2-mod-php7.2

service apache2 restart


apt-get install -y php7.2-common
apt-get install -y php7.2-mcrypt
apt-get install -y php7.2-zip

apt-get install -y php7.2-mysql

sudo service apache2 restart