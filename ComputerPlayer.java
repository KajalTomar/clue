//-----------------------------------------
// CLASS: ComputerPlayer.java
//
// Author: Kajal Tomar
//
// REMARKS: implementaion of the computer
//  player
//
//-----------------------------------------
import java.util.*;

public class ComputerPlayer implements IPlayer{
    private ArrayList<Suspect> suspects;    
    private ArrayList<Location> locations;
    private ArrayList<Weapon> weapons;

    private ArrayList<Suspect> myPeopleCards;
    private ArrayList<Location> myLocationCards;
    private ArrayList<Weapon> myWeaponCards;

    private int numberOfPlayers;
    private int index;

    //------------------------------------------------------
    // ComputerPlayer
    //
    // PURPOSE: constructor for the ComputerPlayer class
    //------------------------------------------------------
    public ComputerPlayer(){
        suspects  = new ArrayList<Suspect>();
        locations = new ArrayList<Location>();
        weapons = new ArrayList<Weapon>();

        myPeopleCards = new ArrayList<Suspect>();
        myLocationCards = new ArrayList<Location>();
        myWeaponCards = new ArrayList<Weapon>();
    }

    //---------------------------------------------------------------
    // setUp
    //
    // PURPOSE: adds the suspects,location, and weapon cards to
    //          three lists. Also sets the numberOfPlayers,
    //          index
    //
    // PARAMETERS: numPlayers (int)     - the number of opponents
    //             index (int)          - what player number this
    //                                      player is
    //             ppl (ArrayList<card>)- list of Suspect cards
    //             places (ArrayList<card>)- list of Location cards
    //             weapons (ArrayList<card>)- list of Weapon cards
    //----------------------------------------------------------------
    public void setUp( int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        numberOfPlayers = numPlayers;
        this.index = index;

        // add the suspect cards to our list
        for(int i = 0; i < ppl.size(); i++){

            // just to make sure that our suspect pile
            // ONLY has Suspect cards
            if(ppl.get(i) instanceof Suspect) {
                suspects.add((Suspect)ppl.get(i));
            }
        }

        // add the location cards to our list
        for(int i = 0; i < places.size(); i++){

            // just to make sure that our location pile
            // ONLY has location cards
            if(places.get(i) instanceof Location) {
                locations.add((Location)places.get(i));
            }
        }

        // add the weapons cards to our list
        for(int i = 0; i < weapons.size(); i++){

            // just to make sure that our weapons pile
            // ONLY has weapons cards
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
    //      Add this card to a list of cards that we have
    //      been dealt.
    //      Remove this card from the total list of cards
    //      possible whoDunIt answers.
    //
    // PARAMETERS: a card
    //------------------------------------------------------
    public void setCard (Card c){
        int indexofCard;

        // check if this is a suspect card
        if (c instanceof Suspect){

            // add to the list of my suspects
            myPeopleCards.add(((Suspect)c));

            // if it's in the list of possible suspects
            // then remove it from there
            indexofCard = suspects.indexOf(c);
            if(indexofCard >= 0) {
                suspects.remove(c);
            }
        }
        else if(c instanceof Location){    /* check if this is a location card */

            // add to the list of my location
            myLocationCards.add((Location)c);

            // if it's in the list of possible locations
            // then remove it from there
            indexofCard = locations.indexOf(c);
            if(indexofCard >= 0) {
                locations.remove(c);
            }
        }

        // check if this is a weapon card
        else if (c instanceof Weapon){

            // add to the list of my weapon
            myWeaponCards.add((Weapon)c);

            // if it's in the list of possible weapons
            // then remove it from there
            indexofCard = weapons.indexOf(c);
            if(indexofCard >= 0) {
                weapons.remove(indexofCard);
            }
        }

    } // setCard

    //------------------------------------------------------
    // getIndex
    //
    // PURPOSE: returns this player's index
    // Returns: index (int)
    //------------------------------------------------------
    public int getIndex(){
        return index;
    } // getIndex

    //------------------------------------------------------
    // canAnswer
    //
    // PURPOSE: pick a card that 'answers' the guess and
    //          send it if one exists.
    // Returns: return a card (which the current player
    // must have in their hand) or null, to represent that
    // the current player cannot answer that guess.
    //------------------------------------------------------
    public Card canAnswer(Guess g, IPlayer ip){
        Card toShow = null; // the card I chose to show
        Random random = new Random();
        int choice; // to pick a card out of many

        // call the cardsIcanShow method
        // which will return an ArrayList of all the
        // cards I have that match the guess card
        ArrayList<Card> canShow = cardsIcanShow(g);

        // if I can show at least 1 card
        if(canShow.size()>0) {

            // if I can only show one card then
            // show that one
            if (canShow.size() == 1) {
                toShow = canShow.get(0);
            } else {    /* can show multiple cards */

                // pick a random number we can choose a random
                // card to show
                choice = random.nextInt(canShow.size());
                toShow = canShow.get(choice);
            }
        }

        return toShow;

    } // canAnswer

    //------------------------------------------------------
    // getGuess
    //
    // PURPOSE:  If this method is called, it indicates that
    // it is the current player’s turn. The method should return
    // the current player’s guess for that turn.
    // The guess may be a suggestion or an accusation.
    //
    // Returns: a guess
    //------------------------------------------------------
    public Guess getGuess(){
        Guess myGuess = null; // will hold the guess
        Suspect personGuess;
        Location placeGuess;
        Weapon weaponGuess;
        Random random = new Random();
        int choice;

        // this will always be true but just in case
        if(suspects.size() > 0  && locations.size() > 0 && weapons.size() > 0) {

            // if there is exactly one possible who,where,how cards left
            if (suspects.size() == 1 && locations.size() == 1 && weapons.size() == 1) {

                // then make a accusation!
                myGuess = new Guess(suspects.get(0), locations.get(0), weapons.get(0), true);
            } else { /* there are more than once choices for at lease one category*/

                // pick a random index within the list of possible suspects
                // and select that one for the guess
                choice = random.nextInt(suspects.size());
                personGuess = suspects.get(choice);

                // pick a random index within the list of possible locations
                // and select that one for the guess
                choice = random.nextInt(locations.size());
                placeGuess = locations.get(choice);

                // pick a random index within the list of possible weapons
                // and select that one for the guess
                choice = random.nextInt(weapons.size());
                weaponGuess = weapons.get(choice);

                // create the guess!
                myGuess = new Guess(personGuess, placeGuess, weaponGuess, false);
            }
        }

        return myGuess;

    } // getGuess

    //------------------------------------------------------
    // receiveInfo
    //
    // PURPOSE: recieves information about the card
    //          someone showed us. If it isn't null and the card is
    //          our possible who,when,how then remove it from it.
    //          Display that an answer was recieved.
    // PARAMETER: a player ip (not an index) and a card
    //------------------------------------------------------
    public void receiveInfo(IPlayer ip, Card c){
        int indexofCard; // to find the card in our pile

        // If a player was able to answer
        // information about this player's previous guess,
        if(ip!= null && c!= null){

            System.out.println("Player "+ip.getIndex()+" answered.");

            // Figure out what kind of card they showed us
            // and remove it from our list.

            // is it a suspect card?
            if (c instanceof Suspect) {

                // remove it from possible whos
                indexofCard = suspects.indexOf(c);
                suspects.remove(c);
            }
            else if (c instanceof Location) { /* is it a Location card? */

                // remove it from possible wheres
                indexofCard = locations.indexOf(c);
                locations.remove(c);
            }
            else if (c instanceof Weapon) { /* is it a Weapon card? */

                // remove it from possible hows
                indexofCard = weapons.indexOf(c);
                weapons.remove(indexofCard);
            }
        }
        else {    /* no one was able to answer your guess */
            System.out.println("No one could answer");
        }

    } // reveiveInfo

    //------------------------------------------------------
    // cardsIcanShow
    //
    // PURPOSE: create a list of cards that match the guess
    // return: list of cards that matches the guess
    //------------------------------------------------------
    private ArrayList<Card> cardsIcanShow(Guess g){
        ArrayList<Card> canShow = new ArrayList<Card>();
        int indexofCard;

        // disect each type of card from the guess
        Suspect suspectGuess = (Suspect) g.guessedSuspect();
        Location locationGuess = (Location) g.guessedLocation();
        Weapon weaponGuess = (Weapon) g.guessedWeapon();

        // make sure then none of the cards are null, just for safety
        if(suspectGuess!= null && locationGuess!= null && weaponGuess != null) {

            // do I have this suspect card in my hand?
            if (myPeopleCards.contains(suspectGuess)) {

                // yes! add it to the list of cards that match guess
                indexofCard = myPeopleCards.indexOf(suspectGuess);
                canShow.add(myPeopleCards.get(indexofCard));
            }

            // do I have this location card in my hand?
            if (myLocationCards.contains(locationGuess)) {

                // yes! add it to the list of cards that match guess
                indexofCard = myLocationCards.indexOf(locationGuess);
                canShow.add(myLocationCards.get(indexofCard));
            }

            // do I have this weapon card in my hand?
            if (myWeaponCards.contains(weaponGuess)) {

                // yes! add it to the list of cards that match guess
                indexofCard = myWeaponCards.indexOf(weaponGuess);
                canShow.add(myWeaponCards.get(indexofCard));
            }
        }

        return canShow;

    }

} // ComputerPlayer
