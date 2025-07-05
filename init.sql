CREATE DATABASE IF NOT EXISTS codehive;

USE codehive;

create table users
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   varchar(50)                         NOT NULL,
    password   VARCHAR(255)                        NOT NULL,
    email      VARCHAR(50) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table snippets
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT,
    title      VARCHAR(255),
    content    TEXT,
    language   VARCHAR(50)                         NOT NULL,
    is_public  BOOLEAN   DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE sessions
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    session_id VARCHAR(255)                        NOT NULL UNIQUE,
    user_id    INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    expires_at TIMESTAMP                           NOT NULL,
    is_active  BOOLEAN   DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE INDEX idx_user_email on users (email);
