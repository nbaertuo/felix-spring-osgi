set MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
mvn grails:run-app -Dgrails.env=dev -Dserver.port=80 -Dapp.context=/
