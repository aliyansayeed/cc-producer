# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/Producer-0.0.1-SNAPSHOT.jar /app/producer-app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8081

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/producer-app.jar"]

