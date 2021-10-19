package nl.hanze.hive;

import java.util.HashMap;
import java.util.Stack;

public class Board {
    private HashMap<Coordinate, Tile> board;

    public Board(){
        this.board = new HashMap<>();
    } 

    public HashMap<Coordinate, Tile> getCurrentBoard() {
        return this.board;

    }

    public void setTile(Coordinate coordinate, Hive.Tile tile){
       
        this.board.put(coordinate, new Tile(tile));
       
    }

    public Tile getTilePosition(Coordinate coordinate){
        return this.board.get(coordinate);
        
    }
    
    public void moveTile(Coordinate oldCoordinate, Coordinate newCoordinate){
        Tile oldTile = board.get(oldCoordinate);
        this.board.remove(oldCoordinate);
        this.board.put(newCoordinate, oldTile);

    }

    // public Stack<>
}
