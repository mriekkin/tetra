# Aiheen kuvaus ja rakenne

## Aihemäärittely

**Aihe:** Tetris

Tetris on yhden pelaajan peli, jossa pyritään pinoamaan palikoita siten, että ne muodostavat täysiä vaakarivejä. Palikat tippuvat yksi kerrallaan pelialueen yläreunasta. Pelaajan tavoite on liikuttaa ja pyörittää pelattavissa oleva palikka mahdollisimman edulliseen asentoon ennen kuin se saavuttaa alla odottavan palikoiden pinon. Tetriksen pelialue on ruudukko, ja palikka siirtyy aina kokonaisin askelin pelialueen ruutujen välillä.

Tetriksessä on 7 erilaista palikkaa. Palikoita saadaan käännettyä erilaisiin asentoihin pyörittämällä niitä tasossa. Kullakin palikalla on 4 erilaista asentoa, mutta osa näistä voi olla keskenään samanlaisia. Erilaisia asentoja onkin siten palikasta riippuen 1, 2 tai 4. Pyörittäminen tapahtuu myötäpäivään tai vastapäivään, mutta aina 90 astetta kerrallaan. Yleensä palikkaa pyöritetään myötäpäivään.

Tetriksen palikat koostuvat neljästä yhden ruudun kokoisesta palasesta. Alkuperäinen nimi tetris on yhdistelmä sanoista tetra, joka tarkoittaa neljää, ja tennis. Tämän Tetris-kloonin nimeksi on valittu alkuperäisen nimen ensimmäinen kantasana: Tetra.

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot pelin aikana:**

Pelin aika pelaajalla on käytettävissä seuraavat pelilogiikkaan liittyvät toiminnot.

* *Palikan siirto sivusuunnassa*
  * Pelaaja voi siirtää sillä hetkellä pelattavissa olevaa palikkaa sivusuunnassa.
  * Palikkaa pitää voida liikuttaa melko nopeasti, jotta pelaaja ehtii reagoida erilaisiin pelitilanteisiin.
* *Palikan pyöritys*
  * Pelaaja voi pyörittää sillä hetkellä pelattavissa olevaa palikkaa myötäpäivään tai vastapäivään.
* *Palikan tippumisen nopeuttaminen (soft drop)*
  * Pelaaja voi käskeä palikkaa tippumaan tavallista nopeammin.
* *Palikan tiputtaminen suoraan ala-asentoon (hard drop)*
  * Pelaaja voi tiputtaa palikan suoraan ala-asentoon.

Seuraavat toiminnot ovat olennainen osa pelilogiikkaa, mutta pelaaja ei kontrolloi niiden suorittamista.

* *Palikkaa tippuu vakionopeudella niin kauan kuin se on pelattavissa*
  * Palikka siirtyy vakionopeudella kohti ruudun alareunaa niin kauan kuin se on pelattavissa.
  * Tippumisnopeus kiihtyy pelin kuluessa. Jokainen täytetty rivi kasvattaa tippumisnopeutta hieman.
* *Palikkaa saavuttaa ala-asennon, jonka jälkeen se ei enää ole pelattavissa*
  * Palikka saavuttaa ala-asennon, kun se törmää toiseen sen alla olevaan palikkaan tai pelialueen alareunaan.
  * Tämän jälkeen palikka ei enää liiku eikä ole pelaajan liikuteltavissa.
  * Jos pelialueelle muodostui yksi tai useampi täysi vaakarivi, täydet vaakarivit poistuvat pelialueelta. Yhtä aikaa voi täyttyä 1-4 riviä.
  * Jos yksikään vaakarivi ei täyttynyt kokonaan, viimeisin pelattu palikka jää paikalleen.
  * Peli arpoo uuden palikan, ja lähettää sen liikkeelle pelialueen yläreunasta.
* *Peli päättyy, kun palikoiden pino saavuttaa pelialueen yläreunan*
  * Pelin päätyttyä näytetään täysien tyhjennettyjen rivien lukumäärä.
  * Pelaaja voi aloittaa uuden pelin, tai poistua ohjelmasta.

Pelin aika pelaajalla on käytettävissä myös seuraavat toiminnot, jotka eivät liity pelilogiikkaan.

* *Uuden pelin aloitus (restart)*
* *Ohjelman sulkeminen*

**Aiheen laajennusmahdollisuudet:**

Seuraavassa on joitakin ajatuksia siitä, miten aihetta olisi mahdollista laajentaa ajan niin salliessa.

* *Pisteiden laskenta*
* *Tulevien palikoiden näyttö*
* *Haamupalikka, joka näyttää tulevan loppuasennon (ghost piece)*
* *Kootut tilastotiedot kaikista pelatuista peleistä*

## Rakennekuvaus

Pelin logiikasta vastaava pääluokka on nimeltään Tetra. Tämän luokan vastuu on päivittää pelisilmukkaa ajastimen avulla. Pääluokka kytkee ohjelman eri osaset toisiinsa, mutta delegoi suurimman osan työstä muille luokille. Pääluokka käyttää apunaan ensisijaisesti luokkia Piece ja Matrix, jotka ovat pelin käsitteistössäkin keskeisiä luokkia. Näistä ensimmäinen, Piece, kuvaa pelaajan hallitsemaa palikkaa. Näistä jälkimmäinen, Matrix, kuvaa tyhjistä ruuduista ja lukittuneista palasista koostuvaa pelialuetta. Näiden kahden lisäksi pääluokka käyttää vielä kolmatta luokkaa RandomTetromino satunnaisuuden mallintamiseen.

Pelin pelialue on matriisi, joka koostuu tyhjistä ruuduista ja paikoilleen lukittuneista palasista. Pelialuetta kuvataan luokalla Matrix. Tämän luokan vastuu on kuvata pelialue ja sen sisältö. Sisältöä, paikoilleen lukittuneita palasia, kuvaa apuluokka Block. Block tarkoittaa yhtä neliön muotoista palasta. Kunkin palasen olennainen ominaisuus on sen väri, joka määrittyy tetrominon tyypin mukaan. Palaset on toteutettu siten, että ilmentymiä tarvitaan vain 7, joihin viitataan tarpeen mukaan. Yhteenvetona voidaan todeta, että matriisi tarjoaa metodit sen sisällön tarkasteluun ja muokkaamiseen. Toisaalta varsinainen sisällön päivittäminen ei kuulu matriisin vastuisiin. Esimerkiksi täysien rivien tunnistaminen ja tyhjentäminen on eriytetty luokkaan LineClearer.

Tetristä pelataan tetromino-palikoilla, joita on olemassa 7 erilaista tyyppiä. Ohjelmassa nämä tyypit määritellään lueteltuna tyyppinä Tetromino. Yksi Tetromino-luokan ilmentymä esittää yhden tyyppisen palikan muodon ja sen sallitut kierrot. Pelaajan ohjaaman palikan Piece esittämiseen tarvitaan siis kolme asiaa: tetromino-palikka, sen sijainti matriisissa, ja orientaatio. Näistä viimeistä, eli orientaatiota, esittää lueteltu tyyppi Direction. Direction määrittelee pelissä sallitut liikkumissuunnat ja kierrot näiden välillä.

![Luokkakaavio](luokkakaavio.png)

## Sekvenssikaaviot

Ensimmäiseksi kuvataan tapaus, jossa käyttäjä ohjaa pelattavaa palikkaa sivusuunnassa. Tässä tapauksessa käyttäjä on ohjannut palikkaa siirtymään vasemmalle. Tarkoitus on havainnollistaa, miten luokat Piece, Tetromino ja Matrix tutkivat yhdessä, onko käyttäjän pyytämä siirto mahdollinen. Ongelman keskiössä on palikan metodi ```Piece.testCollision```. Tämän metodin avulla voidaan tunnistaa muutkin palikan liikkumista rajoittavat tapaukset. Koodin toiminnan kannalta on mielenkiintoisempaa tarkastella sellaista tapausta, jossa syntyy törmäys. Tässä tarkastellaan siis sellaista tilannetta, jossa siirtyminen ei ole mahdollista. Palikan siirtymistä voivat rajoittaa joko pelialueen reunat tai matriisiin kertyneet palaset.

![Sekvenssikaavio tapauksesta, jossa käyttäjä ohjaa palikkaa sivusuunnassa](sekvenssikaavio-palikan-siirto-sivusuunnassa.png)

Sekvenssikaavio tapauksesta, jossa palikkaa pyöritetään myötäpäivään. Tässä kuvaajassa on jätetty metodi ```Piece.testCollision``` avaamatta, koska sen toiminta on kuvattu edellisessä kuvaajassa.

![Sekvenssikaavio tapauksesta, jossa palikkaa pyöritetään myötäpäivään](sekvenssikaavio-palikan-pyoritys.png)

