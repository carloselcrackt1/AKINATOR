package ec.edu.uees.akinatorproject;

import binaryTree.BinaryTree;
import binaryTree.GenericArrayList;
import fileClass.StackFile;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private HBox newCharacterHBox;
    @FXML
    private ImageView newCharacterBackground;
    @FXML
    private TextField newCharacterTxtField;
    @FXML
    private ImageView dissapear;
    @FXML
    private ImageView sigBtn;
    @FXML
    private Label pageCount;
    
    //INTERNAL VARIABLES
    private MediaPlayer mp;
    private boolean cardFlag = true;
    private String actualCard;
    private StackFile sf = new StackFile();
    private BinaryTree<QAndA> bt;
    private boolean isTalking;
    private ClowAnimation ca = new ClowAnimation(this);
    private boolean flag;
    private String newCharacter;
    private String newQuestion;
    private boolean secondFlag;
    private int page = 0;
    private int maxPage;
    
    //CARDS FUNCTIONS
    public ArrayList<QAndA> calculateCuantityCards() throws URISyntaxException{
        ArrayList<QAndA> qAndAs = new ArrayList<>();
        InputStream input = getClass().getResourceAsStream("/data/tree.ser");
        try (ObjectInputStream ois = new ObjectInputStream(input)) {
            while (true) {
                try {
                    QAndA qAndA = (QAndA) ois.readObject();
                    if(qAndA.isCharacter()){
                        qAndAs.add(qAndA);
                    }
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
    
    public void addCharacter(){
        lblAsk.setText("Es el personaje que buscabas?");
        Thread t = new Thread(ca);
        t.start();
    }
    
    public void finalClownAnimation(){
        Platform.runLater(()-> {
            lblAsk.setText("Es el personaje que buscabas?");
            secondFlag = true;
        });
        Thread t = new Thread(ca);
        t.start();
    }
    
    public void removeCards(boolean animation, boolean animationType, int delay){
        GenericArrayList<QAndA> qAndAs = bt.getPosiblesAnswers();
        int count = 10;
        for(Node img: hboxCards.getChildren()){
            boolean flag = false;
            ImageView imgv = (ImageView) img;
            for(QAndA q:qAndAs){
                if(((QAndA)imgv.getUserData()).getCharacterName().equals(q.getCharacterName())){
                    flag = true;
                }
            }
            if(!flag){
                if(animation){
                    TranslateTransition tt = new TranslateTransition(Duration.millis(1000),imgv);
                    tt.setDelay(Duration.millis(count*30));
                        tt.setByX(-3000);
                        tt.play();
                }else{
                    imgv.setTranslateY(-200);
                }
            }else if(flag && animationType){
                imgv.setTranslateX(-3000);
                TranslateTransition tt = new TranslateTransition(Duration.millis(500),imgv);
                tt.setDelay(Duration.millis(count*20));
                tt.setByX(+3000);
                tt.play();
            }
            count--;
        }
    }
    
    public void chooseSiOption(){
        Thread t = new Thread(ca);
        t.start();
        if(bt.getAQ().isCharacter()){
            if(secondFlag){
                lblAsk.setText("Un gusto volver a ganarte...");
                FadeTransition fade = new FadeTransition();
                fade.setNode(dissapear);
                fade.setDuration(Duration.millis(5000));
                fade.setInterpolator(Interpolator.LINEAR);
                fade.setFromValue(0);
                fade.setToValue(1);
                fade.setOnFinished(e -> {
                try {
                    App.setRoot("mainScreen");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                });
                fade.play();
                siOption.setDisable(true);
                noOption.setDisable(true);
                t.start();
            }
        }else{
            bt.jugar(true);
            if(!bt.getAQ().isCharacter()){
                removeCards(true, false, 1);
                lblAsk.setText(bt.getAQ().getCharacterName());
            } else {
                removeCards(true, false, 1);
                lblAsk.setText(bt.getAQ().getCharacterName());
                userGotCharacter ugc = new userGotCharacter(this);
                Thread t1 = new Thread(ugc);
                t1.start();

            }
        }
    }
    
    public void chooseNoOption(){
        Thread t = new Thread(ca);
        t.start();
        if(bt.getAQ().isCharacter()){
            if(secondFlag){
                lblAsk.setText("Que personaje estabas pensando?");
                newCharacterTxtField.setOnKeyPressed(event -> {
                    if(event.getCode().toString().equals("ENTER")){
                        if(!flag){
                            newCharacter = newCharacterTxtField.getText();
                            flag = true;
                            lblAsk.setText("Que pregunta no es cierto de " + bt.getAQ().getCharacterName() + " que si lo sea de " + newCharacter);
                            newCharacterTxtField.clear();
                        }else{
                            lblAsk.setText("Lo tendré en consideración para la próxima!");
                            bt.add(new QAndA(newCharacterTxtField.getText(), "", false), new QAndA(newCharacter, "newCharacter.png", true), true);
                            newCharacterTxtField.setDisable(true);
                            FadeTransition fade = new FadeTransition();
                            fade.setNode(dissapear);
                            fade.setDuration(Duration.millis(5000));
                            fade.setInterpolator(Interpolator.LINEAR);
                            fade.setFromValue(0);
                            fade.setToValue(1);
                            fade.setOnFinished(e -> {
                            try {
                                App.setRoot("mainScreen");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            });
                            fade.play();
                            sf.fileReader();
                            bt = new BinaryTree(sf.getAkiStackNode());
                        }
                    }
                    t.start();
                });
                siOption.setOpacity(0);
                siOption.setDisable(true);
                noOption.setOpacity(0);
                noOption.setDisable(true);
                newCharacterBackground.setOpacity(1);
                newCharacterTxtField.setOpacity(1);
                newCharacterHBox.setOpacity(1);
                newCharacterBackground.setDisable(false);
                newCharacterTxtField.setDisable(false);
                newCharacterHBox.setDisable(false);
            }
        }else{
            bt.jugar(false);
            if(!bt.getAQ().isCharacter()){
                removeCards(true, false, 1);
                lblAsk.setText(bt.getAQ().getCharacterName());
            } else {
                removeCards(true, false, 1);
                lblAsk.setText(bt.getAQ().getCharacterName());
                userGotCharacter ugc = new userGotCharacter(this);
                Thread t1 = new Thread(ugc);
                t1.start();

            }
        }
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
        sf.fileReader();
        bt = new BinaryTree(sf.getAkiStackNode());
        try {
            maxPage = (int)Math.ceil(calculateCuantityCards().size() / 10);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        pageCount.setText((page+1) + " / " + (maxPage+1));
        sigBtn.setOnMouseClicked((u)->{
            if(page > maxPage-1){
                page = -1;
            }
            page++;
            pageCount.setText((page+1) + " / " + (maxPage+1));
            hboxCards.getChildren().clear();
            try {
                ArrayList<QAndA> qa = calculateCuantityCards();
                for(QAndA i: qa){
                    if(qa.indexOf(i)>= (page * 10) && qa.indexOf(i) <= ((page+1)*10)-1){
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
                        card.setUserData(i);
                        card.setOnMouseClicked((e)-> {

                            if(cardFlag){
                                Image imgToChange = new Image(getClass().getResource("/ec/edu/uees/akinatorproject/assets/cards/" + i.getIcon()).toString());
                                this.cardFlag = false;
                                if(cardInformation.getTranslateX() == -464){
                                    cardInformationUp.setToX(-800);
                                    cardInformationDown.setToX(-464);
                                    actualCard = i.getIcon();
                                    SequentialTransition st = new SequentialTransition(cardInformationUp, cardInformationDown);                    
                                    st.play();
                                    cardInformationUp.setOnFinished(eh -> cardInformation.setImage(imgToChange));
                                    st.setOnFinished(eh -> {
                                        this.cardFlag=true;
                                    });
                                }else{
                                    actualCard = i.getIcon();
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
                removeCards(false, true, 1);
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        FadeTransition fade = new FadeTransition();
        fade.setNode(dissapear);
        fade.setDuration(Duration.millis(5000));
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
        TranslateTransition handSiAction = new TranslateTransition(Duration.millis(2000), handSi);
        handSiAction.setDelay(Duration.millis(100));
        handSiAction.setToY(310);
        handSiAction.setCycleCount(TranslateTransition.INDEFINITE);
        handSiAction.setAutoReverse(true);
        TranslateTransition handNoAction = new TranslateTransition(Duration.millis(2000), handNo);
        handNoAction.setCycleCount(TranslateTransition.INDEFINITE);
        handNoAction.setAutoReverse(true);
        handNoAction.setToY(300);
        handSiAction.play();
        handNoAction.play();
        try {
            ArrayList<QAndA> qAndAs = calculateCuantityCards();
            for(QAndA q: qAndAs){
                if(qAndAs.indexOf(q) >= 10) {
                    sigBtn.setOpacity(1);
                    break;
                }
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
                    card.setUserData(q);
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
