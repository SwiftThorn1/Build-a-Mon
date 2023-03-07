package persistence;

import model.Pokemon;
import model.PokemonList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// code from JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PokemonList pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPokemonList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPokemonList.json");
        try {
            PokemonList pl = reader.read();
            assertEquals(0, pl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPokemonList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPokemonList.json");
        try {
            PokemonList result = reader.read();
            assertEquals(2, result.getSize());
            checkPokemon("test1", "one", "", 1, 2, 3, 4, 5, 6, result.get("test1"));
            checkPokemon("test2", "two", "three", 2, 3, 4, 5, 6, 7, result.get("test2"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
