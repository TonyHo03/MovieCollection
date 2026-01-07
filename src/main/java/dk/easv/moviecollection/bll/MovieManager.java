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

}
