/** Represents any of the BoardCells that do not
 *  easily fall into a specific category. Go, Income
 *  Tax, In Jail, Free Parking, Go to Jail, Luxury
 *  Tax. */
public class SpecialCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** Create a new SpecialCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public SpecialCell(String newName, String imagePath, int xPos, int yPos) {
	super(newName, imagePath, xPos, yPos); // Use the BoardCell class' constructor.
    }
}
