package com.ideopoly.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Represents the common behaviors of the
 *  Water Works and Electric Company cells. */
public class UtilityCell extends BoardCell implements Ownable {
    // TODO: Add a test class and tests.
    /** The cost to buy this cell, unimproved. */
    private final int COST = 150;

    /** Status of this UtilityCell's ownership. If true, a Player owns the property. */
    private boolean owned;

    /** The GameBoard that contains this UtilityCell. */
    private GameBoard parentBoard;

    /** Create a new UtilityCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public UtilityCell(String newName, String imagePath, Point coordinates, GameBoard board) {
        super(newName, imagePath, coordinates, board); // Use the BoardCell class' constructor.
        this.parentBoard = board;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                parentBoard.setGUIColor(Color.GRAY);
                parentBoard.setGUICost("$" + Integer.toString(getCost()));
                // TODO: Fill in these things.
                // board.setGUIHouseHotelCost.setText("$" + Integer.toString(u.getHouseOrHotelCost()));
                // board.setGUIRent("$" + Integer.toString(u.getInitialRent()));
                // board.setGUI1House.setText("$" + Integer.toString(u.getRent1House()));
                // board.setGUI2House.setText("$" + Integer.toString(u.getRent2House()));
                // board.setGUI3House.setText("$" + Integer.toString(u.getRent3House()));
                // board.setGUI4House.setText("$" + Integer.toString(u.getRent4House()));
                // board.setGUIHotel.setText ("$" + Integer.toString(u.getRent1Hotel()));
                parentBoard.setGUIMortgage("$75");
                parentBoard.setGUIName(getName());
            }
        });
        owned = false;
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

    /** Set Player p as the owner of this UtilityCell, and charge
     *  them the correct amount of money. */
    @Override
    public void buy(Player p, GameBoard board) {
        p.payBank(this.getCost(), board);
        this.setOwner(p);
        board.printStatusAndLog(p.getName() + " bought " + this.getName() + " for $" + COST + ".");
    }

    /** Return whether or not this UtilityCell is 
     *  currently owned by a Player. */
    @Override
    public boolean isOwned() {
        return ((owned == true) ? true : false);
    }
}
