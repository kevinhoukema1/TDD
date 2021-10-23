package nl.hanze.hive;

import java.util.Stack;

public class TileStack {

    private Stack<Tile> stack;

    public TileStack(Tile tile){
        this.stack = new Stack<>();
        
        putInStack(tile);

    }   
    
    public Stack<Tile> getStack(){
        return this.stack;

    }

    public void putInStack(Tile tile){
        this.stack.push(tile);

    }

    public Tile pullFromStack(){
        return this.stack.pop();

    }

}
