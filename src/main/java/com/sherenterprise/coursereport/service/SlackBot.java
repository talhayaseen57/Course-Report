package com.sherenterprise.coursereport.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.sherenterprise.coursereport.service.SlackSecrets.CHANNEL_ID;
import static com.sherenterprise.coursereport.service.SlackSecrets.SLACK_TOKEN;

public class SlackBot {

    private static final String SLACK_API_URL = "https://slack.com/api/chat.postMessage";

    public static void postMessage(String message) {
        try {
            URL url = new URL(SLACK_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + SLACK_TOKEN);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            System.out.println("About to post this message to slack: " + message);
            String jsonPayload = "{\"channel\":\"" + CHANNEL_ID + "\",\"text\":\"" + message + "\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
