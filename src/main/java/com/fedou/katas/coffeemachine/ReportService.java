package com.fedou.katas.coffeemachine;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private HashMap<String, Integer> records = new HashMap<>();
    private HashMap<String, Integer> prices = new HashMap<>();


    public void report(DrinkMakerCommand machineCommand) {
         records.merge(machineCommand.displayName, 1, (count, increment) -> count + increment);
         prices.computeIfAbsent(machineCommand.displayName, (key) -> machineCommand.priceInCents);
    }

    public void printReport() {
        // print sums by type and total
        float total = 0;
        for (Map.Entry<String, Integer> entry : records.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            float amount = (prices.get(key) * value) / 100f;
            System.out.println(key + " x" + value + ": " + String.format("%.2f", amount) + "€");
            total += amount;
        }
        System.out.println("Total: " + String.format("%.2f", total) + "€");
    }
}
