package model;

public class Word {
    private String word_target;
    private String word_explain;

    /**
     * Constructor.
     * @param word_target: từ mới
     * @param word_explain: nghĩa của từ mới
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    /**
     * return từ mới.
     * @param word_target: từ mới
     */
    public String getWordTarget(String word_target) {
        return word_target;
    }

    /**
     * return nghĩa của từ mới.
     * @param word_explain: nghĩa của từ mới
     */
    public String getWordExplain(String word_explain) {
        return word_explain;
    }

    /**
     * set từ mới.
     * @param word_target: từ mới
     */
    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }

    /**
     * Set nghĩa của từ mới.
     * @param word_explain: nghĩa của từ mới
     */
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }


}
