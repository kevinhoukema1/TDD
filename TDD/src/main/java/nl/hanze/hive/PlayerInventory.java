package nl.hanze.hive;

import java.util.HashMap;

import nl.hanze.hive.Hive.IllegalMove;

public class PlayerInventory {
    Hive.Player colour;
    HashMap<Hive.Tile, Integer> tiles;

    public PlayerInventory(Hive.Player colour){
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

    public boolean checkTiles(){
        // check if any of your inventory is filled, if so you can place something.
        for(int i : tiles.values()){
            if(i > 0){
                return true;
            }
        }
        return false;
    }


    public void playTile(HiveGame game, Hive.Tile tile, int q, int r) throws IllegalMove{
        if(game.getCurrentTurn().getColour() == this.colour){
            if(tiles.get(tile) > 0){
                tiles.put(tile, tiles.get(tile) - 1); 
                game.getBoard().setTile(new Coordinate(q,r), tile, getColour());
                // game.play(tile, q, r);
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
        if(game.getCurrentTurn().getColour() == this.colour){
            Coordinate from = new Coordinate(fromQ, fromR);
            Coordinate to = new Coordinate(toQ, toR);
            game.getBoard().moveTile(from, to, getColour());
            // game.move(fromQ, fromR, toQ, toR);
        }
        else{
            throw new IllegalMove("HET IS JOUW BEURT NIET HENK!");
        }
    }

    public void pass(HiveGame game) throws IllegalMove{
        // pass if current player has no more tiles or if no move can be made 
        if(!checkTiles() && !game.getBoard().moveAvailable(getColour())){
            // game.pass();
            game.getBoard().increaseTurn();
        }
        else{
            throw new IllegalMove("JE KAN NIET PASSEN HENK!");
        }
    }




    
            
}
