import junit.framework.TestCase;
import javax.swing.*;
import org.junit.*;

/** Class to test all methods inside the CommunityChest class.
 *
 *  @author Daniel Neel */
public class CommunityChestTester extends TestCase {
    private CommunityChest commChestCard;
    private IdeopolyGUI    gui = new IdeopolyGUI("Test GUI");
    private Player         p1  = new Player(1, gui);
    private Player         p2  = new Player(2, gui);
    private Player         p3  = new Player(3, gui);
    private Player         p4  = new Player(4, gui);

    // TODO: Javadocs for all methods here.
    @Test
    public void testCommChest1() {
	commChestCard = new CommunityChest(1);
	assertEquals(commChestCard.getType(), 1);
	assertEquals(commChestCard.getText(), "Advance to Go (Collect $200)");

	// Ensure that this method works properly on all three Comm. Chest spots and for all Players.
	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	// Make sure the players land on the right spot and get the right amount of $.
	TestHelper.assertSameCash("1700", p1, p2, p3, p4);
	TestHelper.assertSameCell(0, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1900", p1, p2, p3, p4);
	TestHelper.assertSameCell(0, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("2100", p1, p2, p3, p4);
	TestHelper.assertSameCell(0, gui, p1, p2, p3, p4);
    }

    @Test
    public void testCommChest2() {
	commChestCard = new CommunityChest(2);
	assertEquals(commChestCard.getType(), 2);
	assertEquals(commChestCard.getText(), "Bank error in your favor – collect $200");

	// Test this on all three Comm. Chest spots and for all Players.
	// Make sure the players get the right amount of money and that they're 
	// on the correct BoardCells.
	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1700", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1900", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("2100", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);
    }

    @Test
    public void testCommChest3() {
	commChestCard = new CommunityChest(3);
	assertEquals(commChestCard.getType(), 3);
	assertEquals(commChestCard.getText(), "Doctor's fees – Pay $50");
    }

    @Test
    public void testCommChest4() {
	commChestCard = new CommunityChest(4);
	assertEquals(commChestCard.getType(), 4);
	assertEquals(commChestCard.getText(), "Get Out of Jail Free – this card may be kept until needed, or sold");

	assertEquals(p1.getNumGOOJFCards(), 0);
	assertEquals(p2.getNumGOOJFCards(), 0);
	assertEquals(p3.getNumGOOJFCards(), 0);
	assertEquals(p4.getNumGOOJFCards(), 0);

	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);

	assertEquals(p1.getNumGOOJFCards(), 1);
	assertEquals(p2.getNumGOOJFCards(), 1);
	assertEquals(p3.getNumGOOJFCards(), 1);
	assertEquals(p4.getNumGOOJFCards(), 1);

	// TODO: And then any weird situations.
    }

    @Test
    public void testCommChest5() {
	commChestCard = new CommunityChest(5);
	assertEquals(commChestCard.getType(), 5);
	assertEquals(commChestCard.getText(), "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");

	// Test this on all 3 CommChest cells with all 4 players, and make sure 
	// they land on the correct spot and don't collect $200.
	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1500", p1, p2, p3, p4);
	TestHelper.assertSameCell(10, gui, p1, p2, p3, p4);
	assertEquals(p1.getJailStatus(), 3);
	assertEquals(p2.getJailStatus(), 3);
	assertEquals(p3.getJailStatus(), 3);
	assertEquals(p4.getJailStatus(), 3);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1500", p1, p2, p3, p4);
	TestHelper.assertSameCell(10, gui, p1, p2, p3, p4);
	assertEquals(p1.getJailStatus(), 3);
	assertEquals(p2.getJailStatus(), 3);
	assertEquals(p3.getJailStatus(), 3);
	assertEquals(p4.getJailStatus(), 3);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1500", p1, p2, p3, p4);
	TestHelper.assertSameCell(10, gui, p1, p2, p3, p4);
	assertEquals(p1.getJailStatus(), 3);
	assertEquals(p2.getJailStatus(), 3);
	assertEquals(p3.getJailStatus(), 3);
	assertEquals(p4.getJailStatus(), 3);

	// TODO: And then test any odd cases that could come up.
    }

    @Test
    public void testCommChest6() {
	commChestCard = new CommunityChest(6);
	assertEquals(commChestCard.getType(), 6);
	assertEquals(commChestCard.getText(), "It is your birthday - Collect $10 from each player");
    }

    @Test
    public void testCommChest7() {
	commChestCard = new CommunityChest(7);
	assertEquals(commChestCard.getType(), 7);
	assertEquals(commChestCard.getText(), "Grand Opera Night – collect $50 from every player for opening night seats");
    }

    @Test
    public void testCommChest8() {
	commChestCard = new CommunityChest(8);
	assertEquals(commChestCard.getType(), 8);
	assertEquals(commChestCard.getText(), "Income Tax refund – collect $20");

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1520", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1540", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1560", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);
	// TODO: Then is it possible to test any out of whack cases?
    }

    @Test
    public void testCommChest9() {
	commChestCard = new CommunityChest(9);
	assertEquals(commChestCard.getType(), 9);
	assertEquals(commChestCard.getText(), "Life Insurance Matures – collect $100");

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1600", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1700", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1800", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: And then any screwed cases here.
    }

    @Test
    public void testCommChest10() {
	commChestCard = new CommunityChest(10);
	assertEquals(commChestCard.getType(), 10);
	assertEquals(commChestCard.getText(), "Pay Hospital Fees of $100");

	TestHelper.assertSameCash("1500", p1, p2, p3, p4);

	// TODO: To enable the below tests, I need a way of removing an arbitrary amount
	// of money from a player, rather than dealing directly with units of ones, fives, tens, etc.

	// TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	// TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	// TestHelper.assertSameCash("1400", p1, p2, p3, p4);
	// TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	// TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	// TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	// TestHelper.assertSameCash("1300", p1, p2, p3, p4);
	// TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	// TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	// TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	// TestHelper.assertSameCash("1200", p1, p2, p3, p4);
	// TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: Now test this when players will go bankrupt.
    }

    @Test
    public void testCommChest11() {
	commChestCard = new CommunityChest(11);
	assertEquals(commChestCard.getType(), 11);
	assertEquals(commChestCard.getText(), "Pay School Fees of $50");

	// TODO: Test this when players have adequate money, and when they'll become bankrupt.
    }

    @Test
    public void testCommChest12() {
	commChestCard = new CommunityChest(12);
	assertEquals(commChestCard.getType(), 12);
	assertEquals(commChestCard.getText(), "Receive $25 Consultancy Fee");

	// TODO: Make a helper method for this type of card? IE: all players
	//       move to a spot, doactions, gain money, and land on a final spot.

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1525", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1550", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1575", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest13() {
	commChestCard = new CommunityChest(13);
	assertEquals(commChestCard.getType(), 13);
	assertEquals(commChestCard.getText(), "You are assessed for street repairs – $40 per house, $115 per hotel");

    }

    @Test
    public void testCommChest14() {
	commChestCard = new CommunityChest(14);
	assertEquals(commChestCard.getType(), 14);
	assertEquals(commChestCard.getText(), "You have won second prize in a beauty contest– collect $10");

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1510", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1520", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1530", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest15() {
	commChestCard = new CommunityChest(15);
	assertEquals(commChestCard.getType(), 15);
	assertEquals(commChestCard.getText(), "You inherit $100");

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1600", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1700", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1800", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest16() {
	commChestCard = new CommunityChest(16);
	assertEquals(commChestCard.getType(), 16);
	assertEquals(commChestCard.getText(), "From sale of stock you get $50");

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1550", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1600", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1650", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest17() {
	commChestCard = new CommunityChest(17);
	assertEquals(commChestCard.getType(), 17);
	assertEquals(commChestCard.getText(), "Holiday Fund matures - Receive $100");

	TestHelper.changeCellAllPlayers(2, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1600", p1, p2, p3, p4);
	TestHelper.assertSameCell(2, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(17, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1700", p1, p2, p3, p4);
	TestHelper.assertSameCell(17, gui, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(33, gui, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayersCommChest(commChestCard, gui, p1, p2, p3, p4);
	TestHelper.assertSameCash("1800", p1, p2, p3, p4);
	TestHelper.assertSameCell(33, gui, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    /** Test incorrect constructor values, etc. */
    public void testBrokenCommChest() {
	// Make sure un-allowed card types produce incorrect text, etc.
	commChestCard = new CommunityChest(18);
	assertEquals(commChestCard.getType(), 18);

	assertEquals(commChestCard.getText(), null);

	commChestCard = new CommunityChest(-1);
	assertEquals(commChestCard.getType(), -1);
	assertEquals(commChestCard.getText(), null);

	commChestCard = new CommunityChest(0);
	assertEquals(commChestCard.getType(), 0);
	assertEquals(commChestCard.getText(), null);
    }
}

// Test coverage status:
// (Note that this means basic tests are in place for all relevant methods/conditions. 
//  It does NOT mean that all tests pass, or that every single possible case is tested.
//  Just testing that the basic, expected cases work correctly.)
// DONE: 1
// DONE: 2
// TODO: 3
// DONE: 4
// DONE: 5
// TODO: 6
// TODO: 7
// DONE: 8
// DONE: 9
// TODO: 10
// TODO: 11
// DONE: 12
// TODO: 13
// DONE: 14
// DONE: 15
// DONE: 16
// DONE: 17