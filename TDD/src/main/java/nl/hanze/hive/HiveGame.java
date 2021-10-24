package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Map;

public class HiveGame implements Hive {

    private Board currentBoard;
    private Hive.Player currentPlayer;
    private Hive.Player winner;


    public HiveGame(){
        this.currentBoard = new Board();
        this.currentPlayer = Hive.Player.WHITE;
    }

    public Board getBoard(){
        return this.currentBoard;
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        
        getBoard().setTile(new Coordinate(q,r), tile, this.currentPlayer);
        
        swapTurn();
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        Coordinate from = new Coordinate(fromQ, fromR);
        Coordinate to = new Coordinate(toQ, toR);
        
        getBoard().moveTile(from, to, this.currentPlayer);
        swapTurn();
    }

    @Override
    public void pass() throws IllegalMove {
        getBoard().increaseTurn();
        swapTurn();
        
    }

    @Override
    public boolean isWinner(Player player) {
        // for elkse enrty in het board
        for (Map.Entry<Coordinate, TileStack> entry : this.currentBoard.getCurrentBoard().entrySet()) {
            
            // pak de current value <TileStack<tile>>
            TileStack thisStack = entry.getValue();
            
            // if the tile = the queen bee
            if(thisStack.getStack().peek().getType() == Hive.Tile.QUEEN_BEE){
                
                // if player = current player, this queen is its, so we continue to search for the other.
                if(thisStack.getStack().peek().getPlayer() == player){
                    continue;
                }
                // Beecoordinates found
                Coordinate beeCo = entry.getKey();

                // generate list van neighbours
                ArrayList<Coordinate> neighbours = beeCo.getNeighbours();
                
                // for elke neighbour in de gegenereerde lijst
                for(Coordinate neighbour : neighbours){
                    // check in het board of die coordinate een tile erin heeft
                    if(getBoard().getCurrentBoard().get(neighbour) == null){
                        // Zo niet, is de game nog niet voorbij.
                        return false;
                    }

                }
                // Zo wel, is de player, de winnaar

                this.winner = player;
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean isDraw() {
        return isWinner(Hive.Player.WHITE) && isWinner(Hive.Player.BLACK);
    }
   
    public void swapTurn(){
        
        this.currentPlayer = (this.currentPlayer.equals(Hive.Player.WHITE) ? Hive.Player.BLACK : Hive.Player.WHITE);
    }

    public Hive.Player getCurrentTurn(){
        return this.currentPlayer;
    }
    

    
}
