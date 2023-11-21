package ui;

import Help.Helper;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Myword;

import java.io.IOException;

public class DeleteWordController {
    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField textField;

    @FXML
    public void initialize() {
        textField.setText(AppController.myNoteController.appController.curWord);
        cancelButton.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) quit();
            if (e.getCode() == KeyCode.ENTER) delete();
        });
        deleteButton.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) quit();
            if (e.getCode() == KeyCode.ENTER) delete();
        });
    }

    /**
     * Tương tác với textField.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) delete();
        if (e.getCode() == KeyCode.ESCAPE) quit();
    }

    /**
     * Xóa từ.
     */
    public void delete() {
        App.mydictionary.delete(textField.getText());
        AppController.myNoteController.appController.textField.setText("");
        AppController.myNoteController.loadListViewMyNote();
        AppController.myNoteController.clear();
        quit();
    }

    /**
     * Thoát cửa sổ.
     */
    public void quit() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Helper.playSound("/ui/sound/click.wav");
        stage.close();
    }
}
