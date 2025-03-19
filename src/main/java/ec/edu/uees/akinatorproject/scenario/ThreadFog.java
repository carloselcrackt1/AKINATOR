package ec.edu.uees.akinatorproject.scenario;
import ec.edu.uees.akinatorproject.MainScreenController;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThreadFog implements Runnable{
    private MainScreenController msc;
    private ImageView fogImage;
    private double x;
    private boolean orientation;
    private int offset;
    
    public ThreadFog(MainScreenController msc, ImageView fogImage, double x, boolean orientation, int offset){
        this.msc = msc;
        this.fogImage = fogImage;
        this.x = x;
        this.orientation = orientation;
        this.offset = offset;
    }

    @Override
    public void run() {
        while(true){
            msc.animFog(fogImage);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
