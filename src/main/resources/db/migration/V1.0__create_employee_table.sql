create table employee(
  id int auto_increment primary key,
  name varchar(10),
  age int,
  gender varchar(6),
  salary int,

--  companyId int foreign key references company(id)
);