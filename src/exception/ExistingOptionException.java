package exception;

public class ExistingOptionException extends CoffeeShopException{

    public ExistingOptionException(String message){
        super(message);
    }

    public void handleException(){
        System.out.println("ExistingOptionException: " + getMessage());
        System.out.println("Please enter a different option name.");

    }

}
