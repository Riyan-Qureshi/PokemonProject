package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an ability that can be used by a particular Type of Pokemon
public class Move implements Writable {
    private final String moveName;
    private final int damage;

    // REQUIRES: attackPoints >= 0
    // EFFECTS: Creates a move with a name, and damage number in attack points
    public Move(String moveName, int attackPoints) {
        this.moveName = moveName;
        this.damage = attackPoints;
    }

    public String getMoveName() {
        return this.moveName;
    }

    public int getDamage() {
        return this.damage;
    }

    @Override
    // EFFECTS: returns move as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("moveName", moveName);
        json.put("damage", damage);
        return json;
    }
}
