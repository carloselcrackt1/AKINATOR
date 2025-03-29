package ec.edu.uees.akinatorproject;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AkinatorMusic {
    private static MediaPlayer mediaPlayer; 
    public static void startMusic() {
        if (mediaPlayer == null) {
            URL resource = AkinatorMusic.class.getResource("/media/balatro.mp3");
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }    
    
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
