package com.example;

import java.text.DecimalFormat;

public class GUI {

    static DecimalFormat df = new DecimalFormat("#.###");

    static void showProgress(Workload workload, int max) {
        while (workload.getWorkloadQueueSize() != 0) {
            System.out.print("\rWorkload left... " + workload.getWorkloadQueueSize() + " that is: "
                    + df.format(100 - (workload.getWorkloadQueueSize() / (float) max) * 100f) + "%.            ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nComplete!");
    }
}
