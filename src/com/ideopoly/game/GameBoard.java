package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

// TODO: Write an elisp function for making a new TODO, bind to C-c C-t.
// TODO: com as a package name start doesn't make much sense. I don't own a domain name.
// TODO: Add in Chance and Comm. Chest images.
// TODO: Use the native look and feel for the program.
// TODO: Get rid of the spacing between cells.
// TODO: Make text on cells readable.
// TODO: Review usage of == for String comparison. See http://www.javapractices.com/topic/TopicAction.do;jsessionid=C1B6B905C6F4CC6B91E98376A9194A3B?Id=18
// TODO: Look into replacing arrays with ArrayLists. Could be useful, not sure.
// TODO: Is the Ideopoly- prefix for the class name needed? Wouldn't GUI/similar work just as well and be shorter?

/** Class to contain the GUI of the game board. A new instance of the class
 *  creates the GUI and sets up various things in it. The game takes place mostly
 *  inside this GUI - the others are just there to get ya to the point where you
 *  can play the game.
 *
 *  @author Daniel Neel */
public class GameBoard implements ActionListener {
    // TODO: Make some of these arrays, etc., final/static when they're constant. Also see
    // if it's possible to get rid of some of them.
    /** Number to represent if the game has been won. Once the game's won, 
     *  this switches to another value. */
    private int gameWon = 0;

    // TODO: Can I make this private?
    public final String cashValues[] = { "ones", "fives", "tens", "twenties", "fifties", "hundreds", "fiveHundreds", "total"};
    private final String cashHeadings[] = { "Cash", "1s", "5s", "10s", "20s", "50s", "100s", "500s", "Total", "GOOJF cards", "Turns left in jail" };
    // TODO: Come up with a better solution than making this public.
    public final JLabel[] playerRowLabels = { new JLabel("Player 1"), 
					new JLabel("Player 2"), 
					new JLabel("Player 3"), 
					new JLabel("Player 4") };

    /* TODO: Consider making these values constant, since they won't change.
       IE: static final int ROWS = 4
       static final int COLS = 7
       JLabel[][] cashLabels = new JLabel[ROWS][COLS]; */
    private CashCell[][] cashLabels = new CashCell[4][10];

    /** Array used to store the values of each type of bill a Player
     *  should pay after requiring a payment. */
    // TODO: Can I make this private?
    public  int[]   paymentAmounts   = {0, 0, 0, 0, 0, 0, 0};
    private JFrame  frame	     = new JFrame("Ideopoly | Main game");
    private JButton continueButton   = new JButton("Continue");//new ImageIcon("images/continueButton.jpg"));
    private JButton buyProperty	     = new JButton("Buy property");
    private JButton buyHouse	     = new JButton("Buy house");
    private JButton buyHotel	     = new JButton("Buy hotel");
    private JButton sellProperty     = new JButton("Sell property");
    private JButton mortgageProperty = new JButton("Mortgage property");
    // TODO: Should I make this private, and include a function to set its enable-ability? The Player
    // class is currently the only outside class that changes its state.
    public  JButton useGOOJFCard     = new JButton("Use get out of jail free card");
    /** An area of text where we can display messages to the player. */
    // TODO: Make messages a little larger - would be useful so the player can see more at a time.
    private JTextArea messages = new JTextArea("Welcome to Ideopoly!\nTo start playing, press \"Continue\". \nUse the other buttons to buy properties, sell properties, etc. \nGood luck!", 7, 1);

    // Create the game board.
    // TODO: Remove unneeded image templates.
    private SpecialCell	     go              = new SpecialCell("Go", "go.jpg", 41, 41, this);
    private PropagandaOutlet mediterraneanAv = new PropagandaOutlet("Mediterranean Av.", "purpleTemplate.jpg", 60, 2, 10, 30, 90, 160, 250, 50, 37, 41, this, 120, 1, 217);
    private ChanceOrCommChestCell commChestBottom = new ChanceOrCommChestCell("Community Chest", "bottCommChest.jpg", 33, 41, this);
    private PropagandaOutlet balticAv        = new PropagandaOutlet("Baltic Av.", "purpleTemplate.jpg", 60, 4, 20, 60, 180, 320, 450, 50, 29, 41, this, 120, 1, 217);
    private SpecialCell	     incomeTax       = new SpecialCell("Income Tax", "incomeTax.jpg", 25, 41, this);
    // TODO: Bigger font size (~80) for the text on railroads - hard to read currently.
    private Railroad	     readingRR       = new Railroad("Reading RR", "readingRR.jpg", 21, 41, this);
    private PropagandaOutlet orientalAv      = new PropagandaOutlet("Oriental Av.", "lightBlueTemplate.jpg", 100, 6, 30, 90, 270, 400, 550, 50, 17, 41, this, 174, 239, 255);
    private ChanceOrCommChestCell chanceBottom = new ChanceOrCommChestCell("Chance", "botChance.jpg", 13, 41, this);
    private PropagandaOutlet vermontAv       = new PropagandaOutlet("Vermont Av.", "lightBlueTemplate.jpg", 100, 6, 30, 90, 270, 400, 550, 50, 9, 41, this, 174, 239, 255);
    private PropagandaOutlet connecticutAv   = new PropagandaOutlet("Connecticut Av.", "lightBlueTemplate.jpg", 120, 8, 40, 100, 300, 450, 600, 50, 5, 41, this, 174, 239, 255);
    private SpecialCell	     jail	     = new SpecialCell("Jail", "jail.jpg", 1, 41, this);
    private PropagandaOutlet stCharles       = new PropagandaOutlet("St. Charles Place", "pinkTemplate.jpg", 140, 10, 50, 150, 450, 625, 750, 100, 1, 37, this, 254, 2, 151);
    private UtilityCell	     electricCompany = new UtilityCell("Electric Co.", "electricCo.jpg", 1, 33, this);
    private PropagandaOutlet statesAv	     = new PropagandaOutlet("States Av.", "pinkTemplate.jpg", 140, 10, 50, 150, 450, 625, 750, 100, 1, 29, this, 254, 2, 151);
    private PropagandaOutlet virginiaAv      = new PropagandaOutlet("Virginia Av.", "pinkTemplate.jpg", 160, 12, 60, 180, 500, 700, 900, 100, 1, 25, this, 254, 2, 151);
    private Railroad	     pennsylvaniaRR  = new Railroad("Pennsylvania RR", "pennsylvaniaRR.jpg", 1, 21, this);
    private PropagandaOutlet stJames         = new PropagandaOutlet("St. James", "orangeTemplate.jpg", 180, 14, 70, 200, 550, 750, 950, 100, 1, 17, this, 255, 188, 6);
    private ChanceOrCommChestCell commChestLeft = new ChanceOrCommChestCell("Community Chest", "leftCommChest.jpg", 1, 13, this);
    private PropagandaOutlet tennesseeAv     = new PropagandaOutlet("Tennessee Av.", "orangeTemplate.jpg", 180, 14, 70, 200, 550, 750, 950, 100, 1, 9, this, 255, 188, 6);
    private PropagandaOutlet newYorkAv       = new PropagandaOutlet("New York Av.", "orangeTemplate.jpg", 200, 16, 80, 220, 600, 800, 1000, 100, 1, 5, this, 255, 188, 6);
    private SpecialCell      freeParking     = new SpecialCell("Free Parking", "freeParking.jpg", 1, 1, this);
    private PropagandaOutlet kentuckyAv      = new PropagandaOutlet("Kentucky Av.", "properties/kentuckyAv.jpg", 220, 18, 90, 250, 700, 875, 1050, 150, 5, 1, this, 255, 0, 0);
    private ChanceOrCommChestCell chanceTop  = new ChanceOrCommChestCell("Chance", "topChance.jpg", 9, 1, this);
    private PropagandaOutlet indianaAv	     = new PropagandaOutlet("Indiana Av.", "properties/indianaAv.jpg", 220, 18, 90, 250, 700, 875, 1050, 150, 13, 1, this, 255, 0, 0);
    private PropagandaOutlet illinoisAv      = new PropagandaOutlet("Illinois Av.", "properties/illinoisAv.jpg", 240, 20, 100, 300, 750, 925, 1100, 150, 17, 1, this, 255, 0, 0);
    private Railroad	     bAndORR	     = new Railroad("B & O RR", "bAndORR.jpg", 21, 1, this);
    private PropagandaOutlet atlanticAv      = new PropagandaOutlet("Atlantic Av.", "properties/atlanticAv.jpg", 260, 22, 110, 330, 800, 975, 1150, 1, 25, 1, this, 252, 255, 0);
    private PropagandaOutlet ventnorAv	     = new PropagandaOutlet("Ventnor Av.", "properties/ventnorAv.jpg", 260, 22, 110, 330, 800, 975, 1150, 150, 29, 1, this, 252, 255, 0);
    private UtilityCell	     waterWorks      = new UtilityCell("Water Works", "waterWorks.jpg", 33, 1, this);
    private PropagandaOutlet marvinGardens   = new PropagandaOutlet("Marvin Gardens", "properties/marvinGardens.jpg", 280, 24, 120, 360, 850, 1025, 1200, 150, 37, 1, this, 252, 255, 0);
    private SpecialCell      goToJail        = new SpecialCell("Go to Jail", "goToJail.jpg", 41, 1, this);
    private PropagandaOutlet pacificAv	     = new PropagandaOutlet("Pacific Av.", "greenTemplate.jpg", 300, 26, 130, 390, 900, 1100, 1275, 200, 41, 5, this, 0, 196, 0);
    private PropagandaOutlet nCarolinaAv     = new PropagandaOutlet("N. Carolina Av.", "greenTemplate.jpg", 300, 26, 130, 390, 900, 1100, 1275, 200, 41, 9, this, 0, 196, 0);
    private ChanceOrCommChestCell commChestRight = new ChanceOrCommChestCell("Community Chest", "rightCommChest.jpg", 41, 13, this);
    private PropagandaOutlet pennsylvaniaAv  = new PropagandaOutlet("Pennsylvania Av.", "greenTemplate.jpg", 320, 28, 150, 450, 1000, 1200, 1400, 200, 41, 17, this, 0, 196, 0);
    private Railroad         shortLineRR     = new Railroad("Short Line RR", "shortLineRR.jpg", 41, 21, this);
    private ChanceOrCommChestCell chanceRight = new ChanceOrCommChestCell("Chance", "rightChance.jpg", 41, 25, this);
    private PropagandaOutlet parkPlace       = new PropagandaOutlet("Park Place", "darkBlueTemplate.jpg", 350, 35, 175, 500, 1100, 1300, 1500, 200, 41, 29, this, 0, 8, 253);
    private SpecialCell      luxuryTax       = new SpecialCell("Luxury Tax", "luxuryTax.jpg", 41, 33, this);
    private PropagandaOutlet boardwalk       = new PropagandaOutlet("Boardwalk", "darkBlueTemplate.jpg", 400, 50, 200, 600, 1400, 1700, 2000, 200, 41, 37, this, 0, 8, 253);

    // TODO: See file:///home/daniel/Desktop/aaa-TIJ3-distribution/TIJ3.htm and then
    //       "Downcasting vs. templates/generics" for how to get the original class back out
    //       of this array. This should help solve some problems I was having before. See above TODO.
    // TODO: Note that this might be better represented by a Set collection. I don't have any
    //       duplicate items, and operating on a collection could be handy possibly.
    // TODO: Rename this propaganda outlets rather than properties? Confusing?
    // TODO: It would be cool if I could handle these similar to colors. IE, similar to 
    //       Color.GREEN, I could have IdeopolyGUI.TENNESSEE for referring to the items.
    //       ^-- I'm pretty sure I can do that by making this a static field.
    // And while I'm at it, static imports look worthwhile: http://docs.oracle.com/javase/tutorial/java/package/usepkgs.html
    // TODO: Possible to make this not public?
    public final BoardCell boardProperties[] = { go, mediterraneanAv, commChestBottom, balticAv, incomeTax, readingRR, orientalAv, chanceBottom, vermontAv, connecticutAv, jail, stCharles, electricCompany, statesAv, virginiaAv, pennsylvaniaRR, stJames, commChestLeft, tennesseeAv, newYorkAv, freeParking, kentuckyAv, chanceTop, indianaAv, illinoisAv, bAndORR, atlanticAv, ventnorAv, waterWorks, marvinGardens, goToJail, pacificAv, nCarolinaAv, commChestRight, pennsylvaniaAv, shortLineRR, chanceRight, parkPlace, luxuryTax, boardwalk }; // The game board is represented as an array of BoardCells

    /** Represents the player whose turn it currently is to roll. 0-3. */
    // TODO: There should be a more elegant way of doing this than storing
    //       the player as an int. For example, make current player status
    //       a boolean field that belongs to each player.
    //    One idea would be just to make a function that checks the field for each player
    //    and returns the correct player.
    private int currentPlayer = 0;
    public Player player1   = new Player(1, new Color(1, 238, 0) , this);
    public Player player2   = new Player(2, new Color(223, 254, 10), this);
    public Player player3   = new Player(3, new Color(253, 186, 17), this);
    public Player player4   = new Player(4, new Color(19, 214, 242), this);
    // TODO: Try to reduce usage of this players array. Is useless and confusing except when looping.
    // TODO: Does this need to be public?
    // TODO: Since it's public, javadocs for the field.
    /** An array of the four players who will be playing in this game. player1
     *  is the human player, the rest are computer players. */
    public Player players[] = {player1, player2, player3, player4};

    /** The stack of Chance cards. */
    private Stack<Chance> chanceCards = new Stack<Chance>();

    /** The stack of Community Chest cards. */
    private Stack<CommunityChest> commChestCards = new Stack<CommunityChest>();

    /** Various fields that display detailed property information. */
    // TODO: Better, more descriptive variable names. eg, detailedPropColor, etc.
    private JLabel guiColor    = new JLabel(" ");
    private JLabel guiName     = new JLabel("-");
    private JLabel guiCost     = new JLabel("-");
    private JLabel guiHouseHotelCost = new JLabel("-");
    private JLabel guiRent     = new JLabel("-");
    private JLabel gui1House   = new JLabel("-");
    private JLabel gui2House   = new JLabel("-");
    private JLabel gui3House   = new JLabel("-");
    private JLabel gui4House   = new JLabel("-");
    private JLabel guiHotel    = new JLabel("-");
    private JLabel guiMortgage = new JLabel("-");

    /** Constructor creates the GUI, sets up parts of it, etc. */
    public GameBoard(String playerCharacter) { // TODO: Split up the functions logically.
	// Create the gui.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// TODO: Use multiple instances of GridBagConstraints?
	frame.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	// Align all components in the "top-right" of their display area.
	c.anchor = GridBagConstraints.FIRST_LINE_START;

	// ===================================================
	// === For every cell on the board, add its image. ===
	// ===================================================
	c.gridwidth  = 4;
	c.gridheight = 4;

	for (BoardCell cell : boardProperties) {
	    setConstraintsXY(c, cell.getX(), cell.getY());
	    frame.add(cell.getGraphicalRepresentation(), c);
	}
	
	// ================================================================
	// === Add the standing positions around the edge of the board. ===
	// ================================================================
	c.gridwidth  = 1;
	c.gridheight = 1;

	for (BoardCell property : boardProperties) {
	    BoardPosition[] cellPositions = {property.getPosition(1),
					     property.getPosition(2),
					     property.getPosition(3),
					     property.getPosition(4)};

	    for (BoardPosition pos : cellPositions) {
		setConstraintsXY(c, pos.getXCoord(), pos.getYCoord());
		frame.add(pos, c);
	    }
	}

	// ====================================================
	// === Create labels to display each player's cash. ===
	// ====================================================
	setConstraintsXY(c, 50, 0);

	// TODO: Would be good if I just used a 2D array to store all this, rather
	// than the separate titles, playerRowLabels, and for loop parts.
	// Display the column titles.
	for (String s : cashHeadings) {
	    frame.add(new JLabel(s), c);
	    c.gridx++;
	}

	// Display Player 1/2/3/4: to the left of the rows.
	c.gridx = 50;

	for (JLabel j : playerRowLabels) {
	    c.gridy++;
	    frame.add(j, c);
	}

	// ==============================================================
	// === Initialize the labels that display player cash values. ===
	// ==============================================================
	// TODO: Replace this with a foreach loop. Possible with the players[i] part?
	for (int i = 0; i <= 3; i++) { // Iterate through each row.
	    for (int j = 0; j <= 9; j++) { // And through each column.
		// Add get out of jail free cards, rather than cash, in the 8th column.
		// TODO: I should be able to reduce the code in this block a bit. A little tricky, doable.
		if (j == 9)      // Jail status.
		    cashLabels[i][j] = new CashCell(60, 1 + i, Integer.toString(players[i].getJailStatus()));
		else if (j == 8) // Get out of jail free cards.
		    cashLabels[i][j] = new CashCell(59, 1 + i, Integer.toString(players[i].getNumGOOJFCards()));
		else             // Cash amounts.
		    cashLabels[i][j] = new CashCell(51 + j, 1 + i, Integer.toString(players[i].getCash(cashValues[j])));
	    }
	}

	// Then add all the labels to the board.
	for (CashCell[] outer : cashLabels) {
	    for (CashCell cc : outer) {
		setConstraintsXY(c, cc.getXCoord(), cc.getYCoord());
		frame.add(cc, c);
	    }
	}

	// Add a Continue button in the middle of the board.
	c.gridwidth  = 7;
	c.gridheight = 2;
	setConstraintsXY(c, 22, 22);
	frame.add(continueButton, c);

	// ======================================================================
	// === Add buttons (buy houses, etc.) to the right side of the board. ===
	// ======================================================================
	c.fill = GridBagConstraints.HORIZONTAL;

	// Disable currently unusable buttons.
	buyProperty.setEnabled(false);
	buyHouse.setEnabled(false);
	buyHotel.setEnabled(false);
	sellProperty.setEnabled(false);
	mortgageProperty.setEnabled(false);
	useGOOJFCard.setEnabled(false);

	// Add action listeners.
	continueButton.addActionListener(this);
	buyProperty.addActionListener(this);
	buyHouse.addActionListener(this);
	buyHotel.addActionListener(this);
	sellProperty.addActionListener(this);
	mortgageProperty.addActionListener(this);
	useGOOJFCard.addActionListener(this);

	// Left column.
	setConstraintsXY(c, 50, 6);
	c.gridwidth = 9;
	frame.add(buyProperty, c);

	c.gridwidth = 5;
	c.gridy     = 8;
	frame.add(buyHouse, c);

	c.gridy = 9;
	frame.add(buyHotel, c);

	// Right column.
	c.gridwidth = 4;
	setConstraintsXY(c, 55, 8);
	frame.add(sellProperty, c);

	c.gridy = 9;
	frame.add(mortgageProperty, c);

	setConstraintsXY(c, 50, 11);
	c.gridwidth = 9;
	frame.add(useGOOJFCard, c);

	// ===============================================================
	// === Add the GUI stuff that displays detailed property info. ===
	// ===============================================================
	c.gridy      = 20;
	c.gridwidth  = 9;
	c.gridheight = 1;
	guiColor.setOpaque(true);
	// TODO: Add a thin black border around the color.
	frame.add(guiColor, c);

	c.gridy++;
	// TODO: Center the title here.
	frame.add(guiName, c);

	String[] labels = {"House/Hotel cost", "Rent", "1 house", "2 houses", "3 houses", "4 houses", "1 hotel", "Mortgage value"};
	JLabel[] labelValues = {guiHouseHotelCost, guiRent, gui1House, gui2House, gui3House, gui4House, guiHotel, guiMortgage};

	c.gridwidth = 4;
	c.gridy++;
	frame.add(new JLabel("Cost:"), c);
	c.gridx += 6;
	frame.add(guiCost, c);

	for (int i = 0; i < labels.length; i++) {
	    c.gridy++;
	    c.gridx -= 6;
	    frame.add(new JLabel(labels[i] + ":"), c);
	    c.gridx += 6;
	    frame.add(labelValues[i], c);
	}

	// ==============================
	// === Add the messages area. ===
	// ==============================
	// LEFTOFFHERE: Need to break this into multiple methods or similar.
	//              Maybe break them up by the headings?
	setConstraintsXY(c, 50, 37);
	c.gridheight = 8;
	c.gridwidth  = 11;

	JScrollPane messagesPane = new JScrollPane(messages);
	messagesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	messages.setLineWrap(true);
	messages.setColumns(50);
	messagesPane.setColumnHeaderView(new JLabel("Status"));

	// TODO: Implement formatted text here. For example, bold the Player names
	// in messages.
	// If I want this, it looks like I'll need to switch messages from a JTextArea
	// to a JTextPane. docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html
	frame.add(messagesPane, c);

	// TODO: Make a single Random object and re-use it?
	Random generator = new Random();

	// Make a large stack of cards to use for the game.
	for (int i = 0; i < 200; i++) {
	    // Add random types of Chance and Community Chest cards.
	    chanceCards.push(new Chance(generator.nextInt(16) + 1));
	    commChestCards.push(new CommunityChest(generator.nextInt(17) + 1));
	}

	boardProperties[0].getPosition(1).setImage(player1.getImage());
	boardProperties[0].getPosition(2).setImage(player2.getImage());
	boardProperties[0].getPosition(3).setImage(player3.getImage());
	boardProperties[0].getPosition(4).setImage(player4.getImage());

	frame.pack();
	frame.setVisible(true);

	System.out.println("You picked " + playerCharacter + ".");
	//	readingRR.generateImage("testName", player1.getColor(), Color.YELLOW, "left");
    }

    /** Do a turn's worth of gameplay. First the player p rolls/moves. 
     *  Then cash is exchanged. Then players can buy/trade, etc.
     *  Return a 0 if nobody has won yet, or a 1 if someone won the game. */
    private void doTurn(JFrame frame, Player p) {
	int roll = new Random().nextInt(6) + 1; // Roll a new random number between 1 and 6.
	printStatusAndLog(players[currentPlayer].getName() + " rolls " + roll + ".");

	// First or second week in jail - player can't do anything but sit and wait.
	if (p.getJailStatus() == 3 || p.getJailStatus() == 2) {
	    p.setJailStatus(p.getJailStatus() - 1);
	}
	
	else {
	    if (p.getJailStatus() == 1) { // Last week in jail. Player gets charged $50, then moves forward.
		p.payBank(50, this);
		p.setJailStatus(0);
	    }
	    movePlayer(p, roll);
	}
	
	// Now move on to the next player.
	if (p == player4)
	    currentPlayer = 0;
	else
	    currentPlayer++;

	updateDisplay();
    }

    /** Move the given Player p forward numCells (where numCells is # of 
     *  board cells, not # of positions) on the list positions ON THE GUI. */
    // TODO: This logic should be moved into the Player class. This stuff has nothing to 
    //       do with the GUI. When the player moves, I need to check where (s)he wants to
    //       move and do actions accordingly. Currently, I'm just blindly moving the Player 
    //       to a given spot.

    // TODO: A lot of this method could be replaced by the whole onLand() method idea for
    //       BoardCells.
    // TODO: Review usage of setCell() in all files. In several cases I seem to be manually
    //       moving the player with that, rather than calling this method and having the details
    //       figured out.
    public void movePlayer(Player p, int numCells) {
	int landingSpot = p.getIndex() + numCells;

	// Check for valid input first.
	// TODO: Do I even need these? It's possible to move backwards.
	if (numCells <= 0) {
	    System.out.println("Can't move 0 spaces or backwards!");
	}

	else if (numCells > 6) {
	    System.out.println("You can move at most 6 spaces.");
	}

	else {
	    // Here the Player is moving, so set the label at p's
	    // current position to no player present.
	    p.getCell().setPositionImage(p, new ImageIcon("images/noPlayerPresent.jpg"), this);

	    // The player is about to land on/overshoot Go.
	    if (landingSpot > 39) {
		// TODO: Try to clarify what's happening here. Could probably simplify it.
		// TODO: If circular linked list works, this conditional should be unneeded.
		p.setCell((landingSpot - 40), this);
		p.addCash("hundreds", 2); // Give 200 bucks for passing Go.
	    }

	    // Player lands on Go to Jail.
	    // TODO: I seem to be getting a bug where, after landing on Go to Jail,
	    //       Buy Property will light up for the property player1 would have
	    //       landed on, if (s)he had been allowed to roll. Then I get an error,
	    //       because I try to buy that property, which uses player1's current cell
	    //       (which is Jail, which is a SpecialCell), and you can't buy SpecialCells.
	    else if (landingSpot == 30)
		p.putInJail(this);

	    // Player lands on a Community Chest card.
	    // TODO: For Chance and CommChest, do I need to have the Player
	    //       changeCell() before popping the card off?
	    else if (landingSpot == 2 || landingSpot == 17 || landingSpot == 33)
		commChestCards.pop().doActions(p, this);
	    
	    // Player lands on a Chance card.
	    else if (landingSpot == 7 || landingSpot == 22 || landingSpot == 36)
		chanceCards.pop().doActions(p, this);

	    // TODO: Allow the Player to choose 10% or $200, or do the cheapest automatically.
	    // Player lands on Income Tax.
	    // TODO: Test to make sure this works.
	    else if (landingSpot == 4)
		p.payBank(200, this);

	    // Luxury tax.
	    // TODO: Test to make sure this works.
	    else if (landingSpot == 38)
		p.payBank(75, this);

	    // TODO: Possibly redo this to take advantage of p.getCell().getClass()
	    // rather than using landingSpot numbers.

	    // Free parking
	    else if (landingSpot == 20) {
		if (p == player1) 
		    printStatusAndLog("Free parking!");
	    }

	    // In jail (just visiting)
	    else if (landingSpot == 10) {
		if (p == player1)
		    printStatusAndLog("In jail - but just visiting!");
	    }

	    // Regular move. Here the Player should have not overshot Go or landed on any
	    // position-changing cells (Go to Jail, Chance, etc.). The Player has landed on
	    // an ownable BoardCell.
	    else {
		p.setCell(landingSpot, this);

		// TODO: The below block of stuff looks like things to do post-moving a player to a cell.
		//       Move it all into a new method?
		// No player currently owns the property.			
		if (boardProperties[landingSpot].getOwner() == null ) {

		    // Allow player to buy the property.
		    if (p == player1) {
			// TODO: Have buyProperty disable itself after the player presses "Continue",
			//       so they can't buy the property when it's not their turn?
			//       Also, if I don't want to do that, disable it if another player buys
			//       it or lands on the cell.
			//       Also, have the text for the property name disappear when the button
			//       is disabled.
			buyProperty.setText("Buy property (" + boardProperties[landingSpot].getName() + ")");  
			buyProperty.setEnabled(true);
		    }

		    // Have the AI buy the property if it has more than $500.
		    else {
			if (p.getCash("total") >= 500) {
			    if (   (p.getCell() instanceof  PropagandaOutlet)
			        || (p.getCell() instanceof  Railroad)
                                || (p.getCell() instanceof  UtilityCell)) {
				Ownable cell = (Ownable) p.getCell();
				if (cell.isOwned() == false)
				    cell.buy(p, this);
			    }
			}
		    }
		}
			
		// A player owns the property.
		else {
		    printStatusAndLog(p.getName() + " pays " + p.getCell().getOwner().getName() + "$" + Integer.toString(p.getCell().getRent()));

		    // Disable button when the human player lands on an owned property.
		    if (p == player1)
			buyProperty.setEnabled(false);
			    
		    // Charge the player appropriately.
		    // TODO: Can I use landingSpot rather than p.getCell().getOwner() ?
		    p.payPlayer(p.getCell().getOwner(), p.getCell().getRent(), this);
		}

		// TODO: Make BoardCells and such clickable. That way, I can easily implement
		// property selling, mortgaging, house/hotel buying.
	    }

	    p.getCell().setPositionImage(p, p.getImage(), this);
	}
    }

    /** Update the GUI elements that display player cash values
     *  and the text indicating the current player. */
    public void updateDisplay() {
	// TODO: Replace with foreach?
	// TODO: This same code's used earlier.
	for (int i = 0; i <= 3; i++) {
	    for (int j = 0; j <= 9; j++) {
		if (j == 9)
		    cashLabels[i][j].setText(Integer.toString(players[i].getJailStatus()));
		else if (j == 8)
		    cashLabels[i][j].setText(Integer.toString(players[i].getNumGOOJFCards()));
		else
		    cashLabels[i][j].setText(Integer.toString(players[i].getCash(cashValues[j])));
	    }

	    // Set all player labels to black
	    playerRowLabels[i].setForeground(Color.BLACK);
	}

	// ...then set the current player to green.
	if (currentPlayer >= 1 && currentPlayer <= 3)
	    playerRowLabels[currentPlayer - 1].setForeground(Color.GREEN);
	else if (currentPlayer == 0)
	    playerRowLabels[3].setForeground(Color.GREEN);
    }

    /** Perform actions depending on GUI events. */
    @Override
    public void actionPerformed(ActionEvent e) {
	String eventSource = e.getActionCommand();

	if (eventSource == "Continue") {
	    doTurn(frame, players[currentPlayer]);
	    // TODO: Make sure I don't need a bankruptcy check for this event.
            //       Shouldn't, because button's only highlighted when the Player can buy.
            //       Also add plenty of tests for this.
	}

	else if (eventSource.substring(0, 12).equals("Buy property")) {
	    // TODO: Possible to do this more cleanly?
	    Ownable cell = (Ownable) player1.getCell();
	    cell.buy(player1, this);
	    buyProperty.setEnabled(false); // Disable button after property's bought.
	}

	else if (eventSource == "Buy house") {
	    System.out.println("Testing buy house.");
	}

	else if (eventSource == "Buy hotel") {
	    System.out.println("Testing buy hotel.");
	}

	else if (eventSource == "Sell property") {
	    System.out.println("Testing sell property.");
	}

	else if (eventSource == "Mortgage property") {
	    System.out.println("Testing mortgage property.");
	}

	else if (eventSource == "Use get out of jail free card") {
	    player1.spendGOOJF(this);
	    useGOOJFCard.setEnabled(false);
	    updateDisplay();
	}

	else {
	    System.out.println("Received an action from an unknown source.");
	}

	updateDisplay();
    }

    /** Given an integer i, break it up into the smallest possible amount of bills. */
    // TODO: Can/should this be public? And this seems like it should maybe be associated with
    //       a Player object instead? Or similar?
    public int[] getCashDistribution(int total) {
	/* TODO: This functionality seems so generic that it should be in a library somewhere...
	   TODO: This is almost working, but I think BoardCell's getCost() function
	   is not being overwritten by PropagandaOutlet's => since BoardCell always returns 
	   0, I get this case where total = 0, which screws up gameplay. */
	if (total <= 0) {
	    printStatusAndLog("Can't break up 0 or negative dollars!");

	    // Since we can't break up this amount of money, the amount of each bill to pay is 0.
	    // TODO: Convert this to a for-each loop or similar.
	    for (int i = 0; i <= 6; i++)
	    	paymentAmounts[i] = 0;
	}

	else {
	    /* We keep the arrays in reverse order here. This way, when we iterate
	       through them, we start with the highest dollar amount, which leads to
	       less dividing up the player's money into (eg) hundreds of little bills.
	       TODO: Ambiguous, confusing names here. */
	    /** billValues is the amount, in dollars, of bills that go into a given bill type. */
	    int[] billValues  = {500, 100, 50, 20, 10, 5, 1};

	    /** billTotals is the number of each bill type I have. */
	    int[] billTotals  = {0, 0, 0, 0, 0, 0, 0};
	    // TODO: Do I need this billTotals array? Could I just store values 
	    //       directly in paymentAmounts[] instead?

	    int i = 0;
	    for (int amount : billValues) {
	    	if (total / amount != 0) {
	    	    billTotals[i] = total / amount;
	    	    total -= (amount * billTotals[i]);
	    	}
	    	i++;
	    }

	    // Then have the player pay each of the amounts
	    // TODO: Again, replace with a for each loop.
	    for (int j = 0; j <= 6; j++)
		paymentAmounts[j] = billTotals[6 - j];
	}

	return paymentAmounts;
    }

    /** Given some text, output that text into the messages pane
     *  and append that message to the log file, with a time stamp. */
    // TODO: This isn't logging the stuff currently...
    // TODO: I can do System.err.println(stuff), and then
    //       in run_program, redirect my stderr output to a log file.
    // IE: System.err.println("Player 1 pays Player 2 $10.");
    //     run_program:
    //     java -cp ~/Programming/Java/Ideopoly/bin/ Menu 2> log.txt

    // ...although that might not work on Windows...
    public void printStatusAndLog(String text) {
	messages.append("\n" + text);
	// Setting the caret position is required to make the messages pane scroll.
	// See https://tips4java.wordpress.com/2008/10/22/text-area-scrolling/
	messages.setCaretPosition(messages.getDocument().getLength());
	// fout.append(text);
	// fout.close();

	//	fout = new FileWriter("log.txt");
	//    private FileWriter fout;
    }

    // TODO: Better, more descriptive names for these methods, 
    // and the corresponding variables.
    /** Set the background color for detailed property info. */
    public void setGUIColor(Color c) {
    	guiColor.setBackground(c);
    }
    /** Set the text for detailed property info. */
    public void setGUIName(String t) {
    	guiName.setText(t);
    }
    /** Set the cost text for detailed property info. */
    public void setGUICost(String t) {
    	guiCost.setText(t);
    }
    /** Set the house/hotel cost text for detailed property info. */
    public void setGUIHouseHotelCost(String t) {
    	guiHouseHotelCost.setText(t);
    }
    /** Set the rent text for detailed property info. */
    public void setGUIRent(String t) {
    	guiRent.setText(t);
    }
    /** Set the 1 house cost text for detailed property info. */
    public void setGUI1House(String t) {
    	gui1House.setText(t);
    }
    /** Set the 2 houses cost text for detailed property info. */
    public void setGUI2House(String t) {
    	gui2House.setText(t);
    }
    /** Set the 3 houses cost text for detailed property info. */
    public void setGUI3House(String t) {
    	gui3House.setText(t);
    }
    /** Set the 4 houses cost text for detailed property info. */
    public void setGUI4House(String t) {
    	gui4House.setText(t);
    }
    /** Set the hotel cost text for detailed property info. */
    public void setGUIHotel(String t) {
    	guiHotel.setText(t);
    }
    /** Set the mortgage value for detailed property info. */
    public void setGUIMortgage(String t) {
    	guiMortgage.setText(t);
    }

    /** Helper method to, given a GridBagConstraints object gbc, 
     *  set its gridx and gridx values to x and y. */
    // TODO: Add the step of adding the stuff to the frame here?
    // TODO: Add tests.
    private void setConstraintsXY(GridBagConstraints gbc, int x, int y) {
	gbc.gridx = x;
	gbc.gridy = y;
    }
}