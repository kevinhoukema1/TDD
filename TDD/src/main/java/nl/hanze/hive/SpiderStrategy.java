package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;

public class SpiderStrategy extends SchuifStrategie{

    
    @Override
    public boolean canMoveTo(Board board, Coordinate from, Coordinate to) {
        return true;

    }
    
}
