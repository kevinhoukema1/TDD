package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class PassenSpec {
    @Test // 12.1
    void testIfPlayerCanPlayWithNoInventory() throws IllegalMove{
       //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        HashMap<Hive.Tile, Integer> tiles = p1.getTiles();
        
        for(Map.Entry<Hive.Tile, Integer> entry : tiles.entrySet()){
            entry.setValue(0);
        }

        //assert
        assertThrows(Hive.IllegalMove.class, () -> {  p1.playTile(game, Hive.Tile.BEETLE, 0, 0); });
    }

    @Test //12.2
    void testIfPlayerCannotPassIfPlaysAreAvailable() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);
        p1.pass(game);

        //assert
        assertThrows(Hive.IllegalMove.class, () -> {  p1.pass(game); });
    }
    
    @Test //12.3
    void testIfcanPassWithNoMoreMoves() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        HashMap<Hive.Tile, Integer> p1RemainingTiles = p1.getTiles();
        
        for(Map.Entry<Hive.Tile, Integer> entry : p1RemainingTiles.entrySet()){
            entry.setValue(0);
        }

        p1.pass(game); // white starts but passes with no moves available
        
        // assert
        assertEquals(game.getCurrentTurn(), p2.getColour());
    }
}
