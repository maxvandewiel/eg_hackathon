FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER max@maxvandewiel.nl
EXPOSE 8080
VOLUME /tmp
ADD build/user-registration-service-0.1.0.jar app.jar
#ADD build/user-registration-service.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
