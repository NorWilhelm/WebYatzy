drop schema if exists testschema cascade;

create schema testschema;
set search_path to testschema;

CREATE TABLE users
(
    username    varchar NOT NULL,
    password    varchar NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE games
(
    game_id     serial NOT NULL,
    content     varchar NOT NULL, -- This is a placeholder for testing purposes
    PRIMARY KEY (game_id)
);

INSERT INTO users (username, password)
VALUES ('John', 'Smith'),
       ('William', 'Wontillian'),
       ('Cat', 'Catisen');

INSERT INTO games (content)
VALUES ('Currently empty');