//-----------------------------------------
// CLASS: Guess.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: a guess consists of three cards
//      (Suspect, Weapon, and Location) and
//      is either a accusation or not.
//
//-----------------------------------------
public class Guess{
    private static Suspect suspect;
    private static Location location;
    private static Weapon weapon;
    private boolean accusation;

    //------------------------------------------------------
    // Guess
    //
    // PURPOSE: constructor for the Guess class.
    //      Sets the values of the fields suspect, weapon and
    //      location. Makes sure that we recieve exactly
    //      1 card of each type and in exactly the order
    //      mentioned. If this is not the case that card's
    //      values is set to null.
    //
    // PARAMETERS: suspect (Card), weapon (Card),
    //      location (Card), accusation (Boolean)
    //------------------------------------------------------
    public Guess(Card suspect, Card location, Card weapon, Boolean accusation){

        // first make sure the first card is a Suspect card
        // this is to avoid the problem where a player might
        // accidently try to guess who the perpetrator is using
        // a weapon or location card.
        if(suspect instanceof Suspect){
            this.suspect = (Suspect)suspect;
        } else {
            // the first card was not a suspect card
            suspect = null;
            System.out.println("Warning: No suspect card was found when creating the guess.");
        }

        // make sure the third card is a Location card
        // this is to avoid the problem where a player might
        // accidently try to guess what the location was using
        // a suspect or weapon card.
        if(location instanceof Location) {
            this.location = (Location)location;
        } else {
            // the third card was not a Weapon card
            location = null;
            System.out.println("Warning: No location card was found when creating the guess.");
        }

        // make sure the second card is a Weapon card
        // this is to avoid the problem where a player might
        // accidently try to guess which weapon was used with
        // a suspect or location card.
        if(weapon instanceof Weapon){
            this.weapon = (Weapon)weapon;
        } else {
            // the second card was not a Weapon card
            weapon = null;
            System.out.println("Warning: No weapon card was found when creating the guess.");
        }

        // set the location
        this.accusation = accusation;

    } // Guess constructor

    //------------------------------------------------------
    // guessedSuspect
    //
    // PURPOSE: returns the suspect card
    //
    // Returns: suspect (Card) or null
    //      if we are missing the suspect card.
    //------------------------------------------------------
    public Suspect guessedSuspect(){
        return suspect;
    } // guessedSuspect

    //------------------------------------------------------
    // guessedLocation
    //
    // PURPOSE: returns the Location card
    //
    // Returns: location (Card) or null
    //      if we are missing the lovation card.
    //------------------------------------------------------
    public Location guessedLocation(){
        return location;
    } // guessedLocation

    //------------------------------------------------------
    // guessedWeapon
    //
    // PURPOSE: returns the weapon card
    //
    // Returns: weapon (Card) or null
    //      if we are missing the weapon card.
    //------------------------------------------------------
    public Weapon guessedWeapon(){
        return weapon;
    } // guessedWeapon

    //------------------------------------------------------
    // isAccusation
    //
    // PURPOSE: returns true if the guess is an accusation
    //
    // Returns: true (if an accusation) otherise returns false
    //------------------------------------------------------
    public boolean isAccusation(){
        return accusation;
    } // guessedWeapon

    //------------------------------------------------------
    // printGuess
    //
    // PURPOSE: prints out the guess
    //------------------------------------------------------
    public void printGuess(){
            if(accusation){
                System.out.print("ACCUSATION ");
            } else {
                System.out.print("GUESS ");
            }

            System.out.print("[ SUSPECT: " + suspect.getValue());
            System.out.print(", LOCATION: " + location.getValue());
            System.out.print(", WEAPON: " + weapon.getValue() +" ]");

    } // printGuess

} // Guess