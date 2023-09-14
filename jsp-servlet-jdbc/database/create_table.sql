use jspservletjdbc;

CREATE TABLE role(
  id bigint NOT NULL PRIMARY KEY auto_increment,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(255) NOT NULL,
  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

CREATE TABLE contact(
	id bigint NOT NULL PRIMARY KEY auto_increment,
	fullname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	phonenumber VARCHAR(15) NULL,
	title VARCHAR(255) NULL,
	content VARCHAR(255) NULL,
	createddate TIMESTAMP NULL,
	modifieddate TIMESTAMP NULL,
	createdby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

CREATE TABLE user (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  username VARCHAR(150) NOT NULL,
  password VARCHAR(150) NOT NULL,
  fullname VARCHAR(150) NULL,
  status int NOT NULL,
  roleid bigint NOT NULL,
  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES role(id) ON DELETE CASCADE;
-- thêm unique cho username trong bảng user
ALTER TABLE user ADD CONSTRAINT username_is_unique UNIQUE(username);

CREATE TABLE news (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  title VARCHAR(255) NULL,
  thumbnail VARCHAR(255) NULL,
  shortdescription TEXT NULL,
  content TEXT NULL,
  categoryid bigint NOT NULL,
  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

CREATE TABLE category (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  name VARCHAR(255) NOT NULL,
  code VARCHAR(255) NOT NULL,
  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryid) REFERENCES category(id) ON DELETE CASCADE;

CREATE TABLE comment (
  id bigint NOT NULL PRIMARY KEY auto_increment,
  content TEXT NOT NULL,
  user_id bigint NOT NULL,
  new_id bigint NOT NULL,
  invalid int NOT NULL,
  createddate TIMESTAMP NULL,
  modifieddate TIMESTAMP NULL,
  createdby VARCHAR(255) NULL,
  modifiedby VARCHAR(255) NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE comment ADD CONSTRAINT fk_comment_news FOREIGN KEY (new_id) REFERENCES news(id) ON DELETE CASCADE;


