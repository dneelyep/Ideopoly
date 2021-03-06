package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Represents the common behaviors of the
 *  Water Works and Electric Company cells.
 *
 *  @author Daniel Neel */
public class UtilityCell extends BoardCell implements Ownable {
    // TODO: Add a test class and tests.
    /** The cost to buy this cell, unimproved. */
    private final int COST = 150;

    /** The mortgage value of this UtilityCell. */
    private final int MORTGAGE = 75;

    /** Status of this UtilityCell's ownership. If true, a Player owns the property. */
    private boolean owned = false;

    /** Create a new UtilityCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public UtilityCell(String newName, String imagePath, Point coordinates, final GameBoard board) {
        super(newName, new ImageIcon("res/images/" + imagePath), coordinates, Color.GRAY, board); // Use the BoardCell class' constructor.
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    public int getCost() {
        return COST;
    }

    // TODO: That's the case for SpecialCells, but not UtilityCells. Fix it.
    // TODO: Better comment
    /** Get the amount of rent charged for landing on this cell.
     *  This value depends on a random dice roll. */
    @Override
    public int getRent() {
        return 0;
    }

    /** Return whether or not this UtilityCell is 
     *  currently owned by a Player. */
    @Override
    public boolean isOwned() {
        return owned;
    }

    /** Get the cost of mortgaging this UtilityCell. */
    public int getMortgage() {
        return MORTGAGE;
    }
}
