INSERT INTO ROLE (ROLE_ID, TYPE) VALUES (10, 'Student');
INSERT INTO ROLE (ROLE_ID, TYPE) VALUES (20, 'Teacher');
INSERT INTO ROLE (ROLE_ID, TYPE) VALUES (30, 'Admin');

INSERT INTO PERSON (PERSON_ID, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, ROLE_ROLE_ID) VALUES (1, 'admin@admin.com', 'admin', 'admin', 'adminadmin', 30);

COMMIT;
