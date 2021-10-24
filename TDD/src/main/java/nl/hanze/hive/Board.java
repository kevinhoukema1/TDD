package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import nl.hanze.hive.Hive.IllegalMove;

public class Board {
    private HashMap<Coordinate, TileStack> board;
    private int beurt;

    public Board(){
        this.board = new HashMap<>();
        this.beurt = 1;
    } 

    public HashMap<Coordinate, TileStack> getCurrentBoard() {
        return this.board;

    }

    public void increaseTurn(){
        this.beurt++;
    }

    public int getTurn(){
        return this.beurt;
    }

    
    public void checkForQueenAfterFourTurns(Hive.Player currentPlayer) throws IllegalMove{
        // Checks if queen is played in turn 4.
        Boolean found = false;
        for (Map.Entry<Coordinate, TileStack> tile : this.board.entrySet()){
            if(tile.getValue().peek().getType() == Hive.Tile.QUEEN_BEE && tile.getValue().peek().getPlayer() == currentPlayer){
                // If queen is found, function breaks and returns to  settile
                found = true;
            }
        }
        // If no queen is found, it needs to be played.
        if(!found){
            throw new IllegalMove("SPEEL JE QUEEN HENK! " + currentPlayer);
        }
        
    }

    public void setTile(Coordinate coordinate, Hive.Tile hiveTile, Hive.Player currentPlayer) throws IllegalMove{
        if(getTurn() == 7 || getTurn() == 8){
            checkForQueenAfterFourTurns(currentPlayer);
        }
        if(legalMove(coordinate, currentPlayer)){
            if(this.board.get(coordinate) != null ){
                Tile tile = new Tile(hiveTile, currentPlayer);
                this.board.get(coordinate).putInStack(tile);
            }
            else{
                Tile tile = new Tile(hiveTile, currentPlayer);
                this.board.put(coordinate, new TileStack(tile));
    
            } 
            increaseTurn();
        }
        else{
            throw new IllegalMove("DIT IS EEN ILLEGALE ZET HENK!");
        }
    
       
    }
    
    public Boolean legalMove(Coordinate coordinate, Hive.Player currentPlayer){
        
        if(getCurrentBoard().isEmpty() || this.beurt <= 2){
            return true;
        }
        ArrayList<Coordinate> neighbours = coordinate.getNeighbours();
        for(Coordinate neighbour : neighbours){
            
            // get(neighbour) == null -> checks all neighbours to see if there is at least one.
            // get player checks if the neighbour is from the same colour
            if(getCurrentBoard().get(neighbour) != null 
            && getCurrentBoard().get(neighbour).peek() != null 
            && getCurrentBoard().get(neighbour).peek().getPlayer() == currentPlayer)
            {
                return true;
            }
        }
        return false;
    }

    public Tile getTilePosition(Coordinate coordinate){
        return this.board.get(coordinate).getStack().peek();

    }
    
    public void moveTile(Coordinate oldCoordinate, Coordinate newCoordinate, Hive.Player player) throws IllegalMove{
        Tile tile = this.board.get(oldCoordinate).pullFromStack();
        setTile(newCoordinate, tile.getType(), player);

    } 

    public Stack<Tile> getCoordinateStack(Coordinate coordinate){
        return this.board.get(coordinate).getStack();
    }

}
