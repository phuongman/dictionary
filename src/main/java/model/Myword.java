package model;

public class Myword {
    /**
     * Khai báo các thuộc tính của Myword
     */
    private String word;
    private String meaning;

    /**
     * Khai báo constructor.
     */
    public Myword() {
    }

    /**
     * Khai báo constructor.
     */
    public Myword(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    /**
     * Khai báo getter và setter.
     */
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
