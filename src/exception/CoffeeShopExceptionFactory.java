package exception;

public class CoffeeShopExceptionFactory {
    public static CoffeeShopException createException(String message) {

        return switch (message) {
            case "Coffee shop not found" -> new CoffeeShopNotFoundException(message);
            case "OptionSet not found" -> new OptionSetNotFoundException(message);
            case "Base price is missing" -> new MissingBasePriceException(message);
            case "Coffee shop name is missing" -> new MissingCoffeeShopNameException(message);
            case "Coffee shop already exists" -> new ExistingCoffeeShopException(message);
            case "Number format exception" -> new NumberFormatException(message);

//            case "Coffee shop not found" -> new CoffeeShopNotFoundException(message);

        //     case "OptionSet not found" -> new OptionSetNotFoundException();
        //     case "Missing name" -> new MissingNameException();
        //     case "Existing option" -> new ExistingOptionException();
            default -> new NewException(message);
        };
        
    }


}



