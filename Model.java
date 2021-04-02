//-----------------------------------------
// CLASS: Model.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: Stores the players and cards for
//      a game. Initializes and runs a game.
//      This includes manafind all of the
//      players, cards, and turns.
//
//-----------------------------------------
import java.util.*;
//import java.lang.*;

public class Model{
    private static Scanner scanner;
    private static HumanPlayer human;
    private static ArrayList<ComputerPlayer> bots;

    private static ArrayList<Card> suspects;
    private static ArrayList<Card> locations;
    private static ArrayList<Card> weapons;

    private static ArrayList<IPlayer> allPlayers;
    private static ArrayList<IPlayer> inTheGame;
    private static ArrayList<Card> cards;

    private static Card who;
    private static Card where;
    private static Card how;

    private static int totalPlayers;
    private static final int HUMAN_POSITION = 0;
    boolean gameOver;
    IPlayer activePlayer;
    IPlayer winner;

    public Model(){
        bots  = new ArrayList<ComputerPlayer>();
        suspects = new ArrayList<Card>();
        locations = new ArrayList<Card>();
        weapons = new ArrayList<Card>();

        allPlayers = new ArrayList<IPlayer>();
        inTheGame = new ArrayList<IPlayer>();
        cards = new ArrayList<Card>();
        cards = new ArrayList<Card>();
        inTheGame =  new ArrayList<IPlayer>();

        gameOver = false;
    }

    public void playGame(int numPlayers, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons,ArrayList<ComputerPlayer> computers,HumanPlayer human){
        setup(numPlayers,ppl,places,weapons,computers,human);
        setupPlayers();
        chooseWinningCards();
        dealCards();

        int position = 0;
        int cardNumber = cards.size();

        Collections.shuffle(cards);

        while(!gameOver){

            // whose turn is it?
            activePlayer = allPlayers.get(position);

            if(inTheGame.contains(activePlayer)) {
                playTurn(position);
            }

            position++;
            if(position == totalPlayers){
                position = 0;
            }
        }


    }

    private void setup(int numPlayers, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons,ArrayList<ComputerPlayer> computers,HumanPlayer human){
        this.totalPlayers = numPlayers;
        this.human = human;

        inTheGame.add(human);
        allPlayers.add(human);
        for(int i = 0; i < computers.size(); i++){
            if(computers.get(i) instanceof ComputerPlayer) {
                inTheGame.add((IPlayer)computers.get(i));
                allPlayers.add((IPlayer)computers.get(i));
                bots.add(computers.get(i));
            }
        }

        for(int i = 0; i < ppl.size(); i++){
            if(ppl.get(i) instanceof Suspect) {
                suspects.add((Suspect)ppl.get(i));
                cards.add((Suspect)ppl.get(i));
            }
        }

        for(int i = 0; i < places.size(); i++){
            if(places.get(i) instanceof Location) {
                locations.add((Location)places.get(i));
                cards.add((Location)places.get(i));
            }
        }

        for(int i = 0; i < weapons.size(); i++){
            if(weapons.get(i) instanceof Weapon) {
                (this.weapons).add((Weapon)weapons.get(i));
                cards.add((Weapon)weapons.get(i));
            }
        }
    }

    private void chooseWinningCards(){
        int indexOfCard;

        Collections.shuffle(suspects);
        Collections.shuffle(locations);
        Collections.shuffle(this.weapons);

        who = suspects.get(0);
        where = locations.get(0);
        how = weapons.get(0);

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

    private void setupPlayers(){
        human.setUp(totalPlayers,HUMAN_POSITION,suspects,locations,weapons);

        for(int i = 0; i < (totalPlayers-1); i++){
            bots.get(i).setUp(totalPlayers,(i+1),suspects,locations,weapons);
        }
    }

    public void dealCards(){
        int position = 0;
        int cardNumber = cards.size()-1;


        Collections.shuffle(cards);

        while(cardNumber >= 0){

//            if(position == HUMAN_POSITION){
//                human.setCard(cards.get(cardNumber));
//            } else {
//                (bots.get(position)).setCard(cards.get(cardNumber));
//            }

            (allPlayers.get(position)).setCard(cards.get(cardNumber));

            position++;
            if(position == totalPlayers){
                position = 0;
            }

            cardNumber--;
        }

    }

    private void playTurn(int activePlayerPos){
        IPlayer beingAsked;
        Guess activeGuess;
        Card cardShown;
        int position;
        boolean keepAsking;

        System.out.println("--------------------------");
        System.out.println("Curent turn: player "+activePlayer.getIndex());
        activeGuess = activePlayer.getGuess();

        if(activeGuess != null) {

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
                    System.out.print("\nPlayer "+winner.getIndex()+" won the game with ");
                    activeGuess.printGuess();
                } else {
                    // this player lost
                    System.out.println("\nPlayer "+activePlayer.getIndex()+" made a bad accusation and was removed from the game.");
                    inTheGame.remove(activePlayerPos);

                    if (inTheGame.size() == 1) {
                        gameOver = true;
                        winner = inTheGame.get(0);
                        System.out.println("Player "+winner.getIndex()+" is the last person left, they won the game!");
                    }
                }
            }
            else {

                // where to start asking
                if(activePlayerPos == totalPlayers-1){
                    position = 0;
                } else {
                    position = activePlayerPos + 1;
                }

                keepAsking = true;

                // ask player if they can respond to guess
                while(position != activePlayerPos && (keepAsking) ){

//                    if(position == HUMAN_POSITION){
//                        beingAsked = (IPlayer) human;
//                    } else {
//                        beingAsked = (IPlayer)bots.get(position);
//                    }
                    beingAsked = allPlayers.get(position);

                    System.out.println("\nAsking player "+beingAsked.getIndex());

                    cardShown = beingAsked.canAnswer(activeGuess,activePlayer);

                    if(cardShown != null){
                        activePlayer.receiveInfo(beingAsked,cardShown);
                        keepAsking = false;
                    }

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