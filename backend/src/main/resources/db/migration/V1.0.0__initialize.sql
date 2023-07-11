-- db/migration/V1.0.0_initialize.sql

-- Create the sequence
CREATE SEQUENCE teams_team_id_seq;

-- Create the teams table
CREATE TABLE TEAMS (
   TEAM_ID BIGINT DEFAULT nextval('teams_team_id_seq') PRIMARY KEY NOT NULL,
   TEAM_NAME VARCHAR(255),
   TEAM_WINS INT,
   TEAM_LOSSES INT,
   TEAM_POINTS_SCORED INT, 
   TEAM_POINTS_ALLOWED INT
);
