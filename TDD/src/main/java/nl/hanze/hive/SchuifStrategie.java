package nl.hanze.hive;

import nl.hanze.hive.Hive.IllegalMove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SchuifStrategie implements Strategy{

    @Override
    public ArrayList<Coordinate> moveSet(Board board, Coordinate from) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean checkBlockingNeighbours(Board board, Coordinate from, Coordinate to) {
        // For now; if the stack is 2 high (aka a beelte is on there), it gets free reign
        if(board.getCurrentBoard().get(from) != null && board.getCoordinateStack(from).size() == 2){
            return false;
        }

        // get all from and to neighbours
        ArrayList<Coordinate> neighboursFrom = from.getNeighbours();
        ArrayList<Coordinate> neighboursTo = to.getNeighbours();
        
        // only keep the ones that overlap
        neighboursFrom.retainAll(neighboursTo);

        if(neighboursFrom.size() < 2){
            return false;
        }

        // Check if either one is empty
        // If both are not empty, it means it is blocking. 
        // If only one is empty but the other one isn't, there is no blockage
        if(board.getCurrentBoard().get(neighboursFrom.get(0)) != null && board.getCurrentBoard().get(neighboursFrom.get(1)) != null){
            return true;
        }

        // check if at least one is filled
        if(board.getCurrentBoard().get(neighboursFrom.get(0)) == null && board.getCurrentBoard().get(neighboursFrom.get(1)) == null){
            // None of the common neighbours have a tile in it.
            return true;
        }

        //No blockage detected
        return false;
    }

    @Override
    public boolean canMoveTo(Board board, Coordinate oldCoordinate, Coordinate newCoordinate) {
       
        return true;
    }

    

    

    
    
}
