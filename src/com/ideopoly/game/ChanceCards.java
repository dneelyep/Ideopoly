package com.ideopoly.game;

import java.util.Random;

/** An enumeration of all valid Chance card types.
 *
 * @author Daniel Neel */
public enum ChanceCards {
    // TODO: Replace the textual descriptions  with Ideology-themed ones. Do this on Commchest as well.
    one("Advance to Go (Collect $200)", 1),
    two("Advance to Illinois Ave - if you pass Go, collect $200", 2),
    three("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", 3),
    four("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)", 4),
    five("Advance to St. Charles Place – if you pass Go, collect $200", 5),
    six("Bank pays you dividend of $50", 6),
    seven("Get out of Jail Free – this card may be kept until needed, or traded/sold", 7),
    eight("Go back 3 spaces", 8),
    nine("Go directly to Jail – do not pass Go, do not collect $200", 9),
    ten("Make general repairs on all your property – for each house pay $25 – for each hotel $100", 10),
    eleven("Pay poor tax of $15", 11),
    twelve("Take a trip to Reading Railroad – if you pass Go, collect $200", 12),
    thirteen("Take a walk on the Boardwalk – advance token to Boardwalk", 13),
    fourteen("You have been elected chairman of the board – pay each player $50", 14),
    fifteen("Your building loan matures – collect $150", 15),
    sixteen("You have won a crossword competition - collect $100.", 16);

    /** A textual description of the content of this ChanceCard. */
    private final String text;

    /** A unique number that represents the type of ChanceCard. */
    private final int cardNumber;

    ChanceCards(String text, int cardNumber) {
        this.text = text;
        this.cardNumber = cardNumber;
    }

    /** Get the textual description of this ChanceCard. */
    public String getText() {
        return text;
    }

    /** Get a numerical representation of this ChanceCard. */
    public int getCardNumber() {
        return cardNumber;
    }

    /** Have the given Player draw a ChanceCard of this type and do the relevant actions. */
    // TODO: Rename this to drawCard() or something better.
    public void doActions(Player p, GameBoard board) {
        switch (this) {
            case one:  // "Advance to Go (Collect $200)"
                p.setCell(board.boardProperties.get(0));
                p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 2);
                break;
            case two:  // "Advance to Illinois Ave - if you pass Go, collect $200"
                // TODO: There's a general pattern to these cards. It's if position is
                // >= some value, give $200 dollars. And then, depending on player, set position.
                // Make this type of card into a function.

                // If the player's on B & O RR or after, give money.
                if (board.boardProperties.indexOf(p.getCell()) >= 24)
                    p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 2);

                p.setCell(board.boardProperties.get(24));
                break;
            case three:  // "Advance token to nearest Utility. If unowned, you may buy it from the
                //  Bank. If owned, throw dice and pay owner a total ten times the amount thrown."
                // TODO: I could also make use of p.getCell() instead. Could simplify things maybe.
                if (p.getCell() == board.boardProperties.get(7)) { // Bottom Chance
                    p.setCell(board.boardProperties.get(12)); // move to Electric Co.

                    // TODO: Would be good if I could remove the duplication here.
                    if (board.boardProperties.get(12).getOwner() != null) {
                        // TODO: Add handling for bankruptcy here. Or build that into playerPayPlayer?
                        Random rollGenerator = new Random();
                        int roll = rollGenerator.nextInt(6) + 1;
                        p.payPlayer(board.boardProperties.get(12).getOwner(), roll * 10, board);
                    }
                }
                else if (p.getCell() == board.boardProperties.get(22) || p.getCell() == board.boardProperties.get(36)) { // Top or Right Chance
                    p.setCell(board.boardProperties.get(28)); // move to Water Works.

                    if (board.boardProperties.get(28).getOwner() != null) {
                        Random rollGenerator = new Random();
                        int roll = rollGenerator.nextInt(6) + 1;
                        p.payPlayer(board.boardProperties.get(28).getOwner(), roll * 10, board);
                    }
                }
                else {
                    System.out.println("Error! Apparently you tried to do actions on a Chance card 3, but you weren't standing on a Chance space to begin with.");
                }
                break;
            case four:  // "Advance token to the nearest Railroad and pay owner twice the
                // rental to which he/she is otherwise entitled. If Railroad is unowned,
                // you may buy it from the Bank. (There are two of these.)"
                // TODO: Implement this.
                if (p.getCell() == board.boardProperties.get(7)) {
                    p.setCell(board.boardProperties.get(5));
                }
                else if (p.getCell() == board.boardProperties.get(22)) {
                    p.setCell(board.boardProperties.get(25));
                }
                else if (p.getCell() == board.boardProperties.get(36)) {
                    p.setCell(board.boardProperties.get(35));
                }
                else
                    System.out.println("Error! Apparently you tried to do actions on a Chance card 3, but you weren't standing on a Chance space to begin with.");
                break;
            case five:  // "Advance to St. Charles Place – if you pass Go, collect $200"

                // If the player's position is on or after Electric Company, give em $200.
                if (board.boardProperties.indexOf(p.getCell()) >= 12)
                    p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 2);

                p.setCell(board.boardProperties.get(11));
                break;

            case six:  // "Bank pays you dividend of $50"
                p.setCash(CASH_TYPES.fifties, p.getCash(CASH_TYPES.fifties) + 1);
                break;
            case seven:  // "Get out of Jail Free – this card may be kept until needed, or traded/sold"
                p.giveGOOJF();
                break;
            case eight:  // "Go back 3 spaces"
                // TODO: This should charge the Player for landing on Income Tax.
                p.setCell(board.boardProperties.get(board.boardProperties.indexOf(p.getCell()) - 3));

                // TODO: Then call onland function.
                // TODO: Or just call movePlayer() ?
                // TODO: Should I use changePosition() instead for some reason?
                break;
            case nine:  // "Go directly to Jail – do not pass Go, do not collect $200"
                p.putInJail(board);
                break;
            case ten: // "Make general repairs on all your property – for each house pay $25 –
                //  for each hotel $100"
                // If the payment will bankrupt the Player, do x.
                // TODO: Haven't tested this yet, to make sure I get correct values out of parseInt().
                // TODO: Once this is working, make sure to use playerPayBank() rather than manually
                //       including if (p.willBankrupt()), etc.
                int payment = (p.getNumHouses() * 25) + (p.getNumHotels() * 100);

                if ( p.willBankrupt(payment) )
                    p.bankruptPlayer(board);
                else {
                    //TODO: Remove cash here.
                }
                break;
            case eleven: // "Pay poor tax of $15"
                p.payBank(15, board);
                break;
            case twelve: // "Take a trip to Reading Railroad – if you pass Go, collect $200"
                // If the player's position is on or after Oriental avenue, give em $200.
                if (board.boardProperties.indexOf(p.getCell()) >= 6)
                    p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 2);

                p.setCell(board.boardProperties.get(5));
                // TODO: And then onland function.
                break;

            case thirteen: // "Take a walk on the Boardwalk – advance token to Boardwalk"
                p.setCell(board.boardProperties.get(39));
                // TODO: And then call the onland function for boardwalk.
                break;
            case fourteen: // "You have been elected chairman of the board – pay each player $50"
                for (Player player : board.players) {
                    if (player != p)
                        player.setCash(CASH_TYPES.fifties, player.getCash(CASH_TYPES.fifties) + 1);
                }
                // TODO: This won't be 150 when one or more other players are bankrupt.
                p.payBank(150, board);
                break;
            case fifteen: // "Your building loan matures – collect $150"
                p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 1);
                p.setCash(CASH_TYPES.fifties, p.getCash(CASH_TYPES.fifties) + 1);
                break;
            case sixteen: // "You have won a crossword competition - collect $100"
                p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 1);
                break;
        }
    }
}
