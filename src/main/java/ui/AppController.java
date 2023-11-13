package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Objects;

import javafx.scene.layout.BorderPane;

public class AppController {
    public DictionaryController dictionaryController;
    public String curWord = "";
    private int state;
    @FXML private BorderPane borderPane;
    @FXML public TextField textField;
    @FXML private Button findButton;
    @FXML private Button dictionaryButton;
    @FXML private Button googleTranslateButton;
    @FXML private Button gameButton;
    @FXML private Button myNoteButton;

    /**
     * khởi tạo.
     */
    @FXML
    public void initialize() {
        try {
            state = 1;
            loadTab("fxml/Dictionary.fxml");
        } catch (Exception e) {
            System.out.println("AppController error");
        }
    }

    /**
     * load tab từ điển.
     */
    public void loadDictionary() {
        if (state != 1) {
            state = 1;
            loadTab("fxml/Dictionary.fxml");
        }
    }

    /**
     * load tab google translate.
     */
    public void loadGoogleTranslate() {
        if (state != 2) {
            state = 2;
            loadTab("fxml/Translate.fxml");
        }
    }

    /**
     * load tab game.
     */
    public void loadGame() {
        if (state != 3) {
            state = 3;
            loadTab("fxml/Quiz.fxml");
        }
    }

    /**
     * load tab my note.
     */
    public void loadMyNote() {
        if (state != 4) {
            state = 4;
            loadTab("fxml/MyNote.fxml");
        }
    }

    public void loadTab(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            if (state == 1) dictionaryController = loader.getController();
            borderPane.setCenter(root);
        } catch (IOException e) {
            if (borderPane.getCenter() == null) System.out.println("borderPane is null");
        }
    }

    /**
     * load nghĩa của từ được chọn.
     */
    public void lookupWord() {
        if (state == 1) {
            dictionaryController.lookupWordDictionary();
        }
    }

    /**
     * load list từ đang tra.
     */
    public void loadListView() {
        if (state == 1) {
            dictionaryController.loadListViewDictionary();
        }
    }

    /**
     * tương tác với textField bằng bàn phím (Enter để chọn từ, Down để xuống listView).
     */
    public void keyPressTextField(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            dictionaryController.lookupWordDictionary();
        } else if (e.getCode() == KeyCode.DOWN) {
            dictionaryController.listView.requestFocus();
            dictionaryController.listView.getSelectionModel().select(0);
        }
    }
}