package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import java.io.*;
import com.ideopoly.src.IdeopolyGUI;
import com.ideopoly.src.Player;

/** Class to test all methods inside the BoardCell class.
 *
 *  @author Daniel Neel */
public class BoardCellTester extends TestCase {

    /** Test all methods in the BoardCell class. */ 
    @Test
    public void testBoardCell() {
	IdeopolyGUI gui = new IdeopolyGUI("TestGUI");
	Player  aPlayer = new Player(1, gui);

	// TODO: Should have deleted this file.
	//       I think I want to do this because BoardCells are abstract, so I can't 
	//       instantiate them. Makes 'em harder to test.
	//	BoardCell testCell = new BoardCell("name", "images/orangeTemplate.jpg", 4, 10, gui);

	// Test basic, not out-of-the-ordinary cases.
	// assertEquals(testCell.getName(), "name");
	// assertEquals(testCell.getOwner(), null);

	//	testCell.setOwner(aPlayer);
	//	assertEquals(testCell.getOwner(), aPlayer);

	//	assertEquals(testCell.getImage(), new ImageIcon("images/orangeTemplate.jpg"));
	//	testCell.setImage(new ImageIcon("images/greenTemplate.jpg"));
	//	assertEquals(testCell.getImage(), new ImageIcon("images/greenTemplate.jpg"));

	//	assertEquals(testCell.getX(), 4);
	//	assertEquals(testCell.getY(), 10);

	// TODO: What should these return? This screams for a better design, since I think they're just dummy methods.
	//	assertEquals(testCell.getRent(), );
	//	assertEquals(testCell.getCost(), )

	// TODO: Then more complicated, out of the ordinary situations. For 
	// example, test for odd values to the constructor.
    }
}
