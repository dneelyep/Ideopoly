import junit.framework.TestCase;
import java.awt.*;
import javax.swing.*;
/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */

/**
 * IdeopolyTester - various test cases to make sure the game works as intended.
 */
public class IdeopolyTester extends TestCase {
    // TODO: Add all test cases (and more!) from my notes.
    //    System.out.println("Hello."); // TODO: This is just here to remove make docs warnings.


    // LEFTOFFHERE: Before I go any further in the code, implementing tests for each function in the game.

    /** Test all methods in the BoardCell class. */
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

	testPlayer.giveGetOutOfJailFree(); // TODO: Rename to GOOJF
	assertEquals(testPlayer.getNumGOOJFCards(), 1);

	testPlayer.spendGetOutOfJailFree();
	assertEquals(testPlayer.getNumGOOJFCards(), 0);

	testPlayer.spendGetOutOfJailFree();
	//	assertEquals(testPlayer.getNumGOOJFCards(), 0);


	// //	totalPropertiesOwned = 0; // TODO: <-- Test that.

	// image = new ImageIcon("images/p1_present.jpg"); // getImage()

	//	public Player(int player_number)
	    // public String getCash(String bill_type)
	    // public void addProperty(BoardCell property)
	    // public void removeProperty(BoardCell property)
	    // public void roll()
	    // public Icon getImage()
	    // public int getPosition()
	    // public void setPosition(int p)
	    // public void setCash(String t, int a) //TODO: Also test for correct update of the total field her
	    // public void setInJail(int weeks)
	    // public void giveGetOutOfJailFree()
	    // /** Have this player spend one of their get out of jail free cards. */
	    // //	    TODO: Test this when the person's not in jail.
	    // public void spendGetOutOfJailFree()
	    // public int getNumGetOutOfJailFreeCards()
	    // public int getJailStatus()
	    // public int getNumHouses()
	    // public int getNumHotels()
	    // public void spreadMoney(int numToSwitch)// TODO: LOTS OF TESTS FOR THIS FUNCTION
	    // public void bankruptPlayer(IdeopolyGUI gui)
	    // public String getName()
    }















//     public Player(int player_number) {
//     public String getCash(String bill_type) {
//     public void addProperty(BoardCell property) {
//     public void removeProperty(BoardCell property) {
//     public void roll() {
//     public Icon getImage() {
//     public int getPosition() {
//     public void setPosition(int p) {
//     public void setCash(String t, int a) //TODO: Also test for correct update of the total field here.
//     public void setInJail(int weeks) {
//     public void giveGetOutOfJailFree() {
//     /** Have this player spend one of their get out of jail free cards. */
// 	// TODO: Test this when the person's not in jail.
//     public void spendGetOutOfJailFree() {
//     public int getNumGetOutOfJailFreeCards() {
//     public int getJailStatus() {
//     public int getNumHouses() {
//     public int getNumHotels() {
//     public Boolean willBankrupt(int amount) {
//     public void spreadMoney(int numToSwitch) {// TODO: LOTS OF TESTS FOR THIS FUNCTION
//     public void bankruptPlayer(IdeopolyGUI gui) {
//     public String getName() {

    // ==================================================================================================================


// /** Class that represents a player. Can be a computer or human-controlled character. */
// public class Player {

//     /** Name of this player. */
//     private String name;

//     /** Player's jail status.
//      *  0 = not in jail.
//      *  1 = last week in jail.
//      *  2 = second week in jail.
//      *  3 = first week in jail. */
//     private int inJail; // TODO: This needed? Couldn't I just use board position?

//     /** Amount of one dollar bills this player has. */
//     private int ones;

//     /** Amount of five dollar bills this player has. */
//     private int fives;

//     /** Amount of ten dollar bills this player has. */
//     private int tens;

//     /** Amount of twenty dollar bills this player has. */
//     private int twenties;

//     /** Amount of fifty dollar bills this player has. */
//     private int fifties;

//     /** Amount of hundred dollar bills this player has. */
//     private int hundreds;

//     /** Total amount of money this player has. */
//     private int totalMoney;

//     /** Amount of five hundred dollar bills this player has. */
//     private int fiveHundreds;

//     /** Amount of properties this player owns. */
//     private int totalPropertiesOwned;

//     /** Amount of houses this player owns. */
//     private int totalHousesOwned;

//     /** Amount of hotels this player owns. */
//     private int totalHotelsOwned;

//     /** Number of get out of jail free cards this player owns. */
//     private int getOutOfJailFree;

//     /** This player's current board position. Go=0, Boardwalk=whatever, etc. */
//     private int currentPosition;

//     /** Image associated with this player. Used as an icon to indicate board position. */
//     private Icon image;

//     // TODO: Make add/remove functions for owned_outlets. Use something other than an array for ease?

//     /** Actions to take when a player object is initially created. Players
//      *  are only created at the start of the game. Different players start
//      *  at different positions on the board (Same cell, different walking space.) */
//     public Player(int player_number) {

// 	inJail = 0; // TODO: This needed? Couldn't I just use board position?

// 	// Initial cash values.
// 	ones	      = 5;
// 	fives	      = 5;
// 	tens	      = 5;
// 	twenties      = 6;
// 	fifties	      = 2;
// 	hundreds      = 2;
// 	fiveHundreds  = 2;
// 	totalMoney    = (ones + (fives * 5) + (tens * 10) + (twenties * 20)
// 	                + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );

// 	totalPropertiesOwned = 0;
// 	totalHousesOwned     = 0;
// 	totalHotelsOwned     = 0;

// 	// TODO: Create an empty array? Or is it already created?
// 	//	owned_outlets[] = ;

// 	getOutOfJailFree = 0;

// 	switch (player_number) {
// 	    case 1: currentPosition = 3;
// 		name = "Player 1 (H)";
// 		image = new ImageIcon("images/p1_present.jpg");
// 		break;
// 	    case 2: currentPosition = 2;
// 		name = "Player 2 (C)";
// 		image = new ImageIcon("images/p2_present.jpg");
// 		break;
// 	    case 3: currentPosition = 1;
// 		name = "Player 3 (C)";
// 		image = new ImageIcon("images/p3_present.jpg");
// 		break;
// 	    case 4: currentPosition = 0;
// 		name = "Player 4 (c)";
// 		image = new ImageIcon("images/p4_present.jpg");
// 		break;

// 	    default: System.out.println("Error: tried to create a non-standard player.");
// 	}
//     }

//     /** Get the amount of bills of the given type for this player. */
//     public String getCash(String bill_type) {
// 	// TODO: Have the string parameter refer to the actual value. So i could just say "return bill_type" 
// 	// rather than switch

// 	switch (bill_type) {
// 	    case "ones":
// 		return Integer.toString(ones);
// 	    case "fives":
// 		return Integer.toString(fives);
// 	    case "tens":
// 		return Integer.toString(tens);
// 	    case "twenties":
// 		return Integer.toString(twenties);
// 	    case "fifties":
// 		return Integer.toString(fifties);
// 	    case "hundreds":
// 		return Integer.toString(hundreds);
// 	    case "five_hundreds":
// 		return Integer.toString(fiveHundreds);
//             case "total":
// 		return Integer.toString(totalMoney);
// 	    default:
// 		return "Error! Incorrect argument."; // TODO: Make this an error code thing.
// 	}
//     }

//     /** Give this player a new property. */
//     public void addProperty(BoardCell property) {
// 	//TODO: Implement this. add the property, and increment number of properties owned.
//     }
//     /** Remove a property from this player. */
//     public void removeProperty(BoardCell property) {
// 	//TODO: Implement this. remove the property, decrement number of properties owned.
//     }

//     /** Have this player roll the dice. */
//     public void roll() {
// 	// TODO: Implement this
//     }

//     /** Return the image associated with this player. */
//     public Icon getImage() {
// 	return image;
//     }

//     /** Get this player's current position. */
//     public int getPosition() {
// 	return currentPosition;
//     }

//     /** Set this player's current position to p. */
//     public void setPosition(int p) {
// 	currentPosition = p;
//     }

//     /** Change this player's amount a of currency type t. */
//     public void setCash(String t, int a) {
// 	switch (t) {
// 	case "ones":         ones += a;
// 	    break;
// 	case "fives":        fives += a;
// 	    break;
// 	case "tens":         tens += a;
// 	    break;
// 	case "twenties":     twenties += a;
// 	    break;
// 	case "fifties":      fifties += a;
// 	    break;
// 	case "hundreds":     hundreds += a;
// 	    break;
// 	case "fiveHundreds": fiveHundreds += a;
// 	    break;
// 	default: System.out.println("Invalid currency amount.");
// 	    break;
// 	}

// 	// After money total changes, update the totalMoney field accordingly.
// 	totalMoney = ( ones + (fives * 5) + (tens * 10) + (twenties * 20) + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );

//     }

//     /** Set the value of this Player's inJail property. */
//     public void setInJail(int weeks) {
// 	inJail = weeks;
//     }

//     /** Give this player a get out of jail free card. */
//     public void giveGetOutOfJailFree() {
// 	getOutOfJailFree++;
//     }

//     /** Have this player spend one of their get out of jail free cards. */
//     public void spendGetOutOfJailFree() {
// 	// TODO: Don't allow this when the person's not in jail.
// 	getOutOfJailFree--;
// 	inJail = 0;
//     }

//     /** Return the number of GOOJF cards this player owns. */
//     public int getNumGetOutOfJailFreeCards() {
// 	return getOutOfJailFree;
//     }

//     /** See what week of jail this person is in. */
//     public int getJailStatus() {
// 	return inJail;
//     }

//     /** Get the number of houses this player owns. */
//     public int getNumHouses() {
// 	return totalHousesOwned;
//     }

//     /** Get the number of hotels this player owns. */
//     public int getNumHotels() {
// 	return totalHotelsOwned;
//     }

//     /** Determine if the given amount will bankrupt this Player
//      *  if (s)he has to pay it.
//      *  Return True if Player will be bankrupted, False otherwise. */
//     public Boolean willBankrupt(int amount) {
// 	if ( totalMoney < amount ) {
// 	    return true;
// 	}
// 	else {
// 	    return false;
// 	}
//     }

//     /** Convert this Player's money into bill type numToSwitch.
//      *  For example, if a Player needs more 5s, this will convert all the Player's
//      *  cash to 5s. By default (with an invalid argument), converts to 500s. */
//     // TODO: Test this function more, make sure it works correctly in all cases.
//     public void spreadMoney(int numToSwitch) {

// 	// The amount still left to be spread out.
// 	int amountNotSpread = totalMoney;
// 	// The number of a given type of bill spread.
// 	int numSpread;

// 	fiveHundreds = 0;
// 	hundreds     = 0;
// 	fifties	     = 0;
// 	twenties     = 0;
// 	tens	     = 0;
// 	fives	     = 0;
// 	ones	     = 0;

// 	int numbers[]      = {500, 100, 50, 20, 10, 5, 1};
// 	int actualValues[] = {fiveHundreds, hundreds, fifties, twenties, tens, fives, ones};

// 	// TODO: Clean up this mess of code.
// 	//	if input is x, set item[0] to x, and item[x] to fiveHundreds
// 	//      And update the rest of the behavior accordingly.
// 	if (numToSwitch == 1) {
// 	    numbers[0] = 1;
// 	    actualValues[0] = ones;
// 	    numbers[6] = 500;
// 	    actualValues[6] = fiveHundreds ;

// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);
// 		    // Can use this for debugging later if want.
// 		    // System.out.println("Amount not spread: " + amountNotSpread);
// 		    // System.out.println("Five Hundreds: " + actualValues[6]);
// 		    // System.out.println("Hundreds: "      + actualValues[1]);
// 		    // System.out.println("Fifties: "       + actualValues[2]);
// 		    // System.out.println("Twenties: "      + actualValues[3]);
// 		    // System.out.println("Tens: "          + actualValues[4]);
// 		    // System.out.println("Fives: "         + actualValues[5]);
// 		    // System.out.println("Ones: "          + actualValues[0]);

// 		    fiveHundreds = actualValues[6];
// 		    hundreds     = actualValues[1];
// 		    fifties	 = actualValues[2];
// 		    twenties     = actualValues[3];
// 		    tens	 = actualValues[4];
// 		    fives	 = actualValues[5];
// 		    ones         = actualValues[0];
// 		}
// 	    }
// 	}
// 	else if (numToSwitch == 5) {
// 	    numbers[0] = 5;
// 	    actualValues[0] = fives;
// 	    numbers[5] = 500;
// 	    actualValues[5] = fiveHundreds;

// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);

// 		    fiveHundreds = actualValues[5];
// 		    hundreds     = actualValues[1];
// 		    fifties	     = actualValues[2];
// 		    twenties     = actualValues[3];
// 		    tens	     = actualValues[4];
// 		    fives	     = actualValues[0];
// 		    ones         = actualValues[6];
// 		}
// 	    }
// 	}
// 	else if (numToSwitch == 10) {
// 	    numbers[0] = 10;
// 	    actualValues[0] = tens;
// 	    numbers[4] = 500;
// 	    actualValues[4] = fiveHundreds;

// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);

// 		    fiveHundreds = actualValues[4];
// 		    hundreds     = actualValues[1];
// 		    fifties	 = actualValues[2];
// 		    twenties     = actualValues[3];
// 		    tens	 = actualValues[0];
// 		    fives	 = actualValues[5];
// 		    ones         = actualValues[6];
// 		}
// 	    }
// 	}
// 	else if (numToSwitch == 20) {
// 	    numbers[0] = 20;
// 	    actualValues[0] = twenties;
// 	    numbers[3] = 500;
// 	    actualValues[3] = fiveHundreds;

// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);

// 		    fiveHundreds = actualValues[3];
// 		    hundreds     = actualValues[1];
// 		    fifties      = actualValues[2];
// 		    twenties     = actualValues[0];
// 		    tens         = actualValues[4];
// 		    fives        = actualValues[5];
// 		    ones         = actualValues[6];
// 		}
// 	    }
// 	}
// 	else if (numToSwitch == 50) {
// 	    numbers[0] = 50;
// 	    actualValues[0] = fifties;
// 	    numbers[2] = 500;
// 	    actualValues[2] = fiveHundreds;

// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);

// 		    fiveHundreds = actualValues[2];
// 		    hundreds     = actualValues[1];
// 		    fifties      = actualValues[0];
// 		    twenties     = actualValues[3];
// 		    tens         = actualValues[4];
// 		    fives        = actualValues[5];
// 		    ones         = actualValues[6];
// 		}
// 	    }
// 	}
// 	else if (numToSwitch == 100) {
// 	    numbers[0] = 100;
// 	    actualValues[0] = hundreds;
// 	    numbers[1] = 500;
// 	    actualValues[1] = fiveHundreds;

// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);

// 		    fiveHundreds = actualValues[1];
// 		    hundreds     = actualValues[0];
// 		    fifties      = actualValues[2];
// 		    twenties     = actualValues[3];
// 		    tens         = actualValues[4];
// 		    fives        = actualValues[5];
// 		    ones         = actualValues[6];
// 		}
// 	    }
// 	}
// 	else { // Default, orders by 500s.
// 	    for (int i = 0; i <= 6; i++) {
// 		if ( (amountNotSpread / numbers[i]) != 0) {
// 		    numSpread = amountNotSpread / numbers[i];
// 		    actualValues[i] += numSpread;
// 		    amountNotSpread -= (numbers[i] * numSpread);

// 		    fiveHundreds = actualValues[0];
// 		    hundreds     = actualValues[1];
// 		    fifties	 = actualValues[2];
// 		    twenties     = actualValues[3];
// 		    tens	 = actualValues[4];
// 		    fives	 = actualValues[5];
// 		    ones         = actualValues[6];
// 		}
// 	    }
// 	}
//     }
//     /** Bankrupt this player. */
//     public void bankruptPlayer(IdeopolyGUI gui) {
// 	// TODO: Do more than just set cash values. The player can still be considered alive,
// 	// given money, etc. in this state.
// 	// TODO: Set the player's text to red when this happens maybe also?
// 	ones         = 0;	
// 	fives	     = 0;
// 	tens	     = 0;
// 	twenties     = 0;
// 	fifties	     = 0;
// 	hundreds     = 0;
// 	fiveHundreds = 0;
// 	totalMoney   = 0;

// 	//	gui.playersArrSize--;

// 	// TODO: This to remove the player's position image from the board.
// 	// 	gui.positions.get( this.getPosition() ).setImage(new ImageIcon("images/no_player_present.jpg"));

// 	// if      (this == array[0]) {
// 	//     array[0] = array[1];
// 	//     array[1] = array[2];
// 	//     array[2] = array[3];
// 	//     //	    players[]       = { player1, player2, player3, player4 };
// 	// }
// 	// else if (this == gui.getPlayerArr()[1]) {
// 	//     array[1] = array[2];
// 	//     array[2] = array[3];
// 	// }  
// 	// else if (this == gui.getPlayerArr()[2]) {
// 	//     array[2] = array[3];
// 	// }
// 	// else if (this == gui.getPlayerArr()[3]) {
// 	//     array[2] = array[3];
// 	// }
//     }

//     /** Get the name of this Player. */
//     public String getName() {
// 	return name;
//     }
// }

    // ====================
    // CharacterSelect.java
    // ====================

    // =====================
    // PropagandaOutlet.java
    // =====================

    // ===================
    // CommunityChest.java
    // ===================

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