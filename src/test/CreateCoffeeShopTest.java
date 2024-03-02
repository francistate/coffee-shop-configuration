package test;

import model.CoffeeConfig;
import wrapper.CreateCoffeeShop;

public class CreateCoffeeShopTest extends CoffeeShopTest{

    @Override
    public void executeTest(CreateCoffeeShop createCoffeeShopAPI) {
        System.out.println("Executing Coffee Shop Creation Test...\n");
        String coffeeShopName = "Espresso Express";

        var coffeeConfig = new CoffeeConfig("Espresso Express", 5.0);
        coffeeConfig.createOptionSet("Size");
        coffeeConfig.createOption("Size", "Small", 0.0);
        coffeeConfig.createOption("Size", "Medium", 1.2);
        coffeeConfig.createOption("Size", "Large", 1.8);

        coffeeConfig.createOptionSet("Sugar");
        coffeeConfig.createOption("Sugar", "None", 0.0);
        coffeeConfig.createOption("Sugar", "1 Sugar", 0.5);
        coffeeConfig.createOption("Sugar", "2 Sugars", 1.0);

        createCoffeeShopAPI.createCoffeeShop(coffeeShopName, coffeeConfig);
        
        System.out.println("Printing the properties of the coffee shop...\n");
        createCoffeeShopAPI.printCoffeeShop(coffeeShopName);
        
        CoffeeConfig coffeeConfig2 = new CoffeeConfig("Esp Wow", 2.0);
        coffeeConfig2.createOptionSet("Size");
        coffeeConfig2.createOption("Size", "Small", 0.0);
        coffeeConfig2.createOption("Size", "Medium", 1.2);
        coffeeConfig2.createOption("Size", "Large", 1.8);

        coffeeConfig2.createOptionSet("Sugar");
        coffeeConfig2.createOption("Sugar", "None", 0.0);
        coffeeConfig2.createOption("Sugar", "1 Sugar", 0.5);
        coffeeConfig2.createOption("Sugar", "2 Sugars", 1.0);

        createCoffeeShopAPI.createCoffeeShop(coffeeShopName , coffeeConfig2);
        
        createCoffeeShopAPI.printCoffeeShop("Esp Wow");
       
    }



}
