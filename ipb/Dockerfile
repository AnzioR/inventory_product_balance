# Base image
FROM maven:3.8.4-openjdk-11-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project's POM file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Final image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build image to the final image
COPY --from=build /app/target/*.jar ./app.jar

# Expose any necessary ports
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]