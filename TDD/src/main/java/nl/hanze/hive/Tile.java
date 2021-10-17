package nl.hanze.hive;

public class Tile {
    private Hive.Tile type;

    public Tile(Hive.Tile type){
        this.type = type;
    }

    public Hive.Tile getType(){
        return this.type;
    }

}
