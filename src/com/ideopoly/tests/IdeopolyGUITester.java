package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import com.ideopoly.game.*;

/** Class to test all methods inside the IdeopolyGUI class.
 *
 * @author Daniel Neel */
public class IdeopolyGUITester extends TestCase {
    /** A generic GUI we use for the below tests. */
    private IdeopolyGUI testGUI;

    /** Generic Players that we also use for tests. */
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;

    /** Test all methods in the IdeopolyGUI class. */
    @Test
    public void testIdeopolyGUI() {
	testGUI = new IdeopolyGUI("Test GUI");
	// LEFTOFFHERE: To enable these, make a new IdeopolyGUI object.
	// TODO: Test, after creation of the GUI, the isEnabled() statuses for all the buttons.
	// TODO: These Players have to have a gui to pass in. 
	p1 = new Player(1, testGUI);
	p2 = new Player(2, testGUI);
	p3 = new Player(3, testGUI);
	p4 = new Player(4, testGUI);
	assertEquals(p1.getCell(), testGUI.boardProperties[0]);
	assertEquals(p2.getCell(), testGUI.boardProperties[0]);
	assertEquals(p3.getCell(), testGUI.boardProperties[0]);
	assertEquals(p4.getCell(), testGUI.boardProperties[0]);
    }

    // TODO: Javadocs for this and all other methods.
    // "Given an integer i, break it up into the smallest possible amount of bills."
    @Test
    public void testGetCashDistribution() {
	testGUI = new IdeopolyGUI("Test GUI");

	p1  = new Player(1, testGUI);
	p2  = new Player(2, testGUI);
	p3  = new Player(3, testGUI);
	p4  = new Player(4, testGUI);

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
	p1 = new Player(1, testGUI);
	p2 = new Player(2, testGUI);
	p3 = new Player(3, testGUI);
	p4 = new Player(4, testGUI);

	// TODO: Loop relevant stuff in this method.
	testGUI.playerPayPlayer(500, p1, p2); // between all 4 players
	assertEquals(p1.getCash("total"), 1000);
	assertEquals(p2.getCash("total"), 2000);
	testGUI.playerPayPlayer(500, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 2000);
	testGUI.playerPayPlayer(500, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 2000);
	testGUI.playerPayPlayer(500, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	// Started immediately after semicolon on the first playerPayPlayer here--v
	testGUI.playerPayPlayer(100, p1, p2);
	assertEquals(p1.getCash("total"), 1400);
	assertEquals(p2.getCash("total"), 1600);
	testGUI.playerPayPlayer(100, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1600);
	testGUI.playerPayPlayer(100, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1600);
	testGUI.playerPayPlayer(100, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	testGUI.playerPayPlayer(50, p1, p2);
	assertEquals(p1.getCash("total"), 1450);
	assertEquals(p2.getCash("total"), 1550);
	testGUI.playerPayPlayer(50, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1550);
	testGUI.playerPayPlayer(50, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1550);
	testGUI.playerPayPlayer(50, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	testGUI.playerPayPlayer(20, p1, p2);
	assertEquals(p1.getCash("total"), 1480);
	assertEquals(p2.getCash("total"), 1520);
	testGUI.playerPayPlayer(20, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1520);
	testGUI.playerPayPlayer(20, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1520);
	testGUI.playerPayPlayer(20, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	testGUI.playerPayPlayer(10, p1, p2);
	assertEquals(p1.getCash("total"), 1490);
	assertEquals(p2.getCash("total"), 1510);
	testGUI.playerPayPlayer(10, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1510);
	testGUI.playerPayPlayer(10, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1510);
	testGUI.playerPayPlayer(10, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	testGUI.playerPayPlayer(5, p1, p2);
	assertEquals(p1.getCash("total"), 1495);
	assertEquals(p2.getCash("total"), 1505);
	testGUI.playerPayPlayer(5, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1505);
	testGUI.playerPayPlayer(5, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1505);
	testGUI.playerPayPlayer(5, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	testGUI.playerPayPlayer(1, p1, p2);
	assertEquals(p1.getCash("total"), 1499);
	assertEquals(p2.getCash("total"), 1501);
	testGUI.playerPayPlayer(1, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1501);
	testGUI.playerPayPlayer(1, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1501);
	testGUI.playerPayPlayer(1, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);


	// Test when we bring a player to $0.
	testGUI.playerPayPlayer(1500, p1, p2);
	assertEquals(p1.getCash("total"), 0);
	assertEquals(p2.getCash("total"), 3000);
	testGUI.playerPayPlayer(1500, p3, p2);
	assertEquals(p3.getCash("total"), 0);
	assertEquals(p2.getCash("total"), 4500);
	testGUI.playerPayPlayer(1500, p4, p2);
	assertEquals(p4.getCash("total"), 0);
	assertEquals(p2.getCash("total"), 6000);
	testGUI.playerPayPlayer(1500, p2, p1);
	testGUI.playerPayPlayer(1500, p2, p1);
	testGUI.playerPayPlayer(1500, p2, p1);
	testGUI.playerPayPlayer(1500, p2, p1);
	assertEquals(p2.getCash("total"), 0);
	// TODO: Is this correct behavior? Player shouldn't be allowed to give money after
	//       they're bankrupt...
	assertEquals(p1.getCash("total"), 6000);

	// Will bankrupt first player.
	p1 = new Player(1, testGUI);
	p2 = new Player(2, testGUI);
	p3 = new Player(3, testGUI);
	p4 = new Player(4, testGUI);
	testGUI.playerPayBank(1501, p1);
	assertEquals(p1.getCash("total"), 0);
	// TODO: Should have a better method for these cases. IE, isBankrupt()
	testGUI.playerPayBank(1570, p2);
	assertEquals(p1.getCash("total"), 0);
	testGUI.playerPayBank(1600, p3);
	assertEquals(p1.getCash("total"), 0);
	testGUI.playerPayBank(20000, p4);
	assertEquals(p1.getCash("total"), 0);


	// Now test with un-sensible values being transferred.
	p1 = new Player(1, testGUI);
	p2 = new Player(2, testGUI);
	p3 = new Player(3, testGUI);
	p4 = new Player(4, testGUI);

	// Negative values. These aren't allowed, so cash totals 
	// should stay the same.
	testGUI.playerPayPlayer(-1, p1, p2);
	assertEquals(p1.getCash("total"), 1500);
	assertEquals(p2.getCash("total"), 1500);
	testGUI.playerPayPlayer(-1, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1500);
	testGUI.playerPayPlayer(-1, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1500);
	testGUI.playerPayPlayer(-1, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	testGUI.playerPayPlayer(-100, p1, p2);
	assertEquals(p1.getCash("total"), 1500);
	assertEquals(p2.getCash("total"), 1500);
	testGUI.playerPayPlayer(-100, p2, p3);
	assertEquals(p2.getCash("total"), 1500);
	assertEquals(p3.getCash("total"), 1500);
	testGUI.playerPayPlayer(-100, p3, p4);
	assertEquals(p3.getCash("total"), 1500);
	assertEquals(p4.getCash("total"), 1500);
	testGUI.playerPayPlayer(-100, p4, p1);
	assertEquals(p4.getCash("total"), 1500);
	assertEquals(p1.getCash("total"), 1500);

	// Player pays the other player $0 - pointless
	testGUI.playerPayPlayer(0, p1, p2);
	assertEquals(p1.getCash("total"), 1500);
	assertEquals(p2.getCash("total"), 1500);
	testGUI.playerPayPlayer(0, p2, p3);
	assertEquals(p1.getCash("total"), 1500);
	assertEquals(p2.getCash("total"), 1500);
	testGUI.playerPayPlayer(0, p3, p4);
	assertEquals(p1.getCash("total"), 1500);
	assertEquals(p2.getCash("total"), 1500);
	testGUI.playerPayPlayer(0, p4, p1);
	assertEquals(p1.getCash("total"), 1500);
	assertEquals(p2.getCash("total"), 1500);


	// And now test transferring from a player to the bank.
	// For all 4 players
	testGUI.playerPayBank(1500, p1);
	assertEquals(p1.getCash("total"), 0);
	testGUI.playerPayBank(1430, p2);
	assertEquals(p2.getCash("total"), 70);
	testGUI.playerPayBank(10, p3);
	assertEquals(p3.getCash("total"), 1490);
	testGUI.playerPayBank(500, p4);
	assertEquals(p4.getCash("total"), 1000);
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
    // 	//	p1;
    // }
}
