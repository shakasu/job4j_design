--table creation
create table type (
    id serial primary key,
    name varchar (2000)
);
create table product (
    id serial primary key,
    name varchar (2000),
    type_id int references type (id),
    expired_date date,
    price int
);
--put data in types
insert into type (name) values ('СЫР');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('МОРОЖЕННОЕ');
--put data in products
insert into product (name, type_id, expired_date, price)
values ('пискаревское молоко', 2, '2020-07-08 04:05:06', 50);

insert into product (name, type_id, expired_date, price)
values ('сыр гауда', 1, '2020-07-08 04:05:06', 200);

insert into product (name, type_id, expired_date, price)
values ('пломбир мороженное', 3, '2020-09-08 04:05:06', 70);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select *
from
    product as p inner join type as t
on
    p.type_id = t.id
where
    t.name = 'СЫР';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select *
from product
where
    name like '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select *
from product as p
where
--не просрочен на данный момент
	current_date < p.expired_date
and
	extract(month from expired_date) = mod(cast(extract(month from current_date) + 1 as int), 12);
--4. Написать запрос, который выводит самый дорогой продукт.
select *
from product
where
    price = (SELECT MAX(price) FROM product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*)
from product
where type_id = 1;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select *
from
    product as p inner join type as t
on
    p.type_id = t.id
where
    t.name = 'СЫР' or t.name = 'МОЛОКО';
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name
from product as p
inner join type as t on p.type_id = t.id
group by t.name
having count(t.id) < 10;
--8. Вывести все продукты и их тип.
select *
from product as p
inner join type as t on p.type_id = t.id;
