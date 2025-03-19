package ec.edu.uees.akinatorproject;

import ec.edu.uees.akinatorproject.scenario.ThreadFog;
import ec.edu.uees.akinatorproject.scenario.ThreadMainAnimation;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainScreenController implements Initializable{
    @FXML
    private ImageView Akinator;
    @FXML
    private ImageView fog1;
    @FXML
    private ImageView fog2;
    @FXML
    private ImageView fog3;
    
    public void animFog(ImageView fogToAnimate){
        fogToAnimate.setTranslateX(-800);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(fog1);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDuration(Duration.millis(8000));
        translate.setByX(600);
        translate.play();
        
        FadeTransition fade = new FadeTransition();
        fade.setNode(fog1);
        fade.setDuration(Duration.millis(4000));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(fog2);
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setDuration(Duration.millis(8000));
        translate2.setByX(-700);
        translate2.setDelay(Duration.millis(2000));
        translate2.play();
        
        FadeTransition fade2 = new FadeTransition();
        fade2.setNode(fog2);
        fade2.setDuration(Duration.millis(4000));
        fade2.setCycleCount(TranslateTransition.INDEFINITE);
        fade2.setAutoReverse(true);
        fade2.setInterpolator(Interpolator.LINEAR);
        fade2.setDelay(Duration.millis(2000));
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.play();
        
        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(fog3);
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
        translate3.setDuration(Duration.millis(10000));
        translate3.setByX(-200);
        translate3.play();
        
        FadeTransition fade3 = new FadeTransition();
        fade3.setNode(fog3);
        fade3.setDuration(Duration.millis(5000));
        fade3.setCycleCount(TranslateTransition.INDEFINITE);
        fade3.setAutoReverse(true);
        fade3.setInterpolator(Interpolator.LINEAR);
        fade3.setFromValue(0);
        fade3.setToValue(1);
        fade3.play();
        TranslateTransition logoAnimation = new TranslateTransition();
        logoAnimation.setNode(Akinator);
        logoAnimation.setDuration(Duration.millis(1000));
        logoAnimation.setByY(-165); 
        logoAnimation.play();
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(Akinator);
        scale.setDuration(Duration.millis(1000));
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(4.2);
        scale.setByY(4.2);
        scale.play();
        Thread t1 = new Thread(new ThreadMainAnimation(this.Akinator));
        t1.start(); 
    }
    
    
    
}
