import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.util.Stack;

// TODO: Add in Chance and Comm. Chest images.
// TODO: Use the native look and feel for the program.
// TODO: Keep using M-a/M-e. Useful.
// TODO: Make an updateStatus() function that, given a string as an input,
//       updates the status text at bottom of screen.
// TODO: Remove unneeded image templates.
// TODO: Get rid of the spacing between cells.
// TODO: Make text on cells readable.

/** Class to contain the GUI of the game board. A new instance of the class
 *  creates the GUI and sets up various things in it. The game takes place mostly
 *  inside this GUI - the others are just there to get ya to the point where you
 *  can play the game.
 *
 *  @author Daniel Neel */

public class IdeopolyGUI implements ActionListener {
    // TODO: Make some of these arrays, etc., final/static when they're constant. Also see
    // if it's possible to get rid of some of them.
    /** Number to represent if the game has been won. Once the game's won, 
     *  this switches to another value. */
    private int gameWon = 0;

    // TODO: Try to reduce usage of this players array. Is useless and confusing except when looping.
    private String   cashValues[]	 = { "ones", "fives", "tens", "twenties", "fifties", "hundreds", "fiveHundreds", "total"};
    private String   titles[] = { "Cash", "1s", "5s", "10s", "20s", "50s", "100s", "500s", "Total", "GOOJF cards", "Turns left in jail" };
    private JLabel[] playerRowLabels = { new JLabel("Player 1"), 
					 new JLabel("Player 2"), 
					 new JLabel("Player 3"), 
					 new JLabel("Player 4") };

    /* TODO: Consider making these values constant, since they won't change.
       IE: static final int ROWS = 4
       static final int COLS = 7
       JLabel[][] cashLabels = new JLabel[ROWS][COLS]; */
    private CashCell[][] cashLabels	 = new CashCell[4][10];

    /** Array used to store the values of each type of bill a Player should pay after requiring a payment. */
    private int[]       paymentAmounts = {0, 0, 0, 0, 0, 0, 0};

    private JFrame	frame			= new JFrame("Ideopoly | Main game");

    private JButton	continueButton		= new JButton("Continue");
    // TODO: Would be good to list the property name, such as "Buy property (Mediterranean Avenue)"
    private JButton	buyProperty		= new JButton("Buy property");
    private JButton	buyHouse		= new JButton("Buy house");
    private JButton	buyHotel		= new JButton("Buy hotel");
    private JButton	sellProperty		= new JButton("Sell property");
    private JButton	mortgageProperty	= new JButton("Mortgage property");
    // TODO: Should I make this private, and include a function to set its enable-ability? The Player
    // class is currently the only outside class that changes its state.
    public  JButton	useGOOJFCard		= new JButton("Use get out of jail free card");
    private JLabel      status                  = new JLabel ("Status:");

    // Create the game board.
    private Icon             darkBlue        = new ImageIcon       ("images/darkBlueTemplate.jpg");
    private Icon             green           = new ImageIcon       ("images/greenTemplate.jpg");
    private Icon             lightBlue       = new ImageIcon       ("images/lightBlueTemplate.jpg");
    private Icon             orange          = new ImageIcon       ("images/orangeTemplate.jpg");
    private Icon             pink            = new ImageIcon       ("images/pinkTemplate.jpg");
    private Icon             purple          = new ImageIcon       ("images/purpleTemplate.jpg");

    private Icon             luxTax          = new ImageIcon       ("images/luxuryTax.jpg");
    // TODO: Redo this image. Just re-download the version from email and add border. Had resized it incorrectly.
    private Icon             incTax          = new ImageIcon       ("images/incomeTax.jpg");

    // TODO: Is it possible to do this without the lengthily declaring variables for each?
    // TODO: Possible to get rid of the images prefix?

    private BoardCell	     go              = new BoardCell       ("Go", new ImageIcon("images/go.jpg"), 41, 41);
    private PropagandaOutlet mediterraneanAv = new PropagandaOutlet("Mediterranean Av.", purple, 60, 2, 10, 30, 90, 160, 250, 50, 37, 41);
    private BoardCell        commChestBottom = new BoardCell       ("Community Chest", new ImageIcon("images/bottCommChest.jpg"), 33, 41);
    private PropagandaOutlet balticAv        = new PropagandaOutlet("Baltic Av.", purple, 60, 4, 20, 60, 180, 320, 450, 50, 29, 41);
    private BoardCell	     incomeTax       = new BoardCell       ("Income Tax", incTax, 25, 41);
    // TODO: Bigger font size (~80) for the text on railroads - hard to read currently.
    private Railroad	     readingRR       = new Railroad        ("Reading RR", new ImageIcon("images/readingRR.jpg"), 21, 41);
    private PropagandaOutlet orientalAv      = new PropagandaOutlet("Oriental Av.", lightBlue, 100, 6, 30, 90, 270, 400, 550, 50, 17, 41);
    private BoardCell	     chanceBottom    = new BoardCell       ("Chance", new ImageIcon("images/botChance.jpg"), 13, 41);
    private PropagandaOutlet vermontAv       = new PropagandaOutlet("Vermont Av.", lightBlue, 100, 6, 30, 90, 270, 400, 550, 50, 9, 41);
    private PropagandaOutlet connecticutAv   = new PropagandaOutlet("Connecticut Av.", lightBlue, 120, 8, 40, 100, 300, 450, 600, 50, 5, 41);
    private BoardCell	     jail	     = new BoardCell       ("Jail", new ImageIcon("images/jail.jpg"), 1, 41);
    private PropagandaOutlet stCharles       = new PropagandaOutlet("St. Charles Place", pink, 140, 10, 50, 150, 450, 625, 750, 100, 1, 37);
    private BoardCell	     electricCompany = new BoardCell       ("Electric Co.", new ImageIcon("images/electricCo.jpg"), 1, 33);
    private PropagandaOutlet statesAv	     = new PropagandaOutlet("States Av.", pink, 140, 10, 50, 150, 450, 625, 750, 100, 1, 29);
    private PropagandaOutlet virginiaAv      = new PropagandaOutlet("Virginia Av.", pink, 160, 12, 60, 180, 500, 700, 900, 100, 1, 25);
    private Railroad	     pennsylvaniaRR  = new Railroad        ("Pennsylvania RR", new ImageIcon("images/pennsylvaniaRR.jpg"), 1, 21);
    private PropagandaOutlet stJames         = new PropagandaOutlet("St. James", orange, 180, 14, 70, 200, 550, 750, 950, 100, 1, 17);
    private BoardCell	     commChestLeft   = new BoardCell       ("Community Chest", new ImageIcon("images/leftCommChest.jpg"), 1, 13);
    private PropagandaOutlet tennesseeAv     = new PropagandaOutlet("Tennessee Av.", orange, 180, 14, 70, 200, 550, 750, 950, 100, 1, 9);
    private PropagandaOutlet newYorkAv       = new PropagandaOutlet("New York Av.", orange, 200, 16, 80, 220, 600, 800, 1000, 100, 1, 5);
    private BoardCell	     freeParking     = new BoardCell       ("Free Parking", new ImageIcon("images/freeParking.jpg"), 1, 1);
    private PropagandaOutlet kentuckyAv      = new PropagandaOutlet("Kentucky Av.", new ImageIcon("images/properties/kentuckyAv.jpg"), 220, 18, 90, 250, 700, 875, 1050, 150, 5, 1);
    private BoardCell	     chanceTop       = new BoardCell       ("Chance", new ImageIcon("images/topChance.jpg"), 9, 1);
    private PropagandaOutlet indianaAv	     = new PropagandaOutlet("Indiana Av.", new ImageIcon("images/properties/indianaAv.jpg"), 220, 18, 90, 250, 700, 875, 1050, 150, 13, 1);
    private PropagandaOutlet illinoisAv      = new PropagandaOutlet("Illinois Av.", new ImageIcon("images/properties/illinoisAv.jpg"), 240, 20, 100, 300, 750, 925, 1100, 150, 17, 1);
    private Railroad	     bAndORR	     = new Railroad        ("B & O RR", new ImageIcon("images/bAndORR.jpg"), 21, 1);
    private PropagandaOutlet atlanticAv      = new PropagandaOutlet("Atlantic Av.", new ImageIcon("images/properties/atlanticAv.jpg"), 260, 22, 110, 330, 800, 975, 1150, 1, 25, 1);
    private PropagandaOutlet ventnorAv	     = new PropagandaOutlet("Ventnor Av.", new ImageIcon("images/properties/ventnorAv.jpg"), 260, 22, 110, 330, 800, 975, 1150, 150, 29, 1);
    private BoardCell	     waterWorks      = new BoardCell       ("Water Works", new ImageIcon("images/waterWorks.jpg"), 33, 1);
    private PropagandaOutlet marvinGardens   = new PropagandaOutlet("Marvin Gardens", new ImageIcon("images/properties/marvinGardens.jpg"), 280, 24, 120, 360, 850, 1025, 1200, 150, 37, 1);
    private BoardCell	     goToJail        = new BoardCell       ("Go to Jail", new ImageIcon("images/goToJail.jpg"), 41, 1);
    private PropagandaOutlet pacificAv	     = new PropagandaOutlet("Pacific Av.", green, 300, 26, 130, 390, 900, 1100, 1275, 200, 41, 5);
    private PropagandaOutlet nCarolinaAv     = new PropagandaOutlet("nCarolinaAv", green, 300, 26, 130, 390, 900, 1100, 1275, 200, 41, 9);
    private BoardCell	     commChestRight  = new BoardCell       ("Community Chest", new ImageIcon("images/rightCommChest.jpg"), 41, 13);
    private PropagandaOutlet pennsylvaniaAv  = new PropagandaOutlet("Pennsylvania Av.", green, 320, 28, 150, 450, 1000, 1200, 1400, 200, 41, 17);
    private Railroad         shortLineRR     = new Railroad        ("Short Line RR", new ImageIcon("images/shortLineRR.jpg"), 41, 21);
    private BoardCell        chanceRight     = new BoardCell       ("Chance", new ImageIcon("images/rightChance.jpg"), 41, 25);
    private PropagandaOutlet parkPlace       = new PropagandaOutlet("Park Place", darkBlue, 350, 35, 175, 500, 1100, 1300, 1500, 200, 41, 29);
    private BoardCell	     luxuryTax       = new BoardCell       ("Luxury Tax", luxTax, 41, 33);
    private PropagandaOutlet boardwalk       = new PropagandaOutlet("Boardwalk", darkBlue, 400, 50, 200, 600, 1400, 1700, 2000, 200, 41, 37);

    // TODO: Make this a vector, or similar, so I can insert multiple types of objects. 
    // This will solve the later problem of not being able to use getCashDistribution. 
    // That method can only be called on PropagandaOutlets, but this situation turns all
    // Prop Outlets into BoardCells. => if I solve this problem, that one should disappear. And
    // hundreds more should surface.
    // TODO: Get actionlisteners implemented for the properties, so that I can mouseover them and have a picture of the property card pop up in the middle of the board.

    // TODO: Consider making this an enum. Enumerations are apparently a fixed list of constants,
    //       and that's what this array is.
    // TODO: Also, see file:///home/daniel/Desktop/aaa-TIJ3-distribution/TIJ3.htm and then
    //       "Downcasting vs. templates/generics" for how to get the original class back out
    //       of this array. This should help solve some problems I was having before. See above TODO.
    final BoardCell boardProperties[] = { go, mediterraneanAv, commChestBottom, balticAv, incomeTax, readingRR, orientalAv, chanceBottom, vermontAv, connecticutAv, jail, stCharles, electricCompany, statesAv, virginiaAv, pennsylvaniaRR, stJames, commChestLeft, tennesseeAv, newYorkAv, freeParking, kentuckyAv, chanceTop, indianaAv, illinoisAv, bAndORR, atlanticAv, ventnorAv, waterWorks, marvinGardens, goToJail, pacificAv, nCarolinaAv, commChestRight, pennsylvaniaAv, shortLineRR, chanceRight, parkPlace, luxuryTax, boardwalk }; // The game board is represented as an array of BoardCells

    /** Represents the player whose turn it currently is to roll. 0-3. */
    private int currentPlayer = 0;

    public  Player   player1 = new Player(1, this);
    public  Player   player2 = new Player(2, this);
    public  Player   player3 = new Player(3, this);
    public  Player   player4 = new Player(4, this);
    private Player   players[]       = { player1, player2, player3, player4 };
    private int      playersArrSize  = 3;

    /** The stack of Chance cards. */
    private Stack<Chance>         chanceCards    = new Stack<Chance>();

    /** The stack of Community Chest cards. */
    private Stack<CommunityChest> commChestCards = new Stack<CommunityChest>();

    /** Constructor creates the GUI, sets up parts of it, etc. */
    public IdeopolyGUI(String playerCharacter) { // TODO: Split up the functions logically.
	// Create the gui.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// TODO: Use multiple instances of GridBagConstraints?
	frame.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	// Align all components in the "top-right" of its display area.
	c.anchor = GridBagConstraints.FIRST_LINE_START;

	c.gridwidth  = 4;
	c.gridheight = 4;

	// For every cell on the board, add its image.
	for (BoardCell cell : boardProperties) {
	    c.gridx = cell.getX();
	    c.gridy = cell.getY();
	    JPanel panel = new JPanel();
	    panel.add(new JLabel(cell.getImage()));
	    
	    frame.add(panel, c);
	}

	c.gridwidth  = 1;
	c.gridheight = 1;

	// ================================================================
	// === Add the standing positions around the edge of the board. ===
	// ================================================================
	for (BoardCell property : boardProperties) {
	    c.gridx = property.p1Pos.getXCoord();
	    c.gridy = property.p1Pos.getYCoord();
	    frame.add(property.p1Pos, c);

	    c.gridx = property.p2Pos.getXCoord();
	    c.gridy = property.p2Pos.getYCoord();
	    frame.add(property.p2Pos, c);

	    c.gridx = property.p3Pos.getXCoord();
	    c.gridy = property.p3Pos.getYCoord();
	    frame.add(property.p3Pos, c);

	    c.gridx = property.p4Pos.getXCoord();
	    c.gridy = property.p4Pos.getYCoord();
	    frame.add(property.p4Pos, c);
	}

	// TODO: Rename this propaganda outlets rather than properties? Confusing?
	// TODO: Can I fix the icons to remove duplicate code?

	// === Create labels to display each player's cash. ===
	c.gridx = 49;
	c.gridy = 0;

	// Display the column titles.
	for (String s : titles) {
	    c.gridx++;
	    frame.add(new JLabel(s), c);
	}

	// Display Player 1/2/3/4: to the left of the rows.
	c.gridx = 50;

	for (JLabel j : playerRowLabels) {
	    c.gridy++;
	    frame.add(j, c);
	}

	// Initialize the array of labels.
	// TODO: Replace this with a foreach loop. Possible with the players[i] part?
	for (int i = 0; i <= 3; i++) { // Iterate through each row.
	    for (int j = 0; j <= 9; j++) { // And through each column.
		// Add get out of jail free cards, rather than cash, in the 8th column.
		// TODO: I should be able to reduce the code in this block a bit. A little tricky, doable.
		if (j == 9)
		    cashLabels[i][j] = new CashCell(51 + j, 1 + i, Integer.toString(players[i].getJailStatus()) );
		else if (j == 8)
		    cashLabels[i][j] = new CashCell(51 + j, 1 + i, Integer.toString(players[i].getNumGOOJFCards()) );
		else
		    cashLabels[i][j] = new CashCell(51 + j, 1 + i, players[i].getCash( cashValues[j] ));
	    }
	}

	// Then add all the labels to the board.
	for (CashCell[] outer : cashLabels) {
	    for (CashCell cc : outer) {
		c.gridx = cc.getXCoord();
		c.gridy = cc.getYCoord();
		frame.add(cc, c);
	    }
	}

	// Add a Continue button in the middle of the board.
	c.gridwidth  = 7;
	c.gridheight = 2;
	c.gridx      = 22;
	c.gridy      = 22;
	frame.add(continueButton, c);

	// Add all other buttons to the right side of the board.
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
	c.gridx     = 50;
	c.gridy     = 6;
	c.gridwidth = 9;
	frame.add(buyProperty, c);

	c.gridwidth = 5;
	c.gridy     = 8;
	frame.add(buyHouse, c);

	c.gridy = 9;
	frame.add(buyHotel, c);

	// Right column.
	c.gridwidth = 4;
	c.gridy	    = 8;
	c.gridx     = 55;
	frame.add(sellProperty, c);

	c.gridy = 9;
	frame.add(mortgageProperty, c);


	c.gridx     = 50;
	c.gridy     = 11;
	c.gridwidth = 9;
	frame.add(useGOOJFCard, c);

	// Status bar.
	c.gridx       = 50;
	c.gridy       = 43;
	c.gridwidth   = 1;
	frame.add(status, c);

	// c.gridx      = 51;
	// c.gridheight = 4;
	// JTextArea messages =  new JTextArea("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", 5, 5);
	//	messages.setLineWrap(true);
	//	frame.add(messages, c);

	Random generator = new Random();
	int cardType; // Controls the type of Chance/Community Chest card made.

	// Make a large stack of cards to use for the game.
	for (int i = 0; i < 200; i++) {
	    cardType = generator.nextInt(16) + 1;
	    chanceCards.push(new Chance(cardType));

	    cardType = generator.nextInt(17) + 1;
	    commChestCards.push(new CommunityChest(cardType));
	}
	// TODO: This should happen automatically - I shouldn't need to set images manually.
	boardProperties[0].p1Pos.setImage(player1.getImage());
	boardProperties[0].p2Pos.setImage(player2.getImage());
	boardProperties[0].p3Pos.setImage(player3.getImage());
	boardProperties[0].p4Pos.setImage(player4.getImage());

	frame.pack();
	frame.setVisible(true);

	System.out.println("You picked " + playerCharacter);
    }

    /** Do a turn's worth of gameplay. First the player rolls/moves. 
     *  Then cash is exchanged. Then players can buy/trade, etc.
     *  Return a 0 if nobody has won yet, or a 1 if someone won the game. */
    private void doTurn( JFrame frame) {
	Random rollGenerator = new Random();
	int roll;
	// TODO: Add IE: "Player 1 rolled 7." to status messages after each roll.
	// Later feature: Add all those status messages to a log file players can review.
	//x.setEnabled(true/false)

	    // Roll a new random number between 1 and 6.
	    roll = rollGenerator.nextInt(6) + 1;

	    // ==============================
	    // === First move the player. ===
	    // ==============================

	    // First week in jail.
	    if ( players[currentPlayer].getJailStatus() == 3 ) {
		players[currentPlayer].setJailStatus(2);
	    }

	    // Second week in jail.
	    else if ( players[currentPlayer].getJailStatus() == 2 ) {
		players[currentPlayer].setJailStatus(1);
	    }

	    // Last week in jail. Player gets charged $50, then moves forward.
	    else if ( players[currentPlayer].getJailStatus() == 1 ) {
		if (players[currentPlayer].willBankrupt(50)) {
		    players[currentPlayer].bankruptPlayer();
		}
		else {
		    players[currentPlayer].spreadCash(50);
		    players[currentPlayer].addCash("fifties", -1);
		    players[currentPlayer].spreadCash(500);
		    players[currentPlayer].setJailStatus(0);

		    if (currentPlayer == 0)
			movePlayer(player1, player2, player3, player4, roll);
		    else if (currentPlayer == 1)
			movePlayer(player2, player1, player3, player4, roll);
		    else if (currentPlayer == 2)
			movePlayer(player3, player2, player1, player4, roll);
		    else if (currentPlayer == 3)
			movePlayer(player4, player2, player3, player1, roll);
		}
	    }

	    // Not in jail, so move the player as normal.
	    else {
		// TODO: This is repeated right above.
		if (currentPlayer == 0) {
		    movePlayer(player1, player2, player3, player4, roll);
		}
		else if (currentPlayer == 1) {
		    movePlayer(player2, player1, player3, player4, roll);
		}
		else if (currentPlayer == 2) {
		    movePlayer(player3, player2, player1, player4, roll);
		}
		else if (currentPlayer == 3) {
		    movePlayer(player4, player2, player3, player1, roll);
		}
	    }

	    // =====================================================
	    // === Then allow the player to make moves and such. ===
	    // =====================================================
	    // ....

	    // Perform all the above actions, then move to the next player.
	    if (currentPlayer == playersArrSize)
		currentPlayer = 0;
	    else
		currentPlayer++;

	    updateDisplay();
    }

    /** Move the given Player p forward numCells (where numCells is # of 
     *  board cells, not # of positions) on the list positions ON THE GUI.
     *  Also include players p2/3/4, because it's possible to affect them
     *  by moving a player. */
    // TODO: Do I need the extra arguments here? Should be able to just provide p1 
    // and derive the rest later

    // TODO: This logic should be moved into the Player class. This stuff has nothing to 
    //       do with the GUI. When the player moves, I need to check where (s)he wants to
    //       move and do actions accordingly. Currently, I'm just blindly moving the Player 
    //       to a given spot.
    public void movePlayer(Player p, Player p2, Player p3, Player p4, int numCells) {
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
	    // Is the player currently in jail? If so, do a different execution path.
	    if (p.getJailStatus() != 0) {
		// TODO: Implement all this stuff. See notes file for ideas.

		// /** Player's jail status.
		//  *  0 = not in jail.
		//  *  1 = last week in jail.
		//  *  2 = second week in jail.
		//  *  3 = first week in jail. */
	    }

	    else { // Player's not in jail, so they're free to move around..

		// Get the label at the player's current position,
		// set it to no player present (since we're moving the player).
		// TODO: Shouldn't need this big conditional statement to do this.
		if (p == player1)
		    p.getCell().p1Pos.setImage(new ImageIcon("images/noPlayerPresent.jpg"));
		else if (p == player2)
		    p.getCell().p2Pos.setImage(new ImageIcon("images/noPlayerPresent.jpg"));
		else if (p == player3)
		    p.getCell().p3Pos.setImage(new ImageIcon("images/noPlayerPresent.jpg"));
		else if (p == player4)
		    p.getCell().p4Pos.setImage(new ImageIcon("images/noPlayerPresent.jpg"));

		// The player is about to land on/overshoot Go.
		// TODO: Test to ensure that this is the correct cell. I think it's right but not positive.
		// TODO: Could I just remove p.getIndex() >= 34 and have the same thing?
		if (landingSpot > 39) {
		    // TODO: Try to clarify what's happening here. Could probably simplify it.
		    // TODO: If circular linked list works, this conditional should be unneeded.
		    p.changeCell((landingSpot - 40), this);
		    p.addCash("hundreds", 2); // Give 200 bucks for passing Go.
		}

		// Player lands on Go to Jail.
		else if (landingSpot == 30) {
		    p.putInJail(this);
		}

		// Player lands on a Community Chest card.
		else if (landingSpot == 2 || landingSpot == 17 || landingSpot == 33) {
		    CommunityChest card = commChestCards.pop();
		    card.doActions(p, this, p2, p3, p4);
		}
	    
		// Player lands on a Chance card.
		else if (landingSpot == 7 || landingSpot == 22 || landingSpot == 36) {
		    Chance card = chanceCards.pop();
		    card.doActions(p, this, p2, p3, p4);
		}

		// TODO: Allow the Player to choose 10% or $200, or do the cheapest automatically.
		// Player lands on Income Tax.
		else if (landingSpot == 4) {
		    if (p.willBankrupt(200))
			p.bankruptPlayer();
		    else {
			p.spreadCash(100);
			p.addCash("hundreds", -2);
			p.spreadCash(500);
		    }
		}

		// Regular move - not overshooting Go, landing on Go to Jail, or
		// a Community Chest/Chance card. 

		// TODO: Make this so that the above checks to see if I land on a non-ownable property.
		// So if I land on an ownable property, there shouldn't be a need for the following big
		// conditional and such.
		// The other non-ownable properties are: In Jail, Free Parking, and Luxury Tax.
		// LEFTOFFHERE: Working on the above stuff.
		else {
		    p.changeCell(landingSpot, this);

		    // TODO: Make a separate method to handle this?
		    // TODO: Instead, just check for class. If is PropOutlet/RR/Utility, do x.
		    // And if that doesn't work, could have a field that indicates type of property.
		    if (   players[currentPlayer].getCell() == mediterraneanAv
                        || players[currentPlayer].getCell() == balticAv
                        || players[currentPlayer].getCell() == orientalAv
			|| players[currentPlayer].getCell() == vermontAv
			|| players[currentPlayer].getCell() == connecticutAv
			|| players[currentPlayer].getCell() == stCharles
			|| players[currentPlayer].getCell() == statesAv
			|| players[currentPlayer].getCell() == virginiaAv
			|| players[currentPlayer].getCell() == stJames
			|| players[currentPlayer].getCell() == tennesseeAv
			|| players[currentPlayer].getCell() == newYorkAv
			|| players[currentPlayer].getCell() == kentuckyAv
			|| players[currentPlayer].getCell() == indianaAv
			|| players[currentPlayer].getCell() == illinoisAv
			|| players[currentPlayer].getCell() == atlanticAv
			|| players[currentPlayer].getCell() == ventnorAv
			|| players[currentPlayer].getCell() == marvinGardens
			|| players[currentPlayer].getCell() == pacificAv
			|| players[currentPlayer].getCell() == nCarolinaAv
			|| players[currentPlayer].getCell() == pennsylvaniaAv
			|| players[currentPlayer].getCell() == parkPlace
			|| players[currentPlayer].getCell() == boardwalk

			|| players[currentPlayer].getCell() == readingRR
			|| players[currentPlayer].getCell() == pennsylvaniaRR
			|| players[currentPlayer].getCell() == bAndORR
			|| players[currentPlayer].getCell() == shortLineRR

                        || players[currentPlayer].getCell() == waterWorks
     			|| players[currentPlayer].getCell() == electricCompany) {

			// No player currently owns the property.			
			if ( players[currentPlayer].getCell().getOwner() == null ) {

			    // Allow player to buy the property.
			    if (players[currentPlayer] == player1) {
				// TODO: This is nice to have, but it screws up my later switch statement.
				// buyProperty.setText("Buy property (" + getCurrentLocation(players[currentPlayer]).getName() + ")");
				buyProperty.setEnabled(true);
			    }

			    // Have the AI buy the property if it has more than $500.
			    else {
				if (Integer.parseInt(players[currentPlayer].getCash("total")) >= 500) {
				    players[currentPlayer].getCell().setOwner(players[currentPlayer]);

				    getCashDistribution(players[currentPlayer].getCell().getCost());
				    System.out.println(players[currentPlayer].getCell().getCost() );

				    // Then, for each bill, transfer the correct amount from p1 to p2.
				    players[currentPlayer].spreadCash(1);
				    players[currentPlayer].addCash("ones", - paymentAmounts[0]);

				    players[currentPlayer].spreadCash(5);
				    players[currentPlayer].addCash("fives", - paymentAmounts[1]);

				    players[currentPlayer].spreadCash(10);
				    players[currentPlayer].addCash("tens", - paymentAmounts[2]);

				    players[currentPlayer].spreadCash(20);
				    players[currentPlayer].addCash("twenties", - paymentAmounts[3]);

				    players[currentPlayer].spreadCash(50);
				    players[currentPlayer].addCash("fifties", - paymentAmounts[4]);

				    players[currentPlayer].spreadCash(100);
				    players[currentPlayer].addCash("hundreds", - paymentAmounts[5]);

				    players[currentPlayer].spreadCash(500);
				    players[currentPlayer].addCash("fiveHundreds", - paymentAmounts[6]);


				    // And set cash to sensible values.
				    players[currentPlayer].spreadCash(500);
				}
			    }
			}
			
			// A player owns the property.
			else {
			    // TODO: Add this message to status buffer?
			    System.out.println( players[currentPlayer].getName() + " pays " + players[currentPlayer].getCell().getOwner().getName() + "$" + Integer.toString(players[currentPlayer].getCell().getRent()));

			    // Disable button when the human player lands on an owned property.
			    if (players[currentPlayer] == player1) {
				buyProperty.setEnabled(false);
			    }
			    
			    // Charge the player appropriately.
			    if ( players[currentPlayer].willBankrupt( players[currentPlayer].getCell().getRent() ) ) {
				players[currentPlayer].bankruptPlayer();
			    }
			    else {
				playerPayPlayer(players[currentPlayer].getCell().getRent(), 
						players[currentPlayer], 
						players[currentPlayer].getCell().getOwner());
			    }
			}
		    }

		    // Player lands on a non-ownable property.
		    else {
			if (players[currentPlayer] == player1)
			    buyProperty.setEnabled(false);
		    }

		    // TODO: Make BoardCells and such clickable. That way, I can easily implement
		    // property selling, mortgaging, house/hotel buying.
		}
	    }

	    // TODO: Again, shouldn't need this huge conditional.
	    if (p == player1) {
		p.getCell().p1Pos.setImage(p.getImage());
	    }
	    else if (p == player2) {
		p.getCell().p2Pos.setImage(p.getImage());
	    }
	    else if (p == player3) {
		p.getCell().p3Pos.setImage(p.getImage());
	    }
	    else if (p == player4) {
		p.getCell().p4Pos.setImage(p.getImage());
	    }
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
		    cashLabels[i][j].setText(players[i].getCash(cashValues[j]));
	    }

	    if (currentPlayer == 0) {
		playerRowLabels[3].setForeground(Color.green);
		playerRowLabels[2].setForeground(Color.black);
	    }
	    else if (currentPlayer == 1) {
		playerRowLabels[0].setForeground(Color.green);
		playerRowLabels[3].setForeground(Color.black);
	    }
	    else if (currentPlayer == 2) {
		playerRowLabels[1].setForeground(Color.green);
		playerRowLabels[0].setForeground(Color.black);
	    }
	    else if (currentPlayer == 3) {
		playerRowLabels[2].setForeground(Color.green);
		playerRowLabels[1].setForeground(Color.black);
	    }
	}
    }

    /** Perform actions depending on GUI events. */
    public void actionPerformed(ActionEvent e) {
	String eventSource = e.getActionCommand();

	switch (eventSource) {
	    case "Continue": doTurn(frame);
		break;
		// TODO: Make the player pay to buy it, add bankruptcy check.
	    case "Buy property": player1.getCell().setOwner( player1 );
		buyProperty.setEnabled(false); // Disable button after property's bought.
		break;
	    case "Buy house": System.out.println("Testing buy house.");
		break;
	    case "Buy hotel": System.out.println("Testing buy hotel.");
		break;
	    case "Sell property": System.out.println("Testing sell property.");
		break;
	    case "Mortgage property": System.out.println("Testing mortgage property.");
		break;

            // Use a card and take the main player out of jail.
	    // TODO: Disable this when the player's not on a jail cell.
	    case "Use get out of jail free card":
		// TODO: This conditional's confusing. What's it getting at?
		if (player1.getNumGOOJFCards() > 0)
		    useGOOJFCard.setEnabled(false);

		player1.spendGOOJF();
		updateDisplay();
		break;

	    default:
		System.out.println("Received an action from an unknown source.");
	}
    }

    /** Given an integer i, break it up into the smallest possible amount of bills. */
    // TODO: Can/should this be public? And this seems like it should maybe be associated with
    //       a Player object instead? Or similar?
    public int[] getCashDistribution(int total) {
	// TODO: This functionality seems so generic that it should be in a library somewhere...
	// LEFTOFFHERE: TODO: This is almost working, but I think BoardCell's getCost() function
	// is not being overwritten by PropagandaOutlet's => since BoardCell always returns 
	// 0, I get this case where total = 0, which screws up gameplay.
	if (total <= 0) {
	    System.out.println("Can't break up 0 or negative dollars!");

	    // Since we can't break up this amount of money, the amount for each bill is 0.
	    // TODO: Convert this to a for-each loop or similar.
	    paymentAmounts[0] = 0;
	    paymentAmounts[1] = 0;
	    paymentAmounts[2] = 0;
	    paymentAmounts[3] = 0;
	    paymentAmounts[4] = 0;
	    paymentAmounts[5] = 0;
	    paymentAmounts[6] = 0;
	}

	else {
	    // TODO: Ambiguous, confusing names here.
	    int[] billValues  = {500, 100, 50, 20, 10, 5, 1};
	    int[] billTotals  = {0, 0, 0, 0, 0, 0, 0};
	    int i = 0;

	    for (int bill : billValues) {
		if (total / bill != 0) {
		    billTotals[i] = total / bill;
		    total -= (bill * billTotals[i]);
		}

		i++;
	    }

	    //Then have the player pay each of the amounts
	    paymentAmounts[0] = billTotals[6];
	    paymentAmounts[1] = billTotals[5];
	    paymentAmounts[2] = billTotals[4];
	    paymentAmounts[3] = billTotals[3];
	    paymentAmounts[4] = billTotals[2];
	    paymentAmounts[5] = billTotals[1];
	    paymentAmounts[6] = billTotals[0];
	}

	return paymentAmounts;
    }

    /** Transfer money amount a from player p1 to player p2.*/
    // TODO: This is a little misleading. This provides a general way to split bills correctly and
    // pay for any task. I think. Better method name would be good.
    public void playerPayPlayer(int a, Player p1, Player p2) {
	// TODO: Before each call of this, make sure the player won't go bankrupt.
	// TODO: Move that step of checking for bankruptcy status to the method rather than
	// spreading it out everywhere across the file. Less duplication.
	// First, get a distribution of what bills to pay.
	getCashDistribution(a);

	// Then, for each bill, transfer the correct amount from p1 to p2.
	// TODO: Loop this?

	p1.spreadCash(1);
	p1.addCash("ones", - paymentAmounts[0]);
	p2.addCash("ones", paymentAmounts[0]);

	p1.spreadCash(5);
	p1.addCash("fives", - paymentAmounts[1]);
	p2.addCash("fives", paymentAmounts[1]);

	p1.spreadCash(10);
	p1.addCash("tens", - paymentAmounts[2]);
	p2.addCash("tens", paymentAmounts[2]);

	p1.spreadCash(20);
	p1.addCash("twenties", - paymentAmounts[3]);
	p2.addCash("twenties", paymentAmounts[3]);

	p1.spreadCash(50);
	p1.addCash("fifties", - paymentAmounts[4]);
	p2.addCash("fifties", paymentAmounts[4]);

	p1.spreadCash(100);
	p1.addCash("hundreds", - paymentAmounts[5]);
	p2.addCash("hundreds", paymentAmounts[5]);

	p1.spreadCash(500);
 	p1.addCash("fiveHundreds", - paymentAmounts[6]);
	p2.addCash("fiveHundreds", paymentAmounts[6]);

	// And set cash back to sensible values.
	p2.spreadCash(500);
    }
}