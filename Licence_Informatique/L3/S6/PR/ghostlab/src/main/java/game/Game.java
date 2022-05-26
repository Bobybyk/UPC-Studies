package main.java.game;

public class Game {
    private int height;
    private int width;
    private int nbrPlayers;

    public Game(int nbrPlayers) {
        this.nbrPlayers = nbrPlayers;
        this.height = -1;
        this.width = -1;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getNbrPlayers() {
        return nbrPlayers;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setNbrPlayers(int nbrPlayers) {
        this.nbrPlayers = nbrPlayers;
    }
}
