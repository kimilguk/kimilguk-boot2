# Build stage gradle image
FROM gradle:7.3-jdk11 AS builder
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build -x test --no-daemon

# Run stage OpenJDK 11 base image
FROM eclipse-temurin:11-jdk
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
