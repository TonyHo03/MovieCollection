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

    }

    public void deleteMovie(Movie movie) throws Exception {

        movieManager.deleteMovie(movie);

    }

    public void updateLastOpened(Movie movie) throws Exception{
        movieManager.updateLastOpened(movie);
    }



    public List<Category> loadCategories() throws Exception {

        return categoryManager.loadCategories();

    }

}


