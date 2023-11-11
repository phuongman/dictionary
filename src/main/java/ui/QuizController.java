package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizController implements Initializable {

    @FXML
    private Button playButton;
    @FXML
    private Pane preQuiz;
    @FXML
    private Label numberQuiz;
    @FXML
    private Label statusQuiz;
    @FXML
    private ProgressBar Timer;
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
    private ToggleButton Choices;
    @FXML
    private HBox informationQuiz;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


    }
}
