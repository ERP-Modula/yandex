FROM openjdk:17-jdk-slim

WORKDIR /app
COPY . .

ARG GITHUB_TOKEN
ARG GITHUB_USER


RUN chmod +x gradlew
RUN sed -i 's/\r$//' gradlew

RUN ./gradlew clean build

#TODO вставить название вашего jar
ENTRYPOINT ["java","-jar","build/libs/platform-0.0.1-SNAPSHOT.jar"]