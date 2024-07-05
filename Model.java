//-----------------------------------------
// CLASS: Model.java
//
// Author: Kajal Tomar
//
// REMARKS: Stores the players and cards for
//      a game. Initializes and runs a game.
//      This includes managing all of the
//      players, cards, and turns.
//
//-----------------------------------------
import java.util.*;

public class Model{
    private static Scanner scanner;
    private static HumanPlayer human;
    private static ArrayList<ComputerPlayer> bots;

    // three lists of each type of card
    private static ArrayList<Card> suspects;
    private static ArrayList<Card> locations;
    private static ArrayList<Card> weapons;

    private static ArrayList<IPlayer> allPlayers;   // list of all the players combined
    private static ArrayList<IPlayer> inTheGame;    // list of players that are still in the game
    private static ArrayList<Card> cards;           // list of all the cards

    // the answer cards
    private static Card who;
    private static Card where;
    private static Card how;

    private static int totalPlayers;    // total amount of players
    private static final int HUMAN_POSITION = 0;    // right now, the human players has to be player 0
    boolean gameOver;
    IPlayer activePlayer;   // the active player, each round
    IPlayer winner;         // the winner!

    //------------------------------------------------------
    // Model
    //
    // PURPOSE: constructor for the Model class.
    //      initializes the lists and sets gameOver to false
    //------------------------------------------------------
    public Model(){
        // initialize the lists
        bots  = new ArrayList<ComputerPlayer>();

        suspects = new ArrayList<Card>();
        locations = new ArrayList<Card>();
        weapons = new ArrayList<Card>();

        allPlayers = new ArrayList<IPlayer>();
        inTheGame = new ArrayList<IPlayer>();

        cards = new ArrayList<Card>();
        cards = new ArrayList<Card>();

        gameOver = false;
    }

    //---------------------------------------------------------------
    // playGame
    //
    // PURPOSE: calls the appropriate methods to run the game
    //
    // PARAMETERS: numPlayers (int)     - the number of opponents
    //             index (int)          - what player number this
    //                                      player is
    //             ppl (ArrayList<card>)- list of Suspect cards
    //             places (ArrayList<card>)- list of Location cards
    //             weapons (ArrayList<card>)- list of Weapon cards
    //----------------------------------------------------------------
    public void playGame(int numPlayers, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons,ArrayList<ComputerPlayer> computers,HumanPlayer human){
        int position = 0;   // which which person's turn is it

        setup(numPlayers,ppl,places,weapons,computers,human); // set up initial list

        setupPlayers();
        chooseWinningCards();
        dealCards();

        // shuffle all the cards
        Collections.shuffle(cards);

        // run each turn until the game is over
        while(!gameOver){

            // whose turn is it?
            activePlayer = allPlayers.get(position);

            // if this player is still in the game
            if(inTheGame.contains(activePlayer)) {
                playTurn(position); // play out their turn
            }

            // next player!
            position++;

            // if we are the end, then go back to player one
            if(position == totalPlayers){
                position = 0;
            }
        }

    } // playGame

    //---------------------------------------------------------------
    // setup
    //
    // PURPOSE: adds the suspects,location, and weapon cards to
    //          three lists. Also sets the numberOfPlayers,
    //          set up human and bots.
    //
    // PARAMETERS: numPlayers (int)     - the number of opponents
    //             index (int)          - what player number this
    //                                      player is
    //             ppl (ArrayList<card>)- list of Suspect cards
    //             places (ArrayList<card>)- list of Location cards
    //             weapons (ArrayList<card>)- list of Weapon cards
    //             computers (ArrayList<ComputerPlayer>)- list of bots
    //             human (HumanPlayer) - the human player!
    //----------------------------------------------------------------
    private void setup(int numPlayers, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons,ArrayList<ComputerPlayer> computers,HumanPlayer human){
        this.totalPlayers = numPlayers;
        this.human = human;

        inTheGame.add(human); // add the human to list of players that haven't lost
        allPlayers.add(human);  // also add it the list of all players

        for(int i = 0; i < computers.size(); i++){

            // make sure we only add computerPlayers to list of
            // computer player
            if(computers.get(i) instanceof ComputerPlayer) {


                inTheGame.add((IPlayer)computers.get(i));   // add it to the list of players who haven't lost
                allPlayers.add((IPlayer)computers.get(i));  // add it to the list of all players that
                bots.add(computers.get(i)); // add to the list of bots
            }
        }

        // add the suspect cards to our list
        for(int i = 0; i < ppl.size(); i++){

            // just to make sure that our suspect pile
            // ONLY has Suspect cards
            if(ppl.get(i) instanceof Suspect) {
                suspects.add((Suspect)ppl.get(i));
                cards.add((Suspect)ppl.get(i)); // also add it to the list of all cards
            }
        }

        // add the Location cards to our list
        // ONLY has Location cards
        for(int i = 0; i < places.size(); i++){
            if(places.get(i) instanceof Location) {
                locations.add((Location)places.get(i));
                cards.add((Location)places.get(i));  // also add it to the list of all cards
            }
        }

        // add the weapons cards to our list
        // ONLY has Weapon cards
        for(int i = 0; i < weapons.size(); i++){
            if(weapons.get(i) instanceof Weapon) {
                (this.weapons).add((Weapon)weapons.get(i));
                cards.add((Weapon)weapons.get(i));  // also add it to the list of all cards
            }
        }
    }

    //---------------------------------------------------------------
    // chooseWinningCards
    //
    // PURPOSE: pick out who,where,how cards and remove those
    //          cards from our pile that we will deal the cards
    //          from.
    //----------------------------------------------------------------
    private void chooseWinningCards(){
        int indexOfCard;

        // shuffle all the piles
        Collections.shuffle(suspects);
        Collections.shuffle(locations);
        Collections.shuffle(this.weapons);

        // take the card at the top of each pile
        who = suspects.get(0);
        where = locations.get(0);
        how = weapons.get(0);

        // find the index of each card in our "will deal from this pile"
        // and remove it from that pile.

        indexOfCard = cards.indexOf(who);

        if(indexOfCard >= 0){
            cards.remove(indexOfCard);
        }

        indexOfCard = cards.indexOf(where);

        if(indexOfCard >= 0){
            cards.remove(indexOfCard);
        }

        indexOfCard = cards.indexOf(how);

        if(indexOfCard >= 0){
            cards.remove(indexOfCard);
        }

    }

    //---------------------------------------------------------------
    // setupPlayers
    //
    // PURPOSE: call the setup method for each player
    //----------------------------------------------------------------
    private void setupPlayers(){
        human.setUp(totalPlayers,HUMAN_POSITION,suspects,locations,weapons);

        for(int i = 0; i < (totalPlayers-1); i++){
            bots.get(i).setUp(totalPlayers,(i+1),suspects,locations,weapons);
        }
    }

    //---------------------------------------------------------------
    // dealCards
    //
    // PURPOSE: deal the cards!
    //----------------------------------------------------------------
    public void dealCards(){
        int position = 0;
        int cardNumber = cards.size()-1;


        Collections.shuffle(cards);

        while(cardNumber >= 0){
            (allPlayers.get(position)).setCard(cards.get(cardNumber));

            position++;
            if(position == totalPlayers){
                position = 0;
            }

            cardNumber--;
        }

    }

    //---------------------------------------------------------------
    // playTurn
    //
    // PURPOSE: play out the active player's turn. Ask them to make a guess
    //          or accusation.
    //          If they make an accusation, then determine if they won or lost
    //          If they make a guess, then ask if any other player
    //          can answer the guess. Show the active player this info.
    // PARAMETER: the index of the active player
    //----------------------------------------------------------------
    private void playTurn(int activePlayerPos){
        IPlayer beingAsked; // which player are we asking?
        Guess activeGuess;  // the guess that the activePlayer made
        Card cardShown;
        int position;       // to go around the players
        boolean keepAsking;

        System.out.println("--------------------------");
        System.out.println("Curent turn: player "+activePlayer.getIndex());

        // get the guess
        activeGuess = activePlayer.getGuess();

        // just to be sure
        if(activeGuess != null) {

            // only display the guess if a bot made it
            if(activePlayerPos!=HUMAN_POSITION) {
                System.out.print("\nPlayer " + activePlayer.getIndex() + ": ");
                activeGuess.printGuess();
            }

            // the player made an accusation!
            if (activeGuess.isAccusation()) {

                // this player won!
                if (activeGuess.guessedSuspect() == who && activeGuess.guessedLocation() == where && activeGuess.guessedWeapon() == how) {

                    gameOver = true;
                    winner = activePlayer;

                    // print out the results of the game
                    System.out.print("\nPlayer "+winner.getIndex()+" won the game with ");
                    activeGuess.printGuess();

                } else { /* the player lost*/

                    // let everyone know and remove them from list of players that are still in the game
                    System.out.println("\nPlayer "+activePlayer.getIndex()+" made a bad accusation and was removed from the game.");
                    inTheGame.remove(activePlayerPos);

                    // if there is only one person left in the game then they are the winner
                    if (inTheGame.size() == 1) {

                        // the game is over, print out the results
                        gameOver = true;
                        winner = inTheGame.get(0);
                        System.out.println("Player "+winner.getIndex()+" is the last person left, they won the game!");

                    }
                }
            }
            else { /* the player made a guess (NOT an accusation)*/

                // where to start asking
                if(activePlayerPos == totalPlayers-1){
                    position = 0;
                } else {
                    position = activePlayerPos + 1;
                }

                keepAsking = true; // so we stop asking once some has asked

                // ask each player if they can respond to the guess
                while(position != activePlayerPos && (keepAsking) ){

                    beingAsked = allPlayers.get(position); // which person is being asked
                    System.out.println("\nAsking player "+beingAsked.getIndex());

                    // ask this player if they can show a card
                    cardShown = beingAsked.canAnswer(activeGuess,activePlayer);

                    // if they showed us a card
                    if(cardShown != null){

                        // show this card to the active player
                        activePlayer.receiveInfo(beingAsked,cardShown);
                        keepAsking = false; // no need to keep asking
                    }

                    // update postion
                    position++;
                    if(position == totalPlayers){
                        position = 0;
                    }
                }

                if(keepAsking == true) {
                    // no one was able to answer
                    activePlayer.receiveInfo(null,null);
                }

            }
        }
    }

} // Model
