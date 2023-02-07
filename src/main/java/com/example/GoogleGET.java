package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GoogleGET {

  public static void main(String[] args) {

    String query = "bitcoin";
    StringBuilder response = new StringBuilder();
    String line;
    
    try {
      URL url = new URL("https://trends.google.com/trends/explore?q=" + query + "&geo=US&date=today%2012-m");
      URLConnection connection = url.openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0");
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      
      reader.close();
    } catch (IOException e ) {
      e.printStackTrace();
    }
    
    // Parse the response to extract the desired data
  }
}
