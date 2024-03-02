package exception;

public class NumberFormatException extends CoffeeShopException{

            public NumberFormatException(String message){
                super(message);
            }

            public void handleException(){
                System.out.println("NumberFormatException: " + getMessage());

            }
}
