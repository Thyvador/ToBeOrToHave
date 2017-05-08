DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS employes;
DROP TABLE IF EXISTS sales;

CREATE TABLE shops (
  id INT PRIMARY KEY ,
  name TEXT,
  adress TEXT,
  benefits INT,
  cost INT);

CREATE TABLE employes(
  id INT PRIMARY KEY,
  name TEXT,
  role TEXT,
  shopID INT);

CREATE TABLE sales(
  id INT PRIMARY KEY,
  sale TEXT,
  number INT,
  shopID INT);

INSERT INTO shops (id, name, adress, benefits, cost) VALUES
  (0, 'To Be Or To Have - Nice', 'Nice', 250000, 135000),
  (1, 'To Be Or To Have - Paris', 'Paris', 300000, 11500),
  (2, 'To Be Or To Have - Bollène', 'Bollène', 160000, 148500),
  (3, 'To Be Or To Have - Biot', 'Biot', 240000, 162500),
  (4, 'To Be Or To Have - Nîmes', 'Nîmes', 120000, 146500);

INSERT INTO employes(id, name, role, shopId) VALUES
  (0, 'Sébastien Patrick', 'Manager', 0),
  (1, 'Jackie Michel', 'Responsable marketing', 0),
  (2, 'Luke Skywalker', 'Responsable rayon', 0),
  (3, 'R2 D2', 'Caisse enregistreuse', 0),
  (4, 'Jean-Michel Test', 'Manager', 1),
  (5, 'Lando Calrissian', 'Responsable rayon', 2),
  (6, 'C3 PO', 'Responsable communication', 3),
  (7, 'The Doctor', 'Responsable tech', 4);

INSERT INTO sales(id, sale, number, shopID) VALUES
  (0, 'JUL - On m''appelle l''ovni', 150000, 0),
  (1, 'Les cinquantes nuances de Grey', 2033, 0),
  (2, 'JUL - On m''appelle l''ovni', 13254, 1),
  (3, 'Kaaris - Thcoin', 156423, 1),
  (4, 'Guardians of the galaxy', 416541, 2),
  (5, 'Guardians of the galaxy : Awesome Mix Vol 2', 2315, 2),
  (6, 'Umberto Eco - Reconnaitre le fascisme', 1654, 3),
  (7, 'Kaaris - Blow', 54621, 3),
  (8, 'Fast & Furious 7', 135438, 4),
  (9, 'JUL - On m''appelle l''ovni', 7564, 4),
  (10, 'Umberto Eco - Reconnaitre le fascisme', 123452, 4),
  (11, 'Guardians of the galaxy', 1234, 4),
  (12, 'Les cinquantes nuances de Grey', 135438, 4);