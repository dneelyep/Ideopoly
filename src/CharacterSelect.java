import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/** Class to represent the character select screen, after the user has decided
 *  to start a game.
 *
 *  @author Daniel Neel */
public class CharacterSelect implements ActionListener {

    /** This string represents the character currently selected.
     *  It's used to set the player's token in the main game. */
    private String selectedPlayer = "null";

    /** Buttons to represent the various characters players can pick. */
    // TODO: Make the stallman photo a jpeg, or all png, or whatever for consistency.
    //       Currently, I run into errors when the image is a jpeg.
    private JButton stallmanButton  = new JButton(new ImageIcon("images/Richard_Stallman.png"));
    private JButton thatcherButton  = new JButton(new ImageIcon("images/Margaret_Thatcher.jpg"));
    private JButton marxButton      = new JButton(new ImageIcon("images/Karl_Marx.jpg"));
    private JButton gandhiButton    = new JButton(new ImageIcon("images/Mahatma_Gandhi.jpg"));
    private JButton kropotkinButton = new JButton(new ImageIcon("images/Peter_Kropotkin.jpg"));
    private JButton bismarckButton  = new JButton(new ImageIcon("images/Otto_von_Bismarck.jpg"));
    // LEFTOFFHERE: Added these images to CharacterSelect. Now I need to fix the part where, after
    // running the program, I get "You picked" rather than "You picked Richard Stallman".

    /** An array full of the token buttons. */
    // TODO: Move this to the constructor?
    private JButton[] tokenButtons = {stallmanButton, thatcherButton, marxButton, gandhiButton, kropotkinButton, bismarckButton};

    /** Button to continue to the actual game. */
    private JButton continueButton = new JButton("Continue");

    /** Button to go back to the main menu. */
    private JButton backButton = new JButton("Back");

    /** Represents the frame. */
    private JFrame  frame = new JFrame("Ideopoly | Character select");

    // TODO: Review other constructors - should their stuff be fields?
    public CharacterSelect() {
	// TODO: Should this be set above? Outside the constructor that is.
	Container pane       = frame.getContentPane();
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	continueButton.setEnabled(false); // Disable at first, since a character hasn't been picked.

	// Make all character token buttons listen for actions.
	for (JButton button : tokenButtons) {
	    button.addActionListener(this);
	}

	// Set roll-over icons for each character.
	stallmanButton.setRolloverIcon(new ImageIcon("images/Richard_Stallman_rollover.png"));
	thatcherButton.setRolloverIcon(new ImageIcon("images/Margaret_Thatcher_rollover.png"));
	marxButton.setRolloverIcon(new ImageIcon("images/Karl_Marx_rollover.png"));
	gandhiButton.setRolloverIcon(new ImageIcon("images/Mahatma_Gandhi_rollover.png"));
	kropotkinButton.setRolloverIcon(new ImageIcon("images/Peter_Kropotkin_rollover.png"));
	bismarckButton.setRolloverIcon(new ImageIcon("images/Otto_von_Bismarck_rollover.png"));

	// And set icons for when a character is selected.
	stallmanButton.setSelectedIcon(new ImageIcon("images/Richard_Stallman_selected.png"));
	thatcherButton.setSelectedIcon(new ImageIcon("images/Margaret_Thatcher_selected.png"));
	marxButton.setSelectedIcon(new ImageIcon("images/Karl_Marx_selected.png"));
	gandhiButton.setSelectedIcon(new ImageIcon("images/Mahatma_Gandhi_selected.png"));
	kropotkinButton.setSelectedIcon(new ImageIcon("images/Peter_Kropotkin_selected.png"));
	bismarckButton.setSelectedIcon(new ImageIcon("images/Otto_von_Bismarck_selected.png"));

	continueButton.addActionListener(this);
	backButton.addActionListener(this);

	c.fill = GridBagConstraints.HORIZONTAL;

	JLabel chooseCharacter = new JLabel(new ImageIcon("images/chooseLeader.png"));

	// Add all buttons to the character select screen.
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 3;
	pane.add(chooseCharacter, c);
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

	frame.pack();
	frame.setVisible(true);
    }

    /** Set the player's token to the clicked character.
     *  If they click Continue, start a new game. If they
     *  click Back, head back to the main menu. */
    public void actionPerformed(ActionEvent e) {
	String actionSource = e.getActionCommand();
	continueButton.setEnabled(true);

	if (actionSource == "Continue") {
	    new IdeopolyGUI(selectedPlayer);
	    frame.dispose();
	}
	else if (actionSource == "Back") {
	    new Menu();
	    frame.dispose();
	}
	else
	    selectedPlayer = actionSource;

	JButton src = (JButton) e.getSource();

	// Deselect all buttons, then select only the selected button.
	stallmanButton.setSelected(false);
	thatcherButton.setSelected(false);
	marxButton.setSelected(false);
	gandhiButton.setSelected(false);
	kropotkinButton.setSelected(false);
	bismarckButton.setSelected(false);

	if (src.getIcon() == stallmanButton.getIcon())
	    stallmanButton.setSelected(true);
	else if (src.getIcon() == thatcherButton.getIcon())
	    thatcherButton.setSelected(true);
	else if (src.getIcon() == marxButton.getIcon())
	    marxButton.setSelected(true);
	else if (src.getIcon() == gandhiButton.getIcon())
	    gandhiButton.setSelected(true);
	else if (src.getIcon() == kropotkinButton.getIcon())
	    kropotkinButton.setSelected(true);
	else if (src.getIcon() == bismarckButton.getIcon())
	    bismarckButton.setSelected(true);
    }
}
