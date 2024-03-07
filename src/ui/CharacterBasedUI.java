package ui;

import client.TestClient;
import wrapper.CoffeeShopConfigAPI;

import java.util.Scanner;

public class CharacterBasedUI {

    TestClient testClient;
    public CharacterBasedUI() {
        // constructor
        testClient = new TestClient();
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            // Display menu options
            System.out.println("1. Upload a Properties file");
            System.out.println("2. Show available Coffee Configurations");
            System.out.println("3. Print a Coffee Shop");
            System.out.println("4. Delete a Coffee Shop");
            System.out.println("5. Configure a Coffee Shop");
            System.out.println("6. Exit");

            int userChoice;

            // Prompt user for input and handle exceptions
            String coffeeShopName;
            double newPrice;
            String optionSetName;
            String newOptionName;
            // Process user choice
            userChoice = getChoice(scanner,1, 6);
            switch (userChoice) {
                case 1:
                    System.out.print("Enter the name+extension of the properties file: ");
//                    String filePath = scanner.next();
                    String filePath = "C:\\Users\\Tatenda\\Desktop\\config.properties";
                    testClient.uploadPropertiesFile(filePath);
                    System.out.println("======================================================");
                    break;
                case 2:
                    testClient.showAvailableCoffeeShops();
                    System.out.println("======================================================");
                    break;
                case 3:
                    System.out.print("Enter the name of the Coffee Shop: ");
//                    coffeeShopName = scanner.next();
                    coffeeShopName = "CoffeeShop Express";
                    testClient.printCoffeeShop(coffeeShopName);
                    System.out.println("======================================================");
                    break;
                case 4:
                    System.out.print("Enter the name of the Coffee Shop: ");
                    coffeeShopName = "CoffeeShop Express";
                    testClient.deleteCoffeeShop(coffeeShopName);
                    System.out.println("======================================================");
                    break;

                case 5:
                    System.out.println("1. Update the Base Price");
                    System.out.println("2. Add an Option to an existing Option Set");
                    userChoice = getChoice(scanner,1, 2);
                    switch (userChoice) {
                        case 1:
                            System.out.print("Enter the name of the Coffee Shop: ");
                            coffeeShopName = "CoffeeShop Express";
                            System.out.print("Enter the new base price: ");
//                            newPrice = scanner.nextDouble();
                            newPrice = 3.5;
                            testClient.updateBasePrice(coffeeShopName, newPrice);
                            System.out.println("\n======================================================");
                            break;
                        case 2:
                            System.out.print("Enter the name of the Coffee Shop: ");
                            coffeeShopName = "CoffeeShop Express";
                            System.out.print("Enter the name of the Option Set: ");
                            optionSetName = "Size";
                            System.out.print("Enter the name of the new Option: ");
                            newOptionName = "Large";
                            System.out.print("Enter the price of the new Option: ");
//                            newPrice = scanner.nextDouble();
                            newPrice = 2.5;
                            testClient.addOptionToOptionSet(coffeeShopName, optionSetName, newOptionName, newPrice);
                            System.out.println("\n======================================================");
                            break;


                    }
                    break;
                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0);

//            scanner.close();
            }
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
//                scanner.nextLine(); // Clear input buffer
            }
            finally {
                ;
            }
        }
        return userChoice;
    }


    public static void main(String[] args) {
        CharacterBasedUI characterBasedUI = new CharacterBasedUI();
        characterBasedUI.displayMainMenu();
    }
}


