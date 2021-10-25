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

    
    public Boolean checkForQueen(Hive.Player currentPlayer) throws IllegalMove{
        // Checks if queen is played in turn 4.
        
        for (Map.Entry<Coordinate, TileStack> tile : this.board.entrySet()){
            if(tile.getValue().peek().getType() == Hive.Tile.QUEEN_BEE && tile.getValue().peek().getPlayer() == currentPlayer){
                // If queen is found, function breaks and returns to  settile
                return true;
            }
        }
        // If no queen is found, it needs to be played.
        return false;
        
    }

    public void setTile(Coordinate coordinate, Hive.Tile hiveTile, Hive.Player currentPlayer) throws IllegalMove{
        if(getTurn() == 7 || getTurn() == 8){
            if(!checkForQueen(currentPlayer)){
                throw new IllegalMove("SPEEL JE QUEEN HENK! " + currentPlayer);
            }
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
      
        if(!checkForQueen(player)){
            
            throw new IllegalMove("JE HEBT EERST JE QUEEN NODIG VOORDAT JE KAN BEWEGEN HENK!");
        }
        else if(!legalMove(newCoordinate, player)){
            throw new IllegalMove("DIT IS GEEN LEGALE MOVE HENK!!");

        }
        else if(chainBreak(oldCoordinate, newCoordinate, player)){
            throw new IllegalMove("JE SLOOPT JE KETTING HENK!");
        }
        else{
            Tile moveTile = this.board.get(oldCoordinate).pullFromStack();
            setTile(newCoordinate, moveTile.getType(), player);
        }
    } 

    public Boolean chainBreak(Coordinate oldCoordinate, Coordinate newCoordinate, Hive.Player player) throws IllegalMove{
        // Make copy of the board and do the movement
        HashMap<Coordinate, TileStack> currentboard = getCurrentBoard();

        // Pull tile from the stack
        Tile tile = currentboard.get(oldCoordinate).pullFromStack();
       
        // Do the move
        if(currentboard.get(newCoordinate) != null ){
            currentboard.get(newCoordinate).putInStack(tile);
        }
        else{
            currentboard.put(newCoordinate, new TileStack(tile));
        }

        // make visited list to check with the board size later on
        ArrayList<Coordinate> visited = new ArrayList<>();

        // if the neighbour does not contain a tile, the neighbour is empty and should be skipped
        if(currentboard.get(newCoordinate) != null && !currentboard.get(newCoordinate).getStack().isEmpty()){
            // visited calls the recersive function seek to get a list of all neighbour connections to see 
            // how many directly connected neighbours are with this coordinate
            visited = seek(currentboard, newCoordinate, visited);
        }

        
        // if the size of visited is smaller than the current board.size it means there are two or more islands
        if(visited.size() < currentboard.size() -1){
            // throw to get the sizes, change back to return true to let the move function handle the error.
            throw new IllegalMove("Visited: " + visited.size() + " Current: " + currentboard.size());
            // return true;
        }
        
        // the visited is the same size as current so there is only one island.
        return false;

    }

    public ArrayList<Coordinate> seek(HashMap<Coordinate, TileStack> currentboard, Coordinate coordinate, ArrayList<Coordinate> visited ){
        // copy the arraylist of the given visited
        ArrayList<Coordinate> newVisited = visited;

        // add the coordinate to the list. This list should contain all coordinates that are not empty and are connected
        newVisited.add(coordinate);

        // loop throug all the neighbours of this coordinate
        for(Coordinate neighbour : coordinate.getNeighbours()){
            
            // if the coordinate is not empty it is a neighbour
            if(currentboard.get(neighbour) != null && !currentboard.get(neighbour).getStack().isEmpty()){
                // check if the coordinate is already visited
                if(newVisited.contains(coordinate)){
                    continue;
                }

                // the arraylist newVisited will recusrively call seek() again with the neighbour and the list
                // of the visited nodes. 
                newVisited = seek(currentboard, neighbour, newVisited);
            }
        }
        // if all loops have been done, return the visitedList
        return newVisited; 
    }

    public Stack<Tile> getCoordinateStack(Coordinate coordinate){
        return this.board.get(coordinate).getStack();
    }

}
