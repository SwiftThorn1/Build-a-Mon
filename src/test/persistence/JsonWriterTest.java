package persistence;

import model.Pokemon;
import model.PokemonList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// code from JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            PokemonList pl = new PokemonList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPokemonList() {
        try {
            PokemonList pl = new PokemonList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPokemonList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPokemonList.json");
            pl = reader.read();
            assertEquals(0, pl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPokemonList() {
        try {
            PokemonList pl = new PokemonList();
            Pokemon p1 = new Pokemon("test1", "one", "", 1, 2, 3, 4, 5, 6);
            Pokemon p2 = new Pokemon("test2", "two", "three", 2, 3, 4, 5, 6, 7);
            pl.add(p1);
            pl.add(p2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPokemonList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPokemonList.json");
            PokemonList result = reader.read();
            assertEquals(2, result.getSize());
            checkPokemon("test1", "one", "", 1, 2, 3, 4, 5, 6, result.get("test1"));
            checkPokemon("test2", "two", "three", 2, 3, 4, 5, 6, 7, result.get("test2"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
