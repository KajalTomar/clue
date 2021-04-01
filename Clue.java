//-----------------------------------------
// NAME		: Kajal Tomar
// STUDENT NUMBER	: 7793306
// COURSE		: COMP 2150
// INSTRUCTOR	: Mike Domaratzki
// ASSIGNMENT	: assignment 3
// QUESTION	: question 1
//
// REMARKS: This is an implementation of
// 'whodunit?', a game that involves deduction
// to determine the 'who, where and how' of a murder.
// Each player makes guesses about the crime,
// and then other players refute that guess,
// if possible, based on information they have on cards
// that have been dealt to them.
// This is a text-based hame that allows
// one human player to play againse a
// group of computer players.
//
// * taken from the assignment description
//-----------------------------------------
import java.util.ArrayList;

public class Clue{

    private static HumanPlayer player;
    private static ArrayList<ComputerPlayer> bots;
    private static ArrayList<Card> people;
    private static ArrayList<Card> places;
    private static ArrayList<Card> weapons;
    private static ArrayList<Card> cards;
    private static Model model;

    //------------------------------------------------------
    // main
    //
    // PURPOSE: prompts the user for how many computer
    //      oppenents they would like to play with.
    //      Creates the required players, cards and the model
    //      and calls model to begin the game.
    //------------------------------------------------------
    public static void main(String[] args){
        player = new HumanPlayer();
        ComputerPlayer testBot = new ComputerPlayer();

        bots = new ArrayList<ComputerPlayer>();
        people = new ArrayList<Card>(); // Bob, Timmy, Lori, John, Ryan
        places = new ArrayList<Card>(); // China, Polopark, Paris, Kitchen
        weapons = new ArrayList<Card>(); // Knife, CharmingSmile, Gun

        cards = new ArrayList<Card>(); // Knife, CharmingSmile, Gun
        model = new Model();

        // an original copy of each category of cards that you do not destroy
        // copy the piles to give one to each player, make as many copies as computer players (this is liek that card you get to write all over in clue)
        // shuffle it up and distribute it

        people.add(new Suspect("Bob"));
        people.add(new Suspect("Timmy"));
        people.add(new Suspect("Lori"));
        people.add(new Suspect("Mindy"));


   //     System.out.println("Suspect list size: "+people.size());

        places.add(new Location("China"));
        places.add(new Location("Polopark"));
        places.add(new Location("Paris"));
        places.add(new Location("Kitchen"));

 //      System.out.println("Places list size: "+places.size());

        weapons.add(new Weapon("Knife"));
        weapons.add(new Weapon("CharmingSmile"));
        weapons.add(new Weapon("Gun"));

//        System.out.println("Weapons list size: "+weapons.size());

        cards.addAll(people);
        cards.addAll(places);
        cards.addAll(weapons);

//        System.out.println("Total card list size: "+cards.size());

        testBot.setUp(2,0,people,places,weapons);
        player.setUp(2,1,people,places,weapons);

        player.setCard(people.get(0)); // Bob
        player.setCard(places.get(2)); // paris
        player.setCard(places.get(3)); // Kitchen
        player.setCard(weapons.get(2)); // Gun

        Guess newGuess = new Guess(people.get(0),places.get(3),weapons.get(2),false);

        Card botShowed = player.canAnswer(newGuess,testBot);

        if(botShowed!=null) {
            botShowed.printCard();
        }

        player.getGuess();

    } // main

} // Clue