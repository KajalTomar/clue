//-----------------------------------------
// NAME		: Kajal Tomar
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
import java.util.Scanner;

public class Clue{
    private static Scanner scanner;
    private static HumanPlayer human;
    private static ArrayList<ComputerPlayer> bots;

    private static ArrayList<Card> people;
    private static ArrayList<Card> places;
    private static ArrayList<Card> weapons;

    private static ArrayList<Card> cards;
    private static Model model;

    private static int totalPlayers;

    //------------------------------------------------------
    // main
    //
    // PURPOSE: calls the setup method to create the players,
    // the cards, create the model and then call model's
    // playGame model to start the game.
    //------------------------------------------------------
    public static void main(String[] args){

        setup();

        // create all the cards
        createSuspectCards();
        createLocationCards();
        createWeaponCards();

        // make model and start the game
        model = new Model();
        model.playGame(totalPlayers,people,places,weapons,bots,human);

    } // main

    //---------------------------------------------------------------
    // setUp
    //
    // PURPOSE: initilize the lists. ask the human how many players
    //         they want to play against. make that many bots.
    //----------------------------------------------------------------
    private static void setup(){
        int howManyBots;
        scanner = new Scanner(System.in);

        // make lists for the cards
        people = new ArrayList<Card>();
        places = new ArrayList<Card>();
        weapons = new ArrayList<Card>();

        // human and bots
        bots = new ArrayList<ComputerPlayer>();
        human = new HumanPlayer();

        // ask about how many bots the human wants to play with
        System.out.print("How many computer players would you like to play against? ");
        howManyBots = scanner.nextInt();
        System.out.println();

        // if they enter a negative number then keep asking
        while(howManyBots < 1){
            System.out.println("Invalid entry. Try again.");
            howManyBots = scanner.nextInt();
        }

        totalPlayers = howManyBots + 1;

        // create the bots
        for(int i = 0; i < howManyBots; i++){
            bots.add(new ComputerPlayer());
        }

    } // setup

    //---------------------------------------------------------------
    // createSuspectCards
    //
    // PURPOSE: create the suspect cards and add them to the list of
    //          people.
    //
    //----------------------------------------------------------------
    public static void createSuspectCards(){
        // create Suspects and add them to the people list
        people.add(new Suspect("IronMan"));
        people.add(new Suspect("Captain America"));
        people.add(new Suspect("Black Widow"));
        people.add(new Suspect("Hulk"));
        people.add(new Suspect("Thor"));
        people.add(new Suspect("Hawkeye"));
    } // createSuspectCards

    //---------------------------------------------------------------
    // createLocationCards
    //
    // PURPOSE: create the location cards and add them to the list of
    //          places.
    //
    //----------------------------------------------------------------
    public static void createLocationCards(){
        // create Locations and add them to the places list
        places.add(new Location("NewYork City"));
        places.add(new Location("Manhattan"));
        places.add(new Location("Volgograd"));
        places.add(new Location("Dayton"));
        places.add(new Location("Asgard"));
        places.add(new Location("Waverly"));
    } // createLocationCards

    //---------------------------------------------------------------
    // createWeaponCards
    //
    // PURPOSE: create the Weapon cards and add them to the list of
    //          weapons.
    //
    //----------------------------------------------------------------
    public static void createWeaponCards(){
        // create Weapon and add them to the weapons list
        weapons.add(new Weapon("Intelligence and money"));
        weapons.add(new Weapon("Vibranium Shield"));
        weapons.add(new Weapon("Widows Bite"));
        weapons.add(new Weapon("Superhuman Strength"));
        weapons.add(new Weapon("Mjolnir"));
        weapons.add(new Weapon("Bow & Arrow"));
    } // createWeaponCards

} // Clue
