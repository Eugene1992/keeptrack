CREATE TABLE sprint
(
  id INTEGER PRIMARY KEY NOT NULL,
  description VARCHAR(255),
  name VARCHAR(255),
  status VARCHAR(255),
  project_id INTEGER,
  startdate DATE,
  enddate DATE,
  CONSTRAINT fkep2okjn6bvwwnre5o1w7y5psd FOREIGN KEY (project_id) REFERENCES project (id)
);

insert into sprint (id, name, description, startdate, enddate, status, project_id) values (1, 'Sprint 1', 'Some sprint description', '2013-01-19', '2013-02-03', 'CLOSED', 1);
insert into sprint (id, name, description, startdate, enddate, status, project_id) values (2, 'Sprint 2', 'Some sprint description', '2013-01-19', '2013-02-03', 'CLOSED', 1);

insert into sprint (id, name, description, startdate, enddate, status, project_id) values (3, 'Sprint 6', 'Some sprint description', '2013-01-19', '2013-02-03', 'CLOSED', 2);
insert into sprint (id, name, description, startdate, enddate, status, project_id) values (4, 'Sprint 5', 'Some sprint description', '2013-01-19', '2013-02-03', 'IN_PROGRESS', 2);

insert into sprint (id, name, description, startdate, enddate, status, project_id) values (5, 'Sprint 8', 'Some sprint description', '2013-01-19', '2013-02-03', 'IN_PROGRESS', 3);
insert into sprint (id, name, description, startdate, enddate, status, project_id) values (6, 'Sprint 9', 'Some sprint description', '2013-01-19', '2013-02-03', 'CREATED', 3);

insert into sprint (id, name, description, startdate, enddate, status, project_id) values (7, 'Sprint 10', 'Some sprint description', '2013-01-19', '2013-02-03', 'CLOSED', 4);
insert into sprint (id, name, description, startdate, enddate, status, project_id) values (8, 'Sprint 11', 'Some sprint description', '2013-01-19', '2013-02-03', 'IN_PROGRESS', 4);


