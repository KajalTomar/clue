//-----------------------------------------
// CLASS: Weapon.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: Child of Card Class
//      one of the three types of Card
//      objects that can be created.
//
//-----------------------------------------
public class Weapon extends Card{

    //------------------------------------------------------
    // Weapon
    //
    // PURPOSE: constructor for the Weapon class. Calls
    //      the super class card and sends the name of the
    //      weapon as the value
    //      and "Weapon" as the type of card.
    //
    // PARAMETERS: weapon (String)
    //------------------------------------------------------
    public Weapon(String weapon){
        super(weapon, "weapon");
    }

} // Weapon