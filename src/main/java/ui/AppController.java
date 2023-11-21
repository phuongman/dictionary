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
    public static DictionaryController dictionaryController;
    public static MyNoteController myNoteController;
    public String curWord = "";
    private int state;
    @FXML private BorderPane borderPane;
    @FXML public TextField textField;
    @FXML private Button findButton;
    @FXML private Button dictionaryButton;
    @FXML private Button googleTranslateButton;
    @FXML private Button gameButton;
    @FXML private Button myNoteButton;
    @FXML private Button learnButton;
    @FXML private Button currentButton = null;

    /**
     * khởi tạo.
     */
    @FXML
    public void initialize() {
        try {
            loadDictionary();
        } catch (Exception e) {
            System.out.println("AppController error");
        }
    }

    /**
     * load tab.
     */
    public void loadTab(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            if (state == 1) dictionaryController = loader.getController();
            if (state == 5) myNoteController = loader.getController();
            borderPane.setCenter(root);
        } catch (IOException e) {
            if (borderPane.getCenter() == null) System.out.println("borderPane is null");
        }
    }

    /**
     * load tab từ điển.
     */
    public void loadDictionary() {
        if (currentButton != null) currentButton.getStyleClass().remove("button-clicked");
        currentButton = dictionaryButton;
        currentButton.getStyleClass().add("button-clicked");
        if (state == 5) textField.setText("");
        if (state != 1) {
            state = 1;
            loadTab("fxml/Dictionary.fxml");
        }
    }

    /**
     * load tab google translate.
     */
    public void loadGoogleTranslate() {
        if (currentButton != null) currentButton.getStyleClass().remove("button-clicked");
        currentButton = googleTranslateButton;
        currentButton.getStyleClass().add("button-clicked");
        if (state == 5) textField.setText("");
        if (state != 2) {
            state = 2;
            loadTab("fxml/Translate.fxml");
        }
    }

    /**
     * load tab learn.
     */
    public void loadLearn() {
        if (currentButton != null) currentButton.getStyleClass().remove("button-clicked");
        currentButton = learnButton;
        currentButton.getStyleClass().add("button-clicked");
        if (state == 5) textField.setText("");
        if (state != 3) {
            state = 3;
            loadTab("fxml/Learn.fxml");
        }
    }

    /**
     * load tab game.
     */
    public void loadGame() {
        if (currentButton != null) currentButton.getStyleClass().remove("button-clicked");
        currentButton = gameButton;
        currentButton.getStyleClass().add("button-clicked");
        if (state == 5) textField.setText("");
        if (state != 4) {
            state = 4;
            loadTab("fxml/Wordle.fxml");
        }
    }

    /**
     * load tab my note.
     */
    public void loadMyNote() {
        if (currentButton != null) currentButton.getStyleClass().remove("button-clicked");
        currentButton = myNoteButton;
        currentButton.getStyleClass().add("button-clicked");
        textField.setText("");
        if (state != 5) {
            state = 5;
            loadTab("fxml/MyNote.fxml");
        }
    }

    /**
     * load nghĩa của từ được chọn.
     */
    public void lookupWord() {
        if (state == 2 || state == 3 || state == 4) {
            loadDictionary();
        }
        if (state == 1) {
            dictionaryController.lookupWordDictionary();
        }
        if (state == 5) {
            myNoteController.lookupWordMyNote();
        }
    }

    /**
     * load list từ đang tra.
     */
    public void loadListView() {
        if (state == 1) {
            dictionaryController.loadListViewDictionary();
        }
        if (state == 5) {
            myNoteController.loadListViewMyNote();
        }
    }

    /**
     * tương tác với textField bằng bàn phím (Enter để chọn từ, Down để xuống listView).
     */
    public void keyPressTextField(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            lookupWord();
        } else if (e.getCode() == KeyCode.DOWN) {
            if (state == 1) {
                loadDictionary();
                dictionaryController.listView.requestFocus();
                dictionaryController.listView.getSelectionModel().select(0);
            } else if (state == 5) {
                loadMyNote();
                myNoteController.listViewMyNote.requestFocus();
                myNoteController.listViewMyNote.getSelectionModel().select(0);
            }
        }
    }
}