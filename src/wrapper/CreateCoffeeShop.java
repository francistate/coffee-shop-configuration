package wrapper;

import model.CoffeeConfig;

public interface CreateCoffeeShop {



    void createCoffeeShop(String coffeeShopName, CoffeeConfig coffeeConfig);

    void configureCoffeeShop(String filename);

    void printCoffeeShop(String coffeeShopName);

    void printAllCoffeeShops();


    
}

