package nl.hanze.hive;

import java.util.ArrayList;

public class Coordinate {
    private int q;
    private int r;

    public Coordinate(int q, int r){
        this.q = q;
        this.r = r;
    }

    public int[] getQR(){
        return new int[]{this.q, this.r};
 
    }

    public ArrayList<Coordinate> getNeighbours(){
        ArrayList<Coordinate> list = new ArrayList<>();
        // current = 0, 0
        list.add(new Coordinate(q - 1, r + 1)); // linksonder
        list.add(new Coordinate(q - 1, r)); // links
        list.add(new Coordinate(q, r - 1)); // linksboven
        list.add(new Coordinate(q + 1, r - 1)); // rechtsboven
        list.add(new Coordinate(q + 1, r)); // rechts
        list.add(new Coordinate(q, r + 1)); // rechtsonder

        return list;

    }

}
