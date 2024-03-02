# Use the latest version of Ubuntu as the base image
FROM ubuntu:latest

# Expose port 8080
EXPOSE 8080

# Update package lists and install OpenJDK 17 JRE
RUN apt-get update && \
    apt-get install -y openjdk-17-jre

# Add the compiled JAR file from the target directory to the container
ADD target/miniproject.jar miniproject.jar

# Set the entry point for the container to run the JAR file
ENTRYPOINT ["java","-jar","/miniproject.jar"]