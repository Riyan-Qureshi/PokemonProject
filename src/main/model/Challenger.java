package model;

import java.util.Random;

// Represents a Trainer that has a specified sized party of randomized Pokemon
public class Challenger extends Trainer {
    private final GeneratePokemonPC availablePokemonStorage;
    private final int partySize;

    // REQUIRES: partySize > 0
    // MODIFIES: this
    // EFFECTS: Creates a Challenger with a name and random Pokemon filled party of size partySize
    public Challenger(String name, int partySize) {
        super(name);
        availablePokemonStorage = new GeneratePokemonPC();
        this.partySize = partySize;
        createRandomParty();
    }

    // MODIFIES: this
    // EFFECTS: Creates a challenger with a name
    public Challenger(String name){
        super(name);
        availablePokemonStorage = new GeneratePokemonPC();
        this.partySize = getParty().getPartySize();
    }

    // EFFECTS: Add random Pokemon from availablePokemonStorage to challenger's party until party has partySize number
    //          of Pokemon in the party
    private void createRandomParty() {
        Random generateStorageSlotNum = new Random();
        final int minSlotNum = 0;
        final int maxSlotNum = availablePokemonStorage.getStorage().size();
        int randomStorageSlotNum;
        Pokemon randomPokemon;

        for (int i = 0; i < this.partySize; i++) {
            randomStorageSlotNum = generateStorageSlotNum.nextInt(maxSlotNum - minSlotNum) + minSlotNum;
            randomPokemon = availablePokemonStorage.getPokemon(randomStorageSlotNum);
            getParty().addMember(randomPokemon);
        }
    }

    public int getPartySize() {
        return this.partySize;
    }

}
