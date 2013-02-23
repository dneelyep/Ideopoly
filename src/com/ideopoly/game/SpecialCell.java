package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Represents any of the BoardCells that do not easily
 *  fall into a specific category. Includes Go, Income
 *  Tax, In Jail, Free Parking, Go to Jail, Luxury Tax.
 *
 *  @author Daniel Neel*/
public class SpecialCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** Create a new SpecialCell with a given name,
     *  image, and x/y coordinates on the board.*/
    public SpecialCell(String newName, String imagePath, Point coordinates, final GameBoard board) {
        super(newName, new ImageIcon("res/images/" + imagePath), coordinates, Color.WHITE, board);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (board.getFocusedCell() == null) {
                    // TODO: Remove the labels when we mouse over an un-ownable property?
                    board.setGUIColor(SpecialCell.this.getColor());
                    board.setGUIHouseHotelCost("-");
                    board.setGUIRent("-");
                    board.setGUI1HouseLabel("-");
                    board.setGUI2HouseLabel("-");
                    board.setGUI3HouseLabel("-");
                    board.setGUI4HouseLabel("-");
                    board.setGUIHotel("-");
                }
            }
        });
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
