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
        bots = new ArrayList<ComputerPlayer>();
        cards = new ArrayList<Card>();
        model = new Model();

        cards.add(new Suspect("Ryan"));
        cards.add(new Weapon("knife"));
        cards.add(new Location("Park"));


       Guess testguess = new Guess(cards.get(0),cards.get(1), new Weapon("potato"),false);
       testguess.printGuess();
    } // main

} // Clue