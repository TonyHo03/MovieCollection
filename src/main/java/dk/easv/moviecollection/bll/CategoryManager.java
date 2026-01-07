package dk.easv.moviecollection.bll;

import dk.easv.moviecollection.BE.Category;
import dk.easv.moviecollection.BE.Movie;
import dk.easv.moviecollection.dal.db.IMovieCollectionDataAccess;
import dk.easv.moviecollection.dal.db.MovieCollectionDAO_DB;

import java.util.List;

public class CategoryManager {

    public CategoryManager() throws Exception {}

    private IMovieCollectionDataAccess movieDAO = new MovieCollectionDAO_DB();

    public List<Category> loadCategories() throws Exception {

        return movieDAO.loadCategories();

    }

}


