FROM openjdk:23-jdk-slim-bullseye

WORKDIR /app

COPY target/telegram-bot-0.0.1-SNAPSHOT.jar /app/telegram-bot.jar

LABEL name="telegram-bot" version="0.0.1" description="Telegram bot for peranner-app"

CMD ["java", "-jar", "telegram-bot.jar"]
