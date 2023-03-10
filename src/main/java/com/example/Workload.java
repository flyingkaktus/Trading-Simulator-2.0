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
            for (float e0 = EMA_low; e0 < EMA_high; e0++) {
                for (float e1 = EMA_low; e1 < EMA_high; e1++) {
                    for (float e2 = EMA_low; e2 < EMA_high; e2++) {
                        for (float e3 = EMA_low; e3 < EMA_high; e3++) {
                            // for (float r = RSI_low; r < RSI_high; r++) {
                            WorkloadUnits workloadUnite = new WorkloadUnits();
                            workloadUnite.gain = g;
                            // workloadUnite.SMMA = s;
                            workloadUnite.EMA0 = e0;
                            workloadUnite.EMA1 = e1;
                            workloadUnite.EMA2 = e2;
                            workloadUnite.EMA3 = e3;
                            // workloadUnite.RSI = r;
                            workloadQueue.add(workloadUnite);
                            // }
                        }
                    }
                }
            }
            // }
        }
        System.out.println("Workload: " + workloadQueue.size());
    }

    void generateOne() {
        for (float g = 0.6f; g < 0.8f; g += 0.2f) {
            // for (float s = SMMA_low; s < SMMA_high; s++) {
            for (float e0 = 105f; e0 < 106f; e0++) {
                for (float e1 = 96f; e1 < 97f; e1++) {
                    for (float e2 = 98f; e2 < 99f; e2++) {
                        for (float e3 = 97f; e3 < 98f; e3++) {
                            // for (float r = RSI_low; r < RSI_high; r++) {
                            WorkloadUnits workloadUnite = new WorkloadUnits();
                            workloadUnite.gain = g;
                            // workloadUnite.SMMA = s;
                            workloadUnite.EMA0 = e0;
                            workloadUnite.EMA1 = e1;
                            workloadUnite.EMA2 = e2;
                            workloadUnite.EMA3 = e3;
                            // workloadUnite.RSI = r;
                            workloadQueue.add(workloadUnite);
                            // }
                        }
                    }
                }
            }
            // }
        }
        System.out.println("Workload: " + workloadQueue.size());
    }

    int getWorkloadQueueSize() {
        return workloadQueue.size();
    }
}
