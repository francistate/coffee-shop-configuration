package server;

import java.util.Properties;

public interface CoffeeShopInterface {


    void showAvailableCoffeeShops();

    void configureCoffeeShopFromProperties(Properties props);

    void printCoffeeShop(String coffeeShopName);

    void deleteCoffeeShop(String coffeeShopName);

    void configureCoffeeShop(String coffeeShopName);

    void updateBasePrice(String coffeeShopName, double newPrice);

    void addOptionToOptionSet(String coffeeShopName, String optionSetName, String newOptionName, double newPrice);
}
