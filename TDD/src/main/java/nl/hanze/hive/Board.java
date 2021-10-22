package nl.hanze.hive;

import java.util.HashMap;
import java.util.Stack;

public class Board {
    private HashMap<Coordinate, TileStack> board;

    public Board(){
        this.board = new HashMap<>();
    } 

    public HashMap<Coordinate, TileStack> getCurrentBoard() {
        return this.board;

    }

    public void setTile(Coordinate coordinate, Hive.Tile tile){
        if(this.board.get(coordinate) != null){
            this.board.get(coordinate).putInStack(tile);
        }
        else{
            this.board.put(coordinate, new TileStack(tile));

        }
       
    }

    public Tile getTilePosition(Coordinate coordinate){
        return this.board.get(coordinate).getStack().peek();

    }
    
    public void moveTile(Coordinate oldCoordinate, Coordinate newCoordinate){
        Tile tile = this.board.get(oldCoordinate).pullFromStack();
        setTile(newCoordinate, tile.getType());

    } 

    public Stack<Tile> getCoordinateStack(Coordinate coordinate){
        return this.board.get(coordinate).getStack();
    }
}
