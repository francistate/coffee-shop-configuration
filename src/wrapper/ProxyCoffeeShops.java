package wrapper;

import java.util.*;

import exception.CoffeeShopException;
import exception.CoffeeShopExceptionFactory;

import io.CoffeeShopConfigBuilder;
import model.*;

public abstract class ProxyCoffeeShops {


    private static LinkedHashMap<String, CoffeeConfig> configs;

    // Constructor to initialize the LinkedHashMap
    public ProxyCoffeeShops() {
        configs = new LinkedHashMap<>();
    }


    public synchronized void createCoffeeShop(String coffeeShopName, CoffeeConfig coffeeConfig) {

        System.out.println("configs size before add ----->" + configs.size());
        try {
            if (configs.containsKey(coffeeShopName)) {
                System.out.println("Coffee shop already exists \n\"Replacing Name with a variant\"");

                int randomNumber = new Random().nextInt(1000);
                coffeeShopName = coffeeConfig.getName() + randomNumber;
                coffeeConfig.setName(coffeeShopName);


            }
            if (coffeeConfig.getName() == null || coffeeConfig.getName().isEmpty()) {
                throw CoffeeShopExceptionFactory.createException("Coffee shop name is missing");
            }

            // Check for missing base price
            if (coffeeConfig.getBasePrice() == 0.0) {
                throw CoffeeShopExceptionFactory.createException("Base price is missing");
            }

            // Check for missing coffee shop name
            if (coffeeShopName == null || coffeeShopName.isEmpty()) {
                throw CoffeeShopExceptionFactory.createException("Coffee shop name is missing");
            }

            // configs.put(coffeeShopName, coffeeConfig);


        } catch (CoffeeShopException ex) {
            ex.handleException(coffeeConfig);
        } finally {
            configs.put(coffeeShopName, coffeeConfig);
            System.out.println("configs after add------>" + configs.size());
        }

    }


    public synchronized void configureCoffeeShop(String filename) {
        try {
            CoffeeConfig coffeeConfig = CoffeeShopConfigBuilder.buildCoffeeConfigFromTxt(filename);
            if (coffeeConfig == null) {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }

            createCoffeeShop(coffeeConfig.getName(), coffeeConfig);
            printCoffeeShop(coffeeConfig.getName());
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
//            System.out.println("-//-");
            ;
        }


    }


    public synchronized void configureCoffeeShopFromProperties(Properties properties) {
        try {
            CoffeeConfig coffeeConfig = buildCoffeeConfigFromProperties(properties);
            if (coffeeConfig == null) {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
            createCoffeeShop(coffeeConfig.getName(), coffeeConfig);
//            printCoffeeShop(coffeeConfig.getName());
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
        }
    }

    public synchronized void printCoffeeShop(String coffeeShopName) {

        try {
            System.out.println("Printing coffee shop " + coffeeShopName);
            CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
            if (coffeeConfig == null) {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
            coffeeConfig.print();
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
//            System.out.println("-//-");
            ;
        }

    }

    public synchronized void deleteOption(String coffeeShopName, String optionSetName, String option) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
                if (!coffeeConfig.hasOptionSet(optionSetName)) {
                    throw CoffeeShopExceptionFactory.createException("OptionSet not found");
                }
                coffeeConfig.deleteOption(optionSetName, option);
            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
        }

    }

    public synchronized void updateOptionSetName(String coffeeShopName, String optionSetName, String newName) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
                coffeeConfig.updateOptionSetValues(optionSetName, newName);
                if (!coffeeConfig.hasOptionSet(optionSetName)) {
                    throw CoffeeShopExceptionFactory.createException("OptionSet not found");
                }

            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
            ;
        }
    }


    public synchronized void updateBasePrice(String coffeeShopName, double newPrice) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                configs.get(coffeeShopName).setBasePrice(newPrice);
            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
//            System.out.println("-//-");
            ;
        }


    }


    public synchronized void updateOptionPrice(String coffeeShopName, String optionSetName, String optionName, double newPrice) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
                if (!coffeeConfig.hasOptionSet(optionSetName)) {
                    throw CoffeeShopExceptionFactory.createException("OptionSet not found");
                }
                coffeeConfig.updateOptionValues(optionSetName, optionName, optionName, newPrice);
            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
//            System.out.println("-//-");
            ;
        }


    }

    public synchronized void printAllCoffeeShops() {
        try {
            if (configs.isEmpty()) {
                throw CoffeeShopExceptionFactory.createException("No coffee shops found");
            }
            System.out.println("Printing all coffee shops...");

            Iterator<Map.Entry<String, CoffeeConfig>> iterator = configs.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, CoffeeConfig> entry = iterator.next();
                String coffeeShopName = entry.getKey();
                CoffeeConfig coffeeConfig = entry.getValue();

                System.out.println("Coffee Shop Name: " + coffeeShopName);
                coffeeConfig.print();
            }

        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
//            System.out.println("-//-");
            ;
        }
    }


    public synchronized void deleteCoffeeShop(String coffeeShopName) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                configs.remove(coffeeShopName);
            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {
//            System.out.println("-//-");
            ;
        }
    }

    public synchronized void deleteOptionSet(String coffeeShopName, String optionSetName) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
                if (!coffeeConfig.hasOptionSet(optionSetName)) {
                    throw CoffeeShopExceptionFactory.createException("OptionSet not found");
                }
                coffeeConfig.deleteOptionSet(optionSetName);
            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {

                ;
        }
    }

    public synchronized CoffeeConfig buildCoffeeConfigFromProperties(Properties properties) {
        CoffeeConfig coffeeConfig = new CoffeeConfig();

        // Extract coffee shop name and base price
        String name = properties.getProperty("Name");
        double basePrice = Double.parseDouble(properties.getProperty("BasePrice"));
        coffeeConfig.setName(name);
        coffeeConfig.setBasePrice(basePrice);

        // Extract and create option sets and options in the desired sequence
        int optionSetIndex = 1;
        while (properties.containsKey("OptionSet" + optionSetIndex + ".Name")) {
            String optionSetName = properties.getProperty("OptionSet" + optionSetIndex + ".Name");
            coffeeConfig.createOptionSet(optionSetName);

            int optionIndex = 1;
            while (properties.containsKey("OptionSet" + optionSetIndex + ".Option" + optionIndex + ".Name")) {
                String optionName = properties.getProperty("OptionSet" + optionSetIndex + ".Option" + optionIndex + ".Name");
                double price = Double.parseDouble(properties.getProperty("OptionSet" + optionSetIndex + ".Option" + optionIndex + ".Price"));
                coffeeConfig.createOption(optionSetName, optionName, price);
                optionIndex++;
            }

            optionSetIndex++;
        }

        return coffeeConfig;
    }

    public synchronized void addOptionToOptionSet(String coffeeShopName, String optionSetName, String newOptionName, double price) {
        try {
            if (configs.containsKey(coffeeShopName)) {
                CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
                if (!coffeeConfig.hasOptionSet(optionSetName)) {
                    throw CoffeeShopExceptionFactory.createException("OptionSet not found");
                }
                coffeeConfig.createOption(optionSetName, newOptionName, price);
            } else {
                throw CoffeeShopExceptionFactory.createException("Coffee shop not found");
            }
        } catch (CoffeeShopException ex) {
            ex.handleException();
        } finally {

        }
    }
}
