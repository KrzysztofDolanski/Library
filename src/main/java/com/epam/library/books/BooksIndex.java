package com.epam.library.books;

public class BooksIndex {

    public static void main(String[] args) {
        produceBooks(index);
    }
    public static void produceBooks(String index){
            System.out.println("INSERT INTO BOOKS (id, title, author, available) VALUES");
            int no = 1;
        for (String s : index.split(";")) {

            String[] split = s.split("-");
            System.out.printf("(%d, '%s', '%s', true),", no++,
                    split[0].replaceAll("\n", ""),
                    split[1].replaceAll("\n", ""));
        }
    }

    private static String index = """
            Nela\s
            -
            10 NIESAMOWITYCH PRZYGÓD NELI
            ;A. Pilipiuk
            -
            2586 KROKÓW
            ;A. Griffiths
            -
            39 PIĘTROWY DOMEK NA DRZEWIE
            ;M.Warda
            -
            5 SEKUND DO IO. REBELIANTKA
            ;A. Griffiths
            -
            65 PIĘTROWY DOMEK NA DRZEWIE
            ;A. Griffiths
            -
            91 PIĘTROWY DOMEK NA DRZEWIE
            ;P.Beręsewicz
            -
            A NIECH TO CZYKOLADA
            ;K.Szymeczko
            -
            A TO HISTORIA. OPOWIADANIA Z DZIEJÓW POLSKI
            ;G.Bąkiewicz
            -
            A U NAS W DOMU
            ;Ł. Wierzbicki
            -
            AFRYKA KAZIKA
            ;B.Nawrocka
            -
            AIRAM OSTATNIA TAJEMNICA
            ;J. Sykes
            -
            AKADEMIA JEDNOROŻCÓW.\s
            ALEKSANDRA I ISKRA
            ;J. Sykes
            -
            AKADEMIA JEDNOROŻCÓW. ZOSIA I TĘCZA
            ALADDIN AND THE LAMP\s
            ;B.Nawrocka
            -
            ALHAR SYN ANHARA
            ;R.Kosik
            -
            AMELIA I KUBA\s
            ;B.Nawrocka
            -
            ANHAR POWIEŚĆ ANTYMAGICZNA
            ;M.Widmark
            -
            ANTYKWARIAT POD BŁĘKITNYM LUSTREM
            ;B.Nawrocka
            -
            ARI
            EL WNUK ALHARA
            ;M.Szczygielski
            -
            ARKA CZASU
            ;J.Papuzińska
            -
            ASIUNIA
            ASTERIKS\s
            ;J.Wachowiak
            -
            AWARIA
            ;J.Bednarek
            -
            BABCOCHA
            BAJKI 5 MINUT PRZED SNEM\s
            ;M.Białek
            -
            BAJKI DLA CHŁOPCÓW
            ;M.Białek
            -
            BAJKI DLA DZIEWCZYNEK
            ;B.Stańczuk
            -
            BAJKI POMAGAJKI NA D
            OBRY DZIEŃ I JESZCZE LEPSZE JUTRO
            ;B.Stańczuk
            -
            BAJKI TERAPEUTYCZNE NA DOBRY DZIEŃ I JESZCZE LEPSZE\s
            JUTRO
            ;B.Stańczuk
            -
            BAJKI UŚMIECHAJKI
            ;J.Bednarek
            -
            BANDA CZARNEJ FROTTE
            ;A.Wheatley
            -
            BARDZO ILUSTROWANA HISTORIA ZIEMI
            ;Z.Stanecka
            -
            BASIA\s
            ;J.Ch.Andersen
            -
            BAŚNIE ANDERSEN\s
            ;N.Vanier
            -
            BELLA I SEBASTIAN
            ;Ch. Féret\s
            -
            BELLA I SEBASTIAN
            2\s
            ;Ch. Féret\s
            -
            BELLA I SEBASTIAN
            3
            ;D.Comrzyńska
            -
            BEZSENNOŚĆ JUTKI
            ;G.Kasdepke
            -
            BEZU
            ;J.Escott
            -
            BIG BAG MISTAKE\s
            ;A.Sewell
            -
            BLACK BEAUTY\s
            ;E.Nowak
            -
            BŁAHOSTKA I KAMYK\s
            ;M.Zarębska
            -
            BORYS I ZAJĄCZKI
            ;J.Brzechwa
            -
            BRZECHWA DZIECIOM
            ;J.Brzechwa
            -
            BRZECHWA DZIECIOM, DZIEŁA WSZYSTKIE. TEATRZYKI
            ;B.Kosmowska
            -
            BUBA
            ;B.Kosmowska
            -
            BUBA I SEZON OGÓRKOWY
            ;J.Korczakowska
            -
            BUŁECZKA
            ;W.Bruce Cameron
            -
            BYŁ SOBIE PIES
            2
            ;W.Bruce Cameron
            -
            BYŁ SOBIE SZCZENIAK
            ;M.Melin
            -
            CHŁOPAK Z LASU
            ;R.Witek
            -
            CHŁOPIEC Z LAMPEDUSY
            ;O.Q.Raúf
            -
            CHŁOPIEC Z OSTATNIEJ ŁAWKI
            ;A.Shepherd
            -
            CHŁOPIEC, KTÓRY HODOWAŁ SMOKI
            ;Ł.Kaniewski
            -
            CHOLERA I INNE CHOROBY
            ;A.J.Epstein
            -
            CHOWAŃCE\s
            ;E.Piotrowska
            -
            CIOCIA JADZIA\s
            ;L.Erocak
            -
            CLOTHES AT WORK
            ;W.Cichoń
            -
            CO SŁYCHAĆ, CUKIERKU?
            ;I.Michto
            -
            CO WARTO WIEDZIEĆ O PRZYRODZIE
            ;W.Błach
            -
            CO WARTO WIEDZIEĆ O ZWIERZĘTACH
            ;D.Terakowska
            -
            CÓRKA CZAROWNIC
            ;J.Duszyńska
            -
            CUDACZEK
            WYŚMIEWACZEK
            ;R.Piątkowska
            -
            CUKIERKI
            ;M.Kulus
            -
            CYKOR
            ;D.Bach
            -
            CYNAMON CHŁOPAK I JA
            ;D.Bach
            -
            CYNAMON KŁOPOTY I JA
            ;J.Jagiełło
            -
            CZACHA SIĘ BUNTUJE
            ;D.Suwalska
            -
            CZARNE JEZIORA
            ;U.K. Le Guin
            -
            CZARNOKSIĘŻNIK Z ARCHIPELAGU
            ;M.Szczygielski
            -
            CZARNY MŁ
            YN
            ;M.Szczygielski
            -
            CZAROWNICA PIĘTRO NIŻEJ
            ;K.Giery
            -
            CZERWIEŃ RUBINU
            ;J.Podsiadło
            -
            CZERWONA KARTKA DLA SPRĘŻYNY
            ;Opracowanie zbiorowe
            -
            CZYTAM SOBIE\s
            ;K.Rundell
            -
            DACHOŁAZY
            ;W.Cichoń
            -
            DASZ RADĘ, CUKIERKU\s
            ;M.Pałasz
            -
            DASZ RADĘ, MARCELKO\s
            ;B.Wicher
            -
            DETEKTYW ŁODYGA
            ;Opracowanie zbiorowe
            -
            DETEKTYW ŁODYGA II
            ;A.T.Smith
            -
            DETEKTYW PINGWIN I FORTECA TAJEMNIC
            ;A.T.Smith
            -
            DETEKTYW PINGWIN I SPRAWA ZAGINIONEGO SKARBU
            ;G.Kasdepke
            -
            DETEKTYW POZYTYWKA
            ;Z.Orlińska
            -
            DETEKTYWI Z KLASZTORNEGO WZGÓRZA
            ;P.Wakuła
            -
            DĘBOWA KOŁYSKA\s
            ;A.Franc
            -
            DIARY OF A YOUNG GIRL\s
            ;G.Strzeboński
            -
            DOBRE MANIERY DLA DZIECI
            ;H.Lofting
            -
            DOKTOR DOLITTLE I JEGO ZWIERZĘTA
            """;
}
