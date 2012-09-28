package com.ideopoly.game;

/** Represents any of the BoardCells that do not
 *  easily fall into a specific category. Go, Income
 *  Tax, In Jail, Free Parking, Go to Jail, Luxury
 *  Tax. */
public class SpecialCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** Create a new SpecialCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public SpecialCell(String newName, String imagePath, int xPos, int yPos, GameBoard board) {
	super(newName, imagePath, xPos, yPos, board); // Use the BoardCell class' constructor.
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
