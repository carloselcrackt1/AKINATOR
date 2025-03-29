module ec.edu.uees.akinatorproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
 
    opens ec.edu.uees.akinatorproject to javafx.fxml;
    exports ec.edu.uees.akinatorproject;
}
