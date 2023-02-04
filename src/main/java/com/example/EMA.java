package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EMA {
    public static void main(String[] args) {
        String csvFile = "candlestick_data/candlestick_data_ADAUSDT_1m.csv";
        String line = "";
        String cvsSplitBy = ",";
        List<Data> dataList = new ArrayList<>();
        int candles = 500;
        DecimalFormat df = new DecimalFormat("#.###");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                long timestamp = Long.parseLong(data[0]);
                float price = Float.parseFloat(data[1]);
                Data d = new Data(timestamp, price);
                dataList.add(d);
                lineNumber++;
            }

            // EMA berechnen
            float ema = 0;
            for (int i = candles; i < dataList.size(); i++) {
                float prevEma = ema;
                float price = dataList.get(i).price;
                ema = (price * 2 + prevEma * (candles - 1)) / (candles + 1);
                dataList.get(i).ema = Float.parseFloat(df.format(ema));
            }

            // EMA zur CSV hinzufügen
            try (FileWriter writer = new FileWriter("output_EMA.csv")) {
                writer.append("timestamp");
                writer.append(",");
                writer.append("price");
                writer.append(",");
                writer.append("EMA");
                writer.append("\n");

                for (Data d : dataList) {
                    writer.append(String.valueOf(d.timestamp));
                    writer.append(",");
                    writer.append(String.valueOf(d.price));
                    writer.append(",");
                    writer.append(String.valueOf(d.ema));
                    writer.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Data {
        long timestamp;
        float price;
        float ema;

        public Data(long timestamp, float price) {
            this.timestamp = timestamp;
            this.price = price;
        }
    }
}
