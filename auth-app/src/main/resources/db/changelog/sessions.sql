--liquibase formatted sql
--changeset Dmitriy Chereukho:2
create table "sessions"(
    id UUID primary key,
    user_id UUID,
    token varchar(256) not null,
    expires timestamp not null,
    foreign key (user_id) references "users"(id)
);
