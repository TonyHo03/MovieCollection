package dk.easv.moviecollection.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddMovieController
{

    @FXML
    public TextField txtMovieTitle;
    public TextField txtMovieCategory;
    public void btnAddMovie(ActionEvent actionEvent) {

        String title = txtMovieTitle.getText();
        String category = txtMovieCategory.getText();
    }
}
