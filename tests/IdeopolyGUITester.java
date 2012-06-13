import junit.framework.TestCase;
import org.junit.*;

/** Class to test all methods inside the IdeopolyGUI class.
 *
 *  @author Daniel Neel */
public class IdeopolyGUITester extends TestCase{
    /** A generic GUI we use for the below tests. */
    private IdeopolyGUI testGUI;

    /** Generic Players that we also use for tests. */
    private Player testCCPlayer1;
    private Player testCCPlayer2;
    private Player testCCPlayer3;
    private Player testCCPlayer4;

    /** Test all methods in the IdeopolyGUI class. */
    @Test
    public void testIdeopolyGUI() {
	testGUI = new IdeopolyGUI("Test GUI");
	// LEFTOFFHERE: To enable these, make a new IdeopolyGUI object.
	// TODO: Test, after creation of the GUI, the isEnabled() statuses for all the buttons.
	// TODO: These Players have to have a gui to pass in. 
	testCCPlayer1  = new Player(1, testGUI);
	testCCPlayer2  = new Player(2, testGUI);
	testCCPlayer3  = new Player(3, testGUI);
	testCCPlayer4  = new Player(4, testGUI);
	assertEquals(testCCPlayer1.getCell(), testGUI.boardProperties[0]);
	assertEquals(testCCPlayer2.getCell(), testGUI.boardProperties[0]);
	assertEquals(testCCPlayer3.getCell(), testGUI.boardProperties[0]);
	assertEquals(testCCPlayer4.getCell(), testGUI.boardProperties[0]);
    }

    // TODO: Javadocs for this and all other methods.
    // "Given an integer i, break it up into the smallest possible amount of bills."
    @Test
    public void testGetCashDistribution() {
	testGUI = new IdeopolyGUI("Test GUI");

	testCCPlayer1  = new Player(1, testGUI);
	testCCPlayer2  = new Player(2, testGUI);
	testCCPlayer3  = new Player(3, testGUI);
	testCCPlayer4  = new Player(4, testGUI);

	// TODO: This long assertEquals looks like an opportunity for a helper method.
	int[] cashDistribution = testGUI.getCashDistribution(2000);
	assertEquals(cashDistribution[0], 0); // 1s
	assertEquals(cashDistribution[1], 0); // 5s
	assertEquals(cashDistribution[2], 0); // 10s
	assertEquals(cashDistribution[3], 0); // 20s
	assertEquals(cashDistribution[4], 0); // 50s
	assertEquals(cashDistribution[5], 0); // 100s
	assertEquals(cashDistribution[6], 4); // 500s

	cashDistribution = testGUI.getCashDistribution(1500);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 0);
	assertEquals(cashDistribution[5], 0);
	assertEquals(cashDistribution[6], 3);

	cashDistribution = testGUI.getCashDistribution(1200);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 0);
	assertEquals(cashDistribution[5], 2);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1100);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 0);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1150);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 1);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1170);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 1);
	assertEquals(cashDistribution[4], 1);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1180);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 1);
	assertEquals(cashDistribution[3], 1);
	assertEquals(cashDistribution[4], 1);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1185);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 1);
	assertEquals(cashDistribution[2], 1);
	assertEquals(cashDistribution[3], 1);
	assertEquals(cashDistribution[4], 1);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1186);
	assertEquals(cashDistribution[0], 1);
	assertEquals(cashDistribution[1], 1);
	assertEquals(cashDistribution[2], 1);
	assertEquals(cashDistribution[3], 1);
	assertEquals(cashDistribution[4], 1);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);

	cashDistribution = testGUI.getCashDistribution(1187);
	assertEquals(cashDistribution[0], 2);
	assertEquals(cashDistribution[1], 1);
	assertEquals(cashDistribution[2], 1);
	assertEquals(cashDistribution[3], 1);
	assertEquals(cashDistribution[4], 1);
	assertEquals(cashDistribution[5], 1);
	assertEquals(cashDistribution[6], 2);


	// And then test some invalid values...
	cashDistribution = testGUI.getCashDistribution(0);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 0);
	assertEquals(cashDistribution[5], 0);
	assertEquals(cashDistribution[6], 0);

	cashDistribution = testGUI.getCashDistribution(-1);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 0);
	assertEquals(cashDistribution[5], 0);
	assertEquals(cashDistribution[6], 0);

	cashDistribution = testGUI.getCashDistribution(-37);
	assertEquals(cashDistribution[0], 0);
	assertEquals(cashDistribution[1], 0);
	assertEquals(cashDistribution[2], 0);
	assertEquals(cashDistribution[3], 0);
	assertEquals(cashDistribution[4], 0);
	assertEquals(cashDistribution[5], 0);
	assertEquals(cashDistribution[6], 0);
    }

    @Test
    /** Test the playerPayPlayer method. */
    public void testPlayerPayPlayer() {
	// First test with a variety of sensible values being transferred.
	testGUI = new IdeopolyGUI("Test player");
	testCCPlayer1 = new Player(1, testGUI);
	testCCPlayer2 = new Player(2, testGUI);
	testCCPlayer3 = new Player(3, testGUI);
	testCCPlayer4 = new Player(4, testGUI);


	// LEFTOFFHERE: Wrote these stubs to implement. Now to implement the tests.
	//              And add more as/where needed.
	playerPayPlayer(500);// between all 4 players
	assertEquals(p1cash, x);
	assertEquals(p2cash, y);
	playerPayPlayer(500);
	playerPayPlayer(500);
	playerPayPlayer(500);

	playerPayPlayer(100);
	playerPayPlayer(100);
	playerPayPlayer(100);
	playerPayPlayer(100);

	playerPayPlayer(50);
	playerPayPlayer(50);
	playerPayPlayer(50);
	playerPayPlayer(50);

	playerPayPlayer(20);
	playerPayPlayer(20);
	playerPayPlayer(20);
	playerPayPlayer(20);

	playerPayPlayer(10);
	playerPayPlayer(10);
	playerPayPlayer(10);
	playerPayPlayer(10);

	playerPayPlayer(5);
	playerPayPlayer(5);
	playerPayPlayer(5);
	playerPayPlayer(5);

	playerPayPlayer(1);
	playerPayPlayer(1);
	playerPayPlayer(1);
	playerPayPlayer(1);


	// Will bring first player to $0.
	playerPayPlayer(1500);
	playerPayPlayer(1500);
	playerPayPlayer(1500);
	playerPayPlayer(1500);

	// Will bankrupt first player.
	playerPayPlayer(1501);
	playerPayPlayer(1501);
	playerPayPlayer(1501);
	playerPayPlayer(1501);


	// Now test with un-sensible values being transferred.

	// Negative values
	playerPayPlayer(-1);
	playerPayPlayer(-1);
	playerPayPlayer(-1);
	playerPayPlayer(-1);

	playerPayPlayer(-100);
	playerPayPlayer(-100);
	playerPayPlayer(-100);
	playerPayPlayer(-100);

	// Zero (pointless)
	playerPayPlayer(0);
	playerPayPlayer(0);
	playerPayPlayer(0);
	playerPayPlayer(0);


	// And now test transferring from a player to the bank.
	// For all 4 players
	playerPayPlayer(1500, a, null);
	playerPayPlayer(0);
	playerPayPlayer(0);
	playerPayPlayer(0);

	// Then from the bank to the player[?]
    }


    // TODO: Use standardized test player/gui names across all tests.
    // TODO: Add tests for this. In particular, I switched this:
	    // // First week in jail.
	    // if ( players[currentPlayer].getJailStatus() == 3 ) {
	    // 	players[currentPlayer].setJailStatus(2);
	    // }

	    // // Second week in jail.
	    // else if ( players[currentPlayer].getJailStatus() == 2 ) {
	    // 	players[currentPlayer].setJailStatus(1);
	    // }

    // ...to this:


    // /** Test the doTurn() method. */
    // @Test
    // public void testDoTurn() {
    // 	testGUI.doTurn(frame);
    // 	//	testCCPlayer1;
    // }
}
