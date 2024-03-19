FROM openjdk:23-jdk-slim-bullseye

WORKDIR /app

COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar

LABEL name="api-gateway" version="0.0.1" description="API gateway for spring cloud"

CMD ["java", "-jar", "api-gateway.jar"]
