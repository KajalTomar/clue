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
import sun.awt.dnd.SunDragSourceContextPeer;

public class TestComputerGuess{

    @Test
    public void noCards() {
        // a computer player has no cards, then canAnser should returh null
        ComputerPlayer bot = new ComputerPlayer();
        Player player = new HumanPlayer();

        Guess guess = new Guess(new Person("Bobby"),new Location("Kitchen"),Weapon("Gun"),false);

        assertNull( bot.canAnswer(guess, player));
    }
}