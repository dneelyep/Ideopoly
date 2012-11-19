package com.ideopoly.game;

import javax.swing.*;
import java.awt.event.*;
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

// TODO: This should extend JButton/JLabel or similar.
public abstract class BoardCell {
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

    // TODO: Try to make this static, final. Why? So it can't be altered, is constant. Difficult to do though, since it's set in the constructor.
    /** The x-coordinate where this cell should be placed. Used to calculate board positions. */
    private int cellX;

    /** The y-coordinate where this cell should be placed. Used to calculate board positions. */
    private int cellY;

    /** A set of four positions that players stand on, that refer to this BoardCell. */
    private BoardPosition p1Pos;
    private BoardPosition p2Pos;
    private BoardPosition p3Pos;
    private BoardPosition p4Pos;

    /** The graphical representation of this BoardCell. */
    private BoardCellGUI graphicalRepresentation;

    /** The board associated with this BoardCell. */
    public GameBoard board;

    /** Creates a BoardCell object, with the specified name, image path, coordinates, and 
     *  player standing positions. Does not have an owner. There are no players standing 
     *  on this object. */
    public BoardCell(String newName, String imagePath, int xPos, int yPos, GameBoard g) {
        name    = newName;
        ownedBy = null;
        image   = new ImageIcon("src/com/ideopoly/game/images/" + imagePath);
        cellX   = xPos;
        cellY   = yPos;
        board   = g;

        if (xPos >= 1 && xPos <= 41 && yPos == 1) {       // Top row.
            p1Pos = new BoardPosition(cellX + 3, 0);
            p2Pos = new BoardPosition(cellX + 2, 0);
            p3Pos = new BoardPosition(cellX + 1, 0);
            p4Pos = new BoardPosition(cellX,     0);
        }
        else if (xPos >= 1 && xPos <= 41 && yPos == 41) { // Bottom row.
            p1Pos = new BoardPosition(cellX,     45);
            p2Pos = new BoardPosition(cellX + 1, 45);
            p3Pos = new BoardPosition(cellX + 2, 45);
            p4Pos = new BoardPosition(cellX + 3, 45);
        }
        else if (yPos >= 2 && yPos <= 40 && xPos == 1) {  // Left column  (except top/bottom cells).
            p1Pos = new BoardPosition(0, cellY);
            p2Pos = new BoardPosition(0, cellY + 1);
            p3Pos = new BoardPosition(0, cellY + 2);
            p4Pos = new BoardPosition(0, cellY + 3);
        }
        else if (yPos >= 2 && yPos <= 40 && xPos == 41) { // Right column (except top/bottom cells).
            p1Pos = new BoardPosition(45, cellY + 3);
            p2Pos = new BoardPosition(45, cellY + 2);
            p3Pos = new BoardPosition(45, cellY + 1);
            p4Pos = new BoardPosition(45, cellY);
        }

        graphicalRepresentation = new BoardCellGUI(image);
    }

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

    /** Get this cell's x position. */
    // TODO: Rename these for consistency between these and the getXCoord() method elsewhere?
    public int getX() {
        return cellX;
    }

    /** Get this cell's y position. */
    public int getY() {
        return cellY;
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

    /** Get the BoardCellGUI that represents this BoardCell. */
    public BoardCellGUI getGraphicalRepresentation() {
        return graphicalRepresentation;
    }

    /** Given a Player p, set the image icon for the BoardPosition associated
     *  with p on this cell. */
    public void setPositionImage(Player p, Icon icon, GameBoard board) {
        if (p == board.player1)
            p1Pos.setImage(icon);
        else if (p == board.player2)
            p2Pos.setImage(icon);
        else if (p == board.player3)
            p3Pos.setImage(icon);
        else if (p == board.player4)
            p4Pos.setImage(icon);
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
     *  the property name, which is distplayed at the "top" of the image. The Player p is used to
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
            graphicalRepresentation = new BoardCellGUI(image);

            // And lastly, delete the file.
            File f = new File("temp.svg");

            if (f.delete() == false) {
                System.out.println("Error: SVG file does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }


    // TODO: Add tests for this nested class.
    // TODO: Better class name. BoardCellGUI implies that this is the picture displayed for
    //       the cell around the edge of the board, where this is really the detailed property
    //       information on the right side of the screen.
    /** BoardCellGUI - The graphical representation of any BoardCell object.
     *  We extend JPanels to allow them to receieve mouse events. That way, we can
     *  tell when and how the user mouses over, clicks on, etc. the panels.
     *
     * @author Daniel Neel */
    private class BoardCellGUI extends JPanel implements MouseListener {
        // TODO: Several files have this 42L thing. What is it? Why do I need it? Will
        // the same value in all cases cause errors?
        // v--- This gets rid of some compiler errors.
        private static final long serialVersionUID = 42L;

        public BoardCellGUI(Icon image) {
            add(new JLabel(image));
            addMouseListener(this);
        }

        /** When the mouse hovers over a property, display relevant
         *  information in the detailed property info. GUI section. */
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO: Tests for this stuff.
            if (BoardCell.this instanceof Railroad) {
                // TODO Do I even need to do this cast?
                Railroad r = (Railroad) BoardCell.this;
                board.setGUIColor(Color.BLACK);
                board.setGUICost("$" + r.getCost());
                // TODO: Change the gui text to 1RR/2RRs/etc. maybe
                //       where houses would be displayed.
                board.setGUIRent("$" + r.getRent());
                // TODO: Combine the unownable/can't buy parts? Reduce a bit of duplication.
                board.setGUI1House("-");
                board.setGUI2House("-");
                board.setGUI3House("-");
                board.setGUI4House("-");
                board.setGUIHotel("-");
                board.setGUIMortgage("$" + r.getMortgageValue());
            }
            else if (BoardCell.this instanceof PropagandaOutlet) {
                PropagandaOutlet p = (PropagandaOutlet) BoardCell.this;

                board.setGUIColor(p.getColor());
                // TODO: Find a way to prepend the $ automatically.
                board.setGUICost("$" + Integer.toString(p.getCost()));
                board.setGUIHouseHotelCost("$" + Integer.toString(p.getHouseOrHotelCost()));
                board.setGUIRent("$" + Integer.toString(p.getInitialRent()));
                board.setGUI1House("$" + Integer.toString(p.getRent1House()));
                board.setGUI2House("$" + Integer.toString(p.getRent2House()));
                board.setGUI3House("$" + Integer.toString(p.getRent3House()));
                board.setGUI4House("$" + Integer.toString(p.getRent4House()));
                board.setGUIHotel("$" + Integer.toString(p.getRent1Hotel()));
                board.setGUIMortgage("$" + Integer.toString(p.getMortgageValue()));
            }
            else if (BoardCell.this instanceof UtilityCell) {
                UtilityCell u = (UtilityCell) BoardCell.this;

                board.setGUIColor(Color.GRAY);
                board.setGUICost("$" + Integer.toString(u.getCost()));
                // TODO: Fill in these things.
                // board.setGUIHouseHotelCost.setText("$" + Integer.toString(u.getHouseOrHotelCost()));
                // board.setGUIRent("$" + Integer.toString(u.getInitialRent()));
                // board.setGUI1House.setText("$" + Integer.toString(u.getRent1House()));
                // board.setGUI2House.setText("$" + Integer.toString(u.getRent2House()));
                // board.setGUI3House.setText("$" + Integer.toString(u.getRent3House()));
                // board.setGUI4House.setText("$" + Integer.toString(u.getRent4House()));
                // board.setGUIHotel.setText ("$" + Integer.toString(u.getRent1Hotel()));
                board.setGUIMortgage("$75");
            }
            else if (BoardCell.this instanceof SpecialCell ||  BoardCell.this instanceof ChanceOrCommChestCell) {
                // TODO: Remove the labels when we mouse over an un-ownable property?
                board.setGUIColor(Color.WHITE);
                board.setGUICost("-");
                board.setGUIHouseHotelCost("-");
                board.setGUIRent("-");
                board.setGUI1House("-");
                board.setGUI2House("-");
                board.setGUI3House("-");
                board.setGUI4House("-");
                board.setGUIHotel("-");
                board.setGUIMortgage("-");
            }

            board.setGUIName(name);
        }
        // TODO: Is there a better way to do this? YES there is, see Swing adapters.
        @Override
        public void mouseExited(MouseEvent e) {	}
        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
    }

    // Start with these, add more if/where needed. Add specifics in inherited classes.
    // TODO: Make creative alternatives to jail/free parking
    // TODO: Add a timer so they don't have time to add up their money?
} 
