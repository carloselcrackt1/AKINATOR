package ec.edu.uees.akinatorproject;

import binaryTree.BinaryTree;
import fileClass.StackFile;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import questionAnswerClass.QAndA;

public class PlayScreenController implements Initializable{
    
    @FXML
    private MediaView balatroBackground;
    @FXML
    private HBox hboxCards;
    @FXML
    private ImageView cardInformation;
    @FXML
    private ImageView siOption;
    @FXML
    private ImageView noOption;
    @FXML
    private Label lblAsk;
    @FXML
    private ImageView handSi;
    @FXML
    private ImageView handNo;
    @FXML
    private ImageView clownHead;
    
    //INTERNAL VARIABLES
    private MediaPlayer mp;
    private boolean cardFlag = true;
    private String actualCard;
    private StackFile sf = new StackFile();
    private BinaryTree<QAndA> bt;
    private boolean isTalking;
    private ClowAnimation ca = new ClowAnimation(this);
    
    //CARDS FUNCTIONS
    public void upCard(){
        
    }
    
    public ArrayList<QAndA> calculateCuantityCards() throws URISyntaxException{
        ArrayList<QAndA> qAndAs = new ArrayList<>();
        InputStream input = getClass().getResourceAsStream("/data/tree.ser");
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            while (true) {
                try {
                    QAndA qAndA = (QAndA) ois.readObject();
                    qAndAs.add(qAndA);
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
       }catch(IOException e){
           e.printStackTrace();
       }catch(ClassNotFoundException e){
           e.printStackTrace();   
       }
        return qAndAs;
    }
    
    // QUESTION FUNCTIONS
    
    public void chooseSiOption(){
        Thread t =new Thread(ca);
        t.start();
        bt.jugar(true);
        lblAsk.setText(bt.getAQ().getCharacterName());
    }
    
    public void chooseNoOption(){
        Thread t =new Thread(ca);
        t.start();
        bt.jugar(false);
        lblAsk.setText(bt.getAQ().getCharacterName());
    }

    // OPTIONAL FUNCTIONS
    
    public void clownAnimation(){
        if(isTalking){
            Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/sprites/clown_1.png").toString());
            clownHead.setImage(img);
            isTalking = false;
        } else {
            Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/sprites/clown_2.png").toString());
            clownHead.setImage(img);
            isTalking = true;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition handSiAction = new TranslateTransition(Duration.millis(2000), handSi);
        handSiAction.setDelay(Duration.millis(100));
        handSiAction.setToY(325);
        handSiAction.setCycleCount(TranslateTransition.INDEFINITE);
        handSiAction.setAutoReverse(true);
        TranslateTransition handNoAction = new TranslateTransition(Duration.millis(2000), handNo);
        handNoAction.setCycleCount(TranslateTransition.INDEFINITE);
        handNoAction.setAutoReverse(true);
        handNoAction.setToY(315);
        handSiAction.play();
        handNoAction.play();
        sf.fileReader();
        bt = new BinaryTree(sf.getAkiStackNode());
        try {
            ArrayList<QAndA> qAndAs = calculateCuantityCards();
            for(QAndA q: qAndAs){
                if(q.isCharacter()){
                    
                    Image img = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/cards/blueCard.png").toString());
                    ImageView card = new ImageView();
                    card.setImage(img);
                    card.setFitHeight(100);
                    card.setFitWidth(100);
                    card.setScaleX(1.6);
                    hboxCards.getChildren().add(card);
                    TranslateTransition cardUpTransition = new TranslateTransition(Duration.millis(50), card);
                    cardUpTransition.setCycleCount(1);
                    TranslateTransition cardInformationUp = new TranslateTransition(Duration.millis(50), cardInformation);
                    TranslateTransition cardInformationDown = new TranslateTransition(Duration.millis(50), cardInformation);
                    cardUpTransition.setCycleCount(1);
                    card.setOnMouseClicked((e)-> {
                        
                        if(cardFlag){
                            Image imgToChange = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/cards/" + q.getIcon()).toString());
                            this.cardFlag = false;
                            if(cardInformation.getTranslateX() == -464){
                                cardInformationUp.setToX(-800);
                                cardInformationDown.setToX(-464);
                                actualCard = q.getIcon();
                                SequentialTransition st = new SequentialTransition(cardInformationUp, cardInformationDown);                    
                                st.play();
                                cardInformationUp.setOnFinished(eh -> cardInformation.setImage(imgToChange));
                                st.setOnFinished(eh -> {
                                    this.cardFlag=true;
                                });
                            }else{
                                actualCard = q.getIcon();
                                cardInformation.setImage(imgToChange);
                                cardInformationUp.setToX(-464);
                                cardInformationUp.play();
                                cardInformationUp.setOnFinished(eh -> this.cardFlag = true);
                            }
                        }
                    });
                    card.hoverProperty().addListener((observable, oldValue, newValue) -> {
                        cardUpTransition.stop(); 
                        if (newValue) {
                            cardUpTransition.setToY(-5);
                        } else {
                            cardUpTransition.setToY(0); 
                        }
                        cardUpTransition.play();
                    });
                }
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        String urlv = getClass().getResource("/media/videoplayback_felt.mp4").toExternalForm();
        Media media = new Media(urlv);
        this.mp = new MediaPlayer(media);
        balatroBackground.setMediaPlayer(mp);
        mp.play();
        
        lblAsk.setText(bt.getAQ().getCharacterName());
    }
    
    
    
}
