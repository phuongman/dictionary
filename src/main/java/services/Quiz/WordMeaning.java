package services.Quiz;

public class WordMeaning extends Quiz {

    public WordMeaning() {
        super();
    }

    @Override
    public void setQuestion(String question) {
        this.question = "What is the word that has the meaning: " + question;
    }


    public void initWordMeaning() {
    }
}
