package wrapper;

import java.util.Properties;

public interface RetrieveCoffeeShop {
    String[] getAllCoffeeShopNames();
//    void printCoffeeShop(String coffeeShopName);

    String getCoffeeShop(String coffeeShopName);

    String[] getOptionSetNames(String coffeeShopName);
}
