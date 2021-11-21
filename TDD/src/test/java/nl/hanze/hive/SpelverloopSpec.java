package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class SpelverloopSpec {
    @Test // 3A
    void testIfPlayerWhiteStartedTheGame(){
        //arrange
        HiveGame game = new HiveGame();

        //act
        Boolean myTurn = game.getCurrentTurn().getColour() == game.getCurrentTurn().getColour();

        //assert
        assertTrue(myTurn);
    } 

    @Test // 3B
    void testIfPlayerCanPlay() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.BEETLE, 0, 0);

        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(0,0)).peek().getType());
    }

    @Test //3B
    void testIfPlayerCanMove() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);
       
        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -2, 1);

        game.move(1, -1, 1, 0);
        
        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getCoordinateStack(new Coordinate(1,0)).peek().getType());
    }

    @Test // 3B
    void testIfPlayerCanPass() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        //// REFACTOR AFTER 12
        HashMap<Hive.Tile, Integer> p1RemainingTiles = game.getCurrentTurn().getTiles();
        
        for(Map.Entry<Hive.Tile, Integer> entry : p1RemainingTiles.entrySet()){
            entry.setValue(0);
        }
        ////

        game.pass();

        //assert
        assertEquals(game.getCurrentTurn().getColour(), Hive.Player.BLACK);
    }
    
    @Test // 3C
    void testIfPlayerHasWon() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -2, 0);

        game.move(1, -1, 0, -1);
        game.move(-2, 0, -1, -1);

        game.play(Hive.Tile.BEETLE, 0, 1);
        game.move(-1, -1, -2, 0);

        game.move(0, 1, -1, 1);
        game.move(-2, 0, -1, -1);

        game.play(Hive.Tile.SPIDER, 1, -1);
        game.move(-1, -1, -2, 0);

        game.play(Hive.Tile.SPIDER, 1, 0);
        game.move(-2, 0, -1, -1);

        game.play(Hive.Tile.GRASSHOPPER, 0, 1);
        game.move(-1, -1, -2, 0);
        
        //assert
        assertTrue(game.isWinner(Hive.Player.BLACK));
        
    }

    @Test
    void testIfGameEndsInDraw() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 0);

        game.play(Hive.Tile.BEETLE, 1, -1);
        game.play(Hive.Tile.BEETLE, -1, -1);

        game.play(Hive.Tile.SPIDER, 0, 1);
        game.play(Hive.Tile.SPIDER, -2, 1);

        game.move(+1, -1, 0, -1);
        game.move(-2, 1, -1, 1);
        
        game.play(Hive.Tile.SPIDER, 1, -1);
        game.play(Hive.Tile.SPIDER, -2, 1);

        //assert
        assertTrue(game.isDraw());
        
    }


}
