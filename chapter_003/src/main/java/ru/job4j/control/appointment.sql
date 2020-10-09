--1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).
--Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах. У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.

--таблица пользователей
create table users (
    id serial primary key,
    name varchar(200)
);

--таблица встреч
create table appointment (
    id serial primary key,
    name varchar(200)
);

--вспомогательная таблица для создания отношения многие ко многим
create table appointment_for_user (
    id serial primary key,
    users_id int references users (id),
    appointment_id int references appointment (id),
    confirm boolean default false,
    request boolean default false
);

insert into users(name) values('person1');
insert into users(name) values('person2');
insert into users(name) values('person3');

insert into appointment(name) values('appointment1');
insert into appointment(name) values('appointment2');
insert into appointment(name) values('appointment3');

insert into appointment_for_user(users_id, appointment_id) values (1, 1);
insert into appointment_for_user(users_id, appointment_id) values (2, 1);
insert into appointment_for_user(users_id, appointment_id) values (3, 1);

--три заявки от трех пользователей
update appointment_for_user
set request = 'true'
where users_id = 1
and appointment_id = 1;

update appointment_for_user
set request = 'true'
where users_id = 2
and appointment_id = 1;

update appointment_for_user
set request = 'true'
where users_id = 3
and appointment_id = 1;

--три подтверждения от трех пользователей
update appointment_for_user
set confirm = 'true'
where users_id = 1
and appointment_id = 1;

update appointment_for_user
set confirm = 'true'
where users_id = 2
and appointment_id = 1;

update appointment_for_user
set confirm = 'true'
where users_id = 3
and appointment_id = 1;

--три заявки, которые не будут подтверждены
insert into appointment_for_user(users_id, appointment_id) values (1, 2);
insert into appointment_for_user(users_id, appointment_id) values (2, 2);
insert into appointment_for_user(users_id, appointment_id) values (3, 2);

update appointment_for_user
set request = 'true'
where users_id = 1
and appointment_id = 2;

update appointment_for_user
set request = 'true'
where users_id = 2
and appointment_id = 2;

update appointment_for_user
set request = 'true'
where users_id = 3
and appointment_id = 2;

--три заявки, на которые не будет запроса
insert into appointment_for_user(users_id, appointment_id) values (1, 3);
insert into appointment_for_user(users_id, appointment_id) values (2, 3);
insert into appointment_for_user(users_id, appointment_id) values (3, 3);


--2. Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
select appointment_id as app,
  sum(case when request = 'true' then 1 else 0 end) as requested,
  sum(case when confirm in ('true') then 1 else 0 end) as confirmed
into summary
from appointment_for_user
group by appointment_id;

select * from summary;

--3. Нужно получить все совещания, где не было ни одной заявки на посещения
select count(app)
from summary
where requested = 0
and confirmed = 0;






