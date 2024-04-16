package client;

import wrapper.CoffeeShopConfigAPI;

import java.util.Properties;

import io.CoffeeShopConfigBuilder;

public class TestClient implements ClientInterface {
    CoffeeShopConfigAPI api;
    public TestClient(){
        api = new CoffeeShopConfigAPI();
    }

    // methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void uploadPropertiesFile(String filePath) {
        CoffeeShopConfigBuilder builder = new CoffeeShopConfigBuilder();
        System.out.println("Uploading properties file: " + filePath);

        Properties props = builder.parsePropertiesFile(filePath);
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
