import junit.framework.TestCase;
import org.junit.*;

/** Class to test all methods inside the IdeopolyGUI class.
 *
 *  @author Daniel Neel */
public class IdeopolyGUITester extends TestCase{
    /** Test all methods in the IdeopolyGUI class. */
    public void testIdeopolyGUI() {
	IdeopolyGUI testGUI = new IdeopolyGUI("Test GUI");
	// LEFTOFFHERE: To enable these, make a new IdeopolyGUI object.
	// TODO: Test, after creation of the GUI, the isEnabled() statuses for all the buttons.
	// TODO: These Players have to have a gui to pass in. 
	Player testCCPlayer1  = new Player(1, testGUI);
	Player testCCPlayer2  = new Player(2, testGUI);
	Player testCCPlayer3  = new Player(3, testGUI);
	Player testCCPlayer4  = new Player(4, testGUI);
	assertEquals(testCCPlayer1.getCell(), testGUI.boardProperties[0]);
	assertEquals(testCCPlayer2.getCell(), testGUI.boardProperties[0]);
	assertEquals(testCCPlayer3.getCell(), testGUI.boardProperties[0]);
	assertEquals(testCCPlayer4.getCell(), testGUI.boardProperties[0]);
    }

    // TODO: Javadocs for this and all other methods.
    public void testGetCashDistribution() {
	// /** Given an integer i, break it up into the smallest possible amount of bills. */
	// private void getCashDistribution(int total) {
	//     // LEFTOFFHERE: TODO: This is almost working, but I think BoardCell's getCost() function
	//     // is not being overwritten by PropagandaOutlet's => since BoardCell always returns 
	//     // 0, I get this case where total = 0, which screws up gameplay.
	//     if (total <= 0) {
	// 	System.out.println("Can't break up 0 or negative dollars!");
	//     }

	//     else {
	// 	// TODO: Ambiguous, confusing names here.
	// 	int[] billValues  = {500, 100, 50, 20, 10, 5, 1};
	// 	int[] billTotals  = {0, 0, 0, 0, 0, 0, 0};
	// 	int i = 0;

	// 	for (int bill : billValues) {
	// 	    if (total / bill != 0) {
	// 		billTotals[i] = total / bill;
	// 		total -= (bill * billTotals[i]);
	// 	    }

	// 	    i++;
	// 	}

	// 	//Then have the player pay each of the amounts
	// 	paymentAmounts[0] = billTotals[6];
	// 	paymentAmounts[1] = billTotals[5];
	// 	paymentAmounts[2] = billTotals[4];
	// 	paymentAmounts[3] = billTotals[3];
	// 	paymentAmounts[4] = billTotals[2];
	// 	paymentAmounts[5] = billTotals[1];
	// 	paymentAmounts[6] = billTotals[0];
	//     }  
    }
}