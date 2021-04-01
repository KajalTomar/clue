//--------------------------------------------------------
// CLASS: TestComputerGuess.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: the purpose of this class is to test the
// the guesses made by the ComputerPlayer class.
//
//--------------------------------------------------------

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestComputerGuess{

    @Test
    public void noCards() {
        // a computer player has no cards, then canAnser should returh null
        ComputerPlayer bot = new ComputerPlayer();
        HumanPlayer player = new HumanPlayer();

        Guess guess = new Guess(new Suspect("Bobby"),new Location("Kitchen"), new Weapon("Gun"),false);

        assertNull( bot.canAnswer(guess, player));
    }
}