package ui;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//Custom Pokemon creator
public class PokemonEditor {
    private PokemonList list;
    private Scanner scanner;

    private static final ArrayList<String> ATTRIBUTES
            = new ArrayList<>(Arrays.asList("name", "type1", "type2", "hp", "atk", "def", "spa", "spd", "spe"));

    //EFFECTS: runs the editor
    public PokemonEditor() {
        runEditor();
    }

    //MODIFIES: this
    //EFFECTS: carries out user instructions
    private void runEditor() {
        // code from TellerApp
        boolean keepGoing = true;
        String command;

        setup();

        while (keepGoing) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Gotta catch 'em all!");
    }

    //EFFECTS: displays user menu
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tc -> Create a new Pokemon");
        System.out.println("\tv -> View your list of Pokemon");
        System.out.println("\te -> Edit an existing Pokemon");
        System.out.println("\td -> Delete a Pokemon");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: initializes list
    private void setup() {
        list = new PokemonList();
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
    }

    //MODIFIES: this
    //EFFECTS: processes given command
    private void processCommand(String command) {
        switch (command) {
            case "c":
                create();
                break;
            case "v":
                view();
                break;
            case "e":
                edit();
                break;
            case "d":
                delete();
                break;
            default:
                System.out.println("Please enter a valid input.");
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: creates Pokemon
    private void create() {
        System.out.println("Name? (Must be unique)");
        String name = scanner.next();
        while (list.containsName(name)) {
            System.out.println(name + " already exists. Please choose a different name.");
            name = scanner.next();
        }
        System.out.println("Type 1?");
        String type1 = scanner.next();
        System.out.println("Type 2? (Press enter for none)");
        String type2 = scanner.next();
        System.out.println("HP");
        int hp = promptInt();
        System.out.println("Attack?");
        int atk = promptInt();
        System.out.println("Defense?");
        int def = promptInt();
        System.out.println("Special Attack?");
        int spa = promptInt();
        System.out.println("Special Defense?");
        int spd = promptInt();
        System.out.println("Speed?");
        int spe = promptInt();
        list.add(new Pokemon(name, type1, type2, hp, atk, def, spa, spd, spe));
    }

    //EFFECTS: displays Pokemon collection
    private void view() {
        if (list.getSize() > 0) {
            System.out.println(list);
        } else {
            System.out.println("You have no Pokemon.");
        }
    }

    //MODIFIES: this
    //EFFECTS: edits Pokemon
    private void edit() {
        if (list.getSize() > 0) {
            System.out.println("Which Pokemon would you like to edit:");
            Pokemon select = selectPokemon();
            if (select != null) {
                System.out.println("Which attribute? (one of: name, type1, type2, hp, atk, def, spa, spd, spe)");
                String attribute = scanner.next();
                while (!ATTRIBUTES.contains(attribute)) {
                    System.out.println("Please enter a valid attribute.");
                    attribute = scanner.next();
                }
                editAttribute(select, attribute);
                System.out.println("Successfully edited " + select.getName() + ".");
            }
        } else {
            System.out.println("You have no Pokemon.");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes Pokemon from list
    private void delete() {
        if (list.getSize() > 0) {
            System.out.println("Which Pokemon would you like to delete?");
            Pokemon select = selectPokemon();
            list.remove(select.getName());
            System.out.println("Deleted " + select.getName() + ".");
        } else {
            System.out.println("You have no Pokemon.");
        }
    }

    //MODIFIES: select, this
    //EFFECTS: changes selected Pokemon's attribute
    private void editAttribute(Pokemon select, String attribute) {
        System.out.println("Change to: ");
        if (attribute.equals("name")) {
            select.setName(scanner.next());
        } else if (attribute.equals("type1")) {
            select.setType1(scanner.next());
        } else if (attribute.equals("type2")) {
            select.setType2(scanner.next());
        } else if (attribute.equals("hp")) {
            select.setHp(promptInt());
        } else if (attribute.equals("atk")) {
            select.setAtk(promptInt());
        } else if (attribute.equals("def")) {
            select.setDef(promptInt());
        } else if (attribute.equals("spa")) {
            select.setSpa(promptInt());
        } else if (attribute.equals("spd")) {
            select.setSpd(promptInt());
        } else if (attribute.equals("spe")) {
            select.setSpe(promptInt());
        } else {
            System.out.println("Invalid attribute.\n");
        }
    }

    //EFFECTS: returns Pokemon selected
    private Pokemon selectPokemon() {
        System.out.println(list.getNames());
        String select = scanner.next();
        while (!list.containsName(select)) {
            System.out.println(select + " was not found in your collection.\n");
            select = scanner.next();
        }
        return list.get(select);
    }

    //EFFECTS: returns user's integer input
    private int promptInt() {
        String input = scanner.next();
        if (!validPosInt(input)) {
            return promptInt();
        }
        return Integer.parseInt(input);
    }

    //EFFECTS: returns true if given string is a properly formatted positive integer
    private boolean validPosInt(String string) {
        try {
            if (Integer.parseInt(string) > 0) {
                return true;
            }
            System.out.println("Please enter a positive number.");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return false;
        }
    }
}
