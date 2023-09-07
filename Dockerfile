FROM openjdk:17-jdk-alpine
MAINTAINER Deeksha
COPY target/docker-LinktreeClone-0.0.1-SNAPSHOT.jar LinktreeClone-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/LinktreeClone-0.0.1-SNAPSHOT.jar"]

