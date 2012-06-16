import javax.swing.*;

/** A BoardCell represents any of the cells running along the 
 *  outside edge of the game board. Since there are different types
 *  of cells running along the outside of the board, we use inheritance
 *  to create classes derived from BoardCells.
 *
 *  @author Daniel Neel */ // TODO: Improve that crap comment.

// TODO: Need to implement the whole idea of an onLand() method. Should simplify
// things considerably.
// TODO: BIG CHANCE FOR IMPROVEMENT: Rather than keeping the GUI representation separate from the
// BoardCell object, store for example the JLabels inside this class with appropriate coordinates.
// Should make things much clearer and clean up a lot of unneeded code.

// TODO: Review all methods, implement camelcase variable names and such for consistency.
// TODO: Make BoardCell implement ActionListener, like Menu.java, so I can get mouseover events on cells.
// TODO: Also, just have the subclasses implement the listener, since not all subclasses need to be listened to? IE: Go to jail.
// TODO: On go to jail, on land do a funny animation?


// LEFTOFFHERE: About to go ahead with this idea. Should help things make more sense I think.
// TODO: Consider making this an abstract class. I don't think I actually create any BoardCell
//       objects, just the sub-classes.
public class BoardCell {
    // TODO: Add a field indicating whether or not a player is present on this cell? And a method to set that.
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
    // TODO: Should I have these be private and get them through accessors?
    public BoardPosition p1Pos;
    public BoardPosition p2Pos;
    public BoardPosition p3Pos;
    public BoardPosition p4Pos;

    /** Creates a BoardCell object, with the specified name, image path, coordinates, and 
     *  player standing positions. Does not have an owner. There are no players standing 
     *  on this object. */
    // TODO: Need to test BoardPositions to make sure we get out correct x and 
    //       y values (should be doable visually).

    public BoardCell(String newName, String imagePath, int xPos, int yPos) {
	name	    = newName;
	ownedBy     = null;
	image	    = new ImageIcon("images/" + imagePath); // Use the path to generate the image.
	// TODO: Make the "images/" prefix automatic here?
	cellX	    = xPos;
	cellY       = yPos;

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
	else if (yPos >= 2 && yPos <= 40 && xPos == 1) {  // Left column  (excluding top/bottom cells).
	    p1Pos = new BoardPosition(0, cellY);
	    p2Pos = new BoardPosition(0, cellY + 1);
	    p3Pos = new BoardPosition(0, cellY + 2);
	    p4Pos = new BoardPosition(0, cellY + 3);
	}
	else if (yPos >= 2 && yPos <= 40 && xPos == 41) { // Right column (excluding top/bottom cells).
	    p1Pos = new BoardPosition(45, cellY + 3);
	    p2Pos = new BoardPosition(45, cellY + 2);
	    p3Pos = new BoardPosition(45, cellY + 1);
	    p4Pos = new BoardPosition(45, cellY);
	}
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
	// TODO: Have this function also set the relevant image for the property to indicate ownership.
	// Can use player.getImage()
    }

    /** Get the image associated with this property. */
    public Icon getImage() {
	return image;
    }

    public void setImage(Icon newImage) {
	image = newImage;
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
    /** Dummy method. Used so I can access getRent() from the derived class PropagandaOutlet. */
    public int getRent() {
	return 0;
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

    // TODO: Setters needed?

    // Start with these, add more if/where needed. Add specifics in inherited classes.
    // TODO: Note that subclasses inherit public and protected, not private members.
    // TODO: Implement those extra classes.


    /*
* Cell group
Represents a group of related properties.
** Fields
*** Id    // A unique identifier of the group. // TODO This needed?
*** Color // The color displayed for each property in this group.
TODO: Add an owner field to state that a person owns all sub-properties?
*** Owner // If a person owns all properties in the cell group, this is a player object referring to them.
*** Number_of_properties // The number of properties belonging to this cell group.
*** Vector/array Child_properties // A list of all sub-properties in this cell group.
** Methods

* Go to jail
** fields
** methods
*** onLand()
n**** Send the player directly to jail. Set the player's currently in jail value to the correct value.
* Jail // TODO: Make creative alternatives to jail/free parking
** Fields
** Methods
*** onLand()
**** Is the player just visiting?
**** Is player.numberofgetoutofjailcards > 0? // TODO: Should this go here?
******* Yes
******** Let player use it if they want
******* No
******** Do nothing.
* Free parking //TODO: Save this in a separate planning file in the assignment directory.
** Fields
** Methods
*** onLand()
Nothing happens when the player lands here.
* Scandalous tv show [Electric company]
** Fields
*** Owned_by
* Scandalous newspaper [water works]
** Owned_by
* Go
** Fields
** Methods
***** onLand(player) [Overload[?] the parent function] // When a player lands on the cell, give them two Glenn Becks ($200)
* Income tax
** Fields
** Methods
*** onLand()
**** Ask player whether they want to pay 10% or $200 bucks to the bank. Depending on which they pick, charge them the correct amount.
**** TODO Add a timer so they don't have time to add up their money? */
} 
