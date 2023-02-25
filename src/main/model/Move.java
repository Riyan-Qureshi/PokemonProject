package model;

// Represents an ability that can be used by a particular Type of Pokemon
public class Move {
    private String moveName;
    private int damage;

    // EFFECTS: Creates a move with a name, and damage number in attack points
    public Move(String moveName, int attackPoints){
        this.moveName = moveName;
        this.damage = attackPoints;
    }

    public String getMoveName(){
        return this.moveName;
    }

    public int getDamage(){
        return this.damage;
    }
}
