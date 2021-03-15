-- creating the schema for the project

DROP TABLE IF EXISTS project1.roles;
CREATE TABLE project1.roles(
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(50) UNIQUE NOT NULL
);
	

DROP TABLE IF EXISTS project1.users;
CREATE TABLE project1.users (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	username VARCHAR(50),
	pass VARCHAR(50),
	email VARCHAR(50),
	role_id INTEGER NOT NULL REFERENCES project1.roles(id)
	
);

DROP TABLE IF EXISTS project1.reimburse_type;
CREATE TABLE project1.reimburse_type(
	id SERIAL PRIMARY KEY,
	r_type VARCHAR(50) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS project1.reimburse_status;
CREATE TABLE project1.reimburse_status(
	id SERIAL PRIMARY KEY,
	r_status VARCHAR(50) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS project1.reimbursments;
CREATE TABLE project1.reimbursments(
	id SERIAL PRIMARY KEY,
	amount NUMERIC(20, 2) NOT NULL,
	submitted TIMESTAMP NOT NULL,
	resolved TIMESTAMP,
	description VARCHAR(250),
	receipt BYTEA,
	author INTEGER NOT NULL REFERENCES project1.users(id),
	resolver INTEGER REFERENCES project1.users(id),
	status_id INTEGER NOT NULL REFERENCES project1.reimburse_status(id),
	type_id INTEGER NOT NULL REFERENCES project1.reimburse_type(id)
);

-- testing SQL statements in the DAO class

SELECT * FROM project1.reimbursments INNER JOIN project1.reimburse_status ON project1.reimbursments.status_id = project1.reimburse_status.id
	 INNER JOIN project1.reimburse_type ON project1.reimbursments.type_id = project1.reimburse_type.id;


SELECT * FROM project1.users INNER JOIN project1.roles ON project1.users.role_id = project1.roles.id;

SELECT * FROM project1.reimbursments INNER JOIN project1.reimburse_status ON project1.reimbursments.status_id = project1.reimburse_status.id
 INNER JOIN project1.reimburse_type ON project1.reimbursments.type_id = project1.reimburse_type.id INNER JOIN project1.users ON 
project1.reimbursments.author = project1.users.id WHERE username = 'e123';

-- inserting values into reimbursment table

INSERT INTO project1.roles (id, role_name) VALUES (1, 'Employee');
INSERT INTO project1.roles (id, role_name) VALUES (2, 'Manager');

INSERT INTO project1.reimburse_status (id, r_status) VALUES (1, 'Approved');
INSERT INTO project1.reimburse_status (id, r_status) VALUES (2, 'Pending');
INSERT INTO project1.reimburse_status (id, r_status) VALUES (3, 'Denied');

INSERT INTO project1.reimburse_type (id, r_type) VALUES (1, 'Lodging');
INSERT INTO project1.reimburse_type (id, r_type) VALUES (2, 'Travel');
INSERT INTO project1.reimburse_type (id, r_type) VALUES (3, 'Food');
INSERT INTO project1.reimburse_type (id, r_type) VALUES (4, 'Other');

-- Test dummys

INSERT INTO project1.users (first_name, last_name, username, pass, email, role_id)
	VALUES ('Employee', 'EmployeeL', 'e123', 'password', 'emp1@mail.com', 1);

INSERT INTO project1.users (first_name, last_name, username, pass, email, role_id)
	VALUES ('Manager', 'ManagerL', 'm123', 'password', 'man1@mail.com', 2);

INSERT INTO project1.reimbursments (amount, submitted, description, author, status_id, type_id)
	VALUES (100, current_timestamp, 'For the hotel I stayed at during the business trip', 1, 2, 1);

INSERT INTO project1.reimbursments (amount, submitted, description, author, resolver, status_id, type_id)
	VALUES (100, current_timestamp, 'For the hotel I stayed at during the business trip', 1, 2, 1, 1);

UPDATE project1.reimbursments SET status_id = 2 WHERE id = 1 ;

DELETE FROM project1.users WHERE users.id = 4;
















