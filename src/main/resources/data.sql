--DROP TABLE IF EXISTS employee;
--DROP TABLE IF EXISTS company;
--
--CREATE TABLE employee
--(
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    name varchar(100),
--    age INT,
--    gender varchar(10),
--    company_id INT
--);

INSERT INTO employee(`name`, age, gender,salary) VALUES
('AAAA','11','male',600),
('BBBB','11','male',300),
('CCCC','11','female',600),
('DDDD','11','male',600),
('EEEE','11','female',9000),
('FFFF','11','female',999),
('GGGG','11','female',1111),
('HHHH','11','female',3333);

--CREATE TABLE company
--(
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    name varchar(100),
--    employeesNumber INT
--);

INSERT INTO company(`company_name`,employees_number) VALUES
('AAAA',600),
('BBBB',300),
('CCCC',600),
('DDDD',600);