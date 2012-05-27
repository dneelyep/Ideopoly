import junit.framework.TestCase;
import javax.swing.*;
import org.junit.*;

/** Class to test all methods inside the CommunityChest class.
 *
 *  @author Daniel Neel */
public class CommunityChestTester extends TestCase {
    private CommunityChest testCommChestCard;
    private IdeopolyGUI gui     = new IdeopolyGUI("Test GUI");
    private Player      player1 = new Player(1, gui);
    private Player      player2 = new Player(2, gui);
    private Player      player3 = new Player(3, gui);
    private Player      player4 = new Player(4, gui);

    // TODO: Javadocs for all methods here.
    @Test
    public void testCommChest1() {
	testCommChestCard = new CommunityChest(1);
	assertEquals(testCommChestCard.getType(), 1);
	assertEquals(testCommChestCard.getText(), "Advance to Go (Collect $200)");

	// Ensure that this method works properly on all three Comm. Chest spots and for all Players.
	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	// Make sure the players land on the right spot and get the right amount of $.
	TestHelper.assertSameCash("1700", player1, player2, player3, player4);
	TestHelper.assertSameCell(0, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1900", player1, player2, player3, player4);
	TestHelper.assertSameCell(0, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("2100", player1, player2, player3, player4);
	TestHelper.assertSameCell(0, gui, player1, player2, player3, player4);
    }

    @Test
    public void testCommChest2() {
	testCommChestCard = new CommunityChest(2);
	assertEquals(testCommChestCard.getType(), 2);
	assertEquals(testCommChestCard.getText(), "Bank error in your favor – collect $200");

	// Test this on all three Comm. Chest spots and for all Players.
	// Make sure the players get the right amount of money and that they're 
	// on the correct BoardCells.
	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1700", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1900", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("2100", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);
    }

    @Test
    public void testCommChest3() {
	testCommChestCard = new CommunityChest(3);
	assertEquals(testCommChestCard.getType(), 3);
	assertEquals(testCommChestCard.getText(), "Doctor's fees – Pay $50");
    }

    @Test
    public void testCommChest4() {
	testCommChestCard = new CommunityChest(4);
	assertEquals(testCommChestCard.getType(), 4);
	assertEquals(testCommChestCard.getText(), "Get Out of Jail Free – this card may be kept until needed, or sold");
    }

    @Test
    public void testCommChest5() {
	testCommChestCard = new CommunityChest(5);
	assertEquals(testCommChestCard.getType(), 5);
	assertEquals(testCommChestCard.getText(), "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");

	// Test this on all 3 CommChest cells with all 4 players, and make sure 
	// they land on the correct spot and don't collect $200.
    }

    @Test
    public void testCommChest6() {
	testCommChestCard = new CommunityChest(6);
	assertEquals(testCommChestCard.getType(), 6);
	assertEquals(testCommChestCard.getText(), "It is your birthday - Collect $10 from each player");
    }

    @Test
    public void testCommChest7() {
	testCommChestCard = new CommunityChest(7);
	assertEquals(testCommChestCard.getType(), 7);
	assertEquals(testCommChestCard.getText(), "Grand Opera Night – collect $50 from every player for opening night seats");
    }

    @Test
    public void testCommChest8() {
	testCommChestCard = new CommunityChest(8);
	assertEquals(testCommChestCard.getType(), 8);
	assertEquals(testCommChestCard.getText(), "Income Tax refund – collect $20");

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1520", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1540", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1560", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);
	// TODO: Then is it possible to test any out of whack cases?
    }

    @Test
    public void testCommChest9() {
	testCommChestCard = new CommunityChest(9);
	assertEquals(testCommChestCard.getType(), 9);
	assertEquals(testCommChestCard.getText(), "Life Insurance Matures – collect $100");

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1600", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1700", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1800", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);

	// TODO: And then any screwed cases here.
    }

    @Test
    public void testCommChest10() {
	testCommChestCard = new CommunityChest(10);
	assertEquals(testCommChestCard.getType(), 10);
	assertEquals(testCommChestCard.getText(), "Pay Hospital Fees of $100");
    }

    @Test
    public void testCommChest11() {
	testCommChestCard = new CommunityChest(11);
	assertEquals(testCommChestCard.getType(), 11);
	assertEquals(testCommChestCard.getText(), "Pay School Fees of $50");
    }

    @Test
    public void testCommChest12() {
	testCommChestCard = new CommunityChest(12);
	assertEquals(testCommChestCard.getType(), 12);
	assertEquals(testCommChestCard.getText(), "Receive $25 Consultancy Fee");

	// TODO: Make a helper method for this type of card? IE: all players
	//       move to a spot, doactions, gain money, and land on a final spot.

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1525", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1550", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1575", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest13() {
	testCommChestCard = new CommunityChest(13);
	assertEquals(testCommChestCard.getType(), 13);
	assertEquals(testCommChestCard.getText(), "You are assessed for street repairs – $40 per house, $115 per hotel");

    }

    @Test
    public void testCommChest14() {
	testCommChestCard = new CommunityChest(14);
	assertEquals(testCommChestCard.getType(), 14);
	assertEquals(testCommChestCard.getText(), "You have won second prize in a beauty contest– collect $10");

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1510", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1520", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1530", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest15() {
	testCommChestCard = new CommunityChest(15);
	assertEquals(testCommChestCard.getType(), 15);
	assertEquals(testCommChestCard.getText(), "You inherit $100");

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1600", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1700", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1800", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest16() {
	testCommChestCard = new CommunityChest(16);
	assertEquals(testCommChestCard.getType(), 16);
	assertEquals(testCommChestCard.getText(), "From sale of stock you get $50");

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1550", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1600", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1650", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest17() {
	testCommChestCard = new CommunityChest(17);
	assertEquals(testCommChestCard.getType(), 17);
	assertEquals(testCommChestCard.getText(), "Holiday Fund matures - Receive $100");

	TestHelper.changeCellAllPlayers(2, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1600", player1, player2, player3, player4);
	TestHelper.assertSameCell(2, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(17, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1700", player1, player2, player3, player4);
	TestHelper.assertSameCell(17, gui, player1, player2, player3, player4);

	TestHelper.changeCellAllPlayers(33, gui, player1, player2, player3, player4);
	TestHelper.doActionsAllPlayersCommChest(testCommChestCard, gui, player1, player2, player3, player4);
	TestHelper.assertSameCash("1800", player1, player2, player3, player4);
	TestHelper.assertSameCell(33, gui, player1, player2, player3, player4);

	// TODO: And then any odd cases here.
    }

    @Test
    /** Test incorrect constructor values, etc. */
    public void testBrokenCommChest() {
	// Make sure un-allowed card types produce incorrect text, etc.
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
}

// Test coverage status:
// (Note that this means basic tests are in place for all relevant methods/conditions. 
//  It does NOT mean that all tests pass, or that every single possible case is tested.
//  Just testing that the basic, expected cases work correctly.)
// DONE: 1
// DONE: 2
// TODO: 3
// TODO: 4
// TODO: 5
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