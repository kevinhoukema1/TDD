package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import nl.hanze.hive.Hive.IllegalMove;

public class Board {
    private HashMap<Coordinate, TileStack> board;
    private int beurt;
    public StratFactory factory;

    public Board(){
        this.board = new HashMap<>();
        this.beurt = 1;
        this.factory = new StratFactory();
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

    public Strategy getStrat(Hive.Tile strat){
        return this.factory.getStrat(strat);
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
            Tile tile = new Tile(hiveTile, currentPlayer);
            if(this.board.get(coordinate) != null ){
                this.board.get(coordinate).putInStack(tile);
            }
            else{
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
        Strategy strat = getStrat(getCoordinateStack(oldCoordinate).peek().getType());
        // check if the player has played a queen before being able to move.
        if(!checkForQueen(player)){
            
            throw new IllegalMove("JE HEBT EERST JE QUEEN NODIG VOORDAT JE KAN BEWEGEN HENK!");
        }
        // check if a move is legal
        else if(!legalMove(newCoordinate, player)){
            throw new IllegalMove("DIT IS GEEN LEGALE MOVE HENK!!");

        }
        // check if the move doesn't break a chain
        else if(chainBreak(oldCoordinate, newCoordinate, player)){
            
            throw new IllegalMove("JE SLOOPT JE KETTING HENK!");
        }
        //check if the location has available moves
        else if(strat.checkBlockingNeighbours(this, oldCoordinate, newCoordinate)){
            // Also checks if both common neighbours have a tile in it.
            throw new IllegalMove("JE HEBT BLOCKING NEIGHBOURS HENK!");
        }
        else{
            // Remove tile from stack
            Tile moveTile = this.board.get(oldCoordinate).getStack().pop();
            // check if stack is empty, if so, remove the coordinate
            this.board.entrySet().removeIf(ent -> ent.getValue().getStack().isEmpty());
            // set old tile on new coordinate
            setTile(newCoordinate, moveTile.getType(), player);
        }
    } 

    public Boolean chainBreak(Coordinate oldCoordinate, Coordinate newCoordinate, Hive.Player player) throws IllegalMove{

        // Pull tile from the stack
        Tile tile = getCurrentBoard().get(oldCoordinate).getStack().pop();
       
        // Do the move
        if(this.board.get(newCoordinate) != null ){
            this.board.get(newCoordinate).putInStack(tile);
        }
        else{
            this.board.put(newCoordinate, new TileStack(tile));

        } 

        // make visited list to check with the board size later on
        ArrayList<Coordinate> visited = new ArrayList<>();
        
        // visited calls the recersive function seek to get a list of all neighbour connections to see 
        // how many directly connected neighbours are with this coordinate
        visited = seek(newCoordinate, visited);
        
        // Pull tile from the stack
        Tile oldTile = getCurrentBoard().get(newCoordinate).getStack().pop();
       
        // Do the move
        if(this.board.get(oldCoordinate) != null ){
            this.board.get(oldCoordinate).putInStack(oldTile);
        }
        else{
            this.board.put(oldCoordinate, new TileStack(oldTile));

        } 
        this.board.entrySet().removeIf(ent -> ent.getValue().getStack().isEmpty()); 
        // if the size of visited is smaller than the current board.size it means there are two or more islands
        if(visited.size() < getCurrentBoard().size() -1){
            // throw to get the sizes, change back to return true to let the move function handle the error.
            return true;
        }
        
        // the visited is the same size as current so there is only one island.
        return false;

    }

    public ArrayList<Coordinate> seek(Coordinate coordinate, ArrayList<Coordinate> visited ) throws IllegalMove{
        // copy the arraylist of the given visited
        ArrayList<Coordinate> newVisited = visited;

        // add the coordinate to the list. This list should contain all coordinates that are not empty and are connected
        newVisited.add(coordinate); 
        ArrayList<Coordinate> neighbours = coordinate.getNeighbours();
        // loop throug all the neighbours of this coordinate
        for(Coordinate neighbour : neighbours){
            
            // if the coordinate is not empty it is a neighbour
            if(getCurrentBoard().get(neighbour) != null && !getCurrentBoard().get(neighbour).getStack().isEmpty()){
                // check if the coordinate is already visited
                if(newVisited.contains(neighbour)){
                    continue;
                }
                
                // the arraylist newVisited will recusrively call seek() again with the neighbour and the list
                // of the visited nodes. 
                else{
                    newVisited = seek(neighbour, newVisited);
                }
            }
        }
        // if all loops have been done, return the visitedList
        return newVisited; 
    }

    public Stack<Tile> getCoordinateStack(Coordinate coordinate){
        return this.board.get(coordinate).getStack();
    }

}
