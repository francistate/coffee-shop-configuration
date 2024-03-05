package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileParser {

    public static void main(String[] args) {
        Properties properties = new Properties();
        String fileName = "./src/io/config.properties";

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);

            // accessing CoffeeShop details
            String coffeeShopName = properties.getProperty("CoffeeShop");
            double basePrice = Double.parseDouble(properties.getProperty("BasePrice"));
            System.out.println("Coffee Shop Name: " + coffeeShopName);
            System.out.println("Base Price: " + basePrice);

            // accessing OptionSet details
            for (int i = 1; ; i++) {
                String optionSetNameKey = "CoffeeShop.Option" + i;
                String optionSetName = properties.getProperty(optionSetNameKey);
                if (optionSetName == null) {
                    break; // No more OptionSets found
                }
                System.out.println("\nOptionSet Name: " + optionSetName);
                for (int j = 1; ; j++) {
                    String optionKey = optionSetNameKey + ".OptionValue" + j;
                    String optionValue = properties.getProperty(optionKey);
                    if (optionValue == null) {
                        break; // No more option values found for this OptionSet
                    }
                    System.out.println("Option Value: " + optionValue);
                }
            }

            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
