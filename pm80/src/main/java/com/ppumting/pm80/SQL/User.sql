CREATE TABLE Users (
	name VARCHAR(20) NOT NULL,
	ssn VARCHAR(7) NOT NULL,
	phone VARCHAR(13) NOT NULL,
	userId VARCHAR(16)  PRIMARY KEY,
	pw VARCHAR(20) NOT NULL,
	addr VARCHAR(25) NOT NULL
);

SELECT * FROM Users;

DROP TABLE Users;

INSERT INTO Users (name, ssn, phone, userId, pw, addr)
VALUES('이태영', '970312', '010-2881-2498', 'lty', '1234', 'daegu');