package com.ideopoly.tests;

import junit.framework.TestCase;
import org.junit.*;
import java.awt.*;
import com.ideopoly.game.*;

/** Class to test all methods inside the CommunityChest class.
 *
 *  @author Daniel Neel */
public class CommunityChestTester extends TestCase {
    private CommunityChest commChestCard;
    private GameBoard board = new GameBoard("Test Board");
    private Player p1 = new Player(1, new Color(1, 238, 0) , board);
    private Player p2 = new Player(2, new Color(223, 254, 10), board);
    private Player p3 = new Player(3, new Color(253, 186, 17), board);
    private Player p4 = new Player(4, new Color(19, 214, 242), board);

    // TODO: Javadocs for all methods here.
    @Test
    public void testCommChest1() {
	commChestCard = new CommunityChest(1);
	assertEquals(commChestCard.getType(), 1);
	assertEquals(commChestCard.getText(), "Advance to Go (Collect $200)");
	// Ensure that this method works properly on all three Comm. Chest spots and for all Players.
	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	// Make sure the players land on the right spot and get the right amount of $.
	TestHelper.assertCash(1700, p1, p2, p3, p4);
	TestHelper.assertSameCell(0, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1900, p1, p2, p3, p4);
	TestHelper.assertSameCell(0, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(2100, p1, p2, p3, p4);
	TestHelper.assertSameCell(0, board, p1, p2, p3, p4);
    }

    @Test
    public void testCommChest2() {
	commChestCard = new CommunityChest(2);
	assertEquals(commChestCard.getType(), 2);
	assertEquals(commChestCard.getText(), "Bank error in your favor – collect $200");

	// Test this on all three Comm. Chest spots and for all Players.
	// Make sure the players get the right amount of money and that they're 
	// on the correct BoardCells.
	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1700, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1900, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(2100, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);
    }

    @Test
    public void testCommChest3() {
	commChestCard = new CommunityChest(3);
	assertEquals(commChestCard.getType(), 3);
	assertEquals(commChestCard.getText(), "Doctor's fees – Pay $50");

	// Test on all three Community Chest spots with normal conditions. Then
	// make sure bankruptcy is correctly handled..
	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1450, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1400, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1350, p1, p2, p3, p4);


	// Now bankrupt the player.
	// TODO: Use the player's bankruptPlayer() method here? Or make a helper method?
	p1.setCash(CASH_TYPES.ones,         1);
	p1.setCash(CASH_TYPES.fives,        0);
	p1.setCash(CASH_TYPES.tens,         0);
	p1.setCash(CASH_TYPES.twenties,     0);
	p1.setCash(CASH_TYPES.fifties,      0);
	p1.setCash(CASH_TYPES.hundreds,     0);
	p1.setCash(CASH_TYPES.fiveHundreds, 0);
	commChestCard.doActions(p1, board);

	assertEquals(p1.getCash(CASH_TYPES.total),        0);
	assertEquals(p1.getCash(CASH_TYPES.ones),         0);
	assertEquals(p1.getCash(CASH_TYPES.fives),        0);
	assertEquals(p1.getCash(CASH_TYPES.tens),         0);
	assertEquals(p1.getCash(CASH_TYPES.twenties),     0);
	assertEquals(p1.getCash(CASH_TYPES.fifties),      0);
	assertEquals(p1.getCash(CASH_TYPES.hundreds),     0);
	assertEquals(p1.getCash(CASH_TYPES.fiveHundreds), 0);
    }

    @Test
    public void testCommChest4() {
	commChestCard = new CommunityChest(4);
	assertEquals(commChestCard.getType(), 4);
	assertEquals(commChestCard.getText(), "Get Out of Jail Free – this card may be kept until needed, or sold");

	assertEquals(p1.getNumGOOJFCards(), 0);
	assertEquals(p2.getNumGOOJFCards(), 0);
	assertEquals(p3.getNumGOOJFCards(), 0);
	assertEquals(p4.getNumGOOJFCards(), 0);

	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);

	assertEquals(p1.getNumGOOJFCards(), 1);
	assertEquals(p2.getNumGOOJFCards(), 1);
	assertEquals(p3.getNumGOOJFCards(), 1);
	assertEquals(p4.getNumGOOJFCards(), 1);

	// TODO: And then any weird situations.
    }

    @Test
    public void testCommChest5() {
	commChestCard = new CommunityChest(5);
	assertEquals(commChestCard.getType(), 5);
	assertEquals(commChestCard.getText(), "Go to Jail – go directly to jail – Do not pass Go, do not collect $200");

	// Test this on all 3 CommChest cells with all 4 players, and make sure 
	// they land on the correct spot and don't collect $200.
	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1500, p1, p2, p3, p4);
	TestHelper.assertSameCell(10, board, p1, p2, p3, p4);
	TestHelper.assertSameJailStatus(3, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1500, p1, p2, p3, p4);
	TestHelper.assertSameCell(10, board, p1, p2, p3, p4);
	TestHelper.assertSameJailStatus(3, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1500, p1, p2, p3, p4);
	TestHelper.assertSameCell(10, board, p1, p2, p3, p4);
	TestHelper.assertSameJailStatus(3, p1, p2, p3, p4);

	// TODO: And then test any odd cases that could come up.
    }

    @Test
    public void testCommChest6() {
	board = new GameBoard("Test Board");
	p1 = board.player1;
	p2 = board.player2;
	p3 = board.player3;
	p4 = board.player4;

	commChestCard = new CommunityChest(6);
	assertEquals(commChestCard.getType(), 6);
	assertEquals(commChestCard.getText(), "It is your birthday - Collect $10 from each player");

	// Test when no players are bankrupt.
	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	commChestCard.doActions(p1, board);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);
	TestHelper.assertCash(1530, 1490, 1490, 1490, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	commChestCard.doActions(p1, board);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);
	TestHelper.assertCash(1560, 1480, 1480, 1480, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	commChestCard.doActions(p1, board);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);
	TestHelper.assertCash(1590, 1470, 1470, 1470, p1, p2, p3, p4);

	// And test when 1, 2, and 3 other players are bankrupt.
	p1 = new Player(1, new Color(1, 238, 0), board);
	p2.bankruptPlayer(board);
	commChestCard.doActions(p1, board);
	TestHelper.assertCash(1520, 0, 1460, 1460, p1, p2, p3, p4);

	p3.bankruptPlayer(board);
	commChestCard.doActions(p1, board);
	TestHelper.assertCash(1530, 0, 0, 1450, p1, p2, p3, p4);

	p4.bankruptPlayer(board);
	commChestCard.doActions(p1, board);
	TestHelper.assertCash(1530, 0, 0, 0, p1, p2, p3, p4);
    }

    @Test
    public void testCommChest7() {
	board = new GameBoard("Test Board");
	p1 = board.player1;
	p2 = board.player2;
	p3 = board.player3;
	p4 = board.player4;

	commChestCard = new CommunityChest(7);
	assertEquals(commChestCard.getType(), 7);
	assertEquals(commChestCard.getText(), "Grand Opera Night – collect $50 from every player for opening night seats");

	// Test when no players are bankrupt.
	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	commChestCard.doActions(p1, board);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.assertCash(1650, 1450, 1450, 1450, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	commChestCard.doActions(p1, board);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);
	TestHelper.assertCash(1800, 1400, 1400, 1400, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	commChestCard.doActions(p1, board);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);
	// TODO: A method for asserting arbitrary total cash values?
	TestHelper.assertCash(1950, 1350, 1350, 1350, p1, p2, p3, p4);

	// And then when 1, 2, and 3 other players are bankrupt.
	p1 = new Player(1, new Color(1, 238, 0), board);
	p2.bankruptPlayer(board);
	commChestCard.doActions(p1, board);
	TestHelper.assertCash(1600, 0, 1300, 1300, p1, p2, p3, p4);

	p3.bankruptPlayer(board);
	commChestCard.doActions(p1, board);
	TestHelper.assertCash(1650, 0, 0, 1250, p1, p2, p3, p4);

	p4.bankruptPlayer(board);
	commChestCard.doActions(p1, board);
	TestHelper.assertCash(1650, 0, 0, 0, p1, p2, p3, p4);
    }

    @Test
    public void testCommChest8() {
	commChestCard = new CommunityChest(8);
	assertEquals(commChestCard.getType(), 8);
	assertEquals(commChestCard.getText(), "Income Tax refund – collect $20");

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1520, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1540, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1560, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);
	// TODO: Then is it possible to test any out of whack cases?
    }

    @Test
    public void testCommChest9() {
	commChestCard = new CommunityChest(9);
	assertEquals(commChestCard.getType(), 9);
	assertEquals(commChestCard.getText(), "Life Insurance Matures – collect $100");

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1600, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1700, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1800, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: And then any screwed cases here.
    }

    @Test
    public void testCommChest10() {
	commChestCard = new CommunityChest(10);
	assertEquals(commChestCard.getType(), 10);
	assertEquals(commChestCard.getText(), "Pay Hospital Fees of $100");

	TestHelper.assertCash(1500, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1400, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1300, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1200, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: This pattern of testing is repeated. Can probably refactor.
	// ...and then when a player will bankrupt.
	p1.setCash(CASH_TYPES.ones,         1);
	p1.setCash(CASH_TYPES.fives,        0);
	p1.setCash(CASH_TYPES.tens,         0);
	p1.setCash(CASH_TYPES.twenties,     0);
	p1.setCash(CASH_TYPES.fifties,      0);
	p1.setCash(CASH_TYPES.hundreds,     0);
	p1.setCash(CASH_TYPES.fiveHundreds, 0);
	commChestCard.doActions(p1, board);

	assertEquals(p1.getCash(CASH_TYPES.total),        0);
	assertEquals(p1.getCash(CASH_TYPES.ones),         0);
	assertEquals(p1.getCash(CASH_TYPES.fives),        0);
	assertEquals(p1.getCash(CASH_TYPES.tens),         0);
	assertEquals(p1.getCash(CASH_TYPES.twenties),     0);
	assertEquals(p1.getCash(CASH_TYPES.fifties),      0);
	assertEquals(p1.getCash(CASH_TYPES.hundreds),     0);
	assertEquals(p1.getCash(CASH_TYPES.fiveHundreds), 0);


	// And when the amount payed is the Player's exact total of cash.
	p1.setCash(CASH_TYPES.ones,         0);
	p1.setCash(CASH_TYPES.fives,        0);
	p1.setCash(CASH_TYPES.tens,         0);
	p1.setCash(CASH_TYPES.twenties,     0);
	p1.setCash(CASH_TYPES.fifties,      0);
	p1.setCash(CASH_TYPES.hundreds,     1);
	p1.setCash(CASH_TYPES.fiveHundreds, 0);
	commChestCard.doActions(p1, board);

	assertEquals(p1.getCash(CASH_TYPES.total),        0);
	assertEquals(p1.getCash(CASH_TYPES.ones),         0);
	assertEquals(p1.getCash(CASH_TYPES.fives),        0);
	assertEquals(p1.getCash(CASH_TYPES.tens),         0);
	assertEquals(p1.getCash(CASH_TYPES.twenties),     0);
	assertEquals(p1.getCash(CASH_TYPES.fifties),      0);
	assertEquals(p1.getCash(CASH_TYPES.hundreds),     0);
	assertEquals(p1.getCash(CASH_TYPES.fiveHundreds), 0);
    }

    @Test
    public void testCommChest11() {
	commChestCard = new CommunityChest(11);
	assertEquals(commChestCard.getType(), 11);
	assertEquals(commChestCard.getText(), "Pay School Fees of $50");

	TestHelper.assertCash(1500, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1450, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1400, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1350, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// ...and then when a player will bankrupt.
	p1.setCash(CASH_TYPES.ones,         1);
	p1.setCash(CASH_TYPES.fives,        0);
	p1.setCash(CASH_TYPES.tens,         0);
	p1.setCash(CASH_TYPES.twenties,     0);
	p1.setCash(CASH_TYPES.fifties,      0);
	p1.setCash(CASH_TYPES.hundreds,     0);
	p1.setCash(CASH_TYPES.fiveHundreds, 0);
	commChestCard.doActions(p1, board);

	assertEquals(p1.getCash(CASH_TYPES.total),        0);
	assertEquals(p1.getCash(CASH_TYPES.ones),         0);
	assertEquals(p1.getCash(CASH_TYPES.fives),        0);
	assertEquals(p1.getCash(CASH_TYPES.tens),         0);
	assertEquals(p1.getCash(CASH_TYPES.twenties),     0);
	assertEquals(p1.getCash(CASH_TYPES.fifties),      0);
	assertEquals(p1.getCash(CASH_TYPES.hundreds),     0);
	assertEquals(p1.getCash(CASH_TYPES.fiveHundreds), 0);


	// And when the amount payed is the Player's exact total of cash.
	p1.setCash(CASH_TYPES.ones,         0);
	p1.setCash(CASH_TYPES.fives,        0);
	p1.setCash(CASH_TYPES.tens,         0);
	p1.setCash(CASH_TYPES.twenties,     0);
	p1.setCash(CASH_TYPES.fifties,      1);
	p1.setCash(CASH_TYPES.hundreds,     0);
	p1.setCash(CASH_TYPES.fiveHundreds, 0);
	commChestCard.doActions(p1, board);

	assertEquals(p1.getCash(CASH_TYPES.total),        0);
	assertEquals(p1.getCash(CASH_TYPES.ones),         0);
	assertEquals(p1.getCash(CASH_TYPES.fives),        0);
	assertEquals(p1.getCash(CASH_TYPES.tens),         0);
	assertEquals(p1.getCash(CASH_TYPES.twenties),     0);
	assertEquals(p1.getCash(CASH_TYPES.fifties),      0);
	assertEquals(p1.getCash(CASH_TYPES.hundreds),     0);
	assertEquals(p1.getCash(CASH_TYPES.fiveHundreds), 0);
    }

    @Test
    public void testCommChest12() {
	commChestCard = new CommunityChest(12);
	assertEquals(commChestCard.getType(), 12);
	assertEquals(commChestCard.getText(), "Receive $25 Consultancy Fee");

	// TODO: Make a helper method for this type of card? IE: all players
	//       move to a spot, doactions, gain money, and land on a final spot.

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1525, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1550, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1575, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest13() {
	commChestCard = new CommunityChest(13);
	assertEquals(commChestCard.getType(), 13);
	assertEquals(commChestCard.getText(), "You are assessed for street repairs – $40 per house, $115 per hotel");

	// Test when the player has no property.
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 1500);

	// Then when the player has 1 house.
	p1.setNumHouses(1);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 1460);

	// Then when the player has 2 houses.
	p1.setNumHouses(2);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 1380);

	// Then when the player has 3 houses.
	p1.setNumHouses(3);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 1260);

	// Then when the player has 4 houses.
	p1.setNumHouses(4);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 1100);

	// Then when the player has 5 houses.
	p1.setNumHouses(5);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 900);

	// Then when the player has 1 hotel.
	p1.setNumHouses(0);
	p1.setNumHotels(1);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 785);

	// Then when the player has 2 hotels.
	p1.setNumHotels(2);
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 555);

	// And lastly when the player will be bankrupt by this.
	p1.setNumHouses(10); // $400
	p1.setNumHotels(2);  // $300
	commChestCard.doActions(p1, board);
	assertEquals(p1.getCash(CASH_TYPES.total), 0);
    }


    @Test
    public void testCommChest14() {
	commChestCard = new CommunityChest(14);
	assertEquals(commChestCard.getType(), 14);
	assertEquals(commChestCard.getText(), "You have won second prize in a beauty contest– collect $10");

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1510, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1520, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1530, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest15() {
	commChestCard = new CommunityChest(15);
	assertEquals(commChestCard.getType(), 15);
	assertEquals(commChestCard.getText(), "You inherit $100");

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1600, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1700, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1800, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest16() {
	commChestCard = new CommunityChest(16);
	assertEquals(commChestCard.getType(), 16);
	assertEquals(commChestCard.getText(), "From sale of stock you get $50");

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1550, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1600, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1650, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    public void testCommChest17() {
	commChestCard = new CommunityChest(17);
	assertEquals(commChestCard.getType(), 17);
	assertEquals(commChestCard.getText(), "Holiday Fund matures - Receive $100");

	TestHelper.changeCellAllPlayers(board.boardProperties.get(2), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1600, p1, p2, p3, p4);
	TestHelper.assertSameCell(2, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(17), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1700, p1, p2, p3, p4);
	TestHelper.assertSameCell(17, board, p1, p2, p3, p4);

	TestHelper.changeCellAllPlayers(board.boardProperties.get(33), board, p1, p2, p3, p4);
	TestHelper.doActionsAllPlayers(commChestCard, board, p1, p2, p3, p4);
	TestHelper.assertCash(1800, p1, p2, p3, p4);
	TestHelper.assertSameCell(33, board, p1, p2, p3, p4);

	// TODO: And then any odd cases here.
    }

    @Test
    /** Test incorrect constructor values, etc. */
    public void testBrokenCommChest() {
	// Make sure un-allowed card types produce incorrect text, etc.
	commChestCard = new CommunityChest(18);
	assertEquals(commChestCard.getType(), 18);

	assertEquals(commChestCard.getText(), null);

	commChestCard = new CommunityChest(-1);
	assertEquals(commChestCard.getType(), -1);
	assertEquals(commChestCard.getText(), null);

	commChestCard = new CommunityChest(0);
	assertEquals(commChestCard.getType(), 0);
	assertEquals(commChestCard.getText(), null);
    }
}
