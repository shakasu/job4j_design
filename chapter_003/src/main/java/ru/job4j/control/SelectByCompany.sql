--создание таблиц.
CREATE TABLE company(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id));

CREATE TABLE person(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id));

--заполнение - три персоны в трех разных компаниях.
insert into company (id, name) values (1, 'name1');
insert into company (id, name) values (2, 'name2');
insert into company (id, name) values (5, 'name5');

insert into person (id, name, company_id) values (1, 'person1', 1);
insert into person (id, name, company_id) values (2, 'person2', 2);
insert into person (id, name, company_id) values (5, 'person5', 5);

--скрипт выводит имя персон (и имена их компаний), состоящий НЕ в компаниях с id=5.
select p.name, c.name
from person as p
right outer join company as c on c.id = p.company_id
where c.id != 5;

--добавляем двух персон их одну общую компанию.
insert into person (id, name, company_id) values (3, 'person3', 3);
insert into person (id, name, company_id) values (4, 'person4', 3);
insert into company (id, name) values(3, 'name3');

--скрипт соединяет название компании и количество ее персон, и обрезает сгруппированный вывод до одной компании.
select c.name, count(p)
from person as p
right outer join company as c on c.id = p.company_id
Group by c.name
fetch first 1 rows only;