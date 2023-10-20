package model;

public class Word {
    private int id;
    private String word_target;
    private String word_type;
    private String word_explain;
    private String word_pronounce;
    private String word_example;
    private String word_antonym;
    private String word_synonym;


    /**
     * return id của từ mới.
     * @param id: id của từ mới
     */
    public int getId(int id) {
        return id;
    }
    /**
     * return từ mới.
     * @param word_target: từ mới
     */
    public String getWordTarget(String word_target) {
        return word_target;
    }

    /**
     * return loại từ của từ mới.
     * @param word_type: loại từ của từ mới
     */
    public String getWordType(String word_type) {
        return word_type;
    }

    /**
     * return nghĩa của từ mới.
     * @param word_explain: nghĩa của từ mới
     */
    public String getWordExplain(String word_explain) {
        return word_explain;
    }

    /**
     * return phát âm của từ mới.
     * @param word_pronounce: phát âm của từ mới
     */
    public String getWordPronounce(String word_pronounce) {
        return word_pronounce;
    }

    /**
     * return ví dụ của từ mới.
     * @param word_example: ví dụ của từ mới
     */
    public String getWordExample(String word_example) {
        return word_example;
    }

    /**
     * return từ đồng nghĩa của từ mới.
     * @param word_synonym: từ đồng nghĩa của từ mới
     */
    public String getWordSynonym(String word_synonym) {
        return word_synonym;
    }

    /**
     * return từ trái nghĩa của từ mới.
     * @param word_antonym: từ trái nghĩa của từ mới
     */
    public String getWordAntonym(String word_antonym) {
        return word_antonym;
    }

    /**
     * set id của từ mới.
     * @param id: id của từ mới
     */
    public void setId(int id) {
        this.id = id;
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
     * set ví dụ của từ mới.
     * @param word_example: ví dụ của từ mới
     */
    public void setWordExample(String word_example) {
        this.word_example = word_example;
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
