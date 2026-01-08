package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import dk.easv.moviecollection.BE.Movie;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

public class AddMovieController
{

    public MovieCollectionDAO_DB MovieDAO;
    @FXML
    private TextField txtMovieTitle, txtMovieFile;

    @FXML
    private ComboBox<String> cbCategory1, cbCategory2, cbCategory3;

    @FXML
    private Slider sldrRating;

    @FXML
    private void onChooseBtnClick() {

        FileChooser fileChooser = new FileChooser();

        File chosenFile = fileChooser.showOpenDialog(null);

    }

    @FXML
    private void btnAddMovie(ActionEvent actionEvent) throws Exception {

        String categories = cbCategory1.getValue() + ":" + cbCategory2.getValue() + ":" + cbCategory3.getValue();

        Movie newMovie = new Movie(0, txtMovieTitle.getText(), categories, (float) sldrRating.getValue(), txtMovieFile.getText(), Date.valueOf(LocalDate.now()));
        MovieDAO.createMovie(newMovie);

    }
}
