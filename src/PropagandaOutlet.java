import javax.swing.*;

/** Class to represent a given propaganda outlet.
 *  Extends a generic BoardCell to also include properties 
 *  such as number of houses/hotels present, cost of rent, etc.
 *
 *  @author Daniel Neel */
public class PropagandaOutlet extends BoardCell {
    // TODO: Should these be more than just numbers, so that we can specify who owns each? Maybe number of player 1/2/3/4 houses/hotels? Nevermind, since ya have to own all the color group, and since ownedBy determines who owns the property, we can use those to detrmine who owns the houses.

    // TODO: Make these items constants, so they can't be screwed with accidentally?
    /** Price for the player to buy this property unimproved. */
    private int cost;

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
    /** How much rent costs with 1 house present. */
    private int rent1House;

    /** How much rent costs with 2 houses present. */
    private int rent2House;

    /** How much rent costs with 3 houses present. */
    private int rent3House;

    /** How much rent costs with 4 houses present. */
    private int rent4House;

    /** How much rent costs with a hotel present on this property. */
    private int rent1Hotel;

    /** The cost of a single house or hotel on this property. Since 
     *  houses and hotels always cost the same, we just need one variable. */
    private int houseOrHotelCost;

    /** Create a new propaganda outlet with a given name, image, no owner,
     *  no houses/hotels and the given rent per no house, 1/2/3/4 houses/hotels,
     *  mortgage value, cost per house/hotel, x/y coordinates.
     *  There will never be an owner when we're creating the object, so no need for
     *  a constructor that allows a specified owner. Also, on creation, the object
     *  will never have any houses or hotels. */ // TODO: Useless extra comment there?
    // TODO: Should we accept an array instead maybe?
    // TODO: Accept an array for rent values rather than separate variables?
    // TODO: camelCase variable names here.
    public PropagandaOutlet(String newName, Icon newImage, int newCost, int newInitialRent, int newRent1House, int newRent2House, int newRent3House, int newRent4House, int newRent1Hotel, int newHouseOrHotelCost, int xPos, int yPos) {
	// TODO: Better, less ambiguous variable names here.
	super(newName, newImage, xPos, yPos); // Use the BoardCell class' constructor.
	numHouses	    = 0;
	numHotels	    = 0;
	cost                = newCost;
	mortgageValue	    = newCost / 2; // Mortgage prices are half the price to buy the property.
	initialRent	    = newInitialRent;
	rent1House	    = newRent1House;
	rent2House	    = newRent2House;
	rent3House	    = newRent3House;
	rent4House	    = newRent4House;
	rent1Hotel	    = newRent1Hotel;
	houseOrHotelCost    = newHouseOrHotelCost;
    }

    /** Return the amount of rent that a Player landing here has to pay. */
    public int getRent() {
	// TODO: Make checks so ya can't buy a hotel without 4 houses.
	// TODO: Also checks so ya can't buy more than 1 hotel.
	// Unimproved property.
	if (numHouses == 0) // TODO: Not all props in group owned.
	    return initialRent;

	// TODO: else if (are owned in a group, no houses) {}
	else if (numHouses == 1)
	    return rent1House;

	else if (numHouses == 2)
	    return rent2House;

	else if (numHouses == 3)
	    return rent3House;

	else if (numHouses == 4)
	    return rent4House;

	else if (numHotels == 1)
	    return rent1Hotel;

	else // Error state.
	    return -1;
    }

    public int getCost() {
	return cost;
    }
}


    /*
* Propaganda outlet // A normal board place
** Fields
*** Number of Beck's [houses]
*** TODO Implement the house rules here about buying evenly.
When you own all the properties in a color-group you may buy houses from the Bank and erect
them on those properties. If you buy one house, you may put it on any one of those properties.
The next house you buy must be erected on one of the unimproved properties of this or any
other complete color-group you may own.
The price you must pay the Bank for each house is shown on your Title Deed card for the
property on which you erect the house. The owner still collects double rent from an opponent
who lands on the unimproved properties of his/her complete color-group. Following the above
rules, you may buy and erect at any time as many houses as your judgment and financial
standing will allow. But you must build evenly, i.e., you cannot erect more than one house on
any one property of any color-group until you have built one house on every property of that
group. You may then begin on the second row of houses, and so on, up to a limit of four houses
to a property. For example, you cannot build three houses on one property if you have only one
house on another property of that group.
As you build evenly, you must also break down evenly if you sell houses back to the Bank (see
SELLING PROPERTY).
*** Number of Fox News's [hotels]
*** Mortgage value.  // The amount the player can mortgage the property for. Should be a special value for properties the player can't own.
*** Regular_rent     // How much to pay if this property is un-impr
*** Rent_per_house   // How much to pay if a house is on this object.
*** Rent_per_hotel   // How much to pay if a hotel is on this object.
** Methods
*** onLand() // One thing: If this outlet is owned by a player, charge rent. Else, allow the player to buy, auction, etc.
*** onLand [?] // a function that's called when this cell is landed on. Effect will differ depending upon the cell (IE: different for go, properties, utilities, etc) TODO: So if its effect always differs, why would I have it in the parent class?
***** If it's a property, if noone owns it, onLand should have the effect of allowing the current player to bid on it. If they don't want it, allow other players to buy it at auction.
Which implies... TODO Implement an auction system where, for example auction() allows every player to bid on the property. Highest bidder gets it, no minimum bid.
***** After that bidding process, if someone buys it, ownership should transfer accordingly.
***** Or, if somebody does own it
****** Transfer money.
******* If the lot's unimproved:
******** Are all cards in the group owned by one player?
********* Yes
********** Transfer the list price on the card * 2 to the owner
********* No
********** Transfer the list price on the card to the owner
******* Else: (is improved)
******** Transfer the corresponding amount between players */