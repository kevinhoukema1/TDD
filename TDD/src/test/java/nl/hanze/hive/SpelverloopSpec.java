package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class SpelverloopSpec {
    @Test // 3A
    void testIfPlayerWhiteStartedTheGame(){
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);

        //act
        Boolean myTurn = game.getCurrentTurn() == p1.getColour();

        //assert
        assertTrue(myTurn);
    } 

    @Test // 3B
    void testIfPlayerCanPlay() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);

        //act
        p1.playTile(game, Hive.Tile.BEETLE, 0, 0);

        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(0,0)).peek().getType());
    }

    @Test //3B
    void testIfPlayerCanMove() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.BEETLE, 0, 0);
        p2.playTile(game, Hive.Tile.SPIDER, -1, 0);

        p1.moveTile(game, 0, 0, -1, 0);
        
        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(-1,0)).peek().getType());
    }

    @Test // 3B
    void testIfPlayerCanPass() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.pass(game);

        //assert
        assertEquals(p2.getColour(), game.getCurrentTurn());
    }
    
    @Test // 3C
    void testIfPlayerHasWon() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.BEETLE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 0, -1);
        
        p2.playTile(game, Hive.Tile.SOLDIER_ANT, 1, -1);

        p1.playTile(game, Hive.Tile.SOLDIER_ANT, 1, 0);

        p2.playTile(game, Hive.Tile.SOLDIER_ANT, 0, 1);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, -1, 1);

        //assert
        assertTrue(game.isWinner(p2.getColour()));
        
    }

    @Test
    void testIfGameEndsInDraw() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK); 

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -2, 0);

        p1.playTile(game, Hive.Tile.BEETLE, -1, -1); 
        p2.playTile(game, Hive.Tile.BEETLE, 0, -1);
        
        p1.playTile(game, Hive.Tile.SOLDIER_ANT, -2, -1);
        p2.playTile(game, Hive.Tile.SOLDIER_ANT, 1, -1);

        p1.playTile(game, Hive.Tile.SOLDIER_ANT, -3, 0);
        p2.playTile(game, Hive.Tile.SOLDIER_ANT, 1, 0);

        p1.playTile(game, Hive.Tile.SOLDIER_ANT, -3, 1);
        p2.playTile(game, Hive.Tile.SOLDIER_ANT, 0, 1);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, -2, 1);
        p2.playTile(game, Hive.Tile.GRASSHOPPER, -1, 1);

        p1.playTile(game, Hive.Tile.GRASSHOPPER, -1, 0);

        //assert
        assertTrue(game.isDraw());
        
    }


}
