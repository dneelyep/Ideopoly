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