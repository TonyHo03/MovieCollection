package dk.easv.moviecollection.gui;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;
import dk.easv.moviecollection.gui.model.MovieModel;
import javafx.application.Platform;
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
import java.awt.Desktop;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//import javafx.scene.media.Media;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private MovieModel movieModel;

    @FXML
    private Label welcomeText;

    @FXML
    private Slider sldrVeryNewRating;

    @FXML
    private TableView<Movie> tblMovies;
    @FXML
    private ListView<Category> lstCategories;
    @FXML
    private TableColumn<Movie, String> clmTitle, clmGenre, clmFilelink, clmLastView;
    @FXML
    private TableColumn<Movie, Float> clmRating;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtFldFilter;

    private boolean isFiltering = false;
    private ObservableList<Movie> movieList;
    private ObservableList<Category> categoryList;

    public MainController() {

        try {
            movieModel = new MovieModel();
            movieList = movieModel.getMovieObservableList();
            categoryList = movieModel.getCategoryObservableList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(this::showStartupWarningIfNeeded);


        clmTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        clmRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        clmGenre.setCellValueFactory(new PropertyValueFactory<>("category"));
        clmFilelink.setCellValueFactory(new PropertyValueFactory<>("filePath"));
        clmLastView.setCellValueFactory(new PropertyValueFactory<>("lastOpened"));

        try {
            tblMovies.setItems(movieList);
            lstCategories.setItems(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lstCategories.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->  {

            if (newValue != null) {

                FilteredList<Movie> filteredList = new FilteredList<>(movieList);

                filteredList.setPredicate(movie -> {

                    String[] categories = movie.getCategory().split("\\|");

                    for (String category: categories) {

                        if (newValue.getName().equals(category)) {
                            System.out.println("Matching Category");
                            return true;

                        }
                    }

                    return false;

                });

                tblMovies.setItems(filteredList);

            }
            else {

                movieList = movieModel.getMovieObservableList();
                tblMovies.setItems(movieList);

            }

        }));


        tblMovies.setRowFactory(tv -> {
            TableRow<Movie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2  && !row.isEmpty()) {
                    Movie clickedMovie = row.getItem();
                    String path = clickedMovie.getFilePath();
                    System.out.println("Clicked");


                    File movieFile = new File(path);

                    try {
                        if (Desktop.isDesktopSupported()) {

                            Desktop.getDesktop().open(movieFile);

                            clickedMovie.setLastOpened(Date.valueOf(LocalDate.now()));
                            System.out.println("Setting lastOpened to: " + clickedMovie.getLastOpened());

                            movieModel.updateLastOpened(clickedMovie);

                        } else {
                            System.out.println("Desktop not supported");
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            return row;
        });
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

            AddMovieController addMovieController = fxmlLoader.getController();
            addMovieController.initializeClass(movieModel, stage);

            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnOnNewCategory(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/moviecollection/addCategory.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 310, 218);
            Stage stage = new Stage();
            stage.setTitle("New category");

            stage.initModality(Modality.APPLICATION_MODAL);

            AddCategoryController addCategoryController = fxmlLoader.getController();
            addCategoryController.initializeClass(movieModel, stage);

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

            FilteredList<Movie> filteredList = new FilteredList<>(movieModel.getMovieObservableList());

            filteredList.setPredicate(movie -> matchesFilter(movie, txtFldFilter.getText()) );

            tblMovies.setItems(FXCollections.observableArrayList(filteredList)); }
        else { btnSearch.setText("\uD83D\uDD0E");

            txtFldFilter.clear();

            tblMovies.setItems(movieModel.getMovieObservableList()); }

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
        Category selectedCategory = lstCategories.getSelectionModel().getSelectedItem();

        if (selectedCategory != null) {
            try {
            movieModel.deleteCategory(selectedCategory);

            System.out.println(movieModel.getCategoryObservableList());
        } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteMovieClick(ActionEvent actionEvent) {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            try {
                movieModel.deleteMovie(selectedMovie);

                System.out.println(movieModel.getMovieObservableList());
            }
            catch (Exception e)  {
                e.printStackTrace();
            }
        }
    }

    public void lookupMovies(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.imdb.com/"));
    }

    public void onRateClick(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            System.out.println("No movies selected, asshole");
            return;
        }
        float newRating = (float) sldrVeryNewRating.getValue();
        selectedMovie.setRating(newRating);
        movieModel.updateRating(selectedMovie);
        tblMovies.refresh();
    }

    private void showStartupWarningIfNeeded() {
        List<Movie> warningMovies = movieModel.getStartupWarningMovies();
        if
        (warningMovies.isEmpty())
        {
            return;
        } try {
            FXMLLoader loader = new FXMLLoader( getClass().getResource ("/dk/easv/moviecollection/StartupWarning.fxml") );
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Low rated movies that haven't been opened in 2 years");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(tblMovies.getScene().getWindow());
            StartupWarningController controller = loader.getController();
            controller.setMovies(warningMovies);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


