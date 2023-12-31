package ui;

import help.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import model.Word;
import services.LearnWord;
import services.TextToSpeech;

public class LearnController {

    public Button fiveWordButton;
    public Button tenWordButton;
    public Button fifteenWordButton;
    public ListView listView;
    public Pane pane;
    public Label currentPronounceView;
    public AnchorPane PaneMeaning;
    public Label listWords;
    @FXML private TextFlow textFlowView;
    @FXML
    private Label currentWordView;
    @FXML private Button pronounceButton;
    @FXML private ScrollPane scrollView;


    private LearnWord learnWord = new LearnWord();

    public void onActionChooseNumWord(int cntLearnerWords) {
        PaneMeaning.setVisible(true);
        pane.setVisible(false);
        scrollView.setVisible(true);
        listWords.setVisible(true);
        learnWord.setCntLearnerWords(cntLearnerWords);
        learnWord.initWords();
        listView.getItems().clear();
        listView.setStyle("-fx-font-size: 15px;");
        listView.setPrefHeight(cntLearnerWords * 30);
        listWords.setLayoutY((620 - cntLearnerWords * 30) / 2 - 40);
        listView.setLayoutY((620 - cntLearnerWords * 30) / 2);
        for(int i = 0; i < learnWord.getWordsToLearn().size(); i++) {
            listView.getItems().add(learnWord.getWordsToLearn().get(i).getWordTarget());
        }
        listView.setVisible(true);
        pronounceButton.setVisible(false);

    }
    public void onActionFive(ActionEvent actionEvent) {
        Helper.playSound("/ui/sound/click.wav");
        onActionChooseNumWord(5);

    }

    public void onActionTen(ActionEvent actionEvent) {
        Helper.playSound("/ui/sound/click.wav");
        onActionChooseNumWord(10);

    }

    public void onActionFifteen(ActionEvent actionEvent) {
        Helper.playSound("/ui/sound/click.wav");
        onActionChooseNumWord(15);

    }

    public void lookupWord(String word) {
        for(int j = 0; j < learnWord.getWordsToLearn().size(); j++) {
            Word curWord = learnWord.getWordsToLearn().get(j);
            if (curWord.getWordTarget().equals(word)) {

                currentWordView.setText(word);
                if (curWord.getWordPronounce() != null) currentPronounceView.setText(" " + curWord.getWordPronounce());
                pronounceButton.setVisible(true);

                StringBuilder stringBuilder = new StringBuilder();
                if (curWord.getWordExplain() != null) stringBuilder.append(curWord.getWordExplain()).append("\n");
                if (curWord.getWordSynonym() != null)
                    stringBuilder.append("*Từ đồng nghĩa\n").append("- " + curWord.getWordSynonym()).append("\n");
                if (curWord.getWordAntonym() != null)
                    stringBuilder.append("*Từ trái nghĩa\n").append("- " + curWord.getWordAntonym()).append("\n");

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
                        for (int t = 0; t < tmpString.length(); t++)
                            if (tmpString.charAt(t) == '+') tmpString.setCharAt(t, ':');
                    }

                    tmp.setText(tmpString.toString());
                    textFlowView.getChildren().add(tmp);
                    // System.out.print(tmp.getText());
                }
                textFlowView.setPrefWidth(760);
                textFlowView.setLineSpacing(10);
                scrollView.setContent(textFlowView);
            }
        }
    }
    public void doubleClickWord(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() >= 1) {
            String word = (String) listView.getSelectionModel().getSelectedItem();
            lookupWord(word);
        }
    }

    public void pronounceWord() {
            TextToSpeech.speakEnglish(currentWordView.getText());
        }

    public void keyPressWord(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            String word = (String) listView.getSelectionModel().getSelectedItem();
            lookupWord(word);
        }
    }

    public void onMouseEnteredFive(MouseEvent mouseEvent) {
        fiveWordButton.setStyle(
                "-fx-background-color: #6A7EE4;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 30 30 30 30;"
        );
    }

    public void onMouseExitedFive(MouseEvent mouseEvent) {
        fiveWordButton.setStyle(
                "-fx-background-color: #455ede;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 30 30 30 30;"
        );
    }

    public void onMouseEnteredTen(MouseEvent mouseEvent) {
        tenWordButton.setStyle(
                "-fx-background-color: #6A7EE4;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 30 30 30 30;"
        );
    }

    public void onMouseExitedTen(MouseEvent mouseEvent) {
        tenWordButton.setStyle(
                "-fx-background-color: #455ede;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 30 30 30 30;"
        );
    }

    public void onMouseEnteredFifteen(MouseEvent mouseEvent) {
        fifteenWordButton.setStyle(
                "-fx-background-color: #6A7EE4;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: darkgray;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 30 30 30 30;"
        );
    }

    public void onMouseExitedFifteen(MouseEvent mouseEvent) {
        fifteenWordButton.setStyle(
                "-fx-background-color: #455ede;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 20px; " +
                        "-fx-background-radius: 30 30 30 30;"
        );
    }
}
