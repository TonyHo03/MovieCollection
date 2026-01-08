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
    private CategoryManager categoryManager = new CategoryManager();

    private static ObservableList<Movie> movieObservableList;
    private ObservableList<Category> categoryObservableList;

    public MovieModel() throws Exception {

        movieObservableList = FXCollections.observableArrayList();
        categoryObservableList = FXCollections.observableArrayList();
        movieObservableList.setAll(loadMovies());
        categoryObservableList.setAll(loadCategories());

    }

    public static ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }

    public ObservableList<Category> getCategoryObservableList() {
        return categoryObservableList;
    }



    public List<Movie> loadMovies() throws Exception {

        return movieManager.loadMovies();

    }

    public List<Category> loadCategories() throws Exception {

        return categoryManager.loadCategories();

    }

}


