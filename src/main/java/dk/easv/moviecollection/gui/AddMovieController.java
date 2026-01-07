package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import dk.easv.moviecollection.BE.Movie;

public class AddMovieController
{

    public MovieCollectionDAO_DB MovieDAO;
    @FXML
    public TextField txtMovieTitle;
    public TextField txtMovieCategory;
    public TextField txtFilePath;
    public TextField txtDate;
    public TextField txtRating;
    public void btnAddMovie(ActionEvent actionEvent) throws Exception {

        Movie newMovie = new Movie(txtMovieTitle.getText(), txtMovieCategory.getText(), txtFilePath.getText(), txtDate.getText(), txtRating.getText());
        MovieDAO.createMovie(newMovie);


    }
}
