# Fase 1: build con Maven e Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Fase 2: immagine runtime
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/alloggiati-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]