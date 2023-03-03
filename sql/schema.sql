create database acme_bank;

use acme_bank;

CREATE TABLE `acme_bank`.`accounts` (
  `account_id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `balance` FLOAT NOT NULL,
  PRIMARY KEY (`account_id`));
  
  
INSERT INTO `acme_bank`.`accounts` (`account_id`, `name`, `balance`) VALUES 
('V9L3Jd1BBI', 'fred', 100.00),
('fhRq46Y6vB', 'barney', 300.00),
('uFSFRqUpJy', 'wilma', 1000.00),
('ckTV56axff', 'betty', 1000.00),
('Qgcnwbshbh', 'pebbles', 50.00),
('if9l185l18', 'bambam', 50.00);
