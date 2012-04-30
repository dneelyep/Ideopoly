import junit.framework.TestCase;
import java.awt.*;
import javax.swing.*;
import org.junit.*;

// TODO: Add all test cases from my notes.
// TODO: Split this into multiple files?
/**
 * IdeopolyTester - various test cases to make sure the game works as intended.
 */
public class IdeopolyTester extends TestCase {

    /** Test all methods in the BoardCell class. */ 
   @Test
    public void testBoardCell() {
       // TODO: Should have deleted this file.
	BoardCell testCell = new BoardCell("name", new ImageIcon("images/red_template.jpg"), 4, 10);
	Player    aPlayer  = new Player(1);

	// LEFTOFFHERE: Implmenting tests here. Make sure the order of arguments is correct on assertEquals.
	// Test basic, not out-of-the-ordinary cases.
	assertEquals(testCell.getName(), "name");
	assertEquals(testCell.getOwner(), null);

	testCell.setOwner(aPlayer);
	assertEquals(testCell.getOwner(), aPlayer);

	assertEquals(testCell.getX(), 4);
	assertEquals(testCell.getY(), 10);

// TODO: What should these return? This screams for a better design, since I think they're just dummy methods.
	//	assertEquals(testCell.getRent(), );
	//	assertEquals(testCell.getCost(), )


	// Icon getImage()
	// void setImage(Icon newImage)

	// TODO: Then more complicated, out of the ordinary situations. For 
	// example, test for odd values to the constructor.
    }

	// ================
	// TODO: IdeopolyGUI.java
	// ================
    /** Test all methods in the IdeopolyGUI class. */
    public void testIdeopolyGUI() {

	// =====================
	// Community Chest tests
	// Test all possible 
	// values of CC cards for
	// correct output.
	// =====================
	Player testCCPlayer1  = new Player(1);
	Player testCCPlayer2  = new Player(2);
	Player testCCPlayer3  = new Player(3);
	Player testCCPlayer4  = new Player(4);
	CommunityChest testCommChest = new CommunityChest(1);
	assertEquals(testCCPlayer1.getPosition(), 3);
	assertEquals(testCCPlayer2.getPosition(), 2);
	assertEquals(testCCPlayer3.getPosition(), 1);
	assertEquals(testCCPlayer4.getPosition(), 0);
	// TODO: Here, test the given player's cash amount, to make sure it's amount += 200.
	// This can't CURRENTLY (but SOON CAN BE) be done because my Comm Chest/Chance handling 
	// code is tied into my movePlayer() function. That's another TODO - separate the things.
	// For example, make a drawCommunityChest() function. And for Chance.

	testCommChest = new CommunityChest(2);

	testCommChest = new CommunityChest(3);

	testCommChest = new CommunityChest(4);

	testCommChest = new CommunityChest(5);

	testCommChest = new CommunityChest(6);

	testCommChest = new CommunityChest(7);

	testCommChest = new CommunityChest(8);

	testCommChest = new CommunityChest(9);

	testCommChest = new CommunityChest(10);

	testCommChest = new CommunityChest(11);

	testCommChest = new CommunityChest(12);

	testCommChest = new CommunityChest(13);

	testCommChest = new CommunityChest(14);

	testCommChest = new CommunityChest(15);

	testCommChest = new CommunityChest(16);

	testCommChest = new CommunityChest(17);


// case 2:	// "Bank error in your favor – collect $200"
//     p.addCash("hundreds", 2);
//     break;
// case 3:   // "Doctor's fees – Pay $50"
//     if (p.willBankrupt(50)) {
// 	p.bankruptPlayer(); // TODO: Update this code to do more relevant stuff.
//     }
//     else {
// 	p.spreadCash(50); // Shuffle money around to get 50s.

// 	p.addCash("fifties", -1);
// 	p.spreadCash(500); // Then shuffle back to a more reasonable amount.
//     }
//     break;
// case 4:   // "Get Out of Jail Free – this card may be kept until needed, or sold"
//     // TODO: Repeated later as well.
//     p.giveGOOJF();
//     break;
// case 5:   // "Go to Jail – go directly to jail – Do not pass Go, do not collect $200"
//     // TODO: This repeated later.
//     if ( p == player1 )
// 	p.setPosition( 43 );
//     else if (p == player2)
// 	p.setPosition( 42 );
//     else if (p == player3)
// 	p.setPosition( 41 );
//     else if (p == player4)
// 	p.setPosition( 40 );

//     putInJail(p);
//     break;
// case 6:   // "It is your birthday - Collect $10 from each player"
//     // TODO: Repeated also.
//     if (p2.willBankrupt(10) || p3.willBankrupt(10) || p4.willBankrupt(10)) {
// 	// TODO: ...bankruptcy code...
//     }
//     else {
// 	p2.spreadCash(10);
// 	p2.addCash("tens", -1);
// 	p2.spreadCash(500);

// 	p3.spreadCash(10);
// 	p3.addCash("tens", -1);
// 	p3.spreadCash(500);

// 	p4.spreadCash(10);
// 	p4.addCash("tens", -1);
// 	p4.spreadCash(500);

// 	p.addCash("tens", 3);
//     }

//     break;
// case 7:   // "Grand Opera Night – collect $50 from every player for opening night seats"

//     if (p2.willBankrupt(50) || p3.willBankrupt(50) || p4.willBankrupt(50)) {
// 	// TODO: ...bankruptcy code...
//     }
//     else {
// 	// TODO: Could loop this.
// 	p2.spreadCash(50);
// 	p2.addCash("fifties", -1);
// 	p2.spreadCash(500);

// 	p3.spreadCash(50);
// 	p3.addCash("fifties", -1);
// 	p3.spreadCash(500);

// 	p4.spreadCash(50);
// 	p4.addCash("fifties", -1);
// 	p4.spreadCash(500);

// 	p.addCash("tens", 3);
//     }
//     break;
// case 8:   // "Income Tax refund – collect $20"
//     p.addCash("twenties", 1);
//     break;
// case 9:   // "Life Insurance Matures – collect $100"
//     p.addCash("hundreds", 1);
//     break;
// case 10:  // "Pay Hospital Fees of $100"

//     if (p.willBankrupt(100)) {
// 	p.bankruptPlayer();
//     }
//     else {
// 	p.spreadCash(100);
// 	p.addCash("hundreds", -1);
// 	p.spreadCash(500);
//     }
//     break;
// case 11:  // "Pay School Fees of $50"

//     if (p.willBankrupt(50)) {
// 	p.bankruptPlayer();
//     }
//     else {
// 	p.spreadCash(50);
// 	p.addCash("fifties", -1);
// 	p.spreadCash(500);
//     }
//     break;
// case 12:  // "Receive $25 Consultancy Fee"
//     p.addCash("twenties", 1);
//     p.addCash("fives", 1);
//     break;
// case 13:  // "You are assessed for street repairs – $40 per house, $115 per hotel"
//     if (p.willBankrupt( (p.getNumHouses() * 40) + (p.getNumHotels() * 115) )) {
// 	p.bankruptPlayer();
//     }
//     else {
// 	// TODO: Code here, this varies though. Difficult.
//     }

//     break;
// case 14:  // "You have won second prize in a beauty contest– collect $10"
//     p.addCash("tens", 1);
//     break;
// case 15:  // "You inherit $100"
//     p.addCash("hundreds", 1);
//     break;
// case 16:  // "From sale of stock you get $50"
//     p.addCash("fifties", 1);
//     break;
// case 17:  // "Holiday Fund matures - Receive $100"
//     p.addCash("hundreds", 1);
//     break;
// default:
//     System.out.println("Error! Wrong Community Chest value!");
//     break;
// }








	// ============
	// Chance tests
	// ============





    }

// =======================================================================
// case 1: // "Advance to Go (Collect $200)"
//     // TODO: This is repeated in the Chance deck.
//     if (p == player1)
// 	p.setPosition( 3 );
//     else if (p == player2)
// 	p.setPosition( 2 );
//     else if (p == player3)
// 	p.setPosition( 1 );
//     else if (p == player4)
// 	p.setPosition( 0 );

//     p.addCash("hundreds", 2);
//     break;
// case 2:	// "Bank error in your favor – collect $200"
//     p.addCash("hundreds", 2);
//     break;
// case 3:   // "Doctor's fees – Pay $50"
//     if (p.willBankrupt(50)) {
// 	p.bankruptPlayer(); // TODO: Update this code to do more relevant stuff.
//     }
//     else {
// 	p.spreadCash(50); // Shuffle money around to get 50s.
// 	p.addCash("fifties", -1);
// 	p.spreadCash(500); // Then shuffle back to a more reasonable amount.
//     }
//     break;
// case 4:   // "Get Out of Jail Free – this card may be kept until needed, or sold"
//     // TODO: Repeated later as well.
//     p.giveGOOJF();
//     break;
// case 5:   // "Go to Jail – go directly to jail – Do not pass Go, do not collect $200"
//     // TODO: This repeated later.
//     if ( p == player1 )
// 	p.setPosition( 43 );
//     else if (p == player2)
// 	p.setPosition( 42 );
//     else if (p == player3)
// 	p.setPosition( 41 );
//     else if (p == player4)
// 	p.setPosition( 40 );

//     putInJail(p);
//     break;
// case 6:   // "It is your birthday - Collect $10 from each player"
//     // TODO: Repeated also.
//     if (p2.willBankrupt(10) || p3.willBankrupt(10) || p4.willBankrupt(10)) {
// 	// TODO: ...bankruptcy code...
//     }
//     else {
// 	p2.spreadCash(10);
// 	p2.addCash("tens", -1);
// 	p2.spreadCash(500);

// 	p3.spreadCash(10);
// 	p3.addCash("tens", -1);
// 	p3.spreadCash(500);

// 	p4.spreadCash(10);
// 	p4.addCash("tens", -1);
// 	p4.spreadCash(500);

// 	p.addCash("tens", 3);
//     }

//     break;
// case 7:   // "Grand Opera Night – collect $50 from every player for opening night seats"

//     if (p2.willBankrupt(50) || p3.willBankrupt(50) || p4.willBankrupt(50)) {
// 	// TODO: ...bankruptcy code...
//     }
//     else {
// 	// TODO: Could loop this.
// 	p2.spreadCash(50);
// 	p2.addCash("fifties", -1);
// 	p2.spreadCash(500);

// 	p3.spreadCash(50);
// 	p3.addCash("fifties", -1);
// 	p3.spreadCash(500);

// 	p4.spreadCash(50);
// 	p4.addCash("fifties", -1);
// 	p4.spreadCash(500);

// 	p.addCash("tens", 3);
//     }
//     break;
// case 8:   // "Income Tax refund – collect $20"
//     p.addCash("twenties", 1);
//     break;
// case 9:   // "Life Insurance Matures – collect $100"
//     p.addCash("hundreds", 1);
//     break;
// case 10:  // "Pay Hospital Fees of $100"

//     if (p.willBankrupt(100)) {
// 	p.bankruptPlayer();
//     }
//     else {
// 	p.spreadCash(100);
// 	p.addCash("hundreds", -1);
// 	p.spreadCash(500);
//     }
//     break;
// case 11:  // "Pay School Fees of $50"

//     if (p.willBankrupt(50)) {
// 	p.bankruptPlayer();
//     }
//     else {
// 	p.spreadCash(50);
// 	p.addCash("fifties", -1);
// 	p.spreadCash(500);
//     }
//     break;
// case 12:  // "Receive $25 Consultancy Fee"
//     p.addCash("twenties", 1);
//     p.addCash("fives", 1);
//     break;
// case 13:  // "You are assessed for street repairs – $40 per house, $115 per hotel"
//     if (p.willBankrupt( (p.getNumHouses() * 40) + (p.getNumHotels() * 115) )) {
// 	p.bankruptPlayer();
//     }
//     else {
// 	// TODO: Code here, this varies though. Difficult.
//     }

//     break;
// case 14:  // "You have won second prize in a beauty contest– collect $10"
//     p.addCash("tens", 1);
//     break;
// case 15:  // "You inherit $100"
//     p.addCash("hundreds", 1);
//     break;
// case 16:  // "From sale of stock you get $50"
//     p.addCash("fifties", 1);
//     break;
// case 17:  // "Holiday Fund matures - Receive $100"
//     p.addCash("hundreds", 1);
//     break;
// default:
//     System.out.println("Error! Wrong Community Chest value!");
//     break;
// }

// // TODO: Redirect these out to a GUI status element thing.
// System.out.println(card.getText());
// }
	    
// // Player lands on a Chance card.
// 		    else if (    (landingSpot >= 28  && landingSpot <= 31 ) 
// 				 || (landingSpot >= 88  && landingSpot <= 91 )
// 				 || (landingSpot >= 144 && landingSpot <= 147)) {
// 			// ...do action...
// 			Chance card = chanceCards.pop();
// 			// TODO: Review all of these, make sure the correct code's called when a person 
// 			// lands on a cell. Right now, movement's looking ok, but I also need to 
// 			// handle charging rent, buying properties, etc.

// 			switch ( card.getType() ) {

// 			case 1:  //"Advance to Go (Collect $200)"
// 			    // TODO: Maybe make a move number offset for each player. Idea would be I could
// 			    // do, for each player p.setPosition(cell I want + move offset), rather than
// 			    // hard-coding every value individually.

// 			    if (p == player1)
// 				p.setPosition( 3 );
// 			    else if (p == player2)
// 				p.setPosition( 2 );
// 			    else if (p == player3)
// 				p.setPosition( 1 );
// 			    else if (p == player4)
// 				p.setPosition( 0 );

// 			    p.addCash("hundreds", 2);
// 			    break;
// 			case 2:  //"Advance to Illinois Ave - if you pass Go, collect $200"
// 			    // TODO: There's a general pattern to these cards. It's if position is 
// 			    // >= some value, give $200 dollars. And then, depending on player, set position.
// 			    // Make this type of card into a function.
// 			    // TODO: Duplicate type of function. p1 starts at highest #, others are -1.

//                             // If the player's at B & O RR or after, give money.
// 			    if (p.getPosition() >= 100)
// 				p.addCash("hundreds", 2);

// 			    if ( p == player1 )
// 				p.setPosition( 99 );
// 			    else if (p == player2)
// 				p.setPosition( 98 );
// 			    else if (p == player3)
// 				p.setPosition( 97 );
// 			    else if (p == player4)
// 				p.setPosition( 96 );
// 			    break;
// 			case 3:  //"Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown."


// 			    break;
// 			case 4:  //"Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)"
// 			    // TODO: Account for the two of these, or just ignore it.
// 			    break;
// 			case 5:  //"Advance to St. Charles Place – if you pass Go, collect $200"

// 			    // If the player's position is on or after Electric Company, give em $200.
// 			    if (p.getPosition() >= 48)
// 				p.addCash("hundreds", 2);

// 			    if ( p == player1 )
// 				p.setPosition( 47 );
// 			    else if (p == player2)
// 				p.setPosition( 46 );
// 			    else if (p == player3)
// 				p.setPosition( 45 );
// 			    else if (p == player4)
// 				p.setPosition( 44 );

// 			    break;

// 			case 6:  //"Bank pays you dividend of $50"
// 			    p.addCash("fifties", 1);
// 			    break;
// 			case 7:  //"Get out of Jail Free – this card may be kept until needed, or traded/sold"
// 			    p.giveGOOJF();
// 			    break;
// 			case 8:  //"Go back 3 spaces"
// 			    p.setPosition(p.getPosition() - 12);
// 			    // TODO: Then call onland function.
// 			    break;
// 			case 9:  //"Go directly to Jail – do not pass Go, do not collect $200"
// 			    // TODO: Here's another reason to make put in jail a player method.
// 			    // It's an action that's performed several times.

// 			    if ( p == player1 )
// 				p.setPosition( 43 );
// 			    else if (p == player2)
// 				p.setPosition( 42 );
// 			    else if (p == player3)
// 				p.setPosition( 41 );
// 			    else if (p == player4)
// 				p.setPosition( 40 );

// 			    putInJail(p);

// 			    break;
// 			case 10: //"Make general repairs on all your property – for each house pay $25 – 
// 			         //for each hotel $100"
// 			    // If the payment will bankrupt the Player, do x.
// 			    // TODO: Haven't tested this yet, to make sure I get correct values out of parseInt().
// 			    if ( p.willBankrupt( (p.getNumHouses() * 25) + (p.getNumHotels() * 100 ) ) ) {
// 				p.bankruptPlayer();
// 			    }
// 			    else {
// 				//TODO: Remove cash here.
// 			    }
// 			    break;
// 			case 11: //"Pay poor tax of $15"

// 			    if (p.willBankrupt(15)) {
// 				p.bankruptPlayer();
// 			    }
// 			    else {
// 				p.spreadCash(10);
// 				p.addCash("tens", -1);
// 				p.spreadCash(5);
// 				p.addCash("fives", -1);
// 				p.spreadCash(500);
// 			    }    
// 			    break;
// 			case 12: //"Take a trip to Reading Railroad – if you pass Go, collect $200"
// 			    // If the player's position is on or after Oriental avenue, give em $200.
// 			    // TODO: Test this to make sure it works.
// 			    if (p.getPosition() >= 24)
// 				p.addCash("hundreds", 2);

// 			    if ( p == player1 )
// 				p.setPosition( 23 );
// 			    else if (p == player2)
// 				p.setPosition( 22 );
// 			    else if (p == player3)
// 				p.setPosition( 21 );
// 			    else if (p == player4)
// 				p.setPosition( 20 );

// 			    // TODO: And then onland function.
// 			    break;

// 			case 13: //"Take a walk on the Boardwalk – advance token to Boardwalk"
// 			    if ( p == player1 )
// 				p.setPosition( 159 );
// 			    else if (p == player2)
// 				p.setPosition( 158 );
// 			    else if (p == player3)
// 				p.setPosition( 157 );
// 			    else if (p == player4)
// 				p.setPosition( 156 );

// 			    //TODO: And then call the onland function for boardwalk.
// 			    break;
// 			case 14: //"You have been elected chairman of the board – pay each player $50"
// 			    // TODO: Make sure addCash handles negative values appropriately.
// 			    if (p.willBankrupt(150)) {
// 				p.bankruptPlayer();
// 			    }
// 			    else {
// 				p.spreadCash(50);
// 				p.addCash("fifties", -3);
// 				p2.addCash("fifties", 1);
// 				p3.addCash("fifties", 1);
// 				p4.addCash("fifties", 1);
// 				p.spreadCash(500);
// 			    }
// 			    break;
// 			case 15: //"Your building loan matures – collect $150"
// 			    p.addCash("hundreds", 1);
// 			    p.addCash("fifties", 1);
// 			    break;
// 			case 16: //"You have won a crossword competition - collect $100 "
// 			    p.addCash("hundreds", 1);
// 			    break;

// =======================================================================




































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

	Icon testImage = new ImageIcon("images/p1Present.jpg");
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

	testCashCell = new CashCell(-1, -2, "testingtext");
	assertEquals(testCashCell.getXCoord(), -1);
	assertEquals(testCashCell.getYCoord(), -2);
	assertEquals(testCashCell.getText(), "testingtext");

	testCashCell = new CashCell(0, 0, "testingtext");
	assertEquals(testCashCell.getXCoord(), 0);
	assertEquals(testCashCell.getYCoord(), 0);
	assertEquals(testCashCell.getText(), "testingtext");
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
	assertEquals(testPlayer.getCash("fiveHundreds"), "2");
	assertEquals(testPlayer.getCash("total"),      "1500");

	assertEquals(testPlayer.getNumHouses(),          0);
	assertEquals(testPlayer.getNumHotels(),          0);
	// TODO: For both of these, add tests for adding/removing, negative #s, etc.
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
	// TODO: Test this when the person's not in jail. Ideally the button AND the
	// functionality should be disabled when we're not currently in jail.
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

	// TODO: More tests for this method to test edge cases.
	assertEquals(testPlayer.getNumOwnedProperties(), 0); 

	testPlayer.bankruptPlayer();
	assertEquals(testPlayer.getCash("ones"),          "0");
	assertEquals(testPlayer.getCash("fives"),         "0");
	assertEquals(testPlayer.getCash("tens"),          "0");
	assertEquals(testPlayer.getCash("twenties"),      "0");
	assertEquals(testPlayer.getCash("fifties"),       "0");
	assertEquals(testPlayer.getCash("hundreds"),      "0");
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("total"),         "0");
	assertEquals(testPlayer.getImage(),              null);

	testPlayer.addCash("ones", Integer.parseInt(testPlayer.getCash("ones")) + 20);
	testPlayer.addCash("fives", 5);
	testPlayer.addCash("tens", 10);
	testPlayer.addCash("twenties", 15);
	testPlayer.addCash("fifties", 80);
	testPlayer.addCash("hundreds", -12);
	testPlayer.addCash("fiveHundreds", 10);

	assertEquals(testPlayer.getCash("ones"),          "20");
	assertEquals(testPlayer.getCash("fives"),          "5");
	assertEquals(testPlayer.getCash("tens"),          "10");
	assertEquals(testPlayer.getCash("twenties"),      "15");
	assertEquals(testPlayer.getCash("fifties"),       "80");
	assertEquals(testPlayer.getCash("hundreds"),     "-12");
	assertEquals(testPlayer.getCash("fiveHundreds"), "10");
	assertEquals(testPlayer.getCash("total"),         "8245");

	// TODO: More tests for negative inputs?

	// Test that correct values are set for the spreadMoney() method.
	testPlayer.spreadCash(500);
	assertEquals(testPlayer.getCash("fiveHundreds"), "16");
	assertEquals(testPlayer.getCash("hundreds"),     "2");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "2");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(100);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),    "82");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "2");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(50);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),    "164");
	assertEquals(testPlayer.getCash("twenties"),     "2");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(20);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),   "412");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(10);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),       "824");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(5);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),     "1649");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(1);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),      "8245");


	testPlayer.bankruptPlayer();
	testPlayer.spreadCash(500);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(100);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(50);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(20);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(10);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(5);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(1);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	// Try some invalid arguments to getCash().
	assertEquals(testPlayer.getCash("one"),  "Error! Incorrect argument.");
	assertEquals(testPlayer.getCash("1"),    "Error! Incorrect argument.");
	assertEquals(testPlayer.getCash("500"),  "Error! Incorrect argument.");
	assertEquals(testPlayer.getCash("500s"), "Error! Incorrect argument.");


	// image = new ImageIcon("images/p1Present.jpg"); // getImage()

	// public Player(int playerNumber)
	// public void addProperty(BoardCell property)
	// public void removeProperty(BoardCell property)
	// public void roll()
	// public Icon getImage()
	// public int  getPosition()
	// public void setPosition(int p)
	// TODO: ^-- test ALL these.
    }

    // ====================
    // CharacterSelect.java
    // ====================
    // TODO: ^---This


    /** Test all methods in the PropagandaOutlet class. */
    @Test
    public void testPropagandaOutlet() {
	// TODO: Removed this image
	PropagandaOutlet testPropOutlet = new PropagandaOutlet("testPropOutlet", new ImageIcon("images/yellow_template.jpg"), 40, 10, 60, 180, 350, 630, 800, 100, 18, 18);

	assertEquals(testPropOutlet.getName(), "testPropOutlet");
	//	    TODO: Test the image assigned during creation.
	// public Icon getImage() {
	// public void setImage(Icon newImage) {
	assertEquals(testPropOutlet.getX(), 18);
	assertEquals(testPropOutlet.getY(), 18);

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