package BattleShipGame;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class BattleShipGame{

    // private final static Logger LOGGER = Logger.getLogger(BattleShipGame.class.getName());
    Logger Log = Logger.getLogger(BattleShipGame.class.getName());

    {
        Log.setLevel(INFO);
    }

    public GameMode gamemode = GameMode.MANUAL;

    Boolean winner = false;
    GameBoard board;
    Submarine sm;

    Set<Integer> allShipCoordinates = new HashSet<>();
    List<Ship> fleet = new ArrayList<Ship>();



    public BattleShipGame(){
        board = new GameBoard();

        sm = new Submarine();
        fleet.add(sm);

    }

    public void addShipCoordinates(int[] values){
        for (int i = 0; i < values.length; i++) {
            allShipCoordinates.add(values[i]);
        }

        System.out.println("allShipCoordinates = " + allShipCoordinates);
    }

    public void removeShipCoordinates(int[] values){
        for (int i = 0; i < values.length; i++) {
            allShipCoordinates.remove(values[i]);
        }

        System.out.println("shipCoordinates = " + allShipCoordinates);
    }

    List<String> previouslyGuessedValues = new ArrayList<>();
    public GuessResults userGuesses(String input){

        if (previouslyGuessedValues.contains(input)){
            System.out.println(input + " has already been guessed.");
            return GuessResults.INVALID;
        }
        previouslyGuessedValues.add(input);
        String letter = input.substring(0,1);
        // System.out.println("letter = " + letter);

        int number = Integer.valueOf(input.substring(1,2));
        System.out.println("number = " + number);

        if (allShipCoordinates.contains(number)){
            sm.hits += 1;
            if (sm.hits == sm.length){
                System.out.println("Submarine has been hit too many times. It's sunk!");
            } else {
                System.out.println("Submarine has been hit but it's still afloat!");
            }

            return GuessResults.HIT;
        } else {
            System.out.println("Nothing hit!");
            return GuessResults.MISS;
        }
    }

    public Boolean hasWinner(){
        for (Ship aFleet : fleet) {
            if (aFleet.isAlive()) {
                return false;
            }
        }
        System.out.println("All Ships sank! You Win!");
        return true;
    }

    public void playAgain(){

    }


    public void playGame(){
        Scanner scan = new Scanner(System.in);
        String input = "";

        while (!hasWinner()) {
            userGuesses(input);
        }
    }
    void setBoard(){
        for (int i = 0; i < fleet.size(); i ++) {
            board.addPieceToBoard(sm.getLocation());
        }
    }

    private int[] getRandomLocation(Ship ship){
        int[] returnList = new int[3];
        int random = 0;
        int shipLength = ship.getLength();

        while (random == 0 || allShipCoordinates.contains(random)){
            random = (int )(Math. random() * 50 + 1);
        }

        for (int i = 0; i < shipLength; i ++){
            System.out.println("(random+i) = " + (random+i));
            returnList[i] = random+i;
        }

        System.out.println("returnList = " + Arrays.toString(returnList));
        return returnList;
    }

    private Map<Ship,int[]> fleetLocation; // = new HashMap<Ship, int[]>();
    
    public Map<Ship,int[]> getFleetLocation(){
        System.out.println("fleetLocation = " + fleetLocation);
        if (fleetLocation == null){
            setRandomFleetLocation();
        }
        return fleetLocation;
    }
    public void setRandomFleetLocation(){
        fleetLocation = new HashMap<Ship, int[]>();
        for (Ship aFleet : fleet) {

            aFleet.setLocation(getRandomLocation(aFleet));
            fleetLocation.put(aFleet, aFleet.getLocation());
        }

        for (Ship ship : fleetLocation.keySet()){
            String message = ship.toString() + " - " + Arrays.toString(ship.getLocation());
            Log.log(Level.INFO,message);
            System.out.println("message = " + message);
        }


    }
}

class execute{
    public static void main(String[] args) {
        BattleShipGame battle = new BattleShipGame();
        battle.setBoard();
        battle.playGame();


    }
}


class GameBoard {
    private int[] board;

    GameBoard(){
    }

    void addPieceToBoard(int[] piece){
        board = piece;
    }
}



