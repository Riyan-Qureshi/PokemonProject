package ui;

import model.*;

import java.util.Scanner;

public class PokemonGame {
    private Trainer player;
    private Trainer enemy;
    private GeneratePokemonPC availablePokemonStorage;
    private Scanner input;

    public PokemonGame(){
        runGame();
    }

    private void runGame(){
        initialize();
        createMyTrainer();
        choosePokemonForParty();
    }

    // MODIFIES: this
    // EFFECTS:
    private void initialize(){
        this.availablePokemonStorage = new GeneratePokemonPC();
        this.input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: Prompts user to provide details about themselves to create their personal Trainer
    private void createMyTrainer(){
        System.out.println("\nWelcome to the Pokemon Battle Dome!");
        String name = null;
        String choseName = "n";
        String answer = null;

        while(choseName.equals("n")){
            System.out.println("\nWhat is your name? Type here: ");
            name = input.next();
            System.out.println("\nSo your name is " + name + "?");
            System.out.println("\ty -> YES");
            System.out.println("\tn -> NO");
            answer = input.next();
            answer = answer.toLowerCase();

            if(!answer.equals("n") && !answer.equals("y")){
                System.out.println("\nAnswer is not valid, please rename your character.");
            } else {
                choseName = answer;
            }
        }

        player = new Trainer(name);
        System.out.println("\nIt's nice to meet you " + name + "!");
    }

    // EFFECTS: Prompts user to choose up to 6 Pokemon to add to their Party from list of available Pokemon
    private void choosePokemonForParty(){
        int partyCount = 0;
        int selectedPokemonSlotNum;
        boolean addedToParty;
        boolean addPokemon = true;

        System.out.println("\nLet's get you battle ready!");
        System.out.println("\nBelow is a list of all the Pokemon you can choose to add to your Pokemon party.");
        listAllPokemon();
        System.out.println("\nYou can add up to 6 Pokemon from this list to your battle party.");

        while(partyCount < 6 && addPokemon){
            System.out.println("\nChoose a Pokemon to add to your party: ");
            selectedPokemonSlotNum = input.nextInt();
            addedToParty = addPokemonToParty(selectedPokemonSlotNum);

            if(addedToParty){
                partyCount++;
                if(!keepAddingPokemon()){
                    break;
                }


            } else {
                System.out.println("\nNumber selected is invalid...");
                listAllPokemon();
            }

        }
    }

    // EFFECTS: Prompts user to choose whether they would like to add more Pokemon to their party
    public boolean keepAddingPokemon(){
        String command;
        boolean keepAdding = false;
        boolean validSelection = false;

        while(!validSelection){
            System.out.println("Continue adding more Pokemon to your party?");
            System.out.println("\ty -> YES");
            System.out.println("\tn -> NO");

            command = input.next();
            command = command.toLowerCase();

            if(command.equals("y")){
                keepAdding = true;
                listAllPokemon();
                validSelection = true;
            } else if(command.equals("n")) {
                keepAdding = false;
                validSelection = true;
            } else {
                System.out.println("\nSelection is not valid, please select a valid response.");
            }
        }

        return keepAdding;
    }

    // MODIFIES: this
    // EFFECTS:
    private boolean addPokemonToParty(int selectedPokemonSlotNum){
        Pokemon selectedPokemon;
        boolean validSlot = processSelectedSlotNum(selectedPokemonSlotNum);
        boolean successfullyAdded;

        if(validSlot) {
            selectedPokemon = availablePokemonStorage.getPokemon(selectedPokemonSlotNum - 1);
            player.getParty().addMember(selectedPokemon);
            successfullyAdded = true;
        } else {
            successfullyAdded = false;
        }

        return successfullyAdded;
    }

    // EFFECTS: Creates a numerical list of all the available Pokemon in storage
    private void listAllPokemon(){
        int pokemonStorageSlotNum = 0;
        String previewAllPokemonString = availablePokemonStorage.displayAllPokemon();
        String[] previewAllPokemonList = previewAllPokemonString.split(",");

        System.out.println("\n");
        for(String pokemonName : previewAllPokemonList){
            pokemonStorageSlotNum++;
            System.out.println("\t" + pokemonStorageSlotNum + ". " + previewAllPokemonList[pokemonStorageSlotNum - 1]);
        }
    }

    // EFFECTS: Checks if chosen Pokemon slot number is a valid slot number
    private boolean processSelectedSlotNum(int selectedSlotNum){
        int totalAvailablePokemon = availablePokemonStorage.getStorage().size();
        boolean validSlot;

        if(selectedSlotNum > totalAvailablePokemon || selectedSlotNum <= 0){
            validSlot = false;
        } else {
            validSlot = true;
        }

        return validSlot;
    }

}
