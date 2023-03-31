package ui;

import model.GeneratePokemonPC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Graphical user interface for pokemon game party selection screen
public class PartySelectPanel extends JPanel {
    private JLabel label;
    private JLabel currentParty;
    private JLabel remainingPokemon;
    protected List<JButton> allPartyButtons;
    private List<String> currentPartyList = new ArrayList<>();

    public PartySelectPanel() {
        label = new JLabel("Choose a Pokemon to add to your party: ");
        remainingPokemon = new JLabel("Remaining Pokemon: 6");
        currentParty = new JLabel("Current Party: ");
        add(label);
        add(remainingPokemon);
        add(currentParty);
        partyButtons();

        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(0, 1));
    }

    // EFFECTS: Creates buttons for all pokemon that can be selected
    private void partyButtons() {
        GeneratePokemonPC availablePokemonStorage = new GeneratePokemonPC();
        allPartyButtons = new ArrayList<>();

        int pokemonStorageSlotNum = 0;
        String previewAllPokemonString = availablePokemonStorage.displayAllPokemon();
        String[] previewAllPokemonList = previewAllPokemonString.split(",");

        for (String pokemonName : previewAllPokemonList) {
            JButton button1 = new JButton(previewAllPokemonList[pokemonStorageSlotNum]);
            add(button1);
            pokemonStorageSlotNum++;
            allPartyButtons.add(button1);
        }

        JButton finishAdding = new JButton("Finished Selection");
        add(finishAdding);
        allPartyButtons.add(finishAdding);
    }

    public List<JButton> getAllButtons() {
        return allPartyButtons;
    }

    // EFFECTS: Updates the current party label text
    public void updateCurrentParty(String newPartyMember, int remaining) {
        currentPartyList.add(newPartyMember);
        String allPartyNames = String.join(", ", currentPartyList);
        currentParty.setText("Current Party: " + allPartyNames);
        remainingPokemon.setText("Remaining Pokemon: " + remaining);
    }
}
