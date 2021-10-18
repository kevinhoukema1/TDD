package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;




public class SpelmateriaalSpec {
    
    @Test 
    void testIfPlayerWhiteColourIsWhite(){

        //arrange
        Player whiteTile = new Player(Hive.Player.WHITE);

        //assert
        assertEquals(Hive.Player.WHITE, whiteTile.getColour());

    }
    
    @Test 
    void testIfPlayerBlackColourIsBlack(){

        //arrange
        Player blackTile = new Player(Hive.Player.BLACK);

        
        //assert
        assertEquals(Hive.Player.BLACK, blackTile.getColour());

    } 

    @Test
    void testIfTileIsBeeQeueen(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.QUEEN_BEE);

        //Assert
        assertEquals(Hive.Tile.QUEEN_BEE, tile.getType());
    } 

    @Test
    void testIfTileIsSpoder(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.SPIDER);

        //Assert
        assertEquals(Hive.Tile.SPIDER, tile.getType());
    } 

    @Test
    void testIfTileIsBeetle(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.BEETLE);

        //Assert
        assertEquals(Hive.Tile.BEETLE, tile.getType());
    } 

    @Test
    void testIfTileIsSS(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.SOLDIER_ANT);

        //Assert
        assertEquals(Hive.Tile.SOLDIER_ANT, tile.getType());
    } 

    @Test
    void testIfTileIsGrassHoper(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.GRASSHOPPER);

        //Assert
        assertEquals(Hive.Tile.GRASSHOPPER, tile.getType());
    } 


    // <QUEENBEE,   1>
    // <SPODER,     2>
    // <BEETLE,     2>
    // <SOLDIERANT, 3>
    // <GRASSHOPPER,3>

    @Test
    void testIfPlayerGetsCorrectAmmountOfQueenBee(){
        //Arrange
        Player p1 = new Player(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(1, p1Tiles.get(Hive.Tile.QUEEN_BEE));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfSpoders(){
        //Arrange
        Player p1 = new Player(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(2, p1Tiles.get(Hive.Tile.SPIDER));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfBeetle(){
        //Arrange
        Player p1 = new Player(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(2, p1Tiles.get(Hive.Tile.BEETLE));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfSS(){
        //Arrange
        Player p1 = new Player(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(3, p1Tiles.get(Hive.Tile.SOLDIER_ANT));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfGrasshopper(){
        //Arrange
        Player p1 = new Player(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(3, p1Tiles.get(Hive.Tile.GRASSHOPPER));
    }


     
    
}
