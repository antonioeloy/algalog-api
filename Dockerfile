FROM maven:3.8.2-openjdk-11-slim AS MAVEN_BUILD

MAINTAINER Antonio Eloy

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/*.jar /app/algalog-api.jar

CMD java -XX:+UseContainerSupport -jar algalog-api.jar