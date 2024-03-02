package test;

import wrapper.UpdateCoffeeShop;

public class UpdateBasePriceTest extends CoffeeShopTest{
	
 

    @Override
    public void executeTest(UpdateCoffeeShop updateCoffeeShopAPI) {
        System.out.println("Executing Update Base Price Test...\n");
        String coffeeShopName = "Espresso Express";
        double newBasePrice = 2.50;
        updateCoffeeShopAPI.updateBasePrice(coffeeShopName,  newBasePrice);




    }

}
