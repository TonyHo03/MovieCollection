module dk.easv.moviecollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;

    opens dk.easv.moviecollection to javafx.fxml;
    exports dk.easv.moviecollection;
}