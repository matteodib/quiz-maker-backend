FROM maven:3.8.6-openjdk-8 AS BUILD
WORKDIR /tmp
RUN mkdir proj
WORKDIR /tmp/proj
COPY . .

RUN mvn clean install

FROM openjdk:8-jdk-alpine AS FINAL
RUN mkdir /opt/app
COPY --from=BUILD /tmp/proj/target/*.jar /opt/app/app.jar

CMD ["java", "-jar", "/opt/app/app.jar"]