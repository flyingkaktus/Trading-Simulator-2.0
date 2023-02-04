package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        int threads_ = 0;
        String pathToCSV = "rdy_for_app2.csv";
        Charts charts = new Charts(pathToCSV);
        System.out.println(charts.entries.size());
        int workloadSizeSession = 0;

        Workload workload = new Workload();
        workload.range_gain(0.4f, 6f, 0.2f);
        workload.range_EMA(80, 120);
        // workload.range_SMMA(94, 106);
        // workload.range_RSI(5, 60);

        workload.generate();
        workloadSizeSession = workload.getWorkloadQueueSize();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the number of threads: ");
        threads_ = scanner.nextInt();
        scanner.close();

        long startTime = System.currentTimeMillis();

        Thread[] traders = new Thread[threads_];

        for (int i = 0; i < traders.length; i++) {
            traders[i] = new Thread(new Trader(workload, charts));
            traders[i].start();
        }

        GUI.showProgress(workload, workloadSizeSession);

        for (Thread trader : traders) {
            try {
                trader.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println(
                "Elapsed time: " + elapsedTime + " miliseconds and final size " + workload.getWorkloadQueueSize());

        try {
            FileWriter writer = new FileWriter("elapsedTime.txt", true);
            writer.write("Runtime: " + String.valueOf(elapsedTime) + "ms and " + threads_ +
                    " Threads and Workload of " + workloadSizeSession + ". Speed: " + elapsedTime / workloadSizeSession
                    + " ms per unite.\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
