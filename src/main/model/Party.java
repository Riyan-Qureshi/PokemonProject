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

    public List<Pokemon> getParty(){
        return this.party;
    }

    // REQUIRES: this.party.size() > 0 && 0 <= slotNum < this.party.size()
    // EFFECTS: Retrieves specified Pokemon
    public Pokemon getPartyMember(int slotNum){
        return this.party.get(slotNum);
    }

    // MODIFIES: this
    // EFFECTS: Adds given Pokemon to a party for up to a maximum of 6 Pokemon
    public void addMember(Pokemon pokemon){
        if(this.party.size() < 6){
            this.party.add(pokemon);
        }
    }
}
