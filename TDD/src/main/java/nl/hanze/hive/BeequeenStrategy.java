package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class BeequeenStrategy extends SchuifStrategie{

    
    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        // check if tile can move to available positions. 
        if(!from.getNeighbours().contains(to)){
            return false;
        }

        
        return true;

    }
    
}
