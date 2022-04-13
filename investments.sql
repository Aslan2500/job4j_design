create table investments(
	id serial primary key,
	name varchar(255),
	amountOfMoney integer,
	isProfitable boolean
);

insert into investments(name, amountOfMoney, isProfitable) values('Kroger', 1000, true);

update investments set name = 'Apple';

delete from investments;
