# Automatic Setup Script
Start Docker Desktop

Run ./startup.bat

# Manual Setup
Start Docker Desktop
## Postgres DB
Get a postgres db in a docker container

```
docker pull postgres
```
Run the postgres docker container
```
docker run -d -p  5433:5432 --name postgres-db -e POSTGRES_PASSWORD=password postgres
```
## Quarkus Backend
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

## Angular frontend
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
