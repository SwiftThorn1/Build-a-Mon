package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    private Pokemon testPokemon;

    @BeforeEach
    public void setup() {
        testPokemon = new Pokemon("test", "water", "", 5, 8, 10, 16, 2, 3);
    }

    @Test
    public void constructorTest() {
        assertEquals("test", testPokemon.getName());
        assertEquals("water", testPokemon.getType1());
        assertEquals("", testPokemon.getType2());
        assertEquals(5, testPokemon.getHp());
        assertEquals(8, testPokemon.getAtk());
        assertEquals(10, testPokemon.getDef());
        assertEquals(16, testPokemon.getSpa());
        assertEquals(2, testPokemon.getSpd());
        assertEquals(3, testPokemon.getSpe());
    }

    @Test
    public void toStringTest() {
        String expected
                = "test | Type: water | HP: 5 | Attack: 8 | Defense: 10 | Sp. Attack: 16 | Sp. Def: 2 | Speed: 3";
        assertEquals(expected, testPokemon.toString());
    }
}