package main.java.game;

import java.util.HashMap;
import java.util.LinkedList;

public class GameInfo {
    
    // SAUVEGARDE DES INFORMATIONS


    /*
     * informations client
     */

    // le port UDP utilisé par le client
    public static int portUDP;

    public static int portMulticast = -1;
    public static String ipMulticast = "";

    // marque l'id du client joueur
    public static String playerID;


    /*
     * informations partie courante
     */

    // marque si l'on est inscrit à une partie ou non
    public static boolean isInGame = false;

    //marque si la partie registredGameId dans laquelle on est inscrit a été lancée ou non
    public static boolean hasGameStarted = false;

    // marque l'id de la partie à la quelle on est inscrit
    public static int registredGameId = -1;

    // marque le nom des joueurs avec leur objet
    public static HashMap<String, Player> players = new HashMap<>();

    // marque le dernier id de la partie pour laquelle nous avons reçu LIST!
    public static int listId = -1;

    // marque le nombre de joueurs dans la partie registredGameId
    public static int nbrPlayers = -1;

    // marque l'historique des messages de la partie en cours registredGameId
    public static LinkedList<String> messagesHistory = new LinkedList<>();

    // marque la taille en hauteur du labyrinthe
    public static int gameHeight = -1;

    // marque la taille en largeur du labyrinthe
    public static int gameWidth = -1;

    // marque le nombre de déplacements effectués par le joueur
    public static int nbrMoves = -1;

    // marque la direction de la dernière tentative de déplacement
    public static int lastMoveDirection = -1;

    // marque la position de fantômes
    public static LinkedList<Ghost> ghosts = new LinkedList<>();


    /*
     * informations générales parties disponibles
     */

    // marque le nombre de parties disponibles
    public static int nbrGames = 0;

    // liste des parties
    public static LinkedList<Game> games = new LinkedList<>();

    
    /*
     * historique des parties terminées
     */

    public static LinkedList<GameHistory> gameHistory = new LinkedList<>();

    public static int nbrGhosts = -1;

 
    public static void clearGame() {
        isInGame = false;
        hasGameStarted = false;
        registredGameId = -1;
        players.clear();
        listId = -1;
        nbrPlayers = -1;
        messagesHistory.clear();
        gameHeight = -1;
        gameWidth = -1;
        nbrMoves = -1;
        lastMoveDirection = -1;
        ghosts.clear();
    }

    public static void clear() {
        isInGame = false;
        hasGameStarted = false;
        listId = -1;
        nbrGames = -1;
        portUDP = -1;
        messagesHistory.clear();
        playerID = "";
        registredGameId = -1;
        ipMulticast = "";
        portUDP = -1;
        gameHeight = -1;
        gameWidth = -1;
        nbrPlayers = -1;
        nbrGhosts = -1;
        portMulticast = -1;
        players.clear();
        games.clear();
        nbrMoves = -1;
        lastMoveDirection = -1;
    }

}
