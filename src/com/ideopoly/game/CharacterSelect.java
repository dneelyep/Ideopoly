package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Class to represent the character select screen, after the user has decided
 *  to start a game.
 *
 *  @author Daniel Neel */
public class CharacterSelect extends JFrame implements ActionListener {

    /** This string represents the character currently selected.
     *  It's used to set the player's token in the main game. */
    private String selectedPlayer = "null";

    /** Buttons to represent the various characters players can pick. */
    // TODO: Make the stallman photo a jpeg, or all png, or whatever for consistency.
    //       Currently, I run into errors when the image is a jpeg.
    // TODO: It would be great if all I had to do here was pass in the description, and derive
    //       the image path from that.
    private final JButton stallmanButton  = new JButton(new ImageIcon("images/Richard_Stallman.png", "Richard_Stallman"));
    // TODO: Consider changing Thatcher to Reagan.
    private final JButton thatcherButton  = new JButton(new ImageIcon("images/Margaret_Thatcher.jpg", "Margaret_Thatcher"));
    private final JButton marxButton      = new JButton(new ImageIcon("images/Karl_Marx.jpg", "Karl_Marx"));
    private final JButton gandhiButton    = new JButton(new ImageIcon("images/Mahatma_Gandhi.jpg", "Mahatma_Gandhi"));
    private final JButton kropotkinButton = new JButton(new ImageIcon("images/Peter_Kropotkin.jpg", "Peter_Kropotkin"));
    private final JButton bismarckButton  = new JButton(new ImageIcon("images/Otto_von_Bismarck.jpg", "Otto_von_Bismarck"));

    /** An array full of the token buttons. */
    // TODO: Move this to the constructor?
    private final JButton[] tokenButtons = {stallmanButton, thatcherButton, marxButton, gandhiButton, kropotkinButton, bismarckButton};

    /** Button to continue to the actual game. */
    private final JButton continueButton = new JButton("Continue");
    /** Button to go back to the main menu. */
    private final JButton backButton = new JButton("Back");

    // TODO: Review other constructors - should their stuff be fields?
    public CharacterSelect() {
	// TODO: Should this be set above? Outside the constructor that is.
	this.setTitle("Ideopoly | Character select");
	final Container pane = this.getContentPane();
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	continueButton.setEnabled(false); // Disable at first, since a character hasn't been picked.

	// Make all character token buttons listen for actions.
	for (JButton button : tokenButtons) {
	    button.addActionListener(this);
	}

	continueButton.addActionListener(this);
	backButton.addActionListener(this);

	// Set various icons for each character.
	for (JButton button: tokenButtons) {
	    ImageIcon img = (ImageIcon) button.getIcon();
	    button.setRolloverIcon(new ImageIcon("images/" + img.getDescription() + "_rollover.png"));
	    button.setSelectedIcon(new ImageIcon("images/" + img.getDescription() + "_selected.png"));
	}

	c.fill = GridBagConstraints.HORIZONTAL;

	// Add all buttons to the character select screen.
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 3;
	pane.add(new JLabel(new ImageIcon("images/chooseLeader.png")), c);
	c.gridwidth = 1;

	c.gridy = 1;

	for (JButton button : tokenButtons) {
	    pane.add(button, c);
	    if (button == marxButton) {
		c.gridx = 0;
		c.gridy++;
	    }
	    else {
		c.gridx++;
	    }
	}

	c.gridx = 1;
	c.gridy = 3;
	pane.add(backButton, c);

	c.gridx = 2;
	pane.add(continueButton, c);

	this.pack();
	this.setVisible(true);
    }

    /** Set the player's token to the clicked character.
     *  If they click Continue, start a new game. If they
     *  click Back, head back to the main menu. */
    // TODO: Add @Override annotation here?
    public void actionPerformed(ActionEvent e) {
	JButton sourceButton = (JButton) e.getSource();
	continueButton.setEnabled(true);

	if (e.getActionCommand() == "Continue") {
	    new GameBoard(selectedPlayer);
	    this.dispose();
	}
	else if (e.getActionCommand() == "Back") {
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
}
