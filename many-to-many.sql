create table games(
	id serial primary key,
	name varchar(255)
);

create table players(
	id serial primary key,
	name varchar(255)
);

create table players_games(
	id serial primary key,
	players_id int references players(id),
	games_id int references games(id)
);

insert into players(name) values ('Ivan');
insert into players(name) values ('Artem');
insert into players(name) values ('Misha');

insert into games(name) values ('Apex');
insert into games(name) values ('Dota');
insert into games(name) values ('Witcher');

insert into players_games (players_id, games_id) values (1, 1);
insert into players_games (players_id, games_id) values (1, 2);
insert into players_games (players_id, games_id) values (1, 3);
insert into players_games (players_id, games_id) values (2, 2);
insert into players_games (players_id, games_id) values (3, 3);