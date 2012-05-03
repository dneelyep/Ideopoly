import junit.framework.TestCase;
import org.junit.*;

/** Class to test all methods inside the Chance class.
 *
 *  @author Daniel Neel */
public class ChanceTester extends TestCase {
    // TODO: I added the carryOutActions() method to the Chance class. Make sure to have tests
    // for this.
    // TODO: Split this up into 16 different methods, one for each type of card?
    /** Test all methods in the Chance class. */
    @Test
	public void testChance() {
	// TODO: Later, is it possible for this method to go wrong? If so, test for those ways.
	IdeopolyGUI gui       = new IdeopolyGUI("Ayn Rand");

	Chance chanceCard = new Chance(1);
	assertEquals(chanceCard.getType(), 1);
	assertEquals(chanceCard.getText(), "Advance to Go (Collect $200)");

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	// TODO: Could refactor this. p1's always getPosition(num), p2 getPosition(num - 1), etc.
	assertEquals(gui.player1.getPosition(), 3);
	assertEquals(gui.player2.getPosition(), 2);
	assertEquals(gui.player3.getPosition(), 1);
	assertEquals(gui.player4.getPosition(), 0);
	assertEquals(gui.player1.getCash("total"), "1700");
	assertEquals(gui.player2.getCash("total"), "1700");
	assertEquals(gui.player3.getCash("total"), "1700");
	assertEquals(gui.player4.getCash("total"), "1700");

	chanceCard = new Chance(2);
	assertEquals(chanceCard.getType(), 2);
	assertEquals(chanceCard.getText(), "Advance to Illinois Ave - if you pass Go, collect $200");

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	// First check with players standing at Go. Then with them on Illinois. Then with 
	// them 1 past Illinois.
	assertEquals(gui.player1.getPosition(), 99);
	assertEquals(gui.player2.getPosition(), 98);
	assertEquals(gui.player3.getPosition(), 97);
	assertEquals(gui.player4.getPosition(), 96);
	assertEquals(gui.player1.getCash("total"), "1700");
	assertEquals(gui.player2.getCash("total"), "1700");
	assertEquals(gui.player3.getCash("total"), "1700");
	assertEquals(gui.player4.getCash("total"), "1700");

	// Now they're starting on Illinois.
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getPosition(), 99);
	assertEquals(gui.player2.getPosition(), 98);
	assertEquals(gui.player3.getPosition(), 97);
	assertEquals(gui.player4.getPosition(), 96);
	assertEquals(gui.player1.getCash("total"), "1900");
	assertEquals(gui.player2.getCash("total"), "1900");
	assertEquals(gui.player3.getCash("total"), "1900");
	assertEquals(gui.player4.getCash("total"), "1900");

	// And now start them at 1 cell after Illinois and test.
	changePositionAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, 103);

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getPosition(), 99);
	assertEquals(gui.player2.getPosition(), 98);
	assertEquals(gui.player3.getPosition(), 97);
	assertEquals(gui.player4.getPosition(), 96);
	assertEquals(gui.player1.getCash("total"), "2100");
	assertEquals(gui.player2.getCash("total"), "2100");
	assertEquals(gui.player3.getCash("total"), "2100");
	assertEquals(gui.player4.getCash("total"), "2100");



	chanceCard = new Chance(3);
	assertEquals(chanceCard.getType(), 3);
	assertEquals(chanceCard.getText(), "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.");

	chanceCard = new Chance(4);
	assertEquals(chanceCard.getType(), 4);
	assertEquals(chanceCard.getText(), "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)");

	chanceCard = new Chance(5);
	assertEquals(chanceCard.getType(), 5);
	assertEquals(chanceCard.getText(), "Advance to St. Charles Place – if you pass Go, collect $200");
	// Start with clean Players.
	gui.player1 = new Player(1);
	gui.player2 = new Player(2);
	gui.player3 = new Player(3);
	gui.player4 = new Player(4);

	// Test when we start before, at, and after St. Charles.
	// Starting before St. Charles (at Go).
	changePositionAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, 3);
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getCash("total"), "1500");
	assertEquals(gui.player2.getCash("total"), "1500");
	assertEquals(gui.player3.getCash("total"), "1500");
	assertEquals(gui.player4.getCash("total"), "1500");

	assertEquals(gui.player1.getPosition(), 47);
	assertEquals(gui.player2.getPosition(), 46);
	assertEquals(gui.player3.getPosition(), 45);
	assertEquals(gui.player4.getPosition(), 44);

	// Starting at St. Charles.
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	// TODO: These next two tests fail. Fix them.
	// assertEquals(gui.player1.getCash("total"), "1700");
	// assertEquals(gui.player2.getCash("total"), "1700");
	// assertEquals(gui.player3.getCash("total"), "1700");
	// assertEquals(gui.player4.getCash("total"), "1700");

	assertEquals(gui.player1.getPosition(), 47);
	assertEquals(gui.player2.getPosition(), 46);
	assertEquals(gui.player3.getPosition(), 45);
	assertEquals(gui.player4.getPosition(), 44);

	// Starting after St. Charles (at Electric Company).
	changePositionAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, 51);
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	// assertEquals(gui.player1.getCash("total"), "1900");
	// assertEquals(gui.player2.getCash("total"), "1900");
	// assertEquals(gui.player3.getCash("total"), "1900");
	// assertEquals(gui.player4.getCash("total"), "1900");

	assertEquals(gui.player1.getPosition(), 47);
	assertEquals(gui.player2.getPosition(), 46);
	assertEquals(gui.player3.getPosition(), 45);
	assertEquals(gui.player4.getPosition(), 44);


	chanceCard = new Chance(6);
	assertEquals(chanceCard.getType(), 6);
	assertEquals(chanceCard.getText(), "Bank pays you dividend of $50");

	int p1Money = Integer.parseInt(gui.player1.getCash("total"));
	int p2Money = Integer.parseInt(gui.player2.getCash("total"));
	int p3Money = Integer.parseInt(gui.player3.getCash("total"));
	int p4Money = Integer.parseInt(gui.player4.getCash("total"));

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(Integer.parseInt(gui.player1.getCash("total")), p1Money + 50);
	assertEquals(Integer.parseInt(gui.player2.getCash("total")), p2Money + 50);
	assertEquals(Integer.parseInt(gui.player3.getCash("total")), p3Money + 50);
	assertEquals(Integer.parseInt(gui.player4.getCash("total")), p4Money + 50);



	chanceCard = new Chance(7);
	assertEquals(chanceCard.getType(), 7);
	assertEquals(chanceCard.getText(), "Get out of Jail Free – this card may be kept until needed, or traded/sold");

	assertEquals(gui.player1.getNumGOOJFCards(), 0);
	assertEquals(gui.player2.getNumGOOJFCards(), 0);
	assertEquals(gui.player3.getNumGOOJFCards(), 0);
	assertEquals(gui.player4.getNumGOOJFCards(), 0);

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getNumGOOJFCards(), 1);
	assertEquals(gui.player2.getNumGOOJFCards(), 1);
	assertEquals(gui.player3.getNumGOOJFCards(), 1);
	assertEquals(gui.player4.getNumGOOJFCards(), 1);

	// Chance is on cells 8, 23, and 37.
	chanceCard = new Chance(8); 
	assertEquals(chanceCard.getType(), 8);
	assertEquals(chanceCard.getText(), "Go back 3 spaces");

	// Start with some clean Players.
	// TODO: Make a method for this(?)
	gui.player1 = new Player(1);
	gui.player2 = new Player(2);
	gui.player3 = new Player(3);
	gui.player4 = new Player(4);

	// Test on the first Chance spot.
	changePositionAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, 31);
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getPosition(), 19);
	assertEquals(gui.player2.getPosition(), 18);
	assertEquals(gui.player3.getPosition(), 17);
	assertEquals(gui.player4.getPosition(), 16);

	// TODO: Landed on property is income tax, which takes off $200.
	// TODO: This (and other below getCash()'s) isn't working currently. 
	//       Need the onLand() method first?
	// assertEquals(gui.player1.getCash("total"), 1300);
	// assertEquals(gui.player2.getCash("total"), 1300);
	// assertEquals(gui.player3.getCash("total"), 1300);
	// assertEquals(gui.player4.getCash("total"), 1300);


	// Test on the second Chance spot.
	changePositionAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, 91);
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getPosition(), 79);
	assertEquals(gui.player2.getPosition(), 78);
	assertEquals(gui.player3.getPosition(), 77);
	assertEquals(gui.player4.getPosition(), 76);

	// assertEquals(gui.player1.getCash("total"), 1300);
	// assertEquals(gui.player2.getCash("total"), 1300);
	// assertEquals(gui.player3.getCash("total"), 1300);
	// assertEquals(gui.player4.getCash("total"), 1300);


	// Test on the third (and last) Chance spot.
	changePositionAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, 147);
	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getPosition(), 135);
	assertEquals(gui.player2.getPosition(), 134);
	assertEquals(gui.player3.getPosition(), 133);
	assertEquals(gui.player4.getPosition(), 132);

	// assertEquals(gui.player1.getCash("total"), 1300);
	// assertEquals(gui.player2.getCash("total"), 1300);
	// assertEquals(gui.player3.getCash("total"), 1300);
	// assertEquals(gui.player4.getCash("total"), 1300);


	chanceCard = new Chance(9);
	assertEquals(chanceCard.getType(), 9);
	assertEquals(chanceCard.getText(), "Go directly to Jail – do not pass Go, do not collect $200");

	assertEquals(gui.player1.getJailStatus(), 0);
	assertEquals(gui.player2.getJailStatus(), 0);
	assertEquals(gui.player3.getJailStatus(), 0);
	assertEquals(gui.player4.getJailStatus(), 0);

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getJailStatus(), 3);
	assertEquals(gui.player2.getJailStatus(), 3);
	assertEquals(gui.player3.getJailStatus(), 3);
	assertEquals(gui.player4.getJailStatus(), 3);

	assertEquals(gui.player1.getPosition(), 43);
	assertEquals(gui.player2.getPosition(), 42);
	assertEquals(gui.player3.getPosition(), 41);
	assertEquals(gui.player4.getPosition(), 40);


	chanceCard = new Chance(10);
	assertEquals(chanceCard.getType(), 10);
	assertEquals(chanceCard.getText(), "Make general repairs on all your property – for each house pay $25 – for each hotel $100");
	// TODO: Test this for 0 houses/hotels, 0 houses/n hotels, n houses/0 hotels, n houses/n hotels.


	chanceCard = new Chance(11);
	assertEquals(chanceCard.getType(), 11);
	assertEquals(chanceCard.getText(), "Pay poor tax of $15");
	// Make some new Players.
	gui.player1 = new Player(1);
	gui.player2 = new Player(2);
	gui.player3 = new Player(3);
	gui.player4 = new Player(4);

	assertEquals(gui.player1.getCash("total"), "1500");
	assertEquals(gui.player2.getCash("total"), "1500");
	assertEquals(gui.player3.getCash("total"), "1500");
	assertEquals(gui.player4.getCash("total"), "1500");

	doActionsAllPlayers(gui.player1, gui.player2, gui.player3, gui.player4, chanceCard, gui);

	assertEquals(gui.player1.getCash("total"), "1485");
	assertEquals(gui.player2.getCash("total"), "1485");
	assertEquals(gui.player3.getCash("total"), "1485");
	assertEquals(gui.player4.getCash("total"), "1485");


	chanceCard = new Chance(12);
	assertEquals(chanceCard.getType(), 12);
	assertEquals(chanceCard.getText(), "Take a trip to Reading Railroad – if you pass Go, collect $200");

	chanceCard = new Chance(13);
	assertEquals(chanceCard.getType(), 13);
	assertEquals(chanceCard.getText(), "Take a walk on the Boardwalk – advance token to Boardwalk");

	chanceCard = new Chance(14);
	assertEquals(chanceCard.getType(), 14);
	assertEquals(chanceCard.getText(), "You have been elected chairman of the board – pay each player $50");
	// Make some new Players.
	gui.player1 = new Player(1);
	gui.player2 = new Player(2);
	gui.player3 = new Player(3);
	gui.player4 = new Player(4);

	// TODO: Test this when the main Player is going to go bankrupt.



	chanceCard = new Chance(15);
	assertEquals(chanceCard.getType(), 15);
	assertEquals(chanceCard.getText(), "Your building loan matures – collect $150");

	chanceCard = new Chance(16);
	assertEquals(chanceCard.getType(), 16);
	assertEquals(chanceCard.getText(), "You have won a crossword competition - collect $100.");


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

    public void doActionsAllPlayers(Player p1, Player p2, Player p3, Player p4, Chance card, IdeopolyGUI gui) {
	card.doActions(p1, gui);
	card.doActions(p2, gui);
	card.doActions(p3, gui);
	card.doActions(p4, gui);
    }

    public void changePositionAllPlayers(Player p1, Player p2, Player p3, Player p4, int pos) {
	p1.changePosition(pos);
	p2.changePosition(pos);
	p3.changePosition(pos);
	p4.changePosition(pos);
    }
}

// Test coverage status:
// (Note that this means tests are in place for all relevant methods/conditions. It does NOT mean
//  that all tests pass.)
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
// TODO: Chance(12)
// TODO: Chance(13)
// TODO: Chance(14)
// TODO: Chance(15)
// TODO: Chance(16)

// TODO: After these are in place, split them into separate methods
