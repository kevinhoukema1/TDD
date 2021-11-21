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

public class BeetleVerschuivenSpec {
    @Test // 7A
    void testIfBeetleMovesAtLeastOneTile() throws IllegalMove{
        //Arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 1, 0);
        p2.playTile(game, Hive.Tile.BEETLE, -2, 0);

        p1.moveTile(game, 1, 0, 1, -1);
        
        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getTilePosition(new Coordinate(1, -1)).getType());

        
    }

    @Test // 7A.2
    void testIfErrorIfBeetleMovesMoreThanOne() throws IllegalMove{
       //Arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 1, 0);
        p2.playTile(game, Hive.Tile.BEETLE, -2, 0);

        assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 1, 0, 0, -1); });
    }
}
