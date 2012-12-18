package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/** Represents the main menu for the game. From here, we can do
 *  things such as quit, start a new game, etc.
 *
 *  @author Daniel Neel */
public class Menu {

    /** Create an instance of this menu to start the game */
    public static void main(String[] args) {
        new Menu();
    }

    // TODO: See if any/all of this can be made into a loop rather than repeating everything.
    // TODO Use a simpler layout than gridbaglayout. No need for anything that complex.
    private JFrame mainMenu = new JFrame("Ideopoly | Main menu");  // Initialized here so we can access it via event listeners.

    /** Construct the GUI and make it visible. */
    public Menu() {
        // Create the gui
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box box = Box.createVerticalBox();
        mainMenu.getContentPane().add(box);

        // TODO: Try to loop this? [Very small gains WRT less LOC.]
        JLabel logo = new JLabel(new ImageIcon("res/images/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(logo);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.dispose(); // Get rid of the menu since we don't need it anymore.
                new CharacterSelect();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Goodbye.");
                System.exit(0);
            }
        });

        for (JButton button : Arrays.asList(startButton, quitButton)) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            box.add(button);
        }

        mainMenu.pack();
        mainMenu.setVisible(true);
    }
}
