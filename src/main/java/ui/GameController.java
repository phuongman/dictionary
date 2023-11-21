package ui;

import Help.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static ui.AppController.*;

public class GameController implements Initializable {

    @FXML
    private Button quizbutton;
    @FXML
    private Button wordlebutton;
    @FXML
    private AnchorPane gamePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onActionwordlebutton(ActionEvent actionEvent) {
        Helper.playSound("ui/sound/click.wav");
        loadTab("fxml/Quiz.fxml");
    }

    public void onMouseEnteredwordlebutton(MouseEvent mouseEvent) {
        wordlebutton.setStyle(
                "-fx-background-color: lightsteelblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20 20 20 20;"
        );
    }

    public void onActionquizbutton() {
        Helper.playSound("ui/sound/click.wav");
        loadTab("fxml/Quiz.fxml");
    }

    private void loadTab(String s) {
    }

    public void onMouseEnteredquizbutton() {
        quizbutton.setStyle(
                "-fx-background-color: lightsteelblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20 20 20 20;"
        );
    }

    public void onMouseExitquizbutton(MouseEvent mouseEvent) {
        quizbutton.setStyle(
                "-fx-background-color: steelblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20 20 20 20;"
        );
    }

    public void onMouseExitwordlebutton(MouseEvent mouseEvent) {
        wordlebutton.setStyle(
                "-fx-background-color: steelblue;" +
                        "-fx-text-fill: black;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 20 20 20 20;"
        );
    }
    
}
