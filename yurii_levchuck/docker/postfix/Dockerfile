FROM ubuntu:latest
MAINTAINER Yurii Levchuk 
LABEL Description="Postfix server"
RUN apt update
RUN apt upgrade -y

COPY debconf.selections /tmp/
RUN debconf-set-selections /tmp/debconf.selections

RUN apt install postfix -y

ENV DATE_TIMEZONE UTC

COPY run-postfix /usr/bin
RUN chmod +x /usr/bin/run-postfix

CMD ["/usr/bin/run-postfix"]
