package nl.hanze.hive;

import java.util.HashMap;

public class Player {
    Hive.Player colour;
    HashMap<Hive.Tile, Integer> tiles;

    public Player(Hive.Player colour){
        this.tiles = new HashMap<Hive.Tile, Integer>();
        this.colour = colour;
        fillTiles();
    }

    public Hive.Player getColour(){
        return this.colour;

    }

    public void fillTiles(){
        this.tiles.put(Hive.Tile.QUEEN_BEE, 1);
        this.tiles.put(Hive.Tile.SPIDER, 2);
        this.tiles.put(Hive.Tile.BEETLE, 2);
        this.tiles.put(Hive.Tile.SOLDIER_ANT, 3);
        this.tiles.put(Hive.Tile.GRASSHOPPER, 3);
    }

    public HashMap<Hive.Tile, Integer> getTiles(){
        return this.tiles;
    }
}
