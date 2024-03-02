package test;

//import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;
import wrapper.CreateCoffeeShop;
import wrapper.UpdateCoffeeShop;

public class TestDriver {

    public static void main(String[] args) {

        // Instantiate CoffeeShopConfigAPI using CreateCoffeeShop reference
        CreateCoffeeShop createCoffeeShopAPI;
        UpdateCoffeeShop updateCoffeeShopAPI;

        createCoffeeShopAPI = new CoffeeShopConfigAPI();
        updateCoffeeShopAPI = new CoffeeShopConfigAPI();

        CoffeeShopTest coffeeShopTest;

        coffeeShopTest = new ConfigureCoffeeShopTest();
        coffeeShopTest.executeTest(createCoffeeShopAPI);
        System.out.println("\n#################################\n");
//        coffeeShopTest = new CreateCoffeeShopTest();
//        coffeeShopTest.executeTest(createCoffeeShopAPI);
//        System.out.println("\n#################################\n");



        coffeeShopTest = new UpdateOptionSetNameTest();
        coffeeShopTest.executeTest(updateCoffeeShopAPI);
        System.out.println("\n#################################\n");

        
        coffeeShopTest = new PrintShopTest();
        coffeeShopTest.executeTest(createCoffeeShopAPI);
        System.out.println("\n#################################\n");

        coffeeShopTest = new UpdateOptionPriceTest();
        coffeeShopTest.executeTest(updateCoffeeShopAPI);
        System.out.println("\n#################################\n");

        
        coffeeShopTest = new PrintShopTest();
        coffeeShopTest.executeTest(createCoffeeShopAPI);
        System.out.println("\n#################################\n");


        coffeeShopTest = new UpdateBasePriceTest();
        coffeeShopTest.executeTest(updateCoffeeShopAPI);
        System.out.println("\n#################################\n");

        
        coffeeShopTest = new PrintShopTest();
        coffeeShopTest.executeTest(createCoffeeShopAPI);
        System.out.println("\n#################################\n");



    }
}