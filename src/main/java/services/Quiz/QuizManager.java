package services.Quiz;

import java.util.Random;


public class QuizManager {

    private Quiz quiz;
    private final int numQuiz = 10;
    private int numCorrect = 0;
    private Random rand = new Random();
    private int typeQuiz;
    private String answer;

    enum QuizType {
        FillTheBlank,
        WordMeaning,
        MeaningWord,
        WordPronunciation,
        PronunciationWord,
    }

    public QuizManager() {

    }
    public void setTypeQuiz(int typeQuiz) {
        this.typeQuiz = typeQuiz;
    }

    public int getTypeQuiz() {
        return this.typeQuiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void uppdateNumCorrect() {
        this.numCorrect++;
    }

    public int getNumCorrect() {
        return this.numCorrect;
    }

    public int getNumQuiz() {
        return this.numQuiz;
    }

    public void initQuiz() {
        int typeQuiz = rand.nextInt(6);
        setTypeQuiz(this.typeQuiz);
        switch (QuizType.values()[typeQuiz]) {
            case FillTheBlank:
                FillTheBlank newQuiz = new FillTheBlank();
                newQuiz.initFillTheBlank();
                setQuiz(newQuiz);
                break;
            case WordMeaning:
                WordMeaning newQuiz1 = new WordMeaning();
                newQuiz1.initWordMeaning();
                setQuiz(newQuiz1);
                break;
            case MeaningWord:
                MeaningWord newQuiz2 = new MeaningWord();
                newQuiz2.initMeaningWord();
                setQuiz(newQuiz2);
                break;
            case WordPronunciation:
                WordPronunciation newQuiz3 = new WordPronunciation();
                newQuiz3.initWordPronunciation();
                setQuiz(newQuiz3);
                break;
            case PronunciationWord:
                PronunciationWord newQuiz4 = new PronunciationWord();
                newQuiz4.initPronunciationWord();
                setQuiz(newQuiz4);
                break;
            default:
                break;
        }
    }

    public boolean checkAnswer(String answer) {
        if (answer.equals(getQuiz().getAnswer_correct())) {
            uppdateNumCorrect();
            return true;
        }
        return false;
    }



}
