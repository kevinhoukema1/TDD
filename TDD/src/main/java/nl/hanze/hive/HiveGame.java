package nl.hanze.hive;

public class HiveGame implements Hive {

    Board currentBoard;

    public HiveGame(){
        this.currentBoard = new Board();

    }

    public Board getBoard(){
        return this.currentBoard;
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        this.currentBoard.setTile(new Coordinate(q,r), tile);

    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
       
        
    }

    @Override
    public void pass() throws IllegalMove {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isWinner(Player player) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDraw() {
        // TODO Auto-generated method stub
        return false;
    }
   

    

    
}
