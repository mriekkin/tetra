# Aihemäärittely

**Aihe:** Tetris

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot pelin aikana:**

Pelialue on ruudukko, ja palikkaa siirtyy aina diskreetein askelin pelialueen ruutujen välillä.

* Palikan siirto sivusuunnassa
  * Palikkaa pitää voida liikuttaa varsin nopeasti, jotta pelaaja ehtii reagoida erilaisiin pelitilanteisiin.
* Palikan pyöritys
  * Kullakin palikalla on 4 erilaista asentoa, joihin päästään pyörittämällä palikkaa 90 astetta kerrallaan. Yleensä palikkaa pyöritetään myötäpäivään, mutta pyörittää voi myös vastapäivään.
* Palikan tiputtaminen tavallista nopeammin (*soft drop*)
  * Pelaaja voi käskeä palikkaa tippumaan nopeutetussa tahdissa.
  * Nopeuttamisesta saa pisteitä, koska se lisää pelin vaativuutta.
* Palikan tiputtaminen suoraan ala-asentoon (*hard drop*)
  * Välittömästä tiputtamisesta saa pisteitä, koska se lisää pelin vaativuutta.
  * Välitön tiputtaminen antaa enemmän pisteitä kuin tiputtaminen tavallista nopeammin.

Seuraavat toiminnot ovat olennainen osa pelin toimintaa, mutta pelaaja ei varsinaisesti kontrolloi niiden suorittamista.

* Palikkaa tippuu askel kerrallaan kohti ruudun alareunaa
  * Palikkaa siirtyy vakionopeudella kohti ruudun alareunaa niin kauan kuin se on pelattavissa.
  * Tippumisnopeus kiihtyy pelin kuluessa. Jokainen täytetty rivi kasvattaa tippumisnopeutta hieman.
* Palikkaa saavuttaa ala-asennon, jonka jälkeen se ei enää ole pelattavissa
  * Palikka saavuttaa ala-asennon, kun se törmää toiseen sen alla olevaan palikkaan tai pelialueen alareunaan.
  * Jos pelaaja sai yhden tai useamman vaakarivin täytettyä
      * Täydet vaakarivit poistuvat pelialueelta. Yhtä aikaa voi täyttyä 1-4 riviä.
      * Pelaaja saa pisteitä täyttyneiden rivien lukumäärän mukaan. Parhaat pisteet saa neljällä rivillä.
  * Jos yksikään vaakarivi ei täyttynyt kokonaan
    * Viimeisin pelattu palikka jää paikalleen.
    * Peli arpoo uuden palikan, ja lähettää sen liikkeelle pelialueen yläreunasta.


**Muut toiminnot:**


