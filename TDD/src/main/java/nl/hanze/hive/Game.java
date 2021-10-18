package nl.hanze.hive;

import java.util.Arrays;
import java.util.Map;

public class Game{
    public static void main(String[] args){
        Board board = new Board();
        Tile beeTile = new Tile(Hive.Tile.QUEEN_BEE);
        Coordinate beeCoordinate = new Coordinate(0, 0);  

        Tile spoderTile = new Tile(Hive.Tile.SPIDER);
        Coordinate spoderCoordinate = new Coordinate(0, 0);  

        // board { {0.0, bee} , {0.0, spoder} }

        //act
        board.setTile(beeCoordinate, beeTile);
        board.setTile(spoderCoordinate, spoderTile);
        

        for( Map.Entry<Coordinate, Tile> entry : board.getCurrentBoard().entrySet() ){
            System.out.println( Arrays.toString(entry.getKey().getQR()) + " => " + entry.getValue().getType() );
        }
    }
}