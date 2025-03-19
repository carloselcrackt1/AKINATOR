package ec.edu.uees.akinatorproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoadingScreen implements Initializable {    
    @FXML
    private ImageView loadingLogo;
    
    public void switchToMainScreen(){
        return;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Thread t1 = new Thread(new ThreadLoading(this));
        t1.start();
        TranslateTransition logoAnimation = new TranslateTransition();
        logoAnimation.setNode(loadingLogo);
        logoAnimation.setDuration(Duration.millis(500));
        logoAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        logoAnimation.setByY(-20); 
        logoAnimation.setAutoReverse(true);
        logoAnimation.play();
    }
        
}
