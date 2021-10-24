package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class SteenverschuivenSpec {
    @Test //5A
    void testIfPlayedTilesCanMove() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 1, -1);
        p2.pass(game);

        p1.moveTile(game, 1, -1, 1, 0);

        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(1,0)).peek().getType());
    }
    
    @Test //5B
    void testIfQueenIsPresentBeforeMoveCanBeDone() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK); 

        //act
        p1.playTile(game, Hive.Tile.SPIDER, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);
       
        p1.playTile(game, Hive.Tile.BEETLE, 1, -1);
        p2.pass(game);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p1.moveTile(game, 1, -1, 1, 0); });
    }

}
