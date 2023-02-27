package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Represents a storage space for all available created Pokemon
public class GeneratePokemonPC {
    private List<Pokemon> storage;

    // EFFECTS: Generates all available Pokemon and stores them into a storage
    public GeneratePokemonPC() {
        storage = generateAllPokemon();
    }

    // EFFECTS: Creates a list of all generated Pokemon
    private List<Pokemon> generateAllPokemon() {
        List<Pokemon> generatedPokemon = new ArrayList<>();
        List<Move> generatedMoves = new ArrayList<>();

        Pokemon charizard = new Pokemon("Charizard", Type.FIRE);
        Pokemon blaustoise = new Pokemon("Blaustoise", Type.WATER);
        Pokemon venasaur = new Pokemon("Venasaur", Type.GRASS);
        Pokemon raticate = new Pokemon("Raticate", Type.NORMAL);
        Pokemon vaporeon = new Pokemon("Vaporeon", Type.WATER);
        Pokemon flareon = new Pokemon("Flareon", Type.FIRE);

        Collections.addAll(generatedPokemon, charizard, blaustoise, venasaur, raticate, vaporeon, flareon);

        Move scratch = new Move("Scratch", 15);
        Move punch = new Move("Punch", 20);
        Move kick = new Move("Kick", 20);
        Move bite = new Move("Bite", 15);

        Collections.addAll(generatedMoves, scratch, punch, kick, bite);

        for (Pokemon pokemon : generatedPokemon) {
            for (Move move : generatedMoves) {
                pokemon.addMove(move);
            }
        }

        return generatedPokemon;
    }

    // EFFECTS: Returns the names of all stored Pokemon as a comma-separated string
    public String displayAllPokemon() {
        List<String> allPokemonNamesStrings = this.storage.stream()
                .map(Pokemon::getName)
                .collect(Collectors.toList());
        String allPokemonNames = String.join(", ", allPokemonNamesStrings);

        return allPokemonNames;
    }

    public List<Pokemon> getStorage() {
        return this.storage;
    }

    // REQUIRES: this.storage.size() > 0 and 0 <= slotNum < this.storage.size()
    // EFFECTS: Returns Pokemon at specified slot number
    public Pokemon getPokemon(int slotNum) {
        return this.storage.get(slotNum);
    }
}
