package model;

import java.util.ArrayList;

//Represents a list of Pokemon with unique names
public class PokemonList {
    private ArrayList<Pokemon> pokemons;

    public PokemonList() {
        pokemons = new ArrayList<>();
    }

    //REQUIRES: list does not already contain pokemon
    //MODIFIES: this
    //EFFECTS: adds pokemon to the list
    public void add(Pokemon pokemon) {
        pokemons.add(pokemon);
    }

    //MODIFIES: this
    //EFFECTS: removes Pokemon with given name from list, if it is present
    public void remove(String name) {
        pokemons.remove(get(name));
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

    //REQUIRES: list not empty
    //EFFECTS: returns names and stats of Pokemon
    public String toString() {
        String result = "";
        for (int i = 0; i < getSize() - 1; i++) {
            result += pokemons.get(i) + "\n";
        }
        result += pokemons.get(getSize() - 1);
        return result;
    }
}
