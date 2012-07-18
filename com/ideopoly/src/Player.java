package com.ideopoly.src;

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
	updateTotalMoney();

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
    public int getCash(String billType) {
	// TODO: Have the string parameter refer to the actual value. So i could just 
	// say "return billType" rather than switch

	switch (billType) {
	    case "ones":
		return ones;
	    case "fives":
		return fives;
	    case "tens":
		return tens;
	    case "twenties":
		return twenties;
	    case "fifties":
		return fifties;
	    case "hundreds":
		return hundreds;
	    case "fiveHundreds": // TODO: Replace this with 'fiveHundreds'
		return fiveHundreds;
            case "total":
		return totalMoney;
	    default:
		System.out.println("Error! Incorrect argument."); 
		return -15; // TODO: Make this error code thing make sense.
	}
    }


    /** Return the image associated with this player. */
    public Icon getImage() {
	return image;
    }

    /** Get this player's current position. */
    public BoardCell getCell() {
	return currentCell;
    }

    // TODO: Do I need this really?
    /** Helper function. Return the name of this Player's currentCell's class's name. */
    public String getCellClassName() {
	return currentCell.getClass().getName();
    }

    /** Set this Player's currency of type t to a given amount a.*/
    // TODO: With this, is addCash() now obsolete? For example, 
    //       I could setCash("x", p.getCash("x") + 5)
    public void setCash(String t, int a) {
	// TODO: Make sure this handles negative values appropriately.
	switch (t) {
	case "ones":         ones  = a;
	    break;
	case "fives":        fives = a;
	    break;
	case "tens":         tens  = a;
	    break;
	case "twenties":     twenties = a;
	    break;
	case "fifties":      fifties  = a;
	    break;
	case "hundreds":     hundreds = a;
	    break;
	case "fiveHundreds": fiveHundreds = a;
	    break;
	default: System.out.println("Invalid currency amount.");
	    break;
	}

	updateTotalMoney();
    }

    /** Update this Player's totalMoney value. */
    private void updateTotalMoney() {
	totalMoney = ( ones + (fives * 5) + (tens * 10) + (twenties * 20) + (fifties * 50) + (hundreds * 100) + (fiveHundreds * 500) );
    }

    /** Return the number of GOOJF cards this player owns. */
    public int getNumGOOJFCards() {
	return GOOJFCards;
    }

    /** See what week of jail this person is in. */
    public int getJailStatus() {
	return inJail;
    }

    /** Set the value of this Player's inJail property. */
    public void setJailStatus(int weeks) {
	if (weeks != 0 && weeks != 1 && weeks != 2 && weeks != 3)
	    System.out.println("Invalid weeks value. Must be either 0, 1, 2, or 3.");
	else
	    inJail = weeks;
    }

    /** Get the number of houses this player owns. */
    public int getNumHouses() {
	return totalHousesOwned;
    }

    /** Get the number of hotels this player owns. */
    public int getNumHotels() {
	return totalHotelsOwned;
    }

    /** Set the number of houses this player owns. */
    public void setNumHouses(int num) {
	totalHousesOwned = num;
    }

    /** Set the number of hotels this player owns. */
    public void setNumHotels(int num) {
	totalHotelsOwned = num;
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

    /** Give this player a new property. */
    public void addProperty(BoardCell property) {
	// TODO: Implement this. add the property, and increment number of properties owned.
	// TODO: Rename buyProperty() ?
	// TODO: Below are some thoughts, implement if they'll work well.
	// So, the steps could be here:

	// 	If player will bankrupt, don't allow
	//      else,
	//         make player pay for property.
	//         set the player as the property's owner
	//         increment the player's number of properties owned
	//         change the property's image
	//
	//  Look into BoardCell.setOwner whenever.



	// if (player1.getCellClassName() == "Railroad") {
	//     Railroad r = (Railroad) player1.getCell();
	//     playerPayPlayer(r.getCost(), player1);
	// }
	// else if (player1.getCellClassName() == "PropagandaOutlet") {
	//     PropagandaOutlet pO = (PropagandaOutlet) player1.getCell();
	//     playerPayPlayer(pO.getCost(), player1);
	// }
	// else if (player1.getCellClassName() == "UtilityCell") {
	//     UtilityCell u = (UtilityCell) player1.getCell();
	//     playerPayPlayer(u.getCost(), player1);
	// }
	// player1.getCell().setOwner(player1);
	// buyProperty.setEnabled(false); // Disable button after property's bought.

    }

    /** Remove a property from this player. */
    public void removeProperty(BoardCell property) {
	//TODO: Implement this. remove the property, decrement 
	//      number of properties owned, change image.
    }

    /** Have this player roll the dice. */
    public void roll() {
	// TODO: Implement this
    }

    /** Move this player to a given position p. p refers to the cell the Player wants 
     *  to move to. For example, changePosition(3) will move any player to the 4th
     *  BoardCell on the board. */
    public void changeCell(int p, IdeopolyGUI gui) { // TODO: Better function name.
	// Only allow valid indexes.
	if (p < 0 || p > 39) {
	    System.out.println("Error! That cell is outside the bounds of valid cells!");
	}
	else {
	    currentCell = gui.boardProperties[p];
	    cellIndex = p;
	}
    }

    /** Change this Player's amount a of currency type t. */
    public void addCash(String t, int a) {
	// TODO: This results in the Player having negative cash values. That's not allowed...
	// TODO: Make sure addCash handles negative values appropriately.
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

	updateTotalMoney();
    }

    /** Give this player a get out of jail free card. */
    public void giveGOOJF() {
 	GOOJFCards++;
    }

    /** Have this player spend one of their get out of jail free cards. */
    public void spendGOOJF(IdeopolyGUI gui) {
	if (GOOJFCards <= 0)
	    gui.printStatusAndLog("Error! You can't spend a Get Out of Jail Free card if you have 0 or less.");
	else {
	    //TODO: Don't allow this when the person's not in jail.
	    GOOJFCards--;
	    inJail = 0;
	}
    }

    /** Determine if the given amount will bankrupt this Player
     *  if (s)he has to pay it.
     *  Return True if Player will be bankrupted, False otherwise. */
    public Boolean willBankrupt(int amount) {
	if (totalMoney < amount) return true;
	else return false;
    }

    /** Convert this Player's cash into bill type desiredBill.
     *  For example, if a Player needs more 5s, this will convert all the Player's
     *  cash to 5s. By default (with an invalid argument), converts to 500s. */
    public void spreadCash(int desiredBill) {
	// The amount still left to be spread out.
	int amountNotSpread = totalMoney;
	// The number of a given type of bill spread.
	int numBillsSpread;

	fiveHundreds = 0;
	hundreds     = 0;
	fifties	     = 0;
	twenties     = 0;
	tens	     = 0;
	fives	     = 0;
	ones	     = 0;

	int billValues[]   = {500, 100, 50, 20, 10, 5, 1};
	int newValues[] = {fiveHundreds, hundreds, fifties, twenties, tens, fives, ones};

	// TODO: Clean up this mess of code.
	//	if input is x, set item[0] to x, and item[x] to fiveHundreds
	//      And update the rest of the behavior accordingly.

	if (desiredBill == 1)
	    swapValues(billValues, newValues, 1, ones, 6);

	else if (desiredBill == 5)
	    swapValues(billValues, newValues, 5, fives, 5);

	else if (desiredBill == 10)
	    swapValues(billValues, newValues, 10, tens, 4);

	else if (desiredBill == 20)
	    swapValues(billValues, newValues, 20, twenties, 3);

	else if (desiredBill == 50)
	    swapValues(billValues, newValues, 50, fifties, 2);

	else if (desiredBill == 100)
	    swapValues(billValues, newValues, 100, hundreds, 1);

	for (int i = 0; i <= 6; i++) {
	    if ( (amountNotSpread / billValues[i]) != 0) {
		numBillsSpread   = amountNotSpread / billValues[i];
		newValues[i]    += numBillsSpread;
		amountNotSpread -= (billValues[i] * numBillsSpread);
	    }
	}

	fiveHundreds = newValues[0];
	hundreds     = newValues[1];
	fifties      = newValues[2];
	twenties     = newValues[3];
	tens         = newValues[4];
	fives	     = newValues[5];
	ones         = newValues[6];

	if (desiredBill == 1) {
	    fiveHundreds = newValues[6];
	    ones         = newValues[0];
	}
	else if (desiredBill == 5) {
	    fiveHundreds = newValues[5];
	    fives        = newValues[0];
	}
	else if (desiredBill == 10) {
	    fiveHundreds = newValues[4];
	    tens	 = newValues[0];
	}
	else if (desiredBill == 20) {
	    fiveHundreds = newValues[3];
	    twenties     = newValues[0];
	}
	else if (desiredBill == 50) {
	    fiveHundreds = newValues[2];
	    fifties      = newValues[0];
	}
	else if (desiredBill == 100) {
	    fiveHundreds = newValues[1];
	    hundreds     = newValues[0];
	}
    }

    // LEFTOFFHERE: Check over this. I'm not sure if it really makes sense or not. Tired.
    /** I'm not really sure what this method does, other 
     *  than simplifying some above code. */
    private void swapValues(int[] billVals, int[] newVals, int firstValue, int first2Value, int second) {
	billVals[0]      = firstValue;
	newVals[0]       = first2Value;
	billVals[second] = 500;
	newVals[second]  = fiveHundreds;
    }

    /** Bankrupt this player. */
    public void bankruptPlayer(IdeopolyGUI gui) {
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
	updateTotalMoney();
	image = null;

	// TODO: There's probably a better way of doing this whole thing.
	// TODO: Add tests and things for this. Haven't made sure it works yet.
	if      (name == "Player 1 (H)")
	    gui.playerRowLabels[0].setForeground(Color.red);
	else if (name == "Player 2 (C)")
	    gui.playerRowLabels[1].setForeground(Color.red);
	else if (name == "Player 3 (C)")
	    gui.playerRowLabels[2].setForeground(Color.red);
	else if (name == "Player 4 (C)")
	    gui.playerRowLabels[3].setForeground(Color.red);
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

	changeCell(10, gui);
	setJailStatus(3);
    }
}
