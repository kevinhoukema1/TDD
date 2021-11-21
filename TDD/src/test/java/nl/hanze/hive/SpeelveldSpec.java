package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;

public class SpeelveldSpec {

    //2A
    @Test 
    void testIfCoordinateHasQ(){

        //arrange
        Coordinate coordinate = new Coordinate(1,2);

        //act
        int[] q = coordinate.getQR();

        //assert
        assertEquals(1, q[0]);
    } 

    @Test 
    void testIfCoordinateHasR(){

        //arrange
        Coordinate coordinate = new Coordinate(1,2);

        //act
        int[] r = coordinate.getQR();

        //assert
        assertEquals(2, r[1]);
    } 

    //2B
    @Test 
    void testIfTileHasSixNeighbours(){

        //arrange
        Coordinate coordinate = new Coordinate(1,2);

        //act
        ArrayList<Coordinate> neighbours = coordinate.getNeighbours();

        //assert
        assertEquals(6, neighbours.size());
    } 

    //2C
    @Test
    void testIfPlayingFieldIsEmptyAtStart(){
        //arrange
        Board board = new Board();
        
        //act
        HashMap<Coordinate, TileStack> startBoard = board.getCurrentBoard();

        //assert
        assertEquals(0, startBoard.size());
    }

    //2D
    @Test
    void testIfTileIsInField() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        Tile tile = new Tile(Hive.Tile.QUEEN_BEE, Hive.Player.WHITE);

        //act
        // set the tile and coordinate in a hashmap<key,value> in the board
        game.play(tile.getType(), 0, 0); // (QUEEN_BEE, 0, 0) 

        //assert
        assertEquals(Hive.Tile.QUEEN_BEE, game.getBoard().getTilePosition(new Coordinate(0, 0)).getType());

    }

    //2E
    @Test
    void testIfTileCanBeMoved() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0); 
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, 0);
        game.pass();
       
        game.move(1, 0, 1, -1);

        //assert
        assertEquals(Hive.Tile.BEETLE, game.getBoard().getTilePosition(new Coordinate(1, -1)).getType());
    } 


    //2F
    @Test
    void testIfTilesCanStack() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();  

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.SPIDER, 0, 0);

        //assert
        assertEquals(2, game.getBoard().getCoordinateStack(new Coordinate(0,0)).size());
    }

    // Note: De opdracht zegt dat alleen de bovenste verplaatst mag worden. Die gebeurt automatisch omdat we gebruik maken van java.Stack
    // Zie: https://www.educative.io/edpresso/how-to-use-the-stack-class-in-java
    @Test
    void testIfTopFromStackIsMoved() throws IllegalMove{
        //arrange
        HiveGame game = new HiveGame();
        // PlayerInventory p1 = new PlayerInventory(Hive.Player.WHITE);
        // PlayerInventory p2 = new PlayerInventory(Hive.Player.BLACK);

        //act
        game.play(Hive.Tile.QUEEN_BEE, 0, 0);
        game.play(Hive.Tile.QUEEN_BEE, -1, 0);

        game.play(Hive.Tile.BEETLE, 1, 0);
        game.play(Hive.Tile.BEETLE, -2, 1);

        game.play(Hive.Tile.SPIDER, 1, 0);
        game.play(Hive.Tile.BEETLE, -1, -1);

        game.move(1, 0, 1, -1);
        

        //assert
        assertEquals(Hive.Tile.SPIDER, game.getBoard().getTilePosition(new Coordinate(1, -1)).getType());

        // Deze assert faild expres om te laten zien dat op de oude coordinaten nog steeds de queen bee zit.
        // Comment de eerste assert en uncomment deze om dat te zien.

        // assertEquals(Hive.Tile.SPIDER, game.getBoard().getTilePosition(new Coordinate(0, 0)).getType());
    }
}
