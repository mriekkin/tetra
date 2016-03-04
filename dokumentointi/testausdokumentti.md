# Testausdokumentti

## Yksikkötestaus

Projekti sisältää varsin kattavan joukon yksikkötestejä. Automaattisia yksikkötestejä on kirjoitettu jokaiselle logiikkapaketin (tetra.logic) luokalle. Testit on jaettu erillisiin tiedostoihin, joissa on pääosin 50-150 riviä per tiedosto. Nämä tiedostot on lueteltu alla olevassa taulukossa. Automaattinen testaus ei kuitenkaan koske ohjelman käyttöliittymää. Kaikki testit on kirjoitettu JUnit-kirjastolla.

Testejä on yhteensä 137 kappaletta. Yhdessä niiden rivikattavuus on 99 % ja mutaatiokattavuus 88 % ([PIT-raportti](pit/index.html)). Kattavuus on mitattu PIT-järjestelmällä.

| Testattava luokka | Yksikkötestiluokka              |
| :---------------- | :------------------------------ |
| Block             | BlockTest                       |
| Direction         | DirectionTest                   |
| LineClearer       | LineClearerTest                 |
| LineClearer       | LineClearerShiftRowsDownTest    |
| Matrix            | MatrixTest                      |
| Matrix            | MatrixGetSetBlocksTest          |
| Piece             | PieceTest                       |
| Piece             | PieceMovementTest               |
| Piece             | PieceCollisionDetectionTest     |
| RandomTetromino   | RandomTetrominoTest             |
| Tetra             | TetraTest                       |
| Tetra             | TetraUpdateGameTest             |
| Tetra             | TetraUpdateGameWhenLinesCleared |
| Tetromino         | TetrominoTest                   |
| Tetromino         | TetrominoShapeTest              |

Testit hyödyntävät lisäksi kolmea apuluokkaa, jotka sisältävät pienen määrän metodeita. Nämä apuluokat ovat MatrixHelper, RandomIntStub ja RandomTetrominoStub, ja ne ovat testien kanssa samassa kansiossa. MatrixHelper-luokkaa voi käyttää testin tarvitseman pelitilanteen rakentamisessa ja lopputuloksen tarkistamisessa. Luokkaa RandomIntStub voi käyttää testeissä luokan Random sijasta, ja luokkaa RandomTetrominoStub voi käyttää testeissä luokan RandomTetromino sijasta. Nämä RandomStub-luokat palauttavat aina ennalta määrätyn sarjan alkiota. Haluttu sarja määritellään konstruktorin parametrina.

## Mutaatiotestauksen puutteita

* **tetra.logic.Direction**: Direction-luokan mutaatiokattavuus on PIT-raportin mukaan selvästi muita luokkia pienempi, 46 %. Tämä johtunee siitä, että Direction alustaa sisäiset kenttänsä staattisella alustajalla (*static initializer block*). PIT ei pysty testaamaan tällaista koodia, koska mutaatiotestaus suoritetaan tietokoneen muistissa vasta sen jälkeen, kun staattiset alustajat on jo suoritettu. Oletuksena PIT jättää staattisessa alustajassa sijaitsevan koodin pois laskuista. Tässä tapauksessa tuo tunnistus ei kuitenkaan toimi oikein, koska alustuskoodi on sijoitettu erilliseen metodiin, jota kutsutaan staattisesta lohkosta. Jos metodiin sijoitetun koodin kopioi suoraan static-lohkon sisälle, mutaatiokattavuus nousee 100 %:n. Näin ei ole kuitenkaan tehty, koska erillinen metodi on muuten siistimpi ratkaisu.   [[1]](http://pitest.org/java_mutation_testing_systems/), [[2]](https://groups.google.com/d/msg/pitusers/bszYZ-5zYFE/bF8tOnlJMAcJ), [[3]](https://groups.google.com/forum/#!msg/pitusers/bszYZ-5zYFE/UekWQEoQhKUJ).

## Järjestelmätestaus

Ohjelmaa on testattu järjestelmätasolla ajamalla sitä useita kertoja erilaisissa pelitilanteissa. Järjestelmätestauksessa ei ole hyödynnetty erityistä testaussuunnitelmaa, mutta testaaja on yrittänyt keskittyä sellaisiin operaatioihin, joiden toteutus on vaatinut erityistä huolellisuutta. Testaus on suoritettu pääosin Mac-alustalla, mutta ohjelman toimivuus on tarkistettu myös koulun Linux-koneilla. Ohjelmaa on ajettu sekä Netbeansin kautta että erillisestä jar-tiedostosta.

## Tunnetut bugit

Ohjelmassa on yksi tiedossa oleva bugi, joka voi ilmestyä pelin päättyessä. Viimeinen ilmestyvä palikka voi mennä päällekkäin pinon päällimmäisten palasten kanssa. Tämä ei vaikuta pelin kulkuun, koska tässä tilanteessa peli päättyy. Tämä johtuu siitä, että nykyisessä toteutuksessa uusi palikka ilmestyy aina samaan paikkaan. Tetriksessä tämä ongelma on ratkaistu sillä tavalla, että palikan ilmestymispaikka voi tarvittaessa siirtyä pari riviä ylöspäin. Tätä varten matriisin yläosassa on kaksi ylimääräistä riviä, joita pelaaja ei näe. Siinä mielessä kyseessä on toisaalta bugi, toisaalta ominaisuus, joka on jätetty toteuttamtta yksinkertaisuuden vuoksi.
