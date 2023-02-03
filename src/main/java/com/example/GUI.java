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
    static FixedSizeArray floatArray = new FixedSizeArray(60);

    static void showProgress(Workload workload, int max) {
        while (workload.getWorkloadQueueSize() != 0) {
            System.out.print("\rWorkload left... " + workload.getWorkloadQueueSize() + " that is: "
                    + df.format(100 - (workload.getWorkloadQueueSize() / (float) max) * 100f) + "%. Time remaining: "
                    + tm.format(workload.getWorkloadQueueSize() / floatArray.average() / 60f)
                    + "min.                          ");
            try {
                startWorkload = workload.getWorkloadQueueSize();
                Thread.sleep(1000);
                endWorkload = workload.getWorkloadQueueSize();
                floatArray.add(startWorkload - endWorkload);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nComplete!");
    }
}
