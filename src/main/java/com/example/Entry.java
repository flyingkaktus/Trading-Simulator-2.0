package com.example;

public class Entry {

    long time;
    float close;
    // float SMMA;
    float EMA0;
    float EMA1;
    float EMA2;
    float EMA3;
    // float RSI;

    void show() {

        String[] names = { "time", "close", "EMA" };
        float[] values = { time, close, EMA0 };

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + values[i]);
        }
    }

}
