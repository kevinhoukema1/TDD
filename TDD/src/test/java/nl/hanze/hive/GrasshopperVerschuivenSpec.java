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
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, 1, 0);
        p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, -1);

        p1.moveTile(game, 1, 0, -2, 0);

        assertEquals(Hive.Tile.GRASSHOPPER, game.getBoard().getTilePosition(new Coordinate(-2, 0)).getType());
    }

    @Test //11B
    void testifGrassHopperCannotJumpToItsOwnPosition() throws Exception {
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, 1, 0);
        p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, -1);

        assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 1, 0, 1, 0); });
    }
    
    @Test //11C
    void testIfGrassHopperAtLeastJumpsOverOneTile() throws Exception {
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, 1, 0);
        p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, -1);

        assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 1, 0, 1, -1); });
    }

    @Test //11D
    void testIfGrassHopperJumpsToATileThatIsNotOccupied() throws IllegalMove{
       //arrange
       HiveGame game = new HiveGame();
       PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
       PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

       //act
       p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
       p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

       p1.playTile(game, Hive.Tile.GRASSHOPPER, 1, 0);
       p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, -1);

       assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 1, 0, -1, 0); }); 
    }

    @Test //11E
    void testIfGrassHopperCannotJumpToAPositionIfTheLineIsNotCompletelyFilled() throws IllegalMove{
       //arrange
       HiveGame game = new HiveGame();
       PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
       PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

       //act
       p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
       p2.playTile(game, Hive.Tile.QUEEN_BEE, -2, 0);

       p1.playTile(game, Hive.Tile.GRASSHOPPER, 1, 0);
       p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, -1);

       assertThrows(Hive.IllegalMove.class, ()-> { p1.moveTile(game, 1, 0, -3, 0); });  
    }
}
