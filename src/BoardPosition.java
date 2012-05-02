import javax.swing.*;
/** Class that represents a position on the game board. 
 *  These are used as indicators of where players are currently
 *  standing on the board. This class mainly exists so I can
 *  group coordinates and an image with a JLabel. So I can make
 *  a collection of these and say collection[x].changeimage(), etc.
 *
 *  @author Daniel Neel */
public class BoardPosition extends JLabel {
    // v--- This gets rid of some compiler errors.
    private static final long serialVersionUID = 42L;
    /** This position's x-coordinate in the GUI. */
    private final int xCoordPos;

    /** This position's y-coordinate in the GUI. */
    private final int yCoordPos;

    private Icon image;

    /** Create this object with the given x and y coordinates. */
    public BoardPosition(int newXPos, int newYPos) {
	xCoordPos = newXPos;
	yCoordPos = newYPos;
	image     = new ImageIcon("images/noPlayerPresent.jpg");
	this.setIcon(image);
    }

    /** Get this position's x coordinate. */
    public int getXCoord() {
	return xCoordPos;
    }

    /** Get this position's y coordinate. */
    public int getYCoord() {
	return yCoordPos;
    }

    /** Get the image associated with this position. */
    public Icon getImage() {
	return image;
    }

    /** Set the image associated with this position to
     *  the indicated image i. */
    public void setImage(Icon i) {
	image = i;
	this.setIcon(image);
    }
}