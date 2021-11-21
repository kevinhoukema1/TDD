package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class SteenspelenSpec {
    @Test // 4A
    void testIfPlayerCantPlaySomethingHeDoesntHave() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);
        
        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ game.play(Hive.Tile.QUEEN_BEE, 0, -1); });
    }
    
    @Test // 4B
    void testIfPlayerCanPlayTile() throws IllegalMove{
        // De opdracht zegt dat het op een leeg veld moet... Maar dit gaat tegen de stack mechanisme in... 
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);

        //assert
        assertEquals(Hive.Tile.QUEEN_BEE, game.getBoard().getTilePosition(new Coordinate(0, 0)).getType());
    }

    @Test // 4C
    void testIfPlayedTileHasANeighbour() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);
        
        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p1.playTile(game, Hive.Tile.QUEEN_BEE, -3, 0); });
    }

    @Test // 4D
    void testIfPlacedTileHasNoEnemyNeighboursIfAtLeastTwoTilesHaveBeenPlaced() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0); 
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 1, 0);
        
        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p2.playTile(game, Hive.Tile.QUEEN_BEE, 0, -1); }); 
    }

    @Test // 4E
    void testIfQueenBeeHasBeenPlacedWithinThreeTurnes() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.SPIDER, 0, 0);
        p2.playTile(game, Hive.Tile.BEETLE, -1, 0);

        p1.playTile(game, Hive.Tile.BEETLE, 1, 0);
        p2.playTile(game, Hive.Tile.BEETLE, -2, 0);
        
        p1.playTile(game, Hive.Tile.BEETLE, 0, 1);
        p2.playTile(game, Hive.Tile.SPIDER, -2, 1);

        //assert
        // error message if Queen hasn't been played on before the fourth turn.
        assertThrows(Hive.IllegalMove.class, ()->{ p1.playTile(game, Hive.Tile.SOLDIER_ANT, 1, -1); });  

    }
}
