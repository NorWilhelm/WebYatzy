package utility;

import java.util.Random;

public class Dice {

    private int id;
    private final int facets = 6;

    public Dice (int id) {
        this.id = id;
    }

    public int roll(){
        return (int) ((Math.random() * (6 - 1)) + 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacets() {
        return facets;
    }
}
