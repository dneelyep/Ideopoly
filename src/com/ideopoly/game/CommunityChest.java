package com.ideopoly.game;

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
    public void doActions(Player p, GameBoard board) { // TODO: Rename this to drawCard() or something better.
        switch (this.cardType) {
            case 1: p.setCell(board.boardProperties.get(0)); // "Advance to Go (Collect $200)"
                p.addCash(CASH_TYPES.hundreds, 2);
                break;
            case 2: p.addCash(CASH_TYPES.hundreds, 2); // "Bank error in your favor – collect $200"
                break;
            case 3:   // "Doctor's fees – Pay $50"
                p.payBank(50, board);
                break;
            case 4: p.giveGOOJF();    // "Get Out of Jail Free – this card may be kept until needed, or sold.
                break;
            case 5: p.putInJail(board); // "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");
                break;
            case 6:   // "It is your birthday - Collect $10 from each player"
                // TODO: This code is basically repeated in case 7. Make a method?
                // TODO: Bug in these. If a player bankrupts, the main player still
                // gets their money. Should this be allowed? Add relevant tests.

                /* The number of Players being taken from that are not bankrupt. If a player will
             *  become bankrupt, Player p gets $10 less. */
                int numNotBankrupt = 3;

                for (Player player : board.players) {
                    if (player != p) { // Don't collect money from p.
                        if (player.willBankrupt(10)) {
                            player.bankruptPlayer(board);
                            numNotBankrupt--;
                        }
                        else {
                            player.spreadCash(CASH_TYPES.tens);
                            player.addCash(CASH_TYPES.tens, -1);
                            player.spreadCash(CASH_TYPES.fiveHundreds);
                        }
                    }
                }

                p.addCash(CASH_TYPES.tens, numNotBankrupt);
                break;
            case 7:   // "Grand Opera Night – collect $50 from every player for opening night seats"
                numNotBankrupt = 3;

                for (Player player : board.players) {
                    if (player != p) { // Don't collect money from p.
                        if (player.willBankrupt(50)) {
                            player.bankruptPlayer(board);
                            numNotBankrupt--;
                        }
                        else {
                            player.spreadCash(CASH_TYPES.fifties);
                            player.addCash(CASH_TYPES.fifties, -1);
                            player.spreadCash(CASH_TYPES.fiveHundreds);
                        }
                    }
                }

                p.addCash(CASH_TYPES.fifties, numNotBankrupt);
                break;
            case 8: p.addCash(CASH_TYPES.tens, 2);     // "Income Tax refund – collect $20"
                break;
            case 9: p.addCash(CASH_TYPES.hundreds, 1); // "Life Insurance Matures – collect $100"
                break;
            case 10:  // "Pay Hospital Fees of $100"
                p.payBank(100, board);
                break;
            case 11:  // "Pay School Fees of $50"
                p.payBank(50, board);
                break;
            case 12: p.addCash(CASH_TYPES.twenties, 1); // "Receive $25 Consultancy Fee"
                p.addCash(CASH_TYPES.fives, 1);
                break;
            case 13:  // "You are assessed for street repairs – $40 per house, $115 per hotel"
                int chargeAmount = (p.getNumHouses() * 40) + (p.getNumHotels() * 115);
                p.payBank(chargeAmount, board);
                break;
            case 14: p.addCash(CASH_TYPES.tens, 1);     // "You have won second prize in a beauty contest– collect $10"
                break;
            case 15: p.addCash(CASH_TYPES.hundreds, 1); // "You inherit $100"
                break;
            case 16: p.addCash(CASH_TYPES.fifties, 1);  // "From sale of stock you get $50"
                break;
            case 17: p.addCash(CASH_TYPES.hundreds, 1); // "Holiday Fund matures - Receive $100"
                break;

            default: System.out.println("Wrong Community Chest value!");
                break;
        }
        board.printStatusAndLog(p.getName() + ": " + text);
    }
}
