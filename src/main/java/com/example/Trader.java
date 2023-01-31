package com.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileWriter;
import java.text.DecimalFormat;

public class Trader implements Runnable {

    Workload workload;
    Charts chart;
    float konto = 500;
    float konto_ = konto;
    int buyTimeout = 60 * 5; // in seconds
    float kontoPortfolio;
    float buyAmount = 25;
    Portfolio portfolio_;
    LinkedList<Portfolio> portfolio = new LinkedList<>();
    int sold = 0;
    int bought = 0;
    DecimalFormat df = new DecimalFormat("#.###");

    Trader(Workload workload, Charts chart) {
        this.workload = workload;
        this.chart = chart;
    }

    void tryBuy(Entry chartsEntry, WorkloadUnits workloadUnite) {
        if (portfolio.isEmpty() == true) {
            operationBuy(chartsEntry, workloadUnite);
        } else {
            if (portfolio.getFirst().boughtAt + buyTimeout <= chartsEntry.time) {
                operationBuy(chartsEntry, workloadUnite);
            }

        }
    }

    void operationBuy(Entry chartsEntry, WorkloadUnits workloadUnite) {
        if (chartsEntry.EMA < workloadUnite.EMA &&
                chartsEntry.SMMA < workloadUnite.SMMA &&
                chartsEntry.RSI < workloadUnite.RSI &&
                konto - buyAmount >= buyAmount) {

            float boughtAmount = buyAmount / chartsEntry.close;
            portfolio_ = new Portfolio(chartsEntry.close, boughtAmount, chartsEntry.time);
            portfolio.push(portfolio_);
            konto -= buyAmount;
            bought++;
            // System.out.println("Es wurde was gekauft!" + bought);
        }
    }

    void trySell(Entry chartsEntry, WorkloadUnits workloadUnite) {
        Iterator<Portfolio> iterator = portfolio.iterator();
        while (iterator.hasNext()) {
            Portfolio portfolioEntry = iterator.next();
            float priceToSell = Float.parseFloat(df.format(portfolioEntry.boughtAt * (1 + (workloadUnite.gain / 100))));
            if (priceToSell <= chartsEntry.close) {
                konto += portfolioEntry.coinAmount * chartsEntry.close;
                sold++;
                // System.out.println("- " + portfolioEntry.boughtAt + " -- " + priceToSell + "
                // - " + chartsEntry.close);
                // System.out.println("Es wurde was verkauft!" + sold + "Im Folio: " +
                // portfolio.size());
                iterator.remove();
            }
        }
    }

    void doTrading(WorkloadUnits workloadUnite) throws Exception {
        for (Entry chartsEntry : chart.entries) {
            tryBuy(chartsEntry, workloadUnite);
            trySell(chartsEntry, workloadUnite);
        }
        determineValueInPortfolio();
        saveResult(workloadUnite);
    }

    void determineValueInPortfolio() {
        for (Portfolio entry : portfolio) {
            kontoPortfolio += entry.coinAmount * chart.entries.get(chart.entries.size() - 1).close;
        }
    }

    void reset() {
        konto = 500;
        kontoPortfolio = 0;
        buyAmount = 50;
        portfolio.clear();
        sold = 0;
        bought = 0;
    }

    void saveResult(WorkloadUnits workloadUnite) {
        try {
            FileWriter writer = new FileWriter("Evaluation.csv", true);
            writer.write(
                    workloadUnite.gain + "," + workloadUnite.SMMA + "," + workloadUnite.EMA + "," + workloadUnite.RSI
                            + "," + kontoPortfolio + "," + konto + "," + ((konto + kontoPortfolio) / konto_) + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (workload.workloadQueue.isEmpty() != true) {
            try {
                doTrading(workload.workloadQueue.poll());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            reset();
        }
        System.out.println(Thread.currentThread().getName() + "is done.");
    }
}
