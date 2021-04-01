#define the compiler and compiler flag variables
JC = javac

# clear any default targets for building .class files from .java files
.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

# macro for each java source file
CLASSES = \
        Card.java \
        Clue.java \
        ComputerPlayer.java \
    	Guess.java \
		HumanPlayer.java \
		IPlayer.java \
		Location.java \
		Model.java \
		Suspect.java \
		Weapon.java

# the defaul make target entry
default: classes

# target entry
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
