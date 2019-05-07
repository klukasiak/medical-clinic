INSERT INTO ROLE(ROLE) VALUES 'PATIENT', 'DOCTOR', 'ADMIN';
INSERT INTO SPECIALIZATION(SPECIALIZATION) VALUES 'Surgeon', 'Pediatrician', 'Laryngologist';
INSERT INTO USER (FIRST_NAME, LAST_NAME, PASSWORD, PESEL, PHONE_NUMBER, USERNAME, ROLE_ID) VALUES
  ('Adam', 'Adamowski', '123', '123456789', '2983498234', 'adam', 1),
  ('Michal', 'Jaroszewski', 'ewq', '235478907', '4889012341', 'drmichal', 2),
  ('Damian', 'Olszak', '123456', '987654321', '483872639', 'damian', 1),
  ('Tomasz', 'Legowski', 'tle12', '123890831', '481238901', 'tlegowski', 1),
  ('Pawel', 'Purzycki', 'ppa123', '561354134', '4890128309', 'ppurzycki', 1);
INSERT INTO USER_SPECIALIZATIONS(USER_ID_USER, SPECIALIZATIONS_ID) VALUES (2,1), (2,2);