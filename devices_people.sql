create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Iphone', 10000.0);
insert into devices (name, price) values ('PlayStation', 7000.0);
insert into devices (name, price) values ('Samsung', 9900.0);
insert into devices (name, price) values ('Ipod', 4000.0);

insert into people (name) values ('Boris');
insert into people (name) values ('Ivan');
insert into people (name) values ('Alex');
insert into people (name) values ('Lev');

insert into devices_people (device_id, people_id) values (1, 1), (1, 4);
insert into devices_people (device_id, people_id) values (2, 3);
insert into devices_people (device_id, people_id) values (3, 2);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (4, 1), (4, 2);

select avg(price) from devices;

select p.name, avg(d.price)
from devices d
join people p
on p.id = d.id
group by p.name;

select p.name, avg(d.price)
from devices d
join people p
on p.id = d.id
group by p.name
having avg(d.price) > 5000;