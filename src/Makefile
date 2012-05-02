JFLAGS=-Xlint:cast -Xlint:classfile -Xlint:deprecation -Xlint:dep-ann -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:options -Xlint:overrides -Xlint:path -Xlint:processing -Xlint:rawtypes -Xlint:serial -Xlint:static -Xlint:try -Xlint:unchecked -Xlint:varargs

SRC = *.java
CLASS = *.class
#IdeopolyGUI.java Menu.java CharacterSelect.java BoardCell.java Player.java Railroad.java PropagandaOutlet.java BoardPosition.java

HOME = /home/daniel

all:
	javac -cp $(HOME)/.junit/junit4.10/junit-4.10.jar:. $(JFLAGS) $(SRC) # Compile source files.
	java -cp $(HOME)/.junit/junit4.10/junit-4.10.jar:. org.junit.runner.JUnitCore IdeopolyTester # Run tests.
	java -jar $(HOME)/findbugs-2.0.0/lib/findbugs.jar -textui -emacs $(CLASS) # Check for static analysis bugs
docs: # Regenerate my javadoc stuff.
	javadoc -d javadoc -author $(SRC)