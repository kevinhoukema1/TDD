package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class SoldierantStrategy extends SchuifStrategie{
   
    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        ArrayList<Coordinate> availablePositions = searchPath(board, from, new ArrayList<>());
        
        if(availablePositions.contains(to) && !from.equals(to)){
            return true;
        }
        return false;

    }
    
    // depth first search om de path te vinden naar een locatie en elke stap ertussen te checken op rules
    private ArrayList<Coordinate> searchPath(Board board, Coordinate from, ArrayList<Coordinate> visited) {
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
            if(!checkBlockingNeighbours(board, from, neighbour)){
                searchPath(board, neighbour, newVisited);
            }
        }

        return visited;
    }


}
