JFLAGS=-Xlint:cast -Xlint:classfile -Xlint:deprecation -Xlint:dep-ann -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:options -Xlint:overrides -Xlint:path -Xlint:processing -Xlint:rawtypes -Xlint:serial -Xlint:static -Xlint:try -Xlint:unchecked -Xlint:varargs

SRC        = *.java ../tests/*.java
CLASS      = ../bin/*.class
CLASSPATH  = /home/daniel/Desktop/School_Files/CECS_220/Assignment_5/bin:/home/daniel/Desktop/School_Files/CECS_220/Assignment_5/tests:/home/daniel/.junit/junit4.10/junit-4.10.jar:.

all:
	javac -cp $(CLASSPATH) $(JFLAGS) -d ../bin $(SRC) # Compile source files.

	java -cp $(CLASSPATH) org.junit.runner.JUnitCore BoardCellTester BoardPositionTester CashCellTester CharacterSelectTester CommunityChestTester IdeopolyGUITester MenuTester PlayerTester PropagandaOutletTester RailroadTester # Run tests.

	java -cp $(CLASSPATH) -jar /home/daniel/findbugs-2.0.0/lib/findbugs.jar -textui -emacs $(CLASS)                                                           # Check for static analysis bugs.
docs: # Regenerate my javadoc stuff.
	javadoc -d javadoc -author $(SRC)