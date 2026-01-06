package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MovieModel movieModel;

    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Movie> tblMovies;
    @FXML
    private ListView<Category> lstCategories;
    @FXML
    private TableColumn<Movie, String> clmTitle;
    @FXML
    private TableColumn<Movie, Float> clmRating;

    public MainController() {

        try {
            movieModel = new MovieModel();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        clmRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        try {
            tblMovies.setItems(movieModel.getMovieObservableList());
            lstCategories.setItems(movieModel.getCategoryObservableList());
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void btnOnNewMovie(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollection/addMovieView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Movie");

            stage.initModality(Modality.APPLICATION_MODAL);



            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {

        }
    }

    public void btnOnNewCategory(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollection/addCategory.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New category");

            stage.initModality(Modality.APPLICATION_MODAL);



            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {

        }
    }
    }

