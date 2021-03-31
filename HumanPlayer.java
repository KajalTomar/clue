//-----------------------------------------
// CLASS: HumanPlayer.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS:
//
//-----------------------------------------

import java.util.ArrayList;

public class HumanPlayer extends Player {

    public HumanPlayer(){

    }
    private int numberOfOpponents;
    private int index;
    private ArrayList<Card> people;
    private ArrayList<Card> places;
    private ArrayList<Card> weapons;

    //---------------------------------------------------------------
    // Guess
    //
    // PURPOSE: sets up the information that a player has about
    //      the game.
    //
    // PARAMETERS: numPlayers (int)     - the number of opponents
    //             index (int)          - what player number this
    //                                      player is
    //             ppl (ArrayList<card>)- list of Suspect cards
    //             places (ArrayList<card>)- list of Location cards
    //             weapons (ArrayList<card>)- list of Weapon cards
    //----------------------------------------------------------------
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        numberOfOpponents = numPlayers;
        this.index = index;
        this.people = ppl;
        this.places = places;
        this.weapons = weapons;
    } // setUp

    //------------------------------------------------------
    // setCard
    //
    // PURPOSE: gets the card that this player has been
    //      dealt. Announces this to the player.
    //------------------------------------------------------
    public void setCard (Card c){
        System.out.println("You recieved a "+c.getType()+" card that says\""+ c.getValue()+"\"");
    } // setCard

    //------------------------------------------------------
    // guessedSuspect
    //
    // PURPOSE: returns the suspect card
    //
    // Returns: suspect (Card) or null
    //      if we are missing the suspect card.
    //------------------------------------------------------
    public int getIndex(){
        return index;
    } // getIndex

    public Card canAnswer(Guess g, IPlayer ip){
        return null;
    } // canAnswer

    public Guess getGuess(){
        return null;
    } // getGuess

    public void receiveInfo(IPlayer ip, Card c){

    } // reveiveInfo


} // HumanPlayer