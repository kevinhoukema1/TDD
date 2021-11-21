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

public class SteenverschuivenSpec{

    @Test //6A
    void testIfTileCanSlideOnEmptyPosition() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 1);

        game.play(Hive.Tile.BEETLE, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 1);

        game.move(1, 0, 1, -1);
        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(1,-1)).peek().getType());
    }

    @Test //6A.2
    void testIfTileCanSlideFromTopOfStackToEmptyPosition() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 0); 
        
        // stack tiles on top of other tiles
        game.play(Hive.Tile.SPIDER, 1, 0);
        game.play(Hive.Tile.SPIDER, -2, 0);  

        // Move the top tile
        game.move(1, 0, 1, -1);

        //assert
        // Top tile is moved so the beetle should have remained on 1,0
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(1,0)).peek().getType()); 
    }

    @Test //6B
    void testIfTileIsBlockedBeforeSliding() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -2, 0);  

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ game.move(0, 0, 0, -1); });
    }

    @Test //6C
    void testIfCommonNeighbourHasFilledTile() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        
        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 0, 1);
        game.play(Hive.Tile.BEETLE, -2, 1);

        game.play(Hive.Tile.SPIDER, -1, 2);
        game.play(Hive.Tile.BEETLE, -2, 0);

        assertThrows(Hive.IllegalMove.class, ()->{ game.move(-1, 2, -2, 2); });
    }
}