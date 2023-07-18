# Basketball Table
This app can be used to track the standings of a basketball league (or any other sport that does not have ties).

# Start App
Regardless of the method used to start the application, a Docker daemon must be running.
There are three ways to start the application:
1. [Docker Compose](#docker-compose)
2. [Batch Script](#startup-batch-script)
3. [Manual Setup](#manual-setup)
## Docker Compose
Build the backend
```
cd ./backend
./gradlew build
```

Start the application by navigation to the root folder and running
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
