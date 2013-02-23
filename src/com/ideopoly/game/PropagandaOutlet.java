package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

/** Class to represent a given propaganda outlet.
 *  Extends a generic BoardCell to also include properties 
 *  such as number of houses/hotels present, cost of rent, etc.
 *
 *  @author Daniel Neel */
public class PropagandaOutlet extends BoardCell implements Ownable {
    // TODO: Should these be more than just numbers, so that we can specify who owns each? Maybe number of player 1/2/3/4 houses/hotels? Nevermind, since ya have to own all the color group, and since ownedBy determines who owns the property, we can use those to detrmine who owns the houses.

    // TODO: Make these items constants, so they can't be screwed with accidentally?
    /** Price for the player to buy this property unimproved. */
    private final int COST;

    // TODO: Make houses/hotels Becks/Fox News's?
    /** Number of houses currently on this property. */
    private int numHouses;

    /** Number of hotels currently on this property. */
    private int numHotels;

    /** How much money a player can mortgage this property for. */
    private int mortgageValue;

    /** How much rent costs when a player owns just this property with no houses
     *  or hotels, and when they don't own all items in the color group. */
    private int initialRent;

    // TODO: Is there a formula that can calculate rent per house/hotel?
    /** A map of house numbers to the rent value. For example, the rent for this PropagandaOutlet
     * might be $x with 2 houses present. */
    // TODO Also include Hotel costs in this map? And make it a map from strings or an enum to integer.
     private Map<Integer, Integer> houseRentValues = new LinkedHashMap<>(4);

    /** How much rent costs with a hotel present on this property. */
    private int rent1Hotel;

    /** The cost of a single house or hotel on this property. Since 
     *  houses and hotels always cost the same, we just need one variable. */
    private int houseOrHotelCost;

    /** Status of this PropagandaOutlet's ownership. If true, a Player owns the property. */
    private boolean owned = false;

    /** Create a new propaganda outlet with a given name, image, no owner,
     *  no houses/hotels and the given rent per no house, 1/2/3/4 houses/hotels,
     *  mortgage value, cost per house/hotel, x/y coordinates.
     *  There will never be an owner when we're creating the object, so no need for
     *  a constructor that allows a specified owner. Also, on creation, the object
     *  will never have any houses or hotels. */ // TODO: Useless extra comment there?
    // TODO: Should we accept an array instead maybe?
    // TODO: Accept an array for rent values rather than separate variables?
    // TODO: camelCase variable names here.
    public PropagandaOutlet(String newName, String imagePath, int newCost, int newInitialRent, int newRent1House, int newRent2House, int newRent3House, int newRent4House, int newRent1Hotel, int newHouseOrHotelCost, Point coordinates, final GameBoard board, Color color) {
        // TODO: Better, less ambiguous variable names here.
        // TODO Use an ImageIcon for an argument rather than a String - cleaner. Do this for all relevant classes.
        super(newName, new ImageIcon("res/images/" + imagePath), coordinates, color, board);
        numHouses	    = 0;
        numHotels	    = 0;
        COST            = newCost;
        mortgageValue	= newCost / 2; // Mortgage prices are half the price to buy the property.

        initialRent	     = newInitialRent;
        houseRentValues.put(1, newRent1House);
        houseRentValues.put(2, newRent2House);
        houseRentValues.put(3, newRent3House);
        houseRentValues.put(4, newRent4House);
        rent1Hotel	     = newRent1Hotel;
        houseOrHotelCost = newHouseOrHotelCost;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (board.getFocusedCell() == null) {
                    board.getBuyHouse().setEnabled(true);
                    board.getBuyHotel().setEnabled(true);
                    board.getSellProperty().setEnabled(true);
                    board.getMortgageProperty().setEnabled(true);
                    board.getCancelButton().setEnabled(true);
                    board.setFocusedCell(PropagandaOutlet.this);
//                ((ImageIcon) ((JLabel) getComponent(0)).getIcon()).;

                    ((JLabel) getComponent(0)).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                }
            }
        });
    }

    /** Return the amount of rent that a Player landing here has to pay. */
    @Override
    public int getRent() {
        // TODO: Make checks so ya can't buy a hotel without 4 houses.
        // TODO: Also checks so ya can't buy more than 1 hotel.
        // Unimproved property.
        if (numHouses == 0) // TODO: Not all props in group owned.
            return initialRent;

        // TODO: else if (are owned in a group, no houses) {}
        else if (numHouses == 1)
            return houseRentValues.get(1);

        else if (numHouses == 2)
            return houseRentValues.get(2);

        else if (numHouses == 3)
            return houseRentValues.get(3);

        else if (numHouses == 4)
            return houseRentValues.get(4);

        else if (numHotels == 1)
            return rent1Hotel;

        else // Error state.
            return -1;
    }

    public Map<Integer, Integer> getHouseRentValues() {
        return houseRentValues;
    }

    /** Return whether or not this UtilityCell is 
     *  currently owned by a Player. */
    @Override
    public boolean isOwned() {
        return owned;
    }

    /** Returns the cost for a Player to buy this 
     *  property, unimproved. */
    @Override
    public int getCost() {
        return COST;
    }

    // TODO: Javadocs for these.
    public int getMortgage() {
        return mortgageValue;
    }

    public int getInitialRent() {
        return initialRent;
    }

    public int getRent1Hotel() {
        return rent1Hotel;
    }

    public int getHouseOrHotelCost() {
        return houseOrHotelCost;
    }
}