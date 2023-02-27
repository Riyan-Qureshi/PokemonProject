package model;

import java.util.List;
import java.util.stream.Collectors;

// Represents a Pokemon Trainer with a name and party of Pokemon
public class Trainer {
    private final String name;
    private Party myParty;

    // EFFECTS: Creates a Trainer with a given name and an empty party of Pokemon
    public Trainer(String name) {
        this.name = name;
        this.myParty = new Party();
    }

    public Party getParty() {
        return this.myParty;
    }

    public String getName() {
        return this.name;
    }

    // EFFECTS: Returns the names of all Pokemon in myParty as a comma-separated string
    public String viewParty() {
        List<Pokemon> myParty = this.myParty.getParty();
        List<String> allPokemonNamesStrings = myParty.stream()
                .map(Pokemon::getName)
                .collect(Collectors.toList());
        String allPokemonNames = String.join(", ", allPokemonNamesStrings);

        return allPokemonNames;
    }
}
