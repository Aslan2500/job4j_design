create table carNumber(
    id serial primary key,
    number int
);

create table car(
    id serial primary key,
    name varchar(255)
);

create table carNumber_car(
    id serial primary key,
    carNumber_id int references carNumber(id) unique,
    car_id int references car(id) unique
);