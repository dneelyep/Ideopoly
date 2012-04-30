import javax.swing.*;

// TODO: Is this class even needed?
// TODO: Also, should these be made schools rather than railroads?

/** Represents any of the four railroads. Is a sub-class of BoardCell.*/
public class Railroad extends BoardCell {

    /** Create a new Railroad with a given name, image, and no owner. */
    public Railroad(String new_name, Icon new_image, int pos_x, int pos_y) {
	super(new_name, new_image, pos_x, pos_y); // Use the BoardCell class' constructor.
    }
}