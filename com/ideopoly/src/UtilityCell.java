package com.ideopoly.src;

/** Represents the common behaviors of the
 *  Water Works and Electric Company cells. */
public class UtilityCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** The cost to buy this cell, unimproved. */
    private static final int cost = 150;

    /** Create a new UtilityCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public UtilityCell(String newName, String imagePath, int xPos, int yPos, IdeopolyGUI gui) {
	super(newName, imagePath, xPos, yPos, gui); // Use the BoardCell class' constructor.
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    public int getCost() {
	return cost;
    }

    // TODO: Handle this better than returning a 0 value. Un-elegant.
    /** Get the amount of rent charged for landing on this cell.
     *  Since the Player is not charged for landing on any SpecialCells,
     *  we return 0 for the amount. */
    @Override
    public int getRent() {
	return 0;
    }
}
