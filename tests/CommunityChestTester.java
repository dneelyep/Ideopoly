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
	changeCellAllPlayers(2);
	doActionsAllPlayers(testCommChestCard, gui);
	// Make sure the players land on the right spot and get the right amount of $.
	assertSameCash("1700");
	assertSameCell(0);

	changeCellAllPlayers(17);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("1900");
	assertSameCell(0);

	changeCellAllPlayers(33);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("2100");
	assertSameCell(0);
    }

    @Test
    public void testCommChest2() {
	testCommChestCard = new CommunityChest(2);
	assertEquals(testCommChestCard.getType(), 2);
	assertEquals(testCommChestCard.getText(), "Bank error in your favor – collect $200");

	// Test this on all three Comm. Chest spots and for all Players.
	// Make sure the players get the right amount of money and that they're 
	// on the correct BoardCells.
	changeCellAllPlayers(2);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("1700");
	assertSameCell(2);

	changeCellAllPlayers(17);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("1900");
	assertSameCell(17);

	changeCellAllPlayers(33);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("2100");
	assertSameCell(33);
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

	// Test this on all three Comm. Chest spots and for all Players.
	// Make sure the players get the right amount of money and that they're 
	// on the correct BoardCells.
	changeCellAllPlayers(2);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("1520");
	assertSameCell(2);

	changeCellAllPlayers(17);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("1540");
	assertSameCell(17);

	changeCellAllPlayers(33);
	doActionsAllPlayers(testCommChestCard, gui);
	assertSameCash("1560");
	assertSameCell(33);
	// TODO: Then is it possible to test any out of whack cases?
    }

    @Test
    public void testCommChest9() {
	testCommChestCard = new CommunityChest(9);
	assertEquals(testCommChestCard.getType(), 9);
	assertEquals(testCommChestCard.getText(), "Life Insurance Matures – collect $100");
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
    }

    @Test
    public void testCommChest15() {
	testCommChestCard = new CommunityChest(15);
	assertEquals(testCommChestCard.getType(), 15);
	assertEquals(testCommChestCard.getText(), "You inherit $100");
    }

    @Test
    public void testCommChest16() {
	testCommChestCard = new CommunityChest(16);
	assertEquals(testCommChestCard.getType(), 16);
	assertEquals(testCommChestCard.getText(), "From sale of stock you get $50");
    }

    @Test
    public void testCommChest17() {
	testCommChestCard = new CommunityChest(17);
	assertEquals(testCommChestCard.getType(), 17);
	assertEquals(testCommChestCard.getText(), "Holiday Fund matures - Receive $100");
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

    // ======================
    // === Helper methods ===
    // ======================
    // TODO: These methods are duplicated in ChanceTester. Find a way to remove duplication.
    //       Consider making a class full of testing helper methods?
    /** Have each Player draw a card. */
    public void doActionsAllPlayers(CommunityChest card, IdeopolyGUI gui) {
	// TODO: Do I need the two arguments? They're the same for each time I call them here.
	card.doActions(player1, gui);
	card.doActions(player2, gui);
	card.doActions(player3, gui);
	card.doActions(player4, gui);
    }

    /** Check that the total cash value v is the same for every player.*/
    public void assertSameCash(String v) {
	assertEquals(player1.getCash("total"), v);
	assertEquals(player2.getCash("total"), v);
	assertEquals(player3.getCash("total"), v);
	assertEquals(player4.getCash("total"), v);
    }

    /** Move all players to the same BoardCell on the gui. */
    public void changeCellAllPlayers(int cell) {
	player1.changeCell(cell, gui);
	player2.changeCell(cell, gui);
	player3.changeCell(cell, gui);
	player4.changeCell(cell, gui);
    }

    /** Ensure that all four players are standing on the same cell. */
    public void assertSameCell(int cell) {
	assertEquals(player1.getCell(), gui.boardProperties[cell]);
	assertEquals(player2.getCell(), gui.boardProperties[cell]);
	assertEquals(player3.getCell(), gui.boardProperties[cell]);
	assertEquals(player4.getCell(), gui.boardProperties[cell]);
    }

    // LEFTOFFHERE: Adding tests/functionality here. A good next step would be adding in 
    // a doActionsAllPlayers() method, copied from ChanceTester, and/or go ahead with a TestHelperMethods
    // class.
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
// TODO: 9
// TODO: 10
// TODO: 11
// TODO: 12
// TODO: 13
// TODO: 14
// TODO: 15
// TODO: 16
// TODO: 17