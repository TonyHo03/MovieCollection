package dk.easv.moviecollection.dal;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;

import java.util.List;

public interface IMovieCollectionDataAccess {

    void createMovie() throws Exception;

    void deleteMovie() throws Exception;

    void createCategory() throws Exception;

    void deleteCategory() throws Exception;

    List<Movie> loadMovies() throws Exception;

    List<Category> loadCategories() throws Exception;
}
