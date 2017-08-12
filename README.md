WEB application "PhoneBook"

Created for Lardi Ltd

Pylypchenko Vadym
16.08.2017

==========================================================================


--  SQl query for create DB and tables --

DROP DATABASE IF EXISTS phonebookdb;
CREATE DATABASE IF NOT EXISTS phonebookdb
DEFAULT CHARACTER SET utf8;
USE phonebookdb;


CREATE TABLE users
(
  id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) DEFAULT '' NOT NULL,
  password VARCHAR(255) DEFAULT '' NOT NULL,
  full_name VARCHAR(100) DEFAULT '' NOT NULL,
  role ENUM('ADMIN', 'USER') DEFAULT 'USER' NOT NULL
);


CREATE TABLE contacts
(
  id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
  last_name VARCHAR(100) DEFAULT '' NOT NULL,
  first_name VARCHAR(100) DEFAULT '' NOT NULL,
  middle_name VARCHAR(100) DEFAULT '' NOT NULL,
  mobile_phone VARCHAR(100) DEFAULT '' NOT NULL,
  home_phone VARCHAR(100) DEFAULT '',
  address VARCHAR(100) DEFAULT '',
  e_mail VARCHAR(100) DEFAULT '',
  user_id INT(10) unsigned,
  CONSTRAINT contacts_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);
