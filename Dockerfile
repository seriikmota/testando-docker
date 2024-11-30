# Stage 1 - Build stage
FROM maven:3.9.6-amazoncorretto-17 as stage1
# Speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# Set working directory
WORKDIR /app/build
# Copy just pom.xml to leverage caching
COPY pom.xml .

RUN yum install git -y
RUN head -c 5 /dev/random > random_bytes && git clone https://github.com/seriikmota/generic-architecture.git
RUN head -c 5 /dev/random > random_bytes && cd generic-architecture && git checkout sprint11 && mvn dependency:go-offline && mvn clean install -Dmaven.test.skip=true && cd ..

# Preload dependencies
RUN mvn dependency:go-offline
# Copy the rest of the source code
COPY ./src ./src
# Build the application
RUN mvn clean install -Dmaven.test.skip=true

# Stage 2 - Runtime stage
FROM openjdk:17-slim
# Set working directory
WORKDIR /app
# Copy the built JAR from the build stage
COPY --from=stage1 /app/build/target/*.jar /app/app.jar
# Expose the application's default port
CMD java -jar /app/app.jar