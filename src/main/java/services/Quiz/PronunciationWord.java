package services.Quiz;

public class PronunciationWord extends Quiz {

    public PronunciationWord() {
        super();
    }

    @Override
    public void setQuestion(String question) {
        this.question = "What is the word that has the pronunciation: " + question;
    }

    public void initPronunciationWord() {
    }
}
