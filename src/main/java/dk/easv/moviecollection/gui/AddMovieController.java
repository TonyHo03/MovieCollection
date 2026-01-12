package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import dk.easv.moviecollection.BE.Movie;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable
{


    private MovieModel movieModel;
    private Stage currentStage;

    @FXML
    private TextField txtMovieTitle, txtMovieFile;

    @FXML
    private ChoiceBox<Category> cbCategory1, cbCategory2, cbCategory3;

    @FXML
    private Slider sldrRating;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @FXML
    private void onChooseBtnClick() {

        FileChooser fileChooser = new FileChooser();

        File chosenFile = fileChooser.showOpenDialog(currentStage);

        if (chosenFile != null) {

            txtMovieFile.setText(chosenFile.getPath());

        }

    }

    @FXML
    private void btnAddMovie(ActionEvent actionEvent) throws Exception {

        String categories = cbCategory1.getValue() + ":" + cbCategory2.getValue() + ":" + cbCategory3.getValue();

        Movie newMovie = new Movie(0, txtMovieTitle.getText(), categories, (float) sldrRating.getValue(), txtMovieFile.getText(), Date.valueOf(LocalDate.now()));
        try {
            movieModel.createMovie(newMovie);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initializeClass(MovieModel movieModel, Stage stage) {

        this.movieModel = movieModel;
        this.currentStage = stage;

        comboBoxSetup();

    }

    public void comboBoxSetup() {

        cbCategory1.setItems(movieModel.getCategoryObservableList());
        cbCategory2.setItems(movieModel.getCategoryObservableList());
        cbCategory3.setItems(movieModel.getCategoryObservableList());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //.out.println("hello");

    }
}
