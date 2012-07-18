JFLAGS=-Xlint:cast -Xlint:classfile -Xlint:deprecation -Xlint:dep-ann -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:options -Xlint:overrides -Xlint:path -Xlint:processing -Xlint:rawtypes -Xlint:serial -Xlint:static -Xlint:try -Xlint:unchecked -Xlint:varargs

#TODO: Install JUnit from Arch repos?

SRC        = ../src/*.java ../tests/*.java
CLASS      = ../bin/*.class
CLASSPATH  = ~/Programming/Java/Ideopoly/com/ideopoly/bin:/home/daniel/.junit/junit4.10/junit-4.10.jar:.

all: $(SRC) $(CLASS)
        # Compile source files.
	javac -cp $(CLASSPATH) $(JFLAGS) -d ../bin $(SRC) 

        # Run tests.
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore BoardCellTester BoardPositionTester CashCellTester ChanceTester CharacterSelectTester CommunityChestTester IdeopolyGUITester MenuTester PlayerTester PropagandaOutletTester RailroadTester

        # Check for static analysis bugs.
	java -cp $(CLASSPATH) -jar /home/daniel/findbugs-2.0.0/lib/findbugs.jar -textui -emacs $(CLASS)

docs: # Regenerate my javadoc stuff.
	javadoc -d javadoc -author -classpath $(CLASSPATH) $(SRC) -d ../etc/docs/
