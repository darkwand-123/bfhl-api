FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY bfhl/pom.xml .
COPY bfhl/src ./src
COPY bfhl/mvnw .
COPY bfhl/.mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
