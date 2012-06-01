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
	switch (this.cardType) {
	    case 1: p.changeCell(0, gui); // "Advance to Go (Collect $200)"
		p.addCash("hundreds", 2);
		break;
            case 2: p.addCash("hundreds", 2); // "Bank error in your favor – collect $200"
		break;
	    case 3:   // "Doctor's fees – Pay $50"
    // 	    if (p.willBankrupt(50)) {
    // 		p.bankruptPlayer(); // TODO: Update this code to do more relevant stuff.
    // 	    }
    // 	    else {
    // 		p.spreadCash(50); // Shuffle money around to get 50s.
    // 		p.addCash("fifties", -1);
    // 		p.spreadCash(500); // Then shuffle back to a more reasonable amount.
    // 	    }
    // 	    break;
		break;
	    case 4: p.giveGOOJF();    // "Get Out of Jail Free – this card may be kept until needed, or sold.
		break;
	    case 5: p.putInJail(gui); // "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");
		break;
	    case 6:   // "It is your birthday - Collect $10 from each player"
		// TODO: Repeated also.
		// TODO: Subtle bug(?) in these. If a player bankrupts, the main player still 
		// gets their money. Should this be allowed? Add relevant tests.

    // 	    if      (p == player1) {
    // 		otherPlayers = new Player[] { player2, player3, player4 };
    // 	    }
    // 	    else if (p == player2) {
    // 		otherPlayers = new Player[] { player1, player3, player4};
    // 	    }
    // 	    else if (p == player3) {
    // 		otherPlayers = new Player[] { player1, player2, player4 };
    // 	    } 
    // 	    else { // p == player4, or some random other value.
    // 		// TODO: Shouldn't allow this for all other cases.
    // 		otherPlayers = new Player[] { player1, player2, player3 };
    // 	    }

    // 	    for (Player iterPlayer : otherPlayers) {
    // 		if (iterPlayer.willBankrupt(10)) {
    // 		    // TODO: More code needed to handle this? And the identical below cases?
    // 		    iterPlayer.bankruptPlayer();
    // 		}
    // 		else {
    // 		    iterPlayer.spreadCash(10);
    // 		    iterPlayer.addCash("tens", -1);
    // 		    iterPlayer.spreadCash(500);
    // 		}
    // 	    }

    // 	    p.addCash("tens", 3);
    // 	    break;
		break;
	    case 7:   // "Grand Opera Night – collect $50 from every player for opening night seats"
    // 	    Player[] extraPlayers;
    // 	    if      ( p == player1 ) {
    // 		// TODO: Better variable name, or a better way of doing this.
    // 		extraPlayers = new Player[] { player2, player3, player4 };
    // 	    }
    // 	    else if (p == player2) {
    // 		extraPlayers = new Player[] { player1, player3, player4 };
    // 	    }
    // 	    else if (p == player3) {
    // 		extraPlayers = new Player[] { player1, player2, player4 };
    // 	    }
    // 	    else { // (p == player4) and all other cases. TODO: Shouldn't allow all cases for this.
    // 		extraPlayers = new Player[] { player1, player2, player3 };
    // 	    }

    // 	    for (Player iterPlayer : extraPlayers) {
    // 		if (iterPlayer.willBankrupt(50)) {
    // 		    iterPlayer.bankruptPlayer();
    // 		}
    // 		else {
    // 		    iterPlayer.spreadCash(50);
    // 		    iterPlayer.addCash("fifties", -1);
    // 		    iterPlayer.spreadCash(500);
    // 		}
    // 	    }

    // 	    p.addCash("fifties", 3);
    // 	    break;

		break;
	    case 8: p.addCash("tens", 2);     // "Income Tax refund – collect $20"
		break;
	    case 9: p.addCash("hundreds", 1); // "Life Insurance Matures – collect $100"
		break;
	    case 10:  // "Pay Hospital Fees of $100"

    // 	    if (p.willBankrupt(100)) {
    // 		p.bankruptPlayer();
    // 	    }
    // 	    else {
    // 		p.spreadCash(100);
    // 		p.addCash("hundreds", -1);
    // 		p.spreadCash(500);
    // 	    }
    // 	    break;
		break;
	    case 11:  // "Pay School Fees of $50"
    // 	case 11:  // "Pay School Fees of $50"

    // 	    if (p.willBankrupt(50)) {
    // 		p.bankruptPlayer();
    // 	    }
    // 	    else {
    // 		p.spreadCash(50);
    // 		p.addCash("fifties", -1);
    // 		p.spreadCash(500);
    // 	    }
    // 	    break;

		break;
	    case 12: p.addCash("twenties", 1); // "Receive $25 Consultancy Fee"
		p.addCash("fives", 1);
		break;
	    case 13:  // "You are assessed for street repairs – $40 per house, $115 per hotel"
		break;
	    case 14: p.addCash("tens", 1);     // "You have won second prize in a beauty contest– collect $10"
		break;
	    case 15: p.addCash("hundreds", 1); // "You inherit $100"
		break;
	    case 16: p.addCash("fifties", 1);  // "From sale of stock you get $50"
		break;
	    case 17: p.addCash("hundreds", 1); // "Holiday Fund matures - Receive $100"
		break;

    	    default: System.out.println("Wrong Community Chest value!");
		break;
	}
	// 	// TODO: Redirect this out to a GUI status element thing.
	// 	System.out.println(card.getText());
	// }
    }
}
