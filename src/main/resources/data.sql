INSERT INTO role(role) VALUES 'PATIENT', 'DOCTOR', 'ADMIN';

INSERT INTO SPECIALIZATION(SPECIALIZATION) VALUES 'Surgeon', 'Pediatrician', 'Laryngologist';

INSERT INTO USER (FIRST_NAME, LAST_NAME, PESEL, PHONE_NUMBER, USERNAME, PASSWORD) VALUES
  ('Adam', 'Adamowski', '123456789', '2983498234', 'adam', '123'),
  ('Michal', 'Jaroszewski', '235478907', '4889012341', 'drmichal', 'ewq'),
  ('Damian', 'Olszak', '987654321', '483872639', 'damian', '123456'),
  ('Tomasz', 'Legowski', '123890831', '481238901', 'tlegowski', 'tle12'),
  ('Pawel', 'Purzycki', '561354134', '4890128309', 'ppurzycki', 'ppa123'),
  ('Teresa', 'Bulka', '112323237', '412333012341', 'drteresa', 'essq'),
  ('Admin', 'Administrator', '155523237', '46546412341', 'admin', 'admin');

INSERT INTO ADDRESS(STREET, HOUSE_NUMBER, APARTAMENT_NUMBER, CITY, ZIP_CODE, STATE) VALUES
  ('Daleka', 1, NULL, 'Adamowo', '02-434', 'dalekopomorskie'),
  ('Tuwima', 15, NULL, 'Przasnysz', '06-300', 'mazowieckie'),
  ('Zlota', 3, 12, 'Olsztyn', '20-224', 'warminskomazurskie'),
  ('Al. Grunwaldzka', 2137, 420, 'Gdansk', '02-434', 'pomorskie'),
  ('Cicha', 22, NULL, 'Przasnysz', '06-300', 'mazowieckie'),
  ('Bliska', 41, NULL, 'Dalekowo', '02-222', 'mazowieckie');

INSERT INTO USER_ADDRESSES(ID_USER, ID_ADDRESS) VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (1,6);
INSERT INTO USER_SPECIALIZATIONS(ID_USER, ID_SPECIALIZATION) VALUES (2, 1), (2, 2), (6,3), (6,1);
INSERT INTO USER_ROLES(ID_USER, ID_ROLE) VALUES (1, 1), (2, 2), (3, 1), (4, 1), (5, 1), (6, 2), (7, 3);

INSERT INTO RAPORT(DESCRIPTION, DATE_RAPORT) VALUES ('wizyta kontrolna', '2002-09-22'), ('zdiagnozowano przeziebienie', '2004-06-11');

INSERT INTO USER_RAPORTS(ID_USER, ID_RAPORT) VALUES (1, 1), (1, 2);

INSERT INTO VISIT(VISIT_DATE, VISIT_TIME, DOCTOR_ID_USER, PATIENT_ID_USER) VALUES
('2019-09-02', '11:00', 2, 1),
('2019-09-15', '10:30', 6, 1),
('2018-07-14', '9:00', 2, 1);