//-----------------------------------------
// CLASS: ComputerPlayer.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS:
//
//-----------------------------------------

import java.util.ArrayList;

public class ComputerPlayer implements IPlayer{

    public ComputerPlayer(){

    }
    private int numberOfOpponents;
    private int index;
    private ArrayList<Card> suspects;
    private ArrayList<Card> possibleLocations;
    private ArrayList<Card> possibleWeapons;

    private ArrayList<Suspect> myPeopleCards;
    private ArrayList<Location> myLocationCards;
    private ArrayList<Weapon> myWeaponCards;

    //---------------------------------------------------------------
    // Guess
    //
    // PURPOSE: sets up the information that a computer player has about
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
        this.suspects = ppl;
        this.possibleLocations = places;
        this.possibleWeapons = weapons;
    } // setUp

    //------------------------------------------------------
    // setCard
    //
    // PURPOSE: gets the card that this player has been
    //      dealt.
    //      Add this card to a list of own on cards.
    //      Remove this card from the total list of cards
    //      possible whoDunIt answers.
    //
    //      Returns: suspect (Card) or null
    //      if we are missing the suspect card.
    //------------------------------------------------------
    public void setCard (Card c){
        int indexofCard;

        if (c instanceof Suspect){
            myPeopleCards.add((Suspect)c);
            indexofCard = suspects.indexOf(c);
            suspects.remove(c);
        }
        else if(c instanceof Location){
            myLocationCards.add((Location)c);
            indexofCard = possibleLocations.indexOf(c);
            possibleLocations.remove(c);
        }
        else if (c instanceof Weapon){
            myWeaponCards.add((Weapon)c);
            indexofCard = possibleWeapons.indexOf(c);
            possibleWeapons.remove(indexofCard);
        }
    } // setCard

    //------------------------------------------------------
    // getIndex
    //
    // PURPOSE: returns this player's index
    //
    // Returns: index (int)
    //------------------------------------------------------
    public int getIndex(){
        return index;
    } // getIndex

    //------------------------------------------------------
    // canAnswer
    //
    // PURPOSE: returns this player's index
    //
    // Returns: index (int)
    //------------------------------------------------------
    public Card canAnswer(Guess g, IPlayer ip){
        return null;
    } // canAnswer

    public Guess getGuess(){
        return null;
    } // getGuess

    public void receiveInfo(IPlayer ip, Card c){

    } // reveiveInfo


} // ComputerPlayer