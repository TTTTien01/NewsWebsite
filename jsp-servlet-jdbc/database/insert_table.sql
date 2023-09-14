use jspservletjdbc;

insert into role(code,name) values('ADMIN','ADMIN');
insert into role(code,name) values('USER','USER');

insert into user(username,password,fullname,status, roleid) values('admin','123456','admin',1,1);
insert into user(username,password,fullname,status, roleid) values('nguyenvana','123456','nguyen van a',1,2);
insert into user(username,password,fullname,status, roleid) values('nguyenvanb','123456','nguyen van b',1,2);

insert into category(name, code) values('Thể thao', 'the-thao');
insert into category(name, code) values('Thế giới', 'the-gioi');
insert into category(name, code) values('Chính trị', 'chinh-tri');
insert into category(name, code) values('Thời sự', 'thoi-su');
insert into category(name, code) values('Góc nhìn', 'goc-nhin');
