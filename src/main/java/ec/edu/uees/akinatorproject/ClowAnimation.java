package ec.edu.uees.akinatorproject;

public class ClowAnimation implements Runnable{
    
    PlayScreenController psc;
    
    public ClowAnimation(PlayScreenController psc){
        this.psc = psc;
    }

    @Override
    public void run() {
        for(int i=0;i<14;i++){
            try {
                psc.clownAnimation();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
