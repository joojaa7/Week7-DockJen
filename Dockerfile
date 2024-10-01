#FROM maven:latest
#WORKDIR /app
#COPY pom.xml /app/
#COPY . /app/
#RUN mvn package
#CMD ["java", "-jar", "target/interconversions.jar"]

# Start with a base image containing Java runtime
FROM maven:3.8.1-openjdk-17-slim AS build

# Make source directory
RUN mkdir -p /workspace

# Change to source directory
WORKDIR /workspace

# Copy the pom.xml file
COPY pom.xml .

# Initial download of all dependencies
RUN mvn dependency:go-offline -B

# Copy your other files
COPY src ./src

# Build the project and run tests
RUN mvn clean test package

RUN mvn test

# Start with a base image containing Java runtime
FROM openjdk:19-slim

# Make application directory
RUN mkdir -p /app

# Change to application directory
WORKDIR /app

# Copy the jar file
COPY --from=build /workspace/target/*.jar ./app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]