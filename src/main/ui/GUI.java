package ui;

import model.Challenger;
import model.GeneratePokemonPC;
import model.Pokemon;
import model.Trainer;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// A graphical user interface for the Pokemon game
public class GUI extends JFrame {
    private static final String JSON_STORAGE = "./data/pokemonGameGUISaveState.json";
    private Trainer player;
    private Challenger rival;
    private GeneratePokemonPC availablePokemonStorage;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private PartySelectPanel partySelectPanel;
    private MenuPanel menuPanel;
    private PartyListPanel partyListPanel;
    private ImageIcon icon = new ImageIcon("./data/smallPokeball.png");

    public GUI() {
        super("Pokemon Game");
        try {
            initialize();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application due to missing file");
        }
        createMyTrainer();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        partySelectPanel = new PartySelectPanel();
        menuPanel = new MenuPanel();
        partyListPanel = new PartyListPanel(player.viewParty());
        add(partySelectPanel);
        setUpAllButtonListeners();
        pack();
        setVisible(true);
    }

    // EFFECTS: Handles press of any button displayed on GUI
    private void setUpAllButtonListeners() {
        setUpPartySelectButtonListener();
        setUpPartyMenuButtonListener();
        setUpPartyListButtonListener();
    }

    // MODIFIES: this
    // EFFECTS: Handles all buttons contained in partyListPanel
    private void setUpPartyListButtonListener() {
        ActionListener partyListButtonListener = new ActionListener() {
            List<JButton> allButtons = partyListPanel.getAllButtons();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == allButtons.get(0)) {
                    executeMenuSequence();
                } else if (e.getSource() == allButtons.get(1)) {
                    System.out.println("Alternate");
                } else {
                    partyListPanel = new PartyListPanel(reverseParty());
                    setUpPartyListButtonListener();
                    getContentPane().removeAll();
                    repaint();
                    add(partyListPanel);
                    pack();
                }
            }
        };

        setUpPartyViewButtons(partyListButtonListener);
    }

    // EFFECTS: Reverses order of player's party
    private String reverseParty() {
        String[] pokemonNamesList = player.viewParty().split(",");
        List<String> reverseList = new ArrayList<>();
        int count = pokemonNamesList.length - 1;
        for (int i = 0; i < pokemonNamesList.length; i++) {
            reverseList.add(pokemonNamesList[count]);
            count--;
        }

        String reverseListString = reverseList.stream().map(Object::toString).collect(Collectors.joining(","));

        return reverseListString;
    }

    // MODIFIES: this
    // EFFECTS: Handles all buttons contained in menuPanel
    private void setUpPartyMenuButtonListener() {
        ActionListener menuButtonListener = new ActionListener() {
            List<JButton> allOptions = menuPanel.getAllOptions();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == allOptions.get(0)) {
                    saveGame();
                    JOptionPane.showMessageDialog(null, "Successfully saved game!",
                            "Save Prompt", JOptionPane.PLAIN_MESSAGE, icon);
                } else if (e.getSource() == allOptions.get(1)) {
                    loadGame();
                    partyListPanel = new PartyListPanel(player.viewParty());
                    setUpPartyListButtonListener();
                    JOptionPane.showMessageDialog(null, "Successfully loaded game!",
                            "Save Prompt", JOptionPane.PLAIN_MESSAGE, icon);
                } else {
                    getContentPane().removeAll();
                    repaint();
                    add(partyListPanel);
                    pack();
                }
            }
        };

        setUpMenuButtons(menuButtonListener);
    }

    // MODIFIES: this
    // EFFECTS: Handles all buttons contained in partySelect
    private void setUpPartySelectButtonListener() {
        ActionListener partyButtonListener = new ActionListener() {
            int partySize = 0;
            int maxPartySize = player.getParty().getMaxPartySize();
            int partyRemain = maxPartySize;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (((JButton) e.getSource()).getText().equals("Finished Selection")) {
                    rival = new Challenger("Gary", player.getParty().getPartySize());
                    executeMenuSequence();
                } else if (partySize < (maxPartySize - 1)) {
                    int pokemonSlotNum = Integer.parseInt(e.getActionCommand());
                    Pokemon selectedPokemon = availablePokemonStorage.getPokemon(pokemonSlotNum);
                    player.getParty().addMember(selectedPokemon);
                    partySize = player.getParty().getPartySize();
                    partyRemain--;
                    partySelectPanel.updateCurrentParty(selectedPokemon.getName(), partyRemain);
                } else {
                    rival = new Challenger("Gary", player.getParty().getPartySize());
                    executeMenuSequence();
                }
            }
        };

        setUpPartyButtons(partyButtonListener);
    }

    // EFFECTS: Transitions to and displays the menu screen
    private void executeMenuSequence() {
        getContentPane().removeAll();
        repaint();
        add(menuPanel);
        pack();
        partyListPanel = new PartyListPanel(player.viewParty());
        setUpPartyListButtonListener();
    }


    // EFFECTS: Adds listener to each button pokemon button in partyPanel
    private void setUpPartyButtons(ActionListener actionListener) {
        int i = 0;
        for (JButton button : partySelectPanel.getAllButtons()) {
            button.addActionListener(actionListener);
            button.setActionCommand(Integer.toString(i));
            i++;
        }
    }

    // EFFECTS: Adds listener to each button in menuPanel
    private void setUpMenuButtons(ActionListener actionListener) {
        for (JButton button : menuPanel.getAllOptions()) {
            button.addActionListener(actionListener);
        }
    }

    // EFFECTS: Adds listener to each button in partyListPanel
    private void setUpPartyViewButtons(ActionListener actionListener) {
        for (JButton button : partyListPanel.getAllButtons()) {
            button.addActionListener(actionListener);
        }
    }

    // EFFECTS: Saves the trainer details to game file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.writeAll(player, rival);
            jsonWriter.close();
            System.out.println(player.getName() + " successfully saved the game!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file stored at: " + JSON_STORAGE);
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

    // MODIFIES: this
    // EFFECTS: Prompts user to provide details about themselves to create their personal Trainer
    private void createMyTrainer() {
        String name = JOptionPane.showInputDialog("What is your name?");
        player = new Trainer(name);
        System.out.println("\nIt's nice to meet you " + name + "!");
    }

    // MODIFIES: this
    // EFFECTS: Initializes scanner and Pokemon storage
    private void initialize() throws FileNotFoundException {
        this.availablePokemonStorage = new GeneratePokemonPC();
        this.jsonWriter = new JsonWriter(JSON_STORAGE);
        this.jsonReader = new JsonReader(JSON_STORAGE);
    }
}
