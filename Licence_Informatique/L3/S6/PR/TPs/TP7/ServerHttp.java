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
    private ServerSocket socketServer = null;

    public ServerHttp() {
        int max = 0;
        try {
            this.socketServer = new ServerSocket(8080);
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(true) {
            try {
                new ThreadServer(this.socketServer.accept(), this).start();
                max++;
                System.out.println("Thread number : " + max);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            if(max > 10) {
                try {
                    this.socketServer.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
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
            System.exit(1);
		}
        return strToReturn;
    }

    public String listen(BufferedReader in) {
        
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
                System.exit(1);
            }

        }
        return entete+formatageFichierHtml();
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new ServerHttp();
    }

}