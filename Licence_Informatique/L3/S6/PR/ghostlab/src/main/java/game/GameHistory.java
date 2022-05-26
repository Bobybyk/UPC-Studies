package main.java.game;

import java.util.HashMap;
import java.util.LinkedList;

public class GameHistory {
    private final String playerID;
    private final HashMap<String, Integer> playersScore;
    private final LinkedList<String> messagesHistory;
    private final String winner;

    public GameHistory(String playerID, HashMap<String, Player> players, LinkedList<String> messagesHistory) {
        this.playerID = playerID;
        this.playersScore = new HashMap<>();

        for (HashMap.Entry<String, Player> player : players.entrySet()) {
            this.playersScore.put(player.getKey(), player.getValue().getScore());
        }

        this.messagesHistory = new LinkedList<>();
        for (int i = 0 ; i < messagesHistory.size(); i++) {
            this.messagesHistory.add(messagesHistory.get(i));
        }

        int max = -1;
        String winner = "";
        for (HashMap.Entry<String, Player> player : players.entrySet()) {
            if (player.getValue().getScore() > max) {
                max = player.getValue().getScore();
                winner = player.getKey();
            }
        }
        this.winner = winner;
    }

    public String getPlayerID() {
        return playerID;
    }

    public HashMap<String, Integer> getPlayers() {
        return playersScore;
    }

    public LinkedList<String> getMessagesHistory() {
        return messagesHistory;
    }
    public String getWinner() {
        return winner;
    }
    public int getWinnerScore() {
        return playersScore.get(winner);
    }

}
