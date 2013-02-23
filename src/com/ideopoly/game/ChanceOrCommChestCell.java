package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Represents a Chance or Community Chest
 *  cell on the game board.
 *
 *  @author Daniel Neel */
// TODO: More concise name for this class.
public class ChanceOrCommChestCell extends BoardCell {
    // TODO: Add a test class and tests.
    /** Create a new ChanceOrCommChestCell with a given
     *  name, image, and x/y coordinates on the board.*/
    public ChanceOrCommChestCell(String newName, String imagePath, Point coordinates, final GameBoard board) {
        super(newName, new ImageIcon("res/images/" + imagePath), coordinates, Color.WHITE, board);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Make this a unique color rather than sharing the same color with free parking/etc.
                // TODO: Remove the labels when we mouse over an un-ownable property?
                if (board.getFocusedCell() == null) {
                    board.setGUIColor(ChanceOrCommChestCell.this.getColor());
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
     *  Since the Player is not charged for landing on Chance/CommChest
     *  cells, we return 0 for the amount. */
    @Override
    public int getRent() {
        return 0;
    }
}
