import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientDaytime {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private Scanner sc = new Scanner(System.in);//pour lire à partir du clavier

    public ClientDaytime() {
        
        try {
            this.clientSocket = new Socket("192.168.70.237",13);
            //flux pour envoyer
            this.out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String daytimeMessage = in.readLine();

            System.out.println(daytimeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientDaytime();
    }
}
