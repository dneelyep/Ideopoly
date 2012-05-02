import junit.framework.TestCase;
import org.junit.*;

/** Class to test all methods inside the Menu class.
 *
 *  @author Daniel Neel */
public class MenuTester extends TestCase {
    public void MenuTester() {
    }
    //     public Menu() {
    //     public void actionPerformed(ActionEvent e) {



    // /** Represents the main menu for the game. From here, we can do
    //     things such as quit, start a new game, etc. */
    // public class Menu implements ActionListener {

    //     /** Create an instance of this menu to start the game */
    //     public static void main(String[] args) {
    // 	Menu game = new Menu();
    //     }

    //     JFrame mainMenu = new JFrame("Ideopoly | Main menu");  // Initialized here so we can access it via event listeners.

    //     /** Construct the GUI and make it visible. */
    //     public Menu() {
    //  	// Create the gui
    // 	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 	Box box = Box.createVerticalBox();
    //  	mainMenu.getContentPane().add(box);


    // 	JLabel logo = new JLabel(new ImageIcon("images/resizedLogo.png"));//  icon);
    // 	logo.setAlignmentX(Component.CENTER_ALIGNMENT);
    // 	box.add(logo);

    //  	JButton startButton = new JButton("Start");
    // 	startButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
    // 	startButton.addActionListener(this);
    // 	box.add(startBbutton);

    //  	JButton quitButton = new JButton("Quit"); // TODO: Better names for these
    // 	quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    // 	quitButton.addActionListener(this);
    // 	box.add(quitButton);
	
    // 	mainMenu.pack();
    // 	mainMenu.setVisible(true);
    //     }

    //     public void actionPerformed(ActionEvent e) {
    // 	// Launch the game

    // 	String eventSource = e.getActionCommand();

    // 	if (eventSource == "Start") {
    // 	    mainMenu.dispose(); // Close out the menu since we don't need it anymore.
    // 	    CharacterSelect charSelectScreen = new CharacterSelect();
    // 	}
    // 	else if (eventSource == "Quit") {
    // 	    mainMenu.dispose();
    // 	    System.out.println("Goodbye.");
    // 	}
    //     }
    // }
}