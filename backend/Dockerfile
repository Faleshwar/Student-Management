# FROM openjdk:17-jdk-alpine

# WORKDIR /app

# COPY target/StudentManagement-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080
# ENTRYPOINT ["java","-jar", "app.jar"]


# Use a Maven image to build the application
FROM maven:3.8.7-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml ./   # Copy pom.xml from the backend directory
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY . ./         # Copy the entire backend directory

# Build the application and create the JAR file
RUN mvn clean package -DskipTests

# Use a lightweight JRE image for the runtime
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the previous build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your application runs on (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]


