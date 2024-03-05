package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

class OptionSet implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private ArrayList<Option> choices;
    
    // Constructor for OptionSet
    protected OptionSet(String name) {
        this.name = name;
        this.choices = new ArrayList<Option>();
    }

    // inner class representing an Option
    class Option implements Serializable{
        @Serial
        private static final long serialVersionUID = 1L;
        private String name;
        private double price;

        // constructors and protected methods for Option
        // constructor
        protected Option(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // getters and setters for Option
        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected double getPrice() {
            return price;
        }

        protected void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Option: " + this.getName()+ ", Price: " + this.price;
        }

        public void print() {
            System.out.println(this);
        }
    }

    // create and add an option to the option set
    protected void createOption(String name, double price) {
        Option option = new Option(name, price);
        choices.add(option);
    }


    // retrieve an option from the option set by name
    protected Option findOptionByName(String name) {
        for (Option option : choices) {
            if (option.getName().equals(name)) {
                return option;
            }
        }
        return null;
    }


    //  delete an option from the option set
    protected void deleteOptionByName(String name) {
        Option option = findOptionByName(name);
        if (option != null) {
            choices.remove(option);
        }
    }


    //  update an option in the option set
    protected void updateOptionByName(String oldName, String name, double price) {
        Option option = findOptionByName(oldName);
        if (option != null) {
            option.setName(name);
            option.setPrice(price);
        }
    }

    // getters and setters for OptionSet
    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

   
    //  retrieve the choices of the option set
    protected ArrayList<Option> getChoices() {
        return choices;
    }

    protected boolean hasOption(String name) {
        for (Option option : choices) {
            if (option.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // toString method and print method 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nOptionSet: ").append(name).append("\nOptions: \n");
        for (Option option : choices) {
            sb.append("   ").append(option.toString()).append("\n");
        }
        
        return sb.toString();
    }


    public void print() {
        System.out.println(this);
    }



}
