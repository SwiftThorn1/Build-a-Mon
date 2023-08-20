package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents a list of Pokemon with unique names
public class PokemonList implements Writable {
    private ArrayList<Pokemon> pokemons;

    public PokemonList() {
        pokemons = new ArrayList<>();
    }

    //REQUIRES: list does not already contain pokemon
    //MODIFIES: this
    //EFFECTS: adds pokemon to the list
    public void add(Pokemon pokemon) {
        pokemons.add(pokemon);
        EventLog.getInstance().logEvent(new Event("Added Pokemon: " + pokemon.getName()));
    }

    //MODIFIES: this
    //EFFECTS: removes Pokemon with given name from list, if it is present
    public void remove(String name) {
        pokemons.remove(get(name));
        EventLog.getInstance().logEvent(new Event("Removed Pokemon: " + name));
    }

    //EFFECTS: returns Pokemon with given name, or null if not found
    public Pokemon get(String name) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        return null;
    }

    //EFFECTS: returns true if list contains Pokemon of given name
    public boolean containsName(String name) {
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return pokemons.size();
    }

    //REQUIRES: list not empty
    //EFFECTS: returns names of Pokemon in list
    public String getNames() {
        String result = "";
        for (int i = 0; i < getSize() - 1; i++) {
            result += pokemons.get(i).getName() + "\n";
        }
        result += pokemons.get(getSize() - 1).getName();
        return result;
    }

    //EFFECTS: returns names and stats of Pokemon
    public String toString() {
        String result = "";
        for (int i = 0; i < getSize() - 1; i++) {
            result += pokemons.get(i) + "\n";
        }
        result += pokemons.get(getSize() - 1);
        return result;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pokemons", pokemonsToJson());
        return json;
    }

    // EFFECTS: returns pokemons in list as a JSON array
    private JSONArray pokemonsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon p : pokemons) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
