package com.example;

public class Entry {

    long time;
    float close;
    // float SMMA;
    float EMA;
    // float RSI;

    void show() {

        String[] names = { "time", "close", "EMA" };
        float[] values = { time, close, EMA };

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + values[i]);
        }
    }

}
