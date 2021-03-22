package no.hvl.gruppe13.WebYatzy;

import no.hvl.gruppe13.WebYatzy.Die;

import java.util.ArrayList;

class Cup {

    private Integer id;
    private Integer sum;

    private ArrayList<Die> dice;

    public Cup(int id) {
        this.id = id;
        this.dice = new ArrayList<Die>();

        for (int i = 0; i < 2; i++) {
            Die terning = new Die(i);
            this.dice.add(terning);
        }
    }

    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    public int getSum() {
        int sum = 0;

        for (Die die : dice) {
            sum += die.getVerdi();
        }

        return sum;
    }
}