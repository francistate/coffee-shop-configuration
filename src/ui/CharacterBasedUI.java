package ui;

import client.ClientInterface;
import client.RealClient;

import java.util.Scanner;

public class CharacterBasedUI {

    ClientInterface client;


    public CharacterBasedUI() {
        // constructor
        client = new RealClient("localhost", 5001);
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            // display menu options
            System.out.println("1. Upload a Properties file");
            System.out.println("2. Show available Coffee Configurations");
            System.out.println("3. Print a Coffee Shop");
            System.out.println("4. Delete a Coffee Shop");
            System.out.println("5. Update the Base Price");
            System.out.println("6. Add an Option to an existing Option Set");
            System.out.println("7. Exit");

            int userChoice;

            // prompt user for input and handle exceptions
            String coffeeShopName;
            double newPrice;
            String optionSetName;
            String newOptionName;
            // Process user choice
            userChoice = getChoice(scanner,1, 7);
            scanner.nextLine();
            switch (userChoice) {
                case 1:
                    System.out.print("Enter the whole path of the properties file: ");
                    String filePath = scanner.nextLine();
//                    String filePath = "C:\\Users\\Tatenda\\Desktop\\config.properties";
                    client.uploadPropertiesFile(filePath);
                    System.out.println("======================================================");
                    break;
                case 2:
                    System.out.println("Available Coffee Shops:");
                    printAllCoffeeShops();
                    System.out.println("======================================================");
                    break;
                case 3:
                    printAllCoffeeShops();
                    System.out.print("Enter the name of the Coffee Shop: ");
                    coffeeShopName = scanner.nextLine();
//                    coffeeShopName = "CoffeeShop Express";
                    String coffeeShop = client.getCoffeeShop(coffeeShopName);
                    System.out.println(coffeeShop);
                    System.out.println("======================================================");
                    break;
                case 4:
                    printAllCoffeeShops();
                    System.out.print("\nEnter the name of the Coffee Shop: ");
//                    coffeeShopName = "CoffeeShop Express";
                    coffeeShopName = scanner.nextLine();
                    client.deleteCoffeeShop(coffeeShopName);
                    System.out.println("======================================================");
                    break;

                case 5:

                    System.out.print("Enter the name of the Coffee Shop: ");
//                            coffeeShopName = "CoffeeShop Express";
                    coffeeShopName = scanner.nextLine();

                    System.out.print("Enter the new base price: ");
                    newPrice = scanner.nextDouble();
//                            newPrice = 3.5;
                    client.updateBasePrice(coffeeShopName, newPrice);
                    System.out.println("\n======================================================");
                    break;
                case 6:
                    System.out.print("Enter the name of the Coffee Shop: ");
//                            coffeeShopName = "CoffeeShop Express";
                    coffeeShopName = scanner.nextLine();
                    System.out.print("Enter the name of the Option Set: ");
//                            optionSetName = "Size";
                    optionSetName = scanner.nextLine();
                    System.out.print("Enter the name of the new Option: ");
//                            newOptionName = "Large";
                    newOptionName = scanner.nextLine();
                    System.out.print("Enter the price of the new Option: ");
                    newPrice = scanner.nextDouble();
//                            newPrice = 2.5;
                    client.addOptionToOptionSet(coffeeShopName, optionSetName, newOptionName, newPrice);
                    System.out.println("\n======================================================");
                    break;




                    case 7:
                        System.out.println("Exiting...");
                        System.exit(0);

//            scanner.close();
            }
            scanner.nextLine();
        }
    }

    private int getChoice(Scanner scanner, int lowerBound, int upperBound) {

        int userChoice = 0;
        boolean isValidInput = false;

        // Prompt user for input and handle exceptions
        while (!isValidInput) {
            System.out.print("Enter your choice: ");
            try {
                userChoice = scanner.nextInt();
                if (userChoice < lowerBound || userChoice > upperBound) {
                    System.out.println("Invalid choice. Please enter a number between" + lowerBound +" and " + upperBound + ".");
                } else {
                    isValidInput = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear input buffer
            }
            finally {
                ;
            }
        }
        return userChoice;
    }

    private void printAllCoffeeShops(){
        String[] configurations = client.getAllCoffeeShopNames();
                        if (configurations != null && configurations.length > 0) {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (String config : configurations) {
                sb.append(i).append(": ").append(config).append("\n");
                i++;
            }
            System.out.println(sb.toString());
        }else{
            System.out.println("No Coffee Shops available");
        }
    }

    public void start() {
        displayMainMenu();
    }

//    public static void main(String[] args) {
//        CharacterBasedUI characterBasedUI = new CharacterBasedUI();
//        characterBasedUI.displayMainMenu();
//    }
}


