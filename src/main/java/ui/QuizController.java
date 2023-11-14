package ui;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.Quiz.QuizManager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

class QuizService extends Service<Void> {
    private final long delayMillis;

    public QuizService(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(delayMillis);
                return null;
            }
        };
    }
}
public class QuizController implements Initializable {
    private QuizService quizService;
    private QuizManager quizManager = new QuizManager();
    @FXML
    public Label Question;
    @FXML
    private Button playButton;
    @FXML
    private Pane preQuiz;
    @FXML
    private Label numberQuiz;
    @FXML
    private Label statusQuiz;
    @FXML
    private ProgressBar TimerProgressBar;
    @FXML
    private GridPane answerTable;
    @FXML
    private RadioButton answerA;
    @FXML
    private RadioButton answerB;
    @FXML
    private RadioButton answerC;
    @FXML
    private RadioButton answerD;
    @FXML
    private ToggleGroup Choices = new ToggleGroup();
    @FXML
    private HBox informationQuiz;
    @FXML
    private Label Require;
    @FXML
    private AnchorPane Score;
    @FXML
    private Label YourScore;
    @FXML
    private Button ExitQuizButton;
    @FXML
    private Button RePlayQuizButton;

    private Timer timer;
    private int currentTimeInSeconds = 0;
    private int maxTimeInSeconds = 400;
    private RadioButton answerCorrect;
    private RadioButton answerSelected;
    private boolean answerselect = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        informationQuiz.setVisible(false);
        preQuiz.setVisible(true);
        answerTable.setVisible(false);
        TimerProgressBar.setVisible(false);
        Question.setVisible(false);
        Require.setVisible(false);
        quizService = new QuizService(1000);
        quizService.setOnSucceeded(event -> proceedToNextQuestion());
        answerA.setToggleGroup(Choices);
        answerB.setToggleGroup(Choices);
        answerC.setToggleGroup(Choices);
        answerD.setToggleGroup(Choices);
        answerA.setOnMouseClicked(event -> onRadioButtonClicked(answerA));
        answerB.setOnMouseClicked(event -> onRadioButtonClicked(answerB));
        answerC.setOnMouseClicked(event -> onRadioButtonClicked(answerC));
        answerD.setOnMouseClicked(event -> onRadioButtonClicked(answerD));

    }

    @FXML
    private void onRadioButtonClicked(RadioButton selectedAnswer) {
        if (answerselect) {
            // Nếu đã chọn một đáp án, không làm gì cả
            selectedAnswer.setSelected(false);
            answerSelected.setSelected(true);
            return;
        }

        // Chọn đáp án và đặt answerSelected thành true
        selectedAnswer.setSelected(true);

        answerselect = true;

        // Gọi phương thức checkAnswer với đáp án đã chọn
        checkAnswer(selectedAnswer);
    }


    @FXML
    public void onMouseEnteredPlay() {
        playButton.setStyle(
                "-fx-background-color: lightsteelblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20 20 20 20;"
        );
    }
    @FXML
    public void onActionPlay() {
        informationQuiz.setVisible(true);
        preQuiz.setVisible(false);
        answerTable.setVisible(true);
        TimerProgressBar.setVisible(true);
        Question.setVisible(true);
        Require.setVisible(true);
        startQuiz();
    }

    private void startTimer() {
        // Khởi tạo Timer để cập nhật ProgressBar
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateProgressBar();
            }
        }, 50, 50);
    }

    public void stopTimer() {
        timer.cancel();
    }

    private void updateProgressBar() {

        if (answerselect) {
            // Nếu đáp án đã được chọn, không cập nhật ProgressBar và dừng Timer
            timer.cancel();
            return;
        }

        currentTimeInSeconds++;

        // Chạy trên luồng UI để cập nhật ProgressBar
        javafx.application.Platform.runLater(() -> {
            double progress = (double) currentTimeInSeconds / maxTimeInSeconds;
            TimerProgressBar.setProgress(progress);

            if (currentTimeInSeconds == maxTimeInSeconds) {
                setColorAnswerCorrect();
                timer.cancel();
                quizService.restart();

            }
        });
    }

    public void startQuiz() {
        quizManager.reset();
        // Đặt lại giá trị thời gian và câu hỏi đầu tiên
        currentTimeInSeconds = 0;
        proceedToNextQuestion();
    }
    private void showFinalScore() {
        YourScore.setText("Your score: " + quizManager.getNumCorrect() + "/10");
        Score.setVisible(true);
    }

    private void resetAnswerOptions() {
        answerA.setStyle("");  // Đặt lại màu sắc của các đáp án
        answerB.setStyle("");
        answerC.setStyle("");
        answerD.setStyle("");
        answerA.setFocusTraversable(false);
        answerB.setFocusTraversable(false);
        answerC.setFocusTraversable(false);
        answerD.setFocusTraversable(false);
        answerA.setSelected(true);
        answerB.setSelected(true);
        answerC.setSelected(true);
        answerD.setSelected(true);
        resetToggleGroup();
    }
    private void resetToggleGroup() {
        Choices.selectToggle(null);
    }
    private void proceedToNextQuestion() {
        if (quizManager.getCurrentQuiz() == 10) {
            showFinalScore();
            return;
        }
        quizManager.initQuiz();
        resetToggleGroup();
        quizManager.increaseCurrentQuiz();

        numberQuiz.setText("Câu: " + quizManager.getCurrentQuiz() + "/10");
        statusQuiz.setText("Đúng: " + quizManager.getNumCorrect() + " Sai: " + (quizManager.getCurrentQuiz() - 1 - quizManager.getNumCorrect()));



        // set câu hỏi
        String question = quizManager.getQuiz().getQuestion();
        for (int j = 0; j < question.length(); j++) {
            if (question.charAt(j) == ':') {
                Require.setText(question.substring(0, j + 1));
                Question.setText(question.substring(j + 2));
                break;
            }
        }
        answerA.setText(quizManager.getQuiz().getChoices()[0]);
        answerB.setText(quizManager.getQuiz().getChoices()[1]);
        answerC.setText(quizManager.getQuiz().getChoices()[2]);
        answerD.setText(quizManager.getQuiz().getChoices()[3]);

        // đáp án đúng
        if (quizManager.getQuiz().getAnswer_correct().equals(quizManager.getQuiz().getChoices()[0])) {
            answerCorrect = answerA;
        }
        if (quizManager.getQuiz().getAnswer_correct().equals(quizManager.getQuiz().getChoices()[1])) {
            answerCorrect = answerB;
        }
        if (quizManager.getQuiz().getAnswer_correct().equals(quizManager.getQuiz().getChoices()[2])) {
            answerCorrect = answerC;
        }
        if (quizManager.getQuiz().getAnswer_correct().equals(quizManager.getQuiz().getChoices()[3])) {
            answerCorrect = answerD;
        }

        // reset button
        answerselect = false;
        resetAnswerOptions();

        // Bắt đầu đếm thời gian cho câu hỏi mới
        currentTimeInSeconds = 0;
        startTimer();

    }
    public void setColorAnswerCorrect() {
        if(answerA == answerCorrect) {
            answerA.setStyle("-fx-text-fill: #3cb371;");
        }
        if(answerB == answerCorrect) {
            answerB.setStyle("-fx-text-fill: #3cb371;");
        }
        if(answerC == answerCorrect) {
            answerC.setStyle("-fx-text-fill: #3cb371;");
        }
        if(answerD == answerCorrect) {
            answerD.setStyle("-fx-text-fill: #3cb371;");
        }
    }

    private void checkAnswer(RadioButton selectedAnswer) {
        answerselect = true;
        answerSelected = selectedAnswer;
        String answer = selectedAnswer.getText();
        if (quizManager.checkAnswer(answer)) {
            System.out.println("Đúng");
            System.out.println("Đáp án đúng: " + quizManager.getQuiz().getAnswer_correct());
            selectedAnswer.setStyle("-fx-text-fill: #3cb371;");  // Đúng: màu xanh
        } else {
            selectedAnswer.setStyle("-fx-text-fill: #b22222;");  // Sai: màu đỏ
            setColorAnswerCorrect();
        }
        statusQuiz.setText("Đúng: " + quizManager.getNumCorrect() + " Sai: " + (quizManager.getCurrentQuiz() - quizManager.getNumCorrect()));
        stopTimer();  // Dừng đếm thời gian
        quizService.restart();
    }

    public void exitMouseDragPlay() {
        playButton.setStyle(
                "-fx-background-color: steelblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-radius: 20px;" +
                        "-fx-background-radius: 20 20 20 20;"
        );
    }

    public void onActionExitQuizButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onActionRePlayQuizButton(ActionEvent actionEvent) {
        informationQuiz.setVisible(false);
        preQuiz.setVisible(true);
        answerTable.setVisible(false);
        TimerProgressBar.setVisible(false);
        Question.setVisible(false);
        Require.setVisible(false);
        Score.setVisible(false);
    }
}
