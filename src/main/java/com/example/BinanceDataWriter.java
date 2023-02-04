package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class BinanceDataWriter {

    void getData(String symbol, int timeInterval, String tm, long startTime, long endTime) {

        long runs = 0L;
        long interval = 1000L;

        switch (tm) {
            case "m":
                runs = Long.valueOf(timeInterval) * 60L * 1000L * interval;
                System.out.println("case m");
                break;
            case "h":
                runs = Long.valueOf(timeInterval) * 60L * 60L * 1000L * interval;
                System.out.println("case h");
                break;
            case "d":
                runs = Long.valueOf(timeInterval) * 24L * 60L * 60L * 1000L * interval;
                System.out.println("case d");
                break;
        }

        while (startTime < endTime) {
            try {
                // URL für die Binance API-Anfrage erstellen
                URL url = new URL(
                        "https://api.binance.com/api/v3/klines?symbol=" + symbol + "&interval="
                                + String.valueOf(timeInterval)
                                + tm + "&limit=" + String.valueOf(interval) + "&startTime="
                                + String.valueOf(startTime));
                System.out.println(url);
                System.out.println(runs);
                // Verbindung zur URL herstellen und Daten abrufen
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    // Daten in ein Array splitten
                    String[] dataRows = line.split("],");

                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter(
                                    "candlestick_data/candlestick_data" + "_" + symbol + "_" + timeInterval + tm
                                            + ".csv",
                                    true));
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

        binanceDataWriter.getData("ADAUSDT", 1, "m", 1640995261000L, 1675511373000L);
    }
}
