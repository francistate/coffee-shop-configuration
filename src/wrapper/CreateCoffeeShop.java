package wrapper;

import model.CoffeeConfig;

import java.util.Properties;

public interface CreateCoffeeShop {



    void createCoffeeShop(String coffeeShopName, CoffeeConfig coffeeConfig);

    void configureCoffeeShop(String filename);

    void configureCoffeeShopFromProperties(Properties properties);

    void printCoffeeShop(String coffeeShopName);

    void printAllCoffeeShops();


    
}

