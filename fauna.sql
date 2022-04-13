create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Bear', 9000, date '1900-01-01');
insert into fauna (name, avg_age) values ('Shark', 15000);
insert into fauna (name, avg_age, discovery_date) values ('Dog', 8900, date '590-03-10');
insert into fauna (name, avg_age, discovery_date) values ('Deer', 12000, date '1302-05-02');
insert into fauna (name, avg_age, discovery_date) values ('Sunfish', 1000, date '1900-01-01');
insert into fauna (name, avg_age, discovery_date) values ('Bluefish', 300, date '1900-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';