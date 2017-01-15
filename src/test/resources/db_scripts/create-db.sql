CREATE TABLE project ( id INTEGER PRIMARY KEY NOT NULL, enddate DATE, name VARCHAR(255), startdate DATE, status VARCHAR(255));

CREATE TABLE app_user (
  id INTEGER PRIMARY KEY NOT NULL,
  birthday DATE,
  email VARCHAR(255),
  first_name VARCHAR(255),
  gender VARCHAR(255),
  hireday DATE,
  last_name VARCHAR(255),
  password VARCHAR(255),
  role VARCHAR(255),
  salary INTEGER NOT NULL,
  username VARCHAR(255),
  managed_project_id INTEGER,
  project_id INTEGER,
  CONSTRAINT user_managed_project FOREIGN KEY (managed_project_id) REFERENCES project (id),
  CONSTRAINT user_project FOREIGN KEY (project_id) REFERENCES project (id)
);
CREATE UNIQUE INDEX ukt586mi4il2cuntklsyceovras ON app_user (email);
CREATE UNIQUE INDEX uk_5ssgetqhwklwfl3p6691pqfd7 ON app_user (managed_project_id);

CREATE TABLE sprint
(
  id INTEGER PRIMARY KEY NOT NULL,
  description VARCHAR(255),
  name VARCHAR(255),
  status VARCHAR(255),
  project_id INTEGER,
  CONSTRAINT fkep2okjn6bvwwnre5o1w7y5psd FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE task
(
  id INTEGER PRIMARY KEY NOT NULL,
  description VARCHAR(255),
  estimate INTEGER NOT NULL,
  name VARCHAR(255),
  status INTEGER,
  assigner_id INTEGER,
  creator_id INTEGER,
  sprint_id INTEGER,
  CONSTRAINT fkawitl8sxbbsoa72bvove79t51 FOREIGN KEY (assigner_id) REFERENCES app_user (id),
  CONSTRAINT fkns5qgv3boxd8ctgte231tdvtc FOREIGN KEY (creator_id) REFERENCES app_user (id),
  CONSTRAINT fkoew9cen5nj5c154u5y3clh90v FOREIGN KEY (sprint_id) REFERENCES sprint (id)
);

INSERT INTO project(id, name, status) VALUES (1, 'Impleme', 'CREATED');
INSERT INTO project(id, name, status) VALUES (2, 'Threddy', 'CREATED');
INSERT INTO project(id, name, startdate, status) VALUES (3, 'Minerva', '2016-08-20', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (4, 'Flangex', '2016-10-12', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (5, 'Tesla', '1995-07-12', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (6, 'Kaler', '2015-12-17', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (7, 'CoveBox', '2016-04-30', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (8, 'Sugger', '2016-09-09', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (9, 'Deton', '2016-01-21', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (10, 'Mobili', '2016-03-11', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (11, 'ExtJSLinq', '2016-02-14', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, status) VALUES (12, 'Websheling', '2016-02-17', 'IN_PROGRESS');
INSERT INTO project(id, name, startdate, enddate, status) VALUES (13, 'Aidogo', '2010-06-06', '2012-08-20', 'CLOSED');
INSERT INTO project(id, name, startdate, enddate, status) VALUES (14, 'Titartalk', '2010-01-23', '2013-10-19', 'CLOSED');
INSERT INTO project(id, name, startdate, enddate, status) VALUES (15, 'Rivecore', '2010-01-18', '2014-11-29', 'CLOSED');

INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (1, 'employee', 'qwerty', 'EMPLOYEE', 'Evgeniy', 'Deyneka', 'deyneko55@gmail.com', 15000, '1992-12-26', '2016-02-02', 'MALE');
INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (2, 'admin', 'qwerty', 'ADMIN', 'Roman', 'Andriyanov', 'randriyanov@gmail.com', 100000, '1985-10-14', '2012-05-12', 'MALE');
INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (3, 'pmanager', 'qwerty', 'PM', 'Denis', 'Ruzhuh', 'ruzhuh@gmail.com', 70000, '1982-04-02', '2008-07-21', 'MALE');
INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (4, 'customer', 'qwerty', 'CUSTOMER', 'Elon', 'Mask', 'elon@tesla.com', 1000000, '1971-08-28', '1996-03-11', 'MALE');

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (5, 'mholmes4', 'xIPsICnk', 'Matthew', 'Holmes', '1983-12-22', '2012-02-18', 54643, 'EMPLOYEE', 'mholmes4@merriam-webster.com', 'MALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (6, 'fbowman5', '96ZnqN', 'Fred', 'Bowman', '1986-08-01', '2013-02-04', 86500, 'EMPLOYEE', 'fbowman5@baidu.com', 'FEMALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (7, 'kbanks6', 'MlHktd', 'Katherine', 'Banks', '1975-05-15', '2009-01-30', 80332, 'EMPLOYEE', 'kbanks6@netlog.com', 'FEMALE', 7);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (8, 'rporter7', 'Ixa0wcb64', 'Richard', 'Porter', '1978-01-18', '2010-06-09', 20589, 'EMPLOYEE', 'rporter7@cornell.edu', 'FEMALE', 11);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (9, 'mrice8', 'tmAzpQ', 'Martin', 'Rice', '1983-07-17', '2010-12-27', 15508, 'EMPLOYEE', 'mrice8@php.net', 'MALE', 10);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (10, 'nfrazier9', 'oIYRGCqbfo', 'Nicole', 'Frazier', '1983-09-26', '2007-08-16', 32685, 'EMPLOYEE', 'nfrazier9@mapquest.com', 'FEMALE', 15);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (11, 'jjordana', 'C1rsRzR9', 'Jimmy', 'Jordan', '1980-05-29', '2014-11-09', 24680, 'EMPLOYEE', 'jjordana@hao123.com', 'MALE', 4);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (12, 'akingb', 'sT2BSjrKd', 'Aaron', 'King', '1981-04-29', '2015-04-13', 18418, 'EMPLOYEE', 'akingb@meetup.com', 'MALE', 11);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (13, 'jellisc', 'Hz5h4DmWVCO', 'Jennifer', 'Ellis', '1987-10-12', '2007-07-02', 31618, 'EMPLOYEE', 'jellisc@ebay.com', 'FEMALE', 14);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (14, 'aalvarezd', 'JeZrOM', 'Alice', 'Alvarez', '1980-04-16', '2008-09-05', 61004, 'EMPLOYEE', 'aalvarezd@g.co', 'FEMALE', 14);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (15, 'mriverae', 'dpzRB7crPM', 'Maria', 'Rivera', '1987-05-22', '2007-04-05', 24692, 'EMPLOYEE', 'mriverae@histats.com', 'MALE', 12);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (16, 'dmyersf', 'toQEdC5LSk9p', 'Donna', 'Myers', '1989-07-01', '2013-12-20', 44416, 'EMPLOYEE', 'dmyersf@arizona.edu', 'FEMALE', 13);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (17, 'bharperg', '3VqezTtL', 'Brian', 'Harper', '1975-04-27', '2015-12-19', 80032, 'EMPLOYEE', 'bharperg@shop-pro.jp', 'FEMALE', 13);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (18, 'edunnh', 'ddvMq1h4Qp', 'Eric', 'Dunn', '1980-11-14', '2005-09-22', 64520, 'EMPLOYEE', 'edunnh@patch.com', 'MALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (19, 'egarciai', 'wBWfMCy7nGoF', 'Ernest', 'Garcia', '1984-02-28', '2012-12-14', 59228, 'EMPLOYEE', 'egarciai@livejournal.com', 'MALE', 8);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (20, 'khamiltonj', 'jNGoct3WuYFu', 'Kathy', 'Hamilton', '1985-11-22', '2010-11-19', 95681, 'EMPLOYEE', 'khamiltonj@paginegialle.it', 'MALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (21, 'thenryk', 'ndJCovFJT', 'Thomas', 'Henry', '1978-07-13', '2011-11-19', 47195, 'EMPLOYEE', 'thenryk@buzzfeed.com', 'MALE', 7);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (22, 'bjamesl', 'XMLtQrfs', 'Benjamin', 'James', '1987-01-18', '2005-10-21', 5289, 'EMPLOYEE', 'bjamesl@sogou.com', 'FEMALE', 9);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (23, 'pwellsm', 'gt5AFDf', 'Peter', 'Wells', '1978-06-29', '2008-07-30', 79914, 'EMPLOYEE', 'pwellsm@bigcartel.com', 'FEMALE', 5);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (24, 'ddeann', '9xFtnBYhoIG', 'Douglas', 'Dean', '1976-03-13', '2007-06-16', 95054, 'EMPLOYEE', 'ddeann@homestead.com', 'MALE', 2);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (25, 'gmyerso', 'QcC08FXmR6Sw', 'Gregory', 'Myers', '1979-04-09', '2008-07-04', 51422, 'EMPLOYEE', 'gmyerso@irs.gov', 'FEMALE', 7);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (26, 'cpetersonp', 'RSz3RrN', 'Craig', 'Peterson', '1983-07-19', '2014-05-10', 7180, 'EMPLOYEE', 'cpetersonp@army.mil', 'MALE', 1);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (27, 'afoxq', 'z33KlMRWXMT', 'Antonio', 'Fox', '1984-11-21', '2014-04-15', 92169, 'EMPLOYEE', 'afoxq@amazon.com', 'FEMALE', 11);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (28, 'jchapmanr', 'TzfJtTcfEkyU', 'Janice', 'Chapman', '1980-12-23', '2006-09-23', 87276, 'EMPLOYEE', 'jchapmanr@desdev.cn', 'MALE', 11);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (29, 'rwallaces', 'GJJBeRgROEm', 'Raymond', 'Wallace', '1977-02-04', '2011-07-04', 28074, 'EMPLOYEE', 'rwallaces@cbslocal.com', 'MALE', 2);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (30, 'afoxt', '2fNad8GA', 'Ann', 'Fox', '1980-10-04', '2010-03-22', 58322, 'EMPLOYEE', 'afoxt@cam.ac.uk', 'MALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (31, 'cgarrettu', '126oyJB6q', 'Charles', 'Garrett', '1988-04-26', '2015-11-21', 79936, 'EMPLOYEE', 'cgarrettu@marketwatch.com', 'FEMALE', 1);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (32, 'shicksv', 'AlpFlX0xx9fl', 'Sandra', 'Hicks', '1984-01-18', '2010-10-16', 50808, 'EMPLOYEE', 'shicksv@biblegateway.com', 'MALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (33, 'gmorenow', '7lxpV4gPJ2Xe', 'Gloria', 'Moreno', '1980-07-01', '2007-06-12', 62224, 'EMPLOYEE', 'gmorenow@zimbio.com', 'MALE', 10);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (34, 'wkelleyx', 'QmaiKQ', 'Willie', 'Kelley', '1978-12-20', '2014-09-05', 29104, 'EMPLOYEE', 'wkelleyx@sfgate.com', 'FEMALE', 11);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (35, 'fandersony', 'YlZjBxtX', 'Frances', 'Anderson', '1980-08-05', '2007-01-19', 27428, 'EMPLOYEE', 'fandersony@slashdot.org', 'MALE', 8);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (36, 'rwelchz', 'DZhVCs', 'Ruth', 'Welch', '1983-07-08', '2013-01-12', 33907, 'EMPLOYEE', 'rwelchz@altervista.org', 'MALE', 15);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (37, 'khall10', 'OVPzv4nlTV', 'Kimberly', 'Hall', '1985-12-16', '2010-05-01', 46786, 'EMPLOYEE', 'khall10@wsj.com', 'FEMALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (38, 'callen11', 'hnvf3OcHv', 'Christine', 'Allen', '1982-08-20', '2015-12-01', 37617, 'EMPLOYEE', 'callen11@163.com', 'FEMALE', 6);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (39, 'wbanks12', 'Qw3hAK4bNRe', 'Walter', 'Banks', '1984-12-16', '2014-06-28', 95600, 'EMPLOYEE', 'wbanks12@europa.eu', 'FEMALE', 8);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (40, 'jcastillo13', 'MZmYvu2aQX', 'Jacqueline', 'Castillo', '1986-10-02', '2005-09-24', 91573, 'EMPLOYEE', 'jcastillo13@washington.edu', 'MALE', 14);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (41, 'breynolds14', '21GoW9', 'Bonnie', 'Reynolds', '1983-10-12', '2009-06-01', 91212, 'EMPLOYEE', 'breynolds14@w3.org', 'FEMALE', 9);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (42, 'vmeyer15', 'gmUJQ1tw4n', 'Victor', 'Meyer', '1988-09-14', '2012-08-14', 95807, 'EMPLOYEE', 'vmeyer15@newsvine.com', 'FEMALE', 6);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (43, 'twhite16', 'uuexbdoQSS', 'Teresa', 'White', '1975-02-05', '2008-12-09', 36525, 'EMPLOYEE', 'twhite16@nature.com', 'FEMALE', 11);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (44, 'droberts17', 'S1med8SGtpa', 'Donald', 'Roberts', '1981-07-06', '2007-06-10', 15096, 'EMPLOYEE', 'droberts17@jimdo.com', 'FEMALE', 8);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (45, 'plarson18', 'ocgyIL', 'Pamela', 'Larson', '1975-09-01', '2012-05-22', 31853, 'EMPLOYEE', 'plarson18@tinyurl.com', 'MALE', 10);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (46, 'jford19', 'r3fs1vo', 'Jose', 'Ford', '1979-10-29', '2008-10-02', 63750, 'EMPLOYEE', 'jford19@webs.com', 'MALE', 6);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (47, 'sgardner1a', 'iB1ZgeKH0S', 'Samuel', 'Gardner', '1988-06-27', '2006-01-25', 38088, 'EMPLOYEE', 'sgardner1a@soundcloud.com', 'MALE', 5);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (48, 'jelliott1b', 'I4S9eO', 'Jose', 'Elliott', '1981-06-29', '2006-06-18', 43922, 'EMPLOYEE', 'jelliott1b@e-recht24.de', 'FEMALE', 6);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (49, 'dnelson1c', 'mZ6G04tEcyP', 'Daniel', 'Nelson', '1982-08-17', '2015-11-22', 32399, 'EMPLOYEE', 'dnelson1c@bloglines.com', 'FEMALE', 1);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (50, 'jrussell1d', 'lJnLqjIQIo', 'Judith', 'Russell', '1989-01-07', '2007-02-14', 14052, 'EMPLOYEE', 'jrussell1d@mozilla.org', 'FEMALE', 7);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (51, 'mjames0', 'BsT1QhCGqnZn', 'Mark', 'James', '1980-12-06', '2008-09-23', 20188, 'EMPLOYEE', 'mjames0@woothemes.com', 'FEMALE', 2);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (52, 'ghunter1', 'XyM6Zfk', 'Gary', 'Hunter', '1983-02-14', '2012-08-25', 87112, 'EMPLOYEE', 'ghunter1@hubpages.com', 'MALE', 7);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (53, 'ddavis2', 'fywSvLOBsy', 'Diane', 'Davis', '1989-01-31', '2007-12-19', 49968, 'EMPLOYEE', 'ddavis2@sohu.com', 'MALE', 5);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (54, 'gcampbell3', 'bnO11M3tZI4', 'Gloria', 'Campbell', '1979-05-05', '2006-08-27', 48370, 'EMPLOYEE', 'gcampbell3@domainmarket.com', 'FEMALE', 4);

insert into sprint (id, name, description, status, project_id) values (1, 'Sprint 1', 'Some sprint description', 'CLOSED', 1);
insert into sprint (id, name, description, status, project_id) values (2, 'Sprint 2', 'Some sprint description', 'IN_PROGRESS', 5);
insert into sprint (id, name, description, status, project_id) values (3, 'Sprint 3', 'Some sprint description', 'IN_PROGRESS', 2);
insert into sprint (id, name, description, status, project_id) values (4, 'Sprint 4', 'Some sprint description', 'CLOSED', 7);
insert into sprint (id, name, description, status, project_id) values (5, 'Sprint 5', 'Some sprint description', 'CREATED', 14);
insert into sprint (id, name, description, status, project_id) values (6, 'Sprint 6', 'Some sprint description', 'CLOSED', 5);
insert into sprint (id, name, description, status, project_id) values (7, 'Sprint 7', 'Some sprint description', 'CREATED', 10);
insert into sprint (id, name, description, status, project_id) values (8, 'Sprint 8', 'Some sprint description', 'IN_PROGRESS', 10);
insert into sprint (id, name, description, status, project_id) values (9, 'Sprint 9', 'Some sprint description', 'CREATED', 4);
insert into sprint (id, name, description, status, project_id) values (10, 'Sprint 10', 'Some sprint description', 'IN_PROGRESS', 8);
insert into sprint (id, name, description, status, project_id) values (11, 'Sprint 11', 'Some sprint description', 'CREATED', 1);
insert into sprint (id, name, description, status, project_id) values (12, 'Sprint 12', 'Some sprint description', 'CREATED', 7);
insert into sprint (id, name, description, status, project_id) values (13, 'Sprint 13', 'Some sprint description', 'IN_PROGRESS', 7);
insert into sprint (id, name, description, status, project_id) values (14, 'Sprint 14', 'Some sprint description', 'IN_PROGRESS', 8);
insert into sprint (id, name, description, status, project_id) values (15, 'Sprint 15', 'Some sprint description', 'IN_PROGRESS', 2);
insert into sprint (id, name, description, status, project_id) values (16, 'Sprint 16', 'Some sprint description', 'IN_PROGRESS', 2);
insert into sprint (id, name, description, status, project_id) values (17, 'Sprint 17', 'Some sprint description', 'CLOSED', 2);
insert into sprint (id, name, description, status, project_id) values (18, 'Sprint 18', 'Some sprint description', 'CLOSED', 13);
insert into sprint (id, name, description, status, project_id) values (19, 'Sprint 19', 'Some sprint description', 'IN_PROGRESS', 13);
insert into sprint (id, name, description, status, project_id) values (20, 'Sprint 20', 'Some sprint description', 'CREATED', 3);
insert into sprint (id, name, description, status, project_id) values (21, 'Sprint 21', 'Some sprint description', 'CREATED', 8);
insert into sprint (id, name, description, status, project_id) values (22, 'Sprint 22', 'Some sprint description', 'CLOSED', 14);
insert into sprint (id, name, description, status, project_id) values (23, 'Sprint 23', 'Some sprint description', 'CREATED', 3);
insert into sprint (id, name, description, status, project_id) values (24, 'Sprint 24', 'Some sprint description', 'IN_PROGRESS', 13);
insert into sprint (id, name, description, status, project_id) values (25, 'Sprint 25', 'Some sprint description', 'IN_PROGRESS', 13);
insert into sprint (id, name, description, status, project_id) values (26, 'Sprint 26', 'Some sprint description', 'CLOSED', 2);
insert into sprint (id, name, description, status, project_id) values (27, 'Sprint 27', 'Some sprint description', 'CLOSED', 1);
insert into sprint (id, name, description, status, project_id) values (28, 'Sprint 28', 'Some sprint description', 'CLOSED', 6);
insert into sprint (id, name, description, status, project_id) values (29, 'Sprint 29', 'Some sprint description', 'CLOSED', 3);
insert into sprint (id, name, description, status, project_id) values (30, 'Sprint 30', 'Some sprint description', 'CREATED', 5);
insert into sprint (id, name, description, status, project_id) values (31, 'Sprint 31', 'Some sprint description', 'CREATED', 1);
insert into sprint (id, name, description, status, project_id) values (32, 'Sprint 32', 'Some sprint description', 'IN_PROGRESS', 2);
insert into sprint (id, name, description, status, project_id) values (33, 'Sprint 33', 'Some sprint description', 'CREATED', 5);
insert into sprint (id, name, description, status, project_id) values (34, 'Sprint 34', 'Some sprint description', 'CLOSED', 8);
insert into sprint (id, name, description, status, project_id) values (35, 'Sprint 35', 'Some sprint description', 'CLOSED', 8);
insert into sprint (id, name, description, status, project_id) values (36, 'Sprint 36', 'Some sprint description', 'CLOSED', 10);
insert into sprint (id, name, description, status, project_id) values (37, 'Sprint 37', 'Some sprint description', 'IN_PROGRESS', 9);
insert into sprint (id, name, description, status, project_id) values (38, 'Sprint 38', 'Some sprint description', 'IN_PROGRESS', 6);
insert into sprint (id, name, description, status, project_id) values (39, 'Sprint 39', 'Some sprint description', 'CLOSED', 15);
insert into sprint (id, name, description, status, project_id) values (40, 'Sprint 40', 'Some sprint description', 'CLOSED', 3);
insert into sprint (id, name, description, status, project_id) values (41, 'Sprint 41', 'Some sprint description', 'IN_PROGRESS', 8);
insert into sprint (id, name, description, status, project_id) values (42, 'Sprint 42', 'Some sprint description', 'CREATED', 4);
insert into sprint (id, name, description, status, project_id) values (43, 'Sprint 43', 'Some sprint description', 'CREATED', 6);
insert into sprint (id, name, description, status, project_id) values (44, 'Sprint 44', 'Some sprint description', 'CREATED', 4);
insert into sprint (id, name, description, status, project_id) values (45, 'Sprint 45', 'Some sprint description', 'CLOSED', 14);

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, managed_project_id) values (55, 'gcampbell3', 'bnO11M3tZI4', 'Gloria', 'Campbell', '1979-05-05', '2006-08-27', 48370, 'PM', 'gcampbell13@domainmarket.com', 'FEMALE', 1);
