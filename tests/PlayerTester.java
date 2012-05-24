import junit.framework.TestCase;
import org.junit.*;

/** Class to test all methods inside the Player class.
 *
 *  @author Daniel Neel */
public class PlayerTester extends TestCase {
    /** Test all methods in the Player class. */
    @Test
    public void testPlayer() {
	IdeopolyGUI gui = new IdeopolyGUI("Richard Stallman");

	// TODO: Test with a non-standard player number.
	Player testPlayer  = new Player(1, gui);
	Player testPlayer2 = new Player(2, gui);
	Player testPlayer3 = new Player(3, gui);
	Player testPlayer4 = new Player(4, gui);
	assertEquals(testPlayer.getJailStatus(),            0);
	assertEquals(testPlayer.getCash("ones"),          "5");
	assertEquals(testPlayer.getCash("fives"),         "5");
	assertEquals(testPlayer.getCash("tens"),          "5");
	assertEquals(testPlayer.getCash("twenties"),      "6");
	assertEquals(testPlayer.getCash("fifties"),       "2");
	assertEquals(testPlayer.getCash("hundreds"),      "2");
	assertEquals(testPlayer.getCash("fiveHundreds"), "2");
	assertEquals(testPlayer.getCash("total"),      "1500");

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
	testPlayer.spendGOOJF();
	assertEquals(testPlayer.getNumGOOJFCards(), 0);
	testPlayer.spendGOOJF(); // Make sure we can't get a negative # of cards.
	// TODO: Test this when the person's not in jail. Ideally the button AND the
	// functionality should be disabled when we're not currently in jail.
	assertEquals(testPlayer.getNumGOOJFCards(), 0);

	testPlayer.setInJail(3);
	assertEquals(testPlayer.getJailStatus(), 3);
	testPlayer.setInJail(2);
	assertEquals(testPlayer.getJailStatus(), 2);
	testPlayer.setInJail(1);	
	assertEquals(testPlayer.getJailStatus(), 1);
	testPlayer.setInJail(0);
	assertEquals(testPlayer.getJailStatus(), 0);

	// Test un-allowed cases.
	testPlayer.setInJail(4);
	assertEquals(testPlayer.getJailStatus(), 0);
	testPlayer.setInJail(-1);
	assertEquals(testPlayer.getJailStatus(), 0);
	testPlayer.setInJail('c');
	assertEquals(testPlayer.getJailStatus(), 0);

	// TODO: More tests for this method to test edge cases.
	assertEquals(testPlayer.getNumOwnedProperties(), 0); 

	testPlayer.bankruptPlayer();
	assertEquals(testPlayer.getCash("ones"),          "0");
	assertEquals(testPlayer.getCash("fives"),         "0");
	assertEquals(testPlayer.getCash("tens"),          "0");
	assertEquals(testPlayer.getCash("twenties"),      "0");
	assertEquals(testPlayer.getCash("fifties"),       "0");
	assertEquals(testPlayer.getCash("hundreds"),      "0");
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("total"),         "0");
	assertEquals(testPlayer.getImage(),              null);

	testPlayer.addCash("ones", Integer.parseInt(testPlayer.getCash("ones")) + 20);
	testPlayer.addCash("fives", 5);
	testPlayer.addCash("tens", 10);
	testPlayer.addCash("twenties", 15);
	testPlayer.addCash("fifties", 80);
	testPlayer.addCash("hundreds", -12);
	testPlayer.addCash("fiveHundreds", 10);

	assertEquals(testPlayer.getCash("ones"),          "20");
	assertEquals(testPlayer.getCash("fives"),          "5");
	assertEquals(testPlayer.getCash("tens"),          "10");
	assertEquals(testPlayer.getCash("twenties"),      "15");
	assertEquals(testPlayer.getCash("fifties"),       "80");
	assertEquals(testPlayer.getCash("hundreds"),     "-12");
	assertEquals(testPlayer.getCash("fiveHundreds"), "10");
	assertEquals(testPlayer.getCash("total"),         "8245");

	// TODO: More tests for negative inputs?

	// Test that correct values are set for the spreadMoney() method.
	testPlayer.spreadCash(500);
	assertEquals(testPlayer.getCash("fiveHundreds"), "16");
	assertEquals(testPlayer.getCash("hundreds"),     "2");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "2");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(100);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),    "82");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "2");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(50);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),    "164");
	assertEquals(testPlayer.getCash("twenties"),     "2");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(20);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),   "412");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(10);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),       "824");
	assertEquals(testPlayer.getCash("fives"),        "1");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(5);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),     "1649");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(1);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),      "8245");


	testPlayer.bankruptPlayer();
	testPlayer.spreadCash(500);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(100);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(50);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(20);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(10);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(5);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	testPlayer.spreadCash(1);
	assertEquals(testPlayer.getCash("fiveHundreds"), "0");
	assertEquals(testPlayer.getCash("hundreds"),     "0");
	assertEquals(testPlayer.getCash("fifties"),      "0");
	assertEquals(testPlayer.getCash("twenties"),     "0");
	assertEquals(testPlayer.getCash("tens"),         "0");
	assertEquals(testPlayer.getCash("fives"),        "0");
	assertEquals(testPlayer.getCash("ones"),         "0");

	// Try some invalid arguments to getCash().
	assertEquals(testPlayer.getCash("one"),  "Error! Incorrect argument.");
	assertEquals(testPlayer.getCash("1"),    "Error! Incorrect argument.");
	assertEquals(testPlayer.getCash("500"),  "Error! Incorrect argument.");
	assertEquals(testPlayer.getCash("500s"), "Error! Incorrect argument.");


	// image = new ImageIcon("images/p1Present.jpg"); // getImage()

	// public Player(int playerNumber)
	// public void addProperty(BoardCell property)
	// public void removeProperty(BoardCell property)
	// public void roll()
	// public Icon getImage()
	// public int  getPosition()
	// public void setPosition(int p)
	// TODO: ^-- test ALL these.

	// === Test changePosition() ===
	// TODO: This was affected by BoardPosition switchover. Re-implement tests.
	// TODO: Make sure to thoroughly test this function.
	// Make sure a few normal cases work fine.
	// testPlayer.changePosition(3);
	// testPlayer2.changePosition(3);
	// testPlayer3.changePosition(3);
	// testPlayer4.changePosition(3);
	// assertEquals(testPlayer.getPosition(),  3);
	// assertEquals(testPlayer2.getPosition(), 2);
	// assertEquals(testPlayer3.getPosition(), 1);
	// assertEquals(testPlayer4.getPosition(), 0);

	// testPlayer.changePosition(7);
	// testPlayer2.changePosition(7);
	// testPlayer3.changePosition(7);
	// testPlayer4.changePosition(7);
	// assertEquals(testPlayer.getPosition(),  7);
	// assertEquals(testPlayer2.getPosition(), 6);
	// assertEquals(testPlayer3.getPosition(), 5);
	// assertEquals(testPlayer4.getPosition(), 4);

	// testPlayer.changePosition(11);
	// testPlayer2.changePosition(11);
	// testPlayer3.changePosition(11);
	// testPlayer4.changePosition(11);
	// assertEquals(testPlayer.getPosition(),  11);
	// assertEquals(testPlayer2.getPosition(), 10);
	// assertEquals(testPlayer3.getPosition(),  9);
	// assertEquals(testPlayer4.getPosition(),  8);

	// Then try incorrect cases.
	// Players should not be allowed to changePosition() to an incorrect cell. This would
	// lead to more than one Player standing on the same BoardPosition. Bad.
	// testPlayer.changePosition(0);
	// testPlayer2.changePosition(0);
	// testPlayer3.changePosition(0);
	// testPlayer4.changePosition(0);
	// assertEquals(testPlayer.getPosition(),  11);
	// assertEquals(testPlayer2.getPosition(), 10);
	// assertEquals(testPlayer3.getPosition(),  9);
	// assertEquals(testPlayer4.getPosition(),  8);

	// testPlayer.changePosition(1);
	// testPlayer2.changePosition(1);
	// testPlayer3.changePosition(1);
	// testPlayer4.changePosition(1);
	// assertEquals(testPlayer.getPosition(),  11);
	// assertEquals(testPlayer2.getPosition(), 10);
	// assertEquals(testPlayer3.getPosition(),  9);
	// assertEquals(testPlayer4.getPosition(),  8);

	// testPlayer.changePosition(2);
	// testPlayer2.changePosition(2);
	// testPlayer3.changePosition(2);
	// testPlayer4.changePosition(2);
	// assertEquals(testPlayer.getPosition(),  11);
	// assertEquals(testPlayer2.getPosition(), 10);
	// assertEquals(testPlayer3.getPosition(),  9);
	// assertEquals(testPlayer4.getPosition(),  8);

	// testPlayer.changePosition(20);
	// testPlayer2.changePosition(20);
	// testPlayer3.changePosition(20);
	// testPlayer4.changePosition(20);
	// assertEquals(testPlayer.getPosition(),  11);
	// assertEquals(testPlayer2.getPosition(), 10);
	// assertEquals(testPlayer3.getPosition(),  9);
	// assertEquals(testPlayer4.getPosition(),  8);

	// testPlayer.changePosition(19);
	// testPlayer2.changePosition(19);
	// testPlayer3.changePosition(19);
	// testPlayer4.changePosition(19);
	// assertEquals(testPlayer.getPosition(),  19);
	// assertEquals(testPlayer2.getPosition(), 18);
	// assertEquals(testPlayer3.getPosition(), 17);
	// assertEquals(testPlayer4.getPosition(), 16);

	// === Test putInJail() ===
	testPlayer.putInJail(gui);
	// TODO: Need a way to test the below. The problem (I think) is that the code executes and
	// the GUI's destroyed before the statement runs. 
	//	assertEquals(gui.useGOOJFCard.isEnabled(), true);

	testPlayer2.putInJail(gui);
	testPlayer3.putInJail(gui);
	testPlayer4.putInJail(gui);

	// TODO: Fix these tests that previously relied on getPosition()
	//	assertEquals(testPlayer.getPosition(),    43);
	assertEquals(testPlayer.getJailStatus(),  3);
	//	assertEquals(testPlayer2.getPosition(),   42);
	assertEquals(testPlayer2.getJailStatus(), 3);
	//	assertEquals(testPlayer3.getPosition(),   41);
	assertEquals(testPlayer3.getJailStatus(), 3);
	//	assertEquals(testPlayer4.getPosition(),   40);
	assertEquals(testPlayer4.getJailStatus(), 3);
    }
}