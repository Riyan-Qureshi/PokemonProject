package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


// Represents a Pokemon having health points, a name, type, and a set of four moves
public class Pokemon {
    private int healthPoints;
    private final String name;
    private final Type type;
    private List<Move> moves;

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
        List<String> allMovesStrings = this.moves.stream()
                .map(Move::getMoveName)
                .collect(Collectors.toList());
        String allMoves = String.join(", ", allMovesStrings);

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
    // EFFECTS: Adds move to list of moves up to a maximum of 4 moves
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
