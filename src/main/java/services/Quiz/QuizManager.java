package services.Quiz;

import java.util.Random;


public class QuizManager {
    /**
     * Khai báo Quiz.
     */
    private Quiz quiz;
    private final int numQuiz = 10;
    private int numCorrect = 0;
    private ChooseQuestion chooseQuestion;
    private String answer;

    public QuizManager() {
        chooseQuestion = new ChooseQuestion();
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
        Random rand = new Random();
        int typeQuiz = rand.nextInt(5);
        Quiz newQuiz;
        System.out.println(typeQuiz);
        if(typeQuiz == 0) {
            newQuiz = chooseQuestion.initFillTheBlank();
        }
        else {
            newQuiz = chooseQuestion.initQuiz(typeQuiz);
        }
        setQuiz(newQuiz);
    }

    public boolean checkAnswer(String answer) {
        if (answer.equals(getQuiz().getAnswer_correct())) {
            uppdateNumCorrect();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        QuizManager quizManager = new QuizManager();
        quizManager.initQuiz();
        System.out.println(quizManager.getQuiz().getQuestion());
        System.out.println(quizManager.getQuiz().getAnswer_correct());
        System.out.println(quizManager.getQuiz().getChoices()[0]);
        System.out.println(quizManager.getQuiz().getChoices()[1]);
        System.out.println(quizManager.getQuiz().getChoices()[2]);
        System.out.println(quizManager.getQuiz().getChoices()[3]);
    }

}
