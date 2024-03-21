CREATE DATABASE IF NOT EXISTS db;
USE db;

-- Table: User
CREATE TABLE IF NOT EXISTS `user`
(
    id         INT          NOT NULL AUTO_INCREMENT,
    username   VARCHAR(255) NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    gender     VARCHAR(255) NOT NULL,
    birth      DATE         NOT NULL,
    PRIMARY KEY (id)
);

-- Table: Game
CREATE TABLE IF NOT EXISTS game
(
    id         INT          NOT NULL AUTO_INCREMENT,
    difficulty VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    nb_rounds  INT          NOT NULL,
    nb_colors  INT          NOT NULL,
    nb_values_per_color INT NOT NULL,
    PRIMARY KEY (id)
);

-- Table: Player
CREATE TABLE IF NOT EXISTS player
(
    id                INT     NOT NULL  AUTO_INCREMENT,

    game_id           INT     NOT NULL,
    user_id           INT     NOT NULL,

    score             INT     NOT NULL,
    winner            BOOLEAN NOT NULL,
    click_count       INT     NOT NULL,
    right_click_count INT     NOT NULL,
    rapid_click_count INT     NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (user_id) REFERENCES `user` (id)
);
