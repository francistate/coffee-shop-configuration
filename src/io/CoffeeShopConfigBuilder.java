package io;
import model.CoffeeConfig;
import exception.CoffeeShopExceptionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CoffeeShopConfigBuilder {

    public static CoffeeConfig buildCoffeeConfig(String fileName) {
        String filename = "./src/io/" + fileName;
        String defaultFilename = "./src/io/defaultconfig.txt";
        CoffeeConfig coffeeConfig = new CoffeeConfig();
        String optionName;
        double optionPrice;

        File file = new File(filename);
        System.out.println(filename + " " + file.exists());
        System.out.println(filename + " " + file.canRead());

        if (!file.exists() || !file.canRead()) {
            System.out.println("File is not available or not readable, reading default file: " + defaultFilename);
            filename = defaultFilename;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentOptionSetName = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                try {
                    if (line.startsWith("[CoffeeShop]")) {
                        coffeeConfig = new CoffeeConfig();
                    } else if (line.startsWith("Name:")) {
                        coffeeConfig.setName(line.substring(line.indexOf(":") + 1).trim());
                    } else if (line.startsWith("BasePrice:")) {
                        coffeeConfig.setBasePrice(Double.parseDouble(line.substring(line.indexOf(":") + 1).trim()));
                    } else if (line.startsWith("[OptionSet:")) {
                        // start of OptionSet section
                        currentOptionSetName = line.substring(line.indexOf(":") + 1, line.indexOf("]"));
                        coffeeConfig.createOptionSet(currentOptionSetName);
                    } else if (!line.isEmpty() && currentOptionSetName != null) {
                        // Option section
                        StringTokenizer tokenizer = new StringTokenizer(line, ",");
                        optionName = tokenizer.nextToken().trim();
                        try {
                            optionPrice = Double.parseDouble(tokenizer.nextToken().trim());
                        } catch (NumberFormatException ex) {
                            // Handle the exception using factory
                            optionPrice = 1.9;
                            ex.getMessage();
                        } finally {
                            ;
                        }

                        coffeeConfig.createOption(currentOptionSetName, optionName, optionPrice);
                    }
                } catch (NumberFormatException ex) {
                    // Handle the exception using factory
                    CoffeeShopExceptionFactory.createException("Number format exception").handleException();
                }
                catch (NullPointerException ex){
                    // Handle the exception using factory
                    CoffeeShopExceptionFactory.createException("Error processing line: " + line);
                }
            }

        } catch (IOException e) {
            // handle IO exception using the factory
            CoffeeShopExceptionFactory.createException("Error reading file...").handleException();
        }
        finally {
            ;
        }

        return coffeeConfig;
    }



}

