package exception;

public class NewException extends CoffeeShopException{
    
        public NewException(String message){
            super(message);
        }
    
        public void handleException(){
//            System.out.println("This is  error is currently unchecked. Contact your devs to fix it.");
            System.out.println("NewException: " + getMessage());

        }

}
