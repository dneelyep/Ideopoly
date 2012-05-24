import javax.swing.*;
import java.awt.*;

/** Class that represents a player. Can be a computer or human-controlled character.
 * 
 *  @author Daniel Neel */
public class Player {

    /** Name of this player. */
    private String name;

    /** Player's jail status.
     *  0 = not in jail.
     *  1 = last week in jail.
     *  2 = second week in jail.
     *  3 = first week in jail.
     *  Other values not allowed. */
    private int inJail; // TODO: This needed? Couldn't I just use board position?

    /** Amount of one dollar bills this player has. */
    private int ones;

    /** Amount of five dollar bills this player has. */
    private int fives;

    /** Amount of ten dollar bills this player has. */
    private int tens;

    /** Amount of twenty dollar bills this player has. */
    private int twenties;

    /** Amount of fifty dollar bills this player has. */
    private int fifties;

    /** Amount of hundred dollar bills this player has. */
    private int hundreds;

    /** Total amount of money this player has. */
    private int totalMoney;

    /** Amount of five hundred dollar bills this player has. */
    private int fiveHundreds;

    /** Amount of properties this player owns. */
    private int totalPropertiesOwned;

    /** Amount of houses this player owns. */
    private int totalHousesOwned;

    /** Amount of hotels this player owns. */
    private int totalHotelsOwned;

    /** Number of get out of jail free cards this player owns. */
    private int GOOJFCards;

    /** This player's current Board Cell, from 0-39. */
    private BoardCell currentCell;

    /** Image associated with this player. Used as an icon to indicate board position. */
    private Icon image;

    /** An index that represents which element of BoardProperties[] this Player is standing on. */
    private int cellIndex;

    // TODO: Make add/remove functions for ownedOutlets. Use something other than an array for ease?

    /** Actions to take when a player object is initially created. Players
     *  are only created at the start of the game. Different players start
     *  at different positions on the board (Same cell, different walking space.) */
    public Player(int playerNumber, IdeopolyGUI gui) {

	inJail = 0; // TODO: This needed? Couldn't I just use board position?

	// Initial cash values.
	ones	      = 5;
	fives	      = 5;
	tens	      = 5;
	twenties      = 6;
	fifties	      = 2;
	hundreds      = 2;
	fiveHundreds  = 2;
	totalMoney    = (ones + (fives * 5) + (tens * 10) + (twenties * 20)
	                + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );

	totalPropertiesOwned = 0;
	totalHousesOwned     = 0;
	totalHotelsOwned     = 0;

	// TODO: Create an empty array? Or is it already created?
	//	ownedOutlets[] = ;

	GOOJFCards = 0;

	currentCell = gui.boardProperties[0];
	cellIndex   = 0;
	switch (playerNumber) {
	    case 1: name = "Player 1 (H)";
		image = new ImageIcon("images/p1Present.jpg");
		break;
	    case 2: name = "Player 2 (C)";
		image = new ImageIcon("images/p2Present.jpg");
		break;
	    case 3: name = "Player 3 (C)";
		image = new ImageIcon("images/p3Present.jpg");
		break;
	    case 4: name = "Player 4 (C)";
		image = new ImageIcon("images/p4Present.jpg");
		break;

	    default: System.out.println("Error: tried to create a non-standard player.");
	}
    }

    /** Get the amount of bills of the given type for this player. */
    // TODO: Make this return an int instead? Would make much more sense.
    public String getCash(String billType) {
	// TODO: Have the string parameter refer to the actual value. So i could just say "return billType" 
	// rather than switch

	switch (billType) {
	    case "ones":
		return Integer.toString(ones);
	    case "fives":
		return Integer.toString(fives);
	    case "tens":
		return Integer.toString(tens);
	    case "twenties":
		return Integer.toString(twenties);
	    case "fifties":
		return Integer.toString(fifties);
	    case "hundreds":
		return Integer.toString(hundreds);
	    case "fiveHundreds": // TODO: Replace this with 'fiveHundreds'
		return Integer.toString(fiveHundreds);
            case "total":
		return Integer.toString(totalMoney);
	    default:
		return "Error! Incorrect argument."; // TODO: Make this an error code thing.
	}
    }

    /** Give this player a new property. */
    public void addProperty(BoardCell property) {
	//TODO: Implement this. add the property, and increment number of properties owned.
    }
    /** Remove a property from this player. */
    public void removeProperty(BoardCell property) {
	//TODO: Implement this. remove the property, decrement number of properties owned.
    }

    /** Have this player roll the dice. */
    public void roll() {
	// TODO: Implement this
    }

    /** Return the image associated with this player. */
    public Icon getImage() {
	return image;
    }

    /** Get this player's current position. */
    public BoardCell getCell() {
	return currentCell;
    }

    /** Move this player to a given position q. q refers to the cell the Player wants 
     *  to move to. For example, changePosition(3) will move any player to the 4th
     *  BoardCell on the board. */
    // TODO: Just pass in a single value, and have that be boardProperties[q] and index = q.
    public void changeCell(BoardCell q, int index) { // TODO: Better function name.
	currentCell = q;
	cellIndex = index;
    }

    /** Change this player's amount a of currency type t. */
    // TODO: Rename this setCash() ? Seems more consistent with getters/setters, and 
    //       is more accurate to its purpose.
    public void addCash(String t, int a) {
	// TODO: This results in the Player having negative cash values. That's kind 
	// of impossible...
	switch (t) {
	case "ones":         ones  += a;
	    break;
	case "fives":        fives += a;
	    break;
	case "tens":         tens  += a;
	    break;
	case "twenties":     twenties += a;
	    break;
	case "fifties":      fifties  += a;
	    break;
	case "hundreds":     hundreds += a;
	    break;
	case "fiveHundreds": fiveHundreds += a;
	    break;
	default: System.out.println("Invalid currency amount.");
	    break;
	}

	// After money total changes, update the totalMoney field accordingly.
	totalMoney = ( ones + (fives * 5) + (tens * 10) + (twenties * 20) + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );

    }

    /** Set the value of this Player's inJail property. */
    public void setInJail(int weeks) {
	if (weeks != 0 && weeks != 1 && weeks != 2 && weeks != 3)
	    System.out.println("Invalid weeks value. Must be either 0, 1, 2, or 3.");
	else
	    inJail = weeks;
    }

    /** Give this player a get out of jail free card. */
    public void giveGOOJF() {
	GOOJFCards++;
    }

    /** Have this player spend one of their get out of jail free cards. */
    public void spendGOOJF() {
	if (GOOJFCards <= 0) {
	    // TODO: Display this--v in status message area.
	    System.out.println("Error! You can't spend a Get Out of Jail Free card if you have 0 or less.");
	}
	else {
	    //TODO: Don't allow this when the person's not in jail.
	    GOOJFCards--;
	    inJail = 0;
	}
    }

    /** Return the number of GOOJF cards this player owns. */
    public int getNumGOOJFCards() {
	return GOOJFCards;
    }

    /** See what week of jail this person is in. */
    public int getJailStatus() {
	return inJail;
    }

    /** Get the number of houses this player owns. */
    public int getNumHouses() {
	return totalHousesOwned;
    }

    /** Get the number of hotels this player owns. */
    public int getNumHotels() {
	return totalHotelsOwned;
    }

    /** Determine if the given amount will bankrupt this Player
     *  if (s)he has to pay it.
     *  Return True if Player will be bankrupted, False otherwise. */
    public Boolean willBankrupt(int amount) {
	if ( totalMoney < amount ) {
	    return true;
	}
	else {
	    return false;
	}
    }

    /** Convert this Player's cash into bill type numToSwitch.
     *  For example, if a Player needs more 5s, this will convert all the Player's
     *  cash to 5s. By default (with an invalid argument), converts to 500s. */
    // TODO: Test this function more, make sure it works correctly in all cases.
    public void spreadCash(int numToSwitch) {

	// The amount still left to be spread out.
	int amountNotSpread = totalMoney;
	// The number of a given type of bill spread.
	int numSpread;

	fiveHundreds = 0;
	hundreds     = 0;
	fifties	     = 0;
	twenties     = 0;
	tens	     = 0;
	fives	     = 0;
	ones	     = 0;

	int numbers[]      = {500, 100, 50, 20, 10, 5, 1};
	int actualValues[] = {fiveHundreds, hundreds, fifties, twenties, tens, fives, ones};

	// TODO: Clean up this mess of code.
	//	if input is x, set item[0] to x, and item[x] to fiveHundreds
	//      And update the rest of the behavior accordingly.
	if (numToSwitch == 1) {
	    numbers[0] = 1;
	    actualValues[0] = ones;
	    numbers[6] = 500;
	    actualValues[6] = fiveHundreds ;

	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);
		    // Can use this for debugging later if want.
		    // System.out.println("Amount not spread: " + amountNotSpread);
		    // System.out.println("Five Hundreds: " + actualValues[6]);
		    // System.out.println("Hundreds: "      + actualValues[1]);
		    // System.out.println("Fifties: "       + actualValues[2]);
		    // System.out.println("Twenties: "      + actualValues[3]);
		    // System.out.println("Tens: "          + actualValues[4]);
		    // System.out.println("Fives: "         + actualValues[5]);
		    // System.out.println("Ones: "          + actualValues[0]);

		    fiveHundreds = actualValues[6];
		    hundreds     = actualValues[1];
		    fifties	 = actualValues[2];
		    twenties     = actualValues[3];
		    tens	 = actualValues[4];
		    fives	 = actualValues[5];
		    ones         = actualValues[0];
		}
	    }
	}
	else if (numToSwitch == 5) {
	    numbers[0] = 5;
	    actualValues[0] = fives;
	    numbers[5] = 500;
	    actualValues[5] = fiveHundreds;

	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);

		    fiveHundreds = actualValues[5];
		    hundreds     = actualValues[1];
		    fifties	     = actualValues[2];
		    twenties     = actualValues[3];
		    tens	     = actualValues[4];
		    fives	     = actualValues[0];
		    ones         = actualValues[6];
		}
	    }
	}
	else if (numToSwitch == 10) {
	    numbers[0] = 10;
	    actualValues[0] = tens;
	    numbers[4] = 500;
	    actualValues[4] = fiveHundreds;

	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);

		    fiveHundreds = actualValues[4];
		    hundreds     = actualValues[1];
		    fifties	 = actualValues[2];
		    twenties     = actualValues[3];
		    tens	 = actualValues[0];
		    fives	 = actualValues[5];
		    ones         = actualValues[6];
		}
	    }
	}
	else if (numToSwitch == 20) {
	    numbers[0] = 20;
	    actualValues[0] = twenties;
	    numbers[3] = 500;
	    actualValues[3] = fiveHundreds;

	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);

		    fiveHundreds = actualValues[3];
		    hundreds     = actualValues[1];
		    fifties      = actualValues[2];
		    twenties     = actualValues[0];
		    tens         = actualValues[4];
		    fives        = actualValues[5];
		    ones         = actualValues[6];
		}
	    }
	}
	else if (numToSwitch == 50) {
	    numbers[0] = 50;
	    actualValues[0] = fifties;
	    numbers[2] = 500;
	    actualValues[2] = fiveHundreds;

	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);

		    fiveHundreds = actualValues[2];
		    hundreds     = actualValues[1];
		    fifties      = actualValues[0];
		    twenties     = actualValues[3];
		    tens         = actualValues[4];
		    fives        = actualValues[5];
		    ones         = actualValues[6];
		}
	    }
	}
	else if (numToSwitch == 100) {
	    numbers[0] = 100;
	    actualValues[0] = hundreds;
	    numbers[1] = 500;
	    actualValues[1] = fiveHundreds;

	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);

		    fiveHundreds = actualValues[1];
		    hundreds     = actualValues[0];
		    fifties      = actualValues[2];
		    twenties     = actualValues[3];
		    tens         = actualValues[4];
		    fives        = actualValues[5];
		    ones         = actualValues[6];
		}
	    }
	}
	else { // Default, orders by 500s.
	    for (int i = 0; i <= 6; i++) {
		if ( (amountNotSpread / numbers[i]) != 0) {
		    numSpread = amountNotSpread / numbers[i];
		    actualValues[i] += numSpread;
		    amountNotSpread -= (numbers[i] * numSpread);

		    fiveHundreds = actualValues[0];
		    hundreds     = actualValues[1];
		    fifties	 = actualValues[2];
		    twenties     = actualValues[3];
		    tens	 = actualValues[4];
		    fives	 = actualValues[5];
		    ones         = actualValues[6];
		}
	    }
	}
    }
    /** Bankrupt this player. */
    public void bankruptPlayer() {
	// TODO: Do more than just set cash values. The player can still 
	// be considered alive, given money, etc. in this state.
	// TODO: Set the player's text to red when this happens maybe also?
	// TODO: Maybe give the player an isBankrupt field, that can be used elsewhere.
	ones         = 0;	
	fives	     = 0;
	tens	     = 0;
	twenties     = 0;
	fifties	     = 0;
	hundreds     = 0;
	fiveHundreds = 0;
	totalMoney   = 0;
	image = null;
    }

    /** Get the name of this Player. */
    public String getName() {
	return name;
    }

    /** Get the number of properties this Player owns. */
    public int getNumOwnedProperties() {
	return totalPropertiesOwned;
    }

    /** Get the index of the BoardCell in boardProperties[] this Player 
     *  is standing on. */
    public int getIndex() {
	return cellIndex;
    }

    /** Put the given Player p in jail, and enable the "Use get 
     *  out of jail free card" button if the Player is the human player. */
    public void putInJail(IdeopolyGUI gui) {
	// TODO: Make a visual indicator for when a person's in jail. 
	// IE: put a little colored square on their portrait that indicates the week they're in.
	// Or change the color of the text by their name.

	// Allow the main player to use their cards.
	if (name == "Player 1 (H)" && GOOJFCards > 0) {
	    gui.useGOOJFCard.setEnabled(true);
	}
	changeCell(gui.boardProperties[10], 10);
	// TODO: Seems kind of pointless to have two separate methods for this...
	setInJail(3);
    }
}