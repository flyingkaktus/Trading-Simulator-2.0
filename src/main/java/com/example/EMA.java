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
        String csvFile = "candlestick_data/candlestick_data_ADAUSDT_1m_1613347501000.csv";
        String line = "";
        String cvsSplitBy = ",";
        List<Data> dataList = new ArrayList<>();
        int candles0 = 10000;   // 7d
        int candles1 = 1440;     // 24h
        int candles2 = 1220;     // 12h
        int candles3 = 180;      // 3h
        
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

            float ema0 = 0;
            for (int i = candles0; i < dataList.size(); i++) {
                float prevEma = ema0;
                float price = dataList.get(i).price;
                ema0 = (price * 2 + prevEma * (candles0 - 1)) / (candles0 + 1);
                dataList.get(i).ema0 = Float.parseFloat(df.format(ema0));
            }

            float ema1 = 0;
            for (int i = candles1; i < dataList.size(); i++) {
                float prevEma = ema1;
                float price = dataList.get(i).price;
                ema1 = (price * 2 + prevEma * (candles1 - 1)) / (candles1 + 1);
                dataList.get(i).ema1 = Float.parseFloat(df.format(ema1));
            }

            float ema2 = 0;
            for (int i = candles2; i < dataList.size(); i++) {
                float prevEma = ema2;
                float price = dataList.get(i).price;
                ema2 = (price * 2 + prevEma * (candles2 - 1)) / (candles2 + 1);
                dataList.get(i).ema2 = Float.parseFloat(df.format(ema2));
            }

            float ema3 = 0;
            for (int i = candles3; i < dataList.size(); i++) {
                float prevEma = ema3;
                float price = dataList.get(i).price;
                ema3 = (price * 2 + prevEma * (candles3 - 1)) / (candles3 + 1);
                dataList.get(i).ema3 = Float.parseFloat(df.format(ema3));
            }

            // EMA zur CSV hinzufügen
            try (FileWriter writer = new FileWriter("output_EMA.csv")) {
                writer.append("timestamp");
                writer.append(",");
                writer.append("price");
                writer.append(",");
                writer.append("EMA_" + candles0);
                writer.append(",");
                writer.append("EMA_" + candles1);
                writer.append(",");
                writer.append("EMA_" + candles2);
                writer.append(",");
                writer.append("EMA_" + candles3);
                writer.append("\n");

                for (Data d : dataList) {
                    writer.append(String.valueOf(d.timestamp));
                    writer.append(",");
                    writer.append(String.valueOf(d.price));
                    writer.append(",");
                    writer.append(String.valueOf(d.ema0));
                    writer.append(",");
                    writer.append(String.valueOf(d.ema1));
                    writer.append(",");
                    writer.append(String.valueOf(d.ema2));
                    writer.append(",");
                    writer.append(String.valueOf(d.ema3));
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
        float ema0;
        float ema1;
        float ema2;
        float ema3;

        public Data(long timestamp, float price) {
            this.timestamp = timestamp;
            this.price = price;
        }
    }
}
