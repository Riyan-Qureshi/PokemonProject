package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a Pokemon having health points, a name, type, and a set of four moves
public class Pokemon {
    int healthPoints;
    String name;
    Type type;
    List<Move> moves;

    // REQUIRES: type to be a valid type of Pokemon
    // EFFECTS: Creates a Pokemon with a name, a type and a number of health points
    public Pokemon(String name, Type type){
        setInitialHealthPoints();
        this.name = name;
        this.type = type;
        this.moves = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Generates random number of health points between specified min and max health
    private void setInitialHealthPoints(){
        Random generateHealth = new Random();
        final int minHealth = 140;
        final int maxHealth = 160;

        this.healthPoints = generateHealth.nextInt(maxHealth - minHealth) + minHealth;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }

    // REQUIRES: moves.size() > 0
    // EFFECTS: Returns the names of all the Pokemon's moves as a string
    public String previewMoves(){
        String allMoves = moves.get(0).getMoveName();

        for(int i = 1; i < moves.size(); i++){
            allMoves = allMoves + ", " + moves.get(i).getMoveName();
        }

        return allMoves;
    }

    public List<Move> getMoves(){
        return this.moves;
    }

    // REQUIRES: moves.size() > 0
    // EFFECTS: Returns selected move
    public Move getMove(int moveNum){
        return moves.get(moveNum);
    }

    // MODIFIES: this
    // EFFECTS: Adds move to list of moves as long as moves list contains <= 4 moves
    public void addMove(Move move){
        if(moves.size() < 4){
            this.moves.add(move);
        }
    }

    public String getName(){
        return this.name;
    }

    public Type getType(){
        return this.type;
    }
}
