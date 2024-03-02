package wrapper;

public interface DeleteCoffeeShop {
    void deleteCoffeeShop(String coffeeShopName);
    void deleteOption(String coffeeShopName, String optionSetName, String optionName);
    void deleteOptionSet(String coffeeShopName, String optionSetName);
}
