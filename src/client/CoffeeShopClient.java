package client;

public interface CoffeeShopClient {

    void uploadPropertiesFile(String filePath);

    void showAvailableCoffeeShops();

    void printCoffeeShop(String coffeeShopName);

    void deleteCoffeeShop(String coffeeShopName);

    void configureCoffeeShop(String coffeeShopName);

    void updateBasePrice(String coffeeShopName, double newPrice);

    void addOptionToOptionSet(String coffeeShopName, String optionSetName, String newOptionName, double newPrice);

    String getCoffeeShop(String coffeeShopName);
    String[] getOptionSetNames(String coffeeShopName);
}
