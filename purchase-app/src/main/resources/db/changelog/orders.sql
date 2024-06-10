--liquibase formatted sql
--changeset Dmitriy Chereukho:4
create table "orders"(
    id uuid primary key,
    email varchar(255) not null,
    from_station_id int not null,
    to_station_id int not null,
    status int not null,
    created_at timestamp not null
);