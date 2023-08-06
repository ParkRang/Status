create table IF not exists log(
	id VARCHAR(25) not null,
	myPTZ VARCHAR(5) not null,
	enemyPTZ VARCHAR(5) not null,
	WinLose VARCHAR(10) not null,
	LP INTEGER,
	MAP VARCHAR(20) not null,
	YMDHMS VARCHAR(50) not null
) default charset=utf8;