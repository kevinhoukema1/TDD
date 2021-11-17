package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public interface Strategy {

    ArrayList<Coordinate> moveSet(Board board, Coordinate from);
    
    Boolean checkBlockingNeighbours(Board board, Coordinate from, Coordinate to);

    boolean canMoveTo(Board board, Coordinate oldCoordinate, Coordinate newCoordinate);

}
