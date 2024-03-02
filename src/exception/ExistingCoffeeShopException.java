package exception;

import java.util.Random;

import model.CoffeeConfig;

public class ExistingCoffeeShopException extends CoffeeShopException{
    public ExistingCoffeeShopException(String message){
        super(message);
    }

    public void handleException(CoffeeConfig coffeeConfig){
        
        System.out.println("ExistingCoffeeShopNameException: " + getMessage());
        System.out.println("Replacing Name with a variant");
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String variantName = coffeeConfig.getName() + randomNumber;
        System.out.println("New name ---> " + variantName);
        coffeeConfig.setName(variantName);
    }

}
