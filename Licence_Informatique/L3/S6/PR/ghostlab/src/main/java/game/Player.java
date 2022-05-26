package main.java.game;

public class Player {
    private int score;
    private int posX;
    private int posY;
    private int lastPosX;
    private int lastPosY;
    private int shiftingAsked;

    public Player(int score, int posX, int posY) {
        this.score = score;
        this.posX = posX;
        this.posY = posY;
        this.lastPosX = posX;
        this.lastPosY = posY;
        this.shiftingAsked = 0;
    }

    public Player(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.lastPosX = posX;
        this.lastPosY = posY;
        this.score = 0;
        this.shiftingAsked = 0;
    }

    public Player() {
        this.score = 0;
        this.posX = -1;
        this.posY = -1;
        this.lastPosX = -1;
        this.lastPosY = -1;
        this.shiftingAsked = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void setLastPosX(int lastPosX) {
        this.lastPosX = lastPosX;
    }
    public void setLastPosY(int lastPosY) {
        this.lastPosY = lastPosY;
    }
    public void setShiftingAsked(int shiftingAsked) {
        this.shiftingAsked = shiftingAsked;
    }

    public int getScore() {
        return score;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getLastPosX() {
        return lastPosX;
    }
    public int getLastPosY() {
        return lastPosY;
    }
    public int getShiftingAsked() {
        return shiftingAsked;
    }
}
