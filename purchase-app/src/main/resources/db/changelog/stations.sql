--liquibase formatted sql
--changeset Dmitriy Chereukho:3
create table "stations" (
    "id" int primary key,
    "name" varchar(255)
);

insert into "stations" ("id", "name") values (1, 'Station 1');
insert into "stations" ("id", "name") values (2, 'Station 2');
insert into "stations" ("id", "name") values (3, 'Station 3');
insert into "stations" ("id", "name") values (4, 'Station 4');
insert into "stations" ("id", "name") values (5, 'Station 5');
insert into "stations" ("id", "name") values (6, 'Station 6');
insert into "stations" ("id", "name") values (7, 'Station 7');
insert into "stations" ("id", "name") values (8, 'Station 8');
insert into "stations" ("id", "name") values (9, 'Station 9');
insert into "stations" ("id", "name") values (10, 'Station 10');