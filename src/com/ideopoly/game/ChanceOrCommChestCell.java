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
        // TODO Give these a unique color rather than sharing the same color with free parking/etc.
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
