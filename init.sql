CREATE DATABASE IF NOT EXISTS codehive;

USE codehive;

create table users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username varchar(50)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(50) UNIQUE
);

create table snippets
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT,
    title      VARCHAR(255),
    content    TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE INDEX idx_user_email on users (email);
