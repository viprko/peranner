FROM openjdk:23-jdk-slim-bullseye

WORKDIR /app

COPY target/user-profile-service-0.0.1-SNAPSHOT.jar /app/user-profile-service.jar

LABEL name="user-profile-service" version="0.0.1" description="Service for saving additional info about users"

CMD ["java", "-jar", "user-profile-service.jar"]
