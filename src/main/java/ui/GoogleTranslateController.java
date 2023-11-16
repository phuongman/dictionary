package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import services.Translator;
import services.TextToSpeech;

import java.util.Timer;
import java.util.TimerTask;

public class GoogleTranslateController {
    @FXML private Label leftLabel;
    @FXML private Label rightLabel;
    @FXML private TextArea leftTextArea;
    @FXML private TextArea rightTextArea;
    @FXML private Button leftPronounceButton;
    @FXML private Button rightPronounceButton;
    @FXML private Button swapButton;

    private Timer timer = new Timer();

    /**
     * Đổi ngôn ngữ.
     */
    public void swapLanguage() {
        String left = leftLabel.getText();
        String right = rightLabel.getText();
        leftLabel.setText(right);
        rightLabel.setText(left);

        String temp = leftTextArea.getText();
        leftTextArea.setText(rightTextArea.getText());
        rightTextArea.setText(temp);
    }

    /**
     * Dịch ngôn ngữ trái sang ngôn ngữ phải.
     */
    public void translate() {
        String[] save = leftTextArea.getText().split("\n");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < save.length; i++) {
            if (i != 0) {
                result.append("\n");
            }
            System.out.println(save[i]);
            if (leftLabel.getText().equals("Tiếng Anh")) result.append(Translator.translateEnglishToVietnamese(save[i]));
            else result.append(Translator.translateVietnameseToEnglish(save[i]));
        }

        rightTextArea.setText(result.toString());
    }

    /**
     * Tự động dịch.
     */
    public void autoTranslate() {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                translate();
            }
        }, 200);
    }

    /**
     * speak left.
     */
    public void speakLeft() {
        if (leftLabel.getText().equals("Tiếng Anh")) {
            TextToSpeech.speakEnglish(leftTextArea.getText());
        } else {
            TextToSpeech.speakVietnamese(leftTextArea.getText());
        }
    }

    /**
     * speak right.
     */
    public void speakRight() {
        if (rightLabel.getText().equals("Tiếng Anh")) {
            TextToSpeech.speakEnglish(rightTextArea.getText());
        } else {
            TextToSpeech.speakVietnamese(rightTextArea.getText());
        }
    }
}
