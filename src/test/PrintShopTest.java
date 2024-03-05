package test;

import wrapper.CreateCoffeeShop;

public class PrintShopTest extends CoffeeShopTest{


    @Override
    public void executeTest(CreateCoffeeShop createCoffeeShopAPI) {
//        System.out.println("Executing Print Coffee Shop Test...\n");
//        String coffeeShopName = "Espresso Express";
//        createCoffeeShopAPI.printCoffeeShop(coffeeShopName);
        createCoffeeShopAPI.printAllCoffeeShops();
    }

}
