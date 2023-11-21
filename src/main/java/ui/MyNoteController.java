package ui;

import Help.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Myword;
import model.Word;
import services.TextToSpeech;

import java.io.IOException;
import java.util.List;

public class MyNoteController {
    public AppController appController;

    @FXML private Label currentWordViewMyNote;
    @FXML public ListView<String> listViewMyNote;
    @FXML private Button pronounceButtonMyNote;
    @FXML private Button saveButton;
    @FXML private TextArea textArea;
    @FXML private ScrollPane scrollView;
    @FXML private TextFlow textFlowView;

    public boolean editing = false;

    @FXML
    public void initialize() {
        try {
            appController = App.loaderApp.getController();
        } catch (Exception e) {
            System.out.println("MyNoteController error");
        }
        pronounceButtonMyNote.setVisible(false);
        loadListViewMyNote();

        textArea.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV)  // focus lost
                if (editing) endEditWord();
        });
        textArea.setOnKeyPressed(e -> {
            if (e.isControlDown() && e.getCode() == KeyCode.S) {
                endEditWord();
                appController.textField.requestFocus();
            }
        });

        Tooltip tooltip = new Tooltip("Save");
        tooltip.setShowDelay(javafx.util.Duration.millis(100));
        tooltip.setFont(Font.font(15));
        tooltip.setOnShown(e -> {
            Bounds bounds = saveButton.localToScreen(saveButton.getBoundsInLocal());
            tooltip.setX(bounds.getMinX() - tooltip.getWidth() + 10);
            tooltip.setY(bounds.getCenterY() - tooltip.getHeight() / 2);
        });
        saveButton.setTooltip(tooltip);
    }

    /**
     * clear mynote view.
     */
    public void clear() {
        currentWordViewMyNote.setText("");
        textArea.setText("");
        textFlowView.getChildren().clear();
        textArea.setDisable(true);
        textFlowView.setDisable(true);
        pronounceButtonMyNote.setVisible(false);
    }

    /**
     * kiểm tra xem từ đã có trong mynote chưa.
     */
    public boolean checkExist() {
        appController.curWord = appController.textField.getText();
        Myword word = App.mydictionary.lookup(appController.curWord);
        if (word.getWord() == null) return false;
        return true;
    }

    /**
     * load list từ đang tra.
     */
    public void loadListViewMyNote() {
        listViewMyNote.getItems().clear();
        listViewMyNote.setCellFactory(
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
        List<String> loadWords = App.mydictionary.search(appController.textField.getText());
//        System.out.println(appController.textField.getText());
//        System.out.println(loadWords);
        listViewMyNote.getItems().addAll(loadWords);
    }

    /**
     * load nghĩa của từ được chọn.
     */
    public void lookupWordMyNote() {
        if (appController.textField.getText() == null || appController.textField.getText().isEmpty()) return;
        appController.curWord = appController.textField.getText();
        Myword word = App.mydictionary.lookup(appController.curWord);
        if (word.getWord() == null) {
            clear();
            return;
        }

        currentWordViewMyNote.setText(appController.curWord);
        textArea.setDisable(true);
        textArea.setVisible(false);
        textFlowView.setDisable(false);
        textFlowView.setVisible(true);
        pronounceButtonMyNote.setVisible(true);

        textFlowView = new TextFlow();
        String[] lines = word.getMeaning().split("\n");
        for (int i = 0; i < lines.length; i++) {
            Text tmp = new Text();
            StringBuilder tmpString = new StringBuilder(lines[i]);
            if (i < lines.length - 1) tmpString.append("\n");
            tmp.setFont(Font.font(20));

            // Danh từ, động từ, ..., từ đồng nghĩa, từ trái nghĩa
            if (!tmpString.isEmpty() && tmpString.charAt(0) == '*') {
                tmp.setFont(Font.font(null, FontWeight.BOLD, 20));
                tmpString.deleteCharAt(0);
                tmpString.setCharAt(0, Character.toUpperCase(tmpString.charAt(0)));
            }

            // Ví dụ
            if (!tmpString.isEmpty() && tmpString.charAt(0) == '=') {
                tmp.setFont(Font.font(null, FontPosture.ITALIC, 20));
                tmp.setFill(Paint.valueOf("#455ede"));
                tmpString.deleteCharAt(0);
            }

            tmp.setText(tmpString.toString());
            textFlowView.getChildren().add(tmp);
        }
        textFlowView.setPrefWidth(660);
        textFlowView.setLineSpacing(10);
        scrollView.setContent(textFlowView);
    }

    /**
     * double click để chọn từ.
     */
    public void doubleClickWordMyNote(MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() >= 1) {
            String word = listViewMyNote.getSelectionModel().getSelectedItem();
            appController.textField.setText(word);
            lookupWordMyNote();
        }
    }

    /**
     * tương tác với listView bằng bàn phím (Enter để chọn từ).
     */
    public void keyPressWordMyNote(KeyEvent e) {
        if (listViewMyNote.getSelectionModel().getSelectedIndices().isEmpty()) return;
        if (e.getCode() == KeyCode.ENTER) {
            String word = listViewMyNote.getSelectionModel().getSelectedItem();
            appController.textField.setText(word);
            lookupWordMyNote();
        } else if (e.getCode() == KeyCode.UP) {
            if (listViewMyNote.getSelectionModel().getSelectedIndex() == 0) {
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

    /**
     * Thêm từ vào mynote.
     */
    public void addWord(ActionEvent e) {
        Helper.playSound("/ui/sound/click.wav");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AddWord.fxml"));
            Parent root = loader.load();
            Stage addStage = new Stage();
            Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            addStage.initOwner(appStage);

            addStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);

            addStage.setResizable(false);
            addStage.setScene(scene);
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.show();
        } catch (IOException exception) {
            System.out.println("addWord error");
        }
    }

    /**
     * Sửa từ trong mynote.
     */
    public void editWord() {
        Helper.playSound("/ui/sound/click.wav");
        textFlowView.setDisable(true);
        textFlowView.setVisible(false);
        textArea.setDisable(false);
        textArea.setVisible(true);
        textArea.setEditable(true);
        textArea.requestFocus();
        editing = true;

        if (appController.textField.getText() == null || appController.textField.getText().isEmpty()) return;
        appController.curWord = appController.textField.getText();
        Myword word = App.mydictionary.lookup(appController.curWord);
        currentWordViewMyNote.setText(appController.curWord);
        textArea.setText(word.getMeaning());
    }

    /**
     * Kết thúc sửa từ trong mynote.
     */
    public void endEditWord() {
        if (!editing) return;
        Myword newWord = new Myword(appController.curWord, textArea.getText());

        App.mydictionary.edit(newWord, appController.curWord);

        textArea.setEditable(false);
        textArea.setDisable(true);
        textArea.setVisible(false);
        editing = false;
        lookupWordMyNote();
    }

    /**
     *
     */
    public void saveWord() {
        Helper.playSound("/ui/sound/click.wav");
        if (!editing) return;
        endEditWord();
    }

    /**
     * Xóa từ trong mynote.
     */
    public void deleteWord(ActionEvent e) {
        Helper.playSound("/ui/sound/click.wav");
        if (!checkExist()) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/DeleteWord.fxml"));
            Parent root = loader.load();
            Stage addStage = new Stage();
            Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            addStage.initOwner(appStage);

            addStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);

            addStage.setResizable(false);
            addStage.setScene(scene);
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.show();
        } catch (IOException exception) {
            System.out.println("addWord error");
        }
    }

}
