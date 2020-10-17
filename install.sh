#CORE STUFF


#LINUX STUFF
sudo apt-get update

sudo apt-get -y install vim
sudo apt-get -y install git


#APACHE STUFF
sudo apt-get -y install apache2
#базові налаштування вебсервера
sudo a2enmod expires
sudo a2enmod headers
sudo a2enmod include
sudo a2enmod rewrite

#PHP STUFF
sudo apt-get -y install php

#бібліотека для взаємодії вебсервера з php

sudo apt-get -y install libapache2-mod-php

sudo service apache2 restart

#базові модулі
sudo apt-get -y install php-common
sudo apt-get -y install php-all-dev

#корисні утиліти
sudo apt-get -y install php-mcrypt
sudo apt-get -y install php-zip
sudo apt-get -y install php-xml


#MYSQL STUFF
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'

sudo apt-get -y install mysql-server
sudo mysqladmin -uroot -proot create scotchbox
sudo apt-get -y install php-mysql
sudo service apache2 restart

sudo service apache2 restart