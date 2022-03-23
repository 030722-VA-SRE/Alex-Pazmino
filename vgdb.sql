drop table if exists users;
create table if not exists users(
	id serial primary key,
	username varchar(30) unique not null,
	password varchar(30) not null
);

drop table if exists platforms;
create table if not exists platforms (
	pid integer primary key check (pid <= 4 and pid >= 1),
	platform varchar(20) not null,
	has_exclusives boolean,
	msrp numeric check (msrp > 0.00)
);


insert into platforms (pid, platform, msrp) values 
							(1, 'playstation-5', 499.99),
							(2, 'xbox-series-x', 499.99),
							(3, 'nintendo-switch', 299.99),
							(4, 'steam-deck', 649.99);
						

drop table if exists games;
create table if not exists games (
	gid serial primary key,
	game varchar(30) not null,
	pid integer references platforms(pid) not null,
	console_exclusive boolean, 
	msrp numeric check (msrp > 0.00)
);

insert into games (game, pid, console_exclusive, msrp) values 
								('ghost-of-tsushima', 1, true, 69.99),
				  				('super-mario-party', 3, true, 59.99),
				  				('the-last-spell', 4, false, 19.99),
								('halo-infinite', 2, false, 59.99),
								('elden-ring', 4, false, 59.99),
								('overwatch', 1, false, 39.99),
								('dark-souls-3', 4, false, 59.99),
								('zelda-breath-of-the-wild', 3, true, 59.99),
								('devil-may-cry-5', 2, false, 59.99),
								('elden-ring', 1, false, 59.99),
								('paper-mario-the-origami-king', 3, true, 59.99),
								('rare-replay', 2, true, 29.99),
								('god-of-war', 1, false, 59.99),
								('animal-crossing-new-horizons', 3, true, 59.99),
								('uncharted', 1, false, 49.99);

update games set
game = ?, pid = ?, console_exclusive = ?, msrp = ?
where gid = ?;

-- join the platform name into the games for a clean table
select platform from platforms where pid = 2;
select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid
insert into games (game, platform, console_exclusive, msrp) values 
													('god-of-war-2', 'playstation-5', true, 59.99);

select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid;
where games.pid = 4;

select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid
where platform = 'nintendo-switch';

select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid
where gid = ?;

select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid
where game = ?;

select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid
where console_exclusive = true;

select games.gid, games.game, platforms.platform, games.console_exclusive, games.msrp
from games
inner join
platforms on games.pid = platforms.pid
where games.msrp = ?
order by gid;

--create table if not exists game_owners (
--	user integer references users(id),
--	game integer references games(id)
--);

create table if not exists shop (
	seller integer references users(id),
	game integer references games(id),
	price numeric check (price > 0)
);

insert into users(username, password) values
							('Alex', 'alex1'),
							('Katee', 'Katee2'),
							('Brian', 'Brian3');



