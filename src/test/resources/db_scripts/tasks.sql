CREATE TABLE task
(
  id INTEGER PRIMARY KEY NOT NULL,
  description VARCHAR(255),
  estimate INTEGER NOT NULL,
  name VARCHAR(255),
  status VARCHAR(255),
  assigner_id INTEGER,
  creator_id INTEGER,
  sprint_id INTEGER,
  CONSTRAINT fkawitl8sxbbsoa72bvove79t51 FOREIGN KEY (assigner_id) REFERENCES app_user (id),
  CONSTRAINT fkns5qgv3boxd8ctgte231tdvtc FOREIGN KEY (creator_id) REFERENCES app_user (id),
  CONSTRAINT fkoew9cen5nj5c154u5y3clh90v FOREIGN KEY (sprint_id) REFERENCES sprint (id)
);

insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (1, 'Task 1', 14, 'CREATED', 'Some description', 5, 22, 1);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (2, 'Task 2', 4, 'CLOSED', 'Some description', 5, 22, 1);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (3, 'Task 3', 17, 'CREATED', 'Some description', 6, 22, 1);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (4, 'Task 4', 15, 'IN_PROGRESS', 'Some description', 6, 22, 2);

insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (5, 'Task 5', 13, 'CREATED', 'Some description', 7, 23, 3);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (6, 'Task 6', 7, 'CLOSED', 'Some description', 7, 23, 3);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (7, 'Task 7', 18, 'IN_PROGRESS', 'Some description', 8, 23, 3);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (8, 'Task 8', 3, 'IN_PROGRESS', 'Some description', 8, 23, 4);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (9, 'Task 9', 4, 'CREATED', 'Some description', 9, 23, 4);

insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (10, 'Task 10', 17, 'CREATED', 'Some description', 10, 24, 5);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (11, 'Task 11', 7, 'CLOSED', 'Some description', 10, 24, 5);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (12, 'Task 12', 5, 'CLOSED', 'Some description', 11, 24, 6);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (13, 'Task 13', 10, 'CREATED', 'Some description', 12, 24, 6);

insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (14, 'Task 14', 16, 'CREATED', 'Some description', 13, 25, 7);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (15, 'Task 15', 5, 'CLOSED', 'Some description', 14, 25, 7);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (16, 'Task 16', 17, 'CLOSED', 'Some description', 15, 25, 7);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (17, 'Task 17', 4, 'CREATED', 'Some description', 16, 25, 8);
insert into task (id, name, estimate, status, description, assigner_id, creator_id, sprint_id) values (18, 'Task 18', 9, 'CLOSED', 'Some description', 15, 25, 8);
