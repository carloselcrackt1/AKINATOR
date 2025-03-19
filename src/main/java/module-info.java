module ec.edu.uees.akinatorproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.uees.akinatorproject to javafx.fxml;
    exports ec.edu.uees.akinatorproject;
}
