package com.ideopoly.game;

/** Interface for BoardCells that a Player can own.
 *  For every ownable BoardCell, we want methods to buy the cell and
 *  return the amount of rent charged for landing on it.
 *
 * @author Daniel Neel */
public interface Ownable {
    // TODO Don't Ownables also have mortgage values?
    // TODO: Tests? Possible for an interface?
    /** Get the amount of rent charged to a Player
     *  landing on this property. */
    public int getRent();

    /** Get the amount of money required to buy this Ownable property. */
    public int getCost();

    /** Get the amount of money the Player can sell this Ownable for. */
    public int getMortgage();

    /** Return true if this property is owned, false otherwise. */
    public boolean isOwned();

    /** Change the owner of this Ownable to a given Player player. */
    public void setOwner(Player player);

    /** Get a string representation of the name of this Ownable. */
    public String getName();
}
