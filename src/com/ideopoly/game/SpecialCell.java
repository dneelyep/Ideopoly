package com.ideopoly.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Represents any of the BoardCells that do not
 *  easily fall into a specific category. Go, Income
 *  Tax, In Jail, Free Parking, Go to Jail, Luxury
 *  Tax. */
public class SpecialCell extends BoardCell {
    // TODO: Add a test class and tests.

    /** The GameBoard that contains this SpecialCell. */
    private GameBoard parentBoard;

    /** Create a new SpecialCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public SpecialCell(String newName, String imagePath, Point coordinates, GameBoard board) {
        super(newName, imagePath, coordinates, board); // Use the BoardCell class' constructor.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO: Remove the labels when we mouse over an un-ownable property?
                parentBoard.setGUIColor(Color.WHITE);
                parentBoard.setGUICost("-");
                parentBoard.setGUIHouseHotelCost("-");
                parentBoard.setGUIRent("-");
                parentBoard.setGUI1HouseLabel("-");
                parentBoard.setGUI2HouseLabel("-");
                parentBoard.setGUI3HouseLabel("-");
                parentBoard.setGUI4HouseLabel("-");
                parentBoard.setGUIHotel("-");
                parentBoard.setGUIMortgage("-");
                parentBoard.setGUIName(getName());
            }
        });
        this.parentBoard = board;
    }

    // TODO: Handle this better than returning a 0 value. Un-elegant.
    /** Get the amount of rent charged for landing on this cell.
     *  Since the Player is not charged for landing on any SpecialCells,
     *  we return 0 for the amount. */
    @Override
    public int getRent() {
        return 0;
    }
}
