package server;

import protocol.Request;
import protocol.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        // Initialize input and output streams
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            // handle client requests
            handleRequests();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // close the client socket when done
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRequests() throws IOException, ClassNotFoundException {
        while (true) {
            // read the request object from the client
            Request request = (Request) inputStream.readObject();
            // process the request and generate a response
            Response response = processRequest(request);
            // send the response back to the client
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private Response processRequest(Request request) {


        return null;
    }

    public void start() {
        new Thread(this).start();
    }
}

