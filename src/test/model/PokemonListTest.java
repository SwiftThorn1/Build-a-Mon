package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonListTest {
    private PokemonList testPokemonList;
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    @BeforeEach
    public void setup() {
        testPokemonList = new PokemonList();
        pokemon1 = new Pokemon("pokemon", "fire", "", 4, 5, 6, 7, 2, 4);
        pokemon2 = new Pokemon("test", "flying", "", 10, 2, 5, 7, 8, 9);
    }

    @Test
    public void constructorTest() {
        assertEquals(0, testPokemonList.getSize());
    }

    @Test
    public void addTest() {
        testPokemonList.add(pokemon1);
        assertEquals(1, testPokemonList.getSize());
        testPokemonList.add(pokemon2);
        assertEquals(2, testPokemonList.getSize());
    }

    @Test
    public void removeFoundTest() {
        testPokemonList.add(pokemon1);
        assertEquals(1, testPokemonList.getSize());
        testPokemonList.remove(pokemon1.getName());
        assertEquals(0, testPokemonList.getSize());
    }

    @Test
    public void removeNotFoundTest() {
        testPokemonList.add(pokemon1);
        assertEquals(1, testPokemonList.getSize());
        testPokemonList.remove(pokemon2.getName());
        assertEquals(1, testPokemonList.getSize());
    }

    @Test
    public void getTest() {
        testPokemonList.add(pokemon1);
        assertEquals(pokemon1, testPokemonList.get(pokemon1.getName()));
        assertEquals(null, testPokemonList.get(pokemon2.getName()));
    }

    @Test
    public void containsNameTest() {
        testPokemonList.add(pokemon1);
        assertTrue(testPokemonList.containsName(pokemon1.getName()));
        assertFalse(testPokemonList.containsName(pokemon2.getName()));
    }

    @Test
    public void getNamesTest() {
        testPokemonList.add(pokemon1);
        testPokemonList.add(pokemon2);
        String expected = pokemon1.getName() + "\n" + pokemon2.getName();
        assertEquals(expected, testPokemonList.getNames());
    }

    @Test
    public void toStringTest() {
        testPokemonList.add(pokemon1);
        testPokemonList.add(pokemon2);
        String expected = pokemon1.toString() + "\n" + pokemon2.toString();
        assertEquals(expected, testPokemonList.toString());
    }
}
