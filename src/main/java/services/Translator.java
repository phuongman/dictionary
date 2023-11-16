package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    private static final String myUrl = "https://script.google.com/macros/s/AKfycbz0u_LKYSLRwuK3MqJfORN7OkQH2EqzGxKuvQ7bLTmNXM39V55d2AD5uFzFv1z1IourYw/exec";


    /**
     * Dịch Tiếng Anh ra Tiếng Việt.
     */
    public static String translateEnglishToVietnamese(String text) {
        try {
            return translate("en", "vi", text);
        } catch (IOException e) {
            System.out.println("chuyển anh sang việt sai");
        }
        return "Error";
    }

    /**
     * Dịch Tiếng Việt ra Tiếng Anh.
     */
    public static String translateVietnameseToEnglish(String text) {
        try {
            return translate("vi", "en", text);
        } catch (IOException e) {
            System.out.println("chuyển việt sang anh sai");
        }
        return "Error";
    }

    public static String translate(String langFrom, String langTo, String text) throws IOException {
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

