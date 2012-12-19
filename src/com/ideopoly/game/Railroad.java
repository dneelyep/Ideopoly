package com.ideopoly.game;

// TODO: Is this class even needed?
// TODO: Also, should these be made schools rather than railroads?

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    /** Create a new Railroad with a given name, image, and no owner. */
    public Railroad(String newName, String imagePath, Point coordinates, final GameBoard board) {
        super(newName, imagePath, coordinates); // Use the BoardCell class' constructor.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                board.setGUIColor(Color.BLACK);
                board.setGUICost("$" + getCost());
                // TODO: Change the gui text to 1RR/2RRs/etc. maybe
                //       where houses would be displayed.
                board.setGUIRent("$" + getRent());
                // TODO: Combine the unownable/can't buy parts? Reduce a bit of duplication.
                board.setGUI1HouseLabel("-");
                board.setGUI2HouseLabel("-");
                board.setGUI3HouseLabel("-");
                board.setGUI4HouseLabel("-");
                board.setGUIHotel("-");
                board.setGUIMortgage("$" + getMortgageValue());
                board.setGUIName(getName());
            }
        });
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
        // TODO buy should belong to the Player class, and be named buyProperty or similar. Better to maintain a single
        //      implementation in one class than an implementation for every type of property.
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