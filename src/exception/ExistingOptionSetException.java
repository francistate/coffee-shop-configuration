package exception;

public class ExistingOptionSetException extends CoffeeShopException{

    public ExistingOptionSetException(String message){
        super(message);
    }

    public void handleException(){
        System.out.println("ExistingOptionSetException: " + getMessage());
        System.out.println("Please enter a valid option set name.");

    }

}
