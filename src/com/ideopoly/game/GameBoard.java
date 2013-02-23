package com.ideopoly.game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

// TODO: Add in Chance and Comm. Chest images.
// TODO: Use the native look and feel for the program.
// TODO: Get rid of the spacing between cells.
// TODO: Make text on cells readable.
// TODO: Look into replacing arrays with ArrayLists. Could be useful, not sure.
// TODO: Is the Ideopoly- prefix for the class name needed? Wouldn't GUI/similar work just as well and be shorter?

/** Class to contain the GUI of the game board. A new instance of the class
 *  creates the GUI and sets up various things in it. The game takes place mostly
 *  inside this GUI - the others are just there to get ya to the point where you
 *  can play the game.
 *
 *  @author Daniel Neel */
public class GameBoard {
    // TODO Review and look for places where I can make more use of generics.

    // TODO Have this class extend JFrame?
    // TODO: Make some of these arrays, etc., final/static when they're constant. Also see
    // if it's possible to get rid of some of them.
    /** Number to represent if the game has been won. Once the game's won,
     *  this switches to another value. */
    private int gameWon = 0;

    // TODO: Can I make this private?
    protected final ArrayList<String> cashValues = new ArrayList<>(
            Arrays.asList("ones", "fives", "tens", "twenties", "fifties", "hundreds", "fiveHundreds", "total"));

    // TODO: Come up with a better solution than making this public.
    public final ArrayList<JLabel> playerRowLabels = new ArrayList<>(
            Arrays.asList(new JLabel("Player 1"), new JLabel("Player 2"), new JLabel("Player 3"), new JLabel("Player 4")));

    /* TODO: Consider making these values constant, since they won't change.
       IE: static final int ROWS = 4
       static final int COLS = 7
       JLabel[][] cashLabels = new JLabel[ROWS][COLS]; */
    private JLabel[][] cashLabels = new JLabel[4][10];

    /** Array used to store the values of each type of bill a Player
     *  should pay after requiring a payment. */
    private JFrame  frame	         = new JFrame("Ideopoly | Main game");
    private JButton continueButton   = new JButton("Continue");//new ImageIcon("images/continueButton.jpg"));
    private JButton buyProperty	     = new JButton("Buy property");
    private JButton buyHouse	     = new JButton("Buy house");
    private JButton buyHotel	     = new JButton("Buy hotel");
    private JButton sellProperty     = new JButton("Sell property");
    private JButton mortgageProperty = new JButton("Mortgage property");
    private JButton cancelButton     = new JButton("Cancel");

    // TODO: Should I make this private, and include a function to set its enable-ability? The Player
    // class is currently the only outside class that changes its state.
    public  JButton useGOOJFCard     = new JButton("Use get out of jail free card");
    /** An area of text where we can display messages to the player. */
    // TODO: Make messages a little larger - would be useful so the player can see more at a time.
    private JTextArea messages = new JTextArea("Welcome to Ideopoly!\nTo start playing, press \"Continue\". \nUse the other buttons to buy properties, sell properties, etc. \nGood luck!", 7, 1);

    /** The BoardCell currently focused by the Player. */
    private BoardCell focusedCell = null;

    // Create the game board.
    // TODO: Remove unneeded image templates.
    // TODO Store this configuration data in a file, XML or similar.
    private SpecialCell	     go              = new SpecialCell("Go", "go.jpg", new Point(41, 41), this);
    private PropagandaOutlet mediterraneanAv = new PropagandaOutlet("Mediterranean Av.", "purpleTemplate.jpg", 60, 2, 10, 30, 90, 160, 250, 50, new Point(37, 41), this, new Color(120, 1, 217));
    private ChanceOrCommChestCell commChestBottom = new ChanceOrCommChestCell("Community Chest", "bottCommChest.jpg", new Point(33, 41), this);
    private PropagandaOutlet balticAv        = new PropagandaOutlet("Baltic Av.", "purpleTemplate.jpg", 60, 4, 20, 60, 180, 320, 450, 50, new Point(29, 41), this, new Color(120, 1, 217));
    private SpecialCell	     incomeTax       = new SpecialCell("Income Tax", "incomeTax.jpg", new Point(25, 41), this);
    // TODO: Bigger font size (~80) for the text on railroads - hard to read currently.
    private Railroad	     readingRR       = new Railroad("Reading RR", "readingRR.jpg", new Point(21, 41), this);
    private PropagandaOutlet orientalAv      = new PropagandaOutlet("Oriental Av.", "lightBlueTemplate.jpg", 100, 6, 30, 90, 270, 400, 550, 50, new Point(17, 41), this, new Color(174, 239, 255));
    private ChanceOrCommChestCell chanceBottom = new ChanceOrCommChestCell("Chance", "botChance.jpg", new Point(13, 41), this);
    private PropagandaOutlet vermontAv       = new PropagandaOutlet("Vermont Av.", "lightBlueTemplate.jpg", 100, 6, 30, 90, 270, 400, 550, 50, new Point(9, 41), this, new Color(174, 239, 255));
    private PropagandaOutlet connecticutAv   = new PropagandaOutlet("Connecticut Av.", "lightBlueTemplate.jpg", 120, 8, 40, 100, 300, 450, 600, 50, new Point(5, 41), this, new Color(174, 239, 255));
    private SpecialCell	     jail	         = new SpecialCell("Jail", "jail.jpg", new Point(1, 41), this);
    private PropagandaOutlet stCharles       = new PropagandaOutlet("St. Charles Place", "pinkTemplate.jpg", 140, 10, 50, 150, 450, 625, 750, 100, new Point(1, 37), this, new Color(254, 2, 151));
    private UtilityCell	     electricCompany = new UtilityCell("Electric Co.", "electricCo.jpg", new Point(1, 33), this);
    private PropagandaOutlet statesAv	     = new PropagandaOutlet("States Av.", "pinkTemplate.jpg", 140, 10, 50, 150, 450, 625, 750, 100, new Point(1, 29), this, new Color(254, 2, 151));
    private PropagandaOutlet virginiaAv      = new PropagandaOutlet("Virginia Av.", "pinkTemplate.jpg", 160, 12, 60, 180, 500, 700, 900, 100, new Point(1, 25), this, new Color(254, 2, 151));
    private Railroad	     pennsylvaniaRR  = new Railroad("Pennsylvania RR", "pennsylvaniaRR.jpg", new Point(1, 21), this);
    private PropagandaOutlet stJames         = new PropagandaOutlet("St. James", "orangeTemplate.jpg", 180, 14, 70, 200, 550, 750, 950, 100, new Point(1, 17), this, new Color(255, 188, 6));
    private ChanceOrCommChestCell commChestLeft = new ChanceOrCommChestCell("Community Chest", "leftCommChest.jpg", new Point(1, 13), this);
    private PropagandaOutlet tennesseeAv     = new PropagandaOutlet("Tennessee Av.", "orangeTemplate.jpg", 180, 14, 70, 200, 550, 750, 950, 100, new Point(1, 9), this, new Color(255, 188, 6));
    private PropagandaOutlet newYorkAv       = new PropagandaOutlet("New York Av.", "orangeTemplate.jpg", 200, 16, 80, 220, 600, 800, 1000, 100, new Point(1, 5), this, new Color(255, 188, 6));
    private SpecialCell      freeParking     = new SpecialCell("Free Parking", "freeParking.jpg", new Point(1, 1), this);
    private PropagandaOutlet kentuckyAv      = new PropagandaOutlet("Kentucky Av.", "properties/kentuckyAv.jpg", 220, 18, 90, 250, 700, 875, 1050, 150, new Point(5, 1), this, new Color(255, 0, 0));
    private ChanceOrCommChestCell chanceTop  = new ChanceOrCommChestCell("Chance", "topChance.jpg", new Point(9, 1), this);
    private PropagandaOutlet indianaAv	     = new PropagandaOutlet("Indiana Av.", "properties/indianaAv.jpg", 220, 18, 90, 250, 700, 875, 1050, 150, new Point(13, 1), this, new Color(255, 0, 0));
    private PropagandaOutlet illinoisAv      = new PropagandaOutlet("Illinois Av.", "properties/illinoisAv.jpg", 240, 20, 100, 300, 750, 925, 1100, 150, new Point(17, 1), this, new Color(255, 0, 0));
    private Railroad	     bAndORR	     = new Railroad("B & O RR", "bAndORR.jpg", new Point(21, 1), this);
    private PropagandaOutlet atlanticAv      = new PropagandaOutlet("Atlantic Av.", "properties/atlanticAv.jpg", 260, 22, 110, 330, 800, 975, 1150, 1, new Point(25, 1), this, new Color(252, 255, 0));
    private PropagandaOutlet ventnorAv	     = new PropagandaOutlet("Ventnor Av.", "properties/ventnorAv.jpg", 260, 22, 110, 330, 800, 975, 1150, 150, new Point(29, 1), this, new Color(252, 255, 0));
    private UtilityCell	     waterWorks      = new UtilityCell("Water Works", "waterWorks.jpg", new Point(33, 1), this);
    private PropagandaOutlet marvinGardens   = new PropagandaOutlet("Marvin Gardens", "properties/marvinGardens.jpg", 280, 24, 120, 360, 850, 1025, 1200, 150, new Point(37, 1), this, new Color(252, 255, 0));
    private SpecialCell      goToJail        = new SpecialCell("Go to Jail", "goToJail.jpg", new Point(41, 1), this);
    private PropagandaOutlet pacificAv	     = new PropagandaOutlet("Pacific Av.", "greenTemplate.jpg", 300, 26, 130, 390, 900, 1100, 1275, 200, new Point(41, 5), this, new Color(0, 196, 0));
    private PropagandaOutlet nCarolinaAv     = new PropagandaOutlet("N. Carolina Av.", "greenTemplate.jpg", 300, 26, 130, 390, 900, 1100, 1275, 200, new Point(41, 9), this, new Color(0, 196, 0));
    private ChanceOrCommChestCell commChestRight = new ChanceOrCommChestCell("Community Chest", "rightCommChest.jpg", new Point(41, 13), this);
    private PropagandaOutlet pennsylvaniaAv  = new PropagandaOutlet("Pennsylvania Av.", "greenTemplate.jpg", 320, 28, 150, 450, 1000, 1200, 1400, 200, new Point(41, 17), this, new Color(0, 196, 0));
    private Railroad         shortLineRR     = new Railroad("Short Line RR", "shortLineRR.jpg", new Point(41, 21), this);
    private ChanceOrCommChestCell chanceRight = new ChanceOrCommChestCell("Chance", "rightChance.jpg", new Point(41, 25), this);
    private PropagandaOutlet parkPlace       = new PropagandaOutlet("Park Place", "darkBlueTemplate.jpg", 350, 35, 175, 500, 1100, 1300, 1500, 200, new Point(41, 29), this, new Color(0, 8, 253));
    private SpecialCell      luxuryTax       = new SpecialCell("Luxury Tax", "luxuryTax.jpg", new Point(41, 33), this);
    private PropagandaOutlet boardwalk       = new PropagandaOutlet("Boardwalk", "darkBlueTemplate.jpg", 400, 50, 200, 600, 1400, 1700, 2000, 200, new Point(41, 37), this, new Color(0, 8, 253));

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
    public final ArrayList<BoardCell> boardProperties = new ArrayList<>(Arrays.asList(go, mediterraneanAv, commChestBottom, balticAv, incomeTax, readingRR, orientalAv, chanceBottom, vermontAv, connecticutAv, jail, stCharles, electricCompany, statesAv, virginiaAv, pennsylvaniaRR, stJames, commChestLeft, tennesseeAv, newYorkAv, freeParking, kentuckyAv, chanceTop, indianaAv, illinoisAv, bAndORR, atlanticAv, ventnorAv, waterWorks, marvinGardens, goToJail, pacificAv, nCarolinaAv, commChestRight, pennsylvaniaAv, shortLineRR, chanceRight, parkPlace, luxuryTax, boardwalk));

    public Player player1 = new Player(1, new Color(1, 238, 0) , this);
    public Player player2 = new Player(2, new Color(223, 254, 10), this);
    public Player player3 = new Player(3, new Color(253, 186, 17), this);
    public Player player4 = new Player(4, new Color(19, 214, 242), this);
    /** A queue of the four players who will be playing in this game. player1
     *  is the human player, the rest are computer players. */
    protected Queue<Player> playerQueue = new ArrayDeque<>(Arrays.asList(player1, player2, player3, player4));

    /** The stack of Chance cards. */
    private Stack<ChanceCards> chanceCards = new Stack<>();

    /** The stack of Community Chest cards. */
    private Stack<CommunityChestCards> commChestCards = new Stack<>();

    /** Various fields that display detailed property information. */
    // TODO: Better, more descriptive variable names. eg, detailedPropColor, etc.
    private JLabel guiColor          = new JLabel(" ");
    private JLabel guiName           = new JLabel("-");
    private JLabel guiCost           = new JLabel("-");
    private JLabel guiHouseHotelCost = new JLabel("-");
    private JLabel guiRent           = new JLabel("-");
    // TODO Convert these to a Map or similar.
    private JLabel gui1HouseLabel = new JLabel("-");
    private JLabel gui2HouseLabel = new JLabel("-");
    private JLabel gui3HouseLabel = new JLabel("-");
    private JLabel gui4HouseLabel = new JLabel("-");
    private JLabel guiHotel       = new JLabel("-");
    private JLabel guiMortgage    = new JLabel("-");

    /** Constructor creates the GUI, sets up parts of it, etc. */
    public GameBoard(String playerCharacter) { // TODO: Split up the functions logically.
        // Create the gui.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
            addAtCoords(cell, cell.getCoordinates(), c);
        }

        // ================================================================
        // === Add the standing positions around the edge of the board. ===
        // ================================================================
        c.gridwidth  = 1;
        c.gridheight = 1;

        // TODO Re-design the display of player statistics. It's not useful to see tons of info.
        //      Viewing a single player's money/etc all at once would be more useful.
        for (BoardCell property : boardProperties) {
            for (BoardPosition pos : Arrays.asList(property.getPosition(1), property.getPosition(2),
                    property.getPosition(3), property.getPosition(4))) {
                addAtCoords(pos, pos.getCoordinates(), c);
            }
        }

        // ====================================================
        // === Create labels to display each player's cash. ===
        // ====================================================
        c.gridx = 50;
        c.gridy = 0;

        // TODO: Would be good if I just used a 2D array to store all this, rather
        // than the separate titles, playerRowLabels, and for loop parts.
        // Display the column titles.
        for (String s : Arrays.asList("Cash", "1s", "5s", "10s", "20s", "50s", "100s", "500s",
                "Total", "GOOJF cards", "Turns left in jail")) {
            frame.add(new JLabel(s), c);
            c.gridx++;
        }

        // Display Player 1/2/3/4: to the left of each row.
        for (int i = 0; i < 4; i++)
            addAtCoords(playerRowLabels.get(i), new Point(50, i + 1), c);

        // =======================================================
        // === Add the labels that display player cash values. ===
        // =======================================================
        // TODO Consider storing these arrays of labels in Player, and just adding them here.
        for (int player = 0; player < playerQueue.size(); player++) { // Iterate through each row.
            Player tmpPlayer = playerQueue.remove();

            for (int billType = 0; billType < 8; billType++) {
                cashLabels[player][billType] = new JLabel(Integer.toString(tmpPlayer.getCash(CASH_TYPES.valueOf(cashValues.get(billType)))));
                addAtCoords(cashLabels[player][billType], new Point(51 + billType, 1 + player), c);
            }

            cashLabels[player][8] = new JLabel(Integer.toString(tmpPlayer.getNumGOOJFCards()));
            addAtCoords(cashLabels[player][8], new Point(59, 1 + player), c);

            cashLabels[player][9] = new JLabel(Integer.toString(tmpPlayer.getJailStatus()));
            addAtCoords(cashLabels[player][9], new Point(60, 1 + player), c);

            playerQueue.add(tmpPlayer);
        }

        // Add a Continue button in the middle of the board.
        c.gridwidth  = 7;
        c.gridheight = 2;
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doTurn(playerQueue.element());
                // TODO: Make sure I don't need a bankruptcy check for this event.
                //       Shouldn't, because button's only highlighted when the Player can buy.
                //       Also add plenty of tests for this.
                updateDisplay();
                playerQueue.add(playerQueue.remove());
            }
        });
        addAtCoords(continueButton, new Point(22, 22), c);

        // ======================================================================
        // === Add buttons (buy houses, etc.) to the right side of the board. ===
        // ======================================================================
        c.fill = GridBagConstraints.HORIZONTAL;

        // Disable currently unusable buttons and add Action Listeners.
        buyProperty.setEnabled(false);
        buyProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Ownable.class.isInstance(player1.getCell())) {
                    // TODO This should not be commented out.
                    //player1.buyProperty((Ownable) player1.getCell(), GameBoard.this);
                    buyProperty.setEnabled(false);
                    updateDisplay();
                }
            }
        });

        useGOOJFCard.setEnabled(false);
        useGOOJFCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1.spendGOOJF(GameBoard.this);
                useGOOJFCard.setEnabled(false);
                updateDisplay();
            }
        });

        cancelButton.setEnabled(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButton.setEnabled(false);
                buyHouse.setEnabled(false);
                buyHotel.setEnabled(false);
                sellProperty.setEnabled(false);
                mortgageProperty.setEnabled(false);

                ((JLabel) focusedCell.getComponent(0)).setBorder(BorderFactory.createEmptyBorder());
                focusedCell = null;
            }
        });

        for (JButton button : Arrays.asList(buyHouse, buyHotel, sellProperty, mortgageProperty)) {
            button.setEnabled(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Testing " + ((JButton) e.getSource()).getText() + ".");
                    updateDisplay();
                }
            });
        }

        // Left column.
        c.gridwidth = 9;
        addAtCoords(buyProperty, new Point(50, 6), c);

        c.gridwidth = 5;
        addAtCoords(buyHouse, new Point(50, 8), c);
        addAtCoords(buyHotel, new Point(50, 9), c);

        // Right column.
        c.gridwidth = 4;
        addAtCoords(sellProperty, new Point(55, 8), c);
        addAtCoords(mortgageProperty, new Point(55, 9), c);

        c.gridwidth = 9;
        addAtCoords(useGOOJFCard, new Point(50, 11), c);
        addAtCoords(cancelButton, new Point(50, 13), c);

        // ===============================================================
        // === Add the GUI stuff that displays detailed property info. ===
        // ===============================================================
        c.gridwidth  = 9;
        c.gridheight = 1;
        guiColor.setOpaque(true);
        guiColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        addAtCoords(guiColor, new Point(50, 20), c);
        addAtCoords(guiName, new Point(50, 21), c);

        for (String string : Arrays.asList("House/Hotel cost", "Rent", "1 house", "2 houses", "3 houses", "4 houses", "1 hotel", "Mortgage value")) {
            c.gridy++;
            addAtCoords(new JLabel(string + ":"), new Point(50, c.gridy), c);
        }

        JLabel[] labelValues = {guiHouseHotelCost, guiRent, gui1HouseLabel, gui2HouseLabel, gui3HouseLabel,
                gui4HouseLabel, guiHotel, guiMortgage};

        addAtCoords(guiCost, new Point(56, 21), c);
        for (JLabel label : labelValues) {
            c.gridy++;
            addAtCoords(label, new Point(56, c.gridy), c);
        }

        // ==============================
        // === Add the messages area. ===
        // ==============================
        // LEFTOFFHERE: Need to break this into multiple methods or similar.
        //              Maybe break them up by the headings?
        c.gridheight = 8;
        c.gridwidth  = 11;

        JScrollPane messagesPane = new JScrollPane(messages);
        messagesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        messages.setLineWrap(true);
        messages.setColumns(50);
        messagesPane.setColumnHeaderView(new JLabel("Status"));

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        // TODO: Implement formatted text here. For example, bold the Player names
        // in messages.
        // If I want this, it looks like I'll need to switch messages from a JTextArea
        // to a JTextPane. docs.oracle.com/javase/tutorial/uiswing/components/editorpane.html
        // TODO Also looking into using get/putClientProperty to store coordinates inside the objects.
        addAtCoords(messagesPane, new Point(50, 31), c);

        // TODO: Make a single Random object and re-use it?
        Random generator = new Random();

        // Make a large stack of cards to use for the game.
        for (int i = 0; i < 200; i++) {
            // Add random types of Chance and Community Chest cards.
            // TODO Do I need the +1? Can I just do .nextInt(17)?

            int chanceCardType = generator.nextInt(16) + 1;
            for (ChanceCards type : ChanceCards.values()) {
                if (type.getCardNumber() == chanceCardType)
                    chanceCards.push(type);
            }

            int commChestCardType = generator.nextInt(17) + 1;
            for (CommunityChestCards type : CommunityChestCards.values()) {
                if (type.getCardNumber() == commChestCardType)
                    commChestCards.push(type);
            }
        }

        boardProperties.get(0).getPosition(1).setIcon(player1.getImage());
        boardProperties.get(0).getPosition(2).setIcon(player2.getImage());
        boardProperties.get(0).getPosition(3).setIcon(player3.getImage());
        boardProperties.get(0).getPosition(4).setIcon(player4.getImage());

        frame.pack();
        frame.setVisible(true);

        System.out.println("You picked " + playerCharacter + ".");
        //	readingRR.generateImage("testName", player1.getColor(), Color.YELLOW, "left");

        // TODO See if there is a built-in collection for the players Queue.
    }

    /** Do a turn's worth of gameplay. First the player p rolls/moves. 
     *  Then cash is exchanged. Then players can buy/trade, etc.
     *  Return a 0 if nobody has won yet, or a 1 if someone won the game. */
    private void doTurn(Player p) {
        int roll = new Random().nextInt(6) + 1; // Roll a new random number between 1 and 6.
        printStatusAndLog(playerQueue.element().getName() + " rolls " + roll + ".");

        // TODO Make an enumeration for jail statuses?
        // First or second week in jail - player can't do anything but sit and wait.
        if (p.getJailStatus() == 3 || p.getJailStatus() == 2)
            p.setJailStatus(p.getJailStatus() - 1);

        else {
            if (p.getJailStatus() == 1) { // Last week in jail. Player gets charged $50, then moves forward.
                p.makePayment(50, this);
                p.setJailStatus(0);
            }
            movePlayer(p, roll);
        }
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
        int landingSpot = boardProperties.indexOf(p.getCell()) + numCells;

        // The actual space that will be landed on.
        BoardCell landingSpace = boardProperties.get(landingSpot % 40);

        // TODO Rather than this landingSpot nonsense, I could associate a set of 4 BoardPositions with every
        //      BoardCell, and then say if (landed on any of cell.getPositions()) do something.

        // Check for valid input first.
        // TODO: Do I even need these? It's possible to move backwards.
        if (numCells <= 0) {
            System.out.println("Can't move 0 spaces or backwards!");
        }

        else if (numCells > 6) {
            System.out.println("You can move at most 6 spaces.");
        }

        else {
            // Here the Player is moving, so set the label at p's current position to no player present.
            p.getCell().setPositionImage(p, new ImageIcon("res/images/noPlayerPresent.jpg"), this);

            // The player is about to land on/overshoot Go.
            if (landingSpot > 39) {
                // TODO: Try to clarify what's happening here. Could probably simplify it.
                // TODO: If circular linked list works, this conditional should be unneeded.
                p.setCell(boardProperties.get(landingSpot - 40));
                p.setCash(CASH_TYPES.hundreds, p.getCash(CASH_TYPES.hundreds) + 2); // Give 200 bucks for passing Go.
            }

            // TODO: I seem to be getting a bug where, after landing on Go to Jail,
            //       Buy Property will light up for the property player1 would have
            //       landed on, if (s)he had been allowed to roll. Then I get an error,
            //       because I try to buy that property, which uses player1's current cell
            //       (which is Jail, which is a SpecialCell), and you can't buy SpecialCells.
            else if (landingSpace == goToJail)
                p.putInJail(this);

            // TODO: For Chance and CommChest, do I need to have the Player
            //       changeCell() before popping the card off?
            else if (landingSpace == commChestBottom || landingSpace == commChestLeft || landingSpace == commChestRight)
                commChestCards.pop().doActions(p, this);

            else if (landingSpace == chanceBottom || landingSpace == chanceTop || landingSpace == chanceRight)
                chanceCards.pop().doActions(p, this);

            // TODO: Allow the Player to choose 10% or $200, or do the cheapest automatically.
            // TODO: Test to make sure this works.
            else if (landingSpace == incomeTax)
                p.makePayment(200, this);

            // TODO: Test to make sure this works.
            else if (landingSpace == luxuryTax)
                p.makePayment(75, this);

            else if (landingSpace == freeParking && p == player1)
                printStatusAndLog("Free parking!");

            else if (landingSpace == jail && p == player1)
                printStatusAndLog("In jail - but just visiting!");

            // Regular move. Here the Player should have not overshot Go or landed on any
            // position-changing cells (Go to Jail, Chance, etc.). The Player has landed on
            // an ownable BoardCell.
            // TODO Add checks to see if the property is ownable or not.
            else {
                p.setCell(boardProperties.get(landingSpot));

                // TODO: The below block of stuff looks like things to do post-moving a player to a cell.
                //       Move it all into a new method?
                // No player currently owns the property.
                if (boardProperties.get(landingSpot).getOwner() == null ) {

                    // Allow player to buy the property.
                    if (p == player1) {
                        // TODO: Have buyProperty disable itself after the player presses "Continue",
                        //       so they can't buy the property when it's not their turn?
                        //       Also, if I don't want to do that, disable it if another player buys
                        //       it or lands on the cell.
                        //       Also, have the text for the property name disappear when the button
                        //       is disabled.
                        buyProperty.setText("Buy property (" + boardProperties.get(landingSpot).getName() + ")");
                        buyProperty.setEnabled(true);
                    }

                    // Have the AI buy the property if it has more than $500.
                    else {
                        if (   p.getCash(CASH_TYPES.total) >= 500     // player has enough money to buy the property
                            && Ownable.class.isInstance(p.getCell())  // property is ownable
                            && !((Ownable) p.getCell()).isOwned()) {  // property is not owned
                            // TODO This should not be commented out.
                            //p.buyProperty((Ownable) p.getCell(), this);
                        }
                    }
                }

                // A player owns the property.
                else {
                    printStatusAndLog(p.getName() + " pays " + p.getCell().getOwner().getName() + "$" + Integer.toString(((Ownable) p.getCell()).getRent()));

                    // Disable button when the human player lands on an owned property.
                    if (p == player1)
                        buyProperty.setEnabled(false);

                    // Charge the player appropriately.
                    p.makePayment(p.getCell().getOwner(), ((Ownable) p.getCell()).getRent(), this);
                }
            }

            p.getCell().setPositionImage(p, p.getImage(), this);
        }
    }

    /** Update the GUI elements that display player cash values
     *  and the text indicating the current player. */
    public void updateDisplay() {
        // TODO: Replace with foreach?
        // TODO: This same code's used earlier.
        Queue<Player> tmpPlayersQueue = new ArrayDeque<>(Arrays.asList(player1, player2, player3, player4));

        for (int i = 0; i < tmpPlayersQueue.size(); i++) {
            for (int j = 0; j <= 9; j++) {
                Player tmpPlayer = tmpPlayersQueue.remove();

                if (j == 9)
                    cashLabels[i][j].setText(Integer.toString(tmpPlayer.getJailStatus()));
                else if (j == 8)
                    cashLabels[i][j].setText(Integer.toString(tmpPlayer.getNumGOOJFCards()));
                else
                    cashLabels[i][j].setText(Integer.toString(tmpPlayer.getCash(CASH_TYPES.valueOf(cashValues.get(j)))));

                tmpPlayersQueue.add(tmpPlayer);
            }

            // Set all player labels to black
            playerRowLabels.get(i).setForeground(Color.BLACK);
        }

        // ...then set the current player's label to green.
        int player = Integer.parseInt(Character.toString(playerQueue.element().getName().charAt(7)));
        playerRowLabels.get(player == 4 ? 0 : player).setForeground(Color.GREEN);
    }

    /** Given an integer i, break it up into the smallest possible amount of bills. */
    // TODO: Can/should this be public? And this seems like it should maybe be associated with
    //       a Player object instead? Or similar?

    /** Given a total amount of cash to split up, get back an array that contains
     * the number of each type of bill that represents that total amount. For example,
     * getCashDistribution(1300) would yield {2, 3, 0, 0, 0, 0, 0}, for two 500s and
     * three 100s. */
    public int[] getCashDistribution(int total) {
        int[] paymentAmounts = {0, 0, 0, 0, 0, 0, 0};

        // TODO: This functionality seems so generic that it should be in a library somewhere...
        if (total <= 0) {
            printStatusAndLog("Can't break up 0 or negative dollars!");
	    
            // Since we can't break up this amount of money, the amount of each bill to pay is 0.
            for (int amount : paymentAmounts)
                amount = 0;
        }

        else {
            /* We keep the arrays in reverse order here. This way, when we iterate
              through them, we start with the highest dollar amount, which leads to
              less dividing up the player's money into lots of small bills.
              TODO: Fix ambiguous, confusing names here. */
            /** billValues is the amount, in dollars, of bills that go into a given bill type. */
            int[] billValues  = {500, 100, 50, 20, 10, 5, 1};

            /** billTotals is the number of each bill type that have to be paid. */
            int[] billTotals  = {0, 0, 0, 0, 0, 0, 0};
            // TODO: Do I need this billTotals array? Could I just store values
            //       directly in paymentAmounts[] instead?

            for (int i = 0; i < billValues.length; i++) {
                int numberOfBills = total / billValues[i];

                billTotals[i] = numberOfBills;
                total -= (billValues[i] * billTotals[i]);
            }

            // Then set the amount of each bill type the player has to pay.
            // TODO: Again, replace with a for each loop.
            for (int i = 0; i < paymentAmounts.length; i++)
                paymentAmounts[i] = billTotals[6 - i];
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
        messages.setCaretPosition(messages.getDocument().getLength());
        // fout.append(text);
        // fout.close();

        //	fout = new FileWriter("log.txt");
        //    private FileWriter fout;
    }

    // TODO Replace these with a more generic method that applies to multiple gui properties.
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
    public void setGUI1HouseLabel(String t) {
        gui1HouseLabel.setText(t);
    }
    /** Set the 2 houses cost text for detailed property info. */
    public void setGUI2HouseLabel(String t) {
        gui2HouseLabel.setText(t);
    }
    /** Set the 3 houses cost text for detailed property info. */
    public void setGUI3HouseLabel(String t) {
        gui3HouseLabel.setText(t);
    }
    /** Set the 4 houses cost text for detailed property info. */
    public void setGUI4HouseLabel(String t) {
        gui4HouseLabel.setText(t);
    }
    /** Set the hotel cost text for detailed property info. */
    public void setGUIHotel(String t) {
        guiHotel.setText(t);
    }
    /** Set the mortgage value for detailed property info. */
    public void setGUIMortgage(String t) {
        guiMortgage.setText(t);
    }

    // TODO: Add tests.
    /** Add a given JComponent component to this GameBoard at the coordinates specified by a given Point. */
    private void addAtCoords(JComponent component, Point point, GridBagConstraints constraints) {
        constraints.gridx = point.x;
        constraints.gridy = point.y;
        frame.add(component, constraints);
    }

    /** Get this GameBoard's Buy House button. */
    public JButton getBuyHouse() {
        return buyHouse;
    }

    /** Get this GameBoard's Buy Hotel button. */
    public JButton getBuyHotel() {
        return buyHotel;
    }

    /** Get this GameBoard's Sell Property button. */
    public JButton getSellProperty() {
        return sellProperty;
    }

    /** Get this GameBoard's Mortgage Property button. */
    public JButton getMortgageProperty() {
        return mortgageProperty;
    }

    /** Get this GameBoard Cancel button, used to cancel focus
     * of the current BoardCell. */
    public JButton getCancelButton() {
        return cancelButton;
    }

    /** Set the focusedcell to a given BoardCell cell. */
    public void setFocusedCell(BoardCell cell) {
        focusedCell = cell;
    }

    /** Get the focusedcell for this GameBoard. */
    public BoardCell getFocusedCell() {
        return focusedCell;
    }
}
