import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import java.io.*;

/** Represents the main menu for the game. From here, we can do
 *  things such as quit, start a new game, etc.
 *
 *  @author Daniel Neel */
public class Menu implements ActionListener {

    /** Create an instance of this menu to start the game */
    public static void main(String[] args) {
	new Menu();
    }

    // TODO: Use multiple instances of GridBagConstraints.
    // TODO: See if any/all of this can be made into a loop rather than repeating everything.

    JFrame mainMenu = new JFrame("Ideopoly | Main menu");  // Initialized here so we can access it via event listeners.

    /** Construct the GUI and make it visible. */
    public Menu() {
 	// Create the gui
	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Box box = Box.createVerticalBox();
 	mainMenu.getContentPane().add(box);

	// TODO: Try to loop this? [Very small gains WRT less LOC.]
	// TODO: Clean up the edges on the image, make it look a bit better.
	// TODO: Consider making the background transparent also so it blends with the GUI.
	JLabel logo = new JLabel(new ImageIcon("images/logo.png"));
	logo.setAlignmentX(Component.CENTER_ALIGNMENT);
	box.add(logo);

 	JButton startButton = new JButton("Start");
	startButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
	startButton.addActionListener(this);
	box.add(startButton);

 	JButton quitButton = new JButton("Quit");
	quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	quitButton.addActionListener(this);
	box.add(quitButton);
	
	mainMenu.pack();
	mainMenu.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
	String eventSource = e.getActionCommand();

	if (eventSource == "Start") { // Launch the game
	    // TODO: Or should I just make the object invisible and keep it around?
	    mainMenu.dispose(); // Get rid of the menu since we don't need it anymore.
	    new CharacterSelect();
	}
	else if (eventSource == "Quit") {
	    mainMenu.dispose();
	    System.out.println("Goodbye.");
	}
    }
}
