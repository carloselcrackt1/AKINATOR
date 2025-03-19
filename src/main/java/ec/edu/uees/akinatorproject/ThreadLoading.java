package ec.edu.uees.akinatorproject;
public class ThreadLoading implements Runnable {
    private LoadingScreen ls;
    
    public ThreadLoading(LoadingScreen ls){
        this.ls = ls;
    }
    
    
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            ls.switchToMainScreen();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        ls.switchToMainScreen();
    }
    
}
