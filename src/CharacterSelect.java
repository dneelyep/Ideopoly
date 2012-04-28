import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Class to represent the character select screen, after the user has decided
 *  to start a game. */
public class CharacterSelect implements ActionListener {

    /** This string represents the character the player currently has selected.
     *  It's used to set the player's token in the main game. */
    private String selected_player = "null";

    /** Buttons to represent the various characters players can pick. */
    // TODO: Make these buttons pictures instead, that highlight when selected.
    private JButton     stallmanButton = new JButton("Richard Stallman");
    private JButton	thatcherButton = new JButton("Margaret Thatcher");
    private JButton	randButton	= new JButton("Ayn Rand");
    private JButton	gandhiButton	= new JButton("Mahatma Gandhi");
    private JButton	hitlerButton	= new JButton("Adolf Hitler");
    private JButton	bismarckButton = new JButton("Otto von Bismarck");

    /** Button to continue to the actual game. */
    private JButton continueButton = new JButton("Continue");

    /** Button to go back to the main menu. */
    private JButton backButton = new JButton("Back");

    /** Represents the frame. */
    private JFrame  frame = new JFrame("Ideopoly | Character select");

    // TODO: Review other constructors - should their stuff be fields?
    public CharacterSelect() {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Container pane = frame.getContentPane();
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	// Add items to the frame and set everything up.

	continueButton.setEnabled(false); // Disable the button at first, since the user hasn't yet picked a character.

	// Make all the buttons listen for actions.
	stallmanButton.addActionListener(this);
	thatcherButton.addActionListener(this);
	randButton.addActionListener(this);
	gandhiButton.addActionListener(this);
	hitlerButton.addActionListener(this);
	bismarckButton.addActionListener(this);

	continueButton.addActionListener(this);
	backButton.addActionListener(this);

	// TODO: Loop this.
	c.fill = GridBagConstraints.HORIZONTAL;

	JLabel choose_character = new JLabel("Choose your character below:"); // TODO: Center this text across the row
	// TODO: Also, make it sexy artwork rather than bland text.

	// =======================================================
	// === Add all buttons to the character select screen. ===
	// =======================================================
	// TODO: Put buttons in an array and loop through
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 3;
	pane.add(choose_character, c);
	c.gridwidth = 1;

	c.gridx = 0;
	c.gridy = 1;

	pane.add(stallmanButton, c);
	c.gridx++;
	pane.add(thatcherButton, c);
	c.gridx++;
	pane.add(randButton, c);

	c.gridx = 0;
	c.gridy++;

	pane.add(gandhiButton, c);
	c.gridx++;
	pane.add(hitlerButton, c);
	c.gridx++;
	pane.add(bismarckButton, c);

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
	String action_source = e.getActionCommand();
	continueButton.setEnabled(true);

	if (action_source == "Continue") {
	    IdeopolyGUI main_game = new IdeopolyGUI(selected_player);
	    frame.dispose();
	}
	else if (action_source == "Back") {
	    Menu game = new Menu();
	    frame.dispose();
	}
	else {
	    selected_player = action_source;
	}
    }
}