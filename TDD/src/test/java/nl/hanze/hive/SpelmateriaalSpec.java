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
        PlayerInventory whiteTile = new PlayerInventory(Hive.Player.WHITE);

        //assert
        assertEquals(Hive.Player.WHITE, whiteTile.getColour());

    }
    
    @Test 
    void testIfPlayerBlackColourIsBlack(){

        //arrange
        PlayerInventory blackTile = new PlayerInventory(Hive.Player.BLACK);

        
        //assert
        assertEquals(Hive.Player.BLACK, blackTile.getColour());

    } 

    @Test
    void testIfTileIsBeeQeueen(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE);

        //Assert
        assertEquals(Hive.Tile.QUEEN_BEE, tile.getType());
    } 

    @Test
    void testIfTileIsSpoder(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.SPIDER, Hive.Player.WHITE);

        //Assert
        assertEquals(Hive.Tile.SPIDER, tile.getType());
    } 

    @Test
    void testIfTileIsBeetle(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.BEETLE, Hive.Player.WHITE);

        //Assert
        assertEquals(Hive.Tile.BEETLE, tile.getType());
    } 

    @Test
    void testIfTileIsSS(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.SOLDIER_ANT, Hive.Player.WHITE);

        //Assert
        assertEquals(Hive.Tile.SOLDIER_ANT, tile.getType());
    } 

    @Test
    void testIfTileIsGrassHoper(){
        
        //Arragne
        Tile tile = new Tile(Hive.Tile.GRASSHOPPER, Hive.Player.WHITE);

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
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(1, p1Tiles.get(Hive.Tile.QUEEN_BEE));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfSpoders(){
        //Arrange
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(2, p1Tiles.get(Hive.Tile.SPIDER));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfBeetle(){
        //Arrange
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(2, p1Tiles.get(Hive.Tile.BEETLE));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfSS(){
        //Arrange
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(3, p1Tiles.get(Hive.Tile.SOLDIER_ANT));
    }

    @Test
    void testIfPlayerGetsCorrectAmmountOfGrasshopper(){
        //Arrange
        PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);

        //Act
        HashMap<Hive.Tile, Integer> p1Tiles =  p1.getTiles();
        
        //Assert
        assertEquals(3, p1Tiles.get(Hive.Tile.GRASSHOPPER));
    }


     
    
}
