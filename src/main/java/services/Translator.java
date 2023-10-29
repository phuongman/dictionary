package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {

    /**
     * This class is used to translate a word from English to Vietnamese
     * using Google Translate API
     * @param text the word to be translated
     * @return the translated word
     * @throws IOException
     */
    private final String langFrom = "en";
    private final String langTo = "vi";
    private final String myUrl = "https://script.google.com/macros/s/AKfycbz0u_LKYSLRwuK3MqJfORN7OkQH2EqzGxKuvQ7bLTmNXM39V55d2AD5uFzFv1z1IourYw/exec";
    private String text;

    public Translator(String text) {
        this.text = text;
    }
    public String translate() throws IOException {
        // INSERT YOU URL HERE
        String urlStr =  myUrl +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}

