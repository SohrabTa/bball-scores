@echo off

REM Postgres DB
echo Setting up Postgres DB...
docker pull postgres
docker run -d -p 5433:5432 --name postgres-db -e POSTGRES_PASSWORD=password postgres

REM Quarkus Backend
echo Building Quarkus backend...
cd ./backend
call gradlew build
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-backend .
docker run -d -p 8080:8080 --name quarkus-backend quarkus-backend

REM Angular Frontend
echo Building Angular frontend...
cd ../frontend
docker build . -t angular-frontend
docker run -d -p 4200:4200 --name angular-frontend angular-frontend

echo Startup script executed successfully!
echo Open webserver at http://localhost:4200 in your browser.
