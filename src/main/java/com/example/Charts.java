package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Charts {

    ArrayList<Entry> entries = new ArrayList<Entry>();

    Charts(String pathToCSV) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(pathToCSV));
        DecimalFormat df = new DecimalFormat("#.###");
        String line;

        while ((line = in.readLine()) != null) {

            String[] values = line.split(",");

            Entry newEntry = new Entry();
            try {
                newEntry.time = Long.parseLong(values[0]);
                newEntry.close = Float.parseFloat(df.format(Float.parseFloat(values[1])));
                // newEntry.SMMA = Float.parseFloat(df.format((Float.parseFloat(values[2]) /
                // newEntry.close) * 100));
                newEntry.EMA0 = Float.parseFloat(df.format((Float.parseFloat(values[2]) / newEntry.close) * 100));
                newEntry.EMA1 = Float.parseFloat(df.format((Float.parseFloat(values[3]) / newEntry.close) * 100));
                newEntry.EMA2 = Float.parseFloat(df.format((Float.parseFloat(values[4]) / newEntry.close) * 100));
                newEntry.EMA3 = Float.parseFloat(df.format((Float.parseFloat(values[5]) / newEntry.close) * 100));
                // newEntry.RSI = Float.parseFloat(df.format(Float.parseFloat(values[4])));
                entries.add(newEntry);
            } catch (NumberFormatException e) {
                System.out.println("Zeile wird ├╝bersprungen..");
            }

            // newEntry.show();

        }

        in.close();
    }
}
