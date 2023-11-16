package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import model.Word;
import services.TextToSpeech;

import java.util.List;

public class DictionaryController {
    public AppController appController;

    @FXML private Label currentWordView;
    @FXML private Label currentPronounceView;
    @FXML public ListView<String> listView;
    @FXML private Button pronounceButton;
    @FXML private ScrollPane scrollView;
    @FXML private TextFlow textFlowView;


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

        currentWordView.setText(appController.curWord);
        if (word.getWordPronounce() != null) currentPronounceView.setText(" " + word.getWordPronounce());
        pronounceButton.setVisible(true);

        StringBuilder stringBuilder = new StringBuilder();
        if (word.getWordExplain() != null) stringBuilder.append(word.getWordExplain()).append("\n");
        if (word.getWordSynonym() != null) stringBuilder.append("*Từ đồng nghĩa\n").append(word.getWordSynonym()).append("\n");
        if (word.getWordAntonym() != null) stringBuilder.append("*Từ trái nghĩa\n").append(word.getWordAntonym()).append("\n");

        textFlowView = new TextFlow();
        String[] lines = stringBuilder.toString().split("\n");
        for (int i = 0; i < lines.length; i++) {
            Text tmp = new Text();
            StringBuilder tmpString = new StringBuilder(lines[i]);
            if (i < lines.length - 1) tmpString.append("\n");
            tmp.setFont(Font.font(20));

            // Danh từ, động từ, ..., từ đồng nghĩa, từ trái nghĩa
            if (!tmpString.isEmpty() && tmpString.charAt(0) == '*') {
//                tmp.setStyle("-fx-font-weight: bold");
                tmp.setFont(Font.font(null, FontWeight.BOLD, 20));
                tmpString.deleteCharAt(0);
                tmpString.setCharAt(0, Character.toUpperCase(tmpString.charAt(0)));
            }

            // Ví dụ
            if (!tmpString.isEmpty() && tmpString.charAt(0) == '=') {
                tmp.setFont(Font.font(null, FontPosture.ITALIC, 20));
                //tmp.setFill(Paint.valueOf("#639bff"));
                tmp.setFill(Paint.valueOf("#455ede"));
                tmpString.deleteCharAt(0);
                tmpString.insert(0, "Ex: ");
                for (int j = 0; j < tmpString.length(); j++) if (tmpString.charAt(j) == '+') tmpString.setCharAt(j, ':');
            }

            tmp.setText(tmpString.toString());
            textFlowView.getChildren().add(tmp);
             System.out.print(tmp.getText());
        }
        textFlowView.setPrefWidth(760);
        scrollView.setContent(textFlowView);
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
