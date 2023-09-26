package dictionary.Model;

public class Word {
    private final String wordTarget;
    private String wordExplain;

    /**
     * Constructor.
     * 
     * @param _wordTarget  English word
     * @param _wordExplain meaning of the word in Vietnamese
     */
    public Word(String _wordTarget, String _wordExplain) {
        this.wordTarget = _wordTarget;
        this.wordExplain = _wordExplain;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void setWordExplain(String _wordExplain) {
        this.wordExplain = _wordExplain;
        return;
    }
}
