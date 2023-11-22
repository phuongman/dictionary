package ui;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import services.Wordle;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import help.Helper;

public class WordleController implements Initializable {
    private final String[] firstRow = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
    private final String[] secondRow = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
    private final String[] thirdRow = {"↵", "Z", "X", "C", "V", "B", "N", "M", "←"};
    private final int max_row = 6;
    private final int max_col = 5;

    private int currentRow = 1;
    private int currentCol = 1;
    private Wordle wordleManager;

    public AppController appController;

    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane keyBoardRow1;
    @FXML
    private GridPane keyBoardRow2;
    @FXML
    private GridPane keyBoardRow3;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Pane helpPane;
    @FXML
    private Button homeButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appController = App.loaderApp.getController();
        wordleManager = new Wordle();
        initGame();
        initGameUI();

//        System.out.println("WordleController");
        Platform.runLater(() -> gridPane.requestFocus());
    }

    /**
     * Về màn hình chính.
     */
    public void quitWordle() {
        Helper.playSound("/ui/sound/click.wav");
        appController.loadTab("fxml/ChooseGame.fxml");
    }

    public void initGame() {
        wordleManager.setWinningWord();
        wordleManager.reset();
    }

    public void initGameUI() {
        initGrid();
        initKeyBoard();
        Platform.runLater(() -> gridPane.requestFocus());

    }

    public void initGrid() {
        for(int i = 1; i <= max_row; i++) {
            for(int j = 1; j <= max_col; j++) {
                Label label = new Label();
                label.getStyleClass().add("defaultLabel");
                gridPane.add(label, j, i);
            }
        }
        gridPane.requestFocus();

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


    public void onActionHelpButton() {
        Helper.playSound("/ui/sound/click.wav");
        borderPane.setDisable(true);
        helpPane.setVisible(true);
    }

    private void resetGameUI() {
        gridPane.requestFocus();
        Label label;
        for (Node con : gridPane.getChildren()) {
            if (con != null) {
                label = (Label) con;
                label.setText("");
                label.getStyleClass().clear();
                label.getStyleClass().add("defaultLabel");

            }
        }

        for (Node con : keyBoardRow1.getChildren()) {
            if (con != null) {
                label = (Label) con;
                label.getStyleClass().clear();
                label.getStyleClass().add("defaultKeyboard");
            }
        }

        for (Node con : keyBoardRow2.getChildren()) {
            if (con != null) {
                label = (Label) con;
                label.getStyleClass().clear();
                label.getStyleClass().add("defaultKeyboard");
            }
        }

        for (Node con : keyBoardRow3.getChildren()) {
            if (con != null) {
                label = (Label) con;
                label.getStyleClass().clear();
                if (label.getText().equals("↵") || label.getText().equals("←")) {
                    label.getStyleClass().add("defaultKeyboardSymbol");
                } else {
                    label.getStyleClass().add("defaultKeyboard");
                }
            }
        }
        currentRow = 1;
        currentCol = 1;
    }
    public void onActionReplayButton() {
        Helper.playSound("/ui/sound/click.wav");
        resetGameUI();
        wordleManager.rePlay();
    }

    public void onActionExitButton() {
        Helper.playSound("/ui/sound/click.wav");
        wordleManager.reset();
        System.exit(0);
    }

    public void onActionGoBack(ActionEvent actionEvent) {
        Helper.playSound("/ui/sound/click.wav");
        borderPane.setDisable(false);
        helpPane.setVisible(false);
        gridPane.requestFocus();

    }

    public void setLabelGrid(GridPane gridPane, int row, int col, String word) {
        Label label = getLabelGrid(gridPane, row, col);
        if (label != null) {
            label.setText(word.toUpperCase());
        }
    }

    public Label getLabelGrid(GridPane gridPane, int row, int col) {
        for (int i = 0; i < gridPane.getChildren().size(); i++) {
            if (GridPane.getRowIndex(gridPane.getChildren().get(i)) == row && GridPane.getColumnIndex(gridPane.getChildren().get(i)) == col) {
                return (Label) gridPane.getChildren().get(i);
            }
        }
        return null;
    }

    public String getLabelGridText(GridPane gridPane, int row, int col) {
        Label label = getLabelGrid(gridPane, row, col);
        if (label != null) {
            return label.getText().toLowerCase();
        }
        return null;
    }

    public Label getLabelKeyboard(GridPane gridPane, String word) {
        for (int i = 0; i < gridPane.getChildren().size(); i++) {
            if (((Label) gridPane.getChildren().get(i)).getText().equalsIgnoreCase(word)) {
                return (Label) gridPane.getChildren().get(i);
            }
        }
        return null;
    }

    public void setLabelStyle(GridPane gridPane, int row, int col, String style) {
        Label label = getLabelGrid(gridPane, row, col);
        if (label != null) {
            label.getStyleClass().add(style);
        }
    }

    public void clearLabelStyle(GridPane gridPane, int row, int col) {
        Label label = getLabelGrid(gridPane, row, col);
        if (label != null) {
            label.getStyleClass().clear();
        }
    }

    public void updateRowColor(GridPane gridPane, int row) {
        for (int i = 1; i <= max_col; i++) {
            Label label = getLabelGrid(gridPane, row, i);
            String style;
            String text = String.valueOf(label.getText().charAt(0)).toLowerCase();
            if (String.valueOf(wordleManager.getWinningWord().charAt(i - 1)).equals(text)) {
                style = "correctCharacter";
            } else if (wordleManager.getWinningWord().contains(text)) {
                style = "wrongPositionCharacter";
            } else {
                style = "wrongCharacter";
            }
            RotateTransition rt = new RotateTransition(Duration.millis(500), label);
            // lật theo chiều dọc
            rt.setAxis(new Point3D(1, 0, 0));
            rt.setFromAngle(0);
            rt.setToAngle(-45); // Lật lên 180 độ
            rt.setOnFinished(e -> {
                label.getStyleClass().clear();
                label.getStyleClass().add(style);
            });
            RotateTransition rt2 = new RotateTransition(Duration.millis(500), label);
            rt.setAxis(new Point3D(1, 0, 0));
            rt2.setFromAngle(45); // Đặt góc quay là 180 độ
            rt2.setToAngle(0);
            new SequentialTransition(rt, rt2).play();
        }
    }

    private String getWordFromGrid(GridPane gridPane) {
        StringBuilder word = new StringBuilder();
        for (int i = 1; i <= max_col; i++) {
            Label label = getLabelGrid(gridPane, currentRow, i);
            word.append(label.getText().toLowerCase());
        }
        return word.toString();
    }

    private boolean inGrid(GridPane keyBoardRow1, String character) {
        for (int i = 0; i < keyBoardRow1.getChildren().size(); i++) {
            if (((Label) keyBoardRow1.getChildren().get(i)).getText().equalsIgnoreCase(character)) {
                return true;
            }
        }
        return false;
    }
    public void updateKeyboardColor(GridPane gridPane, GridPane keyBoardRow1, GridPane keyBoardRow2, GridPane keyBoardRow3) {
        String text = getWordFromGrid(gridPane);
        for (int i = 1; i <= max_col; i++) {
            Label keyboardLabel = new Label();
            String style;
            String character = String.valueOf(text.charAt(i - 1));
            String characterWin = String.valueOf(wordleManager.getWinningWord().charAt(i - 1));

            if (inGrid(keyBoardRow1, character)) {
                keyboardLabel = getLabelKeyboard(keyBoardRow1, character);
            } else if (inGrid(keyBoardRow2, character)) {
                keyboardLabel = getLabelKeyboard(keyBoardRow2, character);
            } else if (inGrid(keyBoardRow3, character)) {
                keyboardLabel = getLabelKeyboard(keyBoardRow3, character);
            }

            if (character.equalsIgnoreCase(characterWin)) {
                style = "keyboardCorrectCharacter";
            } else if (wordleManager.getWinningWord().contains(character)) {
                style = "keyboardPresentCharacter";
            } else {
                style = "keyboardWrongCharacter";
            }

            if (keyboardLabel != null) {
                keyboardLabel.getStyleClass().clear();
                keyboardLabel.getStyleClass().add(style);
            }
        }

    }

    public void onKeyPressed(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2,
                             GridPane keyboardRow3, KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            onBackSpace(gridPane);
        } else if (keyEvent.getCode() == KeyCode.ENTER) {
            onEnter(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
        } else if (keyEvent.getCode().isLetterKey()) {
            onLetter(gridPane, keyEvent);
        }

    }

    private void onLetter(GridPane gridPane, KeyEvent keyEvent) {
        if (Objects.equals(getLabelGridText(gridPane, currentRow, currentCol), "")) {
            String character = keyEvent.getText().toUpperCase();
            setLabelGrid(gridPane, currentRow, currentCol, character);
            Label label = getLabelGrid(gridPane, currentRow, currentCol);
            ScaleTransition ft = new ScaleTransition(Duration.millis(100), label);
            ft.fromXProperty().setValue(1);
            ft.fromYProperty().setValue(1);
            ft.toXProperty().setValue(1.1);
            ft.toYProperty().setValue(1.1);
            ScaleTransition ft2 = new ScaleTransition(Duration.millis(100), label);
            ft2.fromXProperty().setValue(1.1);
            ft2.fromYProperty().setValue(1.1);
            ft2.toYProperty().setValue(1);
            ft2.toXProperty().setValue(1);
            new SequentialTransition(ft, ft2).play();
            setLabelStyle(gridPane, currentRow, currentCol, "currentLabel");
            if (currentCol < max_col) {
                currentCol++;
            }
        }
    }

    public void onBackSpace(GridPane gridPane) {
        if ((currentCol == 1 || currentCol == max_col) && !Objects.equals(getLabelGridText(gridPane, currentRow, currentCol), "")) {
            clearLabelStyle(gridPane, currentRow, currentCol);
            setLabelGrid(gridPane, currentRow, currentCol, "");
            setLabelStyle(gridPane, currentRow, currentCol, "defaultLabel");
        } else if ((currentCol > 1 && currentCol < max_col)
                || (max_col == currentCol && Objects.equals(getLabelGridText(gridPane, currentRow, currentCol), ""))) {
            currentCol--;
            clearLabelStyle(gridPane, currentRow, currentCol);
            setLabelGrid(gridPane, currentRow, currentCol, "");
            setLabelStyle(gridPane, currentRow, currentCol, "defaultLabel");
        }
    }

    private void showIsNotWord() {
        Pane newPane = new Pane();
        newPane.getStyleClass().add("isNotWord");
        newPane.setPrefSize(300, 100);
        newPane.setLayoutX(150);
        newPane.setLayoutY(170);
        Label label = new Label("This is not a word");
        label.getStyleClass().add("isNotWordLabel");
        label.setLayoutX(50);
        label.setLayoutY(50);
        newPane.getChildren().add(label);
        borderPane.getChildren().add(newPane);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), newPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        ft.setOnFinished(e -> {
            FadeTransition ft2 = new FadeTransition(Duration.millis(1000), newPane);
            ft2.setFromValue(1);
            ft2.setToValue(0);
            ft2.play();
            ft2.setOnFinished(e2 -> {
                borderPane.getChildren().remove(newPane);
            });
        });
    }

    private void showResultGame(boolean b) {
        Pane newPane = new Pane();
        newPane.getStyleClass().add("isNotWord");
        newPane.setPrefSize(300, 100);
        newPane.setLayoutX(150);
        newPane.setLayoutY(170);
        Label label = new Label();
        if (b) {
            Helper.playSound("/ui/sound/win.wav");
            label.setText("You win");
            label.getStyleClass().add("winWordLabel");
        } else {
            Helper.playSound("/ui/sound/lose.wav");
            label.setText("You lose \n The word is " + wordleManager.getWinningWord().toUpperCase() + "");
            label.getStyleClass().add("loseWordLabel");
        }
        label.setLayoutX(50);
        label.setLayoutY(50);
        newPane.getChildren().add(label);
        borderPane.getChildren().add(newPane);
        FadeTransition ft = new FadeTransition(Duration.millis(3000), newPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        ft.setOnFinished(e -> {
            FadeTransition ft2 = new FadeTransition(Duration.millis(3000), newPane);
            ft2.setFromValue(1);
            ft2.setToValue(0);
            ft2.play();
            ft2.setOnFinished(e2 -> {
                borderPane.getChildren().remove(newPane);
            });
        });
    }
    public void onEnter(GridPane gridPane, GridPane keyboardRow1, GridPane keyboardRow2, GridPane keyboardRow3) {
        if (currentRow <= max_row && max_col == currentCol) {
            String guess = getWordFromGrid(gridPane);
            if (wordleManager.checkWin(guess)) {
                updateKeyboardColor(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                updateRowColor(gridPane, currentRow);
                showResultGame(true);

            } else if (wordleManager.checkWord(guess)) {
                updateRowColor(gridPane, currentRow);
                updateKeyboardColor(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
                if (currentRow < max_row) {
                    currentRow++;
                    currentCol = 1;
                } else {
                    showResultGame(false);
                }
            } else {
                showIsNotWord();
            }
        }
    }


    public void onKeyPressed(KeyEvent keyEvent) {
        onKeyPressed(gridPane, keyBoardRow1, keyBoardRow2, keyBoardRow3, keyEvent);
    }
}
