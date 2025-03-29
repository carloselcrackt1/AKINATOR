package ec.edu.uees.akinatorproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        new Thread(() -> AkinatorMusic.startMusic()).start();
        scene = new Scene(loadFXML("loadingScreen"), 1240, 720);
        Image icon = new Image(getClass().getResourceAsStream("/ec/edu/uees/akinatorproject/assets/icon.png"));
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}