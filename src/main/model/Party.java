package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of Pokemon available to use during battle
public class Party implements Writable {
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
            EventLog.getInstance().logEvent(new Event("Added Pokemon to a party."));
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

    @Override
    // EFFECTS: returns party as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("party", partyToJson());
        return json;
    }

    // EFFECTS: returns party as a JSON array
    public JSONArray partyToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon pokemon : party) {
            jsonArray.put(pokemon.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: reverses current order of party
    public void reverseParty() {
        List<Pokemon> reversedParty = new ArrayList<>();
        for (int i = this.party.size(); i > 0; i--) {
            reversedParty.add(this.party.get(i - 1));
        }

        this.party = reversedParty;
        EventLog.getInstance().logEvent(new Event("Party order reversed."));
    }
}
