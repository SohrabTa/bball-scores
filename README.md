# Start App
Regardless of which method used to start the app, a docker deamon has to be running.
Three options to start the app:
1. [Docker Compose](#docker-compose)
2. [Batch Script](#startup-batch-script)
3. [Manual Setup](#manual-setup)
## Docker Compose
Start the application by running
```
docker compose up
```

## Startup Batch Script
Start the application by running
```
./startup.bat
```
## Manual Setup
### Postgres DB
Get a postgres db in a docker container

```
docker pull postgres
```
Run the postgres docker container
```
docker run -d -p  5433:5432 --name postgres-db -e POSTGRES_PASSWORD=password postgres
```
### Quarkus Backend
Build the Quarkus backend docker image

```
cd ./backend
./gradlew build
docker build -f src/main/docker/Dockerfile.jvm -t backend .
```
Run the backend docker container
```
docker run -d -p 8080:8080 backend
```

### Angular frontend
Build the Angular frontend docker image
```
cd ./frontend
docker build . -t frontend
```
Run the frontend docker container
```
docker run -d -p 4200:4200 frontend
```

Open webserver at `http://localhost:4200` in your browser
