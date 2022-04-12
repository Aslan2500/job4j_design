create table departments(
	id serial primary key,
	name varchar(255)
);

create table employers(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

insert into departments (name) values ('Development');
insert into departments (name) values ('Management');
insert into departments (name) values ('Finance');
insert into departments (name) values ('Design');

insert into employers (name, departments_id) values ('Boris', 1);
insert into employers (name, departments_id) values ('Mikhail', 1);
insert into employers (name, departments_id) values ('Alex', 1);
insert into employers (name, departments_id) values ('Elena', 1);
insert into employers (name, departments_id) values ('Aslan', 2);
insert into employers (name, departments_id) values ('Ivan', 2);
insert into employers (name, departments_id) values ('Eva', 3);
insert into employers (name, departments_id) values ('Maria', 3);

select * from departments;

select * from employers e left join departments d on e.departments_id = d.id;
select * from employers e right join departments d on e.departments_id = d.id;
select * from employers e full join departments d on e.departments_id = d.id;
select * from employers e cross join departments d;

select * from departments d left join employers e on e.departments_id = d.id where e.id is null;

select * from employers e left join departments d on e.departments_id = d.id;
select * from departments d right join employers e on e.departments_id = d.id;
select * from employers e right join departments d on e.departments_id = d.id;
select * from departments d left join employers e on e.departments_id = d.id;

create table teens(
	id serial primary key,
	name varchar(255),
	gender text
);

insert into teens (name, gender) values ('Mari', 'Female');
insert into teens (name, gender) values ('Alex', 'Male');
insert into teens (name, gender) values ('Lev', 'Male');
insert into teens (name, gender) values ('Eva', 'Female');
insert into teens (name, gender) values ('Tom', 'Male');
insert into teens (name, gender) values ('Polina', 'Female');

select t1.name as Male_name,
t2.name as Female_name
from teens t1
cross join teens t2
where t1.gender = 'Male' and t1.gender != t2.gender;