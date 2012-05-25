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
}