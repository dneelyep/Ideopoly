/** Represents a card in the Community Chest stack.
 *
 *  @author Daniel Neel */
// TODO: Just make Chance and CommChest two types of a single class? They're the same 
// thing with different text values (and hence somewhat different behaviors)..
// They would each derive from a CommChestorChanceCard class, and have individual specific
// values/behaviors.
public class CommunityChest {
    /** Number representing the type of this card. This type is used to
     *  determine the text on the card and the actions taken when this
     *  card is drawn. */
    private int cardType;

    /** The text wrizv/\tten on this card. Used for information for the player. */
    private String text;

    /** Create the card, using its type t to set its text field. */
    public CommunityChest(int t) {
	cardType = t;

	switch (t) {
	case 1:  text = "Advance to Go (Collect $200)";
	    break;
	case 2:  text = "Bank error in your favor – collect $200";
	    break;
	case 3:  text = "Doctor's fees – Pay $50";
	    break;
	case 4:  text = "Get Out of Jail Free – this card may be kept until needed, or sold";
	    break;
	case 5:  text = "Go to Jail – go directly to jail – Do not pass Go, do not collect $200";
	    break;
	case 6:  text = "It is your birthday - Collect $10 from each player";
	    break;
	case 7:  text = "Grand Opera Night – collect $50 from every player for opening night seats";
	    break;
	case 8:  text = "Income Tax refund – collect $20";
	    break;
	case 9:  text = "Life Insurance Matures – collect $100";
	    break;
	case 10: text = "Pay Hospital Fees of $100";
	    break;
	case 11: text = "Pay School Fees of $50";
	    break;
	case 12: text = "Receive $25 Consultancy Fee";
	    break;
	case 13: text = "You are assessed for street repairs – $40 per house, $115 per hotel";
	    break;
	case 14: text = "You have won second prize in a beauty contest– collect $10";
	    break;
	case 15: text = "You inherit $100";
	    break;
	case 16: text = "From sale of stock you get $50";
	    break;
	case 17: text = "Holiday Fund matures - Receive $100";
	    break;

	default: System.out.println("Error! Tried to create a non-standard Community Chest card.");
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

    /** Have the Player p carry out actions associated with 
     *  a Community Chest card of this type. */
    public void doActions(Player p, IdeopolyGUI gui) { // TODO: Rename this to drawCard() or something better.

}
