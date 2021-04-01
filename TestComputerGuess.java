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

        assertNull(bot.canAnswer(guess, player));
    }

    @Test
    public void exactlyOneCard() {
        // If a computer player has exactly one card from a guess, canAnswer should return that card.

        // these are the cards the computer will have
        Card joe = new Suspect("Joe");
        Card livingRoom = new Location("Living room");
        Card knife = new Weapon("Knife");

        ComputerPlayer bot = new ComputerPlayer();
        HumanPlayer player = new HumanPlayer();

        bot.setCard(joe);
        bot.setCard(livingRoom);
        bot.setCard(knife);

        // has only the suspect
        Guess guess0 = new Guess(joe,new Location("Kitchen"), new Weapon("Gun"),false);
        assertEquals(joe,bot.canAnswer(guess0,player));

        // has only the location
        Guess guess1 = new Guess(new Suspect("Bobby"),livingRoom, new Weapon("Gun"),false);
        assertEquals(livingRoom,bot.canAnswer(guess1,player));

        // has only the weapon
        Guess guess2 = new Guess(new Suspect("Bobby"),new Location("Kitchen"),knife,false);
        assertEquals(knife,bot.canAnswer(guess2,player));
    }

}