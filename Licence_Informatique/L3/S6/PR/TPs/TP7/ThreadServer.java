import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServer extends Thread{
    private Socket socket;
    private ServerHttp server;
    private BufferedReader in;
    private PrintWriter out;

    public ThreadServer(Socket socket, ServerHttp server) {
        this.socket = socket;
        this.server = server;

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    @Override
    public void run() {
        String messageToSend = server.listen(in);
        System.out.println(messageToSend);
        this.out.println(messageToSend);
        this.out.flush();
        System.out.println("OK");

        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
