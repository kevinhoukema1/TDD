package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.TimeLimitExceededException;

import org.junit.jupiter.api.Test;

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
        HashMap<Coordinate, Tile> startBoard = board.getCurrentBoard();

        //assert
        assertEquals(0, startBoard.size());
    }

    //2D
    @Test
    void testIfTileIsInField(){
        //arrange
        Board board = new Board();
        Tile tile = new Tile(Hive.Tile.QUEEN_BEE);
        Coordinate beeCoordinate = new Coordinate(0, 0);

        //act
        // set the tile and coordinate in a hashmap<key,value> in the board
        board.setTile(beeCoordinate, tile);

        // prepare an expected hashmap to check in the assert
        HashMap<Coordinate, Tile> expectedHash= new HashMap<Coordinate, Tile>()
        {{
            // put in the tile and coordinate
            put(beeCoordinate, tile);
        }};

        //assert
        // the .get(tile) returns the coordinate
        assertEquals(expectedHash.get(beeCoordinate), board.getTilePosition(beeCoordinate));

    }

    //2E
    @Test
    void testIfTileCanBeMoved(){
        //arrange
        Board board = new Board();
        Tile tile = new Tile(Hive.Tile.QUEEN_BEE);
        Coordinate beeCoordinate = new Coordinate(0, 0); 

        //act
        board.setTile(beeCoordinate, tile); 
        Coordinate newCoordinate = new Coordinate(0,1);
        board.moveTile(beeCoordinate, newCoordinate);

        //assert
        assertEquals(tile, board.getTilePosition(newCoordinate));
    } 


    //2F
    @Test
    void testIfTilesCanStack(){
        //arrange
        Board board = new Board();
        Tile beeTile = new Tile(Hive.Tile.QUEEN_BEE);
        Coordinate beeCoordinate = new Coordinate(0, 0);  

        Tile spoderTile = new Tile(Hive.Tile.SPIDER);
        Coordinate spoderCoordinate = new Coordinate(0, 0);  

        // board { {0.0, bee} , {0.0, spoder} }

        //act
        board.setTile(beeCoordinate, beeTile);
        board.setTile(spoderCoordinate, spoderTile);
        System.out.println(board.getCurrentBoard());


        //assert
        // assertEquals(expected, actual);
        assertEquals(2, board.getCurrentBoard().get(new Coordinate(0,0)).size());
    }
}
