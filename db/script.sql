create table cars(
    id serial primary key,
    brand varchar(255),
    model varchar(255),
    power smallint
);
insert into cars(brand, model, power) values('Toyota', 'Prius', 98);
update cars set model='Land Cruiser', power=249;
delete from cars;
