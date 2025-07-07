# RealHostManager
Sistema gestionale per strutture ricettive, sviluppato in architettura a microservizi utilizzando Java, Spring Boot, Spring Security, Docker e MySQL.

## Tecnologie utilizzate

- Java 21
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

## Stato progetto

Il progetto è attualmente in fase di refactoring architetturale, con l’obiettivo di migliorare la struttura dei pacchetti, la separazione delle responsabilità e la manutenibilità complessiva del codice.
L’intero sistema è correttamente containerizzato tramite Docker e organizzato secondo un’architettura a microservizi.
Il frontend React è in corso di sviluppo e sarà collegato tramite API Gateway con autenticazione JWT.
