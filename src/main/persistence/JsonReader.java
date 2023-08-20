package persistence;

import model.Pokemon;
import model.PokemonList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads pokemonlist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads pokemonlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PokemonList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePokemonList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses pokemonlist from JSON object and returns it
    private PokemonList parsePokemonList(JSONObject jsonObject) {
        PokemonList pl = new PokemonList();
        addPokemons(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses pokemons from JSON object and adds them to given list
    private void addPokemons(PokemonList pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pokemons");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            addPokemon(pl, nextPokemon);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses pokemon from JSON object and adds it to given list
    private void addPokemon(PokemonList pl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type1 = jsonObject.getString("type1");
        String type2 = jsonObject.getString("type2");
        int hp = jsonObject.getInt("hp");
        int atk = jsonObject.getInt("atk");
        int def = jsonObject.getInt("def");
        int spa = jsonObject.getInt("spa");
        int spd = jsonObject.getInt("spd");
        int spe = jsonObject.getInt("spe");
        Pokemon pokemon = new Pokemon(name, type1, type2, hp, atk, def, spa, spd, spe);
        pl.add(pokemon);
    }
}
