package ec.edu.uees.akinatorproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoadingScreen implements Initializable {    
    @FXML
    private ImageView loadingLogo;
    
    public void switchToMainScreen(){
        try {
            App.setRoot("mainScreen");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Thread t1 = new Thread(new ThreadLoading(this));
            t1.start();
        });
        
    }
        
}
