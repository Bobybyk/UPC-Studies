package main.java.commands.dev;

import main.java.commands.CommandDev;
import main.java.console.DebugLogger;
import main.java.console.DebugType;

public class CommandHelp extends CommandDev {

    @Override
    public void execute(String[] args) {
        DebugLogger.print(DebugType.HELP, "###########################################################################");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "#                        Liste commandes utilisateurs                     #");
        DebugLogger.print(DebugType.HELP, "#                         acceptées par le programme                      #");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "#-------------------------------------------------------------------------#");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "# DEBUG :                                                                 #");
        DebugLogger.print(DebugType.HELP, "#   debug ERROR <0/1> : ON/OFF retour d'erreur                            #");
        DebugLogger.print(DebugType.HELP, "#   debug WARNING <0/1> : ON/OFF retour alerte                            #");
        DebugLogger.print(DebugType.HELP, "#   debug ALL <0/1> : ON/OFF retour d'erreur                              #");
        DebugLogger.print(DebugType.HELP, "#   debug CONFIRM <0/1> : ON/OFF confirmation lecture commande            #");
        DebugLogger.print(DebugType.HELP, "#   debug MESSAGE <0/1> : ON/OFF retour messages joueurs                  #");
        DebugLogger.print(DebugType.HELP, "#   debug COM <0/1> : ON/OFF retour div informations système              #");
        DebugLogger.print(DebugType.HELP, "#   debug FORMAT <0/1> : ON/OFF retour message après formatage            #");
        DebugLogger.print(DebugType.HELP, "#   debug LIST : affiche liste types debug                                #");
        DebugLogger.print(DebugType.HELP, "# HELP :                                                                  #");
        DebugLogger.print(DebugType.HELP, "#   help : afficher liste commandes                                       #");
        DebugLogger.print(DebugType.HELP, "# INFOS :                                                                 #"); 
        DebugLogger.print(DebugType.HELP, "#   gameinfo : affiche liste des parties disponibles                      #");
        DebugLogger.print(DebugType.HELP, "#   historical : affiche l'historique des parties jouées                  #");
        DebugLogger.print(DebugType.HELP, "# KILL :                                                                  #");             
        DebugLogger.print(DebugType.HELP, "#   kill : ferme les connections et tue le programme                      #");
        DebugLogger.print(DebugType.HELP, "#   killclient : ferme les connections TCP/UDP                            #");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "#-------------------------------------------------------------------------#");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "# REQUÊTES :                                                              #");
        DebugLogger.print(DebugType.HELP, "#   NEWPL : demande au serveur de créer une partie                        #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : NEWPL id port***               #"); 
        DebugLogger.print(DebugType.HELP, "#   REGIS <arg> : demande au serveur d'être inscrit à la partie en arg    #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : REGIS id port arg***           #");
        DebugLogger.print(DebugType.HELP, "#      -> n'est envoyé que si la partie <arg> existe                      #");
        DebugLogger.print(DebugType.HELP, "#   START : dit au serveur que l'on est prêt pour lancer la partie        #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : START***                       #");
        DebugLogger.print(DebugType.HELP, "#      -> n'est envoyé que si l'on est déjà inscrit dans une partie       #");
        DebugLogger.print(DebugType.HELP, "#   UNREG : demande au serveur d'être désinscrit à la partie              #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : UNREG***                       #");
        DebugLogger.print(DebugType.HELP, "#      -> n'est envoyé que si l'on est déjà inscrit dans une partie       #");
        DebugLogger.print(DebugType.HELP, "#   SIZE? <arg> : demande au serveur la taille du labyrinthe en arg       #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : SIZE? arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   LIST? <arg> : demande au serveur nombre d'inscrits à partie en arg    #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : LIST? arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   GAME? : demande au serveur la liste des parties disponibles           #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : GAME?***                       #");
        DebugLogger.print(DebugType.HELP, "#                                                                         #");
        DebugLogger.print(DebugType.HELP, "#   LEMOV <arg> : déplace le joueur de arg cases vers la gauche           #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : LEMOV arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   UPMOV <arg> : déplace le joueur de arg cases vers le haut             #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : UPMOV arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   RIMOV <arg> : déplace le joueur de arg cases vers la droite           #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : RIMOV arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   DOMOV <arg> : déplace le joueur de arg cases vers le bas              #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : DOMOV arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   IQUIT : quitte la partie en cours                                     #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : IQUIT***                       #");
        DebugLogger.print(DebugType.HELP, "#      -> n'est envoyé que si la partie en cours a démarré                #");
        DebugLogger.print(DebugType.HELP, "#   GLIS? : demande la liste des joueurs présents dans la partie en arg   #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : GLIS? arg***                   #");
        DebugLogger.print(DebugType.HELP, "#   MALL? <arg> : envoie <arg1> à tous les joueurs de la partie           #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : MALL? arg1***                  #");
        DebugLogger.print(DebugType.HELP, "#   SEND? <arg1> <arg2> : envoie <arg2> au joueur <arg>1                  #");
        DebugLogger.print(DebugType.HELP, "#      -> envoie une requête de la forme : SEND? arg1 arg2***             #");
        DebugLogger.print(DebugType.HELP, "###########################################################################");
    }
    
}
