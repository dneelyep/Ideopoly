package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

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

    // TODO Initialize bills here.
    // TODO Thoroughly review uses of the bill variables to make improvements.
    /** A Map of the amounts of all bills belonging to this Player. */
    private Map<CASH_TYPES, Integer> bills = new LinkedHashMap<>();

    // TODO I should not have this field. It should be calculated dynamically.
    /** Total amount of money this player has. */
    private int totalMoney = 0;

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

    /** Color associated with this Player. Player1 = green, 2 = yellow, 
     *  3 = orange, 4 = blue. */
    private Color color;

    // TODO: Make add/remove functions for ownedOutlets. Use something other than an array for ease?

    /** Actions to take when a player object is initially created. Players
     *  are only created at the start of the game. Different players start
     *  at different positions on the board (Same cell, different walking space.) */
    public Player(int playerNumber, Color c, GameBoard board) {

        inJail = 0; // TODO: This needed? Couldn't I just use board position?

        // Initial cash values.
        bills.put(CASH_TYPES.ones, 5);
        bills.put(CASH_TYPES.fives, 5);
        bills.put(CASH_TYPES.tens, 5);
        bills.put(CASH_TYPES.twenties, 6);
        bills.put(CASH_TYPES.fifties, 2);
        bills.put(CASH_TYPES.hundreds, 2);
        bills.put(CASH_TYPES.fiveHundreds, 2);
        updateTotalMoney();

        totalPropertiesOwned = 0;
        totalHousesOwned     = 0;
        totalHotelsOwned     = 0;

        this.color = c;

        // TODO: Create an empty array? Or is it already created?
        //	ownedOutlets[] = ;

        GOOJFCards = 0;

        currentCell = board.boardProperties[0];
        cellIndex   = 0;
        switch (playerNumber) {
            case 1: name = "Player 1 (H)";
                image = new ImageIcon("src/com/ideopoly/game/images/p1Present.jpg");
                break;
            case 2: name = "Player 2 (C)";
                image = new ImageIcon("src/com/ideopoly/game/images/p2Present.jpg");
                break;
            case 3: name = "Player 3 (C)";
                image = new ImageIcon("src/com/ideopoly/game/images/p3Present.jpg");
                break;
            case 4: name = "Player 4 (C)";
                image = new ImageIcon("src/com/ideopoly/game/images/p4Present.jpg");
                break;

            default: System.out.println("Error: tried to create a non-standard player.");
        }
    }

    /** Get the amount of bills of the given type for this player. */
    public int getCash(CASH_TYPES cashType) {
        // TODO Here can't I just use return cashType?
        return (cashType.getDescription().equals("total") ? totalMoney : bills.get(cashType));
    }

    /** Return the image associated with this player. */
    public Icon getImage() {
        return image;
    }

    /** Get this player's current position. */
    public BoardCell getCell() {
        return currentCell;
    }

    /** Set this Player's currency of type cashType to a given amount. */
    // TODO: With this, is addCash() now obsolete? For example, 
    //       I could setCash("x", p.getCash("x") + 5)
    public void setCash(CASH_TYPES cashType, int amount) {
        // TODO: Make sure this handles negative values appropriately.
        // TODO An error here. CASH_TYPES should not include "total".
        if (bills.containsKey(cashType))
            bills.put(cashType, amount);

        updateTotalMoney();
    }

    // TODO: Get rid of the totalMoney field, since I can just use this. When I want the total,
    //       all I should have to do is call getCash("total"), and the ones + (fives * 5), etc. should
    //       be returned.
    /** Update this Player's totalMoney value. */
    private void updateTotalMoney() {
        int total = 0;
        for (CASH_TYPES cashType : CASH_TYPES.values()) {
            if (cashType != CASH_TYPES.total)
                total += (bills.get(cashType) * cashType.asInt());
        }
        totalMoney = total;
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

    /** Set the number of properties owned by this Player to n. */
    public void setNumOwnedProperties(int n) {
        totalPropertiesOwned = n;
    }

    /** Get the index of the BoardCell in boardProperties[] this Player 
     *  is standing on. */
    public int getIndex() {
        return cellIndex;
    }

    /** Have this player roll the dice. */
    public void roll() {
        // TODO: Implement this
    }

    /** Move this player to a given position p. p refers to the cell the Player wants 
     *  to move to. For example, changePosition(3) will move any player to the 4th
     *  BoardCell on the board. */
    public void setCell(int p, GameBoard board) { // TODO: Better function name.
        // Only allow valid indexes.
        if (p < 0 || p > 39)
            System.out.println("Error! That cell is outside the bounds of valid cells!");
        else {
            currentCell = board.boardProperties[p];
            cellIndex = p;
        }
    }

    /** Change this Player's amount of currency type cashType. */
    public void addCash(CASH_TYPES cashType, int amount) {
        // TODO: This results in the Player having negative cash values. That's not allowed...
        // TODO: Make sure addCash handles negative values appropriately.
        if (bills.containsKey(cashType)) {
            bills.put(cashType, bills.get(cashType) + amount);
        }
        else {
            System.out.println("Invalid currency amount.");
        }

        updateTotalMoney();
    }

    /** Give this player a get out of jail free card. */
    public void giveGOOJF() {
        GOOJFCards++;
    }

    /** Have this player spend one of their get out of jail free cards. */
    public void spendGOOJF(GameBoard board) {
        if (GOOJFCards <= 0)
            board.printStatusAndLog("Error! You can't spend a Get Out of Jail Free card if you have 0 or less.");
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

        for (CASH_TYPES key : bills.keySet()) {
            bills.put(key, 0);
        }

        int billValues[]   = {500, 100, 50, 20, 10, 5, 1};
        // TODO Can I replace this with a method of bills? Such as toArray or something?
        int newValues[] = {bills.get(CASH_TYPES.fiveHundreds), bills.get(CASH_TYPES.hundreds), bills.get(CASH_TYPES.fifties),
                bills.get(CASH_TYPES.twenties), bills.get(CASH_TYPES.tens), bills.get(CASH_TYPES.fives), bills.get(CASH_TYPES.ones)};

        // TODO: Clean up this mess of code.
        //	if input is x, set item[0] to x, and item[x] to fiveHundreds
        //      And update the rest of the behavior accordingly.

        if (desiredBill == 1)
            swapValues(billValues, newValues, desiredBill, bills.get(CASH_TYPES.ones), 6);

        else if (desiredBill == 5)
            swapValues(billValues, newValues, desiredBill, bills.get(CASH_TYPES.fives), 5);

        else if (desiredBill == 10)
            swapValues(billValues, newValues, desiredBill, bills.get(CASH_TYPES.tens), 4);

        else if (desiredBill == 20)
            swapValues(billValues, newValues, desiredBill, bills.get(CASH_TYPES.twenties), 3);

        else if (desiredBill == 50)
            swapValues(billValues, newValues, desiredBill, bills.get(CASH_TYPES.fifties), 2);

        else if (desiredBill == 100)
            swapValues(billValues, newValues, desiredBill, bills.get(CASH_TYPES.hundreds), 1);

        for (int i = 0; i <= 6; i++) {
            if ( (amountNotSpread / billValues[i]) != 0) {
                numBillsSpread   = amountNotSpread / billValues[i];
                newValues[i]    += numBillsSpread;
                amountNotSpread -= (billValues[i] * numBillsSpread);
            }
        }

        bills.put(CASH_TYPES.fiveHundreds, newValues[0]);
        bills.put(CASH_TYPES.hundreds, newValues[1]);
        bills.put(CASH_TYPES.fifties, newValues[2]);
        bills.put(CASH_TYPES.twenties, newValues[3]);
        bills.put(CASH_TYPES.tens, newValues[4]);
        bills.put(CASH_TYPES.fives, newValues[5]);
        bills.put(CASH_TYPES.ones, newValues[6]);

        if (desiredBill == 1) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[6]);
            bills.put(CASH_TYPES.ones, newValues[0]);
        }
        else if (desiredBill == 5) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[5]);
            bills.put(CASH_TYPES.fives, newValues[0]);
        }
        else if (desiredBill == 10) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[4]);
            bills.put(CASH_TYPES.tens, newValues[0]);
        }
        else if (desiredBill == 20) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[3]);
            bills.put(CASH_TYPES.twenties, newValues[0]);
        }
        else if (desiredBill == 50) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[2]);
            bills.put(CASH_TYPES.fifties, newValues[0]);
        }
        else if (desiredBill == 100) {
            bills.put(CASH_TYPES.fiveHundreds, newValues[1]);
            bills.put(CASH_TYPES.hundreds, newValues[0]);
        }
    }

    // LEFTOFFHERE: Check over this. I'm not sure if it really makes sense or not. Tired.
    /** I'm not really sure what this method does, other 
     *  than simplifying some above code. */
    private void swapValues(int[] billVals, int[] newVals, int firstValue, int first2Value, int second) {
        billVals[0]      = firstValue;
        newVals[0]       = first2Value;
        billVals[second] = 500;
        newVals[second]  = bills.get(CASH_TYPES.fiveHundreds);
    }

    /** Bankrupt this player. */
    public void bankruptPlayer(GameBoard board) {
        // TODO: Do more than just set cash values. The player can still
        // be considered alive, given money, etc. in this state.
        // TODO: Set the player's text to red when this happens maybe also?
        // TODO: Maybe give the player an isBankrupt field, that can be used elsewhere.
        for (CASH_TYPES key : CASH_TYPES.values()) {
            bills.put(key, new Integer(0));
        }
        updateTotalMoney();
        image = null;

        // TODO: There's probably a better way of doing this whole thing.
        // TODO: Add tests and things for this. Haven't made sure it works yet.
        // TODO This should be trivially clean-uppable.
        switch (name) {
            case "Player 1 (H)":
                board.playerRowLabels.get(0).setForeground(Color.red); break;
            case "Player 2 (C)":
                board.playerRowLabels.get(1).setForeground(Color.red); break;
            case "Player 3 (C)":
                board.playerRowLabels.get(2).setForeground(Color.red); break;
            case "Player 4 (C)":
                board.playerRowLabels.get(3).setForeground(Color.red); break;
        }
    }

    /** Put the given Player p in jail, and enable the "Use get 
     *  out of jail free card" button if the Player is the human player. */
    public void putInJail(GameBoard board) {
        // TODO: Make a visual indicator for when a person's in jail.
        // IE: put a little colored square on their portrait that indicates the week they're in.
        // Or change the color of the text by their name.

        // Allow the main player to use their cards.
        if (name.equals("Player 1 (H)") && GOOJFCards > 0) {
            board.useGOOJFCard.setEnabled(true);
        }

        setCell(10, board);
        setJailStatus(3);
    }

    /** Have this Player pay the bank amount dollars.
     *  The Player will bankrupt if charged an amount higher than
     *  his/her totalMoney value. */
    public void payBank(int amount, GameBoard board) {
        // Player will be bankrupt.
        if (this.willBankrupt(amount))
            this.bankruptPlayer(board);
            // Amount is ok.
        else {
            board.getCashDistribution(amount);

            // TODO: Make this array shared between the two methods somehow?
            // TODO Get rid of cashValues altogether.
            // LEFTOFFHERE Making progress using my enum type. Need to use it to replace these and other arrays throughout the program.
            // TODO spreadCash should take in an enum, rather than an int, again.
            int[] billInt = {1, 5, 10, 20, 50, 100, 500};
            for (int i = 0; i < billInt.length; i++) {
                this.spreadCash(billInt[i]);
                this.addCash(CASH_TYPES.getTypeFromDescription(board.cashValues.get(i)), - board.paymentAmounts[i]);
            }
        }
    }

    // TODO Disassociate this enum from the Player class, since it's not
    // particularly tied to Players. Put it in its own file.
    /** Have this Player pay Player p amount dollars. This Player
     *  will bankrupt if amount is larger than this Player's totalMoney
     *  value. */
    // TODO: Have any tests transferred over to these new methods.
    public void payPlayer(Player p, int amount, GameBoard board) {
        if (this.willBankrupt(amount))
            this.bankruptPlayer(board);
        else {
            // First, get a distribution of what bills to pay.
            board.getCashDistribution(amount);

            // Then, for each bill, transfer the correct amount from this Player to p.
            int[] billInt = {1, 5, 10, 20, 50, 100, 500}; // TODO: Better array name here.
            for (int i = 0; i < billInt.length; i++) {
                this.spreadCash(billInt[i]);
                this.addCash(CASH_TYPES.getTypeFromDescription(board.cashValues.get(i)), - board.paymentAmounts[i]);
                p.addCash(CASH_TYPES.getTypeFromDescription(board.cashValues.get(i)), board.paymentAmounts[i]);
            }

            // And set cash back to sensible values.
            p.spreadCash(500);
        }
    }
}