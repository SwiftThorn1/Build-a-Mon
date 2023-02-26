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
    public void setNameTest() {
        testPokemon1.setName("test2");
        assertEquals("test2", testPokemon1.getName());
    }

    @Test
    public void setType1Test() {
        testPokemon1.setType1("fire");
        assertEquals("fire", testPokemon1.getType1());
    }
    @Test
    public void setType2Test() {
        testPokemon1.setType2("water");
        assertEquals("water", testPokemon1.getType2());
    }

    @Test
    public void setHpTest() {
        testPokemon1.setHp(50);
        assertEquals(50, testPokemon1.getHp());
    }

    @Test
    public void setAtkTest() {
        testPokemon1.setAtk(60);
        assertEquals(60, testPokemon1.getAtk());
    }

    @Test
    public void setDefTest() {
        testPokemon1.setDef(70);
        assertEquals(70, testPokemon1.getDef());
    }

    @Test
    public void setSpaTest() {
        testPokemon1.setSpa(80);
        assertEquals(80, testPokemon1.getSpa());
    }

    @Test
    public void setSpdTest() {
        testPokemon1.setSpd(90);
        assertEquals(90, testPokemon1.getSpd());
    }

    @Test
    public void setSpeTest() {
        testPokemon1.setSpe(100);
        assertEquals(100, testPokemon1.getSpe());
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