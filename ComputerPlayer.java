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

    private int numberOfOpponents;
    private int index;
    private ArrayList<Suspect> suspects;
    private ArrayList<Location> locations;
    private ArrayList<Weapon> weapons;

    private ArrayList<Suspect> myPeopleCards;
    private ArrayList<Location> myLocationCards;
    private ArrayList<Weapon> myWeaponCards;

    public ComputerPlayer(){
        suspects  = new ArrayList<Suspect>();
        locations = new ArrayList<Location>();
        weapons = new ArrayList<Weapon>();

        myPeopleCards = new ArrayList<Suspect>();
        myLocationCards = new ArrayList<Location>();
        myWeaponCards = new ArrayList<Weapon>();
    }

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
        numberOfOpponents = numPlayers-1;
        this.index = index;

        for(int i = 0; i < ppl.size(); i++){
            if(ppl.get(i) instanceof Suspect) {
                suspects.add((Suspect)ppl.get(i));
            }
        }

        for(int i = 0; i < places.size(); i++){
            if(places.get(i) instanceof Location) {
                locations.add((Location)places.get(i));
            }
        }

        for(int i = 0; i < weapons.size(); i++){
            if(weapons.get(i) instanceof Weapon) {
                (this.weapons).add((Weapon)weapons.get(i));
            }
        }

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
            myPeopleCards.add(((Suspect)c));
            indexofCard = suspects.indexOf(c);
            suspects.remove(c);
        }
        else if(c instanceof Location){
            myLocationCards.add((Location)c);
            indexofCard = locations.indexOf(c);
            locations.remove(c);
        }
        else if (c instanceof Weapon){
            myWeaponCards.add((Weapon)c);
            indexofCard = weapons.indexOf(c);
            weapons.remove(indexofCard);
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
        Suspect suspectGuess = (Suspect) g.guessedSuspect();
        Location locationGuess = (Location) g.guessedLocation();
        Weapon weaponGuess = (Weapon) g.guessedWeapon();
        Card toShow = null;
        int indexofCard;

        if(suspectGuess!= null && locationGuess!= null && weaponGuess != null) {
            if (myPeopleCards.contains(suspectGuess)) {
                indexofCard = myPeopleCards.indexOf(suspectGuess);
                toShow = myPeopleCards.get(indexofCard);
            } else if (myLocationCards.contains(locationGuess)) {
                indexofCard = myLocationCards.indexOf(locationGuess);
                toShow = myLocationCards.get(indexofCard);
            } else if (myWeaponCards.contains(weaponGuess)) {
                indexofCard = myWeaponCards.indexOf(weaponGuess);
                toShow = myWeaponCards.get(indexofCard);
            }
        }

        return toShow;

    } // canAnswer

    public Guess getGuess(){
        return null;
    } // getGuess

    public void receiveInfo(IPlayer ip, Card c){

    } // reveiveInfo


} // ComputerPlayer