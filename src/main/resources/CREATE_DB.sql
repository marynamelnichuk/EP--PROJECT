DROP DATABASE placement_of_goods;
CREATE DATABASE placement_of_goods;

USE placement_of_goods;

CREATE TABLE categories(
id int primary key auto_increment,
name varchar(100) unique
);

CREATE TABLE goods(
id int primary key auto_increment,
name varchar(100),
category_id int,
FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE customers(
id int primary key auto_increment,
name varchar(100),
surname varchar(100),
date date
);

CREATE TABLE users (
id int primary key auto_increment,
name varchar(100),
surname varchar(100),
email varchar(100),
password varchar(20)
);

CREATE TABLE shops(
id int primary key auto_increment,
name varchar(100),
country varchar(100),
city varchar(100),
owner int,
FOREIGN KEY (owner) REFERENCES users(id)
);

CREATE TABLE shops_categories(
id int primary key auto_increment,
shop_id int,
category_id int,
FOREIGN KEY (shop_id) REFERENCES shops(id),
FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE locations(
id int primary key auto_increment,
name varchar(100),
distance float,
shop_id int,
side varchar(100),
FOREIGN KEY (shop_id) REFERENCES shops(id)
);

CREATE TABLE categories_locations(
id int key auto_increment,
global_location_id int,
category_id int,
location_id int,
date date,
FOREIGN KEY (category_id) REFERENCES shops_categories(id),
FOREIGN KEY (location_id) REFERENCES locations(id)
);

CREATE TABLE purchase (
id int primary key auto_increment,
purchase_id int,
shop_id int,
customer_id int,
good_id int,
location_id int,
date DATETIME DEFAULT  CURRENT_TIMESTAMP,
FOREIGN KEY (shop_id) REFERENCES shops(id),
FOREIGN KEY (good_id) REFERENCES goods(id),
FOREIGN KEY (customer_id) REFERENCES customers(id),
FOREIGN KEY (location_id) REFERENCES categories_locations(id)
);
