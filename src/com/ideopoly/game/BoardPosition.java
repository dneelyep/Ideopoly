package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;

/** Class that represents a standing position on the game board.
 *  These are used as indicators of where players are currently 
 *  standing on the board. This class mainly exists so I can
 *  group coordinates and an image with a JLabel. So I can make
 *  a collection of these and say collection[x].changeimage(), etc.
 *
 *  @author Daniel Neel */
public class BoardPosition extends JLabel {
    // TODO Get rid of this class altogether, I don't need it. I can just use get/putClientProperty to store x/y coords,
    //      and store the images in a JLabel.

    // v--- This gets rid of some compiler errors.                                            key
    private static final long serialVersionUID = 42L;

    /** The x/y coordinates of this BoardPosition in the GUI. */
    private final Point coordinates;

    /** Create this object with the given x and y coordinates. */
    public BoardPosition(Point coordinates) {
        this.coordinates = coordinates;
        setIcon(new ImageIcon("res/images/noPlayerPresent.jpg"));
    }

    /** Get the coordinates belonging to this BoardPosition. */
    public Point getCoordinates() {
        return coordinates;
    }
}
