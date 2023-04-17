package com.OOP.eplpredictions.processors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiProcessor {
    private static final String key = "phKuvBohYEATSTSx";
    private static final String secret = "nVsIw2tSxYyJhlBGClb8WAqVf0O7UHFh";

    public static String apiToString(String link) {
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                connection.disconnect();
                in.close();
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (IOException e) {
            return "";
        }

        return response.toString();
    }
}
