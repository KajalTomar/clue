//-----------------------------------------
// CLASS: Suspect.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: Child of Card Class
//      one of the three types of Card
//      objects that can be created.
//
//-----------------------------------------
public class Suspect extends Card{

    //------------------------------------------------------
    // Suspect
    //
    // PURPOSE: constructor for the Suspect class. Calls
    //      the super class card and sends the name as the value
    //      and "suspect" as the type of card.
    //
    // PARAMETERS: name (String) - name of the suspect
    // Returns: describe the return value
    //------------------------------------------------------
    public Suspect(String name){
        super(name, "Suspect");
    }

} // Suspect