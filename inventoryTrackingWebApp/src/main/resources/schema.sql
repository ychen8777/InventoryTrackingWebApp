CREATE TABLE IF NOT EXISTS INVENTORY(
  productId INT PRIMARY KEY auto_increment,
  productName VARCHAR(35),
  quantity INT
);

INSERT INTO INVENTORY (productName, quantity)
VALUES('bread', 25);

INSERT INTO INVENTORY (productName, quantity)
VALUES('beer', 16);

INSERT INTO INVENTORY (productName, quantity)
VALUES('frozen fried fries', 62);