package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    private Pokemon testPokemon1;
    private Pokemon testPokemon2;

    @BeforeEach
    public void setup() {
        testPokemon1 = new Pokemon("test", "water", "", 5, 8, 10, 16, 2, 3);
        testPokemon2 = new Pokemon("test2", "one", "two", 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void constructorTest() {
        assertEquals("test", testPokemon1.getName());
        assertEquals("water", testPokemon1.getType1());
        assertEquals("", testPokemon1.getType2());
        assertEquals(5, testPokemon1.getHp());
        assertEquals(8, testPokemon1.getAtk());
        assertEquals(10, testPokemon1.getDef());
        assertEquals(16, testPokemon1.getSpa());
        assertEquals(2, testPokemon1.getSpd());
        assertEquals(3, testPokemon1.getSpe());
    }

    @Test
    public void toStringTest() {
        String expected1
                = "test | Type: water | HP: 5 | Attack: 8 | Defense: 10 | Sp. Attack: 16 | Sp. Def: 2 | Speed: 3";
        assertEquals(expected1, testPokemon1.toString());
        String expected2
                = "test2 | Type: one/two | HP: 5 | Attack: 6 | Defense: 7 | Sp. Attack: 8 | Sp. Def: 9 | Speed: 10";
        assertEquals(expected2, testPokemon2.toString());
    }
}