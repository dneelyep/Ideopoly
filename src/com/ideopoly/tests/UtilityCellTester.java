package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import com.ideopoly.game.*;

import java.awt.*;

/** UtilityCellTester.java - Tests for the UtilityCell class.
 *
 * @author Daniel Neel */
public class UtilityCellTester extends TestCase { 
   
   private UtilityCell testWaterWorks = new UtilityCell("Water Works", "waterWorks.jpg", new Point(33, 1), new GameBoard("Test"));
    //    UtilityCell testElectricCo = new UtilityCell();

    @Test
    /** Make sure the UtilityCell is initialized correctly, with a cost
     *  of 150 and the correct rent value. Also ensure correct X/Y coordinates
     *  and other properties for various components of the UtilityCell. */
    public void testConstructor() {
	assertEquals(testWaterWorks.getName(), "Water Works");
	assertEquals(testWaterWorks.getOwner(), null);
	assertEquals(testWaterWorks.getCost(), 150);
	//	assertEquals(testWaterWorks.getRent(), 0);
	assertEquals(testWaterWorks.getCoordinates().x, 33);
	assertEquals(testWaterWorks.getCoordinates().y, 1);

	assertEquals(testWaterWorks.getPosition(1).getCoordinates().x, 36);
	assertEquals(testWaterWorks.getPosition(2).getCoordinates().x, 35);
	assertEquals(testWaterWorks.getPosition(3).getCoordinates().x, 34);
	assertEquals(testWaterWorks.getPosition(4).getCoordinates().x, 33);

	assertEquals(testWaterWorks.getPosition(1).getCoordinates().y, 0);
	assertEquals(testWaterWorks.getPosition(2).getCoordinates().y, 0);
	assertEquals(testWaterWorks.getPosition(3).getCoordinates().y, 0);
	assertEquals(testWaterWorks.getPosition(4).getCoordinates().y, 0);

	// private Icon image;
	// private BoardCellGUI graphicalRepresentation;
	// public GameBoard board;
    }

    @Test
    /** Ensure the cost of the UtilityCell is correct. */
    public void testGetRent() {
    }
}
