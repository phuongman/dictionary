package services.Quiz;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class QuizManager {
    /**
     * Khai báo Quiz.
     */
    private Quiz quiz;
    private static final int numQuiz = 10;
    private int currentQuiz = 0;
    private int numCorrect = 0;
    private ChooseQuestion chooseQuestion;
    private String answer;

    public int getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(int currentQuiz) {
        this.currentQuiz = currentQuiz;
    }
    public void increaseCurrentQuiz() {
        this.currentQuiz++;
    }

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

    public void updateNumCorrect() {
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
        //System.out.println(typeQuiz);
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
            updateNumCorrect();
            return true;
        }
        return false;
    }

    public void reset() {
        currentQuiz = 0;
        numCorrect = 0;
    }



    public static void main(String[] args) {
        QuizManager quizManager = new QuizManager();
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            System.out.println("ban co muon exit ko:");
            int ok = sc.nextInt();
            if(ok == 1) {
                quizManager.reset();
                exit = true;
                System.out.println("exit game");
                continue;
            }
            for(int i = 1; i <= 10; i++) {
                quizManager.initQuiz();
                System.out.println(quizManager.getQuiz().getQuestion());
                System.out.println(quizManager.getQuiz().getAnswer_correct());
                System.out.println(quizManager.getQuiz().getChoices()[0]);
                System.out.println(quizManager.getQuiz().getChoices()[1]);
                System.out.println(quizManager.getQuiz().getChoices()[2]);
                System.out.println(quizManager.getQuiz().getChoices()[3]);
                System.out.println("nhap dap an:");
                int x = sc.nextInt();
                System.out.println(quizManager.checkAnswer(quizManager.getQuiz().getChoices()[x]));
            }
            System.out.println(quizManager.numCorrect);
            System.out.println("ban co muon choi lai:");
            ok = sc.nextInt();
            if(ok != 0) {
                quizManager.reset();
            }
            else {
                exit = true;
            }


        }
    }

}
