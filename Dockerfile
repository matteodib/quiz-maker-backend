FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /workdir
COPY pom.xml .
COPY src ./src


RUN mvn clean install
