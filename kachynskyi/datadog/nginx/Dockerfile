FROM nginx:latest

# install curl (need to install dd-agent)
RUN  apt-get update && apt-get install -y curl
RUN DD_AGENT_MAJOR_VERSION=7 DD_API_KEY=f2eaf9b212fb3106f8761f7e8529aefc DD_SITE="datadoghq.eu" bash -c "$(curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script.sh)"

COPY ./conf/nginx/conf.d/status.conf /etc/nginx/conf.d/status.conf
COPY ./conf/dd-agent/conf.d/nginx/conf.yaml /etc/datadog-agent/conf.d/nginx.d/conf.yaml
COPY ./scripts /scripts

RUN chmod -R 700 /scripts
EXPOSE 80
CMD [ "/scripts/init.sh" ]