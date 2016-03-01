# Testausdokumentti

## Mutaatiotestauksen puutteita

* **tetra.logic.Direction**: Uskoakseni Direction-luokan mutaatiokattavuuden pitäisi olla 100%, vaikka raportilla näkyvä luku on 46%. Tämä johtuu siitä, että PIT ei pysty testaamaan luokan staattisessa alustajassa sijaitsevaa koodia. Oletuksena PIT jättääkin tälläisen koodin testaamatta. Tässä tapauksessa tuo tunnistus ei kuitenkaan toimi oikein, koska koodi on sijoitettu erilliseen metodiin, jota kutsutaan staattisesta lohkosta. Jos metodiin sijoitetun koodin kopioi suoraan static-lohkon sisälle, tuloksena on 100%:n mutaatiokattavuus. Direction on lueteltu tyyppi, joka alustaa sisäiset kenttänsä staattisella alustajalla (static initializer block). Ilmeisesti PIT suorittaa mutaatiotestauksen tietokoneen muistissa vasta sen jälkeen, kun staattiset alustajat on jo suoritettu. [[1]](http://pitest.org/java_mutation_testing_systems/), [[2]](https://groups.google.com/d/msg/pitusers/bszYZ-5zYFE/bF8tOnlJMAcJ), [[3]](https://groups.google.com/forum/#!msg/pitusers/bszYZ-5zYFE/UekWQEoQhKUJ).

* **tetra.logic.Tetromino**:
