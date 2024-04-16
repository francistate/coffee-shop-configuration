package client;

import io.CoffeeShopConfigBuilder;
import protocol.Request;
import protocol.RequestType;
import protocol.Response;
import protocol.ResponseType;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

public class RealClient implements ClientInterface {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public RealClient(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println( e.getMessage());
        }
        finally {
            ;
        }
    }

    @Override
    public void uploadPropertiesFile(String filePath) {
        CoffeeShopConfigBuilder builder = new CoffeeShopConfigBuilder();
        try {
            Properties props = builder.parsePropertiesFile(filePath);
            Request request = new Request(RequestType.UPLOAD_PROPERTIES_FILE, props);

            out.writeObject(request);

            // wait for response from server
            Response response = (Response) in.readObject();
            // PRIN
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println( e.getMessage());
        }
        finally {
            ;
        }
    }

    @Override
    public void showAvailableCoffeeShops() {
        try {
            Request request = new Request(RequestType.GET_ALL_COFFEE_SHOP_NAMES, null);
            out.writeObject(request);

            // Wait for response from server
            Response response = (Response) in.readObject();
            // PRIN
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println( e.getMessage());
        }
        finally {
            ;
        }
    }

    @Override
    public void printCoffeeShop(String coffeeShopName) {
        try {
            Request request = new Request(RequestType.GET_COFFEE_SHOP, coffeeShopName);
            out.writeObject(request);

            // Wait for response from server
            Response response = (Response) in.readObject();
            // Process response if needed
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println( e.getMessage());
        }
        finally {
            ;
        }
    }

    @Override
    public void deleteCoffeeShop(String coffeeShopName) {
        try {
            Request request = new Request(RequestType.DELETE_COFFEE_SHOP, coffeeShopName);
            out.writeObject(request);
            // wait for response from server
            Response response = (Response) in.readObject();
            // PRIN
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println( e.getMessage());
        }
        finally {
            ;
        }
    }

    @Override
    public void updateBasePrice(String coffeeShopName, double newPrice) {
        try {
            Object[] requestData = {coffeeShopName, newPrice};
            Request request = new Request(RequestType.UPDATE_BASE_PRICE, requestData);
            out.writeObject(request);

            // wait for response from server
            Response response = (Response) in.readObject();
            // PRIN
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOptionToOptionSet(String coffeeShopName, String optionSetName, String newOptionName, double newPrice) {
        try {
            Object[] requestData = {coffeeShopName, optionSetName, newOptionName, newPrice};
            Request request = new Request(RequestType.ADD_OPTION_TO_OPTION_SET, requestData);
            out.writeObject(request);
            // Wait for response from server
            Response response = (Response) in.readObject();
            // PRIN
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getAllCoffeeShopNames() {
        try {
            Request request = new Request(RequestType.GET_ALL_COFFEE_SHOP_NAMES, null);
            out.writeObject(request);
            // wait for response from server
            Response response = (Response)in.readObject();
            if (response.getResponseType() == ResponseType.SUCCESS) {
                return (String[]) response.getResponseData();
            } else {
                System.out.println("Error: " + response.getErrorMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            ;
        }
        return null;
    }

    @Override
    public String getCoffeeShop(String coffeeShopName) {

        try {
            Request request = new Request(RequestType.GET_COFFEE_SHOP, coffeeShopName);
            out.writeObject(request);

            // wait for response from server
            Response response = (Response) in.readObject();
            if (response.getResponseType() == ResponseType.SUCCESS) {
                return (String) response.getResponseData();
            } else {
                System.out.println("Error: " + response.getErrorMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] getOptionSetNames(String coffeeShopName) {
        try {
            Request request = new Request(RequestType.GET_OPTION_SET_NAMES, coffeeShopName);
            out.writeObject(request);

            Response response = (Response) in.readObject();
            if (response.getResponseType() == ResponseType.SUCCESS) {
                return (String[]) response.getResponseData();
            } else {
                System.out.println("Error: " + response.getErrorMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnectClient(){
        try {
            Request request = new Request(RequestType.DISCONNECT, null);
            out.writeObject(request);

            Response response = (Response) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }finally{
            System.out.println("Clossed Connection...");
        }

    }


    public void close() {
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
