package test;

import wrapper.CreateCoffeeShop;

public class ConfigureCoffeeShopTest extends CoffeeShopTest {


    @Override
    public void executeTest(CreateCoffeeShop createCoffeeShopAPI) {
        String filename = "1.txt";
        System.out.println("Executing Configure Coffee Shop Test...\n");
        createCoffeeShopAPI.configureCoffeeShop("1.txt");
        createCoffeeShopAPI.configureCoffeeShop("2.txt");
        createCoffeeShopAPI.configureCoffeeShop("0.txt");


        createCoffeeShopAPI.printAllCoffeeShops();
        
    }

 

}
