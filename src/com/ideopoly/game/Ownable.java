package com.ideopoly.game;

/** Interface for BoardCells that a Player can own.
 *  For every ownable BoardCell, we want methods to buy the cell and
 *  return the amount of rent charged for landing on it.
 *
 * @author Daniel Neel */
public interface Ownable {
    // TODO: Tests? Possible for an interface?
    /** Set Player p as this property's owner, and
     *  charge them the relevant amount of money. */
    void buy(Player p, GameBoard board);

    /** Get the amount of rent charged to a Player
     *  landing on this property. */
    int getRent();

    /** Return true if this property is owned, false otherwise. */
    boolean isOwned();
}
