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

public class GrasshopperVerschuivenSpec {
    @Test //11a
    void testIfGrassHopperCanJumpOnAStraightLine() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, 1, 0);
        p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, -1);

        p1.moveTile(game, 1, 0, -2, 0);

        assertEquals(Hive.Tile.GRASSHOPPER, game.getBoard().getTilePosition(new Coordinate(-2, 0)).getType());
    }
}
