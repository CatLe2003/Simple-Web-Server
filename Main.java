package webserver;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static ServerSocket serverSocket;
    static int PORT = 8080; 
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            // serverSocket.setSoTimeout(10000);

            while (true) {
                Socket s = serverSocket.accept();
                WebSocket webSocket = new WebSocket(s);
                Connection connection = new Connection(webSocket);
                connection.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
