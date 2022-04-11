create table type (
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
	price int
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Хлеб');
insert into type (name) values ('Молоко');

insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, date '2020-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Сыр творожный', 1, date '2022-05-10', 100);
insert into product (name, type_id, expired_date, price) values ('Сыр пармезан', 1, date '2023-01-01', 250);
insert into product (name, type_id, expired_date, price) values ('Хлеб черный', 2, date '2022-04-12', 75);
insert into product (name, type_id, expired_date, price) values ('Хлеб белый', 2, date '2021-04-10', 65);
insert into product (name, type_id, expired_date, price) values ('Молоко коровье', 3, date '2023-01-01', 135);
insert into product (name, type_id, expired_date, price) values ('Молоко козье', 3, date '2022-10-10', 200);
insert into product (name, type_id, expired_date, price) values ('Молоко мороженое', 3, date '2023-10-10', 400);

select * from type;
select * from product;

select p.name from product p where p.type_id = 1;

select p.name from product p where p.name like '%мороженое%';

select p.name from product p where current_date > p.expired_date;

select p.name, p.price
from product p 
where p.price = (select max(price) from product);

select t.name, count(p)
from type t
join product p
on t.id = p.type_id
group by t.name;

select p.name from product p where p.type_id = 1 or p.type_id = 3;

select t.name, count(p)
from type t
join product p
on t.id = p.type_id
group by t.name
having count(p) < 10;

select p.name, t.name
from type t
join product p
on t.id = p.type_id;