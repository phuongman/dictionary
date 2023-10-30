package model;

public class Word {
    private String word_target;
    private String word_type;
    private String word_explain;
    private String word_pronounce;
    private String word_antonym;
    private String word_synonym;


    /**
     * return từ mới.
     */
    public String getWordTarget() {
        return word_target;
    }

    /**
     * return loại từ của từ mới.
     */
    public String getWordType() {
        return word_type;
    }

    /**
     * return nghĩa của từ mới.
     */
    public String getWordExplain() {
        return word_explain;
    }

    /**
     * return phát âm của từ mới.
     */
    public String getWordPronounce() {
        return word_pronounce;
    }

    /**
     * return từ đồng nghĩa của từ mới.
     */
    public String getWordSynonym() {
        return word_synonym;
    }

    /**
     * return từ trái nghĩa của từ mới.
     */
    public String getWordAntonym() {
        return word_antonym;
    }

    /**
     * set từ mới.
     * @param word_target: từ mới
     */
    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }

    /**
     * set loại từ của từ mới.
     * @param word_type: loại từ của từ mới
     */
    public void setWordType(String word_type) {
        this.word_type = word_type;
    }

    /**
     * Set nghĩa của từ mới.
     * @param word_explain: nghĩa của từ mới
     */
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    /**
     * set phát âm của từ mới.
     * @param word_pronounce: phát âm của từ mới
     */
    public void setWordPronounce(String word_pronounce) {
        this.word_pronounce = word_pronounce;
    }

    /**
     * set từ trái nghĩa của từ mới.
     * @param word_antonym: từ trái nghĩa của từ mới
     */
    public void setWordAntonym(String word_antonym) {
        this.word_antonym = word_antonym;
    }

    /**
     * set từ đồng nghĩa của từ mới.
     * @param word_synonym: từ đồng nghĩa của từ mới
     */
    public void setWordSynonym(String word_synonym) {
        this.word_synonym = word_synonym;
    }




}
