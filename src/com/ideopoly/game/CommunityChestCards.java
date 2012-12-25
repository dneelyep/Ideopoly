package com.ideopoly.game;

/** An enumeration of all valid Community Chest card types.
 *
 * @author Daniel Neel */

public enum CommunityChestCards {
    // TODO: Just make Chance and CommChest two types of a single class? They're the same
    // thing with different text values (and hence somewhat different behaviors)..
    // They would each derive from a CommChestorChanceCard class, and have individual specific
    // values/behaviors.
    one("Advance to Go (Collect $200)", 1),
    two("Bank error in your favor – collect $200", 2),
    three("Doctor's fees – Pay $50", 3),
    four("Get Out of Jail Free – this card may be kept until needed, or sold", 4),
    five("Go to Jail – go directly to jail – Do not pass Go, do not collect $200", 5),
    six("It is your birthday - Collect $10 from each player", 6),
    seven("Grand Opera Night – collect $50 from every player for opening night seats", 7),
    eight("Income Tax refund – collect $20", 8),
    nine("Life Insurance Matures – collect $100", 9),
    ten("Pay Hospital Fees of $100", 10),
    eleven("Pay School Fees of $50", 11),
    twelve("Receive $25 Consultancy Fee", 12),
    thirteen("You are assessed for street repairs – $40 per house, $115 per hotel", 13),
    fourteen("You have won second prize in a beauty contest– collect $10", 14),
    fifteen("You inherit $100", 15),
    sixteen("From sale of stock you get $50", 16),
    seventeen("Holiday Fund matures - Receive $100", 17);

    /** A textual description of the content of this CommunityChestCard. */
    private final String text;

    /** A unique number that represents the type of CommunityChestCard. */
    private final int cardNumber;

    CommunityChestCards(String text, int cardNumber) {
        this.text = text;
        this.cardNumber = cardNumber;
    }

    /** Get the textual description of this CommunityChestCard. */
    public String getText() {
        return text;
    }

    /** Get a numerical representation of this CommunityChestCard. */
    public int getCardNumber() {
        return cardNumber;
    }

    /** Have the given Player p draw a ChanceCard of this type and do the relevant actions. */
    // TODO: Rename this to drawCard() or something better.
    public void doActions(Player player, GameBoard board) {
        switch (this) {
            case one: player.setCell(board.boardProperties.get(0)); // "Advance to Go (Collect $200)"
                player.setCash(CASH_TYPES.hundreds, player.getCash(CASH_TYPES.hundreds) + 2);
                break;
            case two: player.setCash(CASH_TYPES.hundreds, player.getCash(CASH_TYPES.hundreds) + 2);
                break;
            case three:   // "Doctor's fees – Pay $50"
                player.makePayment(50, board);
                break;
            case four: player.giveGOOJF();    // "Get Out of Jail Free – this card may be kept until needed, or sold.
                break;
            case five: player.putInJail(board); // "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");
                break;
            case six:   // "It is your birthday - Collect $10 from each player"
                // TODO: This code is basically repeated in case 7. Make a method?
                // TODO: Bug in these. If a player bankrupts, the main player still
                // gets their money. Should this be allowed? Add relevant tests.

                /* The number of Players being taken from that are not bankrupt. If a player will
                 *  become bankrupt, Player p gets $10 less. */
                int numNotBankrupt = 3;

                for (Player tmpPlayer : board.playerQueue) {
                    if (tmpPlayer != player) { // Don't collect money from p.
                        if (tmpPlayer.willBankrupt(10)) {
                            tmpPlayer.bankruptPlayer(board);
                            numNotBankrupt--;
                        }
                        else {
                            tmpPlayer.spreadCash(CASH_TYPES.tens);
                            tmpPlayer.setCash(CASH_TYPES.tens, tmpPlayer.getCash(CASH_TYPES.tens) - 1);
                            tmpPlayer.spreadCash(CASH_TYPES.fiveHundreds);
                        }
                    }
                }

                player.setCash(CASH_TYPES.tens, player.getCash(CASH_TYPES.tens) + numNotBankrupt);
                break;
            case seven:   // "Grand Opera Night – collect $50 from every player for opening night seats"
                numNotBankrupt = 3;

                for (Player tmpPlayer : board.playerQueue) {
                    if (tmpPlayer != player) { // Don't collect money from p.
                        if (tmpPlayer.willBankrupt(50)) {
                            tmpPlayer.bankruptPlayer(board);
                            numNotBankrupt--;
                        }
                        else {
                            tmpPlayer.spreadCash(CASH_TYPES.fifties);
                            tmpPlayer.setCash(CASH_TYPES.fifties, tmpPlayer.getCash(CASH_TYPES.fifties) - 1);
                            tmpPlayer.spreadCash(CASH_TYPES.fiveHundreds);
                        }
                    }
                }

                player.setCash(CASH_TYPES.fifties, player.getCash(CASH_TYPES.fifties) + numNotBankrupt);
                break;
            case eight: player.setCash(CASH_TYPES.tens, player.getCash(CASH_TYPES.tens) + 2); // "Income Tax refund – collect $20"
                break;
            case nine: player.setCash(CASH_TYPES.hundreds, player.getCash(CASH_TYPES.hundreds) + 1); // "Life Insurance Matures – collect $100"
                break;
            case ten:  // "Pay Hospital Fees of $100"
                player.makePayment(100, board);
                break;
            case eleven:  // "Pay School Fees of $50"
                player.makePayment(50, board);
                break;
            case twelve: player.setCash(CASH_TYPES.twenties, player.getCash(CASH_TYPES.twenties) + 1); // "Receive $25 Consultancy Fee"
                player.setCash(CASH_TYPES.fives, player.getCash(CASH_TYPES.fives) + 1);
                break;
            case thirteen:  // "You are assessed for street repairs – $40 per house, $115 per hotel"
                int chargeAmount = (player.getNumHouses() * 40) + (player.getNumHotels() * 115);
                player.makePayment(chargeAmount, board);
                break;
            case fourteen: player.setCash(CASH_TYPES.tens, player.getCash(CASH_TYPES.tens) + 1); // "You have won second prize in a beauty contest– collect $10"
                break;
            case fifteen: player.setCash(CASH_TYPES.hundreds, player.getCash(CASH_TYPES.hundreds) + 1); // "You inherit $100"
                break;
            case sixteen: player.setCash(CASH_TYPES.fifties, player.getCash(CASH_TYPES.fifties) + 1); // "From sale of stock you get $50"
                break;
            case seventeen: player.setCash(CASH_TYPES.hundreds, player.getCash(CASH_TYPES.hundreds) + 1); // "Holiday Fund matures - Receive $100"
                break;
        }
        board.printStatusAndLog(player.getName() + ": " + text);
    }
}