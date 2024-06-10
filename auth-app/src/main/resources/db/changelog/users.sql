--liquibase formatted sql
--changeset Dmitriy Chereukho:1
create table "users"(
    id UUID primary key,
    username varchar(64) not null,
    email varchar(128) not null,
    password varchar(256) not null,
    created timestamp default  current_timestamp
);

