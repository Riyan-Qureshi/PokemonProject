package ui;

import model.Pokemon;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Graphical user interface for pokemon game party list viewing screen
public class PartyListPanel extends JPanel {
    private JList<String> partyList;
    private JLabel titleText;
    private List<JButton> allButtons = new ArrayList<>();

    public PartyListPanel(String pokemonParty) {
        setUpPanel();
        setUpButtons();
        setUpLabels();

        add(titleText);

        String[] pokemonNamesList = pokemonParty.split(",");
        DefaultListModel<String> partyNamesModel = new DefaultListModel<>();

        for (String pokemonName : pokemonNamesList) {
            partyNamesModel.addElement(pokemonName);
        }

        partyList = new JList<>(partyNamesModel);
        partyList.setVisibleRowCount(pokemonNamesList.length);
        partyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(partyList));
        for (JButton button : allButtons) {
            add(button);
        }
        partyList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        executeAction();
                    }
                });
    }

    // EFFECTS: Sets dimensions and layout of panel
    private void setUpPanel() {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setLayout(new GridLayout(3, 2));
    }

    // EFFECTS: Creates all buttons
    private void setUpButtons() {
        JButton back = new JButton("Back");
        JButton showMoves = new JButton("Show Moves");
        JButton shuffle = new JButton("Reverse");
        allButtons.add(back);
        allButtons.add(showMoves);
        allButtons.add(shuffle);
    }

    // EFFECTS: Creates all labels
    private void setUpLabels() {
        titleText = new JLabel("Your Current Party: ");
    }

    public List<JButton> getAllButtons() {
        return allButtons;
    }

    // EFFECTS: When run will execute any line of code assigned to it
    public void executeAction() {

    }
}
