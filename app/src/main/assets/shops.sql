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
  (0, 'ToBeOrToHave - Nice', '13 Boulevard Jean Médecin, Nice', 250000, 135000),
  (1, 'ToBeOrToHave - Champs Elysées', 'Champs Elysées, Paris', 300000, 11500),
  (2, 'ToBeOrToHave - Bollène', 'Rue de la paix, Bollène', 160000, 148500),
  (3, 'ToBeOrToHave - Biot, Saint Phillipe', '200 AVENUE ROUMANILLE, ESPACE SAINT PHILIPPE, Biot', 240000, 162500),
  (4, 'ToBeOrToHave - Nîmes', '10 Avenus Kennedy, Nîmes', 120000, 146500);

INSERT INTO employes(id, name, role, shopId) VALUES
  (0, 'Patrcik Sébastien', 'Manager', 0),
  (1, 'Jackie Michel', 'Responsable marketing', 0),
  (2, 'Luke Skywalker', 'Responsable rayon', 0),
  (3, 'R2 D2', 'Caisse enregistreuse', 0),
  (4, 'Jean-Michel Test', 'Manager', 1),
  (5, 'Lando Calrissian', 'Responsable rayon', 2),
  (6, 'C3 PO', 'Responsable communication', 3),
  (7, 'The Doctor', 'Responsable tech', 4),
  (8, 'Chewbacca', 'Responsable rayon', 4),
  (9, 'Leia Organa', 'Responsable rayon femmes', 1),
  (10, 'Yoda', 'Manager', 1),
  (11, 'Mace Windu', 'Manager', 2),
  (12, 'Obi wan Kennodi', 'Responsable publicité', 2),
  (13, 'Ahsoka Tano', 'Manager', 3);

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
  (13, 'Le Coran', 323135, 0),
  (14, 'Le petit livre vert', 45345, 0),
  (15, 'Le petit livre rouge', 323135, 1),
  (16, 'La Bible', 45345, 1),
  (17, 'Le petit livre violet', 323135, 2),
  (18, 'La Torah', 45345, 2),
  (19, 'Mein Kampf', 323135, 3),
  (20, 'Le petit livre bleu', 45345, 3);