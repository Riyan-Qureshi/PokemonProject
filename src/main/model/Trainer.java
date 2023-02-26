package model;

// Represents a Trainer with a name and party of Pokemon
public class Trainer {
    private String name;
    private Party myParty;

    // EFFECTS: Creates a Trainer with a given name and an empty party of Pokemon
    public Trainer(String name){
        this.name = name;
        this.myParty = new Party();
    }

    public Party getParty(){
        return this.myParty;
    }
}
