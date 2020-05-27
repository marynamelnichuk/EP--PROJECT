
USE placement_of_goods;

INSERT INTO users(name, surname, email, password)
VALUES('Maryna', 'Melnychuk', 'marynamelnichuk859@gmail.com', 'mary67123'),
('Olena', 'Fedorchuk', 'ofedorchuk@gmail.com', 'olena67123');

INSERT INTO shops(name, country, city, owner)
VALUES('ATB', 'USA', 'Ostin', 1),
('Metro', 'Ulraine', 'Lviv', 2);

INSERT INTO categories(name) VALUES('dairy products'), ('baked goods'), ('fruits'),
('vegetables'), ('frozen foods'), ('alcohol'), ('meat'), ('fish'), ('cereals'), ('confectionery');

INSERT INTO shops_categories(shop_id, category_id) VALUES(1,1), (1,2),
(1,3), (1,4), (1,5), (1,6), (1,7), (2,1), (2,2),
(2,3), (2,4), (2,5), (2,8), (2,9);

INSERT INTO goods(name, category_id) VALUES('apples', 3), ('cucumber', 4),
('bread', 2), ('milk', 1), ('yogurt',1), ('kefir',1), ('cheese', 1), ('vodka', 6),
('pork',7), ('beef',7), ('dumplings',5), ('salmon',8), ('rise',9), ('buckwheat',9),
('candy roshen',10), ('cookies',10),('oranges',3), ('potato', 4), ('pancakes',5),
('beer',6), ('herring',8), ('buns',2), ('bulgur', 9), ('bananas', 3), ('tomatoes', 4);

INSERT INTO customers(name, surname, date) VALUES('Irina', 'Rishko', '2000-09-07'),
('Elizaveta', 'Koziurman', '2002-10-12'), ('Vasilina','Skorohoda','1999-01-02'),
('Eduard', 'Yarsyk', '1989-11-09'), ('Oleksandr', 'Kondrat', '1996-05-01');

INSERT INTO locations(name, distance, shop_id, side) VALUES('posithion_1', 1.5, 1, 'left'),
('posithion_2', 2.0, 1, 'right'), ('posithion_3', 2.5, 1, 'center'), ('posithion_4', 1.7, 1, 'left'),
('posithion_5', 2.0, 1, 'right'), ('posithion_6', 1.4, 2, 'center'), ('posithion_7', 1.5, 2, 'left'),
('posithion_8', 2.5, 2, 'left'), ('posithion_9', 2.0, 2, 'right'), ('posithion_10', 1.0, 2, 'right'),
('posithion_11', 1.5, 1, 'right'), ('posithion_12', 2.5, 2, 'center');

INSERT INTO categories_locations(global_location_id, category_id, location_id, date) VALUES(1, 1, 1, '2019-07-12'),
(1, 2, 2, '2019-07-12'),(1, 3, 3, '2019-07-12'),(1, 4, 4, '2019-07-12'),(1, 5, 5, '2019-07-12'),
(1, 6, 6, '2019-07-12'),(2, 7, 7, '2020-01-23'),(2, 8, 8, '2020-01-23'),(2, 9, 9, '2020-01-23'),
(2, 10, 10, '2020-01-23'),(2, 11, 11, '2020-01-23'),(2,12,12, '2020-01-23'), (2,1,1, '2020-01-23'), (2,2,2, '2020-01-23');

INSERT INTO purchase(purchase_id, shop_id, customer_id, good_id, location_id) VALUES(1, 1, 1, 1, 1),
(2, 1, 2, 9, 1), (3, 2, 3, 11, 2), (4, 2, 4, 10, 2), (5, 2, 1, 2, 1), (6, 1, 3, 6, 1), (4, 2, 4, 5, 2),
(5, 2, 1, 7, 2), (1, 1, 1, 8, 1), (6, 1, 3, 9, 1), (7, 2, 2, 3, 2), (3, 2, 3, 8, 2), (5, 2, 1, 5, 2),
(6, 1, 3, 1, 1), (8, 1, 5, 3, 1), (9, 2, 5, 4, 2), (9, 2, 5, 12, 2), (10, 1, 3, 13, 1), (9, 2, 5, 14, 2);
