package nl.hanze.hive;

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
        if(board.getCoordinateStack(from).size() == 2){
            return false;
        }

        // get all from and to neighbours
        ArrayList<Coordinate> neighboursFrom = from.getNeighbours();
        ArrayList<Coordinate> neighboursTo = to.getNeighbours();
        
        // only keep the ones that overlap
        neighboursFrom.retainAll(neighboursTo);

        // Check if either one is empty
        // If both are not empty, it means it is blocking. 
        // If only one is empty but the other one isn't, there is no blockage
        if(board.getCurrentBoard().get(neighboursFrom.get(0)) != null && board.getCurrentBoard().get(neighboursFrom.get(1)) != null){
            return true;
        }
        
        //No blockage detected
        return false;
    }

    
    
}
