import javax.swing.*;

/** Class to represent a player's cash in the GUI. */
public class CashCell extends JLabel {
    // v--- This gets rid of some compiler errors.
    private static final long serialVersionUID = 42L;

    /** This position's x-coordinate in the GUI. */
    private final int xCoordPos;

    /** This position's y-coordinate in the GUI. */
    private final int yCoordPos;

    /** The text to be displayed in this JLabel. */
    private String value;

    /** Create this object with the given x and y coordinates. */
    public CashCell(int newXPos, int newYPos, String text) {
	// LEFTOFFHERE: Implementing these checks. Need to find a way to abort creation 
	// of the object when these arguments are present.
	if (newXPos < 0) {
	    System.out.println("Invalid argument! Can't use a negative x-coordinate.");
	}
	else {
	    xCoordPos = newXPos;
	    this.setText(text);
	}

	if (newYPos < 0) {
	    System.out.println("Invalid argument! Can't use a negative y-coordinate.");
	}
	else {
	    yCoordPos = newYPos;
	    this.setText(text);
	}

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