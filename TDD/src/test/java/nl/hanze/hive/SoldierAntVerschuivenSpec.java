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

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.SOLDIER_ANT, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 1);

        game.move(1, 0, -2, 0);

        assertEquals(Hive.Tile.SOLDIER_ANT,  game.getBoard().getTilePosition(new Coordinate(-2, 0)).getType());
    }


    @Test //9B
    void testIfSoldierAntCannotMoveToItsOwnPosition() throws Exception {
       //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.SOLDIER_ANT, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 1);

        assertThrows(Hive.IllegalMove.class, () -> { game.move(1, 0, 1, 0); });
    }

    @Test //9C
    void testIfSoldierAntCannotMoveOnTopOfTakenTiles() throws IllegalMove{
       //arrange
       HiveGame game = new HiveGame();
       PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
       PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

       //act
       game.play(Hive.Tile.QUEEN_BEE, 0, 0);
       game.play(Hive.Tile.QUEEN_BEE, -1, 0);

       game.play(Hive.Tile.BEETLE, 1, -1);
       game.play(Hive.Tile.BEETLE, -1, -1);

       game.play(Hive.Tile.SOLDIER_ANT, 1, 0);
       game.play( Hive.Tile.BEETLE, -2, 1);

       assertThrows(Hive.IllegalMove.class, () -> {  game.move(1, 0, 1, -1); });
    }
}
