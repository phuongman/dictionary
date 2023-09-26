package dictionary.Model;

public class Word {
    private final String wordTarget;
    private String wordExplain;

    /**
     * Constructor.
     * 
     * @param wordTarget  English word
     * @param wordExplain meaning of the word in Vietnamese
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
        return;
    }
}
