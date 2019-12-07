package com.fedou.katas.coffeemachine;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private HashMap<String, Integer> records = new HashMap<>();
    private HashMap<String, Integer> prices = new HashMap<>();


    public void record(DrinkMakerCommand machineCommand) {
        if (Boolean.TRUE.equals(machineCommand.succeeded)) {
            records.merge(machineCommand.displayName, 1, (count, increment) -> count + increment);
            prices.computeIfAbsent(machineCommand.displayName, (key) -> machineCommand.priceInCents);
        } // else no need to record failure
    }

    public void printReport() {
        // print sums by type and total
        int total = 0;
        for (Map.Entry<String, Integer> entry : records.entrySet()) {
            String key = entry.getKey();
            Integer drinksServed = entry.getValue();
            Integer drinkPrice = prices.get(key);
            int drinksServedInCents = drinkPrice * drinksServed;
            System.out.println(key + " x" + drinksServed + ": " + centsToEuroString(drinksServedInCents) + "€");
            total += drinksServedInCents;
        }
        System.out.println("Total: " + centsToEuroString(total) + "€");
    }

    static String centsToEuroString(int amount) {
        return String.format("%.2f", amount / 100f);
    }
}
