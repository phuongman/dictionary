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
    private int numCorrect = 0;
    private ChooseQuestion chooseQuestion;
    private String answer;
    Timer timer;
    private static final int time = 2;
    boolean isPlaying = false;
    private int timeElapsed = 0;

    public QuizManager() {
        timer = new Timer();
        chooseQuestion = new ChooseQuestion();
        isPlaying = false;
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
        int timeCur = timeElapsed;
        if (answer.equals(getQuiz().getAnswer_correct()) && isPlaying) {
            uppdateNumCorrect();
            return true;
        }
        return false;
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isPlaying) {
                    timeElapsed++; // Tăng thời gian đã trôi qua sau mỗi giây nếu game đang chạy và không bị tạm dừng
                }
            }
        }, 1000, 1000); // Lặp lại mỗi giây (1000 mili giây)
    }

    public void startGame() {
        isPlaying = true; // Bắt đầu game
    }

    public void reset() {
        timeElapsed = 0;
        numCorrect = 0;
        isPlaying = false;
    }

    public void play() {
        startGame();
        startTimer();
    }

    public void rePlay() {
        reset();
        play();
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
            quizManager.play();
            for(int i = 1; i <= 10; i++) {
                quizManager.timeElapsed = 0;
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
                if(quizManager.timeElapsed > time) System.out.println("qua time");
            }
            System.out.println(quizManager.numCorrect);
            System.out.println("ban co muon choi lai:");
            ok = sc.nextInt();
            if(ok != 0) {
                quizManager.rePlay();
            }
            else {
                exit = true;
            }


        }
    }

}
