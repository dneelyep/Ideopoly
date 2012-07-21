package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import com.ideopoly.game.*;

/** Class to test all methods inside the CashCell class.
 *
 *  @author Daniel Neel */
public class CashCellTester extends TestCase {
    /** Test all methods in the CashCell class. */
    @Test
    public void testCashCell() {
	CashCell testCashCell = new CashCell(5, 10, "testingtext");
	assertEquals(testCashCell.getXCoord(), 5);
	assertEquals(testCashCell.getYCoord(), 10);

	testCashCell = new CashCell(-1, -2, "testingtext");
	assertEquals(testCashCell.getXCoord(), -1);
	assertEquals(testCashCell.getYCoord(), -2);
	assertEquals(testCashCell.getText(), "testingtext");

	testCashCell = new CashCell(0, 0, "testingtext");
	assertEquals(testCashCell.getXCoord(), 0);
	assertEquals(testCashCell.getYCoord(), 0);
	assertEquals(testCashCell.getText(), "testingtext");
    }
}
