package nl.hanze.hive;

public class HiveGame implements Hive {

    private Board currentBoard;
    private Hive.Player currentPlayer;

    public HiveGame(){
        this.currentBoard = new Board();
        this.currentPlayer = Hive.Player.WHITE;

    }

    public Board getBoard(){
        return this.currentBoard;
    }

    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        getBoard().setTile(new Coordinate(q,r), tile);

        swapTurn();
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        Coordinate from = new Coordinate(fromQ, fromR);
        Coordinate to = new Coordinate(toQ, toR);
        
        getBoard().moveTile(from, to);
        swapTurn();
    }

    @Override
    public void pass() throws IllegalMove {
        swapTurn();
        
    }

    @Override
    public boolean isWinner(Player player) {
        
        return false;
    }

    @Override
    public boolean isDraw() {
        // TODO Auto-generated method stub
        return false;
    }
   
    public void swapTurn(){
        this.currentPlayer = (this.currentPlayer.equals(Hive.Player.WHITE) ? Hive.Player.BLACK : Hive.Player.WHITE);
    }

    public Hive.Player getCurrentTurn(){
        return this.currentPlayer;
    }
    

    
}
