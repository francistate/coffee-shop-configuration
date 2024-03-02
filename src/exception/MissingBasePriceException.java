package exception;

import model.CoffeeConfig;

public class MissingBasePriceException extends CoffeeShopException{
    public MissingBasePriceException(String message){
        super(message);
    }

    @Override
    public void handleException(CoffeeConfig coffeeConfig){
        
        System.out.println("MissingBasePriceException: " + getMessage());
        System.out.println("Attempting to fix missing base price...");
        // Set a default base price of $1.00
        coffeeConfig.setBasePrice(1.00);
        System.out.println("Default base price set: $1.00");
        
    }


    
    

}
