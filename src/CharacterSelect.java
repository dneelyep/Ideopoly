import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Class to represent the character select screen, after the user has decided
 *  to start a game.
 *
 *  @author Daniel Neel */
public class CharacterSelect implements ActionListener {

    /** This string represents the character currently selected.
     *  It's used to set the player's token in the main game. */
    private String selectedPlayer = "null";

    /** Buttons to represent the various characters players can pick. */
    // TODO: Represent these buttons as sexy pictures that highlight when selected.
    private JButton stallmanButton = new JButton("Richard Stallman");
    private JButton thatcherButton = new JButton("Margaret Thatcher");
    private JButton randButton     = new JButton("Ayn Rand");
    private JButton gandhiButton   = new JButton("Mahatma Gandhi");
    private JButton hitlerButton   = new JButton("Adolf Hitler");
    private JButton bismarckButton = new JButton("Otto von Bismarck");

    /** An array full of the token buttons. */
    // TODO: Move this to the constructor?
    private JButton[] tokenButtons = {stallmanButton, thatcherButton, randButton, gandhiButton, hitlerButton, bismarckButton};

    /** Button to continue to the actual game. */
    private JButton continueButton = new JButton("Continue");

    /** Button to go back to the main menu. */
    private JButton backButton = new JButton("Back");

    /** Represents the frame. */
    private JFrame  frame = new JFrame("Ideopoly | Character select");

    // TODO: Review other constructors - should their stuff be fields?
    public CharacterSelect() {
	// TODO: Should this be set above? Outside the constructor that is.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Container pane       = frame.getContentPane();
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	continueButton.setEnabled(false); // Disable at first, since a character hasn't been picked.

	// Make all character token buttons listen for actions.
	for (JButton button : tokenButtons) {
	    button.addActionListener(this);
	}

	continueButton.addActionListener(this);
	backButton.addActionListener(this);

	c.fill = GridBagConstraints.HORIZONTAL;

	// TODO: Center this text across the row. And make it sexy artwork rather than bland text.
	JLabel chooseCharacter = new JLabel("Choose your character below:");

	// Add all buttons to the character select screen.
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = 3;
	pane.add(chooseCharacter, c);
	c.gridwidth = 1;

	c.gridy = 1;

	for (JButton button : tokenButtons) {
	    pane.add(button, c);
	    if (button == randButton) {
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
	    IdeopolyGUI mainGame = new IdeopolyGUI(selectedPlayer);
	    frame.dispose();
	}
	else if (actionSource == "Back") {
	    Menu game = new Menu();
	    frame.dispose();
	}
	else {
	    selectedPlayer = actionSource;
	}
    }
}