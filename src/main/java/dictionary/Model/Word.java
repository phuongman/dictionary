package dictionary.Model;

public class Word {
    private final String wordTarget;
    private String wordMeaning;

    /**
     * Constructor.
     * 
     * @param wordTarget  English word
     * @param wordMeaning meaning of the word in Vietnamese
     */
    public Word(String wordTarget, String wordMeaning) {
        this.wordTarget = wordTarget;
        this.wordMeaning = wordMeaning;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public String getwordMeaning() {
        return this.wordMeaning;
    }

    public void setwordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
        return;
    }
}
