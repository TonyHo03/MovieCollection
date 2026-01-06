module dk.easv.moviecollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens dk.easv.moviecollection to javafx.fxml;
    exports dk.easv.moviecollection;
}