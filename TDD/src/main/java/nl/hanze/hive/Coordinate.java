package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Objects;

public class Coordinate {
    public int q;
    public int r;

    public Coordinate(int q, int r){
        this.q = q;
        this.r = r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q,r);
    
    }
    public boolean equals(Object obj)
	{
    	if(this == obj)
    		return true;
    	if((obj == null) || (obj.getClass() != this.getClass()))
    		return false;
        // object must be Test at this point
        Coordinate c = (Coordinate)obj;
        return this.q == c.q && this.r == c.r;
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
