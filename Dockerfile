# Use an official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and the source code to the container
COPY pom.xml /app/pom.xml
COPY src /app/src

# Install Maven Wrapper and build the application
RUN apt-get update && apt-get install -y maven && \
    ./mvnw clean install

# Expose the port the app runs on
EXPOSE 8085

# Run the application
CMD ["./mvnw", "spring-boot:run", "--quiet"]
