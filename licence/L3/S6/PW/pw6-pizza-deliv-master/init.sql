/* Suppression des tables */
DROP TABLE ENTREE CASCADE;
DROP TABLE SAUCE CASCADE;
DROP TABLE RECETTE CASCADE;
DROP TABLE BOISSON CASCADE;
DROP TABLE INGREDIENT CASCADE;
DROP TABLE DESSERT CASCADE;
DROP TABLE ACCOUNT CASCADE;
DROP TABLE CART CASCADE;
DROP TABLE "SESSION" CASCADE;

/* Sessions things */
CREATE TABLE "SESSION" (
  "sid" varchar NOT NULL COLLATE "default",
  "sess" json NOT NULL,
  "expire" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE);

ALTER TABLE "SESSION" ADD CONSTRAINT "session_pkey" PRIMARY KEY ("sid") NOT DEFERRABLE INITIALLY IMMEDIATE;

CREATE INDEX "IDX_session_expire" ON "SESSION" ("expire");


/* Création des tables */
CREATE TABLE ENTREE(
	name varchar(20) PRIMARY KEY,
	price float not null
);

CREATE TABLE SAUCE(
	name varchar(20) PRIMARY KEY,
	price float not null
);

CREATE TABLE INGREDIENT(
	name varchar(20) PRIMARY KEY,
	available bool
);

CREATE TABLE RECETTE(
	name varchar(20) PRIMARY KEY,
	ingredient1 varchar(20) not null,
	ingredient2 varchar(20) not null,
	ingredient3 varchar(20) not null,
	ingredient4 varchar(20),
	ingredient5 varchar(20),
	ingredient6 varchar(20),
	price float not null,
	FOREIGN KEY(ingredient1) REFERENCES Ingredient(name),
	FOREIGN KEY(ingredient2) REFERENCES Ingredient(name),
	FOREIGN KEY(ingredient3) REFERENCES Ingredient(name),
	FOREIGN KEY(ingredient4) REFERENCES Ingredient(name),
	FOREIGN KEY(ingredient5) REFERENCES Ingredient(name),
	FOREIGN KEY(ingredient6) REFERENCES Ingredient(name)
);

CREATE TABLE BOISSON(
	name varchar(20) PRIMARY KEY,
	price float not null
);

CREATE TABLE DESSERT(
	name varchar(20) PRIMARY KEY,
	price float not null
);

CREATE TABLE ACCOUNT(
    user_name varchar(20) PRIMARY KEY,
    user_password varchar(200) NOT NULL,
    user_email varchar(20) NOT NULL,
    user_role varchar(10) NOT NULL
);

CREATE TABLE CART(
    id SERIAL PRIMARY KEY,
	order_id uuid NOT NULL,
    order_type varchar(20) NOT NULL,
	description text,
	price INTEGER NOT NULL,
    user_name varchar(20) NOT NULL,
    order_date timestamp
);

/* Population des tables bouffe */

INSERT INTO ENTREE VALUES ('Mozza Sticks',4), ('Salade de tomate',6),('Wings',3),('Nuggets',5);
INSERT INTO SAUCE VALUES ('Ketchup',0.5),('Mayonnaise',0.5),('Barbecue',0.5);
INSERT INTO INGREDIENT VALUES ('Sauce Tomate',true),('Crème fraiche',true),('Fromage à Raclette',true),('Mozzarella',true),('Basilic',true),('Olives',true),('Câpres',true),('Gruyère',true),('Bleu',true),('Chèvre',true),('Miel',true),('Oignons',true),('Lardons',true),('Viande hachée',true),('Parmesan',true),('Champignons',true),('Jambon',true),('Merguez',true);
INSERT INTO BOISSON VALUES ('Coca-Cola',2.5),('Lipton Ice-Tea',2.5),('Orangina',2.5),('Fanta',2.5),('Dr Pepper',1.5),('Heineken',3),('Evian',2),('San Pellegrino',2.5);
INSERT INTO RECETTE VALUES 
('Margarita','Sauce Tomate','Mozzarella','Basilic',null,null,null,10.5), 
('Reine','Sauce Tomate','Jambon','Champignons','Olives',null,null,10.5), 
('4 fromages','Sauce Tomate','Mozzarella','Chèvre','Parmesan','Bleu',null,12),
('Carnivore','Sauce Tomate','Gruyère','Viande hachée','Merguez','Oignons',null,15),
('Montagnarde','Crème fraiche','Jambon','Champignons','Fromage à Raclette',null,null,14);
INSERT INTO DESSERT VALUES ('Gateau au chocolat',4.5),('Tiramisu',6),('Glace',5),('Cookies',2.5);


/* Pupulation de la table user */

INSERT INTO ACCOUNT VALUES 
('admin', '$2a$10$IAcp6Buf8zzFUoPnrx55d.oR0S3PdRvIaoOUsMDg247RClPb2lgEC', 'admin@site.com', 'admin');
INSERT INTO ACCOUNT VALUES 
('delivery','$2a$10$FalUzYItrzMtUTtjIXJizOJPxQTIrgqehs3/vspokh4cIPcvuq/7q', 'delivery@site.com', 'delivery');