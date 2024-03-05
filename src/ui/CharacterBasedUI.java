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

        while (true) {
            Scanner scanner = new Scanner(System.in);
            // Display menu options
            System.out.println("1. Upload a Properties file");
            System.out.println("2. Show available Coffee Configurations");
            System.out.println("3. Print a Coffee Shop");
            System.out.println("4. Delete a Coffee Shop");
//        System.out.println("5. Configure a Coffee Shop");
            System.out.println("6. Update the Base Price");
            System.out.println("7. Add an Option to an existing Option Set");
            System.out.println("8. Exit");

            int userChoice = 0;
            boolean isValidInput = false;

            // Prompt user for input and handle exceptions
            while (!isValidInput) {
                System.out.print("Enter your choice: ");
                try {
                    userChoice = scanner.nextInt();
                    if (userChoice < 1 || userChoice > 8) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                    } else {
                        isValidInput = true;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear input buffer
                }
            }

            String coffeeShopName;
            double newPrice;
            String optionSetName;
            String newOptionName;
            // Process user choice
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
                case 6:
//                    testClient.showAvailableCoffeeShops();
                    System.out.print("\nEnter the name of the Coffee Shop: ");
//                    coffeeShopName = scanner.next();
                    coffeeShopName = "CoffeeShop Express";
                    System.out.print("\nEnter the new base price: ");
//                    newPrice = scanner.nextDouble();
                    newPrice = 6.3;
                    testClient.updateBasePrice(coffeeShopName, newPrice);
                    testClient.printCoffeeShop(coffeeShopName);
                    System.out.println("======================================================");
                    break;
                case 7:
                    testClient.showAvailableCoffeeShops();
                    System.out.print("\nEnter the name of the Coffee Shop: ");
//                    coffeeShopName = scanner.next();
                    coffeeShopName = "CoffeeShop Express";
                    System.out.print("\nEnter the name of the Option Set: ");
                    optionSetName = "Milk";
                    System.out.print("\nEnter the name of the new Option: ");
                    newOptionName = "Soy";
                    System.out.print("\nEnter the new base price: ");
                    newPrice = 9.3;
                    testClient.addOptionToOptionSet(coffeeShopName, optionSetName, newOptionName, newPrice);
                    System.out.println("======================================================");
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
            }
//            scanner.close();
        }



    }

    public static void main(String[] args) {
        CharacterBasedUI characterBasedUI = new CharacterBasedUI();
        characterBasedUI.displayMainMenu();
    }
}
