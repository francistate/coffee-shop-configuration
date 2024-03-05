package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImpl implements Server{

    private ServerSocket serverSocket;

    public ServerImpl(int port) throws IOException {
        // create a ServerSocket to listen on specified port
        serverSocket = new ServerSocket(port);
        System.out.println("Server started. Listening on port " + port + "...");
    }

    public void start() {
        try {
            // continuously accept client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                // handle client connection in a separate thread
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the server socket when done
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int port = 12345; // Specify the port number
        try {
            ServerImpl server = new ServerImpl(port);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
