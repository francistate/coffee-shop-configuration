package model;
import java.io.*;
import java.util.ArrayList;

public class CoffeeConfig implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private double basePrice;

    // private OptionSet options[]; // Partially-filled array in Version 1.0
    // change array to ArrayList in 1.1
    private ArrayList<OptionSet> options;
    

    // Constructors
    public CoffeeConfig() {
        // Default constructor
        this.options = new ArrayList<>();
    }

    public CoffeeConfig(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.options = new ArrayList<>();
    }



    // GETTERS 
    public double getBasePrice() {
        return basePrice;
    }

    public String getName() {
    	return name;
    }


    // SETTERS

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

//    public synchronized void setCoffeeShopName(String name) {
//        this.name = name;
//    }



    //set the values of an option in the context of a specific option set
    public synchronized void setOptionValues(String optionSetName, String optionName, double price) {
        for (OptionSet optionSet : options) {
            if (optionSet.getName().equals(optionSetName)) {
                OptionSet.Option option = optionSet.findOptionByName(optionName);
                if (option != null) {
                    option.setPrice(price);
                }
            }
        }
    }



    // FIND / READ METHODS

    public synchronized void findOptionSetWithName(String name){
        boolean optionSetFound = false;
        for (OptionSet optionSet : options) {
            if (optionSet.getName().equals(name)) {

                System.out.println("optionSet found:\n " + optionSet);
                System.out.println(optionSet);
                optionSetFound = true;
            }
        }
        if (!optionSetFound){
            System.out.println("OptionSet not found");
        }

    }
    
    private OptionSet findOptionSetByName(String name) {
        for (OptionSet optionSet : options) {
            if (optionSet.getName().equals(name)) {
                return optionSet;
            }
        }
        return null;
    }

    // find an option with a given name within a specific optionSet
    public synchronized void findOptionWithName(String optionSetName, String optionName) {
        for (OptionSet optionSet : options) {
            if (optionSet.getName().equals(optionSetName)) {
                OptionSet.Option option = optionSet.findOptionByName(optionName);
                if (option != null) {
                    System.out.println("Option found:\n " + option);
                    // System.out.println(option.toString());
                }
                else {
                    System.out.println("Option not found");
                }
            }
        }

    }
    

    //UPDATE METHODS
    // update the values of option set
    public synchronized void updateOptionSetValues(String name, String newName) {
        OptionSet optionSet = findOptionSetByName(name);
        if (optionSet != null) {
            optionSet.setName(newName);
        }

    }

    // update the values of an option
    public synchronized void updateOptionValues(String optionSetName, String optionName,
                                        String newName, double newPrice) {
        OptionSet optionSet = findOptionSetByName(optionSetName);
        if (optionSet != null) {
            optionSet.updateOptionByName(optionName, newName, newPrice);
        }
        
    }


    // DELETE METHODS
    // delete an option in an option set
    public synchronized void deleteOption(String optionSetName, String optionName) {
        OptionSet optionSet = findOptionSetByName(optionSetName);
        if (optionSet != null) {
            optionSet.deleteOptionByName(optionName);
        }
    }

    // delete an option set 
    public synchronized void deleteOptionSet(String name) {
        OptionSet optionSet = findOptionSetByName(name);
        if (optionSet != null) {
            options.remove(optionSet);
        }
    }




    //CREATE METHODS
    // create an option set
    public synchronized void createOptionSet(String name) {
        options.add(new OptionSet(name));
    }

    // create an option
    public synchronized void createOption(String optionSetName, String optionName, double price) {
        
        OptionSet optionSet = findOptionSetByName(optionSetName);
        if (optionSet != null) {
            optionSet.createOption(optionName, price);
        }
        else {
            System.out.println("OptionSet not found");
        }
    }

    // OTHER METHODS

    // toString method and print method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shop Name: ").append(name).append("\n");
        sb.append("Base Price: ").append(basePrice).append("\n");
        for (OptionSet optionSet : options) {
            if (optionSet != null) {
                sb.append(optionSet.toString());
            }
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(this.toString());
    }


    // serialization method to save object to a file
//    public void serialize(String fileName) {
//        // String filePath = "../../serialized_objects/";
//        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            outputStream.writeObject(this);
//            System.out.println("object serialized successfully.");
//        } catch (IOException e) {
//            System.out.println("Error occurred during serialization: " + e.getMessage());
//        }
//    }

    // deserialization method - read object from a file
//    public static CoffeeConfig deserialize(String fileName) {
//        // String filePath = "../../serialized_objects/";
//        CoffeeConfig coffeeConfig = null;
//        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
//            coffeeConfig = (CoffeeConfig) inputStream.readObject();
//            System.out.println("object deserialized successfully.");
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error occurred during deserialization: " + e.getMessage());
//        }
//        return coffeeConfig;
//    }



    public synchronized boolean hasOptionSet(String optionSetName) {
        if (options == null) {
            return false;
        }
        for (OptionSet optionSet : options) {
            if (optionSet != null && optionSet.getName().equals(optionSetName)) {
                return true;
            }
        }
        return false;
    }





}