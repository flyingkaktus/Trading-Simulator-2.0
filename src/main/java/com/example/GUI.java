package com.example;

import java.text.DecimalFormat;

public class GUI {

    static float startWorkload = 1f;
    static float endWorkload = 1f;
    static float perSecWorkload = 1f;
    static float avgSecWorkload = 1f;
    static float rounds = 1f;

    static DecimalFormat df = new DecimalFormat("#.###");
    static DecimalFormat tm = new DecimalFormat("#");

    static void showProgress(Workload workload, int max) {
        while (workload.getWorkloadQueueSize() != 0) {
            System.out.print("\rWorkload left... " + workload.getWorkloadQueueSize() + " that is: "
                    + df.format(100 - (workload.getWorkloadQueueSize() / (float) max) * 100f) + "%. Time remaining: "
                    + tm.format((workload.getWorkloadQueueSize() / (avgSecWorkload / rounds)) / 60f)
                    + "min.                          ");
            try {
                startWorkload = workload.getWorkloadQueueSize();
                Thread.sleep(999);
                endWorkload = workload.getWorkloadQueueSize();
                perSecWorkload = startWorkload - endWorkload;
                rounds++;
                avgSecWorkload += perSecWorkload;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nComplete!");
    }
}
