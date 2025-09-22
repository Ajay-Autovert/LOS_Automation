package apiLibrary;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIResponces {

    private static final String API_URL = "https://los.dev.autovert.in/api/fetch-otp";
    private static final String CONTENT_TYPE = "application/json";

    public static String fetchOtp(String identifier) throws IOException {
        String jsonPayload;

        if (identifier.matches("\\d{10}")) {
            // It's a phone number
            jsonPayload = "{ \"phone\": \"" + identifier + "\" }";
        } else if (identifier.contains("@")) {
            // It's an email
            jsonPayload = "{ \"email\": \"" + identifier + "\" }";
        } else {
            throw new IllegalArgumentException("Invalid input: must be a 10-digit phone number or a valid email.");
        }

        @SuppressWarnings("deprecation")
		HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        InputStream responseStream = (responseCode >= 200 && responseCode < 300)
                ? conn.getInputStream()
                : conn.getErrorStream();

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
        }

        conn.disconnect();

        // Extract OTP from response JSON
        String[] parts = response.toString().split("\"otp\":\"");
        if (parts.length < 2) throw new RuntimeException("OTP not found in response: " + response);
        return parts[1].split("\"")[0];
    }
}