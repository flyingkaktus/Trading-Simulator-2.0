package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaxDiff {

    public static void main(String[] args) {
        List<Double> prices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("candlestick_data/candlestick_data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                prices.add(Double.parseDouble(values[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double maxPositiveDeviation = 0;
        double maxNegativeDeviation = 0;
        for (int i = 1; i < prices.size(); i++) {
            double deviation = (prices.get(i) - prices.get(i - 1)) / prices.get(i - 1) * 100;
            if (deviation > maxPositiveDeviation) {
                maxPositiveDeviation = deviation;
            } else if (deviation < maxNegativeDeviation) {
                maxNegativeDeviation = deviation;
            }
        }

        System.out.println("Max positive deviation: " + maxPositiveDeviation + "%");
        System.out.println("Max negative deviation: " + maxNegativeDeviation + "%");
    }
}
