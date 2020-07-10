--  docker run --name mysqldb -p 3306:3306 MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

-- create database
CREATE DATABASE recipe_dev;
CREATE DATABASE recipe_prod;

-- create database service accounts

CREATE USER 'recipe_dev_user'@'localhost' IDENTIFIED  BY 'password';
CREATE USER 'recipe_prod_user'@'localhost' IDENTIFIED BY 'password'
CREATE USER 'recipe_dev_user'@'%' IDENTIFIED BY 'password';
CREATE USER 'recipe_prod_user'@'%' IDENTIFIED BY 'password';

-- grant DML access for dev user

GRANT SELECT ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT INSERT ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT UPDATE ON recipe_dev.* to 'recipe_dev_user'@'localhost';
GRANT DELETE ON recipe_dev.* to 'recipe_dev_user'@'localhost';

GRANT SELECT ON recipe_dev.* to 'recipe_dev_user'@'%';
GRANT INSERT ON recipe_dev.* to 'recipe_dev_user'@'%';
GRANT UPDATE ON recipe_dev.* to 'recipe_dev_user'@'%';
GRANT DELETE ON recipe_dev.* to 'recipe_dev_user'@'%';


-- grant DML access for prod user

GRANT SELECT ON recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT INSERT ON recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT DELETE ON recipe_prod.* to 'recipe_prod_user'@'localhost';
GRANT UPDATE ON recipe_prod.* to 'recipe_prod_user'@'localhost';


GRANT SELECT ON recipe_prod.* to 'recipe_prod_user'@'%';
GRANT INSERT ON recipe_prod.* to 'recipe_prod_user'@'%';
GRANT DELETE ON recipe_prod.* to 'recipe_prod_user'@'%';
GRANT UPDATE ON recipe_prod.* to 'recipe_prod_user'@'%';