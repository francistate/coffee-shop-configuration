package server;
import protocol.Request;
import protocol.RequestType;
import protocol.Response;
import protocol.ResponseType;
import wrapper.CoffeeShopConfigAPI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;


public class CoffeeShopServer implements CoffeeShopInterface {
    private ServerSocket serverSocket;
    private Socket socket;
//    private int port;
    private boolean running;
    private CoffeeShopConfigAPI api;

    public CoffeeShopServer(int port) {

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

                // Create a new thread to handle the client's request
                Thread clientThread = new Thread(() -> handleClient(socket));
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private void handleClient(Socket socket) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            boolean clientConnected = true;
            while (clientConnected) {
                // read request from client
                Object requestObject = in.readObject();
                if (requestObject instanceof Request) {
                    Request request = (Request) requestObject;
                    Object responseData = null;
                    String errorMessage = null;
                    ResponseType responseType;
                    Response response;
                    // process request and generate response
                    switch (request.getRequestType()) {

                        case CLOSE_CONNECTION:
                            clientConnected = false;
                            break;
                        case UPLOAD_PROPERTIES_FILE:
                            Properties properties = (Properties) request.getRequestData();
                            configureCoffeeShopFromProperties(properties);
                            System.out.println("in server --> Uploading properties file " );
                            // need to change api method to return boolean and process accordingly
                            response = new Response(ResponseType.SUCCESS, "Properties file uploaded");
                            out.writeObject(response);

                            break;
                        case GET_ALL_COFFEE_SHOP_NAMES:

                            System.out.println("in server --> Showing available coffee shops...");
                            String[] coffeeShopNames = getAllCoffeeShopNames();

                            if (coffeeShopNames != null && coffeeShopNames.length > 0) {
                                responseType = ResponseType.SUCCESS;
                                responseData = coffeeShopNames;
                                response = new Response(responseType, responseData);
                            } else {
                                responseType = ResponseType.FAILURE;
                                errorMessage = "No coffee shops available";
                                response = new Response(responseType, errorMessage);
                            }
                            out.writeObject(response);
                            break;

                        case GET_COFFEE_SHOP:

                            String coffeeShopName = (String) request.getRequestData();
                            System.out.println("in server --> Printing coffee shop: " + coffeeShopName);

                            responseData = getCoffeeShop(coffeeShopName);
                            if (responseData != null) {
                                response = new Response(ResponseType.SUCCESS, responseData);
                            } else {
                                response = new Response(ResponseType.FAILURE, "Coffee shop not found");
                            }
                            out.writeObject(response);


                            break;
                        case DELETE_COFFEE_SHOP:

                            coffeeShopName = (String) request.getRequestData();
                            System.out.println("in server --> Deleting coffee shop: " + coffeeShopName);

                            deleteCoffeeShop(coffeeShopName);
                            // need to change api method to return boolean and process accordingly
                            response = new Response(ResponseType.SUCCESS, "Coffee shop deleted");
                            out.writeObject(response);
                            break;

                        case UPDATE_BASE_PRICE:
                            System.out.println("in server --> Updating base price...");
                            Object[] requestData = (Object[]) request.getRequestData();
                            coffeeShopName = (String) requestData[0];
                            double newPrice = (double) requestData[1];
                            updateBasePrice(coffeeShopName, newPrice);
                            // need to change api method to return boolean
                            response = new Response(ResponseType.SUCCESS, "Base price updated");
                            out.writeObject(response);
                            break;
                        case ADD_OPTION_TO_OPTION_SET:
                            System.out.println("in server --> Adding option to option set...");
                            requestData = (Object[]) request.getRequestData();
                            coffeeShopName = (String) requestData[0];
                            String optionSetName = (String) requestData[1];
                            String newOptionName = (String) requestData[2];
                            double newOptionPrice = (double) requestData[3];
                            addOptionToOptionSet(coffeeShopName, optionSetName, newOptionName, newOptionPrice);
                            // need to change api method to return boolean
                            response = new Response(ResponseType.SUCCESS, "Option added to option set");
                            out.writeObject(response);

                            break;

                        case GET_OPTION_SET_NAMES:
                            System.out.println("in server --> Getting option set names...");
                            coffeeShopName = (String) request.getRequestData();
                            String[] optionSetNames = getOptionSetNames(coffeeShopName);
                            if (optionSetNames != null && optionSetNames.length > 0) {
                                responseType = ResponseType.SUCCESS;
                                responseData = optionSetNames;
                                response = new Response(responseType, responseData);
                            } else {
                                responseType = ResponseType.FAILURE;
                                errorMessage = "No option sets available";
                                response = new Response(responseType, errorMessage);
                            }
                            out.writeObject(response);
                            break;
                        case DISCONNECT:
                            clientConnected = false;
                            response = new Response(ResponseType.SUCCESS, null);
                            // remove line below to keep server running after socket connection is closed
                            running = false;
                            System.out.println(" Connection Closed, Server  Stopped");
                            break;
                        default:
                            errorMessage = "Invalid request type";
                            break;
                    }

                    out.flush();
                }
            }
        }  catch (SocketException e) {

            System.err.println("Connection reset: " + e.getMessage());
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public static void main(String[] args) {
        new CoffeeShopServer(5001);
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
