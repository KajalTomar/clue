//-----------------------------------------
// CLASS: ComputerPlayer.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS:
//
//-----------------------------------------

import java.util.*;

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
        ArrayList<Card> canShow = cardsIcanShow(g);
        Card toShow = null;
        Random random = new Random();
        int choice;

        if(canShow.size()>0) {
            if (canShow.size() == 1) {
                toShow = canShow.get(0);
            } else {
                choice = random.nextInt(canShow.size());
                toShow = canShow.get(choice);
            }
        }

        return toShow;
    } // canAnswer

    public Guess getGuess(){
        Guess myGuess = null;
        Suspect personGuess;
        Location placeGuess;
        Weapon weaponGuess;
        Random random = new Random();
        int choice;

        if(suspects.size() > 0  && locations.size() > 0 && weapons.size() > 0) {
            if (suspects.size() == 1 && locations.size() == 1 && weapons.size() == 1) {
                myGuess = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
            } else {
                choice = random.nextInt(suspects.size());
                personGuess = suspects.get(choice);

                choice = random.nextInt(locations.size());
                placeGuess = locations.get(choice);

                choice = random.nextInt(weapons.size());
                weaponGuess = weapons.get(choice);

                myGuess = new Guess(personGuess, placeGuess, weaponGuess, false);
            }
        }

        return myGuess;

    } // getGuess

    public void receiveInfo(IPlayer ip, Card c){
        int indexofCard;

        if(ip!= null && c!= null){
            if (c instanceof Suspect) {
                indexofCard = suspects.indexOf(c);
                suspects.remove(c);
            } else if (c instanceof Location) {
                indexofCard = locations.indexOf(c);
                locations.remove(c);
            } else if (c instanceof Weapon) {
                indexofCard = weapons.indexOf(c);
                weapons.remove(indexofCard);
            }
        }

    } // reveiveInfo

    private ArrayList<Card> cardsIcanShow(Guess g){
        ArrayList<Card> canShow = new ArrayList<Card>();
        Suspect suspectGuess = (Suspect) g.guessedSuspect();
        Location locationGuess = (Location) g.guessedLocation();
        Weapon weaponGuess = (Weapon) g.guessedWeapon();
        int indexofCard;

        if(suspectGuess!= null && locationGuess!= null && weaponGuess != null) {
            if (myPeopleCards.contains(suspectGuess)) {
                indexofCard = myPeopleCards.indexOf(suspectGuess);
                canShow.add(myPeopleCards.get(indexofCard));
            }

            if (myLocationCards.contains(locationGuess)) {
                indexofCard = myLocationCards.indexOf(locationGuess);
                canShow.add(myLocationCards.get(indexofCard));
            }

            if (myWeaponCards.contains(weaponGuess)) {
                indexofCard = myWeaponCards.indexOf(weaponGuess);
                canShow.add(myWeaponCards.get(indexofCard));
            }
        }

        return canShow;

    }

} // ComputerPlayer