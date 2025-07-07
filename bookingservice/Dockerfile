# Usa l'immagine Maven con Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Imposta la directory di lavoro
WORKDIR /app

# Copia il progetto e costruiscilo
COPY . .
RUN mvn clean package -DskipTests

# Immagine runtime leggera
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia il jar dal builder
COPY --from=builder /app/target/bookingservice-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta su cui gira il servizio
EXPOSE 8084

# Comando di avvio
ENTRYPOINT ["java", "-jar", "app.jar"]
