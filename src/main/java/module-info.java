module dk.easv.moviecollection {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires javafx.base;
    requires com.microsoft.sqlserver.jdbc;
    requires javafx.graphics;

    opens dk.easv.moviecollection to javafx.fxml;
    exports dk.easv.moviecollection;
    exports dk.easv.moviecollection.gui.model;
    exports dk.easv.moviecollection.gui;
    opens dk.easv.moviecollection.gui to javafx.fxml;
    exports dk.easv.moviecollection.BE;
    opens dk.easv.moviecollection.BE to javafx.base;
}