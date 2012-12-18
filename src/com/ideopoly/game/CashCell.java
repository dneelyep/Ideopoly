package com.ideopoly.game;

import javax.swing.*;

/** Class to represent a player's cash in the GUI.
 *
 *  @author Daniel Neel */
public class CashCell extends JLabel {
    // v--- This gets rid of some compiler errors.
    private static final long serialVersionUID = 42L;

    // TODO Store these coordinates as a Point rather than separate values.
    /** This position's x-coordinate in the GUI. */
    private final int xCoordPos;

    /** This position's y-coordinate in the GUI. */
    private final int yCoordPos;
    
    /** Create this object with the given x and y coordinates. */
    public CashCell(int newXPos, int newYPos, String text) {
	    xCoordPos = newXPos;
	    yCoordPos = newYPos;
	    this.setText(text);
    }

    /** Get this position's x coordinate. */
    public int getXCoord() {
	return xCoordPos;
    }

    /** Get this position's y coordinate. */
    public int getYCoord() {
	return yCoordPos;
    }
}
