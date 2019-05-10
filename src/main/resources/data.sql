INSERT INTO role(role) VALUES 'PATIENT', 'DOCTOR', 'ADMIN';

INSERT INTO SPECIALIZATION(SPECIALIZATION) VALUES 'Surgeon', 'Pediatrician', 'Laryngologist';

INSERT INTO USER (FIRST_NAME, LAST_NAME, PESEL, PHONE_NUMBER, USERNAME, PASSWORD, ROLE_ID_ROLE) VALUES
  ('Adam', 'Adamowski', '123456789', '2983498234', 'adam', '123', 1),
  ('Michal', 'Jaroszewski', '235478907', '4889012341', 'drmichal', 'ewq', 2),
  ('Damian', 'Olszak', '987654321', '483872639', 'damian', '123456', 1),
  ('Tomasz', 'Legowski', '123890831', '481238901', 'tlegowski', 'tle12', 1),
  ('Pawel', 'Purzycki', '561354134', '4890128309', 'ppurzycki', 'ppa123', 1);

INSERT INTO ADDRESS(STREET, HOUSE_NUMBER, APARTAMENT_NUMBER, CITY, ZIP_CODE, STATE) VALUES
  ('Daleka', 1, NULL, 'Adamowo', '02-434', 'dalekopomorskie'),
  ('Tuwima', 15, NULL, 'Przasnysz', '06-300', 'mazowieckie'),
  ('Zlota', 3, 12, 'Olsztyn', '20-224', 'warminskomazurskie'),
  ('Al. Grunwaldzka', 2137, 420, 'Gdansk', '02-434', 'pomorskie'),
  ('Cicha', 22, NULL, 'Przasnysz', '06-300', 'mazowieckie');

INSERT INTO USER_ADDRESSES(ID_USER, ID_ADDRESS) VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5);
INSERT INTO USER_SPECIALIZATIONS(ID_USER, ID_SPECIALIZATION) VALUES (2, 1), (2, 2);

INSERT INTO RAPORT(DESCRIPTION, DATE_RAPORT) VALUES ('wizyta kontrolna', '2002-09-22'), ('zdiagnozowano przeziebienie', '2004-06-11');

INSERT INTO USER_RAPORTS(ID_USER, ID_RAPORT) VALUES (1, 1), (1, 2);

INSERT INTO VISIT(VISIT_DATE, VISIT_TIME, DOCTOR_ID_USER, PATIENT_ID_USER) VALUES('2019-09-02', '11:00', 1, 2)