package ui;

import help.Helper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class HelpController {

    @FXML private Button okButton;

    public void initialize() {
        okButton.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.ESCAPE) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Thoát cửa sổ.
     */
    public void quit() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        Helper.playSound("/ui/sound/click.wav");
        stage.close();
    }
}
