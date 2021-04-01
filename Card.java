//-----------------------------------------
// CLASS: Card.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: the abstract parent type of
//      the three possible types of cards
//      (Suspect, Weapon, Location)
//
//-----------------------------------------
public abstract class Card{
    private String type;
    private String value;

    //------------------------------------------------------
    // Card
    //
    // PURPOSE: constructor for the Card class
    // PARAMETERS:
    //      value (String) - what is on the car
    //      type (string)  - what type of card it is (can be
    //------------------------------------------------------
    public Card(String value, String type){
        this.value = value;
        this.type = type;
    }

    //------------------------------------------------------
    // getType
    //
    // PURPOSE: getter for the type of card
    // Returns: type (String)
    //------------------------------------------------------
    public String getType(){ return type; }

    //------------------------------------------------------
    // getValue
    //
    // PURPOSE: getter for the value of card
    // Returns: value (String)
    //------------------------------------------------------
    public String getValue(){
        return value;
    }

    public void printCard(){
        System.out.println("[ "+value+" ("+type+") ]");
    }

} // Card