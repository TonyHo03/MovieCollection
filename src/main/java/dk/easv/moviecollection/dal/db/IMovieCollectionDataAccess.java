package dk.easv.moviecollection.dal.db;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;

import java.util.List;

public interface IMovieCollectionDataAccess {

    void createMovie(Movie movie) throws Exception;

    void deleteMovie(Movie movie) throws Exception;

    void createCategory(Category category) throws Exception;

    void deleteCategory(Category category) throws Exception;

    List<Movie> loadMovies();

    List<Category> loadCategories();
}
