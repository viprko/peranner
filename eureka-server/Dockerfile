FROM openjdk:23-jdk-slim-bullseye

WORKDIR /app

COPY target/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

LABEL name="eureka-server" version="0.0.1" description="Eureka server for spring cloud"

CMD ["java", "-jar", "eureka-server.jar"]
