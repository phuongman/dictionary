package services.Quiz;

public class WordPronunciation extends Quiz{

    public WordPronunciation() {
        super();
    }
    @Override
    public void setQuestion(String question) {
        this.question = "What is the word that is pronounced: " + question;
    }

    public void initWordPronunciation() {
    }
}
