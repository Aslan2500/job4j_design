create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table gearbox(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	gearbox_id int references gearbox(id)
);

insert into body (name) values ('body1');
insert into body (name) values ('body2');
insert into body (name) values ('body3');
insert into body (name) values ('body4');

insert into engine (name) values ('engine1');
insert into engine (name) values ('engine2');
insert into engine (name) values ('engine3');
insert into engine (name) values ('engine4');

insert into gearbox (name) values ('gearbox1');
insert into gearbox (name) values ('gearbox2');
insert into gearbox (name) values ('gearbox3');
insert into gearbox (name) values ('gearbox4');

insert into car (name, body_id, engine_id, gearbox_id) values ('car1', 1, 1, 1);
insert into car (name, body_id, engine_id, gearbox_id) values ('car2', 2, 3, 1);
insert into car (name, body_id, engine_id, gearbox_id) values ('car3', 1, 2, 1);
insert into car (name, body_id, engine_id, gearbox_id) values ('car4', 3, 1, 1);
insert into car (name, body_id, engine_id, gearbox_id) values ('car5', 1, 3, 2);
insert into car (name, body_id, engine_id, gearbox_id) values ('car6', 1, 1, 2);
insert into car (name, body_id, engine_id) values ('car7', 1, 3);
insert into car (name, engine_id, gearbox_id) values ('car8', 1, 3);
insert into car (name) values ('car9');

select c.name as car,
b.name as body,
e.name as engine,
g.name as gearbox
from car c
left join body b 
on c.body_id = b.id
left join engine e
on c.engine_id = e.id
left join gearbox g
on c.gearbox_id = g.id;

select b.name as body
from body b
left join car c
on b.id = c.body_id
where c.body_id is null;

select e.name as engine
from engine e
left join car c
on e.id = c.engine_id
where c.engine_id is null;

select g.name as gearbox
from gearbox g
left join car c
on g.id = c.gearbox_id
where c.gearbox_id is null;