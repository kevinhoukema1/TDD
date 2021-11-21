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

public class QueenVerschuivenSpec {
    @Test // 8A
    void testIfQueenBeeMovesAtLeastOneTile() throws IllegalMove{
        //Arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.moveTile(game, 0, 0, 0, -1);
        
        //assert
        assertEquals(Hive.Tile.QUEEN_BEE, game.getBoard().getTilePosition(new Coordinate(0, -1)).getType());

        
    }

    @Test // 8A.2
    void testIfErrorIfQueenBeeMovesMoreThanOne() throws IllegalMove{
       //Arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 0, 0, -1, -1); });
    }

    @Test //8B
    void testIfTileIsEmpty() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 0, -1);
        p2.playTile(game, Hive.Tile.BEETLE, -2, 0);

        assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 0, 0, 0, -1); });


    }
}
