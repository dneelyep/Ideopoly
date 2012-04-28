import junit.framework.TestCase;
import java.awt.*;
import javax.swing.*;
import org.junit.*;
/**
 * IdeopolyTester - various test cases to make sure the game works as intended.
 */
public class IdeopolyTester extends TestCase {
    // TODO: Add all test cases (and more!) from my notes.
    //    System.out.println("Hello."); // TODO: This is just here to remove make docs warnings.


    // LEFTOFFHERE: Before I go any further in the code, implementing tests for each function in the game.

    /** Test all methods in the BoardCell class. */
    @Test
    public void testBoardCell() {
	// BoardCell.java

	// /** Creates a BoardCell object, with the specified name, image, and coordinates.
	//  *  Does not have an owner. There are no players standing on this object. */
	// public BoardCell(String new_name, Icon new_image, int x_pos, int y_pos, String edge) {
	//     name	    = new_name;
	//     ownedBy     = null;
	//     image	    = new_image;
	//     cellX	    = x_pos;
	//     cellY       = y_pos;
	//     orientation = edge;
	// }

	BoardCell testCell = new BoardCell("name", new ImageIcon("images/red_template.jpg"), 4, 10, "left");
	Player aPlayer  = new Player(1);

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
	//	assertEquals(testCell.getCost(), );


	// Icon getImage()
	// void setImage(Icon new_image)

	// TODO: Then more complicated, out of the ordinary situations.
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

    // ===================
    // IdeopolyTester.java <-- TODO
    // ===================

    // =============
    // CashCell.java
    // =============
    /** Test all methods in the CashCell class.*/
    @Test
    public void testCashCell() {
	CashCell testCashCell = new CashCell(5, 10, "testingtext");
	assertEquals(testCashCell.getXCoord(), 5);
	assertEquals(testCashCell.getYCoord(), 10);
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

    // ===========
    // Chance.java
    // ===========
    // TODO: For this class, since the card's always selected randomly, it's impossible for me
    // to test for valid values. Better would be to accept an argument to the function that 
    // determines its text and such. Would make it testable. But would it improve the code?

    // /** Test all methods in the Chance class. */
    // public void testChance() {
    // }

//     public Chance() {
//     public String getText() {
//     public int getType() {




// public class Chance {
//     /** Number representing the type of this card. This type is used to
//      *  determine the text on the card and the actions taken when this
//      *  card is drawn. */
//     private int cardType;

//     /** The text written on this card. Used for information for the player. */
//     private String text;

//     /** Create the card, setting its text field. */
//     public Chance() {
// 	Random generator = new Random();

// 	// Randomly set this card's type.
// 	cardType = generator.nextInt(16) + 1;

// 	switch (cardType) {
// 	case 1:  text = "Advance to Go (Collect $200)";
// 	    break;
// 	case 2:  text = "Advance to Illinois Ave - if you pass Go, collect $200";
// 	    break;
// 	case 3:  text = "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.";
// 	    break;
// 	case 4:  text = "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)";
// 	    break;
// 	case 5:  text = "Advance to St. Charles Place – if you pass Go, collect $200";
// 	    break;
// 	case 6:  text = "Bank pays you dividend of $50";
// 	    break;
// 	case 7:  text = "Get out of Jail Free – this card may be kept until needed, or traded/sold";
// 	    break;
// 	case 8:  text = "Go back 3 spaces";
// 	    break;
// 	case 9:  text = "Go directly to Jail – do not pass Go, do not collect $200";
// 	    break;
// 	case 10: text = "Make general repairs on all your property – for each house pay $25 – for each hotel $100";
// 	    break;
// 	case 11: text = "Pay poor tax of $15";
// 	    break;
// 	case 12: text = "Take a trip to Reading Railroad – if you pass Go, collect $200";
// 	    break;
// 	case 13: text = "Take a walk on the Boardwalk – advance token to Boardwalk";
// 	    break;
// 	case 14: text = "You have been elected chairman of the board – pay each player $50";
// 	    break;
// 	case 15: text = "Your building loan matures – collect $150";
// 	    break;
// 	case 16: text = "You have won a crossword competition - collect $100 ";
// 	    break;

// 	default: System.out.println("Error: Tried to create a non-standard Chance card.");
// 	    break;
// 	}
//     }

//     /** Get the text field associated with this card. */
//     public String getText() {
// 	return text;
//     }

//     /** Get the type of this card. */
//     public int getType() {
// 	return cardType;
//     }








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

    // =====================
    // PropagandaOutlet.java
    // =====================
    /** Test all methods in the PropagandaOutlet class. */
    @Test
    public void testPropagandaOutlet() {
	PropagandaOutlet testPropOutlet = new PropagandaOutlet("testPropOutlet", new ImageIcon("images/yellow_template.jpg"), 40, 10, 60, 180, 350, 630, 800, 100, 18, 18, "right");

	assertEquals(testPropOutlet.getName(), "testPropOutlet");
	//	    TODO: Test the image assigned during creation.
	assertEquals(testPropOutlet.getX(), 18);
	assertEquals(testPropOutlet.getY(), 18);
	assertEquals(testPropOutlet.getOrientation(), "right");

	// assertEquals(testPropOutlet.getNumHouses(), 0); TODO: Tests/methods for these?
	// assertEquals(testPropOutlet.getNumHotels(), 0);
	assertEquals(testPropOutlet.getCost(), 40);
	// TODO: Test for mortgage value?
	assertEquals(testPropOutlet.getRent(), 10);
	// TODO: Add tests here for when I have 1/2/3/4 houses, and 1 hotel.
    }

// 	super(new_name, new_image, x_pos, y_pos, edge); // Use the BoardCell class' constructor.
// 	numHouses	    = 0;
// 	numHotels	    = 0;
// 	cost                = new_cost;
// 	mortgageValue	    = new_cost / 2; // Mortgage prices are always half the price to buy the property.
// 	initialRent	    = new_initial_rent;
// 	rent1House	    = new_rent_1_house;
// 	rent2House	    = new_rent_2_house;
// 	rent3House	    = new_rent_3_house;
// 	rent4House	    = new_rent_4_house;
// 	rent1Hotel	    = new_rent_1_hotel;
// 	houseOrHotelCost    = new_house_or_hotel_cost;


//     public PropagandaOutlet(String new_name, Icon new_image, int new_cost, int new_initial_rent, int new_rent_1_house, int new_rent_2_house, int new_rent_3_house, int new_rent_4_house, int new_rent_1_hotel, int new_house_or_hotel_cost, int x_pos, int y_pos, String edge) {
// 	// TODO: Better, less ambiguous variable names here.
// 	super(new_name, new_image, x_pos, y_pos, edge); // Use the BoardCell class' constructor.
// 	numHouses	    = 0;
// 	numHotels	    = 0;
// 	cost                = new_cost;
// 	mortgageValue	    = new_cost / 2; // Mortgage prices are always half the price to buy the property.
// 	initialRent	    = new_initial_rent;
// 	rent1House	    = new_rent_1_house;
// 	rent2House	    = new_rent_2_house;
// 	rent3House	    = new_rent_3_house;
// 	rent4House	    = new_rent_4_house;
// 	rent1Hotel	    = new_rent_1_hotel;
// 	houseOrHotelCost    = new_house_or_hotel_cost;
//     }

//     public int getRent() {
//     public int getCost() {


//======
// Methods inherited from BoardCell:
//======

    // public BoardCell(String new_name, Icon new_image, int x_pos, int y_pos, String edge) {
    // 	name	    = new_name;
    // 	ownedBy     = null;
    // 	image	    = new_image;
    // 	cellX	    = x_pos;
    // 	cellY       = y_pos;
    // 	orientation = edge;
    // }

    // /** Get the name of this property. */
    // public String getName() {
    // 	return name;
    // }

    // /** Get the owner of this property. */
    // public Player getOwner() {
    // 	return ownedBy;
    // }

    // /** Make player p the owner of this property. */
    // public void setOwner(Player p) {
    // 	ownedBy = p;
    // 	// TODO: Have this function also set the relevant image for the property to indicate ownership.
    // 	// Can use player.getImage()
    // }

    // /** Get the image associated with this property. */
    // public Icon getImage() {
    // 	return image;
    // }

    // public void setImage(Icon new_image) {
    // 	image = new_image;
    // }

    // /** Get this cell's x position. */
    // public int getX() {
    // 	return cellX;
    // }

    // /** Get this cell's y position. */
    // public int getY() {
    // 	return cellY;
    // }

    // /** Get this cell's orientation on the board. */
    // public String getOrientation() {
    // 	return orientation;
    // }

    // /** Dummy method. Used so I can access getRent() from the derived class PropagandaOutlet. */
    // public int getRent() {
    // 	return 0;
    // }

    // public int getCost() {
    // 	return 0;
    // }






    // ===================
    // CommunityChest.java
    // ==================

    // ==============
    // Railroad.java
    // ==============



}


    /*
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