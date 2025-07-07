# Usa un'immagine Java compatibile con Spring Boot
FROM eclipse-temurin:21-jdk-alpine

# Crea una cartella per l'app
WORKDIR /app

# Copia il jar compilato
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta 8081
EXPOSE 8081

# Comando di avvio
ENTRYPOINT ["java", "-jar", "app.jar"]