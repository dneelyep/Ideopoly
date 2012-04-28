import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Rename images according to camelcase convention.

/** Represents the main menu for the game. From here, we can do
    things such as quit, start a new game, etc. */
public class Menu implements ActionListener {

    /** Create an instance of this menu to start the game */
    public static void main(String[] args) {
	Menu game = new Menu();
    }

    // TODO: Use multiple instances of GridBagConstraints.
    // TODO: See if any/all of this can be made into a loop rather than repeating everything.

    JFrame main_menu = new JFrame("Ideopoly | Main menu");  // Initialized here so we can access it via event listeners.

    /** Construct the GUI and make it visible. */
    public Menu() {
 	// Create the gui
	main_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Box box = Box.createVerticalBox();
 	main_menu.getContentPane().add(box);

	// TODO: Try to loop this?
	// TODO: Clean up the edges on the image, make it look a bit better.
	// TODO: Consider making the background transparent also so it blends with the GUI.
	JLabel logo = new JLabel(new ImageIcon("images/resized_logo.png"));//  icon);
	logo.setAlignmentX(Component.CENTER_ALIGNMENT);
	box.add(logo);

 	JButton start_button = new JButton("Start");
	start_button.setAlignmentX(Component.CENTER_ALIGNMENT); 
	start_button.addActionListener(this);
	box.add(start_button);

 	JButton quit_button = new JButton("Quit"); // TODO: Better names for these
	quit_button.setAlignmentX(Component.CENTER_ALIGNMENT);
	quit_button.addActionListener(this);
	box.add(quit_button);
	
	main_menu.pack();
	main_menu.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
	// Launch the game

	String event_source = e.getActionCommand();

	if (event_source == "Start") {
	    main_menu.dispose(); // Close out the menu since we don't need it anymore.
	    CharacterSelect char_select_screen = new CharacterSelect();
	}
	else if (event_source == "Quit") {
	    main_menu.dispose();
	    System.out.println("Goodbye.");
	}
    }
}