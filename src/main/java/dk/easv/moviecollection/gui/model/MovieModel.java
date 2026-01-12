package dk.easv.moviecollection.gui.model;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.bll.CategoryManager;
import dk.easv.moviecollection.bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieModel {

    private MovieManager movieManager = new MovieManager();
    private CategoryManager categoryManager = new CategoryManager();


    private ObservableList<Movie> movieObservableList;
    private ObservableList<Category> categoryObservableList;

    public MovieModel() throws Exception {

        movieObservableList = FXCollections.observableArrayList();
        categoryObservableList = FXCollections.observableArrayList();
        movieObservableList.setAll(movieManager.loadMovies());
        categoryObservableList.setAll(categoryManager.loadCategories());

    }

    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }

    public ObservableList<Category> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void createCategory(Category category) throws Exception {

        categoryManager.createCategory(category);

        categoryObservableList.add(category);

    }

    public void deleteCategory(Category category) throws Exception {
        categoryManager.deleteCategory(category);

        categoryObservableList.remove(category);
    }


    public List<Movie> loadMovies() throws Exception {

        return movieManager.loadMovies();

    }

    public void createMovie(Movie movie) throws Exception {

        movieManager.createMovie(movie);

        movieObservableList.add(movie);

    }

    public void deleteMovie(Movie movie) throws Exception {

        movieManager.deleteMovie(movie);

        movieObservableList.remove(movie);

    }



    public List<Category> loadCategories() throws Exception {

        return categoryManager.loadCategories();

    }

    public List<Movie> getStartupWarningMovies() {
        List<Movie> result = new ArrayList<>();
        LocalDate twoYearsAgo = LocalDate.now().minusYears(2);

        for (Movie movie : movieObservableList) {
            if (movie.getRating() > 6) {
                continue;

            }
            if (movie.getLastOpened() == null) {
                continue;
            }

            LocalDate lastOpenedDate = movie.getLastOpened().toLocalDate();
            if (lastOpenedDate.isAfter(twoYearsAgo)) {
                continue;
            }

            result.add(movie);
        }
        return result;
    }
}


