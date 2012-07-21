package com.ideopoly.game;

/** Represents a Chance or Community Chest
 *  cell on the game board. */
// TODO: More concise name for this class.
public class ChanceOrCommChestCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** Create a new ChanceOrCommChestCell with a given
     *  name, image, and x/y coordinates on the board.*/
    public ChanceOrCommChestCell(String newName, String imagePath, int xPos, int yPos, IdeopolyGUI gui) {
	super(newName, imagePath, xPos, yPos, gui); // Use the BoardCell class' constructor.
    }

    // TODO: Handle this better than returning a 0 value. Un-elegant.
    /** Get the amount of rent charged for landing on this cell.
     *  Since the Player is not charged for landing on Chance/CommChest
     *  cells, we return 0 for the amount. */
    @Override
    public int getRent() {
	return 0;
    }
}
