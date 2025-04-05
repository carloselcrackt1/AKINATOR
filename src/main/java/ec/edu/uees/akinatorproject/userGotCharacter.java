package ec.edu.uees.akinatorproject;

import javafx.scene.control.Label;

public class userGotCharacter implements Runnable {
    PlayScreenController psc;
    

    
    public  userGotCharacter(PlayScreenController psc){
        this.psc = psc;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        psc.finalClownAnimation();
    }
    
}
