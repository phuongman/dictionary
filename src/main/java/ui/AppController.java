package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

import model.Word;

public class AppController {
    public static String curWord = "";
    @FXML private ListView<String> listView;
    @FXML private TextField textField;
    @FXML private Label labelView;
    @FXML private ScrollPane scrollView;

    /**
     * khởi tạo.
     */
    @FXML
    private void initialize() {
        loadListView();
    }

    /**
     * load list từ đang tra.
     */
    public void loadListView() {
        listView.getItems().clear();
        List<String> loadWords = App.getDictionary().search(textField.getText());
        listView.getItems().addAll(loadWords);
    }

    /**
     * load nghĩa của từ được chọn.
     */
    public void lookupWord() {
        curWord = textField.getText();
        Word word = App.getDictionary().lookup(curWord);
        StringBuilder stringBuilder = new StringBuilder();
        if (word.getWordExplain() != null) stringBuilder.append("Nghĩa: ").append(word.getWordExplain()).append("\n");
        if (word.getWordPronounce() != null) stringBuilder.append("Phát âm: ").append(word.getWordPronounce()).append("\n");
        if (word.getWordType() != null) stringBuilder.append("Loại từ: ").append(word.getWordType()).append("\n");
        if (word.getWordSynonym() != null) stringBuilder.append("Từ đồng nghĩa: ").append(word.getWordSynonym()).append("\n");
        if (word.getWordAntonym() != null) stringBuilder.append("Từ trái nghĩa: ").append(word.getWordAntonym()).append("\n");
        labelView.setText(stringBuilder.toString());
//        System.out.println(stringBuilder.toString());
    }

    /**
     * tương tác với textField bằng bàn phím (Enter để chọn từ, Down để xuống listView).
     */
    public void keyPressTextField(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            lookupWord();
        } else if (e.getCode() == KeyCode.DOWN) {
            listView.requestFocus();
            listView.getSelectionModel().select(0);
        }
    }

    /**
     * tương tác với listView bằng bàn phím (Enter để chọn từ).
     */
    public void keyPressWord(KeyEvent e) {
        if (listView.getSelectionModel().getSelectedIndices().isEmpty()) return;
        if (e.getCode() == KeyCode.ENTER) {
            String word = listView.getSelectionModel().getSelectedItem();
            textField.setText(word);
            lookupWord();
        } else if (e.getCode() == KeyCode.UP) {
            if (listView.getSelectionModel().getSelectedIndex() == 0) {
                textField.requestFocus();
            }
        }
    }

    /**
     * double click để chọn từ.
     */
    public void doubleClickWord(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
            String word = listView.getSelectionModel().getSelectedItem();
            textField.setText(word);
            lookupWord();
        }
    }
}