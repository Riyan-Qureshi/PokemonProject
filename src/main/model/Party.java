package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of Pokemon available to use during battle
public class Party {
    private static final int MAX_PARTY_SIZE = 6;
    private List<Pokemon> party;

    // EFFECTS: Creates a party list that can hold up to 6 Pokemon
    public Party() {
        this.party = new ArrayList<>();
    }

    public List<Pokemon> getParty() {
        return this.party;
    }

    // REQUIRES: this.party.size() > 0 && 0 <= slotNum < this.party.size()
    // EFFECTS: Retrieves specified Pokemon
    public Pokemon getPartyMember(int slotNum) {
        return this.party.get(slotNum);
    }

    // MODIFIES: this
    // EFFECTS: Adds given Pokemon to a party for up to a maximum of 6 Pokemon
    public void addMember(Pokemon pokemon) {
        if (this.party.size() < MAX_PARTY_SIZE) {
            this.party.add(pokemon);
        }
    }

    // EFFECTS: Return the current size of the party
    public int getPartySize() {
        return this.party.size();
    }

    // EFFECTS: Return the maximum party size
    public int getMaxPartySize() {
        return MAX_PARTY_SIZE;
    }
}
