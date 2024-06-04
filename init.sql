-- init.sql
CREATE TABLE IF NOT EXISTS users (
    id bigserial primary key,
    login VARCHAR(255),
    password VARCHAR(255),
    nickname VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS accounts (
    id bigserial primary key,
    amount BIGINT,
    accountType VARCHAR(255),
    status VARCHAR(255)
);
