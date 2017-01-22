CREATE TABLE project
(
  id INTEGER PRIMARY KEY,
  description VARCHAR(255),
  enddate DATE,
  name VARCHAR(255),
  startdate DATE,
  status VARCHAR(255)
);

INSERT INTO project(id, name, startdate, enddate, status, description) VALUES (1, 'Rivecore', '2010-01-18', '2014-11-29', 'CLOSED', 'Some project description');
INSERT INTO project(id, name, startdate, enddate, status, description) VALUES (2, 'Minerva', '2016-08-20', '2017-08-20', 'IN_PROGRESS', 'Some project description');
INSERT INTO project(id, name, startdate, status, description) VALUES (3, 'Flangex', '2016-10-12', 'IN_PROGRESS', 'Some project description');
INSERT INTO project(id, name, startdate, status, description) VALUES (4, 'Tesla', '1995-07-12', 'IN_PROGRESS', 'Some project description');
