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
    // PURPOSE: prompts the user for how many computer
    //      oppenents they would like to play with.
    //      Creates the required players, cards and the model
    //      and calls model to begin the game.
    //------------------------------------------------------
    public static void main(String[] args){

        setup();
        createSuspectCards();
        createLocationCards();
        createWeaponCards();

        model = new Model();
        model.playGame(totalPlayers,people,places,weapons,bots,human);

    } // main

    private static void setup(){
        int howManyBots;
        scanner = new Scanner(System.in);
        people = new ArrayList<Card>();
        places = new ArrayList<Card>();
        weapons = new ArrayList<Card>();

        bots = new ArrayList<ComputerPlayer>();
        human = new HumanPlayer();

        System.out.print("How many computer players would you like to play against? ");
        howManyBots = scanner.nextInt();
        System.out.println();

        while(howManyBots < 1){
            System.out.println("Invalid entry. Try again.");
            howManyBots = scanner.nextInt();
        }

        totalPlayers = howManyBots + 1;

        for(int i = 0; i < howManyBots; i++){
            bots.add(new ComputerPlayer());
        }

    }

    public static void createSuspectCards(){
        people.add(new Suspect("IronMan"));
        people.add(new Suspect("Captain America"));
        people.add(new Suspect("Black Widow"));
        people.add(new Suspect("Hulk"));
        people.add(new Suspect("Thor"));
        people.add(new Suspect("Hawkeye"));
    }

    public static void createLocationCards(){
        places.add(new Location("NewYork City"));
        places.add(new Location("Manhattan"));
        places.add(new Location("Volgograd"));
        places.add(new Location("Dayton"));
        places.add(new Location("Asgard"));
        places.add(new Location("Waverly"));
    }

    public static void createWeaponCards(){
        weapons.add(new Weapon("Intelligence and money"));
        weapons.add(new Weapon("Vibranium Shield"));
        weapons.add(new Weapon("Widows Bite"));
        weapons.add(new Weapon("Superhuman Strength"));
        weapons.add(new Weapon("Mjolnir"));
        weapons.add(new Weapon("Bow & Arrow"));
    }
} // Clue