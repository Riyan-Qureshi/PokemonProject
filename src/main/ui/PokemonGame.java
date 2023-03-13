package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the pokemon game application
public class PokemonGame {
    private static final String JSON_STORAGE = "./data/pokemonGameSaveState.json";
    private Trainer player;
    private Challenger rival;
    private GeneratePokemonPC availablePokemonStorage;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PokemonGame() {
        runGame();
    }

    // EFFECTS: Runs the pokemon game application
    private void runGame() {
        try {
            initialize();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application due to missing file");
        }
        gameStartMenu();
    }

    // REQUIRES: loadGame() requires game to have already been saved at some prior point
    // EFFECTS: Displays a list of options to the user
    private void gameStartMenu() {
        System.out.println("Choose an option: ");
        System.out.println("\tType Anything -> New Game");
        System.out.println("\tl -> Load Game");

        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("l")) {
            loadGame();
        } else {
            createMyTrainer();
            choosePokemonForParty();
            System.out.println("\nNow that you have your party assembled, let's get to battling!");
            introduceRival();
        }

        initiateBattleSequence();
    }

    // MODIFIES: this
    // EFFECTS: Initializes scanner and Pokemon storage
    private void initialize() throws FileNotFoundException {
        this.availablePokemonStorage = new GeneratePokemonPC();
        this.input = new Scanner(System.in);
        this.jsonWriter = new JsonWriter(JSON_STORAGE);
        this.jsonReader = new JsonReader(JSON_STORAGE);
    }

    // MODIFIES: this
    // EFFECTS: Prompts user to provide details about themselves to create their personal Trainer
    private void createMyTrainer() {
        System.out.println("\nWelcome to the Pokemon Battle Dome!");
        String name = null;
        String choseName = "n";
        String answer;

        while (choseName.equals("n")) {
            System.out.println("\nWhat is your name? Type here: ");
            name = input.next();
            System.out.println("\nSo your name is " + name + "?");
            System.out.println("\ty -> YES");
            System.out.println("\tn -> NO");
            answer = input.next();
            answer = answer.toLowerCase();

            if (!answer.equals("n") && !answer.equals("y")) {
                System.out.println("\nAnswer is not valid, please rename your character.");
            } else {
                choseName = answer;
            }
        }

        player = new Trainer(name);
        System.out.println("\nIt's nice to meet you " + name + "!");
    }

    // REQUIRES: selectedPokemonSlotNum input must be integer
    // EFFECTS: Prompts user to choose up to 6 Pokemon to add to their Party from list of available Pokemon
    private void choosePokemonForParty() {
        int selectedPokemonSlotNum;
        boolean addedToParty;
        int partySize = player.getParty().getPartySize();
        int maxPartySize = player.getParty().getMaxPartySize();

        System.out.println("\nLet's get you battle ready!");
        System.out.println("\nBelow is a list of all the Pokemon you can choose to add to your Pokemon party.");
        listAllPokemon();
        System.out.println("\nYou can add up to 6 Pokemon from this list to your battle party.");

        while (partySize < maxPartySize) {
            System.out.println("\nChoose a Pokemon to add to your party: ");
            selectedPokemonSlotNum = input.nextInt();
            addedToParty = addPokemonToParty(selectedPokemonSlotNum);

            if (addedToParty) {
                partySize = player.getParty().getPartySize();
                if (!keepAddingPokemon(partySize, maxPartySize)) {
                    break;
                }

            } else {
                System.out.println("\nNumber selected is invalid..." + "\n");
                listAllPokemon();
            }
        }
    }

    // EFFECTS: Saves the trainer details to game file
    private void saveGame() {
        System.out.println("Would you like to save and quit the game?");
        System.out.println("\ty -> YES");
        System.out.println("\tn -> NO");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            try {
                jsonWriter.open();
                jsonWriter.writeAll(player, rival);
                //jsonWriter.write(player);
                //jsonWriter.write(rival);
                jsonWriter.close();
                System.out.println(player.getName() + " successfully saved the game!");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file stored at: " + JSON_STORAGE);
            }
        }
    }

    // REQUIRES: loadGame() requires game to have already been saved at some prior point
    // MODIFIES: this
    // EFFECTS: loads all characters last saved state from game file
    private void loadGame() {
        try {
            player = jsonReader.readTrainer();
            rival = jsonReader.readChallenger();

            System.out.println(player.getName() + " successfully loaded previous game state!");
        } catch (IOException e) {
            System.out.println("Unable to readTrainer file stored at: " + JSON_STORAGE);
        }
    }

    // EFFECTS: Prompts user to choose whether they would like to add more Pokemon to their party
    private boolean keepAddingPokemon(int partySize, int maxPartySize) {
        String command;
        boolean keepAdding = false;
        boolean validSelection = false;

        while (!validSelection && partySize < maxPartySize) {
            System.out.println("Continue adding more Pokemon to your party?");
            System.out.println("\ty -> YES");
            System.out.println("\tn -> NO");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("y")) {
                keepAdding = true;
                listAllPokemon();
                validSelection = true;
            } else if (command.equals("n")) {
                validSelection = true;
            } else {
                System.out.println("\nSelection is not valid, please select a valid response.");
            }
        }

        return keepAdding;
    }

    // MODIFIES: this
    // EFFECTS: If user selects valid slot then adds Pokemon to party
    private boolean addPokemonToParty(int selectedPokemonSlotNum) {
        Pokemon selectedPokemon;
        boolean validSlot = processSelectedSlotNum(selectedPokemonSlotNum);
        boolean successfullyAdded;

        if (validSlot) {
            selectedPokemon = availablePokemonStorage.getPokemon(selectedPokemonSlotNum - 1);
            player.getParty().addMember(selectedPokemon);
            successfullyAdded = true;
        } else {
            successfullyAdded = false;
        }

        return successfullyAdded;
    }

    // EFFECTS: Displays a numerical list of all the available Pokemon in storage
    private void listAllPokemon() {
        int pokemonStorageSlotNum = 0;
        String previewAllPokemonString = availablePokemonStorage.displayAllPokemon();
        String[] previewAllPokemonList = previewAllPokemonString.split(",");

        for (String pokemonName : previewAllPokemonList) {
            pokemonStorageSlotNum++;
            System.out.println("\t" + pokemonStorageSlotNum + ". " + previewAllPokemonList[pokemonStorageSlotNum - 1]);
        }
    }

    // EFFECTS: Checks if chosen Pokemon slot number is a valid slot number
    private boolean processSelectedSlotNum(int selectedSlotNum) {
        int totalAvailablePokemon = availablePokemonStorage.getStorage().size();
        boolean validSlot;

        if (selectedSlotNum > totalAvailablePokemon || selectedSlotNum <= 0) {
            validSlot = false;
        } else {
            validSlot = true;
        }

        return validSlot;
    }

    // EFFECTS: Creates and introduces a rival for the user to challenge
    private void introduceRival() {
        rival = new Challenger("Gary", player.getParty().getPartySize());
        System.out.println("\nYour first challenger is your childhood rival, " + rival.getName() + "!");
        System.out.println("\nGet ready to battle " + rival.getName() + "!");
        saveGame();
    }

    // EFFECTS: Starts the battle sequence of the game where user battles rival
    private void initiateBattleSequence() {
        boolean validInput = false;
        String rivalName = rival.getName();

        System.out.println("\n*Insert Epic Transition*");
        System.out.println("\n" + rivalName + ": " + player.getName() + ", you challenged the wrong guy!");

        Pokemon currentRivalPokemon = rival.getParty().getPartyMember(0);
        System.out.println("\n" + rivalName + " sent out " + currentRivalPokemon.getName() + "!");

        Pokemon userCurrentPokemon = player.getParty().getPartyMember(0);
        System.out.println("\nYou sent out " + userCurrentPokemon.getName() + "!");

        while (!validInput) {
            System.out.println("\nWhat will you do?");
            System.out.println("\nu -> Use a move");
            System.out.println("\nv -> View party");
            System.out.println("\nSelect an option:");

            String battleCommand = input.next();
            battleCommand = battleCommand.toLowerCase();

            validInput = processBattleCommand(battleCommand, userCurrentPokemon);
        }
    }

    // EFFECTS: Displays a numerical list of all the current Pokemon's moves
    private void listAllMoves(Pokemon pokemon) {
        int pokemonMoveSlotNum = 0;
        String previewAllMovesString = pokemon.previewMoves();
        String[] previewAllMovesList = previewAllMovesString.split(",");

        for (String move : previewAllMovesList) {
            pokemonMoveSlotNum++;
            System.out.println("\t" + pokemonMoveSlotNum + ". " + previewAllMovesList[pokemonMoveSlotNum - 1]);
        }
    }

    // EFFECTS: Processes battle command entered by user
    private boolean processBattleCommand(String battleCommand, Pokemon currentPokemon) {
        boolean validCommand = false;

        while (!validCommand) {
            if (battleCommand.equals("u")) {
                validCommand = true;
                System.out.println("\nSelect a move:");
                listAllMoves(currentPokemon);

                int chosenMoveSlotNum = input.nextInt();
                Move move = currentPokemon.getMove(chosenMoveSlotNum - 1);
                System.out.println("\n" + currentPokemon.getName() + " used " + move.getMoveName() + "!");
            } else if (battleCommand.equals("v")) {
                validCommand = true;
                System.out.println("Your Party:");
                listAllPartyMembers();

            } else {
                System.out.println("Invalid input...");
                break;
            }
        }

        return validCommand;
    }

    // EFFECTS: Displays a numerical list of all the current Pokemon's moves
    private void listAllPartyMembers() {
        int pokemonPartySlotNum = 0;
        String previewAllPartyNamesString = player.viewParty();
        String[] previewAllPartyNamesList = previewAllPartyNamesString.split(",");

        for (String pokemonName : previewAllPartyNamesList) {
            pokemonPartySlotNum++;
            System.out.println("\t" + pokemonPartySlotNum + ". " + previewAllPartyNamesList[pokemonPartySlotNum - 1]);
        }
    }
}
