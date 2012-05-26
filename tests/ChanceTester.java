import junit.framework.TestCase;
import org.junit.*;
import java.util.Random;

/** Class to test all methods inside the Chance class.
 *
 *  @author Daniel Neel */
public class ChanceTester extends TestCase {
    private IdeopolyGUI gui = new IdeopolyGUI("Ayn Rand");
    /** Grab all 4 players from the gui.*/
    private Player player1 = gui.player1;
    private Player player2 = gui.player2;
    private Player player3 = gui.player3;
    private Player player4 = gui.player4;

    // TODO: I added the carryOutActions() method to the Chance class. Make sure to have tests
    // for this.
    // TODO: Split this up into 16 different methods, one for each type of card?
    /** Test all methods in the Chance class. */
    @Test
	public void testChance() {
	// TODO: Later, is it possible for this method to go wrong? If so, test for those ways.

	Chance chanceCard = new Chance(1);
	assertEquals(chanceCard.getType(), 1);
	assertEquals(chanceCard.getText(), "Advance to Go (Collect $200)");

	doActionsAllPlayers(chanceCard, gui);

	assertSameCell(0);
	assertSameCash("1700");

	chanceCard = new Chance(2);
	assertEquals(chanceCard.getType(), 2);
	assertEquals(chanceCard.getText(), "Advance to Illinois Ave - if you pass Go, collect $200");

	// First check with players standing at Go. Then with them on Illinois. Then with 
	// them 1 past Illinois.
	doActionsAllPlayers(chanceCard, gui);
	assertSameCell(24);
	assertSameCash("1700");

	// Now they're starting on Illinois. If this somehow occurs, we have the Player
	// doing a trip around the board and picking up $200.
	doActionsAllPlayers(chanceCard, gui);

	assertSameCell(24);
	assertSameCash("1900");
	
	// And now start them at 1 cell after Illinois and test.
	changePositionAllPlayers(25);
	doActionsAllPlayers(chanceCard, gui);

	assertSameCell(24);
	assertSameCash("2100");


	// TODO: Refactor/loop this section. Lots of duplicate code.
	chanceCard = new Chance(3);
	assertEquals(chanceCard.getType(), 3);
	assertEquals(chanceCard.getText(), "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.");
	// TODO: Test the 10x amount thrown part. And the may buy it part.
	// Test this function's for all 4 players, where they land on all 3 possible Chance locations.
	changePositionAllPlayers(7);
	doActionsAllPlayers(chanceCard, gui);
	assertSameCell(5);

	changePositionAllPlayers(22);
	doActionsAllPlayers(chanceCard, gui);
	assertSameCell(25);

	changePositionAllPlayers(36);
	doActionsAllPlayers(chanceCard, gui);
	assertSameCell(35);

	// Now make sure that, when a player owns the property, players are charged proper rent.
	// Going into this case, each player had 2100 bucks total.
	gui.boardProperties[5].setOwner(gui.player1); // Reading RR
	gui.boardProperties[25].setOwner(gui.player1); // B & O RR
	gui.boardProperties[35].setOwner(gui.player2); // Short Line RR


	// TODO: The below tests should work, I just need to solve the problem of knowing
	// the value of the rollGenerator so I can assertEquals() cash values.


	// "If owned, throw dice and pay owner a total ten times the amount thrown."
	// Random rollGenerator = new Random();
	// int roll = 0;
	// int expectedCash = 0;

	// player1.setPosition(31);
	// player2.setPosition(31);
	// player3.setPosition(31);
	// player4.setPosition(31);
	// doActionsAllPlayers(player1, player2, player3, player4, chanceCard, gui);

	// TODO: Loop this/etc.
	// TODO: Little problem here. I need to know ahead of time what random value will be 
	// generated to test it. So I need to get that information from Chance.java
	// roll = rollGenerator.nextInt(6) + 1;
	// expectedCash = Integer.parseInt(player1.getCash("total")) - (roll * 10);
	// assertEquals(player1.getCash("total"), expectedCash);

	// roll = rollGenerator.nextInt(6) + 1;
	// expectedCash = Integer.parseInt(player2.getCash("total")) - (roll * 10);
        // assertEquals(player2.getCash("total"), expectedCash);

	// roll = rollGenerator.nextInt(6) + 1;
	// expectedCash = Integer.parseInt(player3.getCash("total")) - (roll * 10);
	// assertEquals(player3.getCash("total"), expectedCash);

	// roll = rollGenerator.nextInt(6) + 1;
	// expectedCash = Integer.parseInt(player4.getCash("total")) - (roll * 10);
	// assertEquals(player4.getCash("total"), expectedCash);

	// player1.setPosition(91);
	// player2.setPosition(91);
	// player3.setPosition(91);
	// player4.setPosition(91);
	// doActionsAllPlayers(player1, player2, player3, player4, chanceCard, gui);
	// assertEquals(player1.getCash("total"), 103);
        // assertEquals(player2.getCash("total"), 102);
	// assertEquals(player3.getCash("total"), 101);
	// assertEquals(player4.getCash("total"), 100);

	// player1.setPosition(147);
	// player2.setPosition(147);
	// player3.setPosition(147);
	// player4.setPosition(147);
	// doActionsAllPlayers(player1, player2, player3, player4, chanceCard, gui);
        // assertEquals(player1.getCash("total"), 143);
	// assertEquals(player2.getCash("total"), 142);
	// assertEquals(player3.getCash("total"), 141);
	// assertEquals(player4.getCash("total"), 140);

	// doActionsAllPlayers(player1, player2, player3, player4, chanceCard, gui);
	// Chance locations: 1 by Reading, 1 by B. &. O., 1 by Short Line
	// If Go is cell 1,  ^--8-6        ^---23-26      ^---37()-36()
	// Pattern is ^--chancecell-rrcell

	chanceCard = new Chance(4);
	assertEquals(chanceCard.getType(), 4);
	assertEquals(chanceCard.getText(), "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)");

	chanceCard = new Chance(5);
	assertEquals(chanceCard.getType(), 5);
	assertEquals(chanceCard.getText(), "Advance to St. Charles Place – if you pass Go, collect $200");
	// Start with clean Players.
	player1 = new Player(1, gui);
	player2 = new Player(2, gui);
	player3 = new Player(3, gui);
	player4 = new Player(4, gui);

	// Test when we start before/after St. Charles. Impossible to start at St. Charles (no
	// Chance card there), so we don't test that.
	// Starting before St. Charles (at Go).
	changePositionAllPlayers(0);
	doActionsAllPlayers(chanceCard, gui);

	assertSameCash("1500");
	assertSameCell(11);

	// Starting after St. Charles (at Electric Company).
	changePositionAllPlayers(12);
	doActionsAllPlayers(chanceCard, gui);

	assertSameCash("1700");
	assertSameCell(11);


	chanceCard = new Chance(6);
	assertEquals(chanceCard.getType(), 6);
	assertEquals(chanceCard.getText(), "Bank pays you dividend of $50");

	int p1Money = Integer.parseInt(player1.getCash("total"));
	int p2Money = Integer.parseInt(player2.getCash("total"));
	int p3Money = Integer.parseInt(player3.getCash("total"));
	int p4Money = Integer.parseInt(player4.getCash("total"));

	doActionsAllPlayers(chanceCard, gui);

	assertEquals(Integer.parseInt(player1.getCash("total")), p1Money + 50);
	assertEquals(Integer.parseInt(player2.getCash("total")), p2Money + 50);
	assertEquals(Integer.parseInt(player3.getCash("total")), p3Money + 50);
	assertEquals(Integer.parseInt(player4.getCash("total")), p4Money + 50);


	chanceCard = new Chance(7);
	assertEquals(chanceCard.getType(), 7);
	assertEquals(chanceCard.getText(), "Get out of Jail Free – this card may be kept until needed, or traded/sold");

	assertEquals(player1.getNumGOOJFCards(), 0);
	assertEquals(player2.getNumGOOJFCards(), 0);
	assertEquals(player3.getNumGOOJFCards(), 0);
	assertEquals(player4.getNumGOOJFCards(), 0);

	doActionsAllPlayers(chanceCard, gui);

	assertEquals(player1.getNumGOOJFCards(), 1);
	assertEquals(player2.getNumGOOJFCards(), 1);
	assertEquals(player3.getNumGOOJFCards(), 1);
	assertEquals(player4.getNumGOOJFCards(), 1);

	// Chance is on cells 8, 23, and 37.
	chanceCard = new Chance(8); 
	assertEquals(chanceCard.getType(), 8);
	assertEquals(chanceCard.getText(), "Go back 3 spaces");

	// Start with some clean Players.
	// TODO: Make a method for this(?)
	player1 = new Player(1, gui);
	player2 = new Player(2, gui);
	player3 = new Player(3, gui);
	player4 = new Player(4, gui);

	// Test on the first Chance spot.
	changePositionAllPlayers(7);
	doActionsAllPlayers(chanceCard, gui);
	assertSameCell(4);

	// TODO: Landed on property is income tax, which takes off $200.
	// TODO: This (and other below getCash()'s) isn't working currently. 
	//       Need the onLand() method first?
	// assertEquals(player1.getCash("total"), 1300);
	// assertEquals(player2.getCash("total"), 1300);
	// assertEquals(player3.getCash("total"), 1300);
	// assertEquals(player4.getCash("total"), 1300);


	// Test on the second Chance spot.
	changePositionAllPlayers(22);
	doActionsAllPlayers(chanceCard, gui);
	assertSameCell(19);

	// assertEquals(player1.getCash("total"), 1300);
	// assertEquals(player2.getCash("total"), 1300);
	// assertEquals(player3.getCash("total"), 1300);
	// assertEquals(player4.getCash("total"), 1300);


	// Test on the third (and last) Chance spot.
	changePositionAllPlayers(36);
	doActionsAllPlayers(chanceCard, gui);

	assertSameCell(33);

	// assertEquals(player1.getCash("total"), 1300);
	// assertEquals(player2.getCash("total"), 1300);
	// assertEquals(player3.getCash("total"), 1300);
	// assertEquals(player4.getCash("total"), 1300);


	chanceCard = new Chance(9);
	assertEquals(chanceCard.getType(), 9);
	assertEquals(chanceCard.getText(), "Go directly to Jail – do not pass Go, do not collect $200");

	assertEquals(player1.getJailStatus(), 0);
	assertEquals(player2.getJailStatus(), 0);
	assertEquals(player3.getJailStatus(), 0);
	assertEquals(player4.getJailStatus(), 0);

	doActionsAllPlayers(chanceCard, gui);

	assertEquals(player1.getJailStatus(), 3);
	assertEquals(player2.getJailStatus(), 3);
	assertEquals(player3.getJailStatus(), 3);
	assertEquals(player4.getJailStatus(), 3);

	assertSameCell(10);

	chanceCard = new Chance(10);
	assertEquals(chanceCard.getType(), 10);
	assertEquals(chanceCard.getText(), "Make general repairs on all your property – for each house pay $25 – for each hotel $100");
	// TODO: Test this for 0 houses/hotels, 0 houses/n hotels, n houses/0 hotels, n houses/n hotels.


	chanceCard = new Chance(11);
	assertEquals(chanceCard.getType(), 11);
	assertEquals(chanceCard.getText(), "Pay poor tax of $15");
	// Make some new Players.
	player1 = new Player(1, gui);
	player2 = new Player(2, gui);
	player3 = new Player(3, gui);
	player4 = new Player(4, gui);

	assertSameCash("1500");
	doActionsAllPlayers(chanceCard, gui);
	assertSameCash("1485");


	chanceCard = new Chance(12);
	assertEquals(chanceCard.getType(), 12);
	assertEquals(chanceCard.getText(), "Take a trip to Reading Railroad – if you pass Go, collect $200");

	player1.changeCell(0, gui);  // Test when we start on Go.
	chanceCard.doActions(player1, gui, player2, player3, player4);
	assertEquals(player1.getCell(), gui.boardProperties[5]);
	assertEquals(player1.getCash("total"), "1485");

	player1.changeCell(3, gui); // Test when we start before Reading RR (Baltic Av. here).
	chanceCard.doActions(player1, gui, player2, player3, player4);
	assertEquals(player1.getCell(), gui.boardProperties[5]);
	assertEquals(player1.getCash("total"), "1485");

	player1.changeCell(5, gui); // Test when we start on Reading RR.
	chanceCard.doActions(player1, gui, player2, player3, player4);
	assertEquals(player1.getCell(), gui.boardProperties[5]);
	assertEquals(player1.getCash("total"), "1485"); // TODO: Should this count as passing
	// Go? Even though doing this is impossible by the game's rules...

	player1.changeCell(6, gui); // Test when we start after Reading RR.
	chanceCard.doActions(player1, gui, player2, player3, player4);
	assertEquals(player1.getCell(), gui.boardProperties[5]);
	assertEquals(player1.getCash("total"), "1685");


	chanceCard = new Chance(13);
	assertEquals(chanceCard.getType(), 13);
	assertEquals(chanceCard.getText(), "Take a walk on the Boardwalk – advance token to Boardwalk");
	// TODO: Also test this when the property is owned and the player's charged rent.
	doActionsAllPlayers(chanceCard, gui);

	assertSameCell(39);

	chanceCard = new Chance(14);
	assertEquals(chanceCard.getType(), 14);
	assertEquals(chanceCard.getText(), "You have been elected chairman of the board – pay each player $50");
	// First we test when players will not be bankrupt by this card, so make some new Players.
	player1 = new Player(1, gui);
	player2 = new Player(2, gui);
	player3 = new Player(3, gui);
	player4 = new Player(4, gui);
	// TODO: I think the problem here is that Chance.java's doActions() is referring to and 
	// doing actions on the players associated with the GUI. The players I create here are
	// not the same Players as player1/2/3/4 in gui.
	// Fix this problem and then re-enable the tests.
	chanceCard.doActions(player1, gui, player2, player3, player4);
	assertEquals(player1.getCash("total"), "1350");
	assertEquals(player2.getCash("total"), "1550");
	assertEquals(player3.getCash("total"), "1550");
	assertEquals(player4.getCash("total"), "1550");

	chanceCard.doActions(player2, gui, player1, player3, player4);
	assertEquals(player1.getCash("total"), "1400");
	assertEquals(player2.getCash("total"), "1400");
	assertEquals(player3.getCash("total"), "1600");
	assertEquals(player4.getCash("total"), "1600");

	chanceCard.doActions(player3, gui, player1, player2, player4);
	assertEquals(player1.getCash("total"), "1450");
	assertEquals(player2.getCash("total"), "1450");
	assertEquals(player3.getCash("total"), "1450");
	assertEquals(player4.getCash("total"), "1650");

	chanceCard.doActions(player4, gui, player1, player2, player3);
	assertSameCash("1500");

	// TODO: Then test when the main player will be bankrupt.
	//	player1.addCash("ones", );


	// TODO: Test this when the main Player is going to go bankrupt.
	// Then test when 1/2/n other players are bankrupt. Make sure they're not given money.
	//       Or is that a general thing that should be tested elsewhere?

	chanceCard = new Chance(15);
	assertEquals(chanceCard.getType(), 15);
	assertEquals(chanceCard.getText(), "Your building loan matures – collect $150");
	// Make some new Players.
	player1 = new Player(1, gui);
	player2 = new Player(2, gui);
	player3 = new Player(3, gui);
	player4 = new Player(4, gui);

	doActionsAllPlayers(chanceCard, gui);

	assertSameCash("1650");
	// TODO: Tests for when n number of players are bankrupt and they somehow draw this card.


	chanceCard = new Chance(16);
	assertEquals(chanceCard.getType(), 16);
	assertEquals(chanceCard.getText(), "You have won a crossword competition - collect $100.");
	// Make some new Players.
	player1 = new Player(1, gui);
	player2 = new Player(2, gui);
	player3 = new Player(3, gui);
	player4 = new Player(4, gui);

	doActionsAllPlayers(chanceCard, gui);
	assertSameCash("1600");
	// TODO: Tests for when n number of players are bankrupt and they somehow draw this card.


	// Make sure un-allowed card values don't affect text, etc.
	chanceCard = new Chance(17);
	assertEquals(chanceCard.getType(), 17);
	assertEquals(chanceCard.getText(), null);

	chanceCard = new Chance(-1);
	assertEquals(chanceCard.getType(), -1);
	assertEquals(chanceCard.getText(), null);

	chanceCard = new Chance(0);
	assertEquals(chanceCard.getType(), 0);
	assertEquals(chanceCard.getText(), null);
    }

    // ===============
    // Helper methods.
    // ===============

    // TODO: Javadocs for these methods
    // TODO: Reduce the excessive amount of arguments here. For example, just require 'gui',
    // then use gui.player1/2/3/4, etc. Can also get rid of gui probably.
    public void doActionsAllPlayers(Chance card, IdeopolyGUI gui) {
	card.doActions(player1, gui, player2, player3, player4);
	card.doActions(player2, gui, player1, player3, player4);
	card.doActions(player3, gui, player1, player2, player4);
	card.doActions(player4, gui, player1, player2, player3);
    }

    public void changePositionAllPlayers(int cell) {
	player1.changeCell(cell, gui);
	player2.changeCell(cell, gui);
	player3.changeCell(cell, gui);
	player4.changeCell(cell, gui);
    }

    /** Check that the total cash value v is the same for every player.*/
    public void assertSameCash(String v) {
	assertEquals(player1.getCash("total"), v);
	assertEquals(player2.getCash("total"), v);
	assertEquals(player3.getCash("total"), v);
	assertEquals(player4.getCash("total"), v);
    }

    /** Ensure that all four players are standing on the same cell. */
    public void assertSameCell(int cell) {
	assertEquals(player1.getCell(), gui.boardProperties[cell]);
	assertEquals(player2.getCell(), gui.boardProperties[cell]);
	assertEquals(player3.getCell(), gui.boardProperties[cell]);
	assertEquals(player4.getCell(), gui.boardProperties[cell]);
    }
}

// Test coverage status:
// (Note that this means basic tests are in place for all relevant methods/conditions. 
//  It does NOT mean that all tests pass, or that every single possible case is tested.
//  Just testing that the basic, expected cases work correctly.)
// DONE: Chance(1)
// DONE: Chance(2)
// TODO: Chance(3)
// TODO: Chance(4)
// DONE: Chance(5)
// DONE: Chance(6)
// DONE: Chance(7)
// DONE: Chance(8)
// DONE: Chance(9)
// TODO: Chance(10)
// DONE: Chance(11)
// DONE: Chance(12)
// DONE: Chance(13)
// DONE: Chance(14)
// DONE: Chance(15)
// DONE: Chance(16)

// TODO: After these are in place, split them into separate methods for each card type.
