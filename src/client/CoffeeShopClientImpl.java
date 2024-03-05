package client;

import protocol.Request;
import protocol.RequestType;
import protocol.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

public class CoffeeShopClientImpl implements CoffeeShopClient{
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public CoffeeShopClientImpl(String serverAddress, int port) throws IOException {
        // establish connection with the server
        socket = new Socket(serverAddress, port);
        // initialize input and output streams
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void uploadPropertiesFile(String filename) {
        // parse the properties file
        Properties properties = parsePropertiesFile(filename);
//        Request request = new Request(RequestType.UPLOAD_PROPERTIES_FILE, properties);
        // send the request to the server
        try {
            // create a request object with the properties data
            Request request = new Request(RequestType.UPLOAD_PROPERTIES_FILE, properties);

            // send the request object to the server
            outputStream.writeObject(request);
            outputStream.flush();

            System.out.println("Properties file uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAvailableCoffeeShops() {

    }

    @Override
    public void printCoffeeShop(String coffeeShopName) {

    }

    @Override
    public void deleteCoffeeShop(String coffeeShopName) {

    }

    @Override
    public void configureCoffeeShop(String coffeeShopName) {

    }

    @Override
    public void updateBasePrice(String coffeeShopName, double newPrice) {

    }

    @Override
    public void addOptionToOptionSet(String coffeeShopName, String optionSetName, String newOptionName) {

    }



    private Properties parsePropertiesFile(String file_name) {
        String filepath = "./src/io/";
        Properties properties = new Properties();
        String fileName = filepath + file_name;

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);

            // Accessing CoffeeShop details
            String coffeeShopName = properties.getProperty("CoffeeShop");
            double basePrice = Double.parseDouble(properties.getProperty("BasePrice"));
            System.out.println("Coffee Shop Name: " + coffeeShopName);
            System.out.println("Base Price: " + basePrice);

            // Accessing OptionSet details
            for (int i = 1; ; i++) {
                String optionSetNameKey = "CoffeeShop.Option" + i;
                String optionSetName = properties.getProperty(optionSetNameKey);
                if (optionSetName == null) {
                    break; // No more OptionSets found
                }
                System.out.println("\nOptionSet Name: " + optionSetName);
                for (int j = 1; ; j++) {
                    String optionKey = optionSetNameKey + ".OptionValue" + j;
                    String optionValue = properties.getProperty(optionKey);
                    if (optionValue == null) {
                        break; // No more option values found for this OptionSet
                    }
                    System.out.println("Option Value: " + optionValue);
                }
            }

            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void start() {
        // Start the client
        try {
            // continuously accept client connections
            while (true) {
                // read the request object from the client
                Request request = (Request) inputStream.readObject();
                // process the request and generate a response
                Response response = processRequest(request);
                // send the response back to the client
                outputStream.writeObject(response);
                outputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the client socket when done
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Response processRequest(Request request) {
        System.out.println("Processing request: " + request.getRequestType());
        return null;
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;
        try {
            CoffeeShopClientImpl client = new CoffeeShopClientImpl(serverAddress, port);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}
