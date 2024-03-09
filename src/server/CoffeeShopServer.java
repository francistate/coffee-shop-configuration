package server;
import protocol.Request;
import protocol.RequestType;
import protocol.Response;
import protocol.ResponseType;
import wrapper.CoffeeShopConfigAPI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import static io.CoffeeShopConfigBuilder.parsePropertiesFile;

public class CoffeeShopServer implements CoffeeShopInterface {

    private ServerSocket serverSocket;

    private Socket socket;
//    private int port;
    private boolean running;
    private CoffeeShopConfigAPI api;

    public CoffeeShopServer(int port) {
//        this.port = port;
        this.running = false;
        this.api = new CoffeeShopConfigAPI();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            this.running = true;
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startServer() {
        while (running) {
            try {
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());
                // Handle client request
                handleClient(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleClient(Socket socket) {
        ResponseType reponseType = null;
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            // Read request from client
            Object requestObject = in.readObject();
            if (requestObject instanceof Request) {
                Request request = (Request) requestObject;
                Object responseData = null;
                String errorMessage = null;

                // Process request and generate response
                switch (request.getRequestType()) {
                    case UPLOAD_PROPERTIES_FILE:
                        Properties properties = (Properties) request.getRequestData();
                        configureCoffeeShopFromProperties(properties);

                        break;
                    case SHOW_AVAILABLE_COFFEE_SHOPS:
                        String[] coffeeShopNames = getAllCoffeeShopNames();
                        responseData = coffeeShopNames;
//                        responseData = getAllCoffeeShopNames();
                        reponseType = ResponseType.SUCCESS;

                        Response response = new Response(reponseType, responseData);
                        out.writeObject(response);



                        break;
                    case PRINT_COFFEE_SHOP:
                        String coffeeShopName = (String) request.getRequestData();
                        // Implement printCoffeeShop logic
                        break;
                    case DELETE_COFFEE_SHOP:
                        coffeeShopName = (String) request.getRequestData();
                        // Implement deleteCoffeeShop logic
                        break;

                    case UPDATE_BASE_PRICE:
                        Object[] data = (Object[]) request.getRequestData();
                        coffeeShopName = (String) data[0];
                        double newPrice = (double) data[1];
                        // Implement updateBasePrice logic
                        break;
                    case ADD_OPTION_TO_OPTION_SET:
                        data = (Object[]) request.getRequestData();
                        coffeeShopName = (String) data[0];
                        String optionSetName = (String) data[1];
                        String newOptionName = (String) data[2];
                        double newOptionPrice = (double) data[3];
                        // Implement addOptionToOptionSet logic
                        break;

                    default:
                        errorMessage = "Invalid request type";
                        break;
                }

                // Send response to client
                Response response;
                if (errorMessage != null) {
                    response = new Response(ResponseType.FAILURE, errorMessage);
                } else {
                    response = new Response(ResponseType.SUCCESS, responseData);
                }
                out.writeObject(response);
                out.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        new CoffeeShopServer(5000);
    }





    @Override
    public void configureCoffeeShop(String coffeeShopName) {
        api.configureCoffeeShop(coffeeShopName);
    }

    @Override
    public void configureCoffeeShopFromProperties(Properties props) {
        api.configureCoffeeShopFromProperties(props);

    }

    @Override
    public void showAvailableCoffeeShops() {
        System.out.println("Showing available coffee shops...");
        api.printAllCoffeeShops();
    }

    @Override
    public void printCoffeeShop(String coffeeShopName) {
        System.out.println("Printing coffee shop: " + coffeeShopName);
        api.printCoffeeShop(coffeeShopName);
    }

    @Override
    public void deleteCoffeeShop(String coffeeShopName) {
        System.out.println("Deleting coffee shop: " + coffeeShopName);
        api.deleteCoffeeShop(coffeeShopName);
    }


    @Override
    public void updateBasePrice(String coffeeShopName, double newPrice) {
        api.updateBasePrice(coffeeShopName, newPrice);

    }

    @Override
    public void addOptionToOptionSet(String coffeeShopName, String optionSetName, String newOptionName, double newPrice) {
        api.addOptionToOptionSet(coffeeShopName, optionSetName, newOptionName, newPrice);
    }

    public String[] getAllCoffeeShopNames() {
        return api.getAllCoffeeShopNames();
    }

    public String getCoffeeShop(String coffeeShopName) {
        return api.getCoffeeShop(coffeeShopName);
    }

    public String[] getOptionSetNames(String coffeeShopName) {
        return api.getOptionSetNames(coffeeShopName);
    }


}
