package exception;

public class CoffeeShopNotFoundException extends CoffeeShopException{
    
        public CoffeeShopNotFoundException(String message){
            super(message);
        }
    
        public void handleException(){
            System.out.println("CoffeeShopNotFoundException: " + getMessage());

        }

}
