package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

// import nl.hanze.hive.Hive.Tile;



public class SpelmateriaalSpec {
    
    @Test 
    void testIfPlayerTileColourIsWhite(){

        //arrange
        Player whiteTile = new Player();

        //act
        whiteTile.setColour(Hive.Player.WHITE);
        
        //assert
        assertEquals(Hive.Player.WHITE, whiteTile.getColour());

    }
    
    @Test 
    void testIfPlayerTileColourIsBlack(){

        //arrange
        Player blackTile = new Player();

        //act
        blackTile.setColour(Hive.Player.BLACK);
        
        //assert
        assertEquals(Hive.Player.BLACK, blackTile.getColour());

    } 
}
