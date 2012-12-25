package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import org.apache.batik.transcoder.image.JPEGTranscoder;

/** A BoardCell represents any of the cells running along the 
 *  outside edge of the game board. Since there are different types
 *  of cells running along the outside of the board, we use inheritance
 *  to create classes derived from BoardCells.
 *
 *  @author Daniel Neel */ // TODO: Improve that crap comment.

// TODO: Need to implement the whole idea of an onLand() method. Should simplify
//  things considerably.
// TODO: On go to jail, on land do a funny animation.

public abstract class BoardCell extends JPanel {
    // TODO: Add a field indicating whether or not a player is present 
    //       on this cell? And a method to set that.
    // TODO: Do I need this name field?
    /** A unique name that identifies this cell.
     *  For example, the "Park Place" property would have that name. */
    private String name;

    /** The Player that owns this property. If null, no player owns the property. */
    private Player ownedBy;

    /** Image associated with this property. The thing that gets displayed on the board. */
    private Icon image;

    /** The coordinates of this BoardCell in the GUI. */
    private final Point coordinates;

    /** A set of four positions that players stand on, that refer to this BoardCell. */
    private BoardPosition p1Pos;
    private BoardPosition p2Pos;
    private BoardPosition p3Pos;
    private BoardPosition p4Pos;

    /** Creates a BoardCell object, with the specified name, image path, coordinates, and 
     *  player standing positions. Does not have an owner. There are no players standing 
     *  on this object. */
    public BoardCell(String newName, String imagePath, Point coordinates) {
        name    = newName;
        ownedBy = null;
        image   = new ImageIcon("res/images/" + imagePath);
        this.coordinates = coordinates;

        if (coordinates.x >= 1 && coordinates.x <= 41 && coordinates.y == 1) {       // Top row.
            p1Pos = new BoardPosition(new Point(coordinates.x + 3, 0));
            p2Pos = new BoardPosition(new Point(coordinates.x + 2, 0));
            p3Pos = new BoardPosition(new Point(coordinates.x + 1, 0));
            p4Pos = new BoardPosition(new Point(coordinates.x,     0));
        }
        else if (coordinates.x >= 1 && coordinates.x <= 41 && coordinates.y == 41) { // Bottom row.
            p1Pos = new BoardPosition(new Point(coordinates.x,     45));
            p2Pos = new BoardPosition(new Point(coordinates.x + 1, 45));
            p3Pos = new BoardPosition(new Point(coordinates.x + 2, 45));
            p4Pos = new BoardPosition(new Point(coordinates.x + 3, 45));
        }
        else if (coordinates.y >= 2 && coordinates.y <= 40 && coordinates.x == 1) {  // Left column  (except top/bottom cells).
            p1Pos = new BoardPosition(new Point(0, coordinates.y));
            p2Pos = new BoardPosition(new Point(0, coordinates.y + 1));
            p3Pos = new BoardPosition(new Point(0, coordinates.y + 2));
            p4Pos = new BoardPosition(new Point(0, coordinates.y + 3));
        }
        else if (coordinates.y >= 2 && coordinates.y <= 40 && coordinates.x == 41) { // Right column (except top/bottom cells).
            p1Pos = new BoardPosition(new Point(45, coordinates.y + 3));
            p2Pos = new BoardPosition(new Point(45, coordinates.y + 2));
            p3Pos = new BoardPosition(new Point(45, coordinates.y + 1));
            p4Pos = new BoardPosition(new Point(45, coordinates.y));
        }

        add(new JLabel(image));
    }

    // TODO Make this abstract, and have all sub-classes implement it?
    /** Get the name of this property. */
    public String getName() {
        return name;
    }

    /** Get the owner of this property. */
    public Player getOwner() {
        return ownedBy;
    }

    /** Make player p the owner of this property. */
    public void setOwner(Player p) {
        ownedBy = p;
        // TODO: Have this function also set the relevant image for
        // the property to indicate ownership.
    }

    /** Get the coordinates for this BoardCell in the GUI. */
    public Point getCoordinates() {
        return coordinates;
    }

    // TODO: Look into abstract methods and classes for this part. It looks like those techniques
    // might help me solve this problem.
    // See this: http://www.cafeaulait.org/javafaq.html#xtocid2672631t
    /** Get the amount of rent charged for landing on this cell. */
    public abstract int getRent();

    // TODO: So on abstract methods, the basic idea is that the superclass provides
    // a method but no implementation, and all sub-classes provide the implementations.
    // So then I'm able to apply the method to a collection of the superclass, and be
    // sure it works correctly for each subclass. That fits exactly my problem here.
    // This would simplify things, as I wouldn't have to cast to Railroad/SpecialCell/
    // PropagandaOutlet/etc. - I could call the method on a BoardCell and know it works.
    //
    // So it would be useful to have getCost(), getName(), getRent() as abstract methods.
    //
    // Or maybe I could use interfaces, such as, for example, Buyable.
    /** Given a Player p, set the image icon for the BoardPosition associated
     *  with p on this cell. */
    public void setPositionImage(Player p, ImageIcon icon, GameBoard board) {
        if (p == board.player1)
            p1Pos.setIcon(icon);
        else if (p == board.player2)
            p2Pos.setIcon(icon);
        else if (p == board.player3)
            p3Pos.setIcon(icon);
        else if (p == board.player4)
            p4Pos.setIcon(icon);
    }

    /** Returns the BoardPosition associated with Player p. For example,
     *  if p is 2, we return player 2's BoardPosition.*/
    // TODO: I could make an array of positions, so it would be easy to do, for example,
    //       for (BoardPosition b : boardcell)
    public BoardPosition getPosition(int p) {
        if (p == 1)
            return p1Pos;
        else if (p == 2)
            return p2Pos;
        else if (p == 3)
            return p3Pos;
        else // if (p == 4)
            return p4Pos;
        // else // TODO: Make this an exception or similar?
        //     System.out.println("Error! Ya tried to get the position of a non-existant Player.");
    }

    /** Using the supplied parameters, generate an image of this BoardCell. name is used for
     *  the property name, which is displayed at the "top" of the image. The Player p is used to
     *  set the background color of the image, which indicates who owns the property. Color c is
     *  used to set the color of the horizontal bar at the "top" of the image. And orientation
     *  is used to determine whether to display the image upright, at 90 degrees, etc. */
    // LEFTOFFHERE: Implementing this SVG generation method.
    // TODO: Do I need the Player argument? Can't I use this.getOwner() or similar instead?
    public void generateImage(String name, Color playerColor, Color barColor, String orientation) {
        try {
            // TODO: Set the correct locale for Formatter? Getting a findbugs error.
            Formatter file = new Formatter("temp.svg");
            // TODO: Mediterranean Av., along with some other property, is the longest name I
            //       should expect to receive. Can use that to make sure the text fits.
            file.format("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
                    + "  <rect width=\"50\" height=\"80\" style=\"fill:rgb(" + playerColor.getRed() + "," + playerColor.getGreen() + "," + playerColor.getBlue() + "); stroke-width:1; stroke:rgb(0,0,0)\" />\n"
                    + "  <rect width=\"50\" height=\"20\" style=\"fill:rgb(" + barColor.getRed() + "," + barColor.getGreen() + "," + barColor.getBlue() + "); stroke-width:1; stroke:rgb(0,0,0)\" />\n"
                    + "  <g style=\"font-size: 7pt\">\n"
                    + "    <text x=\"5\"  y=\"10\">" + name + "</text>\n"
                    + "  </g>\n"
                    + "</svg>\n");
            file.close();

            // Then set image to the rasterized version.
            JPEGTranscoder t = new JPEGTranscoder();
            //	    image = ; // LEFTOFFHERE converting it.

            // And lastly, delete the file.
            File f = new File("temp.svg");

            if (f.delete() == false) {
                System.out.println("Error: SVG file does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    // TODO: Make creative alternatives to jail/free parking
    // TODO: Add a timer so they don't have time to add up their money?
}