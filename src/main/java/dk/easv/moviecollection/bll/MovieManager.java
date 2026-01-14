package dk.easv.moviecollection.bll;

import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.dal.db.IMovieCollectionDataAccess;
import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;

import java.util.List;

public class MovieManager {

    public MovieManager() throws Exception {}

    private IMovieCollectionDataAccess movieDAO = new MovieCollectionDAO_DB();

    public List<Movie> loadMovies() throws Exception {

        return movieDAO.loadMovies();

    }
    public void deleteMovie(Movie movie) throws Exception {
        movieDAO.deleteMovie(movie);
    }

    public void createMovie(Movie movie) throws Exception {

        movieDAO.createMovie(movie);

    }

    public void updateLastOpened(Movie movie) throws Exception {
        movieDAO.updateLastOpened(movie);
    }

    public void updateRating(Movie movie) throws Exception {
        movieDAO.updateRating(movie);
    }

}
