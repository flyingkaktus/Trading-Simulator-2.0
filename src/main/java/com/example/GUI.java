package com.example;

public class GUI {
    
    static void showProgress(Workload workload){
        while (workload.getWorkloadQueueSize() != 0) {
            System.out.print("\rWorkload... " + workload.getWorkloadQueueSize());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nComplete!");
    }
}
