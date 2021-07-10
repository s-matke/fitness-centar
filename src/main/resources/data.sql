INSERT INTO FITNESS_CENTAR (adresa, email, naziv, telefon) VALUES ('Neka Adresa 1', 'mejl@gmail.com', 'FitGym', '094-10321-321');
INSERT INTO FITNESS_CENTAR (adresa, email, naziv, telefon) VALUES ('Neka Adresa 2', 'mejl2@gmail.com', 'KerGym', '094-1033321-321');
INSERT INTO FITNESS_CENTAR (adresa, email, naziv, telefon) VALUES ('Neka Adresa 3', 'mejl3@gmail.com', 'Seaeaeea', '09422-10321-321');

INSERT INTO SALA (kapacitet, oznaka, fitness_centar_id) VALUES (5, 'S1', 1);
INSERT INTO SALA (kapacitet, oznaka, fitness_centar_id) VALUES (5, 'S2', 1);
INSERT INTO SALA (kapacitet, oznaka, fitness_centar_id) VALUES (20, 'S3', 1);

INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika, fitness_centar_id) VALUES ('user_trener', '123', 'Aleksandar', 'Treneric', '0391-213-122-2', 'trenerSasa@gmail.com', '1992-12-12', 'Trener', true,  'trener', 1);
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika, fitness_centar_id) VALUES ('user_trener_2', '1233223', 'Marko', 'Markovic', '0391-2213-122-2', 'trenerMarko@gmail.com', '1992-12-02', 'Trener', true,  'trener', 1);
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika, fitness_centar_id) VALUES ('user_trener_44', '123', 'Milan', 'Milanovic', '03912313-2213-122-2', 'trenerMilan@gmail.com', '1992-12-02', 'Trener', true,  'trener', 2);
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika, fitness_centar_id) VALUES ('user_trener_25', '123', 'Pera', 'Peric', '0391-2213214214-122-2', 'trenerPera@gmail.com', '1992-12-02', 'Trener', true,  'trener', 3);
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika, fitness_centar_id) VALUES ('user_trener_21', '123', 'Andjela', 'Andjelic', '0391-2213-1224142-2', 'trenerAndjela@gmail.com', '1992-12-02', 'Trener', true,  'trener', 2);
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, uloga, status, tip_korisnika) VALUES ('user_clan', '123', 'Stefan', 'Stefanovic', '0391-213-12-2', 'stefan@gmail.com', 'Clan', true, 'clan');
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, uloga, status, tip_korisnika) VALUES ('user_clan1', '123', 'Milica', 'Milic', '0391-2103-12-2', 'milica@gmail.com', 'Clan', true, 'clan');
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, uloga, status, tip_korisnika) VALUES ('admin', '123', 'Admin', 'Adminovic', '039122-2103-12-2', 'admin@gmail.com', 'Admin', true, 'admin');

INSERT INTO TRENING (naziv, opis, tip, trajanje, trener_id) VALUES ('Aerobik', 'Efikasne vezbe tela', 'Svi misici', 1, 1);
INSERT INTO TRENING (naziv, opis, tip, trajanje, trener_id) VALUES ('Abs', 'Vezbe sa jacanje stomacnih misica', 'Stomak/Noge', 2, 2);
INSERT INTO TRENING (naziv, opis, tip, trajanje, trener_id) VALUES ('Snaga', 'Vezbe snage', 'Ramena/Ruke/Ledja', 2, 3);


-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (1500, '2021-07-22 09:00:00', 1, 1);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (1000, '2021-07-09 15:00', 2, 2);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (500, '2021-07-09 13:30', 1, 3);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (2000, '2021-07-10 14:20', 3, 1);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (1000, '2021-07-09 15:00', 2, 2);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (500, '2021-07-09 13:30', 1, 3);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (2000, '2021-07-10 14:20', 3, 1);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (1000, '2021-07-09 15:00', 2, 2);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (500, '2021-07-09 13:30', 1, 3);
-- INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (2000, '2021-07-10 14:20', 3, 1);

INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (1, '2021-07-23 08:40:00', '2021-07-23 09:40:00', 1, 1);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (2, '2021-07-22 09:00:00', '2021-07-22 10:00:00', 1, 2); -- posmatrani termin
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (3, '2021-07-22 08:10:00', '2021-07-22 09:10:00', 1, 3);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (4, '2021-07-22 08:20:00', '2021-07-22 09:20:00', 1, 1);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (5, '2021-07-22 08:00:00', '2021-07-22 09:00:00', 1, 2);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (5, '2021-07-22 09:40:00', '2021-07-22 10:40:00', 1, 3);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (6, '2021-07-22 09:50:00', '2021-07-22 10:50:00', 1, 2);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (7, '2021-07-22 10:00:00', '2021-07-22 11:00:00', 1, 1);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (7, '2021-07-22 10:10:00', '2021-07-22 11:10:00', 1, 3);
INSERT INTO TERMIN (cena, datum, kraj, sala_id, trening_id) VALUES (8, '2021-07-22 10:20:00', '2021-07-22 11:20:00', 1, 1);




-- INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 2);
-- INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (7, 1);
-- INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 4);

INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 1); -- user stefan stefanovic
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 3);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 4);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 5);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 6);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 7);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 8);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 9);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (6, 10);




-- INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (5.00, 7, 1);
-- INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (3.00, 6, 2);
-- INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (5.00, 7, 1);
-- INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (5.00, 6, 3);
-- INSERT INTO ODRADJEN_TERMIN (clan_id, termin_id) VALUES (6, 2);

