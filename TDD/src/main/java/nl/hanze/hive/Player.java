package nl.hanze.hive;

import java.util.HashMap;

import nl.hanze.hive.Hive.IllegalMove;

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


    public void playTile(HiveGame game, Hive.Tile tile, int q, int r) throws IllegalMove{
        if(game.getCurrentTurn() == this.colour){
            if(tiles.get(tile) > 0){
                tiles.put(tile, tiles.get(tile) - 1); 
                game.play(tile, q, r);
                if(game.isWinner(getColour())){
                    System.out.println(getColour() + "! You win :D");
                }
                Hive.Player opponent = (getColour() == Hive.Player.WHITE) ? Hive.Player.BLACK : Hive.Player.WHITE;
                if(game.isWinner(opponent)){
                    System.out.println(getColour() + "! You win :D");
                }
            }
            else{
                throw new IllegalMove("JE HEBT GEEN " + tile + " TILES MEER HENK! " ); 
            }
        }
        else{
            throw new IllegalMove("HET IS JOUW BEURT NIET HENK!");
        }
        
    }

    public void moveTile(HiveGame game, int fromQ, int fromR, int toQ, int toR) throws IllegalMove{
        if(game.getCurrentTurn() == this.colour){
            game.move(fromQ, fromR, toQ, toR);
        }
        else{
            throw new IllegalMove("HET IS JOUW BEURT NIET HENK!");
        }
    }

    public void pass(HiveGame game) throws IllegalMove{
        game.pass();
    }
            
}
