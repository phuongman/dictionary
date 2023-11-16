package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import services.Wordle.WordleManager;

import java.net.URL;
import java.util.ResourceBundle;

public class WordleController implements Initializable {
    private final String[] firstRow = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
    private final String[] secondRow = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
    private final String[] thirdRow = {"↵", "Z", "X", "C", "V", "B", "N", "M", "←"};
    private final int max_row = 6;
    private final int max_col = 5;
    private WordleManager wordleManager;

    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane keyBoardRow1;
    @FXML
    private GridPane keyBoardRow2;
    @FXML
    private GridPane keyBoardRow3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordleManager = new WordleManager();
        initGame();
        initGameUI();
    }

    public void initGame() {
        wordleManager.setWinningWord();
        wordleManager.reset();
    }

    public void initGameUI() {
        initGrid();
        initKeyBoard();
    }

    public void initGrid() {
        for(int i = 1; i <= max_row; i++) {
            for(int j = 1; j <= max_col; j++) {
                Label label = new Label();
                label.getStyleClass().add("defaultLabel");
                gridPane.add(label, j, i);
            }
        }
    }

    public void initKeyBoard() {
        for(int i = 0; i < firstRow.length; i++) {
            Label label = new Label(firstRow[i]);
            label.getStyleClass().add("defaultKeyboard");
            label.setText(firstRow[i]);
            keyBoardRow1.add(label, i, 1);
        }
        for(int i = 0; i < secondRow.length; i++) {
            Label label = new Label(secondRow[i]);
            label.getStyleClass().add("defaultKeyboard");
            label.setText(secondRow[i]);
            keyBoardRow2.add(label, i, 2);
        }
        for(int i = 0; i < thirdRow.length; i++) {
            Label label = new Label(thirdRow[i]);
            if (i == 0 || i == thirdRow.length - 1) label.getStyleClass().add("defaultKeyboardSymbol");
            else label.getStyleClass().add("defaultKeyboard");
            label.setText(thirdRow[i]);
            keyBoardRow3.add(label, i, 3);
        }
    }


    public void onActionHelpButton(ActionEvent actionEvent) {
    }

    public void onActionReplayButton(ActionEvent actionEvent) {
    }

    public void onActionexitButton(ActionEvent actionEvent) {
    }
}
