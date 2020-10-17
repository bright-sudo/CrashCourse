# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure('2') do |config|

  config.vm.box = 'bento/ubuntu-16.04'
  config.vm.provider 'VirtualBox' do |vb|
      vb.memory = 2048
      vb.cpus = 2
  end

#network
  config.vm.network 'forwarded_port', guest: 80, host: 8080
  config.vm.network 'private_network', ip: '192.168.33.10'

  config.vm.synced_folder 'C:/Users/Home/Desktop/vagrant_site/', '/var/www/html/'
  config.vm.provision 'shell', path: 'install.sh', privileged: false

end

