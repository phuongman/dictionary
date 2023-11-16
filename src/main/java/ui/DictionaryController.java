package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import model.Word;
import services.TextToSpeech;

import java.util.List;

public class DictionaryController {
    public AppController appController;

    @FXML private Label currentWordView;

    @FXML private Label labelView;

    @FXML public ListView<String> listView;

    @FXML private Button pronounceButton;

    @FXML private ScrollPane scrollView;

    @FXML
    public void initialize() {
        try {
            appController = App.loaderApp.getController();
        } catch (Exception e) {
            System.out.println("DictionaryController error");
        }
        pronounceButton.setVisible(false);
        loadListViewDictionary();
    }

    /**
     * load list từ đang tra.
     */
    public void loadListViewDictionary() {
        listView.getItems().clear();
        listView.setCellFactory(
                new javafx.util.Callback<>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        return new ListCell<String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    setFont(Font.font(15));
                                } else {
                                    setText(null);
                                }
                            }
                        };
                    }
                });
        List<String> loadWords = App.getDictionary().search(appController.textField.getText());
        listView.getItems().addAll(loadWords);
    }

    /**
     * load nghĩa của từ được chọn.
     */
    public void lookupWordDictionary() {
        appController.curWord = appController.textField.getText();
        Word word = App.getDictionary().lookup(appController.curWord);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appController.curWord);
        // if (word.getWordPronounce() != null) stringBuilder.append(" [").append(word.getWordPronounce()).append("]");
        if (word.getWordPronounce() != null) stringBuilder.append(" ").append(word.getWordPronounce());
        currentWordView.setText(stringBuilder.toString());
        pronounceButton.setVisible(true);

        stringBuilder = new StringBuilder();
        if (word.getWordExplain() != null) stringBuilder.append("Nghĩa: ").append(word.getWordExplain()).append("\n");
        if (word.getWordSynonym() != null) stringBuilder.append("Từ đồng nghĩa: ").append(word.getWordSynonym()).append("\n");
        if (word.getWordAntonym() != null) stringBuilder.append("Từ trái nghĩa: ").append(word.getWordAntonym()).append("\n");
        labelView.setText(stringBuilder.toString());
//        System.out.println(stringBuilder.toString());
    }

    /**
     * double click để chọn từ.
     */
    public void doubleClickWord(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
            String word = listView.getSelectionModel().getSelectedItem();
            appController.textField.setText(word);
            lookupWordDictionary();
        }
    }

    /**
     * tương tác với listView bằng bàn phím (Enter để chọn từ).
     */
    public void keyPressWord(KeyEvent e) {
        if (listView.getSelectionModel().getSelectedIndices().isEmpty()) return;
        if (e.getCode() == KeyCode.ENTER) {
            String word = listView.getSelectionModel().getSelectedItem();
            appController.textField.setText(word);
            lookupWordDictionary();
        } else if (e.getCode() == KeyCode.UP) {
            if (listView.getSelectionModel().getSelectedIndex() == 0) {
                appController.textField.requestFocus();
            }
        }
    }

    /**
     * phát âm từ.
     */
    public void pronounceWord() {
        TextToSpeech.speakEnglish(appController.curWord);
    }
}
