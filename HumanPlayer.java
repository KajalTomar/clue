//-----------------------------------------
// CLASS: HumanPlayer.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS:
//
//-----------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

public class HumanPlayer implements IPlayer{
    private int numberOfOpponents;
    private int index;
    private ArrayList<Suspect> suspects;
    private ArrayList<Location> locations;
    private ArrayList<Weapon> weapons;

    private ArrayList<Suspect> myPeopleCards;
    private ArrayList<Location> myLocationCards;
    private ArrayList<Weapon> myWeaponCards;
    private Scanner scanner;


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
        numberOfOpponents = numPlayers-1;
        this.index = index;

        System.out.print("Here are the names of all the suspects: [ ");
        for(int i = 0; i < ppl.size()-1; i++){
            if(ppl.get(i) instanceof Suspect) {
                suspects.add((Suspect)ppl.get(i));
                System.out.print(ppl.get(i).getValue()+", ");
            }
        }

        suspects.add((Suspect)ppl.get(ppl.size()-1));
        System.out.print(ppl.get(ppl.size()-1).getValue()+ " ]");

        System.out.print("\nHere are all the locations: [ ");
        for(int i = 0; i < places.size()-1; i++){
            if(places.get(i) instanceof Location) {
                locations.add((Location)places.get(i));
                System.out.print(places.get(i).getValue()+", ");
            }
        }

        locations.add((Location)places.get(places.size()-1));
        System.out.print(places.get(places.size()-1).getValue()+" ]");

        System.out.print("\nHere are all the weapons: [ ");
        for(int i = 0; i < weapons.size()-1; i++){
            if(weapons.get(i) instanceof Weapon) {
                (this.weapons).add((Weapon)weapons.get(i));
                System.out.print(weapons.get(i).getValue()+", ");
            }
        }

        (this.weapons).add((Weapon)weapons.get(weapons.size()-1));
        System.out.print(weapons.get(weapons.size()-1).getValue()+" ]\n\n");

//        System.out.println("Suspect list size: "+suspects.size());
//        System.out.println("Location list size: "+locations.size());
//        System.out.println("Weapons list size: "+weapons.size());
    } // setUp

    //------------------------------------------------------
    // setCard
    //
    // PURPOSE: gets the card that this player has been
    //      dealt. Announces this to the player.
    //------------------------------------------------------
    public void setCard (Card c){
        int indexofCard;
        System.out.print("You received the card ");
        c.printCard();

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
        ArrayList<Card> canShow = cardsIcanShow(g);
        Card toShow = null;
        int choice;

        System.out.println("Player "+ip.getIndex()+" asked you about: ");
        g.printGuess();

        // show it to them
        if(canShow.size()==0){
            System.out.print(", but you couldn't answer.\n");
        }
        else if(canShow.size() == 1){
            System.out.print(" , you showed them the only have card you have: ");
            toShow = canShow.get(0);
            toShow.printCard();
        }
        else {
            System.out.print(". Which do you show?\n");
            for(int i = 0; i < canShow.size(); i++){
                System.out.print(i+": ");
                canShow.get(i).printCard();
            }


            choice = scanner.nextInt();

            while((choice < 0 || choice >= canShow.size()) ){
                System.out.println("invalid choice");
                choice = scanner.nextInt();
            }

            toShow = canShow.get(choice);
        }

        return toShow;

    } // canAnswer

    public Guess getGuess(){
        Guess theGuess;
        int suspect, crimeScene, weapon;
        String accusationAnswer;
        boolean accusation = false;

        System.out.println("It is your turn.");


        System.out.println("Which person do you want to suggest?");
        printAllSuspects();

        suspect = scanner.nextInt();

//        while((suspect < 0 || suspect >= (suspects.size()+myPeopleCards.size())) ){
        while((suspect < 0 || suspect >= suspects.size()) ){
            System.out.println("invalid choice");
            suspect = scanner.nextInt();
        }

        System.out.println("Which location do you want to suggest?");
        printAllLocations();

        crimeScene = scanner.nextInt();

//        while((crimeScene < 0 || crimeScene >= (locations.size())+myLocationCards.size())){
        while(crimeScene < 0 || crimeScene >= locations.size()){
            System.out.println("invalid choice");
            crimeScene = scanner.nextInt();
        }

        System.out.println("Which weapon do you want to suggest?");
        printAllWeapons();

        weapon = scanner.nextInt();

//        while((weapon < 0 || weapon >= (weapons.size()+myWeaponCards.size())) ){
        while(weapon < 0 || weapon >= weapons.size()){
            System.out.println("invalid choice");
            weapon = scanner.nextInt();
        }

        scanner.nextLine(); //throw away the \n not consumed by nextInt()

        System.out.println("Is this an accusation (Y/[N])?");
        accusationAnswer = scanner.nextLine();



        while(!(accusationAnswer.compareTo("y") == 0 || accusationAnswer.compareTo("Y") == 0 || accusationAnswer.compareTo("yes") == 0 || accusationAnswer.compareTo("n") == 0 || accusationAnswer.compareTo("N") == 0|| accusationAnswer.compareTo("no") == 0)){
            System.out.println("invalid choice");
            accusationAnswer = scanner.nextLine();
        }

        if(accusationAnswer.compareTo("y") == 0 || accusationAnswer.compareTo("yes") == 0 || accusationAnswer.compareTo("Y") == 0 ){
            accusation = true;
        }

        theGuess = new Guess(suspects.get(suspect),locations.get(crimeScene),weapons.get(weapon),accusation);
        System.out.print("you made a(n) ");
        theGuess.printGuess();

        return theGuess;
    } // getGuess

    public void receiveInfo(IPlayer ip, Card c){

        if(ip == null && c == null){
            System.out.println("No one could respond to your suggestion.");
        } else {
            System.out.print("Player "+ip.getIndex()+" showed you ");
            c.printCard();
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

    private void printAllSuspects() {
  //      int index = suspects.size();
        System.out.println("Here are the names of all the suspects:");

        for (int i = 0; i < suspects.size(); i++) {
            System.out.println(i+": "+(suspects.get(i).getValue()));
        }

//        for (int i = 0; i < myPeopleCards.size(); i++) {
//            System.out.println(index+": "+(myPeopleCards.get(i).getValue()));
//            index++;
//        }
    }

    private void printAllLocations() {
     //   int index = locations.size();

        System.out.println("Here are all the locations: ");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(i+": "+(locations.get(i).getValue()));
        }

//        for (int i = 0; i < myLocationCards.size(); i++) {
//            System.out.println(index+": "+(myLocationCards.get(i).getValue()));
//            index++;
//        }

    }

    private void printAllWeapons() {
   //     int index = weapons.size();
        System.out.println("Here are all the weapons:");

        for(int i = 0; i < weapons.size(); i++){
            System.out.println(i+": "+(weapons.get(i).getValue()));
        }

//        for(int i = 0; i < myWeaponCards.size(); i++){
//            System.out.println(index+": "+(myWeaponCards.get(i).getValue()));
//            index++;
//        }
    }
} // HumanPlayer