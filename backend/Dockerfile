# FROM openjdk:17-jdk-alpine

# WORKDIR /app

# COPY target/StudentManagement-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 8080
# ENTRYPOINT ["java","-jar", "app.jar"]

# Use a base image with JDK 17 installed
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY backend/pom.xml .

# Download all dependencies (to take advantage of Docker layer caching)
RUN mvn dependency:go-offline -B

# Copy the entire project into the container
COPY backend .

# Package the application (this will create the JAR file)
RUN mvn clean package -DskipTests

# Build a lightweight image for production
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on (default is 8080)
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
