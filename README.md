# Disclaimer

The Dockerization is not finished yet. The connections between the containers does not work yet.
Also one unit tests is failing. 

Thus, for demonstration purposes run the backend and frontend in dev mode.

# Setup
## Postgres DB
Get a postgres db in a docker container.

```
docker pull postgres
```

Run the postgres container
```
docker run -d -p  5433:5432 --name postgres-db -e POSTGRES_PASSWORD=password postgres
```
To create a `teams` table in the postgres db, first open a terminal in the postgres docker container. Then
```
su - postgres
psql
```
Using this psql shell first create a sequence for the entity IDs
```
CREATE SEQUENCE teams_team_id_seq;
```
Then create the table for the teams
```
CREATE TABLE TEAMS(
   TEAM_ID BIGINT DEFAULT nextval('teams_team_id_seq') PRIMARY KEY NOT NULL,
   TEAM_NAME VARCHAR(255),
   TEAM_WINS INT,
   TEAM_LOSSES INT,
   TEAM_POINTS_SCORED INT, 
   TEAM_POINTS_ALLOWED INT
);
```
## Quarkus Backend
Start the quarkus backend

```
cd ./backend
./gradlew --console=plain quarkusDev
```

## Angular frontend
Install the frontend deps
```
cd ./frontend
npm install
```
Start the frontend
```
ng serve --proxy-config .\proxy.conf.json
```

Open webserver at `http://localhost:4200` in your browser
