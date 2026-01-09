package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;
import dk.easv.moviecollection.gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtFldFilter;

    private boolean isFiltering = false;

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
            movieModel = new MovieModel();
            tblMovies.setItems(MovieModel.getMovieObservableList());
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
            Scene scene = new Scene(fxmlLoader.load(), 310, 218);
            Stage stage = new Stage();
            stage.setTitle("New category");

            stage.initModality(Modality.APPLICATION_MODAL);



            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {

        }
    }

    @FXML
    private void onSearchBtnClick() {

        if (!isFiltering) {
            //Search button indicates the filter is on
            btnSearch.setText("C");

            FilteredList<Movie> filteredList = new FilteredList<>(MovieModel.getMovieObservableList());

            filteredList.setPredicate(movie -> matchesFilter(movie, txtFldFilter.getText()) );

            tblMovies.setItems(FXCollections.observableArrayList(filteredList)); }
        else { btnSearch.setText("\uD83D\uDD0E");

            txtFldFilter.clear();

            tblMovies.setItems(MovieModel.getMovieObservableList()); }

        isFiltering = !isFiltering;
    }

    private boolean matchesFilter(Movie movie, String filterText) {

        if (filterText == null || filterText.isBlank()) {
            return true;
        }

        String filter = filterText.toLowerCase().trim();

        // Title match
        if (movie.getTitle().toLowerCase().contains(filter)) {
            return true;
        }

        // Rating match
        try {
            float ratingFilter = Float.parseFloat(filter);
            return movie.getRating() >= ratingFilter;
        } catch (NumberFormatException ignored) {}

        return false;
    }


    public void onDeleteCategoryClick(ActionEvent actionEvent) {
        Category selectedCategory = lstCategories.getFocusModel().getFocusedItem();

        if (selectedCategory != null) {
            try {
            MovieModel.deleteCategory(selectedCategory);

            System.out.println(MovieModel.getCategory());
        } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

