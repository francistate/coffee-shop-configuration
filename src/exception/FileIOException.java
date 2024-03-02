package exception;

public class FileIOException extends CoffeeShopException{

                public FileIOException(String message){
                    super(message);
                }

                public void handleException(){
                    System.out.println("FileIOException: " + getMessage());
                    String defaultFile = "defaultconfig.txt";

                }
}
