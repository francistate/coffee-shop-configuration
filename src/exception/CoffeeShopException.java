package exception;

import model.CoffeeConfig;

public abstract class CoffeeShopException extends Exception{
    public CoffeeShopException(String message){
        super(message);
    }

    // public abstract void handleException();

    public void handleException(CoffeeConfig coffeeConfig){
        
    }

    public void printMessage(){
        System.out.println("CoffeeShopException: " + getMessage());
    }

	public void handleException() {

    }

}
