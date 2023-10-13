FROM openjdk:17-alpine
VOLUME /tmp
COPY target/*.jar empik-github-api.jar
ENTRYPOINT ["java","-jar","/empik-github-api.jar"]