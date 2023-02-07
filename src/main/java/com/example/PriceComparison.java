package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriceComparison {
    public static void main(String[] args) {
        String csvFile = "data/csv/rdy_for_app.csv";
        String line = "";
        String cvsSplitBy = ",";
        int comparisonWindow = 6 * 60;
        double growthFactor = 1.01d;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            List<String[]> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line.split(cvsSplitBy));
            }

            // Add new result column with header
            String[] headers = lines.get(0);
            String[] newHeaders = new String[headers.length + 1];
            System.arraycopy(headers, 0, newHeaders, 0, headers.length);
            newHeaders[headers.length] = "result";
            lines.set(0, newHeaders);

            // Add result values
            for (int i = 1; i < lines.size(); i++) {
                String[] currentLine = lines.get(i);
                int startIndex = i + 1;
                int endIndex = Math.min(lines.size(), i + comparisonWindow + 1);
                boolean flag = false;
                for (int j = startIndex; j < endIndex; j++) {
                    double nextPrice = Double.parseDouble(lines.get(j)[1]);
                    if (Double.parseDouble(currentLine[1]) * growthFactor < nextPrice) {
                        flag = true;
                        break;
                    }
                }
                String[] newLine = new String[currentLine.length + 1];
                System.arraycopy(currentLine, 0, newLine, 0, currentLine.length);
                newLine[currentLine.length] = flag ? "1" : "0";
                lines.set(i, newLine);
            }

            // Write to new file
            try (FileWriter writer = new FileWriter("result_file.csv")) {
                for (String[] lineArray : lines) {
                    for (int i = 0; i < lineArray.length; i++) {
                        writer.append(lineArray[i]);
                        if (i < lineArray.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }
}
