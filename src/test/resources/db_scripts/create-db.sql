CREATE TABLE app_user
(
    id                  INTEGER      NOT NULL PRIMARY KEY,
    username            VARCHAR(255) NOT NULL UNIQUE,
    password            VARCHAR(255) NOT NULL,
    role                INTEGER      NOT NULL,
    firstname           VARCHAR(255) NOT NULL,
    lastname            VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL UNIQUE,
    salary              INTEGER      NOT NULL,
    birthday            DATE         NOT NULL,
    hireday             DATE         NOT NULL,
    gender              INTEGER      NOT NULL,
    managed_project_id  INTEGER,
    project_id          INTEGER
--     CONSTRAINT app_user_manager_project FOREIGN KEY (managed_project_id) REFERENCES project (id),
--     CONSTRAINT app_user_project FOREIGN KEY (project_id) REFERENCES project (id)
);
-- CREATE UNIQUE INDEX uk_5ssgetqhwklwfl3p6691pqfd7 ON app_user (managed_project_id);

INSERT INTO app_user(id, username, password, role, firstname, lastname, email, salary, birthday, hireday, gender)
VALUES (1, 'legendaryyyy', '26121992z', 1, 'Evgeniy', 'Deyneka', 'deyneko55@gmail.com', 10000, '1992-12-26', '2016-02-02', 1);

