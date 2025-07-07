# Base image per build Vite
FROM node:20-alpine AS build

# Imposta la directory di lavoro
WORKDIR /app

# Copia il file package.json e package-lock.json e installa le dipendenze
COPY package.json package-lock.json ./
RUN npm install

# Copia tutto il codice sorgente e esegui il build di produzione
COPY . .
RUN npm run build

# NGINX per servire i file statici
FROM nginx:alpine

# Copia la build finale di Vite dentro la directory servita da NGINX
COPY --from=build /app/dist /usr/share/nginx/html

# Rimuovi la configurazione di default e inserisci la configurazione custom (opzionale)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Espone la porta 80 per il traffico
EXPOSE 80

# Avvia Nginx
CMD ["nginx", "-g", "daemon off;"]