package model;

public class Myword {
    private String word;
    private String meaning;

    public Myword() {
    }

    public Myword(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
