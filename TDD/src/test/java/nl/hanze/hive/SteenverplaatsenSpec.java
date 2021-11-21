package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class SteenverplaatsenSpec {
    @Test //5A
    void testIfPlayedTilesCanMove() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -2, 0);

        game.move(1, -1, 1, 0);

        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(1,0)).peek().getType());
    }
    
    @Test //5B
    void testIfQueenIsPresentBeforeMoveCanBeDone() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.BEETLE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);
       
        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -2, 0);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ game.move(1, -1, 1, 0); });
    }

    @Test //5C
    void testifTileHasNeighbourAfterMove() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);
        
        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -2, 0);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ game.move(1, -1, 2, -1); });
    }

    @Test //5D
    void testIfMovingATileCreatesTwoGroups() throws IllegalMove{
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);
        
        game.play(Hive.Tile.BEETLE, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 0);

       //assert
       assertThrows(Hive.IllegalMove.class, ()->{  game.move(0, 0, 1, -1); }); 
    }

    @Test //5E
    void testIfAllTilesHaveTheirOwnStrategy(){
        //arrange
        StratFactory factory = new StratFactory();
        Strategy beeStrat = factory.getStrat(Hive.Tile.QUEEN_BEE);
        Strategy spoderStrat = factory.getStrat(Hive.Tile.SPIDER);

        //act

        //assert
        // NOTE:: als de beestrat niet het zelfde is als de spiderstrat, dan geld dat ook voor de rest van de strategies.
        assertTrue(beeStrat != spoderStrat);
    }

}
