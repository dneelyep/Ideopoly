package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/** Class to represent the character select screen, after the user has decided
 *  to start a game.
 *
 *  @author Daniel Neel */
public class CharacterSelect extends JFrame implements ActionListener {

    // TODO These buttons might be better modeled by a set
    /** This string represents the character currently selected.
     *  It's used to set the player's token in the main game. */
    private String selectedPlayer = "null";

    // TODO: Consider changing Thatcher to Reagan.
    /** An array full of the token buttons. */
    private ArrayList<JButton> tokenButtons = new ArrayList<>(6);

    /** Button to continue to the actual game. */
    private final JButton continueButton = new JButton("Continue");

    /** Button to go back to the main menu. */
    private final JButton backButton = new JButton("Back");

    // TODO: Review other constructors - should their stuff be fields?
    /** Create a new CharacterSelect with the provided title. */
    public CharacterSelect(String title) {
        super(title);
        final Container pane = this.getContentPane();
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        continueButton.setEnabled(false); // Disable at first, since a character hasn't been picked.

        for (String string : Arrays.asList("Richard_Stallman", "Margaret_Thatcher", "Karl_Marx", "Mahatma_Gandhi", "Peter_Kropotkin", "Otto_von_Bismarck")) {
            tokenButtons.add(new JButton(new ImageIcon("src/com/ideopoly/game/images/" + string + ".jpg", string)));
        }

        // Make all character token buttons listen for actions.
        for (JButton button : tokenButtons) {
            button.addActionListener(this);
        }

        continueButton.addActionListener(this);
        backButton.addActionListener(this);

        // Set various icons for each character.
        for (JButton button: tokenButtons) {
            ImageIcon img = (ImageIcon) button.getIcon();
            button.setRolloverIcon(new ImageIcon("src/com/ideopoly/game/images/" + img.getDescription() + "_rollover.png"));
            button.setSelectedIcon(new ImageIcon("src/com/ideopoly/game/images/" + img.getDescription() + "_selected.png"));
        }

        c.fill = GridBagConstraints.HORIZONTAL;

        // Add all buttons to the character select screen.
        c.gridwidth = 3;
        addAt(new JLabel(new ImageIcon("src/com/ideopoly/game/images/chooseLeader.png")), 0, 0, c);

        c.gridwidth = 1;
        c.gridy = 1;

        for (JButton button : tokenButtons) {
            pane.add(button, c);
            if (((ImageIcon) button.getIcon()).getDescription().equals("Karl_Marx")) {
                c.gridx = 0;
                c.gridy++;
            }
            else {
                c.gridx++;
            }
        }

        addAt(backButton, 1, 3, c);
        addAt(continueButton, 2, 3, c);

        this.pack();
        this.setVisible(true);
    }

    /** Set the player's token to the clicked character.
     *  If they click Continue, start a new game. If they
     *  click Back, head back to the main menu. */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        continueButton.setEnabled(true);

        if (e.getActionCommand().equals("Continue")) {
            new GameBoard(selectedPlayer);
            this.dispose();
        }
        else if (e.getActionCommand().equals("Back")) {
            new Menu();
            this.dispose();
        }
        else {
            ImageIcon sourceIcon = (ImageIcon) sourceButton.getIcon();
            selectedPlayer = sourceIcon.getDescription();
        }

        // Deselect all buttons, then select only the selected button.
        for (JButton button : tokenButtons)
            button.setSelected(false);

        sourceButton.setSelected(true);
    }

    /** Add a given JComponent component to this CharacterSelect at coordinates (x, y). */
    private void addAt(JComponent component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}