package no.hvl.gruppe13.WebYatzy;

class Die {

    private Integer id;
    private Integer verdi;

    public Die(int id) {
        this.id = id;
    }

    public void roll() {
        this.verdi = (int) Math.floor(Math.random() * 5 + 1);
    }

    public int getVerdi() {
        return this.verdi;
    }

    public int getId() {
        return this.id;
    }
}