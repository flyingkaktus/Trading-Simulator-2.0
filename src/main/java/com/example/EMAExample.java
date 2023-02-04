package com.example;

import java.util.ArrayList;

public class EMAExample {

    public static void main(String[] args) {

        ArrayList<Double> prices = new ArrayList<>();
        prices.add(23339.82);
        prices.add(23318.21);
        prices.add(23310.17);
        prices.add(23321.91);
        prices.add(23305.49);
        prices.add(23309.47);
        prices.add(23305.76);
        prices.add(23307.53);
        prices.add(23301.87);
        prices.add(23297.83);

        int period = 10;
        double alpha = 2.0 / (period + 1);

        double ema = prices.get(0);

        for (int i = 1; i < prices.size(); i++) {
            ema = (prices.get(i) - ema) * alpha + ema;
        }

        System.out.println("Exponentieller gleitender Durchschnitt (EMA): " + ema);
    }
}
