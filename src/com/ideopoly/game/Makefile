JFLAGS=-Xlint:cast -Xlint:classfile -Xlint:deprecation -Xlint:dep-ann -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:options -Xlint:overrides -Xlint:path -Xlint:processing -Xlint:rawtypes -Xlint:serial -Xlint:static -Xlint:try -Xlint:unchecked -Xlint:varargs

#TODO: Install JUnit from Arch repos?

SRC        = ./*.java ../tests/*.java
CLASS      = ../../../../bin/
CLASSPATH  = ~/Programming/Java/Ideopoly/bin:/home/daniel/.junit/junit4.10/junit-4.10.jar
SRCPATH    = ../../../
TESTS      = com.ideopoly.tests.BoardCellTester com.ideopoly.tests.BoardPositionTester com.ideopoly.tests.CashCellTester com.ideopoly.tests.ChanceTester com.ideopoly.tests.CharacterSelectTester com.ideopoly.tests.CommunityChestTester com.ideopoly.tests.IdeopolyGUITester com.ideopoly.tests.MenuTester com.ideopoly.tests.PlayerTester com.ideopoly.tests.PropagandaOutletTester com.ideopoly.tests.RailroadTester

all: $(SRC) $(CLASS)
        # Compile source files.
	javac -cp $(CLASSPATH) -sourcepath $(SRCPATH) $(JFLAGS) -d ../../../../bin/ $(SRC)

        # TODO: Add SRCPATH to all items?
        # Run tests.
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore $(TESTS)

        # Check for static analysis bugs.
	java -cp $(CLASSPATH) -jar ~/findbugs-2.0.0/lib/findbugs.jar -textui -emacs $(CLASS)

docs: # Regenerate my javadoc stuff.
	javadoc -d javadoc -author -classpath $(CLASSPATH) $(SRC) -d ../../../../etc/docs/
