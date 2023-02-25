package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a storage space for all available created Pokemon
public class GeneratePokemonPC {
    private List<Pokemon> storage;

    // EFFECTS: Generates all available Pokemon and stores them into a storage
    public GeneratePokemonPC(){
        storage = generateAllPokemon();
    }

    // EFFECTS: Creates a list of all generated Pokemon
    private List<Pokemon> generateAllPokemon(){
        List<Pokemon> generatedPokemon = new ArrayList<>();
        Pokemon charizard = new Pokemon("Charizard", Type.FIRE);
        Pokemon blaustoise = new Pokemon("Blaustoise", Type.WATER);
        Pokemon venasaur = new Pokemon("Venasaur", Type.GRASS);
        Pokemon raticate = new Pokemon("Raticate", Type.NORMAL);
        Pokemon vaporeon = new Pokemon("Vaporeon", Type.WATER);
        Pokemon flareon = new Pokemon("Flareon", Type.FIRE);

        Collections.addAll(generatedPokemon, charizard, blaustoise, venasaur, raticate, vaporeon, flareon);

        return generatedPokemon;
    }

    // EFFECTS: Returns the names of all stored Pokemon as a string
    public String displayAllPokemon(){
        String allPokemonNames = "";

        for(Pokemon pokemon : storage){
            allPokemonNames = allPokemonNames + ", " + pokemon.getName();
        }

        return allPokemonNames;
    }
}
