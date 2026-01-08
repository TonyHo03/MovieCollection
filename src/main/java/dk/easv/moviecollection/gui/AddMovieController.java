package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import dk.easv.moviecollection.BE.Movie;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class AddMovieController
{
    public MovieCollectionDAO_DB MovieDAO = new MovieCollectionDAO_DB();
    @FXML
    public TextField txtMovieTitle;
    public TextField txtMovieCategory;
    public TextField txtFilePath;
    public TextField txtMovieRating;
    public Button btnAddMovie;

    public AddMovieController() throws IOException {
    }

    public void onBtnAddMovie() throws Exception {

        Movie newMovie = new Movie(txtMovieTitle.getText(), txtMovieCategory.getText(), txtMovieRating.getText());
        MovieDAO.createMovie(newMovie);

    }
}
