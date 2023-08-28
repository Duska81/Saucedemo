package Data;

import java.util.ArrayList;
import java.util.List;

public class SaucedemoProduct {

    public static List<String> returnListProducts() {


        List<String> listBrands = new ArrayList<>();
        listBrands.add("Sauce Labs Fleece Jacket");
        listBrands.add("Sauce Labs Onesie");


        return listBrands;
    }
    public static List<Double> returnListPrices() {
        List<Double> listPrices = new ArrayList<>();
        listPrices.add(49.99);
        listPrices.add(7.99);

        return listPrices;
    }
    public static double calculateTotalPrice() {
        List<Double> prices = returnListPrices();
        double totalPrice = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            Double price = prices.get(i);
            totalPrice += price;
        }

        return totalPrice;
    }
}


