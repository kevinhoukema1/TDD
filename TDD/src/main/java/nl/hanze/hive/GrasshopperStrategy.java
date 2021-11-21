package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class GrasshopperStrategy implements Strategy{

    
    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        // if the moveset contains the required position. it returns true
        if(moveSet(board, from).contains(to) && !from.equals(to) && !from.getNeighbours().contains(to)){
            return true;
        
        } 
        
        //else false
        return false;
       

    }


    @Override
    public ArrayList<Coordinate> moveSet(Board board, Coordinate from) {
        //create new list to set a list of all moves within the line
        ArrayList<Coordinate> moves = new ArrayList<Coordinate>();

        //create a list of all directions to loop through
        int[][] directies = {{-1, 0}, {0, -1}, {1, -1}, {1, 0}, {0, 1}, {-1, 1}};
        
        for(int[] directie : directies){
            // add for every direction all available moves from that line
            moves.add(jump(board, from, directie[0], directie[1]));
        }
        
        //Now we created a list of every spot the grasshopper can jump to.
        return moves;
    }

    public Coordinate jump(Board board, Coordinate from, int q, int r){

        // set the next cord with the direction given. (current qr + the +-1 or 0 from the direction)
        Coordinate coord = new Coordinate(from.q + q, from.r + r);

        // check if the spot is empty or not
        if(board.getCurrentBoard().get(coord) != null && !board.getCoordinateStack(coord).isEmpty())
        {
        
            // if the spot is not empty we can continue jumping
           return jump(board, coord, q, r);
        }
        else{
            // if the spot is empty we return the list of available positions
            // The hopper cannot hop to an occupied space because the recursive function does not add it to the spotlist.
            return coord;
        }
    }


    @Override
    public Boolean checkBlockingNeighbours(Board board, Coordinate from, Coordinate to) {
        //unused function for the grasshopper
        return false;
    }
   
    
}
