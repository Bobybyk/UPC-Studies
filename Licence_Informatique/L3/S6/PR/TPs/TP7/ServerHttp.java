import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerHttp implements Runnable {
    private ServerSocket socketServer;
    private Socket socketduserveur;
    private BufferedReader in;
    private PrintWriter out;

    public ServerHttp() {
        try {
            this.socketServer = new ServerSocket(8080);
            this.socketduserveur = socketServer.accept();
            this.in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
            this.out = new PrintWriter(socketduserveur.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String formatageFichierHtml() {

        String strToReturn = "";
        
        // ouverture du fichier
		File f = new File("ex2.html");

		/*
         * lecture du fichier et ajout de chaque ligne et 
         * de sa séquence de fin dans la chaine à retourner
         */
		try {
			try (Scanner sc = new Scanner(f)) {
				while (sc.hasNextLine()) {
					strToReturn += (sc.nextLine());
                    strToReturn += "\r\n";
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return strToReturn;
    }

    public String listen() {
        
        boolean endSequenceRead = false;
        int line = 0;
        String messageClient;
        String entete =
            "HTTP/1.1 200 OK\r\n"
            +
            "Content-Type: text/html; charset=utf-8\r\n"
            +
            "Connection: close\r\n\r\n";

        while(!endSequenceRead) {

            try {
                System.out.print(line + " : ");
                messageClient = in.readLine();
                if (messageClient.isEmpty()) {
                    endSequenceRead = true;
                }
                System.out.println(messageClient);
                line++;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return entete+formatageFichierHtml();
    }

    @Override
    public void run() {
        String messageToSend = this.listen();
        System.out.println(messageToSend);
        this.out.println(messageToSend);
        this.out.flush();
        System.out.println("OK");
    }

    public static void main(String[] args) {
        
        ServerHttp server = new ServerHttp();
        server.run();
        
        try {
            server.socketServer.close();
            server.socketduserveur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}