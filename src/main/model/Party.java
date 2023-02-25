package model;
import java.util.ArrayList;
import java.util.List;

// Represents a list of Pokemon available to use during battle
public class Party {
    private List<Pokemon> party;

    // EFFECTS: Creates a party list that can hold up to 6 Pokemon
    public Party(){
        this.party = new ArrayList<>();
    }

    // REQUIRES: this.party > 0
    // EFFECTS: Retrieves specified Pokemon
    public Pokemon getPartyMember(int slotNum){
        return this.party.get(slotNum);
    }

    // REQUIRES: this.party.size() <= 6
    // MODIFIES: this
    // EFFECTS: Adds given Pokemon to party
    public void addMember(Pokemon pokemon){
        if(this.party.size() <= 6){
            this.party.add(pokemon);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes specified Pokemon from party
    public void removeMember(Pokemon pokemon){

    }
}
