if [ "$1" != "" ]; then
    export DOCKER_PASSWORD
    DOCKER_PASSWORD=$1
    export DOCKER_USERNAME
    DOCKER_USERNAME="bitunified"
    docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
    MAVEN_HOME="/c/Program Files/apache-maven-3.3.9"
    export MAVEN_HOME
    PATH=$PATH:$MAVEN_HOME/bin
    JAVA_HOME="/c/Program Files/Java/jdk1.8.0_111"
    export PATH=$JAVA_HOME/bin:$PATH
    mvn clean install
    cd web-steps/led-config
    ng build --environment=prod --base-href server
    cd ..
    cd ..
    docker build -t bitunified/led-config-web-steps .
    docker tag bitunified/led-config-web-steps bitunified/led-config-web-steps:prod
    docker push bitunified/led-config-web-steps:prod
    ssh root@192.168.16.4 'docker stop $(docker ps --filter ancestor=bitunified/led-config-web-steps:prod); docker pull bitunified/led-config-web-steps:prod; docker run -i -p 8080:8080 bitunified/led-config-web-steps:prod; ntpd -d -q -n -p pool.ntp.org'
else
    echo "No password"
fi
