package ec.edu.uees.akinatorproject.scenario;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThreadMainAnimation implements Runnable {

    private ImageView akinator;
    private boolean flag = true;
    public ThreadMainAnimation(ImageView akinator){
        this.akinator = akinator;
    }
    
    @Override
    public void run() {
        if(flag){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Image img = new Image(getClass().getResourceAsStream("/ec/edu/uees/akinatorproject/main/frame_2.png"));
            akinator.setImage(img);
            akinator.setScaleX(4);
            akinator.setScaleY(4);
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(akinator);
            translate.setDuration(Duration.millis(1000));
            translate.setCycleCount(TranslateTransition.INDEFINITE);
            translate.setByX(45);
            translate.setByY(-50);
            translate.setAutoReverse(true);
            translate.play();
            this.flag = false;
        }
    }
    
}
