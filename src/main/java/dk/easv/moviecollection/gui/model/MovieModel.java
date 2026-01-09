package dk.easv.moviecollection.gui.model;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.bll.CategoryManager;
import dk.easv.moviecollection.bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class MovieModel {

    private MovieManager movieManager = new MovieManager();
    private static CategoryManager categoryManager;

    static {
        try {
            categoryManager = new CategoryManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ObservableList<Movie> movieObservableList;
    private static ObservableList<Category> categoryObservableList;

    public MovieModel() throws Exception {

        movieObservableList = FXCollections.observableArrayList();
        categoryObservableList = FXCollections.observableArrayList();
        movieObservableList.setAll(loadMovies());
        categoryObservableList.setAll(loadCategories());

    }

    public static ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }

    public static List<Category> getCategory() {
        return categoryObservableList;
    }

    public ObservableList<Category> getCategoryObservableList() {
        return categoryObservableList;
    }

    public ObservableList<Category> getObservableCategoryList() {
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

    }

    public List<Category> loadCategories() throws Exception {

        return categoryManager.loadCategories();

    }

}


