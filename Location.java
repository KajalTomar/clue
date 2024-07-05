//-----------------------------------------
// CLASS: Location.java
//
// Author: Kajal Tomar
//
// REMARKS: Child of Card Class
//      one of the three types of Card
//      objects that can be created.
//
//-----------------------------------------
public class Location extends Card{

    //------------------------------------------------------
    // Location
    //
    // PURPOSE: constructor for the Location class. Calls
    //      the super class card and sends the name of the
    //      location as the value
    //      and "Location" as the type of card.
    //
    // PARAMETERS: location (String)
    //------------------------------------------------------
    public Location(String location){ super(location, "Location"); }

} // Location
