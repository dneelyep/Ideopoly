/** Class to represent a Chance card.
 *
 *  @author Daniel Neel */
public class Chance {
    /** Number representing the type of this card. This type is used to
     *  determine the text on the card and the actions taken when this
     *  card is drawn. */
    private int cardType;

    /** The text written on this card. Used for information for the player. */
    private String text;

    /** Create a card of type t, setting its text field. */
    public Chance(int t) {
	cardType = t;

	// TODO: Replace these messages with Ideology-themed ones.

	switch (t) {
	case 1:  text = "Advance to Go (Collect $200)";
	    break;
	case 2:  text = "Advance to Illinois Ave - if you pass Go, collect $200";
	    break;
	case 3:  text = "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.";
	    break;
	case 4:  text = "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)";
	    break;
	case 5:  text = "Advance to St. Charles Place – if you pass Go, collect $200";
	    break;
	case 6:  text = "Bank pays you dividend of $50";
	    break;
	case 7:  text = "Get out of Jail Free – this card may be kept until needed, or traded/sold";
	    break;
	case 8:  text = "Go back 3 spaces";
	    break;
	case 9:  text = "Go directly to Jail – do not pass Go, do not collect $200";
	    break;
	case 10: text = "Make general repairs on all your property – for each house pay $25 – for each hotel $100";
	    break;
	case 11: text = "Pay poor tax of $15";
	    break;
	case 12: text = "Take a trip to Reading Railroad – if you pass Go, collect $200";
	    break;
	case 13: text = "Take a walk on the Boardwalk – advance token to Boardwalk";
	    break;
	case 14: text = "You have been elected chairman of the board – pay each player $50";
	    break;
	case 15: text = "Your building loan matures – collect $150";
	    break;
	case 16: text = "You have won a crossword competition - collect $100.";
	    break;

	default: System.out.println("Error! Tried to create a non-standard Chance card.");
	    break;
	}
    }

    /** Get the text field associated with this card. */
    public String getText() {
	return text;
    }

    /** Get the type of this card. */
    public int getType() {
	return cardType;
    }

    /** Have the Player p carry out actions associated with a Chance card of this 
     * type, also using the players from the IdeopolyGUI gui when necessary. */
    public void doActions(Player p, IdeopolyGUI gui) {
    	switch (cardType) {
    	case 1:  // "Advance to Go (Collect $200)"
    	    p.changePosition(3);
    	    p.addCash("hundreds", 2);
    	    break;
    	case 2:  //"Advance to Illinois Ave - if you pass Go, collect $200"
    	    // TODO: There's a general pattern to these cards. It's if position is 
    	    // >= some value, give $200 dollars. And then, depending on player, set position.
    	    // Make this type of card into a function.

    	    // If the player's on Illinois RR or after, give money.
    	    if (p.getPosition() >= 96)
    		p.addCash("hundreds", 2);

    	    p.changePosition(99);
    	    break;
    	case 3:  //"Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown."
	    if (p.getPosition() >= 28 && p.getPosition() <= 31) { // Bottom Chance
		p.changePosition(23); // move to Reading RR.
	    }
	    else if (p.getPosition() >= 88 && p.getPosition() <= 91) { // Top Chance
		p.changePosition(103); // move to B & O RR.
	    }
	    else if (p.getPosition() >= 144 && p.getPosition() <= 147) { // Right Chance
		p.changePosition(143); // move to Short Line RR.
	    }
	    else {
		System.out.println("Error! Apparently you tried to do the actions on a Chance card 3, but you weren't standing on a Chance space to begin with.");
	    }

	    // TODO: This still gets called if I get the above error message. Is not correct.
	    if (gui.boardProperties[p.getPosition() / 4].getOwner() != null ) {
		// TODO: Charge the player 10x normal rent here.
	    }
    	    break;
    	case 4:  //"Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)"
    	    // TODO: Implement this.
    	    break;
    	case 5:  //"Advance to St. Charles Place – if you pass Go, collect $200"

    	    // If the player's position is on or after Electric Company, give em $200.
    	    if (p.getPosition() >= 48)
    		p.addCash("hundreds", 2);

    	    p.changePosition(47);
    	    break;

    	case 6:  //"Bank pays you dividend of $50"
    	    p.addCash("fifties", 1);
    	    break;
    	case 7:  //"Get out of Jail Free – this card may be kept until needed, or traded/sold"
    	    p.giveGOOJF();
    	    break;
    	case 8:  //"Go back 3 spaces"
    	    p.setPosition(p.getPosition() - 12);
    	    // TODO: Then call onland function.
    	    // TODO: Or just call movePlayer() ?
	    // TODO: Should I use changePosition() instead for some reason?
    	    break;
    	case 9:  //"Go directly to Jail – do not pass Go, do not collect $200"
    	    p.putInJail(gui);
    	    break;
    	case 10: //"Make general repairs on all your property – for each house pay $25 – 
                 // for each hotel $100"
    	    // If the payment will bankrupt the Player, do x.
    	    // TODO: Haven't tested this yet, to make sure I get correct values out of parseInt().
    	    int payment = (p.getNumHouses() * 25) + (p.getNumHotels() * 100);

    	    if ( p.willBankrupt(payment) ) {
    		p.bankruptPlayer();
    	    }
    	    else {
    		//TODO: Remove cash here.
    	    }
    	    break;
    	case 11: //"Pay poor tax of $15"

    	    if (p.willBankrupt(15)) {
    		p.bankruptPlayer();
    	    }
    	    else {
    		p.spreadCash(10);
    		p.addCash("tens", -1);
    		p.spreadCash(5);
    		p.addCash("fives", -1);
    		p.spreadCash(500);
    	    }    
    	    break;
    	case 12: //"Take a trip to Reading Railroad – if you pass Go, collect $200"
    	    // If the player's position is on or after Oriental avenue, give em $200.
    	    // TODO: Test this to make sure it works.
    	    if (p.getPosition() >= 24)
    		p.addCash("hundreds", 2);

    	    p.changePosition(23);
    	    // TODO: And then onland function.
    	    break;

    	case 13: //"Take a walk on the Boardwalk – advance token to Boardwalk"
    	    p.changePosition(159);
    	    //TODO: And then call the onland function for boardwalk.
    	    break;
    	case 14: //"You have been elected chairman of the board – pay each player $50"
    	    // TODO: Make sure addCash handles negative values appropriately.
    	    Player[] morePlayers; // TODO: Again, better variable name. Or use the same name for all three cases.

    	    if (p.willBankrupt(150)) {
    		p.bankruptPlayer();
    		// TODO: Should this still give the other players 50 bucks each?
    	    }
    	    else {
    		p.spreadCash(50);
    		p.addCash("fifties", -3);

    		if      (p == gui.player1)
    		    morePlayers = new Player[] {gui.player2, gui.player3, gui.player4};
    		else if (p == gui.player2)
    		    morePlayers = new Player[] {gui.player1, gui.player3, gui.player4};
    		else if (p == gui.player3)
    		    morePlayers = new Player[] {gui.player1, gui.player2, gui.player4};
    		else // if (p == gui.player4) TODO: Shouldn't allow this for all cases.
    		    morePlayers = new Player[] {gui.player1, gui.player2, gui.player3};

    		for (Player iterPlayer : morePlayers) {
    		    iterPlayer.addCash("fifties", 1);
    		}

    		p.spreadCash(500);
    	    }
    	    break;
    	case 15: //"Your building loan matures – collect $150"
    	    p.addCash("hundreds", 1);
    	    p.addCash("fifties", 1);
    	    break;
    	case 16: //"You have won a crossword competition - collect $100 "
    	    p.addCash("hundreds", 1);
    	    break;

    	default: System.out.println("Wrong Chance value!");
    	    break;
    	}
    }

    /* Chance values:
       Advance to Go (Collect $200)
       Advance to Illinois Ave - if you pass Go, collect $200
       Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.
       Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)
       Advance to St. Charles Place – if you pass Go, collect $200
       Bank pays you dividend of $50
       Get out of Jail Free – this card may be kept until needed, or traded/sold
       Go back 3 spaces
       Go directly to Jail – do not pass Go, do not collect $200
       Make general repairs on all your property – for each house pay $25 – for each hotel $100
       Pay poor tax of $15
       Take a trip to Reading Railroad – if you pass Go, collect $200
       Take a walk on the Boardwalk – advance token to Boardwalk
       You have been elected chairman of the board – pay each player $50
       Your building loan matures – collect $150
       You have won a crossword competition - collect $100 */
}