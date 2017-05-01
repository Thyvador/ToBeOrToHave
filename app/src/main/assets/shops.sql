DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS employes;

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
  (3, 'R2 D2', 'Caisse enregistreuse', 0);