package controller;

public class Helper {

    /**
     * chuẩn hóa lại xâu.
     */
    public static String stringNormalization(String word, boolean isWord) {
        if(word == null) return null;
        String res = word.trim().replaceAll("\\s+", " ");
        if(isWord) res = res.toLowerCase();
        return res;
    }

}
