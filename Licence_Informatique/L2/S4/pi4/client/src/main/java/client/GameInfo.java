package client;

// import java Classes
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;

//import our classes
import server.elements.*;


public class GameInfo {

    //POO implementation

    private static LinkedList<Wall> walls = new LinkedList<Wall>();
    private static LinkedList<Hole> holes = new LinkedList<Hole>();
    private static LinkedList<Treasure> treasures = new LinkedList<Treasure>();

    public static int getWallsNumber() {
        return walls.size();
    }
    public static int getHolesNumber() {
        return holes.size();
    }
    public static int getTreasuresNumber() {
        return treasures.size();
    }

    private static HashMap<String,Integer[]> players = new HashMap<String,Integer[]>();


    //get linkedlist of element
    public static LinkedList<Wall> getWalls() {
        return walls;
    }
    public static LinkedList<Hole> getHoles() {
        return holes;
    }
    public static LinkedList<Treasure> getTreasures() {
        return treasures;
    }
    public static HashMap<String, Integer[]> getPlayerCoordinates() {
        return players;
    }

    //add element to linkedlist 
    public static void addWall(Wall w) {
        walls.add(w);
    }
    public static void addHole(Hole h) {
        holes.add(h);
    }
    public static void addTreasure(Treasure t) {
        treasures.add(t);
    }

    //remove element from linkedlist
    public static boolean removeTreasure(Treasure t) {
        return treasures.remove(t);
    }

    //Player coordinates management
    public static void addPlayer(String name, int[] coordinate) {
        Integer[] tab = {new Integer(coordinate[0]), new Integer(coordinate[1])};
        players.put(name,tab);
    }

    public static int[] getPlayerCoordinates(String name) {
        Integer tab[] = players.get(name);
        int[] coordinates = {tab[0].intValue(),tab[1].intValue()};
        return coordinates;
    }

    public static void setPlayerCoordinates(String name, int x, int y) {
        if(players.containsKey(name)) {
            players.get(name)[0] = x;
            players.get(name)[1] = y;
        } else {
            int[] tab = {x,y};
            addPlayer(name,tab);
            players.get(name)[0] = x;
            players.get(name)[1] = y;
        }
    }

    public static void removePlayerCoordinates(String name) {
        players.remove(name);
    }


    //previous implementation

    private static int nbrOfGames = 0;
    private static int nbrOfGamesCreated = 0;

    private static int[][] availableGameInfos = null;
    private static boolean isStarted = false;

    public static boolean isStarted() {
        return isStarted;
    }

    public static void setStarted(boolean b) {
        isStarted = b;
    }

    private static int[] map = new int[2];

    private static boolean isDead = false;
    private static boolean canPlay = false;

    private static int money = 0;
    private static int gameMod = -1;

    // to get various elements

    public static void setNumberOfGamesCreated() {
        nbrOfGamesCreated+=1;
    }

    public static int getNumberOfGames() {
        return nbrOfGames;
    }
    public static int getNumberOfGamesCreated() {
        return nbrOfGamesCreated;
    }

    public static int getGameNumber() {
        if(availableGameInfos == null)
            return 0;
        else
            return availableGameInfos.length;
    }
    
    public static boolean getLifeState() {
        return isDead;
    }

    public static boolean getPlayable() {
        return canPlay;
    }

    public static int getGameMod() {
        return gameMod;
    }

    public static int[][] getGameInfo() {
        return availableGameInfos;
    }

    // to get the map size
    public static int[] getMap() {
        return map;
    }

    public static void setNumberOfGames(int nbr) {
        nbrOfGames = nbr;
    }

    //money functions
    public static int getMoney() {
        return money;
    }

    public static void addMoney(int amount) {
        if(money < 0)
            return;
        money += amount;
    }

    public static void resetMoney() {
        money = 0;
    }

    //To set the list of joinable games
    public static int[][] getJoinableGames() {
        if(availableGameInfos == null)
            return new int[0][0];
        else
            return availableGameInfos;
    }

    public static void setGameMod(int g) {
        gameMod = g;
    }

    public static void setGameNumber(int i) {
        availableGameInfos = new int[i][6];
    }
    public static void setLifeState(boolean b) {
        isDead = b;
    }

    // to define the map size
    public static void setMap(int x, int y) {
        map[0] = x;
        map[1] = y;
    }

    public static void setPlayable(boolean b) {
        canPlay = b;
    }


    public static void setGamePos(int pos, int[] tab) {
        availableGameInfos[pos] = tab;
    }
    
}