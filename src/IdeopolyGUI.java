import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.util.Stack;

// TODO: Declare a no player present icon up top? Could help readability later in this file.
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
 *  can play the game. */

public class IdeopolyGUI implements ActionListener {
    // TODO: Make some of these arrays, etc., final/static when they're constant. Also see
    // if it's possible to get rid of some of them.
    /** Number to represent if the game has been won. Once the game's won, this switches to another value. */
    private int gameWon = 0;

    /** Linked list that represents the 160 positions players can stand on. */
    public LinkedList<BoardPosition> positions = new LinkedList<BoardPosition>();

    private Player   player1 = new Player(1);
    private Player   player2 = new Player(2);
    private Player   player3 = new Player(3);
    private Player   player4 = new Player(4);

    private Player   players[]       = { player1, player2, player3, player4 };
    private int      playersArrSize  = 3;
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
    private JButton	useGOOJFCard		= new JButton("Use get out of jail free card");
    private JLabel      status                  = new JLabel ("Status:");

    /** Represents the player whose turn it currently is to roll. 0-3. */
    private int currentPlayer = 0;

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
    private BoardCell        commChest1      = new BoardCell       ("Community Chest", new ImageIcon("images/bottCommChest.jpg"), 33, 41);
    private PropagandaOutlet balticAv        = new PropagandaOutlet("Baltic Av.", purple, 60, 4, 20, 60, 180, 320, 450, 50, 29, 41);
    private BoardCell	     incomeTax       = new BoardCell       ("Income Tax", incTax, 25, 41);
    // TODO: Bigger font size (~80) for the text on railroads - hard to read currently.
    private Railroad	     readingRR       = new Railroad        ("Reading RR", new ImageIcon("images/readingRR.jpg"), 21, 41);
    private PropagandaOutlet orientalAv      = new PropagandaOutlet("Oriental Av.", lightBlue, 100, 6, 30, 90, 270, 400, 550, 50, 17, 41);
    private BoardCell	     chance1	     = new BoardCell       ("Chance", new ImageIcon("images/botChance.jpg"), 13, 41);
    private PropagandaOutlet vermontAv       = new PropagandaOutlet("Vermont Av.", lightBlue, 100, 6, 30, 90, 270, 400, 550, 50, 9, 41);
    private PropagandaOutlet connecticutAv   = new PropagandaOutlet("Connecticut Av.", lightBlue, 120, 8, 40, 100, 300, 450, 600, 50, 5, 41);
    private BoardCell	     jail	     = new BoardCell       ("Jail", new ImageIcon("images/jail.jpg"), 1, 41);
    private PropagandaOutlet stCharles       = new PropagandaOutlet("St. Charles Place", pink, 140, 10, 50, 150, 450, 625, 750, 100, 1, 37);
    private BoardCell	     electricCompany = new BoardCell       ("Electric Co.", new ImageIcon("images/electricCo.jpg"), 1, 33);
    private PropagandaOutlet statesAv	     = new PropagandaOutlet("States Av.", pink, 140, 10, 50, 150, 450, 625, 750, 100, 1, 29);
    private PropagandaOutlet virginiaAv      = new PropagandaOutlet("Virginia Av.", pink, 160, 12, 60, 180, 500, 700, 900, 100, 1, 25);
    private Railroad	     pennsylvaniaRR  = new Railroad        ("Pennsylvania RR", new ImageIcon("images/pennsylvaniaRR.jpg"), 1, 21);
    private PropagandaOutlet stJames         = new PropagandaOutlet("St. James", orange, 180, 14, 70, 200, 550, 750, 950, 100, 1, 17);
    private BoardCell	     commChest2      = new BoardCell       ("Community Chest", new ImageIcon("images/leftCommChest.jpg"), 1, 13);
    private PropagandaOutlet tennesseeAv     = new PropagandaOutlet("Tennessee Av.", orange, 180, 14, 70, 200, 550, 750, 950, 100, 1, 9);
    private PropagandaOutlet newYorkAv       = new PropagandaOutlet("New York Av.", orange, 200, 16, 80, 220, 600, 800, 1000, 100, 1, 5);
    private BoardCell	     freeParking     = new BoardCell       ("Free Parking", new ImageIcon("images/freeParking.jpg"), 1, 1);
    private PropagandaOutlet kentuckyAv      = new PropagandaOutlet("Kentucky Av.", new ImageIcon("images/properties/kentuckyAv.jpg"), 220, 18, 90, 250, 700, 875, 1050, 150, 5, 1);
    private BoardCell	     chance2         = new BoardCell       ("Chance", new ImageIcon("images/topChance.jpg"), 9, 1);
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
    private BoardCell	     commChest3      = new BoardCell       ("Community Chest", new ImageIcon("images/rightCommChest.jpg"), 41, 13);
    private PropagandaOutlet pennsylvaniaAv  = new PropagandaOutlet("Pennsylvania Av.", green, 320, 28, 150, 450, 1000, 1200, 1400, 200, 41, 17);
    private Railroad         shortLineRR     = new Railroad        ("Short Line RR", new ImageIcon("images/shortLineRR.jpg"), 41, 21);
    private BoardCell        chance3	     = new BoardCell       ("Chance", new ImageIcon("images/rightChance.jpg"), 41, 25);
    private PropagandaOutlet parkPlace       = new PropagandaOutlet("Park Place", darkBlue, 350, 35, 175, 500, 1100, 1300, 1500, 200, 41, 29);
    private BoardCell	     luxuryTax       = new BoardCell       ("Luxury Tax", luxTax, 41, 33);
    private PropagandaOutlet boardwalk       = new PropagandaOutlet("Boardwalk", darkBlue, 400, 50, 200, 600, 1400, 1700, 2000, 200, 41, 37);

    // TODO: Make this a vector, or similar, so I can insert multiple types of objects. 
    // This will solve the later problem of not being able to use getCashDistribution. 
    // That method can only be called on PropagandaOutlets, but this situation turns all
    // Prop Outlets into BoardCells. => if I solve this problem, that one should disappear. And
    // hundreds more should surface.
    // TODO: Get actionlisteners implemented for the properties, so that I can mouseover them and have a picture of the property card pop up in the middle of the board.

    final BoardCell boardProperties[] = { go, mediterraneanAv, commChest1, balticAv, incomeTax, readingRR, orientalAv, chance1, vermontAv, connecticutAv, jail, stCharles, electricCompany, statesAv, virginiaAv, pennsylvaniaRR, stJames, commChest2, tennesseeAv, newYorkAv, freeParking, kentuckyAv, chance2, indianaAv, illinoisAv, bAndORR, atlanticAv, ventnorAv, waterWorks, marvinGardens, goToJail, pacificAv, nCarolinaAv, commChest3, pennsylvaniaAv, shortLineRR, chance3, parkPlace, luxuryTax, boardwalk }; // The game board is represented as an array of BoardCells

    /** The stack of Chance cards. */
    private Stack<Chance>         chanceCards    = new Stack<Chance>();

    /** The stack of Community Chest cards. */
    private Stack<CommunityChest> commChestCards = new Stack<CommunityChest>();


    /** Constructor creates the GUI, sets up parts of it, etc. */
    // TODO: Split up the functions logically.
    public IdeopolyGUI(String playerCharacter) {
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
	// TODO: Refactorable?
	// Bottom positions
	for (int i = 44; i >= 1; i--) {
	    positions.add(new BoardPosition(i, 45));
	}

	// Left positions
	for (int i = 40; i >= 5; i--) {
	    positions.add(new BoardPosition(0, i));
	}

	// Top positions
	for (int i = 1; i <= 44; i++) {
	    positions.add(new BoardPosition(i, 0));
	}

	// Right positions
	for (int i = 5; i <= 40; i++) {
	    positions.add(new BoardPosition(45, i));
	}

	for (BoardPosition item : positions) {
	    c.gridx = item.getXCoord();
	    c.gridy = item.getYCoord();
	    frame.add(item, c);
	}

	// TODO: Rename this propaganda outlets rather than properties? Confusing?
	// TODO: Can I fix the icons to remove duplicate code?
	// TODO: Declare i above so I don't have to keep: for (int i = x)
	// TODO: Rather than two arrays, just traverse a single one in reverse?
	// TODO: Swap this section with inserting currency images and such.
	// TODO: Seems kind of weird to have all this initialization stuff in a constructor.

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
	//	JTextArea messages =  new JTextArea("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", 5, 5);
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

	positions.get( 3 ).setImage(player1.getImage());
	positions.get( 2 ).setImage(player2.getImage());
	positions.get( 1 ).setImage(player3.getImage());
	positions.get( 0 ).setImage(player4.getImage());

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
		players[currentPlayer].setInJail(2);
	    }

	    // Second week in jail.
	    else if ( players[currentPlayer].getJailStatus() == 2 ) {
		players[currentPlayer].setInJail(1);
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
		    players[currentPlayer].setInJail(0);

		    if (currentPlayer == 0) {
			movePlayer(players[0], players[1], players[2], players[3], roll);
		    }
		    else if (currentPlayer == 1) {
			movePlayer(players[1], players[0], players[2], players[3], roll);
		    }
		    else if (currentPlayer == 2) {
			movePlayer(players[2], players[1], players[0], players[3], roll);
		    }
		    else if (currentPlayer == 3) {
			movePlayer(players[3], players[1], players[2], players[0], roll);
		    }
		}
	    }

	    // Not in jail, so move the player as normal.
	    else {
		if (currentPlayer == 0) {
		    movePlayer(players[0], players[1], players[2], players[3], roll);
		}
		else if (currentPlayer == 1) {
		    movePlayer(players[1], players[0], players[2], players[3], roll);
		}
		else if (currentPlayer == 2) {
		    movePlayer(players[2], players[1], players[0], players[3], roll);
		}
		else if (currentPlayer == 3) {
		    movePlayer(players[3], players[1], players[2], players[0], roll);
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
	    // * Player and computer tokens should be sent from the character select section to this part.
    }


    /** Move the given Player p forward numCells (where numCells is # of 
     *  board cells, not # of positions) on the list positions ON THE GUI.
     *  Also include players p2/3/4, because it's possible to affect them
     *  by moving a player. */
    // TODO: Do I need the extra arguments here?
    public void movePlayer(Player p, Player p2, Player p3, Player p4, int numCells) {
	int landingSpot = p.getPosition() + (numCells * 4);

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
		positions.get( p.getPosition() ).setImage(new ImageIcon("images/noPlayerPresent.jpg"));

		// The player is on one of the last 6 spaces and will overshoot Go.
		if ( p.getPosition() >= 135 && p.getPosition() <= 159 && (landingSpot > 159) ) {
		    int finalProperty = ( landingSpot - 160 ) / 4;
		
		    if ( p == player1 )
			p.setPosition( (finalProperty * 4) + 3 );
		    else if (p == player2)
			p.setPosition( (finalProperty * 4) + 2 );
		    else if (p == player3)
			p.setPosition( (finalProperty * 4) + 1 );
		    else if (p == player4)
			p.setPosition( (finalProperty * 4) + 0 );

		    p.addCash("hundreds", 2); // Give 200 bucks for passing Go.
		}

		// Player lands on Go to Jail.
		else if ( landingSpot >= 120 && landingSpot <= 123) {
		    // TODO: Just hard code in the values here? Is more clear than this subtraction.
		    p.setPosition(landingSpot - 80);
		    putInJail(p);
		    // TODO: Make a visual indicator when a person's in jail. IE: put a little colored square on their portrait that indicates the week they're in.
		    // TODO: Then make a thing that checks if a person's currently in jail or not.
		}


		// Regular move - not overshooting Go or landing on Go to Jail. Just move forward 4 spaces.
		else {
		    p.setPosition( landingSpot );

		    // Player lands on a Community Chest card.
		    if (       ( landingSpot >= 8   && landingSpot <= 11 )
			       || ( landingSpot >= 68  && landingSpot <= 71 )
			       || ( landingSpot >= 132 && landingSpot <= 135 ) ) {
			CommunityChest card = commChestCards.pop();
			// ...do action...
			switch ( card.getType() ) {

			case 1: // "Advance to Go (Collect $200)"
			    // TODO: This is repeated in the Chance deck.
			    if (p == player1)
				p.setPosition( 3 );
			    else if (p == player2)
				p.setPosition( 2 );
			    else if (p == player3)
				p.setPosition( 1 );
			    else if (p == player4)
				p.setPosition( 0 );

			    p.addCash("hundreds", 2);
			    break;
			case 2:	// "Bank error in your favor – collect $200"
			    p.addCash("hundreds", 2);
			    break;
			case 3:   // "Doctor's fees – Pay $50"
			    if (p.willBankrupt(50)) {
				p.bankruptPlayer(); // TODO: Update this code to do more relevant stuff.
			    }
			    else {
				p.spreadCash(50); // Shuffle money around to get 50s.
				p.addCash("fifties", -1);
				p.spreadCash(500); // Then shuffle back to a more reasonable amount.
			    }
			    break;
			case 4:   // "Get Out of Jail Free – this card may be kept until needed, or sold"
			    // TODO: Repeated later as well.
			    p.giveGOOJF();
			    break;
			case 5:   // "Go to Jail – go directly to jail – Do not pass Go, do not collect $200"
			    // TODO: This repeated later.
			    if ( p == player1 )
				p.setPosition( 43 );
			    else if (p == player2)
				p.setPosition( 42 );
			    else if (p == player3)
				p.setPosition( 41 );
			    else if (p == player4)
				p.setPosition( 40 );

			    putInJail(p);
			    break;
			case 6:   // "It is your birthday - Collect $10 from each player"
			    // TODO: Repeated also.
			    if (p2.willBankrupt(10) || p3.willBankrupt(10) || p4.willBankrupt(10)) {
				// TODO: ...bankruptcy code...
			    }
			    else {
				p2.spreadCash(10);
				p2.addCash("tens", -1);
				p2.spreadCash(500);

				p3.spreadCash(10);
				p3.addCash("tens", -1);
				p3.spreadCash(500);

				p4.spreadCash(10);
				p4.addCash("tens", -1);
				p4.spreadCash(500);

				p.addCash("tens", 3);
			    }

			    break;
			case 7:   // "Grand Opera Night – collect $50 from every player for opening night seats"

			    if (p2.willBankrupt(50) || p3.willBankrupt(50) || p4.willBankrupt(50)) {
				// TODO: ...bankruptcy code...
			    }
			    else {
				// TODO: Could loop this.
				p2.spreadCash(50);
				p2.addCash("fifties", -1);
				p2.spreadCash(500);

				p3.spreadCash(50);
				p3.addCash("fifties", -1);
				p3.spreadCash(500);

				p4.spreadCash(50);
				p4.addCash("fifties", -1);
				p4.spreadCash(500);

				p.addCash("tens", 3);
			    }
			    break;
			case 8:   // "Income Tax refund – collect $20"
			    p.addCash("twenties", 1);
			    break;
			case 9:   // "Life Insurance Matures – collect $100"
			    p.addCash("hundreds", 1);
			    break;
			case 10:  // "Pay Hospital Fees of $100"

			    if (p.willBankrupt(100)) {
				p.bankruptPlayer();
			    }
			    else {
				p.spreadCash(100);
				p.addCash("hundreds", -1);
				p.spreadCash(500);
			    }
			    break;
			case 11:  // "Pay School Fees of $50"

			    if (p.willBankrupt(50)) {
				p.bankruptPlayer();
			    }
			    else {
				p.spreadCash(50);
				p.addCash("fifties", -1);
				p.spreadCash(500);
			    }
			    break;
			case 12:  // "Receive $25 Consultancy Fee"
			    p.addCash("twenties", 1);
			    p.addCash("fives", 1);
			    break;
			case 13:  // "You are assessed for street repairs – $40 per house, $115 per hotel"
			    if (p.willBankrupt( (p.getNumHouses() * 40) + (p.getNumHotels() * 115) )) {
				p.bankruptPlayer();
			    }
			    else {
				// TODO: Code here, this varies though. Difficult.
			    }

			    break;
			case 14:  // "You have won second prize in a beauty contest– collect $10"
			    p.addCash("tens", 1);
			    break;
			case 15:  // "You inherit $100"
			    p.addCash("hundreds", 1);
			    break;
			case 16:  // "From sale of stock you get $50"
			    p.addCash("fifties", 1);
			    break;
			case 17:  // "Holiday Fund matures - Receive $100"
			    p.addCash("hundreds", 1);
			    break;
		        default:
			    System.out.println("Error! Wrong Community Chest value!");
			    break;
			}

			// TODO: Redirect these out to a GUI status element thing.
			System.out.println(card.getText());
		    }
	    
		    // Player lands on a Chance card.
		    else if (    (landingSpot >= 28  && landingSpot <= 31 ) 
				 || (landingSpot >= 88  && landingSpot <= 91 )
				 || (landingSpot >= 144 && landingSpot <= 147)) {
			// ...do action...
			Chance card = chanceCards.pop();
			// TODO: Review all of these, make sure the correct code's called when a person 
			// lands on a cell. Right now, movement's looking ok, but I also need to 
			// handle charging rent, buying properties, etc.

			switch ( card.getType() ) {

			case 1:  //"Advance to Go (Collect $200)"
			    // TODO: Maybe make a move number offset for each player. Idea would be I could
			    // do, for each player p.setPosition(cell I want + move offset), rather than
			    // hard-coding every value individually.

			    if (p == player1)
				p.setPosition( 3 );
			    else if (p == player2)
				p.setPosition( 2 );
			    else if (p == player3)
				p.setPosition( 1 );
			    else if (p == player4)
				p.setPosition( 0 );

			    p.addCash("hundreds", 2);
			    break;
			case 2:  //"Advance to Illinois Ave - if you pass Go, collect $200"
			    // TODO: There's a general pattern to these cards. It's if position is 
			    // >= some value, give $200 dollars. And then, depending on player, set position.
			    // Make this type of card into a function.

                            // If the player's at B & O RR or after, give money.
			    if (p.getPosition() >= 100)
				p.addCash("hundreds", 2);

			    if ( p == player1 )
				p.setPosition( 99 );
			    else if (p == player2)
				p.setPosition( 98 );
			    else if (p == player3)
				p.setPosition( 97 );
			    else if (p == player4)
				p.setPosition( 96 );
			    break;
			case 3:  //"Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown."


			    break;
			case 4:  //"Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.)"
			    // TODO: Account for the two of these, or just ignore it.
			    break;
			case 5:  //"Advance to St. Charles Place – if you pass Go, collect $200"

			    // If the player's position is on or after Electric Company, give em $200.
			    if (p.getPosition() >= 48)
				p.addCash("hundreds", 2);

			    if ( p == player1 )
				p.setPosition( 47 );
			    else if (p == player2)
				p.setPosition( 46 );
			    else if (p == player3)
				p.setPosition( 45 );
			    else if (p == player4)
				p.setPosition( 44 );

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
			    break;
			case 9:  //"Go directly to Jail – do not pass Go, do not collect $200"
			    // TODO: Here's another reason to make put in jail a player method.
			    // It's an action that's performed several times.

			    if ( p == player1 )
				p.setPosition( 43 );
			    else if (p == player2)
				p.setPosition( 42 );
			    else if (p == player3)
				p.setPosition( 41 );
			    else if (p == player4)
				p.setPosition( 40 );

			    putInJail(p);

			    break;
			case 10: //"Make general repairs on all your property – for each house pay $25 – 
			         //for each hotel $100"
			    // If the payment will bankrupt the Player, do x.
			    // TODO: Haven't tested this yet, to make sure I get correct values out of parseInt().
			    if ( p.willBankrupt( (p.getNumHouses() * 25) + (p.getNumHotels() * 100 ) ) ) {
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

			    if ( p == player1 )
				p.setPosition( 23 );
			    else if (p == player2)
				p.setPosition( 22 );
			    else if (p == player3)
				p.setPosition( 21 );
			    else if (p == player4)
				p.setPosition( 20 );

			    // TODO: And then onland function.
			    break;

			case 13: //"Take a walk on the Boardwalk – advance token to Boardwalk"
			    if ( p == player1 )
				p.setPosition( 159 );
			    else if (p == player2)
				p.setPosition( 158 );
			    else if (p == player3)
				p.setPosition( 157 );
			    else if (p == player4)
				p.setPosition( 156 );

			    //TODO: And then call the onland function for boardwalk.
			    break;
			case 14: //"You have been elected chairman of the board – pay each player $50"
			    // TODO: Make sure addCash handles negative values appropriately.
			    if (p.willBankrupt(150)) {
				p.bankruptPlayer();
			    }
			    else {
				p.spreadCash(50);
				p.addCash("fifties", -3);
				p2.addCash("fifties", 1);
				p3.addCash("fifties", 1);
				p4.addCash("fifties", 1);
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

			// TODO: Remove this eventually.
			System.out.println(card.getText());
		    }

		    // TODO: Make a separate method to handle this?
		    // TODO: Instead, just check for class. If is PropOutlet/RR/Utility, do x.
		    else if (    getCurrentLocation(players[currentPlayer]) == mediterraneanAv
			      || getCurrentLocation(players[currentPlayer]) == balticAv
			      || getCurrentLocation(players[currentPlayer]) == orientalAv
			      || getCurrentLocation(players[currentPlayer]) == vermontAv
			      || getCurrentLocation(players[currentPlayer]) == connecticutAv
			      || getCurrentLocation(players[currentPlayer]) == stCharles
			      || getCurrentLocation(players[currentPlayer]) == statesAv
			      || getCurrentLocation(players[currentPlayer]) == virginiaAv
			      || getCurrentLocation(players[currentPlayer]) == stJames
			      || getCurrentLocation(players[currentPlayer]) == tennesseeAv
			      || getCurrentLocation(players[currentPlayer]) == newYorkAv
			      || getCurrentLocation(players[currentPlayer]) == kentuckyAv
			      || getCurrentLocation(players[currentPlayer]) == indianaAv
			      || getCurrentLocation(players[currentPlayer]) == illinoisAv
			      || getCurrentLocation(players[currentPlayer]) == atlanticAv
			      || getCurrentLocation(players[currentPlayer]) == ventnorAv
			      || getCurrentLocation(players[currentPlayer]) == marvinGardens
			      || getCurrentLocation(players[currentPlayer]) == pacificAv
			      || getCurrentLocation(players[currentPlayer]) == nCarolinaAv
			      || getCurrentLocation(players[currentPlayer]) == pennsylvaniaAv
			      || getCurrentLocation(players[currentPlayer]) == parkPlace
			      || getCurrentLocation(players[currentPlayer]) == boardwalk

			      || getCurrentLocation(players[currentPlayer]) == readingRR
			      || getCurrentLocation(players[currentPlayer]) == pennsylvaniaRR
			      || getCurrentLocation(players[currentPlayer]) == bAndORR
			      || getCurrentLocation(players[currentPlayer]) == shortLineRR

			      || getCurrentLocation(players[currentPlayer]) == waterWorks
			      || getCurrentLocation(players[currentPlayer]) == electricCompany) {

			// No player currently owns the property.			
			if ( getCurrentLocation(players[currentPlayer]).getOwner() == null ) {

			    // Allow player to buy the property.
			    if (players[currentPlayer] == player1) {
				// TODO: This is nice to have, but it screws up my later switch statement.
				// buyProperty.setText("Buy property (" + getCurrentLocation(players[currentPlayer]).getName() + ")");
				buyProperty.setEnabled(true);
			    }

			    // AI code here.
			    // Basically, just buy the property if the AI has more than $800.
			    else {
				if (Integer.parseInt(players[currentPlayer].getCash("total")) >= 500) {
				    getCurrentLocation(players[currentPlayer]).setOwner(players[currentPlayer]);

				    getCashDistribution( getCurrentLocation( players[currentPlayer]).getCost() );
				    System.out.println(getCurrentLocation( players[currentPlayer]).getCost() );

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
			    System.out.println( players[currentPlayer].getName() + " pays " + getCurrentLocation(players[currentPlayer]).getOwner().getName() + "$" + Integer.toString( getCurrentLocation(players[currentPlayer]).getRent()) );

			    // Disable button when the human player lands on an owned property.
			    if (players[currentPlayer] == player1) {
				buyProperty.setEnabled(false);
			    }
			    
			    // Charge the player appropriately.
			    if ( players[currentPlayer].willBankrupt( getCurrentLocation(players[currentPlayer]).getRent() ) ) {
				players[currentPlayer].bankruptPlayer();
			    }
			    else {
				playerPayPlayer(getCurrentLocation(players[currentPlayer]).getRent(), 
				    players[currentPlayer], getCurrentLocation(players[currentPlayer]).getOwner());
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

	    positions.get( p.getPosition() ).setImage(p.getImage());
	}
    }

    /** Update the GUI elements that display player cash values
     *  and the text indicating the current player. */
    public void updateDisplay() {
	// TODO: Replace with foreach?
	// TODO: This same code's used earlier.
	for (int i = 0; i <= 3; i++) {
	    for (int j = 0; j <= 9; j++) {
		if (j == 9) {
		    cashLabels[i][j].setText(Integer.toString(players[i].getJailStatus()));
		}
		else if (j == 8) {
		    cashLabels[i][j].setText(Integer.toString(players[i].getNumGOOJFCards()));
		}
		else {
		    cashLabels[i][j].setText(players[i].getCash(cashValues[j]));
		}
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
	    case "Buy property": getCurrentLocation(players[0]).setOwner( players[0] );
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
		if (players[0].getNumGOOJFCards() > 0)
		    useGOOJFCard.setEnabled(false);

		players[0].spendGOOJF();
		updateDisplay();
		break;

	    default:
		System.out.println("Received an action from an unknown source.");
	}
    }

    /** Given a Player p and that player's location, return the cell the player is standing on. */
    // TODO: This sounds like a method that should belong to the Player class.
    private BoardCell getCurrentLocation(Player p) {
	return boardProperties[p.getPosition() / 4];
    }

    /** Put a given player in jail. */
    private void putInJail(Player p) {
	// TODO: This should also move the player to the appropriate position. The code to do that is
	// duplicated in a few places.
	// Allow the main player to use their cards.
	if (p == player1 && p.getNumGOOJFCards() > 0) {
	    useGOOJFCard.setEnabled(true);
	}
	p.setInJail(3);
    }

    /** Given an integer i, break it up into the smallest possible amount of bills. */
    private void getCashDistribution(int total) {
	// LEFTOFFHERE: TODO: This is almost working I think, but I think BoardCell's getCost() function
	// is not being overwritten by PropagandaOutlet's => since BoardCell always returns 0, I get this
	// case where total = 0, which screws up gameplay.
	if (total <= 0) {
	    // TODO: Apparently this is screwed up somehow. I keep getting this case occurring.
	    System.out.println("Can't break up 0 or negative dollars!");
	}

	else {
	    int	fiveHundredsAmount = 0;
	    int	hundredsAmount	   = 0;
	    int	fiftiesAmount	   = 0;
	    int	twentiesAmount	   = 0;
	    int	tensAmount	   = 0;
	    int	fivesAmount	   = 0;
	    int	onesAmount	   = 0;

	    // TODO: Loop this.
	    if (total / 500 != 0) {
	    	fiveHundredsAmount = total / 500;
	    	total -= (500 * fiveHundredsAmount);
	    }

	    if (total / 100 != 0) {
		hundredsAmount = total / 100;
		total -= (100 * hundredsAmount);
	    }

	    if (total / 50 != 0) {
		fiftiesAmount = total / 50;
		total -= (50 * fiftiesAmount);
	    }

	    if (total / 20 != 0) {
		twentiesAmount = total / 20;
		total -= (20 * twentiesAmount);
	    }

	    if (total / 10 != 0) {
		tensAmount = total / 10;
		total -= (10 * tensAmount);
	    }

	    if (total / 5 != 0) {
		fivesAmount = total / 5;
		total -= (5 * fivesAmount);
	    }

	    if (total / 1 != 0) {
		onesAmount = total / 1 ;
		total -= (1 * onesAmount); // TODO: <-- That's not really needed.
	    }

	    //Then have the player pay each of the amounts
	    paymentAmounts[0] = onesAmount;
	    paymentAmounts[1] = fivesAmount;
	    paymentAmounts[2] = tensAmount;
	    paymentAmounts[3] = twentiesAmount;
	    paymentAmounts[4] = fiftiesAmount;
	    paymentAmounts[5] = hundredsAmount;
	    paymentAmounts[6] = fiveHundredsAmount;
	}  
    }
    /** Transfer money amount a from player p1 to player p2.*/
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