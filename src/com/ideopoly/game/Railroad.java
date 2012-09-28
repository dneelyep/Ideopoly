package com.ideopoly.game;

// TODO: Is this class even needed?
// TODO: Also, should these be made schools rather than railroads?

/** Represents any of the four railroads. Is a sub-class of BoardCell.
 *
 *  @author Daniel Neel */
public class Railroad extends BoardCell implements Ownable {
    /** The cost to buy this cell, unimproved. */
    private static final int COST = 200;

    /** The rent for this cell, with no other Railroads owned. */
    private static final int INITIALRENT = 25;

    /** How much money a player can mortgage this Railroad for. */
    private static final int MORTGAGEVALUE = 100;

    /** Status of this Railroad's ownership. If true, a Player owns the property. */
    private boolean owned;

    // TODO: A better solution: Make an interface that declares my constants,
    //       and make use of that interface in various files so I can re-use the
    //       constants.
    // interface ConstantStuff
    // {
    //     public static final int MY_BDATE = 10;
    //     public static final boolean SillyPlatform = true;
    // }

    /** Create a new Railroad with a given name, image, and no owner. */
    public Railroad(String newName, String imagePath, int xPos, int yPos, GameBoard board) {
	super(newName, imagePath, xPos, yPos, board); // Use the BoardCell class' constructor.
	owned = false;
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    public int getCost() {
	return COST;
    }

    /** Returns the rent charged to a Player who lands
     *  on this property, depending on how many Railroads are owned. */
    @Override
    public int getRent() {
	// TODO: This is not correct. This should return different values depending
	// on the # of railroads owned.
	return INITIALRENT;
    }

    /** Set Player p as the owner of this Railroad, and charge
     *  them the correct amount of money. */
    @Override
    public void buy(Player p, GameBoard board) {
	// TODO: This (and the other buy methods) will continue even if the Player becomes
	// bankrupt in payBank(). That should not be allowed.
	p.payBank(this.getCost(), board);
	this.setOwner(p);
	p.setNumOwnedProperties(p.getNumOwnedProperties() + 1);
	board.printStatusAndLog(p.getName() + " bought " + this.getName() + " for $" + COST + ".");
	// TODO: And then change the property's image.
    }

    /** Return whether or not this UtilityCell is 
     *  currently owned by a Player. */
    @Override
    public boolean isOwned() {
	return ((owned == true) ? true : false);
    }

    /** Returns the value this Railroad can be
     *  mortgaged for. */
    public int getMortgageValue() {
	return MORTGAGEVALUE;
    }
}
 
