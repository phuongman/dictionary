package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Dictionary;
import model.Mydictionary;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            rootApp = loaderApp.load();
            Scene scene = new Scene(rootApp);
            String css = this.getClass().getResource("css/App.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.setTitle("Dictionary");
            stage.getIcons().add(new Image(getClass().getResource("/ui/icon/dictionaryIcon.png").toExternalForm()));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dictionary dictionary = new Dictionary();
    public static Mydictionary mydictionary = new Mydictionary();

    public static FXMLLoader loaderApp = new FXMLLoader(App.class.getResource("fxml/App.fxml"));
    public static Parent rootApp;
    public static void main(String[] args) {
        launch();
    }
}