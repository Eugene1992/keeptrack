CREATE TABLE app_user
(
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
  CONSTRAINT fk6cag7h7of5iqdgstcsan3atrc FOREIGN KEY (managed_project_id) REFERENCES project (id),
  CONSTRAINT fkhhrku7ef90xpyxmktlri4iejb FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE UNIQUE INDEX ukt586mi4il2cuntklsyceovras ON app_user (email);
CREATE UNIQUE INDEX uk_5ssgetqhwklwfl3p6691pqfd7 ON app_user (managed_project_id);

INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender, project_id) VALUES (1, 'legendary', 'qwerty', 'EMPLOYEE', 'Evgeniy', 'Deyneka', 'deyneko55@gmail.com', 15000, '1992-12-26', '2016-02-02', 'MALE', 2);
INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (2, 'admin', 'qwerty', 'ADMIN', 'Roman', 'Andriyanov', 'randriyanov@gmail.com', 100000, '1985-10-14', '2012-05-12', 'MALE');
INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (3, 'pmanager', 'qwerty', 'PM', 'Denis', 'Ruzhuh', 'ruzhuh@gmail.com', 70000, '1982-04-02', '2008-07-21', 'MALE');
INSERT INTO app_user(id, username, password, role, first_name, last_name, email, salary, birthday, hireday, gender) VALUES (4, 'customer', 'qwerty', 'CUSTOMER', 'Elon', 'Mask', 'elon@tesla.com', 1000000, '1971-08-28', '1996-03-11', 'MALE');

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (5, 'mholmes4', 'xIPsICnk', 'Matthew', 'Holmes', '1983-12-22', '2012-02-18', 54643, 'EMPLOYEE', 'mholmes4@merriam-webster.com', 'MALE', 1);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (6, 'freddy', '96ZnqN', 'Fred', 'Bowman', '1986-08-01', '2013-02-04', 86500, 'EMPLOYEE', 'fbowman5@baidu.com', 'FEMALE', 1);

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (7, 'kbanks6', 'MlHktd', 'Katherine', 'Banks', '1975-05-15', '2009-01-30', 80332, 'EMPLOYEE', 'kbanks6@netlog.com', 'FEMALE', 2);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (8, 'rporter7', 'Ixa0wcb64', 'Richard', 'Porter', '1978-01-18', '2010-06-09', 20589, 'EMPLOYEE', 'rporter7@cornell.edu', 'FEMALE', 2);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (9, 'mrice8', 'tmAzpQ', 'Martin', 'Rice', '1983-07-17', '2010-12-27', 15508, 'EMPLOYEE', 'mrice8@php.net', 'MALE', 2);

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (10, 'nfrazier9', 'oIYRGCqbfo', 'Nicole', 'Frazier', '1983-09-26', '2007-08-16', 32685, 'EMPLOYEE', 'nfrazier9@mapquest.com', 'FEMALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (11, 'jjordana', 'C1rsRzR9', 'Jimmy', 'Jordan', '1980-05-29', '2014-11-09', 24680, 'EMPLOYEE', 'jjordana@hao123.com', 'MALE', 3);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (12, 'akingb', 'sT2BSjrKd', 'Aaron', 'King', '1981-04-29', '2015-04-13', 18418, 'EMPLOYEE', 'akingb@meetup.com', 'MALE', 3);

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (13, 'jellisc', 'Hz5h4DmWVCO', 'Jennifer', 'Ellis', '1987-10-12', '2007-07-02', 31618, 'EMPLOYEE', 'jellisc@ebay.com', 'FEMALE', 4);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (14, 'aalvarezd', 'JeZrOM', 'Alice', 'Alvarez', '1980-04-16', '2008-09-05', 61004, 'EMPLOYEE', 'aalvarezd@g.co', 'FEMALE', 4);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (15, 'mriverae', 'dpzRB7crPM', 'Maria', 'Rivera', '1987-05-22', '2007-04-05', 24692, 'EMPLOYEE', 'mriverae@histats.com', 'MALE', 4);
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender, project_id) values (16, 'dmyersf', 'toQEdC5LSk9p', 'Donna', 'Myers', '1989-07-01', '2013-12-20', 44416, 'EMPLOYEE', 'dmyersf@arizona.edu', 'FEMALE', 4);

insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender) values (17, 'cgarrettu', '126oyJB6q', 'Charles', 'Garrett', '1988-04-26', '2015-11-21', 79936, 'EMPLOYEE', 'cgarrettu@marketwatch.com', 'FEMALE');
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender) values (18, 'shicksv', 'AlpFlX0xx9fl', 'Sandra', 'Hicks', '1984-01-18', '2010-10-16', 50808, 'EMPLOYEE', 'shicksv@biblegateway.com', 'MALE');
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender) values (19, 'gmorenow', '7lxpV4gPJ2Xe', 'Gloria', 'Moreno', '1980-07-01', '2007-06-12', 62224, 'EMPLOYEE', 'gmorenow@zimbio.com', 'MALE');
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender) values (20, 'wkelleyx', 'QmaiKQ', 'Willie', 'Kelley', '1978-12-20', '2014-09-05', 29104, 'EMPLOYEE', 'wkelleyx@sfgate.com', 'FEMALE');
insert into app_user (id, username, password, first_name, last_name, birthday, hireday, salary, role, email, gender) values (21, 'fandersony', 'YlZjBxtX', 'Frances', 'Anderson', '1980-08-05', '2007-01-19', 27428, 'EMPLOYEE', 'fandersony@slashdot.org', 'MALE');
