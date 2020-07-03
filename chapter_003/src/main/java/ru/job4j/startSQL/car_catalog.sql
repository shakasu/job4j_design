create table body (
	id serial primary key,
	name varchar(200)
);
create table engine (
	id serial primary key,
	name varchar(200)
);
create table gearbox (
	id serial primary key,
	name varchar(200)
);
create table car (
	id serial primary key,
	name varchar(200),
	gearbox_id int references gearbox(id),
	body_id int references body(id),
	engine_id int references engine(id)
);

insert into body(name) values('small');
insert into body(name) values('middle');
insert into body(name) values('big');

insert into engine(name) values('slow');
insert into engine(name) values('fast');
insert into engine(name) values('medium');

insert into gearbox(name) values('Mechanical');
insert into gearbox(name) values('Automated');
insert into gearbox(name) values('Robotic');

insert into car(name, body_id, engine_id, gearbox_id) values('first', 1, 1, 1);
insert into car(name, body_id, engine_id, gearbox_id) values('second', 2, 2, 2);

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name, e.name, g.name, b.name
from car as c
inner join engine as e on e.id = c.engine_id
inner join gearbox as g on c.gearbox_id = g.id
inner join body as b on c.body_id = b.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select e.name
from car as c
right outer join engine as e on e.id = c.engine_id
where c.id is null;

select g.name
from car as c
right outer join gearbox as g on c.gearbox_id = g.id
where c.id is null;

select b.name
from car as c
right outer join body as b on c.body_id = b.id
where c.id is null;