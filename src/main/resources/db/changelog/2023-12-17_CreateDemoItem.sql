--liquibase formatted sql

--changeset author:2023-12-17_CreateDemoItem

CREATE TABLE demo_item
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR
);
