package ui;

import Help.Helper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseGameController {
    public AppController appController;

    @FXML
    public void initialize() {
        try {
            appController = App.loaderApp.getController();
        } catch (Exception e) {
            System.out.println("ChooseGameController error");
        }
    }

    /**
     * Chơi Quiz.
     */
    public void playQuiz() {
        Helper.playSound("/ui/sound/click.wav");
        appController.loadTab("fxml/Quiz.fxml");
    }

    /**
     * Chơi Wordle.
     */
    public void playWordle() {
        Helper.playSound("/ui/sound/click.wav");
        appController.loadTab("fxml/Wordle.fxml");
    }
}
