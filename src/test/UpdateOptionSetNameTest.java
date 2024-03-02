package test;

import wrapper.UpdateCoffeeShop;

public class UpdateOptionSetNameTest extends CoffeeShopTest{




    @Override
    public void executeTest(UpdateCoffeeShop updateCoffeeShopAPI) {
        String coffeeShopName = "Espresso Express";
        System.out.println("Executing Update Option Set Name Test...\n");
        String newName = "Cup Size";
        String optionSetName = "Size1";
        System.out.println("Updating the option set name from " + optionSetName + " to " + newName + "...\n");
        
        updateCoffeeShopAPI.updateOptionSetName(coffeeShopName,optionSetName, newName);
    }

}
