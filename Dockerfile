FROM tomcat:8.5.76-jre8-temurin
COPY target/Messenger.war usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
