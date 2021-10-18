package nl.hanze.hive;

import java.util.HashMap;

public class Board {
    private HashMap<Coordinate, Tile> currentBoard;

    public Board(){
        this.currentBoard = new HashMap<>();
    } 

    public HashMap<Coordinate, Tile> getCurrentBoard() {
        return this.currentBoard;

    }

    public void setTile(Coordinate coordinate, Tile tile){
        currentBoard.put(coordinate, tile);

    }

    public Tile getTilePosition(Coordinate coordinate){
        return currentBoard.get(coordinate);
    }

    public void moveTile(Coordinate oldCoordinate, Coordinate newCoordinate){
        Tile oldTile = currentBoard.get(oldCoordinate);
        this.currentBoard.remove(oldCoordinate);
        this.currentBoard.put(newCoordinate, oldTile);

    }
}
