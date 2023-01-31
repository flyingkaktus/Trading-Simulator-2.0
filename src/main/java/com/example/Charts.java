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

        while((line = in.readLine()) != null) {

            String[] values = line.split(",");

            Entry newEntry = new Entry();

            try {
                newEntry.time = Integer.parseInt(values[0]);
                newEntry.close = Float.parseFloat(df.format(Float.parseFloat(values[1])));
                newEntry.SMMA = Float.parseFloat(df.format(Float.parseFloat(values[2])));
                newEntry.EMA = Float.parseFloat(df.format(Float.parseFloat(values[3])));
                newEntry.RSI = Float.parseFloat(df.format(Float.parseFloat(values[4])));
                newEntry.OBV = Float.parseFloat(df.format(Float.parseFloat(values[5])));
                entries.add(newEntry);
            } catch (NumberFormatException e) {
                System.out.println("Zeile wird übersprungen..");
            }

        }

        in.close();

        }
}
