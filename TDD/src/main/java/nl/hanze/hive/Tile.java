package nl.hanze.hive;

public class Tile {
    private Hive.Tile type;
    private Hive.Player player;

    public Tile(Hive.Tile type){
        this.type = type;
    }

    public Hive.Tile getType(){
        return this.type;
    }

    public Hive.Player getPlayer(){
        return this.player;
    }
}
