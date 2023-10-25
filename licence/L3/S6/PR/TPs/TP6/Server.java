import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket socketServer;
    private Socket socketduserveur;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc;

    public Server() {
        try {
            this.socketServer = new ServerSocket(13);
            this.socketduserveur = socketServer.accept();
            this.in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
            this.out = new PrintWriter(socketduserveur.getOutputStream());
            this.sc = new Scanner(System.in);
            String s = sc.next();
            out.println(s);
            out.flush();
            String clientMessage = in.readLine();
            System.out.println("client message : " + clientMessage);
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}