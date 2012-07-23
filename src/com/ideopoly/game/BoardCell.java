package com.ideopoly.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/** A BoardCell represents any of the cells running along the 
 *  outside edge of the game board. Since there are different types
 *  of cells running along the outside of the board, we use inheritance
 *  to create classes derived from BoardCells.
 *
 *  @author Daniel Neel */ // TODO: Improve that crap comment.

// TODO: Need to implement the whole idea of an onLand() method. Should simplify
// things considerably.

// TODO: Have the subclasses only implement the MouseListener, since not all subclasses need to be listened to? IE: Go to jail.
// TODO: On go to jail, on land do a funny animation.
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

    /** The GUI associated with this BoardCell. */
    public IdeopolyGUI gui;

    /** Creates a BoardCell object, with the specified name, image path, coordinates, and 
     *  player standing positions. Does not have an owner. There are no players standing 
     *  on this object. */
    public BoardCell(String newName, String imagePath, int xPos, int yPos, IdeopolyGUI g) {
	name    = newName;
	ownedBy = null;
	image   = new ImageIcon("images/" + imagePath);
	cellX   = xPos;
	cellY   = yPos;
	gui     = g;

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

    /** Get the image associated with this property. */
    public Icon getImage() {
	return image;
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

    /** Get the BoardCellGUI that represents this BoardCell. */
    public BoardCellGUI getGraphicalRepresentation() {
	return graphicalRepresentation;
    }

    /** Given a Player p, set the image i for the BoardPosition associated 
     *  with p on this cell. */
    public void setPositionImage(Player p, Icon i, IdeopolyGUI gui) {
	if (p == gui.player1)
	    p1Pos.setImage(i);
	else if (p == gui.player2)
	    p2Pos.setImage(i);
	else if (p == gui.player3)
	    p3Pos.setImage(i);
	else if (p == gui.player4)
	    p4Pos.setImage(i);
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

    // TODO: Add tests for this nested class.
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
	    // TODO: Use substring for these to shorten the conditional.

	    if (BoardCell.this.getClass().getName() == "com.ideopoly.game.Railroad") {
		Railroad r = (Railroad) BoardCell.this;
		gui.setGUIColor(Color.BLACK);
		gui.setGUICost("$" + Integer.toString(r.getCost()));
		// TODO: Change the gui text to 1RR/2RRs/etc. maybe 
		//       where houses would be displayed.
		gui.setGUIRent("$" + Integer.toString(r.getRent()));
		// TODO: Combine the unownable/can't buy parts? Reduce a bit of duplication.
		gui.setGUI1House("-");
		gui.setGUI2House("-");
		gui.setGUI3House("-");
		gui.setGUI4House("-");
		gui.setGUIHotel("-");
		gui.setGUIMortgage("$" + Integer.toString(r.getMortgageValue()));
	    }
	    else if (BoardCell.this.getClass().getName() == "com.ideopoly.game.PropagandaOutlet") {
		PropagandaOutlet p = (PropagandaOutlet) BoardCell.this;

		gui.setGUIColor(p.getColor());
		// TODO: Find a way to prepend the $ automatically.
		gui.setGUICost("$" + Integer.toString(p.getCost()));
		gui.setGUIHouseHotelCost("$" + Integer.toString(p.getHouseOrHotelCost()));
		gui.setGUIRent("$" + Integer.toString(p.getInitialRent()));
		gui.setGUI1House("$" + Integer.toString(p.getRent1House()));
		gui.setGUI2House("$" + Integer.toString(p.getRent2House()));
		gui.setGUI3House("$" + Integer.toString(p.getRent3House()));
		gui.setGUI4House("$" + Integer.toString(p.getRent4House()));
		gui.setGUIHotel("$" + Integer.toString(p.getRent1Hotel()));
		gui.setGUIMortgage("$" + Integer.toString(p.getMortgageValue()));
	    }
	    else if (BoardCell.this.getClass().getName() == "com.ideopoly.game.UtilityCell") {
		UtilityCell u = (UtilityCell) BoardCell.this;

		gui.setGUIColor(Color.GRAY);
		gui.setGUICost("$" + Integer.toString(u.getCost()));
		// TODO: Fill in these things.
		// gui.setGUIHouseHotelCost.setText("$" + Integer.toString(u.getHouseOrHotelCost()));
		// gui.setGUIRent("$" + Integer.toString(u.getInitialRent()));
		// gui.setGUI1House.setText("$" + Integer.toString(u.getRent1House()));
		// gui.setGUI2House.setText("$" + Integer.toString(u.getRent2House()));
		// gui.setGUI3House.setText("$" + Integer.toString(u.getRent3House()));
		// gui.setGUI4House.setText("$" + Integer.toString(u.getRent4House()));
		// gui.setGUIHotel.setText ("$" + Integer.toString(u.getRent1Hotel()));
		gui.setGUIMortgage("$75");
	    }
	    else if (BoardCell.this.getClass().getName() == "com.ideopoly.game.SpecialCell"
	          || BoardCell.this.getClass().getName() == "com.ideopoly.game.ChanceOrCommChestCell") {
		// TODO: Remove the labels when we mouse over an un-ownable property?
		gui.setGUIColor(Color.WHITE);
		gui.setGUICost("-");
		gui.setGUIHouseHotelCost("-");
		gui.setGUIRent("-");
		gui.setGUI1House("-");
		gui.setGUI2House("-");
		gui.setGUI3House("-");
		gui.setGUI4House("-");
		gui.setGUIHotel("-");
		gui.setGUIMortgage("-");
	    }

	    gui.setGUIName(name);
	}
	// TODO: Is there a better way to do this?
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
    // TODO: Add an owner field to state that a person owns all sub-properties?
    // TODO: Make creative alternatives to jail/free parking
    // TODO: Add a timer so they don't have time to add up their money?
} 
