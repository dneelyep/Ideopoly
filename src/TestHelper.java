import junit.framework.TestCase;
import org.junit.*;


/** Class to provide helper methods for use in my testing classes. */
class TestHelper extends TestCase {
    // TODO: Make a TestHelperTester class?
    /** Have each Player draw a card. */
    // LEFTOFFHERE: Adding tests. Now I need to have doActionsAllPlayers accept parametrized
    // types (think that's the right term). Basically I need to allow the first type to be either
    // a Chance card or CommunityChest card, and do the same thing to them in either case.
    public static void doActionsAllPlayers(CommunityChest card, IdeopolyGUI gui, Player p1, Player p2, Player p3, Player p4) {
	// TODO: Do I need the two arguments? They're the same for each time I call them here.
	card.doActions(p1, gui);
	card.doActions(p2, gui);
	card.doActions(p3, gui);
	card.doActions(p4, gui);
    }

    /** Check that the total cash value v is the same for every player.*/
    public static void assertSameCash(String v, Player p1, Player p2, Player p3, Player p4) {
	assertEquals(p1.getCash("total"), v);
	assertEquals(p2.getCash("total"), v);
	assertEquals(p3.getCash("total"), v);
	assertEquals(p4.getCash("total"), v);
    }

    /** Move all players to the same BoardCell on the gui. */
    public static void changeCellAllPlayers(int cell, IdeopolyGUI gui, Player p1, Player p2, Player p3, Player p4) {
	p1.changeCell(cell, gui);
	p2.changeCell(cell, gui);
	p3.changeCell(cell, gui);
	p4.changeCell(cell, gui);
    }

    /** Ensure that all four players are standing on the same cell. */
    public static void assertSameCell(int cell, IdeopolyGUI gui, Player p1, Player p2, Player p3, Player p4) {
	assertEquals(p1.getCell(), gui.boardProperties[cell]);
	assertEquals(p2.getCell(), gui.boardProperties[cell]);
	assertEquals(p3.getCell(), gui.boardProperties[cell]);
	assertEquals(p4.getCell(), gui.boardProperties[cell]);
    }

}