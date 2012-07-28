package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import java.awt.*;
import com.ideopoly.game.*;

/** Class to test all methods inside the PropagandaOutlet class.
 *
 *  @author Daniel Neel */
public class PropagandaOutletTester extends TestCase {
    /** Test all methods in the PropagandaOutlet class. */
    @Test
    public void testPropagandaOutlet() {
	IdeopolyGUI gui = new IdeopolyGUI("TestGUI");

	// TODO: Removed this image
	PropagandaOutlet testPropOutlet = new PropagandaOutlet("testPropOutlet", "images/yellow_template.jpg", 40, 10, 60, 180, 350, 630, 800, 100, 18, 18, gui, 252, 255, 0);

	assertEquals(testPropOutlet.getName(), "testPropOutlet");
	//	    TODO: Test the image assigned during creation.
	// public Icon getImage() {
	// public void setImage(Icon newImage) {
	assertEquals(testPropOutlet.getX(), 18);
	assertEquals(testPropOutlet.getY(), 18);

	//assertEquals(testPropOutlet.getNumHouses(), 0); TODO: Tests/methods for these?
	// assertEquals(testPropOutlet.getNumHotels(), 0);
	assertEquals(testPropOutlet.getCost(), 40);
	// TODO: Test for mortgage value?
	assertEquals(testPropOutlet.getRent(), 10);
	// TODO: Add tests here for when I have 1/2/3/4 houses, and 1 hotel.
	// TODO: All derived methods are tested here in normal cases, except the commented out ones (image, etc.)
	//       Need out of the ordinary cases, though.
	assertEquals(testPropOutlet.getOwner(), null);

	Player testPropOutletPlayer = new Player(2, new Color(223, 254, 10), gui);
	testPropOutlet.setOwner(testPropOutletPlayer);
	assertEquals(testPropOutlet.getOwner(), testPropOutletPlayer);
    }
}
