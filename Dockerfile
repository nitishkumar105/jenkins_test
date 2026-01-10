# Build stage - using Maven image with JDK for building
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /build

# Copy Maven configuration files first (for better layer caching)
COPY pom.xml .
COPY src ./src
# Build the application
RUN mvn clean package -DskipTests

# Runtime stage - using minimal JRE image
FROM eclipse-temurin:21-jre-alpine AS runtime

# Add non-root user for better security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /build/target/*.jar app.jar

# Expose port (use the same port as in application.properties)
EXPOSE 8080

# Use exec form for better signal handling
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--server.address=0.0.0.0"]