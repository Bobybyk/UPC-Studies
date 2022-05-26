package main.java.game;

public class Ghost {
    private final int x;
    private final int y;
    private final long creationTime;

    public Ghost(int x, int y, long creationTime) {
        this.x = x;
        this.y = y;
        this.creationTime = creationTime;
    }

    public int getPosX() {
        return x;
    }
    public int getPosY() {
        return y;
    }
    public long getCreationTime() {
        return creationTime;
    }
}
