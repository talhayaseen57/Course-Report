package com.sherenterprise.coursereport.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackBot {

    private static final String TOKEN = "xoxb-4459424149986-6799838706535-64GGpN82MwBtkp9rVD3vFJ2n";
    private static final String CHANNEL_ID = "C06Q0Q5L1NY";
    private static final String SLACK_API_URL = "https://slack.com/api/chat.postMessage";

    public static void postMessage(String message) {
        try {
            URL url = new URL(SLACK_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + TOKEN);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            System.out.println("About to post this message to slack: " + message);
            String jsonPayload = "{\"channel\":\"" + CHANNEL_ID + "\",\"text\":\"" + message + "\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Handle the connection response...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
