package exception;

import model.CoffeeConfig;

public class OptionSetNotFoundException extends CoffeeShopException{

    public OptionSetNotFoundException(String message){
        super(message);
    }

    public void handleException(CoffeeConfig coffeeConfig){
        System.out.println("OptionSetNotFoundException: " + getMessage());
        System.out.println("Please enter a different option set name.");
        
    }

}
