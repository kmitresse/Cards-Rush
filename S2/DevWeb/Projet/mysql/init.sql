CREATE DATABASE IF NOT EXISTS db;
USE db;

-- Table: User
CREATE TABLE IF NOT EXISTS User
(
    id_user    INT          NOT NULL AUTO_INCREMENT,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    birth_date DATE         NOT NULL,
    sexe       VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_user)
);

-- Table: Game
CREATE TABLE IF NOT EXISTS Game
( 
    id_game    INT          NOT NULL AUTO_INCREMENT,
    date       DATE         NOT NULL,
    starting_time TIME      NOT NULL,
    PRIMARY KEY (id_game)
);

CREATE TABLE IF NOT EXISTS Player
(
    id_player       INT     NOT NULL  AUTO_INCREMENT,
    id_game         INT     NOT NULL,
    id_user         INT     NOT NULL,
    score           INT     NOT NULL,
    winner          BOOLEAN NOT NULL,
    nb_clicks       INT     NOT NULL,
    nb_good_clicks  INT     NOT NULL,
    nb_rapid_clicks INT     NOT NULL,
    PRIMARY KEY (id_player),
    FOREIGN KEY (id_game) REFERENCES Game (id_game),
    FOREIGN KEY (id_user) REFERENCES User (id_user)
)

