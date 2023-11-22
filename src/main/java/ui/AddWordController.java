package ui;

import help.Helper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Myword;

public class AddWordController {
    @FXML private Button cancelButton;
    @FXML private TextField textField;

    /**
     * Tương tác với textField.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) ok();
        if (e.getCode() == KeyCode.ESCAPE) quit();
    }

    /**
     * Thoát cửa sổ.
     */
    public void quit() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Helper.playSound("/ui/sound/click.wav");
        stage.close();
    }

    /**
     * Thêm từ vào my note.
     */
    public void ok() {
        if (textField.getText().isEmpty()) quit();
        // System.out.println("them tu :" + textField.getText());

        Myword newWord = App.mydictionary.lookupMyWord(textField.getText());

        if (newWord.getWord() == null) {
            System.out.println("Dang them tu :" + textField.getText());
            App.mydictionary.add(new Myword(textField.getText(), ""));
        } else {
            System.out.println("mynote đã có " + textField.getText());
        }
        AppController.myNoteController.appController.textField.setText("");
        AppController.myNoteController.loadListViewMyNote();
        AppController.myNoteController.appController.textField.setText(textField.getText());
        AppController.myNoteController.lookupWordMyNote();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        AppController.myNoteController.editWord();
    }
}
