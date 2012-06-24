// TODO: Is this class even needed?
// TODO: Also, should these be made schools rather than railroads?

/** Represents any of the four railroads. Is a sub-class of BoardCell.
 *
 *  @author Daniel Neel */
public class Railroad extends BoardCell {
    /** The cost to buy this cell, unimproved. */
    private static final int COST = 200;

    /** The rent for this cell, with no other Railroads owned. */
    private static final int INITIALRENT = 25;

    /** How much money a player can mortgage this Railroad for. */
    private static final int MORTGAGEVALUE = 100;

    // TODO: A better solution: Make an interface that declares my constants,
    //       and make use of that interface in various files so I can re-use the
    //       constants.
    // interface ConstantStuff
    // {
    //     public static final int MY_BDATE = 10;
    //     public static final boolean SillyPlatform = true;
    // }

    /** Create a new Railroad with a given name, image, and no owner. */
    public Railroad(String newName, String imagePath, int xPos, int yPos, IdeopolyGUI gui) {
	super(newName, imagePath, xPos, yPos, gui); // Use the BoardCell class' constructor.
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    public int getCost() {
	return COST;
    }

    /** Returns the rent charged to a Player who
     *  lands on this property, when it's unimproved. */
    public int getInitialRent() {
	return INITIALRENT;
    }

    /** Returns the value this Railroad can be
     *  mortgaged for. */
    public int getMortgageValue() {
	return MORTGAGEVALUE;
    }
}
