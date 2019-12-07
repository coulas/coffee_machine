package com.fedou.katas.coffeemachine;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private HashMap<String, Integer> records = new HashMap<>();
    private HashMap<String, Integer> prices = new HashMap<>();


    public void record(DrinkMakerCommand machineCommand) {
         records.merge(machineCommand.displayName, 1, (count, increment) -> count + increment);
         prices.computeIfAbsent(machineCommand.displayName, (key) -> machineCommand.priceInCents);
    }

    public void printReport() {
        // print sums by type and total
        int total = 0;
        for (Map.Entry<String, Integer> entry : records.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Integer price = prices.get(key);
            System.out.println(key + " x" + value + ": " + centsToEuroString(price*value) + "€");
            total += price;
        }
        System.out.println("Total: " + centsToEuroString(total) + "€");
    }

    static String centsToEuroString(int amount) {
        return String.format("%.2f", amount / 100f);
    }
}
