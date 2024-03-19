FROM openjdk:23-jdk-slim-bullseye

WORKDIR /app

COPY target/contribute-service-0.0.1-SNAPSHOT.jar /app/contribute-service.jar

LABEL name="contribute-service" version="0.0.1" description="Main business logic for peranner-app"

CMD ["java", "-jar", "contribute-service.jar"]
