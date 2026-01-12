package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.BE.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class StartupWarningController {

    @FXML
    private ListView<Movie> lstWarnedMovies;


    public void setMovies(List<Movie> movies) {
        lstWarnedMovies.getItems().setAll(movies);
    }

    @FXML
    private void btnOnIgnore(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) lstWarnedMovies.getScene().getWindow();
        stage.close();
    }
}
