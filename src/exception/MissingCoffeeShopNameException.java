package exception;

import model.CoffeeConfig;

import java.util.Random;

public class MissingCoffeeShopNameException extends CoffeeShopException{

        public MissingCoffeeShopNameException(String message) {
            super(message);
        }

        @Override
        public void handleException(CoffeeConfig coffeeConfig) {
            System.out.println("MissingCoffeeShopNameException: " + getMessage());
            System.out.println("Attempting to fix missing coffee shop name...");
            Random random = new Random();
            String coffeeShopName = "CoffeeShop" + random.nextInt(1000);
            coffeeConfig.setName(coffeeShopName);
            System.out.println("Name set to: " + coffeeShopName);
        }
}
