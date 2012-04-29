import junit.framework.TestCase;
import java.awt.*;
import javax.swing.*;
import org.junit.*;

// TODO: Add all test cases from my notes.
/**
 * IdeopolyTester - various test cases to make sure the game works as intended.
 */
public class IdeopolyTester extends TestCase {

    /** Test all methods in the BoardCell class. */ 
   @Test
    public void testBoardCell() {
	BoardCell testCell = new BoardCell("name", new ImageIcon("images/red_template.jpg"), 4, 10, "left");
	Player    aPlayer  = new Player(1);

	// LEFTOFFHERE: Implmenting tests here. Make sure the order of arguments is correct on assertEquals.
	// Test basic, not out-of-the-ordinary cases.
	assertEquals(testCell.getName(), "name");
	assertEquals(testCell.getOwner(), null);

	testCell.setOwner(aPlayer);
	assertEquals(testCell.getOwner(), aPlayer);

	assertEquals(testCell.getX(), 4);
	assertEquals(testCell.getY(), 10);
	assertEquals(testCell.getOrientation(), "left");

// TODO: What should these return? This screams for a better design, since I think they're just dummy methods.
	//	assertEquals(testCell.getRent(), );
	//	assertEquals(testCell.getCost(), )


	// Icon getImage()
	// void setImage(Icon new_image)

	// TODO: Then more complicated, out of the ordinary situations. For 
	// example, test for odd values to the constructor.
    }

	// ================
	// TODO: IdeopolyGUI.java
	// ================

	// ==================
	// BoardPosition.java
	// ==================
    /** Test all methods in the BoardPosition class. */
    @Test
    public void testBoardPosition() {
	BoardPosition testBoardPosition = new BoardPosition(0, 0);
	assertEquals(testBoardPosition.getXCoord(), 0);
	assertEquals(testBoardPosition.getYCoord(), 0);
	//	assertEquals(testBoardPosition.getImage(), ); <-- TODO

	Icon testImage = new ImageIcon("images/p1_present.jpg");
	testBoardPosition.setImage(testImage);
	assertEquals(testBoardPosition.getImage(), testImage);
    }

    // =============
    // CashCell.java
    // =============
    /** Test all methods in the CashCell class.*/
    @Test
    public void testCashCell() {
	CashCell testCashCell = new CashCell(5, 10, "testingtext");
	assertEquals(testCashCell.getXCoord(), 5);
	assertEquals(testCashCell.getYCoord(), 10);

	CashCell testCashCell2 = new CashCell(-1, -2, "testingtext");
	assertEquals(testCashCell2.getXCoord(), null);
	assertEquals(testCashCell2.getYCoord(), null);
	assertEquals(testCashCell2.getText(),   null);
	CashCell testCashCell3 = new CashCell(0, 0,   "testingtext");
	assertEquals(testCashCell3.getXCoord(), 0);
	assertEquals(testCashCell3.getYCoord(), 0);
	assertEquals(testCashCell3.getText(),   "testingtext");
    }

    // =========
    // Menu.java
    // =========
    //     public Menu() {
    //     public void actionPerformed(ActionEvent e) {



    // /** Represents the main menu for the game. From here, we can do
    //     things such as quit, start a new game, etc. */
    // public class Menu implements ActionListener {

    //     /** Create an instance of this menu to start the game */
    //     public static void main(String[] args) {
    // 	Menu game = new Menu();
    //     }

    //     JFrame main_menu = new JFrame("Ideopoly | Main menu");  // Initialized here so we can access it via event listeners.

    //     /** Construct the GUI and make it visible. */
    //     public Menu() {
    //  	// Create the gui
    // 	main_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 	Box box = Box.createVerticalBox();
    //  	main_menu.getContentPane().add(box);


    // 	JLabel logo = new JLabel(new ImageIcon("images/resized_logo.png"));//  icon);
    // 	logo.setAlignmentX(Component.CENTER_ALIGNMENT);
    // 	box.add(logo);

    //  	JButton start_button = new JButton("Start");
    // 	start_button.setAlignmentX(Component.CENTER_ALIGNMENT); 
    // 	start_button.addActionListener(this);
    // 	box.add(start_button);

    //  	JButton quit_button = new JButton("Quit"); // TODO: Better names for these
    // 	quit_button.setAlignmentX(Component.CENTER_ALIGNMENT);
    // 	quit_button.addActionListener(this);
    // 	box.add(quit_button);
	
    // 	main_menu.pack();
    // 	main_menu.setVisible(true);
    //     }

    //     public void actionPerformed(ActionEvent e) {
    // 	// Launch the game

    // 	String event_source = e.getActionCommand();

    // 	if (event_source == "Start") {
    // 	    main_menu.dispose(); // Close out the menu since we don't need it anymore.
    // 	    CharacterSelect char_select_screen = new CharacterSelect();
    // 	}
    // 	else if (event_source == "Quit") {
    // 	    main_menu.dispose();
    // 	    System.out.println("Goodbye.");
    // 	}
    //     }
    // }

    /** Test all methods in the Chance class. */
    @Test
    public void testChance() {
	//1-16
	Chance testChanceCard = new Chance(1);
	assertEquals(testChanceCard.getType(), 1);
	assertEquals(testChanceCard.getText(), "Advance to Go (Collect $200)");

	testChanceCard = new Chance(2);
	assertEquals(testChanceCard.getType(), 2);
	assertEquals(testChanceCard.getText(), "Advance to Illinois Ave - if you pass Go, collect $200");

	testChanceCard = new Chance(3);
	assertEquals(testChanceCard.getType(), 3);
	assertEquals(testChanceCard.getText(), "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.");

	testChanceCard = new Chance(4);
	assertEquals(testChanceCard.getType(), 4);
	assertEquals(testChanceCard.getText(), "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)");

	testChanceCard = new Chance(5);
	assertEquals(testChanceCard.getType(), 5);
	assertEquals(testChanceCard.getText(), "Advance to St. Charles Place – if you pass Go, collect $200");

	testChanceCard = new Chance(6);
	assertEquals(testChanceCard.getType(), 6);
	assertEquals(testChanceCard.getText(), "Bank pays you dividend of $50");

	testChanceCard = new Chance(7);
	assertEquals(testChanceCard.getType(), 7);
	assertEquals(testChanceCard.getText(), "Get out of Jail Free – this card may be kept until needed, or traded/sold");

	testChanceCard = new Chance(8);
	assertEquals(testChanceCard.getType(), 8);
	assertEquals(testChanceCard.getText(), "Go back 3 spaces");

	testChanceCard = new Chance(9);
	assertEquals(testChanceCard.getType(), 9);
	assertEquals(testChanceCard.getText(), "Go directly to Jail – do not pass Go, do not collect $200");

	testChanceCard = new Chance(10);
	assertEquals(testChanceCard.getType(), 10);
	assertEquals(testChanceCard.getText(), "Make general repairs on all your property – for each house pay $25 – for each hotel $100");

	testChanceCard = new Chance(11);
	assertEquals(testChanceCard.getType(), 11);
	assertEquals(testChanceCard.getText(), "Pay poor tax of $15");

	testChanceCard = new Chance(12);
	assertEquals(testChanceCard.getType(), 12);
	assertEquals(testChanceCard.getText(), "Take a trip to Reading Railroad – if you pass Go, collect $200");

	testChanceCard = new Chance(13);
	assertEquals(testChanceCard.getType(), 13);
	assertEquals(testChanceCard.getText(), "Take a walk on the Boardwalk – advance token to Boardwalk");

	testChanceCard = new Chance(14);
	assertEquals(testChanceCard.getType(), 14);
	assertEquals(testChanceCard.getText(), "You have been elected chairman of the board – pay each player $50");

	testChanceCard = new Chance(15);
	assertEquals(testChanceCard.getType(), 15);
	assertEquals(testChanceCard.getText(), "Your building loan matures – collect $150");

	testChanceCard = new Chance(16);
	assertEquals(testChanceCard.getType(), 16);
	assertEquals(testChanceCard.getText(), "You have won a crossword competition - collect $100.");


	// Make sure un-allowed card values don't affect text, etc.
	testChanceCard = new Chance(17);
	assertEquals(testChanceCard.getType(), 17);
	assertEquals(testChanceCard.getText(), null);

	testChanceCard = new Chance(-1);
	assertEquals(testChanceCard.getType(), -1);
	assertEquals(testChanceCard.getText(), null);

	testChanceCard = new Chance(0);
	assertEquals(testChanceCard.getType(), 0);
	assertEquals(testChanceCard.getText(), null);
    }

    // ===========
    // Player.java
    // ===========

    /** Test all methods in the Player class. */
    @Test
    public void testPlayer() {
	// TODO: Test with a non-standard player number.
	Player testPlayer = new Player(1);
	assertEquals(testPlayer.getJailStatus(),            0);
	assertEquals(testPlayer.getCash("ones"),          "5");
	assertEquals(testPlayer.getCash("fives"),         "5");
	assertEquals(testPlayer.getCash("tens"),          "5");
	assertEquals(testPlayer.getCash("twenties"),      "6");
	assertEquals(testPlayer.getCash("fifties"),       "2");
	assertEquals(testPlayer.getCash("hundreds"),      "2");
	assertEquals(testPlayer.getCash("five_hundreds"), "2");
	assertEquals(testPlayer.getCash("total"),      "1500");

	assertEquals(testPlayer.getNumHouses(),          0);
	assertEquals(testPlayer.getNumHotels(),          0);
	assertEquals(testPlayer.getNumGOOJFCards(),      0);

	assertEquals(testPlayer.getName(), "Player 1 (H)");

	assertEquals( (boolean) testPlayer.willBankrupt(1500), false);
	assertEquals( (boolean) testPlayer.willBankrupt(1501), true);
	assertEquals( (boolean) testPlayer.willBankrupt(1409), false);
	assertEquals( (boolean) testPlayer.willBankrupt(0),    false);
	assertEquals( (boolean) testPlayer.willBankrupt(-1),   false);

	testPlayer.giveGOOJF();
	assertEquals(testPlayer.getNumGOOJFCards(), 1);
	testPlayer.spendGOOJF();
	assertEquals(testPlayer.getNumGOOJFCards(), 0);
	testPlayer.spendGOOJF(); // Make sure we can't get a negative # of cards.
	assertEquals(testPlayer.getNumGOOJFCards(), 0);

	testPlayer.setInJail(3);
	assertEquals(testPlayer.getJailStatus(), 3);
	testPlayer.setInJail(2);
	assertEquals(testPlayer.getJailStatus(), 2);
	testPlayer.setInJail(1);	
	assertEquals(testPlayer.getJailStatus(), 1);
	testPlayer.setInJail(0);
	assertEquals(testPlayer.getJailStatus(), 0);

	// Test un-allowed cases.
	testPlayer.setInJail(4);
	assertEquals(testPlayer.getJailStatus(), 0);
	testPlayer.setInJail(-1);
	assertEquals(testPlayer.getJailStatus(), 0);
	testPlayer.setInJail('c');
	assertEquals(testPlayer.getJailStatus(), 0);

	// totalPropertiesOwned = 0; // TODO: <-- Test that.

	// image = new ImageIcon("images/p1_present.jpg"); // getImage()

	// public Player(int player_number)
	// public String getCash(String bill_type)
	// public void addProperty(BoardCell property)
	// public void removeProperty(BoardCell property)
	// public void roll()
	// public Icon getImage()
	// public int getPosition()
	// public void setPosition(int p)
	// public void setCash(String t, int a) //TODO: Also test for correct update of the total field her
	// public void giveGetOutOfJailFree()
	// /** Have this player spend one of their get out of jail free cards. */
	// //	    TODO: Test this when the person's not in jail.
	// public int getNumHouses()
	// public int getNumHotels()
	// public void spreadMoney(int numToSwitch)// TODO: LOTS OF TESTS FOR THIS FUNCTION
	// public void bankruptPlayer(IdeopolyGUI gui)
	// public String getName()

	// TODO: ^-- test ALL these.
    }

    // ====================
    // CharacterSelect.java
    // ====================
    // TODO: ^---This


    /** Test all methods in the PropagandaOutlet class. */
    @Test
    public void testPropagandaOutlet() {
	PropagandaOutlet testPropOutlet = new PropagandaOutlet("testPropOutlet", new ImageIcon("images/yellow_template.jpg"), 40, 10, 60, 180, 350, 630, 800, 100, 18, 18, "right");

	assertEquals(testPropOutlet.getName(), "testPropOutlet");
	//	    TODO: Test the image assigned during creation.
	// public Icon getImage() {
	// public void setImage(Icon new_image) {
	assertEquals(testPropOutlet.getX(), 18);
	assertEquals(testPropOutlet.getY(), 18);
	assertEquals(testPropOutlet.getOrientation(), "right");

	// assertEquals(testPropOutlet.getNumHouses(), 0); TODO: Tests/methods for these?
	// assertEquals(testPropOutlet.getNumHotels(), 0);
	assertEquals(testPropOutlet.getCost(), 40);
	// TODO: Test for mortgage value?
	assertEquals(testPropOutlet.getRent(), 10);
	// TODO: Add tests here for when I have 1/2/3/4 houses, and 1 hotel.
	// TODO: All derived methods are tested here in normal cases, except the commented out ones (image, etc.)
	//       Need out of the ordinary cases, though.
	assertEquals(testPropOutlet.getOwner(), null);

	Player testPropOutletPlayer = new Player(2);
	testPropOutlet.setOwner(testPropOutletPlayer);
	assertEquals(testPropOutlet.getOwner(), testPropOutletPlayer);
    }

    /** Test all methods in the CommunityChest class. */
    @Test
    public void testCommunityChest() {
	CommunityChest testCommChestCard = new CommunityChest(1);
	assertEquals(testCommChestCard.getType(), 1);
	assertEquals(testCommChestCard.getText(), "Advance to Go (Collect $200)");

	testCommChestCard = new CommunityChest(2);
	assertEquals(testCommChestCard.getType(), 2);
	assertEquals(testCommChestCard.getText(), "Bank error in your favor – collect $200");

	testCommChestCard = new CommunityChest(3);
	assertEquals(testCommChestCard.getType(), 3);
	assertEquals(testCommChestCard.getText(), "Doctor's fees – Pay $50");

	testCommChestCard = new CommunityChest(4);
	assertEquals(testCommChestCard.getType(), 4);
	assertEquals(testCommChestCard.getText(), "Get Out of Jail Free – this card may be kept until needed, or sold");

	testCommChestCard = new CommunityChest(5);
	assertEquals(testCommChestCard.getType(), 5);
	assertEquals(testCommChestCard.getText(), "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");

	testCommChestCard = new CommunityChest(6);
	assertEquals(testCommChestCard.getType(), 6);
	assertEquals(testCommChestCard.getText(), "It is your birthday - Collect $10 from each player");
 

	testCommChestCard = new CommunityChest(7);
	assertEquals(testCommChestCard.getType(), 7);
	assertEquals(testCommChestCard.getText(), "Grand Opera Night – collect $50 from every player for opening night seats");

	testCommChestCard = new CommunityChest(8);
	assertEquals(testCommChestCard.getType(), 8);
	assertEquals(testCommChestCard.getText(), "Income Tax refund – collect $20");

	testCommChestCard = new CommunityChest(9);
	assertEquals(testCommChestCard.getType(), 9);
	assertEquals(testCommChestCard.getText(), "Life Insurance Matures – collect $100");

	testCommChestCard = new CommunityChest(10);
	assertEquals(testCommChestCard.getType(), 10);
	assertEquals(testCommChestCard.getText(), "Pay Hospital Fees of $100");

	testCommChestCard = new CommunityChest(11);
	assertEquals(testCommChestCard.getType(), 11);
	assertEquals(testCommChestCard.getText(), "Pay School Fees of $50");

	testCommChestCard = new CommunityChest(12);
	assertEquals(testCommChestCard.getType(), 12);
	assertEquals(testCommChestCard.getText(), "Receive $25 Consultancy Fee");

	testCommChestCard = new CommunityChest(13);
	assertEquals(testCommChestCard.getType(), 13);
	assertEquals(testCommChestCard.getText(), "You are assessed for street repairs – $40 per house, $115 per hotel");

	testCommChestCard = new CommunityChest(14);
	assertEquals(testCommChestCard.getType(), 14);
	assertEquals(testCommChestCard.getText(), "You have won second prize in a beauty contest– collect $10");

	testCommChestCard = new CommunityChest(15);
	assertEquals(testCommChestCard.getType(), 15);
	assertEquals(testCommChestCard.getText(), "You inherit $100");

	testCommChestCard = new CommunityChest(16);
	assertEquals(testCommChestCard.getType(), 16);
	assertEquals(testCommChestCard.getText(), "From sale of stock you get $50");

	testCommChestCard = new CommunityChest(17);
	assertEquals(testCommChestCard.getType(), 17);
	assertEquals(testCommChestCard.getText(), "Holiday Fund matures - Receive $100");

	// Make sure un-allowed card values don't affect text, etc.
	testCommChestCard = new CommunityChest(18);
	assertEquals(testCommChestCard.getType(), 18);
	assertEquals(testCommChestCard.getText(), null);

	testCommChestCard = new CommunityChest(-1);
	assertEquals(testCommChestCard.getType(), -1);
	assertEquals(testCommChestCard.getText(), null);

	testCommChestCard = new CommunityChest(0);
	assertEquals(testCommChestCard.getType(), 0);
	assertEquals(testCommChestCard.getText(), null);
    }

    // ==============
    // Railroad.java
    // ==============
    // TODO: ^--- This.


/* Potential tests:
Check that every single cell in the board is initialized, with the correct values etc. IE for a property, that all rent values are correct.

Make sure that a person who passes go and lands on the community chest that then passes them back to go is payed twice accordingly.

Make sure that the user gets moved to the correct space when they move pass go.

Have a player mortgage a property. After they mortgage it, make sure it's removed from their list of owned properties and their number of owned properties is decremented. Also, that property should no longer have an associated owner. Make sure also that this works for each type of property. IE for regular tile, school, elec. company, water works.

Have a player land on chance and community chest. Both times, make sure the top item was taken off the stack, that the second to top item was moved to the top, and that the top item gets put back on the bottom.

Have a player use their get out of jail free card. Make sure the card's returned to the bottom of the stack of either community chest or chance accordingly.

Include tests to make sure this works for community chest and chance.
Have the player land on Income tax. Test cases where the user decides to pay $200, or when they choose to pay 10%.

Have the player land on go to jail. Make sure they're not given $200. Make sure their currently in jail value is set to the correct number of weeks.

Have the player roll doubles in jail. Make sure their current space gets set properly, and that their currently in jail value is not true

Make sure a person in jail can still buy and sell property, houses, hotels, and collect rents.

Make sure a person in the last week of jail who doesn't roll doubles is charged $50.

Make sure a person attempting to buy houses has all houses in a color group. Make sure this works for all color groups.

Make sure the person distributes houses evenly, putting them on unimproved properties owned before improved properties.

Make sure a person can't add more than 4 houses to a property.

Make sure a person can't do eg 1 house on 2 properties in a group and then 3 houses in another property.

Make sure that a person can only buy a hotel if they have each property in a color group, and each of those properties holds 4 houses.

Make sure multiple hotels can't be erected on a property.

Make sure the player can't mortgage an unsellable property (IE Go, free parking, jail, etc.) Check this for all relevant properties.

Check to make sure the mortgage process works correctly. Correct amount of money lost by the player, property's owner is removed, etc.

Make sure a bankrupt person gets removed from the game

Make sure a bankrupt person by the bank loses all their assets properly

Make sure a bankrupt person by another player transfers assets properly. And make sure the receiving player pays tax on the items received. */
}