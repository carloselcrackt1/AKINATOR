package ec.edu.uees.akinatorproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import questionAnswerClass.QAndA;

public class MainScreenController implements Initializable{
    
    @FXML
    private ImageView akinatorLogo;
    @FXML
    private MediaView balatroBackground;
    @FXML
    private ImageView playButton;
    @FXML
    private ImageView optionsButton;
    @FXML
    private ImageView quitButton;
    @FXML
    private ImageView collectionButton;
    @FXML
    private ImageView subMenuPlay;
    @FXML
    private ImageView subMenuCardsBackground;
    @FXML
    private ImageView subMenuClassic;
    @FXML
    private ImageView subMenuBack;
    @FXML
    private ImageView subMenuBackground;
    @FXML
    private ImageView blurEffect;
    
    
    //INTERNAL VARIABLES
    private MediaPlayer mp;
    
    
    //MENU FUNCTIONS
    private void moverSubMenu(double desplazamiento) {
        ParallelTransition pt = new ParallelTransition();
        for (Node node : Arrays.asList(subMenuBackground, subMenuBack, subMenuPlay, subMenuCardsBackground, subMenuClassic)) {
            TranslateTransition transition = new TranslateTransition(Duration.millis(500), node);
            transition.setByY(desplazamiento);
            pt.getChildren().add(transition);
        }
        pt.play();
    }

    @FXML
    public void play(){
        blurEffect.setOpacity(1);
        moverSubMenu(-739);
    }
    
    @FXML
    public void back(){
        blurEffect.setOpacity(0);
        moverSubMenu(739);
    }
    
    @FXML
    public void playClassicMode() throws IOException{
        App.setRoot("playScreen");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> {
            playButton.hoverProperty().addListener((observable, oldValue, newValue)-> {
                if(newValue){
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/playButtonHover.png").toString());
                    playButton.setImage(img);
                }else{
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/playButton.png").toString());
                    playButton.setImage(img);
                }
            });
            optionsButton.hoverProperty().addListener((observable, oldValue, newValue)-> {
                if(newValue){
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/optionsButtonHover.png").toString());
                    optionsButton.setImage(img);
                }else{
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/optionsButton.png").toString());
                    optionsButton.setImage(img);
                }
            });
            quitButton.hoverProperty().addListener((observable, oldValue, newValue)-> {
                if(newValue){
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/quitButtonHover.png").toString());
                    quitButton.setImage(img);
                }else{
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/quitButton.png").toString());
                    quitButton.setImage(img);
                }
            });
            collectionButton.hoverProperty().addListener((observable, oldValue, newValue)-> {
                if(newValue){
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/collectionButtonHover.png").toString());
                    collectionButton.setImage(img);
                }else{
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/collectionButton.png").toString());
                    collectionButton.setImage(img);
                }
            });
            subMenuPlay.hoverProperty().addListener((observable, oldValue, newValue)-> {
                if(newValue){
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/playSubMenu/playSubMenu-playHover.png").toString());
                    subMenuPlay.setImage(img);
                }else{
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/playSubMenu/playSubMenu-play.png").toString());
                    subMenuPlay.setImage(img);
                }
            });
            subMenuBack.hoverProperty().addListener((observable, oldValue, newValue)-> {
                if(newValue){
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/playSubMenu/playSubMenu-backHover.png").toString());
                    subMenuBack.setImage(img);
                }else{
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/menu/playSubMenu/playSubMenu-back.png").toString());
                    subMenuBack.setImage(img);
                }
            });
            String urlv = getClass().getResource("/media/videoplayback.mp4").toExternalForm();
            Media media = new Media(urlv);
            this.mp = new MediaPlayer(media);
            balatroBackground.setMediaPlayer(mp);
            mp.play();

            TranslateTransition t1 = new TranslateTransition();
            t1.setNode(akinatorLogo);
            t1.setDuration(Duration.millis(1000));
            t1.setCycleCount(2);
            t1.setByX(5);
            t1.setByY(5);
            t1.setAutoReverse(true);

            TranslateTransition t2 = new TranslateTransition();
            t2.setNode(akinatorLogo);
            t2.setDuration(Duration.millis(1000));
            t2.setCycleCount(2);
            t2.setByX(-5);
            t2.setByY(-5);
            t2.setAutoReverse(true);

            TranslateTransition t3 = new TranslateTransition();
            t3.setNode(akinatorLogo);
            t3.setDuration(Duration.millis(1000));
            t3.setCycleCount(2);
            t3.setByX(5);
            t3.setByY(-5);
            t3.setAutoReverse(true);

            TranslateTransition t4 = new TranslateTransition();
            t4.setNode(akinatorLogo);
            t4.setDuration(Duration.millis(1000));
            t4.setCycleCount(2);
            t4.setByX(-5);
            t4.setByY(5);
            t4.setAutoReverse(true);

            SequentialTransition st = new SequentialTransition(akinatorLogo, t1, t2, t3, t4);
            st.setCycleCount(TranslateTransition.INDEFINITE);
            st.play();
            
        });
        
    }
    // GUARDAR EN EL .SER
    /*
        ArrayList<QAndA> qAndAs = new ArrayList<>();
        qAndAs.add(new QAndA("Judith Uruchima", "judith.png", true));
        qAndAs.add(new QAndA("Paula Benalcazar", "paula.png", true));
        qAndAs.add(new QAndA("¿Su pelo es liso?", "", false));
        qAndAs.add(new QAndA("Carlos Auqui", "carlos.png", true));
        qAndAs.add(new QAndA("Luis Goncalves", "luis.png", true));
        qAndAs.add(new QAndA("¿Tiene Barba?", "", false));
        qAndAs.add(new QAndA("¿Es hombre?", "", false));
        qAndAs.add(new QAndA("Kevin Roman", "kevin.png", true));
        qAndAs.add(new QAndA("Marcos Tapia", "paula.png", true));
        qAndAs.add(new QAndA("¿Crees que vaya al gimnasio con frecuencia?", "", false));
        qAndAs.add(new QAndA("Gabriel Guerrero", "gabriel.png", true));
        qAndAs.add(new QAndA("¿Tiene cabello largo?", "", false));
        qAndAs.add(new QAndA("Fabian Rodas", "", true));
        qAndAs.add(new QAndA("Jesus Jimenez", "jesus.png", true));
        qAndAs.add(new QAndA("¿Su contextura es delgada?", "", false));
        qAndAs.add(new QAndA("¿Usa lentes?", "", false));
        qAndAs.add(new QAndA("¿Mide más de 1.80?", "", false));
        
        
        guardarArchivoEnf(qAndAs);
        */
    /*
    public void guardarArchivoEnf(ArrayList<QAndA> qAndAs){
        try{
            String userDir = System.getProperty("user.dir");
        userDir = userDir.replace("\\", "/");
            FileOutputStream objetoFile = new FileOutputStream(userDir + "/src/main/resources/data/tree.ser");
            ObjectOutputStream oos = new ObjectOutputStream(objetoFile);
            for(QAndA qAndA:qAndAs){ 
                oos.writeObject(qAndA);
            }
            oos.close();
        }catch(IOException  e){
            e.printStackTrace();
        }
    }
    */
}
