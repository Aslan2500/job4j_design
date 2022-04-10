create table companies(
	id serial primary key,
	name varchar(255)
);

create table shareholders(
	id serial primary key,
	name varchar(255),
	companies_id int references companies(id)
);

insert into companies (name) values ('Apple');
insert into companies (name) values ('Kroger');
insert into companies (name) values ('Bank Of America');
insert into companies (name) values ('NVIDIA');

insert into shareholders (name, companies_id) values ('Boris', 1);
insert into shareholders (name, companies_id) values ('Ivan', 1);
insert into shareholders (name, companies_id) values ('Artem', 2);
insert into shareholders (name, companies_id) values ('Alex', 2);
insert into shareholders (name, companies_id) values ('Mikhail', 1);
insert into shareholders (name, companies_id) values ('Genadii', 3);
insert into shareholders (name, companies_id) values ('Ruslan', 3);
insert into shareholders (name, companies_id) values ('Liza', 3);

select ss.name as Имя, cc.name as Название from shareholders as ss join companies as cc on ss.companies_id = cc.id;