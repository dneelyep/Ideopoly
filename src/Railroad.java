// TODO: Is this class even needed?
// TODO: Also, should these be made schools rather than railroads?

/** Represents any of the four railroads. Is a sub-class of BoardCell.
 *
 *  @author Daniel Neel */
public class Railroad extends BoardCell {

    /** Create a new Railroad with a given name, image, and no owner. */
    public Railroad(String newName, String imagePath, int xPos, int yPos) {
	super(newName, imagePath, xPos, yPos); // Use the BoardCell class' constructor.
    }
}
