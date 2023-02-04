package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class BinanceDataWriter {

    void getData(int timeInterval, long startTime, long endTime) {
        long runs = timeInterval * 60 * 1000 * 1000;
        while (startTime < endTime) {
            try {
                // URL für die Binance API-Anfrage erstellen
                URL url = new URL(
                        "https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=" + String.valueOf(timeInterval)
                                + "m&limit=1000&startTime=" + String.valueOf(startTime));
                System.out.println(url);
                System.out.println(runs);
                // Verbindung zur URL herstellen und Daten abrufen
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    // Daten in ein Array splitten
                    String[] dataRows = line.split("],");

                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("candlestick_data/candlestick_data.csv", true));
                    for (String dataRow : dataRows) {
                        String[] data = dataRow.split(",");

                        // Werte extrahieren
                        long timestamp = Long.parseLong(data[0].replace("[", ""));
                        double openPrice = Double.parseDouble(data[1].replace("\"", ""));

                        // Daten in eine Datei schreiben
                        writer.write(timestamp + "," + openPrice);
                        writer.newLine();
                    }
                    writer.close();
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startTime += runs;
        }
    }

    public static void main(String[] args) {

        BinanceDataWriter binanceDataWriter = new BinanceDataWriter();

        binanceDataWriter.getData(5, 1640995261000L, 1675511373000L);
    }
}
