package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static model.DatabaseQuery.*;
import model.Word;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Connect();
        Word res = lookUpWord("hello");
        System.out.println(res.getWordTarget());
        System.out.println(res.getWordType());
        System.out.println(res.getWordPronounce());
        System.out.println(res.getWordExplain());
        System.out.println(res.getWordSynonym());
        System.out.println(res.getWordAntonym());
        launch();
    }
}