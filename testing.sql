drop schema if exists testschema cascade;

create schema testschema;
set search_path to testschema;

create table person
(
    id   serial,
    name varchar
);