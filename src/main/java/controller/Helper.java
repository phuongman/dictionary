package controller;

public class Helper {

    public static String stringNormalization(String word, boolean isWord) {
        if(word == null) return null;
        String res = word.trim().replaceAll("\\s+", " ");
        if(isWord) res = res.toLowerCase();
        return res;
    }

}
