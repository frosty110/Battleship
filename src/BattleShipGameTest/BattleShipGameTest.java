package BattleShipGameTest;

import BattleShipGame.*;
import BattleShipGame.BattleShipGame;
import BattleShipGame.Ship;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class BattleShipGameTest {

    @Test
    void fleetLocationInitalizedTest(){
        BattleShipGame battle = new BattleShipGame();
        Map<Ship, int[]> fleetLocation = battle.getFleetLocation();
        for (Ship ship : fleetLocation.keySet()){
            assertSame(fleetLocation.get(ship), ship.getLocation());
        }
    }
    
    @Tag("GameFunction")
    @Test
    void hitAndMissTest() {

        BattleShipGame battle = new BattleShipGame();

        int[] locations = {1, 2, 3};
        battle.addShipCoordinates(locations);

        assertSame(battle.userGuesses("A5"), GuessResults.MISS);
        assertFalse(battle.hasWinner());
        assertSame(battle.userGuesses("A1"), GuessResults.HIT);
        assertFalse(battle.hasWinner());
        assertSame(battle.userGuesses("A2"), GuessResults.HIT);
        assertFalse(battle.hasWinner());
        assertSame(battle.userGuesses("A3"), GuessResults.HIT);
        assertTrue(battle.hasWinner());

    }

    @Tag("GameFunction")
    @Test
    void sameLocationHitWinnerTest() {

        BattleShipGame battle = new BattleShipGame();

        int[] locations = {1, 2, 3};
        battle.addShipCoordinates(locations);

        assertSame(battle.userGuesses("A1"), GuessResults.HIT);
        assertFalse(battle.hasWinner());
        assertSame(battle.userGuesses("A1"), GuessResults.MISS);
        assertFalse(battle.hasWinner());

    }

    @Disabled
    @Tag("Input_Error_Handling")
    @Test
    void invalidInputTest() {

        BattleShipGame battle = new BattleShipGame();

        int[] locations = {1, 2, 3};
        battle.addShipCoordinates(locations);

        assertSame(battle.userGuesses("A55"), GuessResults.INVALID);
        assertSame(battle.userGuesses("AB1"), GuessResults.INVALID);
        assertSame(battle.userGuesses("A2A2"), GuessResults.INVALID);
        assertSame(battle.userGuesses("A3!3"), GuessResults.INVALID);
    }

}