//-----------------------------------------
// CLASS: HumanPlayer.java
//
// Author: Kajal Tomar
// REMARKS: implements the Iplayer class.
//      provides methods for the human player.
//
//-----------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

public class HumanPlayer implements IPlayer{
    private ArrayList<Suspect> suspects;
    private ArrayList<Location> locations;
    private ArrayList<Weapon> weapons;

    private ArrayList<Suspect> myPeopleCards;
    private ArrayList<Location> myLocationCards;
    private ArrayList<Weapon> myWeaponCards;

    private Scanner scanner;
    private int numberOfOpponents;
    private int index;

    //------------------------------------------------------
    // HumanPlayer
    //
    // PURPOSE: constructor for the HumanPlayer class
    //------------------------------------------------------
    public HumanPlayer(){
        suspects  = new ArrayList<Suspect>();
        locations = new ArrayList<Location>();
        weapons = new ArrayList<Weapon>();

        myPeopleCards = new ArrayList<Suspect>();
        myLocationCards = new ArrayList<Location>();
        myWeaponCards = new ArrayList<Weapon>();

        scanner = new Scanner(System.in);
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
        numberOfOpponents = numPlayers-1;
        this.index = index;

        System.out.print("Here are the names of all the suspects: [ ");

        // add the suspect cards to our list
        for(int i = 0; i < ppl.size()-1; i++){

            // just to make sure that our suspect pile
            // ONLY has Suspect cards
            if(ppl.get(i) instanceof Suspect) {
                suspects.add((Suspect)ppl.get(i));
                System.out.print(ppl.get(i).getValue()+", ");
            }
        }

        suspects.add((Suspect)ppl.get(ppl.size()-1));
        System.out.print(ppl.get(ppl.size()-1).getValue()+ " ]");

        System.out.print("\nHere are all the locations: [ ");

        // add the location cards to our list
        for(int i = 0; i < places.size()-1; i++){

            // just to make sure that our location pile
            // ONLY has location cards
            if(places.get(i) instanceof Location) {
                locations.add((Location)places.get(i));
                System.out.print(places.get(i).getValue()+", ");
            }
        }

        locations.add((Location)places.get(places.size()-1));
        System.out.print(places.get(places.size()-1).getValue()+" ]");

        System.out.print("\nHere are all the weapons: [ ");

        // add the weapons cards to our list
        for(int i = 0; i < weapons.size()-1; i++){

            // just to make sure that our weapons pile
            // ONLY has weapons cards
            if(weapons.get(i) instanceof Weapon) {
                (this.weapons).add((Weapon)weapons.get(i));
                System.out.print(weapons.get(i).getValue()+", ");
            }
        }

        (this.weapons).add((Weapon)weapons.get(weapons.size()-1));
        System.out.print(weapons.get(weapons.size()-1).getValue()+" ]\n\n");

    } // setUp

    //------------------------------------------------------
    // setCard
    //
    // PURPOSE: gets the card that this player has been
    //      dealt.
    //      Add this card to a list of cards that we have
    //      been dealt.
    //
    // PARAMETERS: a card
    //------------------------------------------------------
    public void setCard (Card c){
        int indexofCard;

        System.out.print("You received the card ");
        c.printCard();

        // check if this is a suspect card
        if (c instanceof Suspect){

            // add to the list of my suspects
            myPeopleCards.add(((Suspect)c));
            indexofCard = suspects.indexOf(c);
        }
        else if(c instanceof Location){ /* check if this is a location card */

            // add to the list of my locations
            myLocationCards.add((Location)c);
            indexofCard = locations.indexOf(c);
        }
        else if (c instanceof Weapon){  /* check if this is a weapon card */

            // add to the list of my Weapons
            myWeaponCards.add((Weapon)c);
            indexofCard = weapons.indexOf(c);
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
    // PURPOSE: see if we can answer the guess card,
    //      if we CAN'T then display this info. If we can
    //      but there's only one card we can show then show it.
    //      If there are more than one card to show, then prompt
    //      the user for which one they wanna show.
    // Returns: return a card (which the current player
    // must have in their hand) or null, to represent that
    // the current player cannot answer that guess.
    //------------------------------------------------------
    public Card canAnswer(Guess g, IPlayer ip){
        Card toShow = null; // the card the player chose to show
        int choice; // to pick a card out of many

        // call the cardsIcanShow method
        // which will return an ArrayList of all the
        // cards I have that match the guess card
        ArrayList<Card> canShow = cardsIcanShow(g);

        System.out.println("Player "+ip.getIndex()+" asked you about: ");
        g.printGuess();

        // if we none of the cards in the human's hand answer guess
        if(canShow.size()==0){
            System.out.print(", but you couldn't answer.\n");
        }
        else if(canShow.size() == 1){ /* exactly one card from our hand answers the guess */

            // display this info and pick this card to show
            System.out.print(" , you showed them the only have card you have: ");
            toShow = canShow.get(0);
            toShow.printCard();
        }
        else { /* multiple cards from our hand answers the guess */

            System.out.print(". Which do you show?\n");

            // display all the options
            for(int i = 0; i < canShow.size(); i++){
                System.out.print(i+": ");
                canShow.get(i).printCard();
            }

            // get thier choise
            choice = scanner.nextInt();

            // keep asking until we get a valid choice
            while((choice < 0 || choice >= canShow.size()) ){
                System.out.println("invalid choice, try again");
                choice = scanner.nextInt();
            }

            // pick this card as the one to show
            toShow = canShow.get(choice);
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
        Guess theGuess = null; // will hold the guess
        int suspect, crimeScene, weapon; // their choice for item they want to make the guess with
        String accusationAnswer;
        boolean accusation = false; // are they making an accusation


        System.out.println("It is your turn.");

        // print out all the possible suspects to make a guess with
        System.out.println("Which person do you want to suggest?");
        printAllSuspects();

        // get the person they chose for the guess
        suspect = scanner.nextInt();

        // keep asking until they pick a valid number
        while((suspect < 0 || suspect >= suspects.size()) ){
            System.out.println("invalid choice, try again ");
            suspect = scanner.nextInt();
        }

        // print out all the possible locations to make a guess with
        System.out.println("Which location do you want to suggest?");
        printAllLocations();

        // get the location they chose for the guess
        crimeScene = scanner.nextInt();

        // keep asking until they pick a valid number
        while(crimeScene < 0 || crimeScene >= locations.size()){
            System.out.println("invalid choice, try again");
            crimeScene = scanner.nextInt();
        }

        // print out all the possible weapons to make a guess with
        System.out.println("Which weapon do you want to suggest?");
        printAllWeapons();

        // get the weapon they chose for the guess
        weapon = scanner.nextInt();

        // keep asking until they pick a valid number
        while(weapon < 0 || weapon >= weapons.size()){
            System.out.println("invalid choice, try again");
            weapon = scanner.nextInt();
        }

        scanner.nextLine(); //throw away the \n not consumed by nextInt()

        // ask if they want to make an accusation
        System.out.println("Is this an accusation (Y/[N])?");
        accusationAnswer = scanner.nextLine();

        // keep asking until they enter a valid entry
        while(!(accusationAnswer.compareTo("y") == 0 || accusationAnswer.compareTo("Y") == 0 || accusationAnswer.compareTo("yes") == 0 || accusationAnswer.compareTo("n") == 0 || accusationAnswer.compareTo("N") == 0|| accusationAnswer.compareTo("no") == 0)){
            System.out.println("invalid choice, try again");
            accusationAnswer = scanner.nextLine();
        }

        // if they answered yes, set accusation to true
        if(accusationAnswer.compareTo("y") == 0 || accusationAnswer.compareTo("yes") == 0 || accusationAnswer.compareTo("Y") == 0 ){
            accusation = true;
        }

        // create the guess with this info and print it out!
        theGuess = new Guess(suspects.get(suspect),locations.get(crimeScene),weapons.get(weapon),accusation);
        System.out.print("you made a(n) ");
        theGuess.printGuess();

        return theGuess;

    } // getGuess

    //------------------------------------------------------
    // receiveInfo
    //
    // PURPOSE: recieves information about the card
    //          someone showed us. If it isn't null and the card is
    //          our possible who,when,how then remove it from it.
    //          Display the answer that was recieved.
    // PARAMETER: a player ip (not an index) and a card
    //------------------------------------------------------
    public void receiveInfo(IPlayer ip, Card c){

        if(ip == null && c == null){
            System.out.println("No one could respond to your suggestion.");
        } else {
            System.out.print("Player "+ip.getIndex()+" showed you ");
            c.printCard();
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

            // do human have this suspect card human's my hand?
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

    //------------------------------------------------------
    // printAllSuspects
    //
    // PURPOSE: prints all of the suspects
    //------------------------------------------------------
    private void printAllSuspects() {
        System.out.println("Here are the names of all the suspects:");

        for (int i = 0; i < suspects.size(); i++) {
            System.out.println(i+": "+(suspects.get(i).getValue()));
        }
    }

    //------------------------------------------------------
    // printAllLocations
    //
    // PURPOSE: prints all of the locations
    //------------------------------------------------------
    private void printAllLocations() {
        System.out.println("Here are all the locations: ");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(i+": "+(locations.get(i).getValue()));
        }
    }

    //------------------------------------------------------
    // printAllWeapons
    //
    // PURPOSE: prints all of the weapons
    //------------------------------------------------------
    private void printAllWeapons() {
        System.out.println("Here are all the weapons:");

        for(int i = 0; i < weapons.size(); i++){
            System.out.println(i+": "+(weapons.get(i).getValue()));
        }
    }

} // HumanPlayer
