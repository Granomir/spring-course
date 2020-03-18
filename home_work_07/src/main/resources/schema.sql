CREATE TABLE BOOKS(ID INT PRIMARY KEY AUTO_INCREMENT, TITLE VARCHAR(255), AUTHOR INT, GENRE INT);
CREATE TABLE AUTHORS(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255));
CREATE TABLE GENRES(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255));
CREATE TABLE COMMENTS(ID INT PRIMARY KEY AUTO_INCREMENT, NICK_NAME VARCHAR(255), BODY VARCHAR(255), BOOK_ID VARCHAR(255));