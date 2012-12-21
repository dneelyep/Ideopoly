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
    // v--- This gets rid of some compiler errors.
    private static final long serialVersionUID = 42L;

    /** The x/y coordinates of this BoardPosition in the GUI. */
    private final Point coordinates;

    // TODO Why do I have a separate image field, rather than using JLabel's built-in support?
    private Icon image;

    /** Create this object with the given x and y coordinates. */
    public BoardPosition(Point coordinates) {
        this.coordinates = coordinates;
        image     = new ImageIcon("res/images/noPlayerPresent.jpg");
        this.setIcon(image);
    }

    /** Get the coordinates belonging to this BoardPosition. */
    public Point getCoordinates() {
        return coordinates;
    }

    /** Get the image associated with this position. */
    public Icon getImage() {
        return image;
    }

    /** Set the image associated with this position to
     *  the indicated image i. */
    public void setImage(Icon i) {
        // TODO: This is a bit confusing.
        image = i;
        this.setIcon(i);
    }
}
