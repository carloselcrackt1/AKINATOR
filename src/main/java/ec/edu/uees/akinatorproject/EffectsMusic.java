package ec.edu.uees.akinatorproject;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class EffectsMusic {
    private static MediaPlayer sfxPlayer;

    public static void playSFX() {
        URL resource = EffectsMusic.class.getResource("/audio/sfx.wav");
        Media media = new Media(resource.toString());
        sfxPlayer = new MediaPlayer(media);
        sfxPlayer.setVolume(50);
        sfxPlayer.play();
    }
}
