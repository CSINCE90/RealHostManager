# RealHostManager
Sistema gestionale per strutture ricettive, sviluppato in architettura a microservizi utilizzando Java, Spring Boot, Spring Security, Docker e MySQL.

## Tecnologie utilizzate

- Java 17
- Spring Boot 3.x
- Spring Security con JWT
- API Gateway con Spring Cloud Gateway
- REST API & SOAP/XML (Alloggiati Web)
- Docker & Docker Compose
- MySQL
- React (frontend in sviluppo)

## Microservizi inclusi

- `auth-service` → autenticazione e gestione utenti con JWT
- `booking-service` → gestione delle prenotazioni
- `guest-service` → anagrafica ospiti
- `structure-service` → gestione strutture ricettive
- `apigateway` → sicurezza e routing centralizzato
- `alloggiati` → integrazione SOAP con il portale della Polizia di Stato
- `real-host-manager-frontend` → frontend in React

## Avvio rapido (con Docker)

```bash
docker-compose up --build
