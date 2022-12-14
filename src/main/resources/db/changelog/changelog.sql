
CREATE TABLE IF NOT EXISTS readers
(
    id
            SERIAL
        PRIMARY
            KEY,
    name
            VARCHAR(250),
    surname VARCHAR(250),
    email   VARCHAR(200)
);
CREATE TABLE IF NOT EXISTS books
(
    id
              SERIAL,
    title
              VARCHAR(250),
    author    VARCHAR(250),
    available BOOLEAN,
    reader_id INTEGER,
    rent_date DATE NOT NULL DEFAULT CURRENT_DATE,
    times_of_borrowing INTEGER DEFAULT 1,
    PRIMARY KEY
        (
         id
            ),
    CONSTRAINT fk_reader FOREIGN KEY
        (
         reader_id
            ) REFERENCES readers
            (
             id
                )
);

CREATE OR REPLACE FUNCTION check_name() RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF REGEXP_MATCHES
        (NEW.name, '[1-9]') THEN
        RAISE EXCEPTION 'Readers name should have only letters.';
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER reader_check_name
    BEFORE INSERT
    ON readers
    FOR EACH ROW
EXECUTE PROCEDURE check_name();



INSERT INTO BOOKS (id, author, title, available)
VALUES (1, 'Nela ', '10 NIESAMOWITYCH PRZYGÓD NELI', true),
       (3, 'A. Griffiths', '39 PIĘTROWY DOMEK NA DRZEWIE', true),
       (4, 'M.Warda', '5 SEKUND DO IO. REBELIANTKA', true),
       (5, 'A. Griffiths', '65 PIĘTROWY DOMEK NA DRZEWIE', true),
       (6, 'A. Griffiths', '91 PIĘTROWY DOMEK NA DRZEWIE', true),
       (7, 'P.Beręsewicz', 'A NIECH TO CZYKOLADA', true),
       (8, 'K.Szymeczko', 'A TO HISTORIA. OPOWIADANIA Z DZIEJÓW POLSKI', true),
       (9, 'G.Bąkiewicz', 'A U NAS W DOMU', true),
       (10, 'Ł. Wierzbicki', 'AFRYKA KAZIKA', true),
       (11, 'B.Nawrocka', 'AIRAM OSTATNIA TAJEMNICA', true),
       (12, 'J. Sykes', 'AKADEMIA JEDNOROŻCÓW. ALEKSANDRA I ISKRA', true),
       (13, 'J. Sykes', 'AKADEMIA JEDNOROŻCÓW. ZOSIA I TĘCZAALADDIN AND THE LAMP ', true),
       (14, 'B.Nawrocka', 'ALHAR SYN ANHARA', true),
       (15, 'R.Kosik', 'AMELIA I KUBA ', true),
       (16, 'B.Nawrocka', 'ANHAR POWIEŚĆ ANTYMAGICZNA', true),
       (17, 'M.Widmark', 'ANTYKWARIA POD BŁĘKITNYM LUSTREM', true),
       (18, 'B.Nawrocka', 'ARIEL WNUK ALHARA', true),
       (19, 'M.Szczygielski', 'ARKA CZASU', true),
       (20, 'J.Papuzińska', 'ASIUNIAASTERIKS ', true),
       (21, 'J.Wachowiak', 'AWARIA', true),
       (22, 'J.Bednarek', 'BABCOCHABAJKI 5 MINUT PRZED SNEM ', true),
       (23, 'M.Białek', 'BAJKI DLA CHŁOPCÓW', true),
       (24, 'M.Białek', 'BAJKI DLA DZIEWCZYNEK', true),
       (25, 'B.Stańczuk', 'BAJKI POMAGAJKI NA DOBRY DZIEŃ I JESZCZE LEPSZE JUTRO', true),
       (26, 'B.Stańczuk', 'BAJKI TERAPEUTYCZNE NA DOBRY DZIEŃ I JESZCZE LEPSZE JUTRO', true),
       (27, 'B.Stańczuk', 'BAJKI UŚMIECHAJKI', true),
       (28, 'J.Bednarek', 'BANDA CZARNEJ FROTTE', true),
       (29, 'A.Wheatley', 'BARDZO ILUSTROWANA HISTORIA ZIEMI', true),
       (30, 'Z.Stanecka', 'BASIA ', true),
       (31, 'J.Ch.Andersen', 'BAŚNIE ANDERSEN ', true),
       (32, 'N.Vanier', 'BELLA I SEBASTIAN', true),
       (33, 'Ch. Féret ', 'BELLA I SEBASTIAN2 ', true),
       (34, 'Ch. Féret ', 'BELLA I SEBASTIAN3', true),
       (35, 'D.Comrzyńska', 'BEZSENNOŚĆ JUTKI', true),
       (36, 'G.Kasdepke', 'BEZU', true),
       (37, 'J.Escott', 'BIG BAG MISTAKE ', true),
       (38, 'A.Sewell', 'BLACK BEAUTY ', true),
       (39, 'E.Nowak', 'BŁAHOSTKA I KAMYK ', true),
       (40, 'M.Zarębska', 'BORYS I ZAJĄCZKI', true),
       (41, 'J.Brzechwa', 'BRZECHWA DZIECIOM', true),
       (42, 'J.Brzechwa', 'BRZECHWA DZIECIOM, DZIEŁA WSZYSTKIE. TEATRZYKI', true),
       (43, 'B.Kosmowska', 'BUBA', true),
       (44, 'B.Kosmowska', 'BUBA I SEZON OGÓRKOWY', true),
       (45, 'J.Korczakowska', 'BUŁECZKA', true),
       (46, 'W.Bruce Cameron', 'BYŁ SOBIE PIES2', true),
       (47, 'W.Bruce Cameron', 'BYŁ SOBIE SZCZENIAK', true),
       (48, 'M.Melin', 'CHŁOPAK Z LASU', true),
       (49, 'R.Witek', 'CHŁOPIEC Z LAMPEDUSY', true),
       (50, 'O.Q.Raúf', 'CHŁOPIEC Z OSTATNIEJ ŁAWKI', true),
       (51, 'A.Shepherd', 'CHŁOPIEC, KTÓRY HODOWAŁ SMOKI', true),
       (52, 'Ł.Kaniewski', 'CHOLERA I INNE CHOROBY', true),
       (53, 'A.J.Epstein', 'CHOWAŃCE ', true),
       (54, 'E.Piotrowska', 'CIOCIA JADZIA ', true),
       (55, 'L.Erocak', 'CLOTHES AT WORK', true),
       (56, 'W.Cichoń', 'CO SŁYCHAĆ, CUKIERKU?', true),
       (57, 'I.Michto', 'CO WARTO WIEDZIEĆ O PRZYRODZIE', true),
       (58, 'W.Błach', 'CO WARTO WIEDZIEĆ O ZWIERZĘTACH', true),
       (59, 'D.Terakowska', 'CÓRKA CZAROWNIC', true),
       (60, 'J.Duszyńska', 'CUDACZEKWYŚMIEWACZEK', true),
       (61, 'R.Piątkowska', 'CUKIERKI', true),
       (62, 'M.Kulus', 'CYKOR', true),
       (63, 'D.Bach', 'CYNAMON CHŁOPAK I JA', true),
       (64, 'D.Bach', 'CYNAMON KŁOPOTY I JA', true),
       (65, 'J.Jagiełło', 'CZACHA SIĘ BUNTUJE', true),
       (66, 'D.Suwalska', 'CZARNE JEZIORA', true),
       (67, 'U.K. Le Guin', 'CZARNOKSIĘŻNIK Z ARCHIPELAGU', true),
       (68, 'M.Szczygielski', 'CZARNY MŁYN', true),
       (69, 'M.Szczygielski', 'CZAROWNICA PIĘTRO NIŻEJ', true),
       (70, 'K.Giery', 'CZERWIEŃ RUBINU', true),
       (71, 'J.Podsiadło', 'CZERWONA KARTKA DLA SPRĘŻYNY', true),
       (72, 'Opracowanie zbiorowe', 'CZYTAM SOBIE ', true),
       (73, 'K.Rundell', 'DACHOŁAZY', true),
       (74, 'W.Cichoń', 'DASZ RADĘ, CUKIERKU ', true),
       (75, 'M.Pałasz', 'DASZ RADĘ, MARCELKO ', true),
       (76, 'B.Wicher', 'DETEKTYW ŁODYGA', true),
       (77, 'Opracowanie zbiorowe', 'DETEKTYW ŁODYGA II', true),
       (78, 'A.T.Smith', 'DETEKTYW PINGWIN I FORTECA TAJEMNIC', true),
       (79, 'A.T.Smith', 'DETEKTYW PINGWIN I SPRAWA ZAGINIONEGO SKARBU', true),
       (80, 'G.Kasdepke', 'DETEKTYW POZYTYWKA', true),
       (81, 'Z.Orlińska', 'DETEKTYWI Z KLASZTORNEGO WZGÓRZA', true),
       (82, 'P.Wakuła', 'DĘBOWA KOŁYSKA ', true),
       (83, 'A.Franc', 'DIARY OF A YOUNG GIRL ', true),
       (84, 'G.Strzeboński', 'DOBRE MANIERY DLA DZIECI', true),
       (85, 'H.Lofting', 'DOKTOR DOLITTLE I JEGO ZWIERZĘTA', true);



INSERT INTO readers(id, surname, name)
VALUES (1, 'SMITH ', 'Olivia'),
       (2, 'JOHNSON ', 'Emma'),
       (3, 'WILLIAMS ', 'Amelia'),
       (4, 'BROWN ', 'Ava'),
       (5, 'JONES ', 'Sophia'),
       (6, 'GARCIA ', 'Isabella'),
       (7, 'MILLER ', 'Luna'),
       (8, 'DAVIS ', 'Mia'),
       (9, 'RODRIGUEZ ', 'Charlotte'),
       (10, 'MARTINEZ ', 'Evelyn'),
       (11, 'HERNANDEZ ', 'Harper'),
       (12, 'LOPEZ ', 'Scarlett'),
       (13, 'GONZALEZ ', 'Ella'),
       (14, 'WILSON ', 'Nova'),
       (15, 'ANDERSON ', 'Aurora'),
       (16, 'THOMAS ', 'Gianna'),
       (17, 'TAYLOR ', 'Aria'),
       (18, 'MOORE ', 'Mila'),
       (19, 'JACKSON ', 'Ellie'),
       (20, 'MARTIN ', 'Sofia'),
       (21, 'LEE ', 'Violet'),
       (22, 'PEREZ ', 'Willow'),
       (23, 'THOMPSON ', 'Layla'),
       (24, 'WHITE ', 'Camila'),
       (25, 'HARRIS ', 'Lily'),
       (26, 'SANCHEZ ', 'Hazel'),
       (27, 'CLARK ', 'Chloe'),
       (28, 'RAMIREZ ', 'Avery'),
       (29, 'LEWIS ', 'Elena'),
       (30, 'ROBINSON ', 'Penelope'),
       (31, 'WALKER ', 'Eliana'),
       (32, 'YOUNG ', 'Paisley'),
       (33, 'ALLEN ', 'Isla'),
       (34, 'KING ', 'Eleanor'),
       (35, 'WRIGHT ', 'Abigail'),
       (36, 'SCOTT ', 'Elizabeth'),
       (37, 'TORRES ', 'Ivy'),
       (38, 'NGUYEN ', 'Riley'),
       (39, 'HILL ', 'Nora'),
       (40, 'FLORES ', 'Grace'),
       (41, 'GREEN ', 'Emily'),
       (42, 'ADAMS ', 'Zoey'),
       (43, 'NELSON ', 'Stella'),
       (44, 'BAKER ', 'Emilia'),
       (45, 'HALL ', 'Maya'),
       (46, 'RIVERA ', 'Everly'),
       (47, 'CAMPBELL ', 'Leilani'),
       (48, 'MITCHELL ', 'Delilah'),
       (49, 'CARTER ', 'Athena'),
       (50, 'ROBERTS ', 'Naomi'),
       (51, 'GOMEZ ', 'Noah'),
       (52, 'PHILLIPS ', 'Liam'),
       (53, 'EVANS ', 'Oliver'),
       (54, 'TURNER ', 'Elijah'),
       (55, 'DIAZ ', 'Lucas'),
       (56, 'PARKER ', 'Mateo'),
       (57, 'CRUZ ', 'Levi'),
       (58, 'EDWARDS ', 'Asher'),
       (59, 'COLLINS ', 'Ethan'),
       (60, 'REYES ', 'Leo'),
       (61, 'STEWART ', 'James'),
       (62, 'MORRIS ', 'Grayson'),
       (63, 'MORALES ', 'Luca'),
       (64, 'MURPHY ', 'Ezra'),
       (65, 'COOK ', 'Aiden'),
       (66, 'ROGERS ', 'Sebastian'),
       (67, 'GUTIERREZ ', 'Benjamin'),
       (68, 'ORTIZ ', 'Wyatt'),
       (69, 'MORGAN ', 'Henry'),
       (70, 'COOPER ', 'Mason'),
       (71, 'PETERSON ', 'Owen'),
       (72, 'BAILEY ', 'Jack'),
       (73, 'REED ', 'Jackson'),
       (74, 'KELLY ', 'Daniel'),
       (75, 'HOWARD ', 'Hudson'),
       (76, 'RAMOS ', 'Alexander'),
       (77, 'KIM ', 'Kai'),
       (78, 'COX ', 'Gabriel'),
       (79, 'WARD ', 'Carter'),
       (80, 'RICHARDSON ', 'Maverick'),
       (81, 'WATSON ', 'William'),
       (82, 'BROOKS ', 'Logan'),
       (83, 'CHAVEZ ', 'Muhammad'),
       (84, 'WOOD ', 'Michael'),
       (85, 'JAMES ', 'Samuel'),
       (86, 'BENNETT ', 'Ezekiel'),
       (87, 'GRAY ', 'Jayden'),
       (88, 'MENDOZA ', 'Luke'),
       (89, 'RUIZ ', 'Lincoln'),
       (90, 'HUGHES ', 'Theo'),
       (91, 'PRICE ', 'Elias'),
       (92, 'ALVAREZ ', 'Josiah'),
       (93, 'CASTILLO ', 'Julian'),
       (94, 'SANDERS ', 'Jacob'),
       (95, 'PATEL ', 'Waylon'),
       (96, 'MYERS ', 'David'),
       (97, 'LONG ', 'Theodore'),
       (98, 'ROSS ', 'Jaxon'),
       (99, 'FOSTER ', 'Matthew'),
       (100, 'JIMENEZ ', 'Nathan'),
       (101, 'POWELL ', 'Charlotte'),
       (102, 'JENKINS ', 'Evelyn'),
       (103, 'PERRY ', 'Harper'),
       (104, 'RUSSELL ', 'Scarlett'),
       (105, 'SULLIVAN ', 'Ella'),
       (106, 'BELL ', 'Nova'),
       (107, 'COLEMAN ', 'Aurora'),
       (108, 'BUTLER ', 'Gianna'),
       (109, 'HENDERSON ', 'Aria'),
       (110, 'BARNES ', 'Mila'),
       (111, 'GONZALES ', 'Ellie'),
       (112, 'FISHER ', 'Sofia'),
       (113, 'VASQUEZ ', 'Violet'),
       (114, 'SIMMONS ', 'Willow'),
       (115, 'ROMERO ', 'Layla'),
       (116, 'JORDAN ', 'Camila'),
       (117, 'PATTERSON ', 'Lily'),
       (118, 'ALEXANDER ', 'Hazel'),
       (119, 'HAMILTON ', 'Chloe'),
       (120, 'GRAHAM ', 'Avery'),
       (121, 'REYNOLDS ', 'Elena'),
       (122, 'GRIFFIN ', 'Penelope'),
       (123, 'WALLACE ', 'Eliana'),
       (124, 'MORENO ', 'Paisley'),
       (125, 'WEST ', 'Isla'),
       (126, 'COLE ', 'Eleanor'),
       (127, 'HAYES ', 'Abigail'),
       (128, 'BRYANT ', 'Elizabeth'),
       (129, 'HERRERA ', 'Ivy'),
       (130, 'GIBSON ', 'Riley'),
       (131, 'ELLIS ', 'Nora'),
       (132, 'TRAN ', 'Grace'),
       (133, 'MEDINA ', 'Emily'),
       (134, 'AGUILAR ', 'Zoey'),
       (135, 'STEVENS ', 'Stella'),
       (136, 'MURRAY ', 'Emilia'),
       (137, 'FORD ', 'Maya'),
       (138, 'CASTRO ', 'Everly'),
       (139, 'MARSHALL ', 'Leilani'),
       (140, 'OWENS ', 'Delilah'),
       (141, 'HARRISON ', 'Athena'),
       (142, 'FERNANDEZ ', 'Naomi'),
       (143, 'MCDONALD ', 'Noah'),
       (144, 'WOODS ', 'Liam'),
       (145, 'WASHINGTON ', 'Oliver'),
       (146, 'KENNEDY ', 'Elijah'),
       (147, 'WELLS ', 'Lucas'),
       (148, 'VARGAS ', 'Mateo'),
       (149, 'HENRY ', 'Levi'),
       (150, 'CHEN ', 'Asher'),
       (151, 'FREEMAN ', 'Ethan'),
       (152, 'WEBB ', 'Leo'),
       (153, 'TUCKER ', 'James'),
       (154, 'GUZMAN ', 'Grayson'),
       (155, 'BURNS ', 'Sebastian'),
       (156, 'CRAWFORD ', 'Benjamin'),
       (157, 'OLSON ', 'Wyatt'),
       (158, 'SIMPSON ', 'Henry'),
       (159, 'PORTER ', 'Mason'),
       (160, 'HUNTER ', 'Owen'),
       (161, 'GORDON ', 'Jack'),
       (162, 'MENDEZ ', 'Jackson'),
       (163, 'SILVA ', 'Daniel'),
       (164, 'SHAW ', 'Hudson'),
       (165, 'SNYDER ', 'Alexander'),
       (166, 'MASON ', 'Kai'),
       (167, 'DIXON ', 'Gabriel'),
       (168, 'MUNOZ ', 'Carter'),
       (169, 'HUNT ', 'Maverick'),
       (170, 'HICKS ', 'William'),
       (171, 'HOLMES ', 'Logan'),
       (172, 'PALMER ', 'Muhammad'),
       (173, 'WAGNER ', 'Michael'),
       (174, 'BLACK ', 'Samuel'),
       (175, 'ROBERTSON ', 'Ezekiel'),
       (176, 'BOYD ', 'Jayden'),
       (177, 'ROSE ', 'Luke'),
       (178, 'STONE ', 'Lincoln'),
       (179, 'SALAZAR ', 'Theo'),
       (180, 'FOX ', 'Elias'),
       (181, 'WARREN ', 'Josiah'),
       (182, 'MILLS ', 'Julian'),
       (183, 'MEYER ', 'Jacob'),
       (184, 'RICE ', 'Waylon'),
       (185, 'SCHMIDT ', 'David'),
       (186, 'GARZA ', 'Theodore'),
       (187, 'DANIELS ', 'Jaxon'),
       (188, 'FERGUSON ', 'Matthew'),
       (189, 'NICHOLS ', 'Lincoln'),
       (190, 'STEPHENS ', 'Theo'),
       (191, 'SOTO ', 'Elias'),
       (192, 'WEAVER ', 'Josiah'),
       (193, 'RYAN ', 'Julian'),
       (194, 'GARDNER ', 'Jacob'),
       (195, 'PAYNE ', 'Waylon'),
       (196, 'GRANT ', 'David'),
       (197, 'DUNN ', 'Theodore'),
       (198, 'KELLEY ', 'Jaxon'),
       (199, 'SPENCER ', 'Matthew'),
       (200, 'HAWKINS ', 'Nathan'),
       (201, 'ARNOLD ', 'Charlotte'),
       (202, 'PIERCE ', 'Evelyn'),
       (203, 'VAZQUEZ ', 'Harper'),
       (204, 'HANSEN ', 'Scarlett'),
       (205, 'PETERS ', 'Ella'),
       (206, 'SANTOS ', 'Nova'),
       (207, 'HART ', 'Aurora'),
       (208, 'BRADLEY ', 'Gianna'),
       (209, 'KNIGHT ', 'Aria'),
       (210, 'ELLIOTT ', 'Mila'),
       (211, 'CUNNINGHAM ', 'Ellie'),
       (212, 'DUNCAN ', 'Sofia'),
       (213, 'ARMSTRONG ', 'Violet'),
       (214, 'HUDSON ', 'Willow'),
       (215, 'CARROLL ', 'Layla'),
       (216, 'LANE ', 'Camila'),
       (217, 'RILEY ', 'Lily'),
       (218, 'ANDREWS ', 'Hazel'),
       (219, 'ALVARADO ', 'Chloe'),
       (220, 'RAY ', 'Avery'),
       (221, 'DELGADO ', 'Elena'),
       (222, 'BERRY ', 'Penelope');
