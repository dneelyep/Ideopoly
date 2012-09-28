JFLAGS=-Xlint:cast -Xlint:classfile -Xlint:deprecation -Xlint:dep-ann -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:options -Xlint:overrides -Xlint:path -Xlint:processing -Xlint:rawtypes -Xlint:serial -Xlint:static -Xlint:try -Xlint:unchecked -Xlint:varargs

#TODO: Install JUnit from Arch repos?

SRC        = ./*.java ../tests/*.java
CLASS      = ../../../../bin/
CLASSPATH  = ~/Programming/Java/Ideopoly/bin:/home/daniel/.junit/junit4.10/junit-4.10.jar:/home/daniel/batik/batik-1.7/lib/batik-transcoder.jar
#TODO: What's the point of the sourcepath?
SRCPATH    = ../../../
TESTS      = com.ideopoly.tests.BoardCellTester com.ideopoly.tests.BoardPositionTester com.ideopoly.tests.CashCellTester com.ideopoly.tests.ChanceTester com.ideopoly.tests.CharacterSelectTester com.ideopoly.tests.CommunityChestTester com.ideopoly.tests.GameBoardTester com.ideopoly.tests.MenuTester com.ideopoly.tests.PlayerTester com.ideopoly.tests.PropagandaOutletTester com.ideopoly.tests.RailroadTester com.ideopoly.tests.UtilityCellTester
BIN        = ../../../../bin/com/ideopoly/game

all: $(BIN)/BoardCell.class $(BIN)/BoardPosition.class $(BIN)/CashCell.class $(BIN)/Chance.class $(BIN)/ChanceOrCommChestCell.class $(BIN)/CharacterSelect.class $(BIN)/CommunityChest.class $(BIN)/GameBoard.class $(BIN)/Menu.class $(BIN)/Ownable.class $(BIN)/Player.class $(BIN)/PropagandaOutlet.class $(BIN)/Railroad.class $(BIN)/SpecialCell.class $(BIN)/TestHelper.class $(BIN)/UtilityCell.class
        # Run tests.
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore $(TESTS)

        # Check for static analysis bugs.
	java -cp $(CLASSPATH) -jar ~/findbugs-2.0.0/lib/findbugs.jar -textui -emacs $(CLASS)

$(BIN)/BoardCell.class: BoardCell.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ BoardCell.java

$(BIN)/BoardPosition.class: BoardPosition.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ BoardPosition.java

$(BIN)/CashCell.class: CashCell.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ CashCell.java

$(BIN)/Chance.class: Chance.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ Chance.java

$(BIN)/ChanceOrCommChestCell.class: ChanceOrCommChestCell.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ ChanceOrCommChestCell.java

$(BIN)/CharacterSelect.class: CharacterSelect.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ CharacterSelect.java

$(BIN)/CommunityChest.class: CommunityChest.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ CommunityChest.java

$(BIN)/GameBoard.class: GameBoard.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ GameBoard.java

$(BIN)/Menu.class: Menu.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ Menu.java

$(BIN)/Ownable.class: Ownable.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ Ownable.java

$(BIN)/Player.class: Player.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ Player.java

$(BIN)/PropagandaOutlet.class: PropagandaOutlet.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ PropagandaOutlet.java

$(BIN)/Railroad.class: Railroad.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ Railroad.java

$(BIN)/SpecialCell.class: SpecialCell.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ SpecialCell.java

$(BIN)/TestHelper.class: TestHelper.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ TestHelper.java

$(BIN)/UtilityCell.class: UtilityCell.java
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ UtilityCell.java


docs: # Regenerate my javadoc stuff.
	javadoc -author -classpath $(CLASSPATH) $(SRC) -d ../../../../etc/docs/
