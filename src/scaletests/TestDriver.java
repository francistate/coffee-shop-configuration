package scaletests;
import io.CoffeeShopConfigBuilder;
import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestDriver {

    public static void main(String[] args) {

        CoffeeShopConfigAPI api = new CoffeeShopConfigAPI();
        CoffeeShopConfigBuilder builder = new CoffeeShopConfigBuilder();


        // configure the coffee shops
//        api.configureCoffeeShop("9.txt");
//        api.configureCoffeeShop("1.txt");
        Properties properties = builder.parsePropertiesFile("config.properties");
        api.configureCoffeeShopFromProperties(properties);
        api.printAllCoffeeShops();
//        t.toString();


//        // create multiple SimulatedUser objects
//        int numUsers = 4; // umber of simulated users
//        SimulatedUsersManager usersManager = new SimulatedUsersManager(numUsers, api);
//        usersManager.startUsers();
//
//        // create a thread pool with n threads
//        int numThreads = 4;
//        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
//        // create and submit tasks to the thread pool
//        for (int i = 0; i < numThreads; i++) {
//            int userNumber = i + numThreads;
//            Runnable user = new SimulatedUser("User-" + userNumber, api);
//            executor.submit(user);
//        }
//
//        // shutdown the thread pool after all tasks are completed
//        executor.shutdown();

    }
}
