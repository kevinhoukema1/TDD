package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class SpiderStrategy extends SchuifStrategie{

    
    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        ArrayList<Coordinate> availablePositions = searchPath(board, from, new ArrayList<>(), 0);
        
        if(availablePositions.contains(to)){
            return true; 
        }
        return false;

    }

    // depth first search om de path te vinden naar een locatie en elke stap ertussen te checken op rules
    private ArrayList<Coordinate> searchPath(Board board, Coordinate from, ArrayList<Coordinate> visited, Integer counter) {
        // copy visited
        ArrayList<Coordinate> newVisited = visited;
        // add last node
        newVisited.add(from);

        //create neighbour list
        ArrayList<Coordinate> neighbours = from.getNeighbours();
        for(Coordinate neighbour : neighbours) {
            if(newVisited.contains(neighbour)){
                continue;
            }
            if(!checkBlockingNeighbours(board, from, neighbour) 
                && (board.getCurrentBoard().get(neighbour) == null 
                || board.getCoordinateStack(neighbour).isEmpty()))    
            {
                if(counter >= 3){
                    continue;
                }
                searchPath(board, neighbour, newVisited, counter + 1);
            }
        }
        
        return visited;
    }
    
}
