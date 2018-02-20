FROM tomcat:9.0.0.M13
RUN apt-get --force-yes update
COPY server/tomcat-users.xml $CATALINA_HOME/conf/tomcat-users.xml
COPY server/target/server-1.0.0-SNAPSHOT.war $CATALINA_HOME/webapps/server.war
COPY web-steps/led-config/dist/ $CATALINA_HOME/webapps/ROOT/
COPY server/pkcs8_key /usr/local/tomcat/temp/pkcs8_key
WORKDIR /usr
RUN mkdir led-config
WORKDIR led-config
RUN ls

CMD ["catalina.sh", "run"]