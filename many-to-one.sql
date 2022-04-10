create table illness(
    id serial primary key,
    name varchar(255)
);

create table people(
    id serial primary key,
    name varchar(255),
    illness_id int references illness(id)
);

insert into illness(name) values ('COVID');
insert into people(name, illness_id) VALUES ('Ivan', 1);

select * from people;

select * from illness where id in (select id from people);