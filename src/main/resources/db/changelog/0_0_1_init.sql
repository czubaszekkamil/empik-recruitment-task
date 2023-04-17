--liquibase formatted sql

--changeset kczubaszek:1
CREATE TABLE request
(
    login         varchar(500) NOT NULL,
    request_count int(10)      NOT NULL,
    PRIMARY KEY (login)
) ENGINE = InnoDB;