package client;

import wrapper.CoffeeShopConfigAPI;

import java.util.Properties;

import static io.CoffeeShopConfigBuilder.parsePropertiesFile;

public class TestClient implements CoffeeShopClient {
    CoffeeShopConfigAPI api;
    public TestClient(){
        api = new CoffeeShopConfigAPI();
    }
    @Override
    public void uploadPropertiesFile(String filePath) {
        System.out.println("Uploading properties file: " + filePath);
        // Implement logic to upload a properties file
        Properties props= parsePropertiesFile(filePath);
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
    public void configureCoffeeShop(String coffeeShopName) {
        System.out.println("Configuring coffee shop: " + coffeeShopName);

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
