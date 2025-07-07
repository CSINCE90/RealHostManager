# Costruzione con Maven + Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Immagine runtime leggera
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/guestservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
