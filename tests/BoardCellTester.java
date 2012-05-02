import junit.framework.TestCase;
import org.junit.*;
import javax.swing.*;

/** Class to test all methods inside the BoardCell class.
 *
 *  @author Daniel Neel */
public class BoardCellTester extends TestCase {

    /** Test all methods in the BoardCell class. */ 
    @Test
    public void testBoardCell() {
	// TODO: Should have deleted this file.
	BoardCell testCell = new BoardCell("name", new ImageIcon("images/red_template.jpg"), 4, 10);
	Player    aPlayer  = new Player(1);

	// Test basic, not out-of-the-ordinary cases.
	assertEquals(testCell.getName(), "name");
	assertEquals(testCell.getOwner(), null);

	testCell.setOwner(aPlayer);
	assertEquals(testCell.getOwner(), aPlayer);

	assertEquals(testCell.getX(), 4);
	assertEquals(testCell.getY(), 10);

	// TODO: What should these return? This screams for a better design, since I think they're just dummy methods.
	//	assertEquals(testCell.getRent(), );
	//	assertEquals(testCell.getCost(), )


	// Icon getImage()
	// void setImage(Icon newImage)

	// TODO: Then more complicated, out of the ordinary situations. For 
	// example, test for odd values to the constructor.
    }
}