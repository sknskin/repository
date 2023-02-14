create database pythondemo;

create user pythonuser@localhost identified by "pythonuser";
create user pythonuser@"%" identified by "pythonuser";

grant all privileges on pythondemo.* to pythonuser@localhost;
grant all privileges on pythondemo.* to pythonuser@"%";

use pythondemo;

create table person
(
	idx int primary key auto_increment,
	email varchar(100) not null,
    phone varchar(30) not null
);

select * from person;

create table todo
(
	idx int primary key auto_increment,
    title varchar(100) not null,
    content varchar(1000) not null,
    regdate datetime default now()
);