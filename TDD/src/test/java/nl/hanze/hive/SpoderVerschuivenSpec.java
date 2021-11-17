package nl.hanze.hive;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;


public class SpoderVerschuivenSpec {
    @Test //10a
    void testIfSpoderMovesAtPreciselyThreeTiles() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.SPIDER, 1, 0);
        p2.pass(game);

        p1.moveTile(game, 1, 0, -2, 1);

        assertEquals(Hive.Tile.SPIDER,  game.getBoard().getTilePosition(new Coordinate(-2, 1)).getType());
    }

    @Test //10a.2
    void testIfSpoderNoMoreThanThreeTiles() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.SPIDER, 1, 0);
        p2.playTile(game, Hive.Tile.SPIDER, -2, 0);

        assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 1, 0, -2, -1); });

    }

    @Test //10b
    void testIfSpiderCannotMoveToItsCurrentPosition() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.SPIDER, 1, 0);
        p2.pass(game);

        assertThrows(Hive.IllegalMove.class, () -> { p1.moveTile(game, 1, 0, 1, 0); });
    }


    @Test //10C
    void testIfSpiderCannotMoveOnTopOfTakenTiles() throws IllegalMove{
       //arrange
       HiveGame game = new HiveGame();
       Player p1 = new Player(Hive.Player.WHITE);
       Player p2 = new Player(Hive.Player.BLACK);

       //act
       p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
       p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

       p1.playTile(game, Hive.Tile.BEETLE, 1, -1);
       p2.playTile(game, Hive.Tile.BEETLE, -1, -1);

       p1.playTile(game, Hive.Tile.SPIDER, 1, 0);
       p2.pass(game);

       assertThrows(Hive.IllegalMove.class, () -> {  p1.moveTile(game, 1, 0, 1, -1); });
    }

    // 10D 

    // Er wordt bijgehouden bij welke tiles geweest zijn in de DFS recursie, Dit zorgt ervoor dat tijdens het bewegen nooit dezelfde
    // tile gebruikt wordt. Deze opdracht is hierdoor niet relevant.
}
