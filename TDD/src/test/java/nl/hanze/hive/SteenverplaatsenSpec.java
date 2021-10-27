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
        p1.playTile(game, Hive.Tile.BEETLE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);
       
        p1.playTile(game, Hive.Tile.BEETLE, 1, -1);
        p2.pass(game);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p1.moveTile(game, 1, -1, 1, 0); });
    }

    @Test //5C
    void testifTileHasNeighbourAfterMove() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);  

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);
        
        p1.playTile(game, Hive.Tile.BEETLE, 1, -1);
        p2.pass(game);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p1.moveTile(game, 1, -1, 2, -1); });
    }

    @Test //5D
    void testIfMovingATileCreatesTwoGroups() throws IllegalMove{
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);  

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);
        
        p1.playTile(game, Hive.Tile.BEETLE, 1, 0);
        p2.playTile(game, Hive.Tile.BEETLE, -2, 0);

       //assert
       assertThrows(Hive.IllegalMove.class, ()->{ p1.moveTile(game, 0, 0, 1, -1); }); 
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
