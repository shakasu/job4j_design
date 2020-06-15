--table creation
CREATE TABLE comments (
    id serial PRIMARY KEY,
    name VARCHAR (2000),
    text VARCHAR (2000)
);
CREATE TABLE attach (
    id serial PRIMARY KEY,
    name VARCHAR (2000),
    text VARCHAR (2000)
);
CREATE TABLE item (
    id serial PRIMARY KEY,
    name VARCHAR (2000),
    attach_id INT REFERENCES attach (id),
    comments_id INT REFERENCES comments (id)
);
CREATE TABLE state (
    id serial PRIMARY KEY,
    name VARCHAR (2000),
    item_id INT REFERENCES item (id),
    switch BOOL
);
CREATE TABLE category (
    id serial PRIMARY KEY,
    name VARCHAR(2000),
    item_id INT REFERENCES item (id),
    category_number INT
);
CREATE TABLE user_list (
    id serial PRIMARY KEY,
    name VARCHAR(2000),
    item_id INT REFERENCES item (id),
    creation_date DATE
);
CREATE TABLE role (
    id serial PRIMARY KEY,
    name VARCHAR(2000),
    user_list_id INT REFERENCES user_list (id),
    description VARCHAR(2000)
);
CREATE TABLE rules (
    id serial PRIMARY KEY,
    name VARCHAR(2000),
    description VARCHAR(2000)
);
CREATE TABLE rules_for_roles (
    id serial PRIMARY KEY,
    role_id INT REFERENCES role (id),
    rules_id INT REFERENCES rules (id)
);

--rules
INSERT INTO rules (name, description) VALUES ('The first rule', 'you do not talk about sql');
INSERT INTO rules (name, description) VALUES ('The second rule', 'you do never talk about sql');
--category
INSERT INTO category (name, category_number) VALUES ('system error', '32');
INSERT INTO category (name, category_number) VALUES ('epic error', '101');
--role
INSERT INTO role (name, description) VALUES ('tester', 'who test the service');
INSERT INTO role (name, description) VALUES ('admin', 'who build the service');
--comments
INSERT INTO comments (name, text) VALUES ('urgent', 'do it right now');
INSERT INTO comments (name, text) VALUES ('regular', 'all in good time');
--attach
INSERT INTO attach (name, text) VALUES ('case 1', 'description of case 1');
INSERT INTO attach (name, text) VALUES ('case 2', 'description of case 2');
--item
INSERT INTO item (name) VALUES ('technical support request');
--state
INSERT INTO state (name, switch) VALUES ('first state', TRUE);
--user
INSERT INTO user_list (name, creation_date) VALUES ('user', '1999-01-08 04:05:06');







