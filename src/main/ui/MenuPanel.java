package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Graphical user interface for pokemon game party menu screen
public class MenuPanel extends JPanel {
    protected List<JButton> options = new ArrayList<>();

    public MenuPanel() {
        JLabel label = new JLabel("Options: ");
        add(label);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        setLayout(new GridLayout(1, 0));

        JButton saveButton = new JButton("Save Game");
        JButton loadButton = new JButton("Load Game");
        JButton viewParty = new JButton("View Party");

        options.add(saveButton);
        options.add(loadButton);
        options.add(viewParty);

        for (JButton button : options) {
            add(button);
        }
    }

    public List<JButton> getAllOptions() {
        return options;
    }
}
