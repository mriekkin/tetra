# Aihemäärittely

**Aihe:** Tetris

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot pelin aikana:**

Pelialue on ruudukko, ja palikka siirtyy aina kokonaisin askelin pelialueen ruutujen välillä.

Tetriksessä on 7 erilaista palikkaa. Jokainen näistä palikoista koostuu neljästä palasesta. Palikoita saadaan käännettyä erilaisiin asentoihin pyörittämällä niitä tasossa. Kullakin palikalla on 4 erilaista asentoa, mutta osa näistä voi olla keskenään samanlaisia. Erilaisia asentoja onkin siten palikasta riippuen 1, 2 tai 4. Pyörittäminen tapahtuu myötäpäivään tai vastapäivään, mutta aina 90 astetta kerrallaan. Yleensä palikkaa pyöritetään myötäpäivään.

Pelin aika pelaajalla on käytettävissä seuraavat pelilogiikkaan liittyvät toiminnot.

* *Palikan siirto sivusuunnassa*
  * Pelaaja voi siirtää sillä hetkellä pelattavissa olevaa palikkaa sivusuunnassa.
  * Palikkaa pitää voida liikuttaa melko nopeasti, jotta pelaaja ehtii reagoida erilaisiin pelitilanteisiin.
* *Palikan pyöritys*
  * Pelaaja voi pyörittää sillä hetkellä pelattavissa olevaa palikkaa myötäpäivään tai vastapäivään.
* *Palikan tippumisen nopeuttaminen (soft drop)*
  * Pelaaja voi käskeä palikkaa tippumaan tavallista nopeammin.
  * Nopeuttamisesta saa pisteitä, koska se lisää pelin vaativuutta.
* *Palikan tiputtaminen suoraan ala-asentoon (hard drop)*
  * Välittömästä tiputtamisesta saa pisteitä, koska se lisää pelin vaativuutta.
  * Välitön tiputtaminen antaa enemmän pisteitä kuin tippumisen nopeuttaminen.

Seuraavat toiminnot ovat olennainen osa pelilogiikkaa, mutta pelaaja ei kontrolloi niiden suorittamista.

* *Palikkaa tippuu vakionopeudella niin kauan kuin se on pelattavissa*
  * Palikka siirtyy vakionopeudella kohti ruudun alareunaa niin kauan kuin se on pelattavissa.
  * Tippumisnopeus kiihtyy pelin kuluessa. Jokainen täytetty rivi kasvattaa tippumisnopeutta hieman.
* *Palikkaa saavuttaa ala-asennon, jonka jälkeen se ei enää ole pelattavissa*
  * Palikka saavuttaa ala-asennon, kun se törmää toiseen sen alla olevaan palikkaan tai pelialueen alareunaan.
  * Tämän jälkeen palikka ei enää liiku eikä ole pelaajan liikuteltavissa.
  * Jos pelialueelle muodostui yksi tai useampi täysi vaakarivi
    * Täydet vaakarivit poistuvat pelialueelta. Yhtä aikaa voi täyttyä 1-4 riviä.
    * Pelaaja saa pisteitä täyttyneiden rivien lukumäärän mukaan. Parhaat pisteet saa neljällä samanaikaisella rivillä.
  * Jos yksikään vaakarivi ei täyttynyt kokonaan
    * Viimeisin pelattu palikka jää paikalleen.
    * Peli arpoo uuden palikan, ja lähettää sen liikkeelle pelialueen yläreunasta.

Pelaajalla on käytettävissä myös seuraavat toiminnot, jotka eivät liity pelilogiikkaan.

* *Pause*
* *Pelin lopettaminen ja pelistä poistuminen*

**Pelaajan toiminnot, silloin kun peli ei ole käynnissä (valikot):**

* *Uuden pelin aloitus*
* *Pistelista (high score table)*