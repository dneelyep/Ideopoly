import junit.framework.TestCase;
import org.junit.*;

/** Class to test all methods inside the Chance class.
 *
 *  @author Daniel Neel */
public class ChanceTester extends TestCase {
    // ============
    // Chance tests
    // ============
    // TODO: I added the carryOutActions() method to the Chance class. Make sure to have tests
    // for this.
    /** Test all methods in the Chance class. */
    @Test
	public void testChance() {
	// TODO: Later, is it possible for this method to go wrong? If so, test for those ways.
	IdeopolyGUI gui       = new IdeopolyGUI("Ayn Rand");

	Chance chanceCard = new Chance(1);
	assertEquals(chanceCard.getType(), 1);
	assertEquals(chanceCard.getText(), "Advance to Go (Collect $200)");

	chanceCard.doActions(gui.player1, gui);
	chanceCard.doActions(gui.player2, gui);
	chanceCard.doActions(gui.player3, gui);
	chanceCard.doActions(gui.player4, gui);

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

	// TODO: This block of stuff is a great chance for refactoring.
	chanceCard.doActions(gui.player1, gui);
	chanceCard.doActions(gui.player2, gui);
	chanceCard.doActions(gui.player3, gui);
	chanceCard.doActions(gui.player4, gui);

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
	chanceCard.doActions(gui.player1, gui);
	chanceCard.doActions(gui.player2, gui);
	chanceCard.doActions(gui.player3, gui);
	chanceCard.doActions(gui.player4, gui);

	assertEquals(gui.player1.getPosition(), 99);
	assertEquals(gui.player2.getPosition(), 98);
	assertEquals(gui.player3.getPosition(), 97);
	assertEquals(gui.player4.getPosition(), 96);
	assertEquals(gui.player1.getCash("total"), "1900");
	assertEquals(gui.player2.getCash("total"), "1900");
	assertEquals(gui.player3.getCash("total"), "1900");
	assertEquals(gui.player4.getCash("total"), "1900");

	// And now start them at 1 cell after Illinois and test.
	gui.player1.changePosition(103);
	gui.player2.changePosition(103);
	gui.player3.changePosition(103);
	gui.player4.changePosition(103);

	chanceCard.doActions(gui.player1, gui);
	chanceCard.doActions(gui.player2, gui);
	chanceCard.doActions(gui.player3, gui);
	chanceCard.doActions(gui.player4, gui);

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

	chanceCard = new Chance(6);
	assertEquals(chanceCard.getType(), 6);
	assertEquals(chanceCard.getText(), "Bank pays you dividend of $50");

	chanceCard = new Chance(7);
	assertEquals(chanceCard.getType(), 7);
	assertEquals(chanceCard.getText(), "Get out of Jail Free – this card may be kept until needed, or traded/sold");

	chanceCard = new Chance(8); 
	assertEquals(chanceCard.getType(), 8);
	assertEquals(chanceCard.getText(), "Go back 3 spaces");

	chanceCard = new Chance(9);
	assertEquals(chanceCard.getType(), 9);
	assertEquals(chanceCard.getText(), "Go directly to Jail – do not pass Go, do not collect $200");

	chanceCard = new Chance(10);
	assertEquals(chanceCard.getType(), 10);
	assertEquals(chanceCard.getText(), "Make general repairs on all your property – for each house pay $25 – for each hotel $100");

	chanceCard = new Chance(11);
	assertEquals(chanceCard.getType(), 11);
	assertEquals(chanceCard.getText(), "Pay poor tax of $15");

	chanceCard = new Chance(12);
	assertEquals(chanceCard.getType(), 12);
	assertEquals(chanceCard.getText(), "Take a trip to Reading Railroad – if you pass Go, collect $200");

	chanceCard = new Chance(13);
	assertEquals(chanceCard.getType(), 13);
	assertEquals(chanceCard.getText(), "Take a walk on the Boardwalk – advance token to Boardwalk");

	chanceCard = new Chance(14);
	assertEquals(chanceCard.getType(), 14);
	assertEquals(chanceCard.getText(), "You have been elected chairman of the board – pay each player $50");

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
}