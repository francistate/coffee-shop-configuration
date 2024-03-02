package scaletests;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;


import java.util.ArrayList;
import java.util.List;

public class SimulatedUsersManager {
    private List<SimulatedUser> users;

    public SimulatedUsersManager(int numUsers, CoffeeShopConfigAPI api) {
        // Create the simulated users
        users = new ArrayList<>();
        for (int i = 0; i < numUsers; i++) {
            users.add(new SimulatedUser("User-" + i, api));
        }
        System.out.println("Simulated users created");

    }

// start users threads
    public void startUsers() {
        for (SimulatedUser user : users) {
            Thread thread = new Thread(user);
            thread.start();
        }
    }

}
