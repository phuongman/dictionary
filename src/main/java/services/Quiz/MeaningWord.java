package services.Quiz;

public class MeaningWord extends Quiz {

    public MeaningWord() {
        super();
    }

    @Override
    public void setQuestion(String question) {
        this.question = "What is the word that has the meaning: " + question;
    }

    public void initMeaningWord() {
    }
}
