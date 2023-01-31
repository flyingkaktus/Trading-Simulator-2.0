package com.example;

public class Entry {

    int time;
    float close;
    float SMMA;
    float EMA;
    float RSI;
    float OBV;

    void show() {

        String[] names = { "time", "close", "SMMA", "EMA", "RSI", "OBV" };
        float[] values = { time, close, SMMA, EMA, RSI, OBV };

        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + values[i]);
        }
    }

}
