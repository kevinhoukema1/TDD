package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class GrasshopperStrategy implements Strategy{

    
    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        return true;

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
        Boolean neighbourOne = board.getCoordinateStack(neighboursFrom.get(0)).isEmpty();
        Boolean neighbourTwo = board.getCoordinateStack(neighboursFrom.get(1)).isEmpty();

        // If both are not empty, it means it is blocking. 
        //If only one is empty but the other one isn't, there is no blockage
        if(!neighbourOne && !neighbourTwo){
            return true;
        }
        
        //No blockage detected
        return false;
    }

    @Override
    public ArrayList<Coordinate> moveSet(Board board, Coordinate from) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
