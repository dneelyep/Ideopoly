package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import javax.swing.*;
import com.ideopoly.src.BoardPosition;

/** Class to test all methods inside the BoardPosition class.
 *
 *  @author Daniel Neel */
public class BoardPositionTester extends TestCase {
    /** Test all methods in the BoardPosition class. */
    // TODO: More tests needed? Looks skimpy.
    @Test
    public void testBoardPosition() {
	BoardPosition testBoardPosition = new BoardPosition(0, 0);
	assertEquals(testBoardPosition.getXCoord(), 0);
	assertEquals(testBoardPosition.getYCoord(), 0);
	//	assertEquals(testBoardPosition.getImage(), ); <-- TODO

	Icon testImage = new ImageIcon("images/p1Present.jpg");
	testBoardPosition.setImage(testImage);
	assertEquals(testBoardPosition.getImage(), testImage);
    }
}
