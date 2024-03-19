FROM openjdk:23-jdk-slim-bullseye

WORKDIR /app

COPY target/authentication-service-0.0.1-SNAPSHOT.jar /app/authentication-service.jar

LABEL name="authentication-service" version="0.0.1" description="Authentication service with JWT implementation"

CMD ["java", "-jar", "authentication-service.jar"]
