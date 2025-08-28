FROM alpine:latest AS build

RUN apk add --no-cache openjdk17 curl unzip git bash

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew && ./gradlew bootJar --no-daemon

FROM alpine:latest

RUN apk add --no-cache openjdk17-jre

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
