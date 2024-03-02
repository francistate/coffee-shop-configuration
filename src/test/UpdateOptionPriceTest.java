package test;

import wrapper.UpdateCoffeeShop;

public class UpdateOptionPriceTest extends CoffeeShopTest{



    @Override
    public void executeTest(UpdateCoffeeShop updateCoffeeShopAPI) {
        String coffeeShopName = "Espresso Express";
        System.out.println("Executing Update Option Price Test...\n");
        String optionName = "Small";
        String optionSetName = "Cup Size";
        double newPrice = 0.2;
        System.out.println("Updating the price of the option " + optionName +
                             " in the option set " + optionSetName + " to " + newPrice + "...\n");
        updateCoffeeShopAPI.updateOptionPrice(coffeeShopName, optionSetName, optionName, newPrice);

    }

}
