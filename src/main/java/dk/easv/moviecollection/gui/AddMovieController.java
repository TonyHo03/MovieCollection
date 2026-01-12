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
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @FXML
    private void onChooseBtnClick() {

        FileChooser fileChooser = new FileChooser();

        File chosenFile = fileChooser.showOpenDialog(currentStage);
        if (chosenFile == null) {return;}

        int dotIndex = chosenFile.getName().lastIndexOf(".");
        if (chosenFile.getName().substring(dotIndex).equals(".mp4") || chosenFile.getName().substring(dotIndex).equals(".mpeg4")) {
            txtMovieFile.setText(chosenFile.getPath());

        }
        else {
            txtMovieFile.setText("Filetype must be .mp4 or .mpeg4");
        }

    }

    @FXML
    private void btnAddMovie(ActionEvent actionEvent) throws Exception {

        if (!txtMovieFile.getText().isBlank()) {
            String categories = cbCategory1.getValue() + "|" + cbCategory2.getValue() + "|" + cbCategory3.getValue();

            Movie newMovie = new Movie(0, txtMovieTitle.getText(), categories, (float) sldrRating.getValue(), txtMovieFile.getText(), Date.valueOf(LocalDate.now()));
            try {
                movieModel.createMovie(newMovie);
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        cbCategory1.setValue(new Category(" "));
        cbCategory2.setValue(new Category(" "));
        cbCategory3.setValue(new Category(" "));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //.out.println("hello");

    }
}
