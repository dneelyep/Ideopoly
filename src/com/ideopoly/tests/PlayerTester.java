package com.ideopoly.tests;

import junit.framework.TestCase;
import java.awt.*;
import org.junit.*;
import com.ideopoly.game.*;

/** Class to test all methods inside the Player class.
 *
 *  @author Daniel Neel */
public class PlayerTester extends TestCase {
    /** Test all methods in the Player class. */
    @Test
    public void testPlayer() {
	GameBoard board = new GameBoard("Richard Stallman");

	// TODO: Test with a non-standard player number.
	Player testPlayer = new Player(1, new Color(1, 238, 0) , board);
	Player testPlayer2 = new Player(2, new Color(223, 254, 10), board);
	Player testPlayer3 = new Player(3, new Color(253, 186, 17), board);
	Player testPlayer4 = new Player(4, new Color(19, 214, 242), board);

	assertEquals(testPlayer.getJailStatus(),         0);
	assertEquals(testPlayer.getCash("ones"),         5);
	assertEquals(testPlayer.getCash("fives"),        5);
	assertEquals(testPlayer.getCash("tens"),         5);
	assertEquals(testPlayer.getCash("twenties"),     6);
	assertEquals(testPlayer.getCash("fifties"),      2);
	assertEquals(testPlayer.getCash("hundreds"),     2);
	assertEquals(testPlayer.getCash("fiveHundreds"), 2);
	assertEquals(testPlayer.getCash("total"),        1500);

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
	testPlayer.spendGOOJF(board);
	assertEquals(testPlayer.getNumGOOJFCards(), 0);
	testPlayer.spendGOOJF(board); // Make sure we can't get a negative # of cards.
	// TODO: Test this when the person's not in jail. Ideally the button AND the
	// functionality should be disabled when we're not currently in jail.
	assertEquals(testPlayer.getNumGOOJFCards(), 0);

	testPlayer.setJailStatus(3);
	assertEquals(testPlayer.getJailStatus(), 3);
	testPlayer.setJailStatus(2);
	assertEquals(testPlayer.getJailStatus(), 2);
	testPlayer.setJailStatus(1);	
	assertEquals(testPlayer.getJailStatus(), 1);
	testPlayer.setJailStatus(0);
	assertEquals(testPlayer.getJailStatus(), 0);

	// Test un-allowed cases.
	testPlayer.setJailStatus(4);
	assertEquals(testPlayer.getJailStatus(), 0);
	testPlayer.setJailStatus(-1);
	assertEquals(testPlayer.getJailStatus(), 0);
	testPlayer.setJailStatus('c');
	assertEquals(testPlayer.getJailStatus(), 0);

	// TODO: More tests for this method to test edge cases.
	assertEquals(testPlayer.getNumOwnedProperties(), 0); 

	testPlayer.bankruptPlayer(board);
	assertEquals(testPlayer.getCash("ones"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("total"),        0);
	assertEquals(testPlayer.getImage(),              null);

	testPlayer.addCash("ones", testPlayer.getCash("ones") + 20);
	testPlayer.addCash("fives", 5);
	testPlayer.addCash("tens", 10);
	testPlayer.addCash("twenties", 15);
	testPlayer.addCash("fifties", 80);
	testPlayer.addCash("hundreds", -12);
	testPlayer.addCash("fiveHundreds", 10);

	assertEquals(testPlayer.getCash("ones"),         20);
	assertEquals(testPlayer.getCash("fives"),         5);
	assertEquals(testPlayer.getCash("tens"),         10);
	assertEquals(testPlayer.getCash("twenties"),     15);
	assertEquals(testPlayer.getCash("fifties"),      80);
	assertEquals(testPlayer.getCash("hundreds"),    -12);
	assertEquals(testPlayer.getCash("fiveHundreds"), 10);
	assertEquals(testPlayer.getCash("total"),      8245);

	// TODO: More tests for negative inputs?

	// Test that correct values are set for the spreadCash() method.
	testPlayer.spreadCash(500);
	assertEquals(testPlayer.getCash("fiveHundreds"), 16);
	assertEquals(testPlayer.getCash("hundreds"),     2);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     2);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        1);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(100);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),    82);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     2);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        1);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(50);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),    164);
	assertEquals(testPlayer.getCash("twenties"),     2);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        1);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(20);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),   412);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        1);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(10);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),       824);
	assertEquals(testPlayer.getCash("fives"),        1);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(5);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),     1649);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(1);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),      8245);


	testPlayer.bankruptPlayer(board);
	testPlayer.spreadCash(500);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(100);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(50);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(20);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(10);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(5);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	testPlayer.spreadCash(1);
	assertEquals(testPlayer.getCash("fiveHundreds"), 0);
	assertEquals(testPlayer.getCash("hundreds"),     0);
	assertEquals(testPlayer.getCash("fifties"),      0);
	assertEquals(testPlayer.getCash("twenties"),     0);
	assertEquals(testPlayer.getCash("tens"),         0);
	assertEquals(testPlayer.getCash("fives"),        0);
	assertEquals(testPlayer.getCash("ones"),         0);

	// Try some invalid arguments to getCash().
	assertEquals(testPlayer.getCash("one"),  -15);
	assertEquals(testPlayer.getCash("1"),    -15);
	assertEquals(testPlayer.getCash("500"),  -15);
	assertEquals(testPlayer.getCash("500s"), -15);


	// image = new ImageIcon("images/p1Present.jpg"); // getImage()

	// public Player(int playerNumber)
	// public void addProperty(BoardCell property)
	// public void removeProperty(BoardCell property)
	// public void roll()
	// public Icon getImage()
	// public int  getPosition()
	// public void setPosition(int p)
	// TODO: ^-- test ALL these.

	// === Test changeCell() ===
	// TODO: Make sure to thoroughly test this function.
	// Make sure a few normal cases work fine.
	testPlayer.setCell(0,  board);
	testPlayer2.setCell(0, board);
	testPlayer3.setCell(0, board);
	testPlayer4.setCell(0, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[0]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[0]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[0]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[0]);

	testPlayer.setCell(1,  board);
	testPlayer2.setCell(1, board);
	testPlayer3.setCell(1, board);
	testPlayer4.setCell(1, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[1]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[1]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[1]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[1]);

	testPlayer.setCell(2,  board);
	testPlayer2.setCell(2, board);
	testPlayer3.setCell(2, board);
	testPlayer4.setCell(2, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[2]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[2]);

	// NOTE: I removed previous test cases here. Previously, these tests checked to make 
	//       sure, for example, that players 1 and 2 could not move to the same standing
	//       position. Due to the new data model, that shouldn't be a problem. If I want to
	//       reproduce those tests, just try to move two separate players to standing on the
	//       same standing position.

	// Then try incorrect cases.

	// Don't allow negative BoardCell arguments.
	testPlayer.setCell(-1, board);
	testPlayer.setCell(-1, board);
	testPlayer.setCell(-1, board);
	testPlayer.setCell(-1, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[2]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[2]);

	testPlayer.setCell(-5, board);
	testPlayer.setCell(-5, board);
	testPlayer.setCell(-5, board);
	testPlayer.setCell(-5, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[2]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[2]);

	// And check to make sure we can't move beyond the edge of the board.
	testPlayer.setCell(40, board);
	testPlayer.setCell(40, board);
	testPlayer.setCell(40, board);
	testPlayer.setCell(40, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[2]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[2]);

	testPlayer.setCell(55, board);
	testPlayer.setCell(55, board);
	testPlayer.setCell(55, board);
	testPlayer.setCell(55, board);
	assertEquals(testPlayer.getCell(),  board.boardProperties[2]);
	assertEquals(testPlayer2.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer3.getCell(), board.boardProperties[2]);
	assertEquals(testPlayer4.getCell(), board.boardProperties[2]);


	// === Test putInJail() ===
	testPlayer.putInJail(board);
	// TODO: Need a way to test the below. The problem (I think) is that the code executes and
	// the GUI's destroyed before the statement runs. 
	//	assertEquals(board.useGOOJFCard.isEnabled(), true);

	testPlayer2.putInJail(board);
	testPlayer3.putInJail(board);
	testPlayer4.putInJail(board);

	assertEquals(testPlayer.getCell(),  board.boardProperties[10]);
	assertEquals(testPlayer.getJailStatus(),  3);
	assertEquals(testPlayer2.getCell(), board.boardProperties[10]);
	assertEquals(testPlayer2.getJailStatus(), 3);
	assertEquals(testPlayer3.getCell(), board.boardProperties[10]);
	assertEquals(testPlayer3.getJailStatus(), 3);
	assertEquals(testPlayer4.getCell(), board.boardProperties[10]);
	assertEquals(testPlayer4.getJailStatus(), 3);
    }
}
