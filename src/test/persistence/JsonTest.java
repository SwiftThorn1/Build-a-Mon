package persistence;


import model.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

// code from JsonSerializationDemo
public class JsonTest {
    protected void checkPokemon(String name, String type1, String type2, int hp, int atk, int def, int spa
            , int spd, int spe, Pokemon pokemon) {
        assertEquals(name, pokemon.getName());
        assertEquals(type1, pokemon.getType1());
        assertEquals(type2, pokemon.getType2());
        assertEquals(hp, pokemon.getHp());
        assertEquals(atk, pokemon.getAtk());
        assertEquals(def, pokemon.getDef());
        assertEquals(spa, pokemon.getSpa());
        assertEquals(spd, pokemon.getSpd());
        assertEquals(spe, pokemon.getSpe());
    }
}
