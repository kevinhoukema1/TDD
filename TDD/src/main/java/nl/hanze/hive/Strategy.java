package nl.hanze.hive;

import java.util.ArrayList;

public interface Strategy {


    /**
     * Return a set of available moves for this tile.
     * @param board Board to check
     * @param from location to jump from
     * @return List of possible moves
     */
    ArrayList<Coordinate> moveSet(Board board, Coordinate from);
    
    

}
