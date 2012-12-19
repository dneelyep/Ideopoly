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
public class CharacterSelect extends JFrame {

    // TODO These buttons might be better modeled by a set
    /** This string represents the character currently selected.
     *  It's used to set the player's token in the main game. */
    private String selectedPlayer = "null";

    // TODO: Consider changing Thatcher to Reagan.
    /** An array full of the token buttons. */
    private ArrayList<JButton> tokenButtons = new ArrayList<>(6);

    /** Button to continue to the actual game. */
    private final JButton continueButton = new JButton("Continue");

    // TODO: Review other constructors - should their stuff be fields?
    /** Create a new CharacterSelect with the provided title. */
    public CharacterSelect() {
        super("Ideopoly | Character select");
        final Container pane = this.getContentPane();
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        continueButton.setEnabled(false); // Disable at first, since a character hasn't been picked.

        for (String string : Arrays.asList("Richard_Stallman", "Margaret_Thatcher", "Karl_Marx", "Mahatma_Gandhi",
                "Peter_Kropotkin", "Otto_von_Bismarck")) {
            JButton button = new JButton(new ImageIcon("res/images/" + string + ".jpg", string));
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            tokenButtons.add(button);//new JButton(new ImageIcon("res/images/" + string + ".jpg", string)));
        }

        // Make all character token buttons listen for actions.
        for (JButton button : tokenButtons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    continueButton.setEnabled(true);

                    JButton sourceButton = (JButton) e.getSource();
                    ImageIcon sourceIcon = (ImageIcon) sourceButton.getIcon();

                    selectedPlayer = sourceIcon.getDescription();

                    // Deselect all buttons, except the source button.
                    for (JButton button : tokenButtons)
                        button.setSelected(button == sourceButton);
                }
            });
        }

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameBoard(selectedPlayer);
                SwingUtilities.windowForComponent((JButton) e.getSource()).dispose();
            }
        });

        final JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Menu();
                SwingUtilities.windowForComponent((JButton) e.getSource()).dispose();
            }
        });

        // Set various icons for each character.
        for (JButton button: tokenButtons) {
            ImageIcon img = (ImageIcon) button.getIcon();
            button.setRolloverIcon(new ImageIcon("res/images/" + img.getDescription() + "_rollover.png"));
            button.setSelectedIcon(new ImageIcon("res/images/" + img.getDescription() + "_selected.png"));
        }

        c.fill = GridBagConstraints.HORIZONTAL;

        // Add all buttons to the character select screen.
        c.gridwidth = 3;
        addAt(new JLabel(new ImageIcon("res/images/chooseLeader.png")), 0, 0, c);

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

    /** Add a given JComponent component to this CharacterSelect at coordinates (x, y). */
    private void addAt(JComponent component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}