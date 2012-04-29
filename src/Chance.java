import java.util.Random;

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