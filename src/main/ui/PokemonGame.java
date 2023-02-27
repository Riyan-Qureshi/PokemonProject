package ui;

import model.*;

import java.util.Scanner;

public class PokemonGame {
    private Trainer player;
    private Challenger rival;
    private GeneratePokemonPC availablePokemonStorage;
    private Scanner input;

    public PokemonGame() {
        runGame();
    }

    private void runGame() {
        initialize();
        createMyTrainer();
        choosePokemonForParty();
        System.out.println("\nNow that you have your party assembled, let's get to battling!");
        introduceRival();
        initiateBattleSequence();
    }

    // MODIFIES: this
    // EFFECTS: Initializes scanner and Pokemon storage
    private void initialize() {
        this.availablePokemonStorage = new GeneratePokemonPC();
        this.input = new Scanner(System.in);
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

    // EFFECTS: Prompts user to choose whether they would like to add more Pokemon to their party
    public boolean keepAddingPokemon(int partySize, int maxPartySize) {
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
    public void introduceRival() {
        rival = new Challenger("Gary", player.getParty().getPartySize());
        System.out.println("\nYour first challenger is your childhood rival, " + rival.getName() + "!");
        System.out.println("\nGet ready to battle " + rival.getName() + "!");
    }

    // EFFECTS: Starts the battle sequence of the game where user battles rival
    public void initiateBattleSequence() {
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
