--liquibase formatted sql

--changeset haru:1
CREATE TABLE users (
                       id UUID PRIMARY KEY Not Null,
                       username VARCHAR(50) Not Null UNIQUE,
                       email VARCHAR(100) Not Null UNIQUE,
                       password_hash VARCHAR(255) Not Null,
                       first_name VARCHAR(50) Not Null,
                       last_name VARCHAR(50) Not Null,
                       created_at TIMESTAMP Not Null DEFAULT NOW(),
                       updated_at TIMESTAMP
);

--changeset haru:2
CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY Not Null,
                       name VARCHAR(50) Not Null UNIQUE
);

--changeset haru:3
CREATE TABLE user_roles (
                            user_id UUID Not Null,
                            role_id BIGINT Not Null,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

--changeset haru:4
CREATE TABLE departments (
                             id BIGSERIAL PRIMARY KEY Not Null,
                             name VARCHAR(100) Not Null UNIQUE
);

--changeset haru:5
CREATE TABLE tickets (
                         id UUID PRIMARY KEY Not Null,
                         description TEXT Not Null,
                         title VARCHAR(100) Not Null,
                         status VARCHAR(50) Not Null,
                         priority VARCHAR(50) Not Null,
                         created_at TIMESTAMP Not Null DEFAULT NOW(),
                         updated_at TIMESTAMP,
                         client_id UUID Not Null,
                         assignee_id UUID,
                         department_id BIGINT,
                         FOREIGN KEY (client_id) REFERENCES users(id) ON DELETE CASCADE,
                         FOREIGN KEY (assignee_id) REFERENCES users(id),
                         FOREIGN KEY (department_id) REFERENCES departments(id)
);

--changeset haru:6
CREATE TABLE comments (
                          id BIGSERIAL PRIMARY KEY Not Null,
                          text TEXT Not Null,
                          created_at TIMESTAMP DEFAULT NOW(),
                          ticket_id UUID Not Null,
                          user_id UUID Not Null,
                          FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON DELETE CASCADE,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

--changeset haru:7
CREATE TABLE ticket_histories (
                                  id BIGSERIAL PRIMARY KEY Not Null,
                                  ticket_id UUID Not Null,
                                  changed_by UUID Not Null,
                                  field_name VARCHAR(50) Not Null,
                                  old_value TEXT,
                                  new_value TEXT Not Null,
                                  changed_at TIMESTAMP DEFAULT NOW(),
                                  FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON DELETE CASCADE,
                                  FOREIGN KEY (changed_by) REFERENCES users(id)
);
--changeset haru:8
INSERT INTO roles(name) VALUES ('ROLE_ADMIN'), ('ROLE_USER')