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
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

public class TestComputerGuess{

    private static HumanPlayer player;
    private static ComputerPlayer bot;
    private static ArrayList<Card> people;
    private static ArrayList<Card> places;
    private static ArrayList<Card> weapons;

    @BeforeEach
    public void setup(){
        player = new HumanPlayer();
        bot = new ComputerPlayer();

        people = new ArrayList<Card>();
        places = new ArrayList<Card>();
        weapons = new ArrayList<Card>();

        bot.setUp(2,0,people,places,weapons);
    }

    @Test
    public void test1() {
        // a computer player has no cards, then canAnser should returh null

        Guess guess = new Guess(new Suspect("Bobby"),new Location("Kitchen"), new Weapon("Gun"),false);
        assertNull(bot.canAnswer(guess, player));
    }

    @Test
    public void test2() {
        // If a computer player has exactly one card from a guess, canAnswer should return that card.

        // these are the cards the computer will have
        Card joe = new Suspect("Joe");
        Card livingRoom = new Location("Living room");
        Card knife = new Weapon("Knife");

        bot.setCard(joe);
        bot.setCard(livingRoom);
        bot.setCard(knife);

        // has only the suspect
        Guess guess0 = new Guess(joe,new Location("Kitchen"), new Weapon("Gun"),false);
        assertEquals(joe,bot.canAnswer(guess0,player));

        // has only the location
        Guess guess1 = new Guess(new Suspect("Bobby"),livingRoom, new Weapon("Rock"),false);
        assertEquals(livingRoom,bot.canAnswer(guess1,player));

        // has only the weapon
        Guess guess2 = new Guess(new Suspect("James"),new Location("Bathroom"),knife,false);
        assertEquals(knife,bot.canAnswer(guess2,player));
    }

    @Test
    public void test3(){
        // If a computer player has exactly one card from a guess, canAnswer should return that card.

        // these are the cards the computer will have
        Card joe = new Suspect("Joe");
        Card livingRoom = new Location("Living room");
        Card knife = new Weapon("Knife");

        bot.setCard(joe);
        bot.setCard(livingRoom);
        bot.setCard(knife);

        // has only suspect and location
//        Guess guess0 = new Guess(joe,livingRoom, new Weapon("Gun"),false);
//        Card result0 = bot.canAnswer(guess0,player);
//        assert(assertEquals(result0,joe)||assertEquals(result0,livingRoom));

//        // has only suspect and weapon
//        Guess guess1 = new Guess(joe,new Location("bedroom"),knife,false);
//        Card result1 = bot.canAnswer(guess1,player);
//        assert(result1 == joe || result1 == knife);
//
//
//        // has only the weapon and location
//        Guess guess2 = new Guess(new Suspect("Martha"),livingRoom,knife,false);
//        Card result2 = bot.canAnswer(guess0,player);
//        assert(result2 == joe);
//
//        // has all three of the cards
//        Guess guess3 = new Guess(joe,livingRoom,knife,false);
//        Card result3 = bot.canAnswer(guess0,player);
//        assert(result3 == joe || result3 == livingRoom);
    }

}