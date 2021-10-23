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
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);
        p2.playTile(game, Hive.Tile.QUEEN_BEE, -1, 0);
        
        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, -1); });
    }
    
    @Test // 4B
    void testIfPlayerCanPlayTile() throws IllegalMove{
        // De opdracht zegt dat het op een leeg veld moet... Maar dit gaat tegen de stack mechanisme in... 
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);

        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);

        //assert
        assertEquals(Hive.Tile.QUEEN_BEE, game.getBoard().getTilePosition(new Coordinate(0, 0)).getType());
    }

    @Test // 4C
    void testIfPlayedTileHasANeighbour() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Player p1 = new Player(Hive.Player.WHITE);
        Player p2 = new Player(Hive.Player.BLACK);
        
        //act
        p1.playTile(game, Hive.Tile.QUEEN_BEE, 0, 0);

        //assert
        assertThrows(Hive.IllegalMove.class, ()->{ p2.playTile(game, Hive.Tile.QUEEN_BEE, -3, 0); });
    }
}
