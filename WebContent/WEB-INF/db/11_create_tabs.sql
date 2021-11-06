DROP TABLE IF EXISTS ordered_detail CASCADE;
DROP TABLE IF EXISTS ordered CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS category CASCADE;

CREATE TABLE item (
	code SERIAL PRIMARY kEY,
	category_code INTEGER,
	name TEXT,
	price INTEGER
);

CREATE TABLE category (
	code SERIAL PRIMARY kEY,
	name TEXT
);

CREATE TABLE customer (
	code SERIAL PRIMARY kEY,
	name TEXT,
	address TEXT,
	tel TEXT,
	email TEXT
);

CREATE TABLE ordered (
	code SERIAL PRIMARY kEY,
	customer_code INTEGER,
	ordered_date DATE,
	total_price INTEGER
);

CREATE TABLE ordered_detail (
	ordered_code INTEGER,
	item_code INTEGER,
	num INTEGER
);

INSERT INTO item(category_code, name, price) VALUES (1, 'Java基礎', 2500);
INSERT INTO item(category_code, name, price) VALUES (1, 'MLB Fun', 980);
INSERT INTO item(category_code, name, price) VALUES (1, '料理BOOK！', 1200);

INSERT INTO item(category_code, name, price) VALUES (2, 'なつかしのアニメシリーズ', 2000);
INSERT INTO item(category_code, name, price) VALUES (2, 'The Racer', 1000);
INSERT INTO item(category_code, name, price) VALUES (2, 'Space Wars 3', 1000);

INSERT INTO item(category_code, name, price) VALUES (3, 'パズルゲーム', 780);
INSERT INTO item(category_code, name, price) VALUES (3, 'Invader Fighter', 3400);
INSERT INTO item(category_code, name, price) VALUES (3, 'Play the BasketBall', 2200);
