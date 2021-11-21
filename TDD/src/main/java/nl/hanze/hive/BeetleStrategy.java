package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

import nl.hanze.hive.Hive.IllegalMove;

public class BeetleStrategy extends SchuifStrategie {

    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        
        if(from.getNeighbours().contains(to)){
            return true;
        }
        
        return false;

    }

    public ArrayList<Coordinate> moveSet(Board board, Coordinate from) {
       return from.getNeighbours();
    }
    
}
