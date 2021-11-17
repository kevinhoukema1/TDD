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

public class SoldierAntVerschuivenSpec {
    @Test //9a
    void testIfSoldierAntCanMoveInfinitely() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.SOLDIER_ANT, 1, 0);
        p2.pass(game);

        p1.moveTile(game, 1, 0, -2, 0);

        assertEquals(Hive.Tile.SOLDIER_ANT,  game.getBoard().getTilePosition(new Coordinate(-2, 0)).getType());
    }

    @Test //9B
    void testIfSoldierAntCannotMoveToItsOwnPosition() throws Exception {
       //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.SOLDIER_ANT, 1, 0);
        p2.pass(game);

        assertThrows(Hive.IllegalMove.class, () -> { p1.moveTile(game, 1, 0, 1, 0); });
    }

    @Test //9C
    void testIfSoldierAntCannotMoveOnTopOfTakeTiles() throws IllegalMove{
       //arrange
       HiveGame game = new HiveGame();
       Player p1 = new Player(Hive.Player.WHITE);
       Player p2 = new Player(Hive.Player.BLACK);

       //act
       p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
       p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

       p1.playTile(game, Hive.Tile.BEETLE, 1, -1);
       p2.playTile(game, Hive.Tile.BEETLE, -1, -1);

       p1.playTile(game, Hive.Tile.SOLDIER_ANT, 1, 0);
       p2.pass(game);

       assertThrows(Hive.IllegalMove.class, () -> {  p1.moveTile(game, 1, 0, 1, -1); });
    }
}
