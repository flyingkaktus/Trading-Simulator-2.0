package com.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Workload {

    private float gain_low, gain_high, gain_steps;
    // private int SMMA_low, SMMA_high;
    private int EMA_low, EMA_high;
    // private int RSI_low, RSI_high;

    BlockingQueue<WorkloadUnits> workloadQueue = new LinkedBlockingQueue<WorkloadUnits>();

    void range_gain(float low, float high, float steps) {
        gain_low = low;
        gain_high = high;
        gain_steps = steps;
    }

    // void range_SMMA(int low, int high) {
    // SMMA_low = low;
    // SMMA_high = high;
    // }

    void range_EMA(int low, int high) {
        EMA_low = low;
        EMA_high = high;
    }

    // void range_RSI(int low, int high) {
    // RSI_low = low;
    // RSI_high = high;
    // }

    void generate() {
        for (float g = gain_low; g < gain_high; g += gain_steps) {
            // for (float s = SMMA_low; s < SMMA_high; s++) {
            for (float e = EMA_low; e < EMA_high; e++) {
                // for (float r = RSI_low; r < RSI_high; r++) {
                WorkloadUnits workloadUnite = new WorkloadUnits();
                workloadUnite.gain = g;
                // workloadUnite.SMMA = s;
                workloadUnite.EMA = e;
                // workloadUnite.RSI = r;
                workloadQueue.add(workloadUnite);
                // }
            }
            // }
        }
        System.out.println("Workload: " + workloadQueue.size());
    }

    int getWorkloadQueueSize() {
        return workloadQueue.size();
    }
}
