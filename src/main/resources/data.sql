INSERT INTO FITNESS_CENTAR (adresa, email, naziv, telefon) VALUES ('Neka Adresa 1', 'mejl@gmail.com', 'FitGym', '094-10321-321');

INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, uloga, status, tip_korisnika) VALUES ('user_clan', '123', 'Stefan', 'Stefanovic', '0391-213-12-2', 'stefan@gmail.com', 'Clan', true, 'clan');
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika) VALUES ('user_trener', '123', 'Aleksandar', 'Treneric', '0391-213-122-2', 'trenerSasa@gmail.com', '1992-12-12', 'Trener', true,  'trener');
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, birthday, uloga, status, tip_korisnika) VALUES ('user_trener_2', '1233223', 'Marko', 'Markovic', '0391-2213-122-2', 'trenerMarko@gmail.com', '1992-12-02', 'Trener', true,  'trener');
INSERT INTO KORISNIK (user_name, lozinka, first_name, last_name, telefon, email, uloga, status, tip_korisnika) VALUES ('user_clan1', '123', 'Milica', 'Milic', '0391-2103-12-2', 'milica@gmail.com', 'Clan', true, 'clan');

INSERT INTO TRENING (naziv, opis, tip, trajanje, trener_id) VALUES ('ZxWY', 'Palica', 'M', 1, 2);
INSERT INTO TRENING (naziv, opis, tip, trajanje, trener_id) VALUES ('Abcd', 'E', 'F', 2, 2);
INSERT INTO TRENING (naziv, opis, tip, trajanje, trener_id) VALUES ('HKL', 'Perica', 'M', 1, 3);

INSERT INTO SALA (kapacitet, oznaka, fitness_centar_id) VALUES (10, 'S1', 1);
INSERT INTO SALA (kapacitet, oznaka, fitness_centar_id) VALUES (5, 'S2', 1);
INSERT INTO SALA (kapacitet, oznaka, fitness_centar_id) VALUES (20, 'S3', 1);

INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (1500, '2021-07-22 09:00:00', 1, 1);
INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (1000, '2021-07-05 15:00', 2, 2);
INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (500, '2021-07-07 13:30', 1, 3);
INSERT INTO TERMIN (cena, datum, sala_id, trening_id) VALUES (2000, '2021-07-10 14:20', 3, 1);

INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (1, 2);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (1, 1);
INSERT INTO PRIJAVA_TERMINA (clan_id, termin_id) VALUES (4, 4);

INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (5.00, 1, 1);
INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (3.00, 4, 2);
INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (5.00, 4, 1);
INSERT INTO ODRADJEN_TERMIN (ocena, clan_id, trening_id) VALUES (5.00, 1, 1);
INSERT INTO ODRADJEN_TERMIN (clan_id, trening_id) VALUES (1, 2);

