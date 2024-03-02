package scaletests;

import wrapper.CoffeeShopConfigAPI;

public class SimulatedUser implements Runnable {

    private String name;
    private CoffeeShopConfigAPI api;

    public  SimulatedUser(String name, CoffeeShopConfigAPI api) {
        this.name = name;
        this.api = api;


    }

    @Override
    public void run() {
        System.out.println("Simulated user " + name + " started");

        api.updateOptionSetName("EspWow", "Sugar", "Sweetener");
        api.updateOptionPrice("EspWow", "Sweetener", "None", 0.5f);
        api.updateOptionPrice("EspWow", "Size", "Small", 0.8f);
        api.updateBasePrice("EspWow", 2.5f);
        api.printAllCoffeeShops();
        api.deleteOptionSet("EspWow", "Size");
        api.deleteOption("EspWow", "Size", "Small");
        api.deleteCoffeeShop("EspWow");

    }
}
