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
    }

    @Test
    public void test1() {
        // If a computer player has no cards, then canAnswer should return null.

        bot.setUp(2,0,people,places,weapons);

        Guess guess = new Guess(new Suspect("Bobby"),new Location("Kitchen"), new Weapon("Gun"),false);
        assertNull(bot.canAnswer(guess, player));
    }

    @Test
    public void test2() {
//        If a computer player has exactly one card from a guess, canAnswer should return that
//        card.


                // these are the cards the computer will have
        Card joe = new Suspect("Joe");
        Card livingRoom = new Location("Living room");
        Card knife = new Weapon("Knife");

        bot.setUp(2,0,people,places,weapons);

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
       // If a computer player has more than one card from a guess, canAnswer should return one of the cards.


                Boolean matches;

        // these are the cards the computer will have
        Card joe = new Suspect("Joe");
        Card livingRoom = new Location("Living room");
        Card knife = new Weapon("Knife");

        bot.setUp(2,0,people,places,weapons);

        bot.setCard(joe);
        bot.setCard(livingRoom);
        bot.setCard(knife);


        // has only suspect and location
        Guess guess0 = new Guess(joe,livingRoom, new Weapon("Gun"),false);
        Card result0 = bot.canAnswer(guess0,player);
        matches = (result0 == joe || result0 == livingRoom);
        assertTrue(matches);

        // has only suspect and weapon
        Guess guess1 = new Guess(joe,new Location("bedroom"),knife,false);
        Card result1 = bot.canAnswer(guess1,player);
        matches = (result1 == joe || result1 == knife);
        assertTrue(matches);


        // has only the weapon and location
        Guess guess2 = new Guess(new Suspect("Martha"),livingRoom,knife,false);
        Card result2 = bot.canAnswer(guess0,player);
        matches = (result2 == knife || result2 == livingRoom);
        assertTrue(matches);

        // has all three of the cards
        Guess guess3 = new Guess(joe,livingRoom,knife,false);
        Card result3 = bot.canAnswer(guess0,player);
        matches = (result3 == joe || result3 == livingRoom || result3 == knife);
        assertTrue(matches);
    }

    @Test
    public void test4(){
//        If a computer player is given all but n cards (for some number n > 2 that you should
//        choose) from the set of cards, a call to getGuess should return a guess that does not
//        contain any of the cards that the player has been given. That is, an initial guess from a
//        computer player must consist of cards it does not have.
        boolean matches;

        people.add(new Suspect("Bob"));
        people.add(new Suspect("Timmy"));
        people.add(new Suspect("Lori"));
        people.add(new Suspect("Mindy"));

        places.add(new Location("Kitchen"));
        places.add(new Location("Bathroom"));
        places.add(new Location("LivingRoom"));
        places.add(new Location("Garage"));

        weapons.add(new Weapon("Knife"));
        weapons.add(new Weapon("CandleStick"));
        weapons.add(new Weapon("Gun"));

        bot.setUp(2,0,people,places,weapons);

        // give bot all the player cards except the last one (Mindy)
        for(int i=0; i<people.size()-1;i++){
            bot.setCard(people.get(i));
        }

        // give bot all the location cards except the first one (kitchen)
        for(int i=1; i<places.size();i++){
            bot.setCard(places.get(i));
        }

        // give bot all the weapons cards except the first and the last one (knife,gun)
        for(int i=1; i<weapons.size()-1;i++){
            bot.setCard(weapons.get(i));
        }

        Guess botGuess = bot.getGuess();

        // the guess can only be [Mindy,kitchen,gun]or [Mindy,kitchen,knife] because the computer has all the other cards
        assertEquals(botGuess.guessedSuspect(), people.get(people.size()-1));
        assertEquals(botGuess.guessedLocation(), places.get(0));

        matches = (botGuess.guessedWeapon() == weapons.get(0) || botGuess.guessedWeapon() == weapons.get(weapons.size()-1));
        assertTrue(matches);

        assertFalse(botGuess.isAccusation());
    }

    @Test
    public void test5(){
//        If a computer player is given all but three cards from the set of cards, a call to
//        getGuess should return the correct accusation (not a suggestion).
        boolean matches;

        people.add(new Suspect("Bob"));
        people.add(new Suspect("Timmy"));
        people.add(new Suspect("Lori"));
        people.add(new Suspect("Mindy"));

        places.add(new Location("Kitchen"));
        places.add(new Location("Bathroom"));
        places.add(new Location("LivingRoom"));
        places.add(new Location("Garage"));

        weapons.add(new Weapon("Knife"));
        weapons.add(new Weapon("CandleStick"));
        weapons.add(new Weapon("Gun"));

        bot.setUp(2,0,people,places,weapons);

        // give bot all the player cards except the last one (Mindy)
        for(int i=0; i<people.size()-1;i++){
            bot.setCard(people.get(i));
        }

        // give bot all the location cards except the first one (kitchen)
        for(int i=1; i<places.size();i++){
            bot.setCard(places.get(i));
        }

        // give bot all the weapons cards except the first (knife)
        for(int i=1; i<weapons.size();i++){
            bot.setCard(weapons.get(i));
        }

        Guess botGuess = bot.getGuess();

        // the accusation can only be [Mindy,kitchen,knife] because the computer has all the other cards
        assertEquals(botGuess.guessedSuspect(), people.get(people.size()-1));
        assertEquals(botGuess.guessedLocation(), places.get(0));
        assertEquals(botGuess.guessedWeapon(), weapons.get(0));
        assertTrue(botGuess.isAccusation());
    }

    @Test
    public void test6() {
//        If a computer player is given all but four cards from the set of cards, a call to getGuess
//        should not return an accusation. However, if receiveInfo is called with one of the four
//        cards, then after that, a second call to getGuess should return the correct accusation.
        boolean matches;

        people.add(new Suspect("Bob"));
        people.add(new Suspect("Timmy"));
        people.add(new Suspect("Lori"));
        people.add(new Suspect("Mindy"));

        places.add(new Location("Kitchen"));
        places.add(new Location("Bathroom"));
        places.add(new Location("LivingRoom"));
        places.add(new Location("Garage"));

        weapons.add(new Weapon("Knife"));
        weapons.add(new Weapon("CandleStick"));
        weapons.add(new Weapon("Gun"));

        bot.setUp(2, 0, people, places, weapons);

        // give bot all the player cards except the last one (Mindy)
        for (int i = 0; i < people.size() - 1; i++) {
            bot.setCard(people.get(i));
        }

        // give bot all the location cards except the first one (kitchen)
        for (int i = 1; i < places.size(); i++) {
            bot.setCard(places.get(i));
        }

        // give bot all the weapons cards except the first and the last one (knife,gun)
        for (int i = 1; i < weapons.size() - 1; i++) {
            bot.setCard(weapons.get(i));
        }

        Guess botGuess = bot.getGuess();

        // the guess can only be [Mindy,kitchen,gun]or [Mindy,kitchen,knife] because the computer has all the other cards
        assertEquals(botGuess.guessedSuspect(), people.get(people.size() - 1));
        assertEquals(botGuess.guessedLocation(), places.get(0));

        matches = (botGuess.guessedWeapon() == weapons.get(0) || botGuess.guessedWeapon() == weapons.get(weapons.size() - 1));
        assertTrue(matches);

        assertFalse(botGuess.isAccusation());

        // gets info about the gun card
        bot.receiveInfo(player, weapons.get(weapons.size() - 1));

        botGuess = bot.getGuess();

        // the accusation can only be [Mindy,kitchen,knife] because the computer has all the other cards
        assertEquals(botGuess.guessedSuspect(), people.get(people.size() - 1));
        assertEquals(botGuess.guessedLocation(), places.get(0));
        assertEquals(botGuess.guessedWeapon(), weapons.get(0));
        assertTrue(botGuess.isAccusation());
    }

    @Test
    public void test7() {
//        If a human player is given some cards, and then canAnswer is called with a guess that
//        includes one (or more) of the cards the player has, the method must return one of
//        those cards (that is, the human player cannot give a card that they do not have in their
//        hand)
        boolean matches;

        people.add(new Suspect("Bob"));
        people.add(new Suspect("Timmy"));
        people.add(new Suspect("Lori"));
        people.add(new Suspect("Mindy"));

        places.add(new Location("Kitchen"));

        weapons.add(new Weapon("Knife"));


        bot.setUp(2, 0, people, places, weapons);
        player.setUp(2, 1, people, places, weapons);


        // player only has the cards: Bobby, Kitchen, and Mindy in their hand
        player.setCard(people.get(0));
        player.setCard(places.get(0));
        player.setCard(weapons.get(0));

        Guess guess = new Guess(people.get(2),places.get(0),weapons.get(0),false);

        // should only be able to show ktichen or knife. NOT Lori because player doesn't have that card
        Card humanAnswer = player.canAnswer(guess,bot);

        //    the human can return anything EXCEPT mindy
        assertNotEquals(humanAnswer, (Card)(people.get(2)));

        matches = (humanAnswer == (Card)places.get(0)|| humanAnswer == (Card)weapons.get(0));
        assertTrue(matches);
    }

}



