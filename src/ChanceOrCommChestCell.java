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
}
